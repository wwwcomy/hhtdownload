package com.iteye.wwwcomy.worker;

import com.iteye.wwwcomy.download.ResourceDownloader;
import com.iteye.wwwcomy.parser.HtmlParser;
import com.iteye.wwwcomy.parser.PageResult;

/**
 * Facade for ResourceDownloader & HtmlParser
 * 
 * @author wwwcomy
 * 
 */
public class Worker implements Runnable {
	private String htmlContent;
	private ResourceDownloader downloader = ResourceDownloader.getInstance();
	private HtmlParser parser = HtmlParser.getInstance();

	public Worker(String html) {
		this.htmlContent = html;
	}

	@Override
	public void run() {
		PageResult result = parser.parse(htmlContent);
		downloader.download(result);
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

}
