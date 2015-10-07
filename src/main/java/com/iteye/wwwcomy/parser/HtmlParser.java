package com.iteye.wwwcomy.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.iteye.wwwcomy.constant.Const;
import com.iteye.wwwcomy.util.StringUtil;

/**
 * 页面解析，找出专辑名称，找出资源的地址和名称，最终返回PageResult对象
 * 
 * @author wwwcomy
 * 
 */
public class HtmlParser {
	private static class HOLDER {
		private static final HtmlParser instance = new HtmlParser();
	}

	private static HtmlParser instance = HOLDER.instance;

	public static HtmlParser getInstance() {
		return instance;
	}

	public PageResult parse(String sHtml) {
		Document doc = Jsoup.parse(sHtml);
		PageResult result = new PageResult();
		genResourceList(doc, result);
		genTitle(doc, result);
		return result;
	}

	private void genResourceList(Document doc, PageResult result) {
		ArrayList<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		Elements elements = doc.getElementsByTag("li");
		Iterator<Element> i = elements.iterator();
		while (i.hasNext()) {
			Element element = i.next();
			String refUrl = element.attr("ref");
			String title = element.attr("title");
			if (StringUtils.isEmpty(refUrl) || StringUtils.isEmpty(title)) {
				continue;
			}
			Map<String, String> map = new HashMap<String, String>(4);
			map.put(Const.RESOURCE_URL, refUrl.trim());
			title = StringUtil.toSafePath(title);
			map.put(Const.TITLE, title.trim());
			resultList.add(map);
		}
		result.setResourceList(resultList);
	}

	private void genTitle(Document doc, PageResult result) {
		Elements elements = doc.getElementsByTag("h2");
		Iterator<Element> i = elements.iterator();
		while (i.hasNext()) {
			Element element = i.next();
			Elements es = element.children();
			if (es.size() > 0) {
				Node node = element.child(0);
				if (node instanceof Element) {
					Element spanElement = (Element) node;
					if ("span".equals(spanElement.tagName()) && ("专辑").equals(spanElement.ownText())) {
						String title = StringUtil.toSafePath(element.ownText());
						result.setResourceName(title);
						return;
					}
				}
			}
		}
	}

}
