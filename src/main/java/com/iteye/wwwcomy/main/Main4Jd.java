package com.iteye.wwwcomy.main;

import com.iteye.wwwcomy.crawler.Crawler4Jd;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Entrance of the application
 * 
 * @author wwwcomy
 * 
 */
public class Main4Jd {
	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "/data/music";
		int numberOfCrawlers = 7;
		int maxDepthOfCrawling = 5;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setMaxDepthOfCrawling(maxDepthOfCrawling);

		// int maxPagesToFetch = 2000;
		// config.setMaxPagesToFetch(maxPagesToFetch);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first URLs that
		 * are fetched and then the crawler starts following links which are found in
		 * these pages
		 */
		controller.addSeed("https://mall.jd.com/index-1000000158.html");

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code will
		 * reach the line after this only when crawling is finished.
		 */
		controller.start(Crawler4Jd.class, numberOfCrawlers);
	}
}
