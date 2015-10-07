package com.iteye.wwwcomy.parser;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class HtmlParserTest {
	@Test
	public void canGetResult() throws Exception {
		String html = FileUtils.readFileToString(new File("d:/data/火火兔爱儿歌 - - 火火兔.html"));
		HtmlParser parser = HtmlParser.getInstance();
		PageResult result = parser.parse(html);
		List<Map<String, String>> list = result.getResourceList();
		System.out.println(result.getResourceName());
		System.out.println(list);
	}
}
