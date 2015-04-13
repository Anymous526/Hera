/**
 * @(#)CouponConsumReportManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.report.domain.CouponConsumReport;
import com.vlives.boss.report.manager.support.CouponConsumReportRowMapper;
import com.vlives.boss.report.manager.support.CouponCountReportRowMapper;
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
 * @version 1.0,2011-9-15
 */
@Service("couponConsumReportManager")
public class CouponConsumReportManagerImpl implements CouponConsumReportManager {
	@Resource
	private BaseDao<CouponConsumReport, Long> couponConsumReportDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String REPORT_COUNT_QUERY="select c.coupon_ploy_id, count(c.id) consumcount from coupon c,coupon_verify_transaction co " +
			"where co.coupon_id = c.id and trunc(co.verify_date) = to_date(to_char(sysdate-1,'yyyy-mm-dd'),'yyyy-mm-dd') " +
			"group by c.coupon_ploy_id";
	
	private static final String REPORT_CONSUM_STRING="select c.coupon_ploy_id, t.type,sum(t.money) money from coupon c, trade_order t " +
			"where c.trade_order_id = t.id and t.type in(1,2) and " +
			"trunc(t.trade_date) = to_date(to_char(sysdate-1,'yyyy-mm-dd'),'yyyy-mm-dd') " +
			"group by c.coupon_ploy_id, t.type";
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation =  Propagation.REQUIRES_NEW)
	public void createReoprt() {
		String queryString = "from CouponConsumReport m where m.createDate = ?";
		java.sql.Date queryDate = new java.sql.Date(DateUtils.add(new Date(), TimeUnit.DAYS, -1).getTime());
		boolean existReport = couponConsumReportDao.isExisted(new PlaceholderFinder(queryString, queryDate));
		if(existReport) return;
		List<CouponConsumReport> reports = jdbcTemplate.query(REPORT_COUNT_QUERY,new CouponCountReportRowMapper());
		jdbcTemplate.query(REPORT_CONSUM_STRING,new CouponConsumReportRowMapper(reports));
		couponConsumReportDao.saveAll(reports);
	}

	@Override
	@Transactional(readOnly = true,  propagation = Propagation.SUPPORTS)
	public List<CouponConsumReport> findReoprt(Merchant merchant,Date startDate, Date endDate,
			Pagination pagination) {
		return couponConsumReportDao.find(createFinder(merchant, startDate, endDate), pagination);
	}

	@Override
	@Transactional(readOnly = true,  propagation = Propagation.SUPPORTS)
	public List<CouponConsumReport> findReoprt(Merchant merchant,Date startDate, Date endDate) {
		return couponConsumReportDao.find(createFinder(merchant, startDate, endDate));
	}
	private Finder createFinder(Merchant merchant,Date startDate, Date endDate) {
		String hql = "from CouponConsumReport c where c.couponPloy.merchant =:merchant " +
				"and c.createDate>=:startDate and c.createDate<=:endDate order by c.createDate";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", merchant);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		Finder finder = new DynamicFinder(hql,params);
		return finder;
	}
}
