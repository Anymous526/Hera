/**
 * @(#)CouponManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.Coupon.CouponStatus;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.rulesupport.SendTrigger;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.trade.domain.TradeOrder.Type;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-6
 */
public interface CouponManager {
	
	public Coupon get(Long id);
	
	
	public Coupon getByCode(String code);
	/**
	 * 批量创建一个活动指定会员的电子券
	 * @author jianguo.xu
	 * @param couponPloy
	 * @param ployMembers
	 */
	public void createCoupon(CouponPloy couponPloy,List<User> ployUsers);
	
	/**
	 * 查询需要发送的电子卷
	 * @param maxCount
	 * @return
	 */
	public List<Coupon> findNeedSendCoupon(Date sendDate,int maxCount);
	
	/**
	 * 发送电子券到用户手机上
	 * @author jianguo.xu
	 */
	public void sendCoupon(Coupon coupon);
	/**
	 * 用户通过网站领取电子券，同时下发短信到用户手机上
	 * @author jianguo.xu
	 * @param user
	 * @param couponPloy
	 */
	public void receiveCouponBySite(User user,CouponPloy couponPloy)throws BusinessException;
	
	/**
	 * 用户通过活动下发规则领取电子券
	 * @author jianguo.xu
	 * @param user
	 * @param couponPloy
	 */
	public void receiveCouponBySendRule(User user,CouponPloy couponPloy,SendTrigger sendTrigger)throws BusinessException;
	
	/**
	 * 根据电子券验券
	 * @author jianguo.xu
	 * @param coupon
	 */

	public void verifyCoupon(Coupon coupon)throws PosBussinessException;
	/**
	 * 根据用户和电子券活动验券
	 * @author jianguo.xu
	 * @param user
	 * @param couponPloy
	 */
	public Coupon verfyCoupon(User user,CouponPloy couponPloy)throws PosBussinessException;
	
	/**
	 * 获得用户的优惠券列表(可带查询条件）
	 * @param statuses 优惠券状态 
	 * @param merchantIds 选择的商户
	 * @param sorting 排序条件：领用时间或者过期时间 
	 */
	public List<Coupon> getUserCoupons(User user, CouponStatus[] statuses, Long[] merchantIds, int sorting, Pagination pagination);
	
	/**
	 * 动态查询
	 * @author jianguo.xu
	 * @param params
	 * @param pagination
	 * @return
	 */
	public List<Coupon> find(Map<String, Object> params, Pagination pagination);
	
	
	/**
	 * POS
	 * 查询当前user在商户下所有为
	 * 消费电子劵
	 * @param user
	 * @param merchant
	 * @return
	 */
	public List<Coupon> findByUserAndMer(User user,Merchant merchant);
	
	
	/**
	 * 查找过期电子卷
	 * @return
	 */
	public List<Coupon> findDueCoupon();
	
	/**
	 * 更新过期的电子券为过期状态
	 * @author jianguo.xu
	 */
	public void updateDueCoupon(Coupon  coupon)throws BusinessException;
	
	/**
	 * 消费电子卷关联订单
	 * @param coupon
	 * @param tradeOrder
	 */
	public void relatedTradeOrder(Coupon  coupon,TradeOrder tradeOrder)throws BusinessException ;
	/**
	 * 根据用户在某一活动领取的活动数
	 * @author jianguo.xu
	 * @param user
	 * @param coupon
	 * @return
	 */
	public long countBy(User user,CouponPloy couponPloy);
	/**
	 * 得到活动发放的优惠券总数
	 * @author jianguo.xu
	 * @param couponPloy
	 * @return
	 */
	public long countBy(CouponPloy couponPloy);
	/**
	 * 根据优惠券活动、商户、和交易类型查询交易金额<br/>
	 * 如果金额不传则计算该活动在所有商户的交易金额<br/>
	 * 如果交易类型 为 null则查询所有类型的交易金额<br/>
	 * @return
	 */
	public BigDecimal sumConsumeMoney(CouponPloy couponPloy,Merchant tradeMerchant,Type tradeType);
	
}
