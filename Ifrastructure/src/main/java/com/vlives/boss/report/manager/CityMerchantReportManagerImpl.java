/**
 * @(#)CityMerchantReportManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.area.manager.AreaManager;
import com.vlives.boss.report.domain.CityMerchantReport;
import com.vlives.boss.report.manager.support.ReportSqlParser;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PlaceholderFinder;
import com.vlives.core.pagination.Pagination;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-16
 */
@Service("cityMerchantReportManager")
public class CityMerchantReportManagerImpl implements CityMerchantReportManager {
	@Resource
	private BaseDao<CityMerchantReport, Long> cityMerchantReportDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private AreaManager areaManager;
	/**
	 * 需要统计的城市
	 */
	private static final Long[] reportCityIds = new Long[]{2222L,2195L,2044L,2529L};
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation =  Propagation.REQUIRES_NEW)
	public void createReoprt() {
		java.sql.Date reportDate = new java.sql.Date(DateUtils.add(new Date(), TimeUnit.DAYS, -1).getTime());
		for(Long cityId : reportCityIds) {
			Area city = areaManager.get(cityId);
			createReport(city,reportDate);
		}
	}
	
	private void createReport(Area city,java.sql.Date reportDate) {
		if(checkExistReport(city, reportDate))return;
		CityMerchantReport report = new CityMerchantReport();
		report.setCity(city);
		
		report.setReportDate(reportDate);
		setPerDayAddMerchantCount(report);
		List<Long> merchantIds = setTotalMerchantCount(report);
		if(merchantIds.size()>0) {
			report.setPerDayAddPosCount(getInt("perDayAddPosCount", merchantIds));
			report.setTotalPosCount(getInt("totalPosCount", merchantIds));
			report.setPerDayAddMemberCount(getInt("perDayAddMemberCount", merchantIds));
			report.setTotalMemberCount(getInt("totalMemberCount", merchantIds));
			report.setPerDayPosAddMemberCount(getInt("perDayPosAddMemberCount", merchantIds));
			report.setTotalPosAddMemberCount(getInt("totalPosAddMemberCount", merchantIds));
			report.setPerDayPlatformAddMemberCount(getInt("perDayPlatformAddMemberCount", merchantIds));
			report.setTotalPlatformAddMemberCount(getInt("totalPlatformAddMemberCount", merchantIds));
			report.setPerDayCloudbossAddMemberCount(getInt("perDayCloudbossAddMemberCount", merchantIds));
			report.setTotalCloudbossAddMemberCount(getInt("totalCloudbossAddMemberCount", merchantIds));
			report.setPerDaySysLargessSmsCount(getInt("perDaySysLargessSmsCount", merchantIds));
			report.setTotalSysLargessSmsCount(getInt("totalSysLargessSmsCount", merchantIds));
			report.setPerDayMerchantBuySmsCount(getInt("perDayMerchantBuySmsCount", merchantIds));
			report.setTotalMerchantBuySmsCount(getInt("totalMerchantBuySmsCount", merchantIds));
			int perDaySentSmsCount = getInt("perDaySentSmsCount", merchantIds);
			perDaySentSmsCount =perDaySentSmsCount>=0?0:0-perDaySentSmsCount;
			report.setPerDaySentSmsCount(perDaySentSmsCount);
			report.setTotalSentSmsCount(0-getInt("totalSentSmsCount", merchantIds));
			report.setPerDayCashTradeCount(getInt("perDayCashTradeCount", merchantIds));
			report.setTotalCashTradeCount(getInt("totalCashTradeCount", merchantIds));
			report.setPerDayCardTradeCount(getInt("perDayCardTradeCount", merchantIds));
			report.setTotalCardTradeCount(getInt("totalCardTradeCount", merchantIds));
			report.setPerDayCashTradeMoney(getBigDecimal("perDayCashTradeMoney", merchantIds));
			report.setTotalCashTradeMoney(getBigDecimal("totalCashTradeMoney", merchantIds));
			report.setPerDayCardTradeMoney(getBigDecimal("perDayCardTradeMoney", merchantIds));
			report.setTotalCardTradeMoney(getBigDecimal("totalCardTradeMoney", merchantIds)); 
		}
		cityMerchantReportDao.save(report);
	}
	
	private boolean checkExistReport(Area city,java.sql.Date reportDate) {
		return cityMerchantReportDao.isExisted(new PlaceholderFinder(ReportSqlParser.getQueryString("checkReportExist"), new Object[]{reportDate,city}));
	}
	
	private void setPerDayAddMerchantCount(CityMerchantReport report) {
		int perDayAddMerchantCount = jdbcTemplate.query(ReportSqlParser.getQueryString("perDayAddMerchantCount"), new Object[]{report.getCity().getAreaSearchCode()}, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				while(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		});
		report.setPerDayAddMerchantCount(perDayAddMerchantCount);
	}
	
	private List<Long> setTotalMerchantCount(CityMerchantReport report) {
		List<Long> merchantIds = jdbcTemplate.query(ReportSqlParser.getQueryString("totalMerchantCount"), new Object[]{report.getCity().getAreaSearchCode()}, new ResultSetExtractor<List<Long>>() {
			@Override
			public List<Long> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<Long> merchantIds = new ArrayList<Long>();
				while(rs.next()) {
					merchantIds.add(rs.getLong(1));
				}
				return merchantIds;
			}
		});
		report.setTotalMerchantCount(merchantIds.size());
		return merchantIds;
	}
	
	private int getInt(String queryStringName, List<Long> merchantIds) {
		String value =   getObjectVlaue(queryStringName, merchantIds);
		 return value==null?0:new Integer(value);
	}
	
	private BigDecimal getBigDecimal(String queryStringName, List<Long> merchantIds) {
		String value =   getObjectVlaue(queryStringName, merchantIds);
		 return value==null?BigDecimal.ZERO:new BigDecimal(value);
	}
	 
	private String getObjectVlaue(String queryStringName, List<Long> merchantIds) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<merchantIds.size();i++) {
			sb.append(merchantIds.get(i));
			if(i!=merchantIds.size()-1) {
				sb.append(",");
			}
		}
		String query  = ReportSqlParser.getQueryString(queryStringName);
		query = query.replaceAll("\\?", sb.toString());
		return jdbcTemplate.query(query, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				while(rs.next()) {
					return rs.getString(1);
				}
				return null;
			}
		});
	}
	
	
	@Override
	public List<CityMerchantReport> findReoprt(Date startDate, Date endDate,
			Pagination pagination) {
		return cityMerchantReportDao.find(createFinder(startDate, endDate), pagination);
	}
	@Override
	public List<CityMerchantReport> findReoprt(Date startDate, Date endDate) {
		return cityMerchantReportDao.find(createFinder(startDate, endDate));
	}
	
	private Finder createFinder(Date startDate, Date endDate) {
		String hql = "from CityMerchantReport c where " +
				" c.reportDate>=:startDate and c.reportDate<=:endDate order by c.reportDate";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		Finder finder = new DynamicFinder(hql,params);
		return finder;
	}
}
