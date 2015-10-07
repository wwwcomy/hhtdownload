package com.iteye.wwwcomy.download;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iteye.wwwcomy.constant.Const;
import com.iteye.wwwcomy.parser.PageResult;

/**
 * 资源下载类，通过输入URL,另存文件名和本地存储路径保存
 * 
 * @author wwwcomy
 * 
 */
public class ResourceDownloader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceDownloader.class);
	private static final String LOCAL_PATH = "d:/data/download";

	private static class HOLDER {
		private static final ResourceDownloader instance = new ResourceDownloader();
	}

	private static ResourceDownloader instance = HOLDER.instance;

	public static ResourceDownloader getInstance() {
		return instance;
	}

	public boolean download(String sUrl, String fileName, String localPath) throws Exception {
		String targetPath = (localPath.endsWith("/") || localPath.endsWith("\\")) ? localPath : (localPath + "/");
		String suffix = getFileSuffix(sUrl);
		File targetFile = new File(targetPath + fileName + suffix);
		URL url = new URL(sUrl);
		URLConnection conn = url.openConnection();
		InputStream inStream = conn.getInputStream();
		FileUtils.writeByteArrayToFile(targetFile, IOUtils.toByteArray(inStream));
		return true;
	}

	public static String getFileSuffix(String url) {
		int dotIndex = url.lastIndexOf(".");
		return url.substring(dotIndex);
	}

	public void download(PageResult result) {
		List<Map<String, String>> list = result.getResourceList();
		String folderName = LOCAL_PATH + "/" + result.getResourceName();
		Iterator<Map<String, String>> i = list.iterator();
		while (i.hasNext()) {
			Map<String, String> entry = i.next();
			try {
				download(entry.get(Const.RESOURCE_URL), entry.get(Const.TITLE), folderName);
			} catch (Exception e) {
				LOGGER.error("Failed to download resource from URL:" + entry.get(Const.RESOURCE_URL), e);
			}
		}

	}
}
