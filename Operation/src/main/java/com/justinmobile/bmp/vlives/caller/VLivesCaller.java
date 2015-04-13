/**
 * @(#)ConstantUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.bmp.vlives.caller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.cookie.BasicClientCookie;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.util.HttpClientUtil;
import com.justinmobile.bmp.util.HttpClientUtil.MethodType;

/**
 * 会生活接口调用类
 * @author wei.zhang
 * 
 */
public final class VLivesCaller {

	/**
	 * 会生活HTTP接口
	 *
	 */
	public static enum HTTP {
		
		/**
		 * 添加公告接口
		 */
		ANNOUNCEMENT_CREATE("announcementCreate"),
		
		/**
		 * 取得公告信息接口
		 */
		ANNOUNCEMENT_READ("announcementRead"),
		
		/**
		 * 删除公告接口
		 */
		ANNOUNCEMENT_DELETE("announcementDelete"),
		
		/**
		 * 用户点评信息查询
		 */
		USER_COMMENTS_READ("userCommentsRead"),
		
		/**
		 * 商户点评信息统计
		 */
		MERCHANT_COMMENTS_READ("merchantCommentsRead"),
		
		/**
		 * 网站用户反馈查询
		 */
		USER_FEEDBACK_READ("userFeedbackRead"),
		;

		private String interfaceName;
		private String subUrl;
		private String method;

		HTTP(String name) {
			this.interfaceName = name;
			System.out.println("name:" + name);
			String interfaceInfo = propertiesMap.get(name);
			System.out.println("interfaceInfo:" + interfaceInfo);
			if( interfaceInfo == null || "".equals(interfaceInfo) ){
				System.out.println("interfaceInfo == null or other");
				throw new PlatformException(PlatformErrorCode.CALLER_MISS_ERROR);
			}
			String params[] = interfaceInfo.split(":");
			if (params.length < 2) {
				System.out.println("params.length < 2");
				throw new PlatformException(PlatformErrorCode.CALLER_PARSE_ERROR);
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
		 * 统一调用接口的方法：vlivesCaller.Interface.接口名.invoke(headers, params)
		 * 
		 * @param headers
		 *            请求时头信息参数map
		 * @param params
		 *            请求时一般参数map
		 * @return json对象
		 * @throws ClientProtocolException 
		 * @throws ParseException
		 * @throws IOException
		 */
		public JSONObject invoke(Map<String, Object> headers,
				Map<String, Object> params) throws ClientProtocolException, IOException  {
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
			//
			BasicClientCookie cookie = new BasicClientCookie(getVlivesCookieName(), getVlivesCookieValue());
			cookie.setPath(getVlivesCookiePath());
			cookie.setDomain(getVlivesCookieDomain());
			Date expiryDate = new Date();
			expiryDate.setTime(expiryDate.getTime() + 24*60*60*1000 );
			cookie.setExpiryDate(expiryDate);
			//
			return HttpClientUtil.getResponseByJson(baseUrl + subUrl, headers,
					params, methodType, cookie);
		}

	/**
	 * 统一调用接口的方法：vlivesCaller.Interface.接口名.invokeFile(headers, params,file)
	 * 
	 * @param headers
	 *            请求时头信息参数map
	 * @param params
	 *            请求时一般参数map
	 *            	 * @param params
	 *            上传文件
	 * @return json对象
	 * @throws ClientProtocolException 
	 * @throws ParseException
	 * @throws IOException
	 */
	public JSONObject invokeFile(Map<String, Object> headers,
			Map<String, Object> params , File file) throws ClientProtocolException, IOException  {
			return HttpClientUtil.getResponseByFile(baseUrl + subUrl, headers, params ,file);
	}
}
	private static String propertiesFile = "/config/peripheral/vlives.properties";

	private static final Log LOG = LogFactory.getLog(VLivesCaller.class);

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
			ins = VLivesCaller.class.getResourceAsStream(propertiesFile);
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
	
	public static String getVlivesCookieDomain() {
		try {
			URL url = new URL(baseUrl);
			return url.getHost();
		} catch (MalformedURLException e) {
			return "127.0.0.1";
		}
	}
	
	public static String getVlivesCookieName() {
		String value = propertiesMap.get("vlivesCookieName");
		return value == null?"auto_login":value;
	}
	
	public static String getVlivesCookieValue() {
		String value = propertiesMap.get("vlivesCookieValue");
		return value == null?"MSYxMzg4MDI4NDE3Mg":value;
	}
	
	public static String getVlivesCookiePath() {
		String value = propertiesMap.get("vlivesCookiePath");
		return value == null?"/":value;
	}
}
