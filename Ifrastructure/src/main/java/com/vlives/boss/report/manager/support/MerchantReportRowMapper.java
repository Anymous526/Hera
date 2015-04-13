/**
 * @(#)MerchantReportRowMapper.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager.support;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.report.domain.MerchantReport;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-8-1
 */
@SuppressWarnings("rawtypes")
public class MerchantReportRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		MerchantReport report = new MerchantReport();
		Merchant merchant = new Merchant();
		merchant.setId(rs.getLong(1));
		report.setMerchant(merchant);
		java.sql.Date reportDate = new java.sql.Date(DateUtils.add(new Date(), TimeUnit.DAYS, -1).getTime());
		report.setCreateDate(reportDate);
		report.setPerDayAddMemberCount(rs.getInt(2));
		report.setTotalMemberCount(rs.getInt(3));
		BigDecimal cashTradeMoney = rs.getBigDecimal(4);
		report.setCashTradeMoney(cashTradeMoney ==null?BigDecimal.ZERO:cashTradeMoney);
		report.setCashTradeCount(rs.getInt(5));
		BigDecimal cardTradeMoney = rs.getBigDecimal(6);
		report.setCardTradeMoney(cardTradeMoney ==null?BigDecimal.ZERO:cardTradeMoney);
		report.setCardTradeCount(rs.getInt(7));
		report.setPerDayAddSalePloyCount(rs.getInt(8));
		report.setTotalSalePloyCount(rs.getInt(9));
		report.setSendSmsCount(rs.getInt(10));
		return report;
	}

}
