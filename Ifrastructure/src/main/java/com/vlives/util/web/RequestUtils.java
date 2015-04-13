/**
 * @(#)RequestUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.web;

import javax.servlet.http.HttpServletRequest;

import com.vlives.util.StringUtils;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-7
 */
public class RequestUtils {
	/**
	 * 请求是否支持GZIP压缩
	 * @author jianguo.xu
	 * @param request
	 */
	public static final boolean isSupportGzip(HttpServletRequest request) {
		 String value = getHeader(request, "Accept-Encoding");
		 return value ==null?false:value.indexOf("gzip") >= 0;
	}
	
	 
	public static final String getHeader(HttpServletRequest request,String headerName) {
		String value =  request.getHeader(headerName);
		return StringUtils.isNullOrEmpty(value)?null:value;
	}
	
}
