/**
 * @(#)PosPointProcesser.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.service;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;

/**
 * 会生活应用交易
 * @author  jianguo.xu
 * @version 1.0,2011-8-11
 */
public interface PosProcesserA1 extends PosProcesser{
	/***
	 * POS签到
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto posSign(Hs8583Dto request);

	/**
	 * POS查询会员信息
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto getMemberInfo(Hs8583Dto request);

	/**
	 * POS会员认证
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto authMember(Hs8583Dto request);

	/**
	 * 新增会员
	 * 
	 * @param request
	 * @return
	 */

	public Hs8583Dto joinMember(Hs8583Dto request);

	/**
	 * 绑定交易
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto bindTrade(Hs8583Dto request);

	/**
	 * 交易记录上送
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto uploadTrade(Hs8583Dto request);

	
	/**交易查询
	 * @param request
	 * @return
	 */
	public Hs8583Dto transQuery(Hs8583Dto request);
	/**
	 * 交易结算
	 * 
	 * @param message
	 * @return
	 */
	public Hs8583Dto settleTrade(Hs8583Dto message);
	/**
	 * 折扣消费计算
	 * @author jianguo.xu
	 * @param request
	 * @return
	 */
	public Hs8583Dto discountExpense(Hs8583Dto request);
	
	
	/**用户票信息查询
	 * @param request
	 * @return
	 */
	public Hs8583Dto userCouponInfoQuery(Hs8583Dto request);
	
	/**商户可用票
	 * @param request
	 * @return
	 */
	public Hs8583Dto merchantPublishedCouponQuery(Hs8583Dto request);
	
	/**检券 
	 * @param request
	 * @return
	 */
	public Hs8583Dto userCouponInspect(Hs8583Dto request);
	
	
}
