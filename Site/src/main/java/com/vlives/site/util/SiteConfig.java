package com.vlives.site.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class SiteConfig {

	private static String propertiesFile = "/config/config.properties";

	private static Map<String, String> propertiesMap = new HashMap<String, String>();

	static {
		initProperty();
	}

	private static void initProperty() {
		InputStream ins = null;
		Properties properties = new Properties();
		try {
			ins = SiteConfig.class.getResourceAsStream(propertiesFile);
			properties.load(ins);

			Set<Entry<Object, Object>> entrySet = properties.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				propertiesMap.put((String) entry.getKey(),
						((String) entry.getValue()).trim());
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("无法找到conf/config.properties");
		} catch (IOException e) {
			throw new RuntimeException("无法读取config.properties");
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (Exception e) {
					// Do Nothing
				}
			}
		}
	}
	
	// 网站地址
	public static final String SITE_URL = getString("site.url");

	// 商户平台地址
	public static final String BOSS_URL = getString("boss.url");
	
	// 图片上传目录
	public static final String IMAGE_UPLOAD_PATH = getString("image.upload.path");
	
	/**
	 * QQ APPID
	 */
	public static final String QQ_APPID = getString("qq.appId");

	/**
	 * QQ APPKEY
	 */
	public static final String QQ_APPKEY = getString("qq.appKey");
	
	/**
	 * QQ CALLBACKURL
	 */
	public static final String QQ_CALLBACKURL = getString("qq.callbackUrl");
	
	/**
	 * SINA CALLBACKURL
	 */
	public static final String SINA_CALLBACKURL = getString("sina.callbackUrl");
	
	/**
	 * SINA CONSUMER KEY
	 */
	public static final String SINA_CONSUMER_KEY = getString("sina.consumer.key");
	
	/**
	 * SINA CONSUMER SECRET
	 */
	public static final String SINA_CONSUMER_SECRET = getString("sina.consumer.secret");
	
	private static String getString(String proKey) {
		return propertiesMap.get(proKey);
	}
}
