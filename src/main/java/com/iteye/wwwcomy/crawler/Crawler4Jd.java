package com.iteye.wwwcomy.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iteye.wwwcomy.util.StringUtil;
import com.iteye.wwwcomy.worker.Worker;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Class for crawling production information from JD
 * 
 * @author wwwcomy
 * 
 */
public class Crawler4Jd extends WebCrawler {

	private static final Logger LOGGER = LoggerFactory.getLogger(Crawler4Jd.class);

	private ExecutorService e = new ThreadPoolExecutor(20, 200, 5000L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(2000));

	/**
	 * This method receives two parameters. The first parameter is the page in which
	 * we have discovered this new url and the second parameter is the new url. You
	 * should implement this function to specify whether the given url should be
	 * crawled or not (based on your crawling logic). In this example, we are
	 * instructing the crawler to ignore urls that have css, js, git, ... extensions
	 * and to only accept urls that start with "http://www.ics.uci.edu/". In this
	 * case, we didn't need the referringPage parameter to make the decision.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return href.startsWith("https://item.jd.com/");
	}

	/**
	 * This function is called when a page is fetched and ready to be processed by
	 * your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		LOGGER.info("Visiting URL {}", url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String title = htmlParseData.getTitle();
			title = StringUtil.toSafePath(title);

			// TODO Use another multi-thread but not multi-crawler thread.
			new Worker(htmlParseData.getHtml()).run();

			// ThreadPoolUtil.execute(new Worker(htmlParseData.getHtml()));
			// try {
			// FileUtils.write(file, htmlParseData.getHtml());
			// } catch (IOException e) {
			// LOGGER.error("The content of URL" + url + " cannot be handled!");
			// LOGGER.error("Data cannot be saved", e);
			// }
		} else {
			LOGGER.warn("Not a HTML page!!!");
		}
	}
}
