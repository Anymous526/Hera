/*
 * @(#)TradeDetailManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.manager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.user.domain.User;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
public interface TradeDetailManager {

	public TradeDetail get(Long id);

	/**
	 * 消费查询 分页查询
	 * 
	 * @param params
	 * @param pagination
	 * @return
	 */
	public List<TradeDetail> find(Map<String, Object> params, Pagination pagination);

	/**
	 * 查询数量
	 * 
	 * @param params
	 * @return
	 */
	public long count(Map<String, Object> params);

	/**
	 * 对交易金额进行求和
	 * 
	 * @author jianguo.xu
	 * @param params
	 * @return
	 */
	public BigDecimal sumTradeMoney(Map<String, Object> params);

	/**
	 * 查询消费,不分页
	 * 
	 * @param params
	 * @return
	 */
	public List<TradeDetail> find(Map<String, Object> params);

	/**
	 * 根据商户评论， 得到15天后未评论订单
	 * 
	 * @return
	 */
	public List<TradeDetail> findByMerComment();

	/**
	 * 查询用户消费明细
	 * 
	 * @return
	 */
	public List<TradeDetail> findTradeDetails(Map<String, Object> map, Pagination pagination);

	/**
	 * 查询会员消费未评论的数
	 * 
	 * @param user
	 * @return
	 */
	public long getNoCommentCount(User user);
	
	/**
	 * 查询会员未评论的消费记录
	 * 
	 * @param user
	 * @return
	 */
	public List<TradeDetail> getUncommentTrades(User user, Pagination pagination);

	/**
	 * 查询会员消费未评论消费详情
	 * 
	 * @param user
	 * @return
	 */
	public List<TradeDetail> findNoCommentTradeDetails(Map<String, Object> map,Pagination pagination);
	
	public void save(TradeDetail tradeDetail);

	public TradeDetail getTradeDetail(TradeBatch tradeBatch, int tradeSerialNo);

	public TradeDetail getCancelTradeDetail(TradeBatch origTradeBatch, int origTradeNo);
}
