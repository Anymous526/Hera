/**
 * @(#)ConstantUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.bmp.cloudboss.caller;

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
 * 云掌柜接口调用类
 * @author wei.zhang
 * 
 */
public final class CloudBossCaller {

	/**
	 * 云掌柜HTTP接口
	 *
	 */
	public static enum HTTP {
		/**
		 * 测试用接口
		 */
		TEST("testInterface"),
		/**
		 * 违禁字查询接口
		 */
		BANED_WORD_READ("banedWordRead"),
		/**
		 * 违禁字增加接口
		 */
		BANED_WORD_CREATE("banedWordCreate"),
		/**
		 * 违禁字删除接口
		 */
		BANED_WORD_DELETE("banedWordDelete"),
		/**
		 * 创建营销活动接口
		 */
		SMS_CREATE("smsCreate"),
		/**
		 * 审核营销活动接口
		 */
		SMS_AUDIT("smsAudit"),
		/**
		 * 修改营销活动接口
		 */
		SMS_UPDATE("smsUpdate"),
		/**
		 * 查询营销活动接口
		 */
		SMS_READ("smsRead"),
		/**
		 * 注销营销活动接口
		 */
		SMS_DELETE("smsDelete"),
		/**
		 * 商户创建接口
		 */
		MERCHANT_CREATE("merchantCreate"),
		/**
		 * 商户修改接口
		 */
		MERCHANT_UPDATE("merchantUpdate"),
		/**
		 * 商户审核接口
		 */
		MERCHANT_AUDIT("merchantAudit"),
		/**
		 * 商户冻结接口
		 */
		MERCHANT_FREEZE("merchantFreeze"),
		/**
		 * 商户解冻接口
		 */
		MERCHANT_UNFREEZE("merchantUnfreeze"),
		/**
		 * 商户解冻接口
		 */
		MERCHANT_DELETE("merchantDelete"),
		/**
		 * 父商户列表查询接口
		 */
		MERCHANT_PARENT_READ("merchantParentRead"),
		/**
		 * 商户列表查询接口
		 */
		MERCHANT_LIST_READ("merchantListRead"),
		/**
		 * 导出所有商户信息
		 */
		MERCHANT_EXPORT("merchantExport"),
		/**
		 * 商户查询接口
		 */
		MERCHANT_READ("merchantRead"),
		/**
		 * pos序列号查询商户接口
		 */
		MERCHANT_POS_READ("merchantPosRead"),
		/**
		 * 商户明细查询接口
		 */
		MERCHANT_DETAIL_READ("merchantDetailRead"),
		/**
		 * 商户编码唯一性检查接口
		 */
		MERCHANT_CODE_CHECK("merchantCodeCheck"),
		/**
		 * 商户编码唯一性检查接口
		 */
		MERCHANT_MOBILE_CHECK("merchantMobileCheck"),
		/**
		 * 短信编码唯一性检查接口
		 */
		MERCHANT_NUM_CHECK("merchantNumCheck"),
		
		/**
		 * 短信数量添加
		 */
		SMS_QUANTITY_ADD("smsQuantityAdd"),
		
		/**
		 * 会员查看列表查询接口
		 */
		MEMBER_LIST_READ("memberListRead"),
		
		/**
		 * 会员数据导入接口
		 */
		MEMBER_UPLOAD_ADD("memberUploadAdd"),
		
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
		 * 查询系统剩余短信条数
		 */
		SMS_ALL_QUANTITY_READ("smsAllQuantityRead"),
		
		/**
		 * 区域列表
		 */
		AREA_LIST("areaList"),

		/**
		 * 	行业分类信息列表
		 */
		MERCHANTCATEGORY_LIST("merchantcategoryList"),
		
		/**
		 ** 	新增行业分类信息
		 */
		MERCHANTCATEGORY_CREATE("merchantcategoryCreate"),
		
		/**
		 * 	删除行业分类信息
		 */
		MERCHANTCATEGORY_DELETE("merchantcategoryDelete"),

		/**
		 * 行业类别列表
		 */
		CATEGORIES_LIST("categoriesList"),
		/**
		 * 子商户列表
		 */
		SUB_MERCHANT_LIST_READ("subMerchantListRead"),
		
		/**
		 * 会员查询导出
		 */
		MEMBER_REPORT("memberReport"),
		/**
		 * 商户查询导出
		 */
		MERCHANT_REPORT("merchantReport"),
		/**
		 * 优惠券列表查询
		 */
		COUPON_LIST("couponList"),
		/**
		 * 优惠券详细查询
		 */
		COUPON_DETAIL("couponDetail"),
		/**
		 * 优惠券审核
		 */
		COUPON_AUDIT("couponAudit"),
		/**
		 * 城市报表
		 */
		CITY_REPORT("cityReport"),
		;
		

		private String interfaceName;
		private String subUrl;
		private String method;

		HTTP(String name) {
			this.interfaceName = name;
			String interfaceInfo = propertiesMap.get(name);
			if( interfaceInfo == null || "".equals(interfaceInfo) ){
				throw new PlatformException(PlatformErrorCode.CALLER_MISS_ERROR);
			}
			String params[] = interfaceInfo.split(":");
			if (params.length < 2) {
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
		 * 统一调用接口的方法：CloudBossCaller.Interface.接口名.invoke(headers, params)
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
			BasicClientCookie cookie = new BasicClientCookie(getCloudbossCookieName(), getCloudbossCookieValue());
			cookie.setPath(getCloudbossCookiePath());
			cookie.setDomain(getCloudbossCookieDomain());
			Date expiryDate = new Date();
			expiryDate.setTime(expiryDate.getTime() + 24*60*60*1000 );
			cookie.setExpiryDate(expiryDate);
			//
			return HttpClientUtil.getResponseByJson(baseUrl + subUrl, headers,
					params, methodType, cookie);
		}

	/**
	 * 统一调用接口的方法：CloudBossCaller.Interface.接口名.invokeFile(headers, params,file)
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
	private static String propertiesFile = "/config/peripheral/cloudboss.properties";

	private static final Log LOG = LogFactory.getLog(CloudBossCaller.class);

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
			ins = CloudBossCaller.class.getResourceAsStream(propertiesFile);
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
	
	public static String getCloudbossCookieDomain() {
		try {
			URL url = new URL(baseUrl);
			return url.getHost();
		} catch (MalformedURLException e) {
			return "127.0.0.1";
		}
	}
	
	public static String getCloudbossCookieName() {
		String value = propertiesMap.get("cloudbossCookieName");
		return value == null?"auto_login":value;
	}
	
	public static String getCloudbossCookieValue() {
		String value = propertiesMap.get("cloudbossCookieValue");
		return value == null?"MSYxMzg4MDI4NDE3Mg":value;
	}
	
	public static String getCloudbossCookiePath() {
		String value = propertiesMap.get("cloudbossCookiePath");
		return value == null?"/":value;
	}
}
