/**
 * @(#)ResponseUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vlives.util.JSONUtils;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-1-15
 */
public class ResponseUtils {
	private static final Log LOG = LogFactory.getLog(ResponseUtils.class);

	private static final String DEFAULT_ENCODING = "utf-8";
	
	private static final int SUPPORT_MIN_SIZE = 2048;

	/**
	 * 向response里写json数据
	 * 
	 * @author jianguo.xu
	 * @param response
	 * @param object
	 */
	public static void writeJSON(HttpServletRequest request,
			HttpServletResponse response, Object object, String paramKey) {

		String jsonString = JSONUtils.serialize(object, paramKey);
		jsonString = jsonString == null ? "{}" : jsonString;

		boolean useGzip = RequestUtils.isSupportGzip(request);
		try {
			writeJSONToResponse(response, DEFAULT_ENCODING, jsonString, useGzip,
					false);
		} catch (IOException e) {
			try {
				LOG.error(e);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException e1) {
				throw new RuntimeException("system error.", e1);
			}
		}

		/*
		 * try { response.getWriter().write(jsonString == null ? "{}" :
		 * jsonString.toString()); } catch (IOException e) { try { LOG.error(e);
		 * response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); }
		 * catch (IOException e1) { throw new RuntimeException("system error.",
		 * e1); } }
		 */
	}

	public static void writeJSONToResponse(HttpServletResponse response,
			String encoding, String serializedJSON, boolean gzip,
			boolean noCache) throws IOException {

		response.setContentType("application/json;charset="+encoding);
		if (noCache) {
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
			response.setHeader("Pragma", "No-cache");
		}
		 
		if (gzip&&serializedJSON.length()>SUPPORT_MIN_SIZE) {
			response.addHeader("Content-Encoding", "gzip");
			GZIPOutputStream out = null;
			InputStream in = null;
			try {
				out = new GZIPOutputStream(response.getOutputStream());
				in = new ByteArrayInputStream(serializedJSON.getBytes(encoding));
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			} finally {
				if (in != null)
					in.close();
				if (out != null) {
					out.finish();
					out.close();
				}
			}
		} else {
			response.setContentLength(serializedJSON.getBytes(encoding).length);
			PrintWriter out = response.getWriter();
			out.print(serializedJSON);
		}
	}
	public static void writeXml(HttpServletResponse response, String xml) {
		response.setContentType("text/xml;charset=utf-8");
		try {
			response.getWriter().write(xml);
		} catch (IOException e) {
			try {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException e1) {
				 throw new RuntimeException("system error.",e1);
			}
		}
	}
}
