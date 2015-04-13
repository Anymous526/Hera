package com.vlives.boss.trade.manager;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.trade.manager.exception.TradeExistException;
import com.vlives.core.exception.PosBussinessException;

public interface TradeOrderManager {

	public TradeOrder getId(Long id);

	public void save(TradeOrder tradeOrder);

	public void pointTrade(TradeBatch tradeBatch, Member member, TradeOrder tradeOrder, TradeDetail tradeDetail)
			throws PosBussinessException, TradeExistException;

	public void pointCancel(TradeBatch tradeBatch, TradeBatch originalBatch, Member member, TradeOrder tradeOrder,
			TradeDetail tradeDetail) throws PosBussinessException;

}
