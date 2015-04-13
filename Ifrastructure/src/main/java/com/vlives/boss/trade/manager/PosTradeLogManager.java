package com.vlives.boss.trade.manager;

import com.vlives.boss.trade.domain.PosTradeLog;


public interface PosTradeLogManager {

	/**
	 * 创建pos请求日子
	 * 
	 * @param request
	 * @return
	 */
	public void createLog(PosTradeLog log);

	public void updateResult(PosTradeLog log);
	
	public void updateResult(PosTradeLog log, String respCode,String respDesc);
	

}
