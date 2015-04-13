/**
 * @(#)CouponConsumReportRowMapper.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager.support;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.vlives.boss.report.domain.CouponConsumReport;
import com.vlives.boss.trade.domain.TradeOrder;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-15
 */
@SuppressWarnings("rawtypes")
public class CouponConsumReportRowMapper implements RowMapper{
	private List<CouponConsumReport> reports;
	
	public CouponConsumReportRowMapper(List<CouponConsumReport> reports) {
		 
		this.reports = reports;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long couponPloyId = rs.getLong(1);
		for(CouponConsumReport report : reports) {
			if(report.getCouponPloy().getId()== couponPloyId) {
				int type = rs.getInt(2);
				BigDecimal money = rs.getBigDecimal(3);
				if(type == TradeOrder.Type.TYPE_BRUSH.getValue()) {
					report.setBrushConsumMoney(money);
				}
				if(type == TradeOrder.Type.TYPE_CASH.getValue()) {
					report.setCashConsumMoney(money);
				}
			}
		}
		return null;
	}

}
