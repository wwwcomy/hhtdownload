package com.iteye.wwwcomy.util;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iteye.wwwcomy.entity.exception.HttpException;

public class MyHttpClient {
	private static final Logger logger = LoggerFactory.getLogger(MyHttpClient.class);
	private CloseableHttpClient httpClient;

	private static class HOLDER {
		private static final MyHttpClient instance = new MyHttpClient();
	}

	private static MyHttpClient instance = HOLDER.instance;

	public static MyHttpClient getInstance() {
		return instance;
	}

	private MyHttpClient() {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		httpClient = HttpClients.custom().setMaxConnTotal(200).setMaxConnPerRoute(20)
				.setDefaultRequestConfig(requestConfig).setRetryHandler(new StandardHttpRequestRetryHandler()).build();
	}

	/**
	 * Encode by yourself
	 * 
	 * @param url
	 * @return
	 */
	public String get(String url) {
		logger.info("HTTP GET for URL: {}", url);
		HttpGet httpGet = new HttpGet(url);
		try (CloseableHttpResponse response1 = httpClient.execute(httpGet)) {
			logger.info("HTTP GET response status line: {}", response1.getStatusLine());
			HttpEntity entity = response1.getEntity();
			try (InputStream is = entity.getContent()) {
				return IOUtils.toString(is, "GBK");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new HttpException(e.getMessage(), e);
		}

	}
}
