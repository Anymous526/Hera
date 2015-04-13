/**
 * @(#)PaginationParser.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.pagination;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-12-20
 */
public class PaginationParser {

	private HttpServletRequest request;

	@SuppressWarnings("unused")
	private HttpServletResponse response;

	private static final String DEFAULT_PAGE_SIZE_TARGET = "pageSize";

	private static final String DEFAULT_SKIP_SIZE_TARGET = "skipSize";

	private static final String DEFAULT_CURRENT_PAGE_TARGET = "currentPage";
	
	private static final String DEFAULT_BEGIN_TARGET="begin";

	private String pageSizeTarget = DEFAULT_PAGE_SIZE_TARGET;

	private String skipSizeTarget = DEFAULT_SKIP_SIZE_TARGET;

	private String currentPageTarget = DEFAULT_CURRENT_PAGE_TARGET;
	
	private String beginTarget=DEFAULT_BEGIN_TARGET;

	public PaginationParser(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public PaginationParser(HttpServletRequest request) {
		this.request = request;
	}

	 
	public Pagination parse() throws UnsupportedEncodingException {
		Pagination pagination = new Pagination();
		pagination.setCurrentPageTarget(currentPageTarget);
		pagination.setPageSizeTarget(pageSizeTarget);
		pagination.setSkipSizeTarget(skipSizeTarget);
		String encode = request.getCharacterEncoding();
		if (encode == null)
			encode = "utf8";
		String uri = request.getRequestURI();
		pagination.setUrl(uri);
		Enumeration<?> paramKeys= request.getParameterNames();
		Map<?, ?> params=request.getParameterMap();
		StringBuffer queryString = new StringBuffer();
		int begin=-1;
		if (paramKeys != null) {
			while (paramKeys.hasMoreElements()) {
				String name = (String) paramKeys.nextElement();
				String[] keys = (String[]) params.get(name);
				int length = keys.length;
				if (name.equals(currentPageTarget) && length > 0)
					pagination.setCurrentPage(Integer.parseInt(keys[0]));
				else if (name.equals(pageSizeTarget) && length > 0)
					pagination.setPageSize(Integer.parseInt(keys[0]));
				else if (name.equals(skipSizeTarget) && length > 0)
					pagination.setSkipSize(Integer.parseInt(keys[0]));
				else if(name.equals(beginTarget) && length > 0)
					begin=Integer.parseInt(keys[0]);
				else {
					for (int i = 0; i < length; i++) {
						String key = URLEncoder.encode(keys[i], encode);
						queryString.append(name).append("=").append(key)
								.append("&");
					}
				}

			}
			if (queryString.length() > 0)
				queryString.deleteCharAt(queryString.length() - 1);
			pagination.setQueryString(queryString.toString());
		}
		if(begin>0)
			pagination.setCurrentPage(begin/pagination.getPageSize()+1);
		return pagination;
	}

	public String getCurrentPageTarget() {
		return currentPageTarget;
	}

	public void setCurrentPageTarget(String currentPageTarget) {
		this.currentPageTarget = currentPageTarget;
	}

	public String getPageSizeTarget() {
		return pageSizeTarget;
	}

	public void setPageSizeTarget(String pageSizeTarget) {
		this.pageSizeTarget = pageSizeTarget;
	}

	public String getSkipSizeTarget() {
		return skipSizeTarget;
	}

	public void setSkipSizeTarget(String skipSizeTarget) {
		this.skipSizeTarget = skipSizeTarget;
	}

	public String getBeginTarget() {
		return beginTarget;
	}

	public void setBeginTarget(String beginTarget) {
		this.beginTarget = beginTarget;
	}
	
}
