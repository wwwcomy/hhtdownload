package com.iteye.wwwcomy.worker;

import com.iteye.wwwcomy.parser.HtmlParser;
import com.iteye.wwwcomy.parser.PageResult;

/**
 * JD worker
 * 
 * @author wwwcomy
 * 
 */
public class Worker4Jd implements Runnable {
	private String htmlContent;
	private HtmlParser parser = HtmlParser.getInstance();

	public Worker4Jd(String html) {
		this.htmlContent = html;
	}

	@Override
	public void run() {
		PageResult result = parser.parse(htmlContent);
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

}
