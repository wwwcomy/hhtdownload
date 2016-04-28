package com.iteye.wwwcomy.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iteye.wwwcomy.download.ResourceDownloader;

/**
 * 亲子悦读经典童话书下载,默认的文件名用的是"首字母/首字母.mp3"
 * 
 * @author wwwcomy
 *
 */
public class StoryReaderDownloaderMain {

    private static final Logger logger = LoggerFactory.getLogger(StoryReaderDownloaderMain.class);
    private static final String BASE_URL = "http://123.57.226.8:8080/";
    private static Map<String, String> stories = new HashMap<String, String>();

    static {
        stories.put("拔萝卜", "blb");
        stories.put("龟兔赛跑", "gtsp");
        stories.put("猴子捞月", "hzly");
        stories.put("小马过河", "xmgh");
        stories.put("小猫钓鱼", "xmdy");
        stories.put("杰克和豆茎", "jkhdj");
        stories.put("小红帽", "xhm");
        stories.put("丑小鸭", "cxy");
        stories.put("白雪公主", "bxgz");
        stories.put("糖果屋历险记", "tgwlxj");
        stories.put("木偶奇遇记", "moqyj");
        stories.put("狼和七只小羊", "lhqzxy");
        stories.put("三只小猪", "szxz");
        stories.put("灰姑娘", "hgn");
        stories.put("渔夫和魔鬼", "yfhmg");
        stories.put("三个和尚", "sghs");
        stories.put("莴苣姑娘", "wjgn");
        stories.put("青蛙王子", "qwwz");
        stories.put("雪孩子", "xhz");
        stories.put("拇指姑娘", "mzgn");
        stories.put("野天鹅", "yte");
        stories.put("冰雪皇后", "bxhh");
        stories.put("皇帝的新装", "hdxz");// 汉字与拼音不符
        stories.put("卖火柴的小女孩", "mhcxgn");// 汉字与拼音不符
        stories.put("海的女儿", "hdne");
        stories.put("睡美人", "smr");
        stories.put("阿拉丁神灯", "aldds");// 汉字与拼音不符
        stories.put("美女与野兽", "mnyys");
        stories.put("渔夫和金鱼", "yfhjy");
        stories.put("小蝌蚪找妈妈", "xkdzmm");
    }

    public static void main(String[] args) {
        ResourceDownloader downloader = new ResourceDownloader();
        Set<Entry<String, String>> entrySet = stories.entrySet();
        String localPath = "D:/temp/stories";
        for (Entry<String, String> entry : entrySet) {
            String url = BASE_URL + entry.getValue() + "/" + entry.getValue() + ".mp3";
            String fileName = entry.getKey();
            try {
                downloader.download(url, fileName, localPath);
                logger.info("Story downloaded:" + fileName);
            } catch (Exception e) {
                logger.error("Cannot download:" + fileName, e);
            }
        }
    }

}
