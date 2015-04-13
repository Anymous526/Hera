/*
 * @(#)Pagination.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.vlives.core.pagination;



/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-3-15
 */
public class Pagination{
	
	private static final int DEFAULT_PAGE_SIZE=15;
	private static final int DEFAULT_CURRENT_PAGE=1;
	private static final int DEFAULT_SKIP_SIZE=10;
	private static final int DEFAULT_CURRENT_SKIP=1;
	
	private static final int MAX_PAGE_SIZE=200;
	

	
	private static final PaginationConvertor DEFAULT_CONVERTOR=new FreeMarkerPaginationConvertor();
	

	/**
	 * url
	 */
	private String url;
	
	/**
	 * 查询字符串
	 */
	private String queryString;
	
	/**
	 * 每页对象数的参数名
	 */
	private String pageSizeTarget;
	
	/**
	 * 快进数量的参数名
	 */
	private String skipSizeTarget;

	/**
	 * 当前页的参数名
	 */
	private String currentPageTarget;

	/**
	 * 每页对象数
	 */
	private int pageSize;

	/**
	 * 页数
	 */
	private int pageCount;

	/**
	 * 对象数
	 */
	private int count;

	/**
	 * 当前页
	 */
	private int currentPage;

	/**
	 * 当前页开始的记录的位置
	 */
	private int begin;

	/**
	 * 当前页结束的记录的位置
	 */
	private int end;

	/**
	 * 快近页数量
	 */
	private int skipSize;

	/**
	 * 当前快近所在页面
	 */
	private int currentSkip = DEFAULT_CURRENT_SKIP;
	
	/**
	 * 转换器
	 */
	private PaginationConvertor convertor=DEFAULT_CONVERTOR;
	
	
	public Pagination(){
		this(DEFAULT_PAGE_SIZE,DEFAULT_CURRENT_PAGE,DEFAULT_SKIP_SIZE);
	}

	public Pagination(int pageSize, int currentPage) {
		this(pageSize,currentPage,DEFAULT_SKIP_SIZE);
	}
	

	public Pagination(int pageSize, int currentPage, int skipSize) {
		if(pageSize<=0||pageSize>MAX_PAGE_SIZE)
			pageSize=DEFAULT_PAGE_SIZE;
		if(currentPage<=0)
			currentPage=DEFAULT_CURRENT_PAGE;
		if(skipSize<=0)
			skipSize=DEFAULT_SKIP_SIZE;
		this.pageSize=pageSize;
		this.currentPage=currentPage;
		this.skipSize=skipSize;
	}

	public String getCurrentPageTarget() {
		return currentPageTarget;
	}

	public void setCurrentPageTarget(String currentPageTarget) {
		this.currentPageTarget = currentPageTarget;
	}

	public String getSkipSizeTarget() {
		return skipSizeTarget;
	}

	public void setSkipSizeTarget(String skipSizeTarget) {
		this.skipSizeTarget = skipSizeTarget;
	}

	public String getPageSizeTarget() {
		return pageSizeTarget;
	}

	public void setPageSizeTarget(String pageSizeTarget) {
		this.pageSizeTarget = pageSizeTarget;
	}

	public int getCurrentSkip() {
		return currentSkip;
	}

	public void setCurrentSkip(int currentSkip) {
		this.currentSkip = currentSkip;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public PaginationConvertor getConvertor() {
		return convertor;
	}

	public void setConvertor(PaginationConvertor convertor) {
		this.convertor = convertor;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setSkipSize(int skipSize) {
		this.skipSize = skipSize;
	}

	/**
	 * 返回当前页
	 * 
	 * @return 当前页
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 得到页数
	 * 
	 * @return 页数
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 得到每页记录条数
	 * 
	 * @return 每页记录条数
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 得到记录条数
	 * 
	 * @return 记录条数
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 得到当前页的记录开始位置
	 * 
	 * @return 开始位置
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * 得到记录的结束位置
	 * 
	 * @return 结束位置
	 */
	public int getEnd() {
		return end;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/**
	 * 得到快近页数
	 * 
	 * @return 快近页数
	 */
	public int getSkipSize() {
		return this.skipSize;
	}

	/**
	 * 设置记录条数
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		if (count <= 0)
			return;
		this.count = count;
		this.pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		if (currentPage > pageCount)
			currentPage = pageCount;
		if (currentPage <= 0)
			currentPage = 1;

		begin = (currentPage - 1) * pageSize;
		end = currentPage * pageSize;
		if (end >= count)
			end = count;

		currentSkip = (currentPage / skipSize) * skipSize + 1;
		if (currentPage % skipSize == 0)
			currentSkip = currentSkip - skipSize;
	}

	/**
	 * 是否可以到第一页
	 * 
	 * @return
	 */
	public boolean canGoFirst() {
		return (this.currentPage > 1);
	}

	/**
	 * 是否可以到前一页
	 * 
	 * @return
	 */
	public boolean isCanGoPrevious() {
		return (this.currentPage > 1);
	}

	/**
	 * 是否可以到下一页
	 * 
	 * @return
	 */
	public boolean isCanGoNext() {
		return (this.currentPage < this.pageCount);
	}

	/**
	 * 是否可以到最后一页
	 * 
	 * @return
	 */
	public boolean isCanGoLast() {
		//return currentPage!=pageCount&&pageCount!=0;
		return (this.currentPage < this.pageCount);
	}
	
	public boolean isLastPage() {
		return this.currentPage == this.pageCount;
	}

	/**
	 * 得到前一页页码
	 * 
	 * @return
	 */
	public int previous() {
		if (this.currentPage > 1)
			return this.currentPage - 1;
		else
			return 1;
	}

	/**
	 * 得到下一页页码
	 * 
	 * @return
	 */
	public int next() {
		if (this.currentPage < this.pageCount)
			return this.pageCount + 1;
		else
			return this.pageCount;
	}

	/**
	 * 是否可以向前快进
	 * 
	 * @return
	 */
	public boolean isCanSkipForward() {
		return getSkipForward() > 0;
	}

	/**
	 * 得到向前快近的页码
	 * 
	 * @return
	 */
	public int getSkipForward() {
		return this.currentSkip - skipSize;
	}

	/**
	 * 是否可以向后快进
	 * 
	 * @return
	 */
	public boolean isCanSkipBackward() {
		return (getSkipBackward() <= this.pageCount);
	}

	/**
	 * 得到向后快近的页码
	 * 
	 * @return
	 */
	public int getSkipBackward() {
		return this.currentSkip + skipSize;
	}

	/**
	 * 得到当前显示的页码
	 * 
	 * @return
	 */
	public int[] getCurrentSkipPageNumbers() {
		int count = skipSize;
		if (currentSkip + skipSize > pageCount)
			count = pageCount - currentSkip + 1;
		int[] Result = new int[count];
		for (int i = 0; i < count; i++) {
			Result[i] = currentSkip + i;
		}
		return Result;
	}

	/**
	 * 得到url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	

	/**
	 * 将分页信息转换为html页面的字符串表示
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		return convertor.convert(this);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + pageSize;
		result = prime * result + skipSize;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagination other = (Pagination) obj;
		if (currentPage != other.currentPage)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (skipSize != other.skipSize)
			return false;
		return true;
	}
	
	

}
