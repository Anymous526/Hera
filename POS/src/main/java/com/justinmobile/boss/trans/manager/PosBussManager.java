package com.justinmobile.boss.trans.manager;

import java.util.List;

import com.justinmobile.endpoint.mina.domain.Hs8583BusinessUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583ClosingCostDto;
import com.justinmobile.endpoint.mina.domain.Hs8583CouponUploadInfo;
import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.core.exception.PosBussinessException;

public interface PosBussManager {

	/**
	 * 交易上传处理
	 * 
	 * @param businessInfos
	 *            业务数据结合
	 * @param posTradeLog
	 *            主报文日志
	 * @throws PosBussinessException
	 */
	public void batchTrade(List<Hs8583BusinessUploadInfo> businessInfos, String batchNumber, PosTradeLog posTradeLog)
			throws PosBussinessException;

	
	/**
	 * 交易上送，V1.2版本后，支持电子卷绑定交易
	 * @param businessInfos
	 * @param couponUploadList
	 * @param batchNumber
	 * @param posTradeLog
	 * @throws PosBussinessException
	 */
	public void batchTrade(List<Hs8583BusinessUploadInfo> businessInfos, List<Hs8583CouponUploadInfo> couponUploadList,
			String batchNumber, PosTradeLog posTradeLog) throws PosBussinessException;
	
	
	
	/**
	 * 交易查询
	 * 
	 * @param businessInfos
	 * @param batchNumber
	 * @param posTradeLog
	 * @return
	 * @throws PosBussinessException
	 */
	public List<Hs8583BusinessUploadInfo> transQuery(List<Hs8583BusinessUploadInfo> businessInfos, String batchNumber,
			PosTradeLog posTradeLog) throws PosBussinessException;

	/**
	 * 批次结算
	 * 
	 * @param tradeBatch
	 * @param dto
	 * @return
	 * @throws PosBussinessException
	 */
	public int settleTrade(TradeBatch tradeBatch, Hs8583ClosingCostDto dto) throws PosBussinessException;
}
