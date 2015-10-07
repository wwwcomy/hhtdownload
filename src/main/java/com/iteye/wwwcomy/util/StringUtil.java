package com.iteye.wwwcomy.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static String toSafePath(String input) {
		if (StringUtils.isEmpty(input)) {
			return input;
		}
		return input.replaceAll("[\\r\\n\r\n&nbsp;\\u00A0]", "").replaceAll("[:\\\\/*\"?|<>']", "").trim();
	}
}
