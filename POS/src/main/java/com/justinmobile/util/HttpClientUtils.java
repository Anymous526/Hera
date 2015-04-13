/**
 * @(#)HttpClientUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-25
 */
public class HttpClientUtils {
	private static final Log LOG = LogFactory.getLog(HttpClientUtils.class);
	public static void doGet(String url) {
		HttpParams basicParams = new BasicHttpParams();
		basicParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpRequestBase reqeust = new HttpGet(url);
		try {
			httpclient.execute(reqeust);
		} catch (IOException e) {
			LOG.error("http client do get error.", e);
		}
		finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
	
}
