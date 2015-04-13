package com.vlives.boss.trade.manager;

import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.core.exception.PosBussinessException;

public interface TradeBatchManager {
	
	
	
	public TradeBatch get(Long id);
	
	/**
	 * 查询Pos的交易批次
	 * @param pos
	 * @param batchNumber
	 * @return
	 */
	public 	TradeBatch getByBatchNo(Pos pos,Integer batchNumber);
	
	
	/**
	 * 创建交易批次，检查是否重复
	 * 如果存在重复直接返回。
	 * @param pos
	 * @param batchNumber
	 * @return
	 */
	public  TradeBatch  createBatch(Pos pos,Integer batchNumber);
	
	
	/**
	 * 结算处理	 
	 * @param tradeBatch
	 * @param object
	 * @return  返回批次号
	 * @throws PosBussinessException
	 */
	public int settleTrade(TradeBatch tradeBatch)throws PosBussinessException;

}
