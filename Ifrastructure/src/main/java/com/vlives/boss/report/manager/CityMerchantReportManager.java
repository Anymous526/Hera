/**
 * @(#)CityMerchantReportManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager;

import java.util.Date;
import java.util.List;

import com.vlives.boss.report.domain.CityMerchantReport;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-16
 */
public interface CityMerchantReportManager {
	/**
	 * 创建报表
	 * @author jianguo.xu
	 */
	public void createReoprt();
	
	/**
	 * 分页查询报表
	 * @param params
	 * @param pagination
	 * @return
	 */
	public List<CityMerchantReport> findReoprt(Date startDate,Date endDate,Pagination pagination);
	
	/**
	 * 查询报表
	 * @param params
	 * @return
	 */
	public List<CityMerchantReport> findReoprt(Date startDate,Date endDate);
}
