/*
 * @(#)TradeDetailManagerImpl.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.user.domain.User;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.pagination.Pagination;
import com.vlives.util.DateUtils;

@Service("tradeDetailManager")
public class TradeDetailManagerImpl implements TradeDetailManager {

	@Resource
	private BaseDao<TradeDetail, Long> tradeDetailDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TradeDetail> find(Map<String, Object> params, Pagination pagination) {
		DynamicFinder finder = consutFinder(params);
		return tradeDetailDao.find(finder, pagination);
	}

	private DynamicFinder consutFinder(Map<String, Object> params) {
		if (params.get("name") != null) {
			params.put("name", params.get("name") + "%");
		}
		//t.consumeTrade=true
		String hql = "from TradeDetail t where  1=1 and t.consumeTrade=true" 
			 + "{ and t.consumeTrade = :consumeTrade}"
			+ "{ and t.tradeOrder.member.user = :user}"
				+ "{ and t.tradeOrder.member.user.name like :name}" + "{ and t.tradeOrder.merchant = :createMerchant}"
				+ "{ and t.tradeOrder.merchant.memberGroup = :memberGroup}"
				+ "{ and t.tradeOrder.member.user.mobile = :mobile}" + "{ and t.tradeOrder.member.level = :level}"
				+ "{ and t.tradeDate>=:startTradeDate}" + "{ and t.tradeDate<=:endTradeDate}"
				+ " order by t.tradeDate desc ";

		DynamicFinder finder = new DynamicFinder(hql, params);
		return finder;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long count(Map<String, Object> params) {
		DynamicFinder finder = consutFinder(params);
		return tradeDetailDao.count(finder);
	}

	public BigDecimal sumTradeMoney(Map<String, Object> params) {
		DynamicFinder finder = consutFinder(params);
		BigDecimal sum = (BigDecimal) tradeDetailDao.sum(finder, "t.amount");
		return sum == null ? BigDecimal.ZERO : sum;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TradeDetail> find(Map<String, Object> params) {
		DynamicFinder finder = consutFinder(params);
		return tradeDetailDao.find(finder);
	}
    /**
     * 查询超过15天，需要系统自动点评的消费
     */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TradeDetail> findByMerComment() {
		String hql = "select t  from   MerchantComment c   right join c.tradeDetail t  where "
				+ "t.consumeTrade=:consumeTrade  and  c.id is null  and t.tradeDate<:tradeDate ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("consumeTrade", true);
		Date date = DateUtils.add(new Date(), DateUtils.TimeUnit.DAYS, -15);
		map.put("tradeDate", date);
		Finder finder = new SimpleParametersFinder(hql, map);
		return tradeDetailDao.find(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TradeDetail get(Long id) {
		return tradeDetailDao.get(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TradeDetail> findTradeDetails(Map<String, Object> map, Pagination pagination) {
		String hql = "select l from TradeDetail l, TradeOrder r, Member m, User u where l.tradeOrder = r.id and r.member = m.id "
				+ "{ and m.user = :user}"
				+ "{ and r.merchant = :merchantId}"
				+ "{ and l.tradeDate>=:startDate}"
				+ "{ and l.tradeDate<=:endDate}" + " order by l.tradeDate desc ";
		DynamicFinder finder = new DynamicFinder(hql, map);
		return tradeDetailDao.find(finder);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TradeDetail> findNoCommentTradeDetails(Map<String, Object> map,Pagination pagination) {
		Finder finder = setFinder(map);
		return tradeDetailDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long getNoCommentCount(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		Finder finder = setFinder(map);
		return tradeDetailDao.count(finder);
	}
	
	@Override
	public List<TradeDetail> getUncommentTrades(User user, Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		Finder finder = setFinder(map);
		return tradeDetailDao.find(finder, pagination);
	}

	private Finder setFinder(Map<String, Object> map) {
		String hql = "select t  from   MerchantComment c   right join c.tradeDetail t  where "
				+ "t.consumeTrade=:consumeTrade  and  c.id is null  "
				+ " { and t.tradeOrder.member.user=:user }  "
				+ " { and t.tradeOrder.merchant=:merchant } order by t.id desc";
		map.put("consumeTrade", true);
		Finder finder = new DynamicFinder(hql, map);
		return finder;
	}
	
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(TradeDetail tradeDetail) {
		tradeDetailDao.save(tradeDetail);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TradeDetail getTradeDetail(TradeBatch tradeBatch, int tradeSerialNo) {
		String hql = "from TradeDetail d where  d.tradeBatch=:tradeBatch  and  d.tradeSerialNo=:tradeSerialNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeBatch", tradeBatch);
		map.put("tradeSerialNo", tradeSerialNo);
		Finder finder = new SimpleParametersFinder(hql, map);
		return tradeDetailDao.getBy(finder);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TradeDetail getCancelTradeDetail(TradeBatch origTradeBatch, int origTradeNo) {
		String hql = "from  TradeDetail d where  d.originalTradeBatch =:origTradeBatch  and  d.originalSerailNo=:origTradeSerialNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("origTradeBatch", origTradeBatch);
		map.put("origTradeSerialNo", origTradeNo);
		Finder finder = new SimpleParametersFinder(hql, map);
		return tradeDetailDao.getBy(finder);
	}

}
