package com.iteye.wwwcomy.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * JD page parser, to locate comment, brand, model, price, etc.
 * 
 * @author wwwcomy
 * 
 */
public class HtmlParser4Jd {
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
		return result;
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
