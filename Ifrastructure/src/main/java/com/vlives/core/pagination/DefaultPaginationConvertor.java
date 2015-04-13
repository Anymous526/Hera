/*
 * @(#)DefaultPaginationConvertor.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.vlives.core.pagination;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2010-3-15
 */
public class DefaultPaginationConvertor implements PaginationConvertor {

	public String convert(Pagination pagination) {
		String url = pagination.getUrl();
		int pageSize = pagination.getPageSize();
		int currentPage = pagination.getCurrentPage();
		int skipSize = pagination.getSkipSize();
		String pageSizeTarget = pagination.getPageSizeTarget();
		String currentPageTarget = pagination.getCurrentPageTarget();
		String skipSizeTarget = pagination.getSkipSizeTarget();
		String queryString = pagination.getQueryString();
		if (queryString == null)
			queryString = "";
		else
			queryString = "&" + queryString;
		int pageCount = pagination.getPageCount();
		int count = pagination.getCount();
		int begin = pagination.getBegin();
		int end = pagination.getEnd();
		StringBuffer result = new StringBuffer(
				"<table width=100% border=0 cellspacing=0 cellpadding=0>"
						+ "<tr><td width=\"2%\" height=\"30\">"
						+ "</td><td width=\"25%\">" + (begin + 1) + " - " + end
						+ " 共 " + count + " 条 " + pageCount + " 页<br></td>"
						+ "<td align=right>");
		/**
		 * 第一页
		 */
		if (currentPage != 1)
			result.append("&nbsp;&nbsp;<a href=").append(url).append("?")
					.append(currentPageTarget).append("=").append(1).append(
							"&").append(pageSizeTarget).append("=").append(
							pageSize).append("&").append(skipSizeTarget)
					.append("=").append(skipSize).append(queryString).append(
							">第一页</a>");
		else
			result.append("&nbsp;&nbsp;第一页");
		
		/**
		 * 上一页
		 */
		if (pagination.isCanGoPrevious())
			result.append("&nbsp;&nbsp;<a href=").append(url).append("?")
					.append(currentPageTarget).append("=").append(
							currentPage - 1).append("&").append(pageSizeTarget)
					.append("=").append(pageSize).append("&").append(
							skipSizeTarget).append("=").append(skipSize)
					.append(queryString).append(">上一页</a>");
		else
			result.append("&nbsp;&nbsp;上一页");

		/**
		 * 向前跳转
		 */
		if (pagination.isCanSkipForward())
			result.append("&nbsp;&nbsp;<a href=").append(url).append("?")
					.append(currentPageTarget).append("=").append(
							pagination.getSkipForward()).append("&").append(
							pageSizeTarget).append("=").append(pageSize)
					.append("&").append(skipSizeTarget).append("=").append(
							skipSize).append(queryString).append(">...</a>");

		/**
		 * 页码
		 */ 
		int[] pageNumber = pagination.getCurrentSkipPageNumbers();
		for (int i = 0; i < pageNumber.length; i++) {
			if (pageNumber[i] != currentPage)
				result.append("&nbsp;&nbsp;<a href=").append(url).append("?")
						.append(currentPageTarget).append("=").append(
								pageNumber[i]).append("&").append(
								pageSizeTarget).append("=").append(pageSize)
						.append("&").append(skipSizeTarget).append("=").append(
								skipSize).append(queryString).append(">")
						.append(pageNumber[i]).append("</a>");
			else
				result.append("&nbsp;&nbsp;" + pageNumber[i]);
		}

		/**
		 * 向后跳转
		 */
		if (pagination.isCanSkipBackward())
			result.append("&nbsp;&nbsp;<a href=").append(url).append("?")
					.append(currentPageTarget).append("=").append(
							pagination.getSkipBackward()).append("&").append(
							pageSizeTarget).append("=").append(pageSize)
					.append("&").append(skipSizeTarget).append("=").append(
							skipSize).append(queryString).append(">...</a>");

		/**
		 * 下一页
		 */
		if (pagination.isCanGoNext())
			result.append("&nbsp;&nbsp;<a href=").append(url).append("?")
					.append(currentPageTarget).append("=").append(
							currentPage + 1).append("&").append(pageSizeTarget)
					.append("=").append(pageSize).append("&").append(
							skipSizeTarget).append("=").append(skipSize)
					.append(queryString).append(">下一页</a> ");
		else
			result.append("&nbsp;&nbsp;下一页");
		
		/**
		 * 最后一页
		 */
		if(pagination.isCanGoLast())
			result.append("&nbsp;&nbsp;<a href=").append(url).append("?")
			.append(currentPageTarget).append("=").append(pageCount).append(
					"&").append(pageSizeTarget).append("=").append(
					pageSize).append("&").append(skipSizeTarget)
			.append("=").append(skipSize).append(queryString).append(
					">最后一页</a>");
		else
			result.append("&nbsp;&nbsp;最后一页");
		
		result.append("</td><td width=\"2%\"></td></tr></table>");
	
		return result.toString();
	}

}
