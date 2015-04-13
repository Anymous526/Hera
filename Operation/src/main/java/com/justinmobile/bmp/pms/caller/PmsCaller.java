/**
 * @(#)ConstantUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.bmp.pms.caller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;

import com.justinmobile.bmp.util.HttpClientUtil;
import com.justinmobile.bmp.util.HttpClientUtil.MethodType;

/**
 * 云掌柜接口调用类
 * @author wei.zhang
 * 
 */
public final class PmsCaller {

	/**
	 * 云掌柜HTTP接口
	 *
	 */
	public static enum HTTP {
		/**
		 * 商户状态通知接口 - PMS
		 */
		MERCHANT_NOTIFY("merchantNotify"),
		;

		private String interfaceName;
		private String subUrl;
		private String method;

		HTTP(String name) {
			this.interfaceName = name;
			String interfaceInfo = propertiesMap.get(name);
			String params[] = interfaceInfo.split(":");
			if (params.length < 2) {
				throw new RuntimeException("Illegal argument found.");
			}
			this.subUrl = params[0];
			this.method = params[1];
		}

		public String getInterfaceName() {
			return interfaceName;
		}

		public String getSubUrl() {
			return subUrl;
		}

		public void setSubUrl(String subUrl) {
			this.subUrl = subUrl;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		/**
		 * 统一调用接口的方法：CloudBossCaller.Interface.接口名.invoke(headers, params)
		 * 
		 * @param headers
		 *            请求时头信息参数map
		 * @param params
		 *            请求时一般参数map
		 * @return json对象
		 * @throws ParseException
		 * @throws IOException
		 */
		public JSONObject invoke(Map<String, Object> headers,
				Map<String, Object> params) throws ParseException, IOException {
			MethodType methodType = null;
			if (method.equalsIgnoreCase("GET")) {
				methodType = MethodType.GET;
			} else if (method.equalsIgnoreCase("POST")) {
				methodType = MethodType.POST;
			} else if (method.equalsIgnoreCase("PUT")) {
				methodType = MethodType.PUT;
			} else if (method.equalsIgnoreCase("DELETE")) {
				methodType = MethodType.DELETE;
			} else {
				throw new IllegalArgumentException(this.interfaceName
						+ "has a illegal http method type.");
			}
			return HttpClientUtil.getResponseByJson(baseUrl + subUrl, headers,
					params, methodType, null);
		}
	}

	private static String propertiesFile = "/config/peripheral/pms.properties";

	private static final Log LOG = LogFactory.getLog(PmsCaller.class);

	private static Map<String, String> propertiesMap;

	private static String baseUrl;

	static {
		initProperty();
		baseUrl = propertiesMap.get("baseUrl");
		if (baseUrl == null) {
			throw new RuntimeException("the property baseUrl can not be NULL.");
		}
	}

	private static void initProperty() {
		InputStream ins = null;
		Properties properties = new Properties();
		try {
			ins = PmsCaller.class.getResourceAsStream(propertiesFile);
			properties.load(ins);
			propertiesMap = new HashMap<String, String>();
			Set<Entry<Object, Object>> entrySet = properties.entrySet();

			for (Entry<Object, Object> entry : entrySet) {
				propertiesMap.put((String) entry.getKey(),
						(String) entry.getValue());
			}
		} catch (FileNotFoundException e) {
			LOG.info("file not found." + propertiesFile, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOG.info(e);
			throw new RuntimeException(e);
		}

	}

}
