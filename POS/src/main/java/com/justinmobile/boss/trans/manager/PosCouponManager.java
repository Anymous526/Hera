package com.justinmobile.boss.trans.manager;

import java.util.List;

import com.justinmobile.endpoint.mina.domain.Hs8583CouponList;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.PosBussinessException;

public interface PosCouponManager {

	/**
	 * 9月
	 * 增加了电子卷结算
	 * @param tradeBatch
	 * @param dto
	 * @return
	 * @throws PosBussinessException
	 */
	public void settleCoupon(TradeBatch tradeBatch,List<Hs8583CouponList>  couponLists) throws PosBussinessException;
	
	
	/**
	 * 9月
	 * 电子检券
	 * @param tradeBatch
	 * @param dto
	 * @return
	 * @throws PosBussinessException
	 */
	public Coupon userCoupon(Hs8583Dto request,User user,Pos pos) throws PosBussinessException;

	/**单独检券流程
	 * @param request
	 * @param coupon
	 * @param pos
	 * @throws PosBussinessException
	 */
	public void checkUserCoupon(Hs8583Dto request, Coupon coupon, Pos pos) throws PosBussinessException;
	
}
