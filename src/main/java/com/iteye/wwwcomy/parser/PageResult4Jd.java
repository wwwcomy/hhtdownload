package com.iteye.wwwcomy.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO to save the result for JD page
 * 
 * @author wwwcomy
 * 
 */
public class PageResult4Jd {
	private String pageUrl;
	private String pageId;
	private String model;
	private String brand;
	/**
	 * This should be the price displaying in the page: jdPrice.p
	 */
	private String displayPrice;
	/**
	 * This should be the plus member price: jdPrice.tpp
	 */
	private String plusPrice;
	/**
	 * This should be the usual price: jdPrice.op
	 */
	private String currentPrice;
	private int saleCount;
	private Map<String, String> attributes = new HashMap<>();

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPlusPrice() {
		return plusPrice;
	}

	public void setPlusPrice(String plusPrice) {
		this.plusPrice = plusPrice;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(String displayPrice) {
		this.displayPrice = displayPrice;
	}

	@Override
	public String toString() {
		return "PageResult4Jd [pageUrl=" + pageUrl + ", pageId=" + pageId + ", model=" + model + ", brand=" + brand
				+ ", displayPrice=" + displayPrice + ", plusPrice=" + plusPrice + ", currentPrice=" + currentPrice
				+ ", saleCount=" + saleCount + ", attributes=" + attributes + "]";
	}

}
