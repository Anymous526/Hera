/**
 * @(#)CouponConsumReportManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager;

import java.util.Date;
import java.util.List;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.report.domain.CouponConsumReport;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-15
 */
public interface CouponConsumReportManager {
	/**
	 * 创建商户报表
	 * @author jianguo.xu
	 */
	public void createReoprt();
	
	/**
	 * 分页查询商户报表---按照报表创建时间查询，开始时间、结束
	 * 按照商户编号和商户创建时间排序
	 * @param params
	 * @param pagination
	 * @return
	 */
	public List<CouponConsumReport> findReoprt(Merchant merchant, Date startDate,Date endDate,Pagination pagination);
	
	/**
	 * 分页查询商户报表---按照报表创建时间查询，开始时间、结束
	 * 按照商户编号和商户创建时间排序
	 * 最多导出5000条
	 * 查询商户报表
	 * @param params
	 * @return
	 */
	public List<CouponConsumReport> findReoprt(Merchant merchant,Date startDate,Date endDate);
}
