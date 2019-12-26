package com.iteye.wwwcomy.parser;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class HtmlParser4JdTest {

	@Test
	public void canGetResult() throws Exception {
		String html = FileUtils
				.readFileToString(new File(HtmlParser4Jd.class.getClassLoader().getResource("jd.html").toURI()));
		HtmlParser4Jd parser = HtmlParser4Jd.getInstance();
		PageResult4Jd result = parser.parse(html);
		System.out.println(result);
	}
}
