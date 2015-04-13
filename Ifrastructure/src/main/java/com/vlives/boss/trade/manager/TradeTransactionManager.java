package com.vlives.boss.trade.manager;

import com.vlives.boss.trade.domain.TradeTransaction;

public interface TradeTransactionManager {

	/**
	 * 创建交易交易流水 检查是否重复
	 * 
	 * @param dto
	 * @return
	 */
	public TradeTransaction create(TradeTransaction tradeTransaction);

	/**
	 * 更新
	 * 
	 * @param tradeTransaction
	 * @param respCode
	 * @param respDesc
	 * @return
	 */
	public void updateResult(TradeTransaction tradeTransaction, String respCode, String respDesc);

	public Object[] getConsumeTotal(String posNo, int batchNo);

	public Object[] getCancelTradeTotal(String posNo, int batchNo);
}
