package com.vlives.boss.coupon.manager;

import java.util.Map;

import com.vlives.boss.coupon.domain.CouponVerifyTransaction;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.core.exception.PosBussinessException;

public interface CouponTransManager {
	
	/**
	 * 电子卷结算
	 * @param params
	 * @param tradeBatch
	 * @throws PosBussinessException
	 */
	public void settleCoupon(Map<Long,Long> params,TradeBatch tradeBatch)throws PosBussinessException;
	
	
	/**电子检券流水
	 * @param couponVerifyTransaction
	 */
	public void createCouponTrans(CouponVerifyTransaction couponVerifyTransaction);

}
