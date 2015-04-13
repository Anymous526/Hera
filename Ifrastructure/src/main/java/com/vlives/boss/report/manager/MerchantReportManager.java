/**
 * @(#)MerchantReoprtManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.report.domain.MerchantReport;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-1
 */
public interface MerchantReportManager {
	/**
	 * 创建商户报表
	 * @author jianguo.xu
	 */
	public void createMerchantReoprt();
	
	/**
	 * 分页查询商户报表---按照报表创建时间查询，开始时间、结束
	 * 按照商户编号和商户创建时间排序
	 * @param params
	 * @param pagination
	 * @return
	 */
	public List<MerchantReport> findReoprt(Map<String, Object> params,Pagination pagination);
	
	/**
	 * 分页查询商户报表---按照报表创建时间查询，开始时间、结束
	 * 按照商户编号和商户创建时间排序
	 * 最多导出5000条
	 * 查询商户报表
	 * @param params
	 * @return
	 */
	public List<MerchantReport> findReoprt(Map<String, Object> params);
	
}
