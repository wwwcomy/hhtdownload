package com.iteye.wwwcomy.parser;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.iteye.wwwcomy.entity.exception.InvalidParameterException;
import com.iteye.wwwcomy.util.MyHttpClient;

/**
 * JD page parser, to locate comment, brand, model, price, etc.
 * 
 * @author wwwcomy
 * 
 */
public class HtmlParser4Jd {
	private static final Logger logger = LoggerFactory.getLogger(HtmlParser4Jd.class);

	private static class HOLDER {
		private static final HtmlParser4Jd instance = new HtmlParser4Jd();
	}

	private static HtmlParser4Jd instance = HOLDER.instance;

	public static HtmlParser4Jd getInstance() {
		return instance;
	}

	public PageResult4Jd parse(String sHtml) {
		Document doc = Jsoup.parse(sHtml);
		PageResult4Jd result = new PageResult4Jd();
		populateBasicInformation(doc, result);
		populatePrice(doc, result);
		return result;
	}

	/**
	 * We need 3 parameters to further get the price information using the following
	 * URL. The 3 URLs are: <br />
	 * 1. pageId <br />
	 * 2. venderId <br />
	 * 3. catId <br />
	 * Need to extract 2 and 3 from the origin page. Not having good solution yet,
	 * have to use regular expression
	 * https://c0.3.cn/stock?skuId={pageId}&area=2_2813_51976_0&venderId={venderId}&buyNum=1&choseSuitSkuIds=&cat={catId}&extraParam={%22originid%22:%221%22}&fqsp=0&pdpin=&ch=1
	 * 
	 * When calling this URL, the pageId must be a known parameter
	 * 
	 * @param doc
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void populatePrice(Document doc, PageResult4Jd result) {
		Pattern catPattern = Pattern.compile("^.*cat\\s*:\\s*\\[(.*?)\\],.*", Pattern.DOTALL | Pattern.MULTILINE);
		Pattern vendorPattern = Pattern.compile("^.*venderId\\s*:\\s*(\\d+),.*", Pattern.DOTALL | Pattern.MULTILINE);
		String page = doc.html();
		Matcher m = catPattern.matcher(page);
		Matcher m2 = vendorPattern.matcher(page);
		if (!m.matches() || !m2.matches()) {
			logger.warn("No cat or vendor info found for item {}", result.getPageId());
			return;
		}
		String cat = m.group(1);
		String vendorId = m2.group(1);
		String url = "https://c0.3.cn/stock?area=2_2813_51976_0&buyNum=1&choseSuitSkuIds=&fqsp=0&pdpin=&ch=1";
		URIBuilder uriBuilder;
		try {
			uriBuilder = new URIBuilder(url);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			return;
		}
		uriBuilder.addParameter("skuId", result.getPageId());
		uriBuilder.addParameter("venderId", vendorId);
		uriBuilder.addParameter("cat", cat);
		uriBuilder.addParameter("extraParam", "{\"originid\":\"1\"}");
		String response = MyHttpClient.getInstance().get(uriBuilder.toString());
		Gson g = new Gson();
		HashMap responseMap = g.fromJson(response, HashMap.class);
		Map priceMap = getSubAsMap(getSubAsMap(responseMap, "stock"), "jdPrice");
		result.setCurrentPrice(String.valueOf(priceMap.get("op")));
		result.setPlusPrice(String.valueOf(priceMap.get("tpp")));
	}

	@SuppressWarnings("rawtypes")
	private Map getSubAsMap(Map m, String key) {
		if (!m.containsKey(key)) {
			logger.warn("No key '{}' found in map, skip price handling", key);
			throw new InvalidParameterException("skip price handling");
		}
		return (Map) m.get(key);
	}

	private void populateBasicInformation(Document doc, PageResult4Jd result) {
		getBrand(doc, result);
		getModelAndParamaters(doc, result);
	}

	private void getModelAndParamaters(Document doc, PageResult4Jd result) {
		Elements elements = doc.select("ul.parameter2.p-parameter-list");
		if (elements.isEmpty()) {
			result.setModel("NA");
			result.setPageId("NA");
			return;
		}
		Element ul = elements.get(0);
		String brand = ul.child(0).attr("title");
		String pageId = ul.child(1).attr("title");
		result.setModel(brand);
		result.setPageId(pageId);
		for (int i = 2; i < ul.children().size(); i++) {
			result.getAttributes().put("attribute" + i, ul.child(i).html());
		}
	}

	private void getBrand(Document doc, PageResult4Jd result) {
		Element e = doc.getElementById("parameter-brand");
		if (null == e || null == e.child(0)) {
			result.setBrand("NA");
			return;
		}
		String brand = e.child(0).attr("title");
		result.setBrand(brand);
	}

}
