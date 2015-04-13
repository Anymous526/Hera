/**
 * @(#)CouponConsumReportRowMapper.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.report.domain.CouponConsumReport;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-15
 */
@SuppressWarnings("rawtypes")
public class CouponCountReportRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponConsumReport report = new CouponConsumReport();
		CouponPloy couponPloy = new CouponPloy();
		couponPloy.setId(rs.getLong(1));
		report.setCouponPloy(couponPloy);
		report.setCouponConsumCount(rs.getInt(2));
		java.sql.Date reportDate = new java.sql.Date(DateUtils.add(new Date(), TimeUnit.DAYS, -1).getTime());
		report.setCreateDate(reportDate);
		return report;
	}

}
