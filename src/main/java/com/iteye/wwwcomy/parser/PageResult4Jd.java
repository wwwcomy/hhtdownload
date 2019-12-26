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
	private String pageId;
	private String model;
	private String brand;
	private int originalPrice;
	private int currentPrice;
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

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
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

	@Override
	public String toString() {
		return "PageResult4Jd [pageId=" + pageId + ", model=" + model + ", brand=" + brand + ", originalPrice="
				+ originalPrice + ", currentPrice=" + currentPrice + ", saleCount=" + saleCount + ", attributes="
				+ attributes + "]";
	}

}
