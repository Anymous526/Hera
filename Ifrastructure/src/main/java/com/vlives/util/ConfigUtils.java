/**
 * @(#)ConstantUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2010-10-15
 */
public final class ConfigUtils {

	private static String propertiesFile = "/base/conf/config.properties";

	private static final Log LOG = LogFactory.getLog(ConfigUtils.class);

	private static Map<String, String> propertiesMap;

	static {
		initProperty();
	}

	private static void initProperty() {
		if (propertiesMap != null)
			return;
		InputStream ins = null;
		Properties properties = new Properties();
		try {
			ins = ConfigUtils.class.getResourceAsStream(propertiesFile);
			properties.load(ins);
			propertiesMap = new HashMap<String, String>();
			Set<Entry<Object, Object>> entrySet = properties.entrySet();

			for (Entry<Object, Object> entry : entrySet) {
				propertiesMap.put((String) entry.getKey(),
						((String) entry.getValue()).trim());
			}
		} catch (FileNotFoundException e) {
			LOG.info("file not found." + propertiesFile, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOG.info(e);
			throw new RuntimeException(e);
		}
	}

	public static String getString(String proKey) {
		return propertiesMap.get(proKey);
	}

	/**
	 * 得到文件或目录的正式路径<br/>
	 * 如果文件以 "/"那么表示绝对路径，直接返回<br/>
	 * 否则表示相对于当前classpath的相对路径
	 * 
	 * @author jianguo.xu
	 * @param pathKey
	 * @return
	 */
	private static final String getRealPath(String pathKey) {
		String path = propertiesMap.get(pathKey);
		if (path.startsWith("/")) {
			return path;
		} else {
			URL url = ConfigUtils.class.getResource("/" + path);
			return url.getPath();
		}
	}

	private static final String getWebRoot() {

		URL url = ConfigUtils.class.getResource("/");
		String path = url.getPath();
		if (path.endsWith("/WEB-INF/classes/")) {
			int beginIndex = path.length() - "WEB-INF/classes/".length();
			return path.substring(0, beginIndex);
		}
		return path;
	}

	private static final int getInt(String key) {
		return Integer.parseInt(propertiesMap.get(key));
	}
	
	private static final boolean getBoolean(String key) {
		String str = getString(key);
		if(str.equalsIgnoreCase("true")||str.equals("1")||str.equals("是")||str.equalsIgnoreCase("yes"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	/**
	 * 是否是开发模式
	 */
	public static final boolean IS_DEVELOPMENT_MODE = getBoolean("development.mode");
	/**
	 * 是否使用页面缓存
	 */
	public static final boolean USE_PAGE_CACHE = getBoolean("use.page.cache");
	 
	/**
	 * 网站中文名
	 */
	public static final String WEB_SITE_NAME = getString("web.site.name");
	
	/**
	 * 会生活网站的IP URL
	 */
	public static final String WEB_SITE_IP_URL = getString("web.site.ip.url");
	
	/**
	 * 商户管理平台的IP URL
	 */
	public static final String WEB_CLOUDBOSS_IP_URL = getString("web.cloudboss.ip.url");
	
	
	/**
	 * 商户默认头像地址(相对web路径)
	 */
	public static final String MERCHANT_DEFAULT_HEAD = getString("merchant.default.head");

	/**
	 * 得到网站地址
	 */
	public static final String WEBSITE = getString("web.site");
	/**
	 * 得到网站域名
	 */
	public static final String DOMAIN = getString("web.domain");
	/**
	 * 网站的绝对路径
	 */
	public static final String WEB_ROOT = getWebRoot();
	/**
	 * 网站上传图片的路径
	 */
	public static final String UPLOAD_IMAGE_PATH = getString("upload.image.path");

	

	/**
	 * 短信软件接口序列号
	 */
	public static final String SMS_SOFTWARE_SERIAL_NO = getString("sms.softwareSerialNo");

	/**
	 * 短信密码
	 */
	public static final String SMS_PASSWORD = getString("sms.password");
	/**
	 * 短信自定义关键字，要求密码一样(短信目前公司要)
	 */
	public static final String SMS_KEY = getString("sms.key");

	/**
	 * key是否注册，true：表示已注册，false：表示为注册。(调试为true)
	 */
	public static final Boolean SMS_ISREGIST_KEY = Boolean
			.valueOf(getString("sms.isRegistKey"));

	/**
	 * freemarker模板的目录
	 */
	public static final String FREEMARKER_TEMPLATE_DIR = getRealPath("freemarker.template.dir");

	/**
	 * 用户会员应用编码
	 */
	public static final String CUSTOMER_MEMBER_APP_CODE = getString("customer.member.app.code");

	/**
	 * 商户会员应用编码
	 */
	public static final String MERCHANT_MEMBER_APP_CODE = getString("merchant.member.app.code");

	/**
	 * 是否发送短信到亿美平台
	 */
	public static final boolean SMS_SEND = Boolean
			.valueOf(getString("sms.send"));
}
