package com.iteye.wwwcomy.download;

import java.io.File;

import org.junit.Test;

import junit.framework.Assert;

public class ResourceDownloaderTest {
	@Test
	public void canDownload() throws Exception {
		String sUrl = "http://resource.alilo.com.cn/Upload/Audio/2015/03/03/09/15310_192.mp3";
		String fileName = "Twinkle twinkle little star";
		ResourceDownloader downloader = new ResourceDownloader();
		downloader.download(sUrl, fileName, "d:/data");
		Assert.assertTrue(new File("d:/data/Twinkle twinkle little star.mp3").exists());
	}

	@Test
	public void canGetSuffix() {
		String url = "http://resource.alilo.com.cn/Upload/Audio/2015/03/03/09/15310_192.mp3";
		String suffix = ResourceDownloader.getFileSuffix(url);
		Assert.assertEquals(".mp3", suffix);
	}
}
