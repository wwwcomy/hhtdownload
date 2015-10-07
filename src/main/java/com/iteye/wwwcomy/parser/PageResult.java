package com.iteye.wwwcomy.parser;

import java.util.ArrayList;
import java.util.Map;

/**
 * POJO 保存页面解析结果
 * 
 * @author wwwcomy
 * 
 */
public class PageResult {
	private String resourceName;
	private ArrayList<Map<String, String>> resourceList;

	public ArrayList<Map<String, String>> getResourceList() {
		return resourceList;
	}

	public void setResourceList(ArrayList<Map<String, String>> resourceList) {
		this.resourceList = resourceList;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
