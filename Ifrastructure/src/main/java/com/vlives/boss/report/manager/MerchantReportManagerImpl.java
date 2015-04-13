/**
 * @(#)MerchantReoprtManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.report.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.report.domain.MerchantReport;
import com.vlives.boss.report.manager.support.MerchantReportRowMapper;
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
 * @version 1.0,2011-8-1
 */
@Service("merchantReportManager")
public class MerchantReportManagerImpl implements MerchantReportManager{
	@Resource
	private BaseDao<MerchantReport, Long> merchantReportDao;
	 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String MERCHANT_QUERY_STRING=  "select a.id, b.bcount,c.ccount,d.dsum ,d.dcount,e.esum,e.ecount,f.fcount,g.gcount,hcount "+
	"from "+
	"(select id,code,name,create_date from merchant where id not in(1019,1020)) a "+
	"left join "+
	"(select m.id bid, count(m.id) bcount from  merchant m ,member_group mg, member mb "+
	"where m.member_group_id = mg.id and mg.id = mb.member_group_id "+
	"and TRUNC(mb.create_date) = to_date(to_char(sysdate-1,'yyyy-mm-dd'),'yyyy-mm-dd') "+
	"group by m.id) "+
	"b on a.id =b.bid "+
	"left join "+
	"(select m.id cid, count(m.id) ccount from  merchant m ,member_group mg, member mb "+
	"where m.member_group_id = mg.id and mg.id = mb.member_group_id group by m.id) "+
	"c on a.id = c.cid "+
	"left join "+
	"(select tor.merchant_id did,sum(tor.money) dsum, count(tor.merchant_id) dcount from trade_detail td,trade_order tor "+
	"where tor.id = td.trade_order_id "+
	"and td.is_consume = 1 "+
	"and tor.TYPE=2 "+
	"group by tor.merchant_id) "+
	"d on a.id = d.did "+
	"left join "+
	"(select tor.merchant_id eid,sum(tor.money) esum, count(tor.merchant_id) ecount from trade_detail td,trade_order tor "+
	"where tor.id = td.trade_order_id "+
	"and td.is_consume = 1 "+
	"and tor.TYPE=1 "+
	"group by tor.merchant_id) "+
	"e on a.id = e.eid "+
	" left join "+
	"(select s.merchant_id fid,count(s.merchant_id) fcount from SALE_PLOY s where status in (1,3,4,6) "+
	"and TRUNC(s.create_date)  =to_date(to_char(sysdate-1,'yyyy-mm-dd'),'yyyy-mm-dd') "+
	"group by s.merchant_id "+
	") f on a.id = f.fid "+
	"left join "+
	"(select s.merchant_id gid,count(s.merchant_id) gcount from SALE_PLOY s where status in (1,3,4,6) "+
	"group by s.merchant_id "+
	")g on a.id =g.gid "+
	"left join "+
	"(select s.merchant_id hid,sum(s.SEND_COUNT) hcount from SALE_PLOY s  "+
	"group by s.merchant_id"+
	")h on a.id =h.hid";
	
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void createMerchantReoprt() { 
		String queryString = "from MerchantReport m where m.createDate = ?";
		java.sql.Date queryDate = new java.sql.Date(DateUtils.add(new Date(), TimeUnit.DAYS, -1).getTime());
		boolean existReport = merchantReportDao.isExisted(new PlaceholderFinder(queryString, queryDate));
		if(!existReport) {
			@SuppressWarnings("unchecked")
			List<MerchantReport> reports = jdbcTemplate.query(MERCHANT_QUERY_STRING,new MerchantReportRowMapper());
			merchantReportDao.saveAll(reports);
		}		
	}

	@Override
	public List<MerchantReport> findReoprt(Map<String, Object> params,
			Pagination pagination) {
		Finder finder = createFinder(params);
		return merchantReportDao.find(finder,pagination);
	}

	@Override
	public List<MerchantReport> findReoprt(Map<String, Object> params) {
		Finder finder = createFinder(params);
		return merchantReportDao.find(finder);
	}
	
	private Finder createFinder(Map<String, Object> params) {
		String hql = "from MerchantReport m where 1=1 " +
				"{ and m.createDate>=:startDate}" +
				"{ and m.createDate<=:endDate}" +
				" order by m.merchant.createDate desc,m.merchant.code";
		Finder finder = new DynamicFinder(hql,params);
		return finder;
	}
}
