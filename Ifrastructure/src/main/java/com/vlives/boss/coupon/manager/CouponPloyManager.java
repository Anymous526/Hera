/**
 * @(#)CouponPloyManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloyType;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-6
 */
public interface CouponPloyManager {
	
	public CouponPloy get(Long id);
	/**
	 * 创建商户电子券活动<br/>
	 * 需要指定会员
	 * @author jianguo.xu
	 * @param couponPloy
	 * @param ployMembers 指定电子券的用户群
	 * @param operaotr
	 */
	public void create(CouponPloy couponPloy,List<User> ployUsers,Operator operator)throws BusinessException;
	/**
	 * 根据商户和活动类型查询有效的活动集合
	 * @author jianguo.xu
	 * @param merchant
	 * @param couponPloyType
	 * @return
	 */
	public List<CouponPloy> findValidPloyByType(Merchant merchant,CouponPloyType couponPloyType);
	 
	
	/**
	 * 创建商户活动
	 * @author jianguo.xu
	 * @param couponPloy
	 * @param operaotr
	 */
	public void create(CouponPloy couponPloy,Operator operator);
	/**
	 * 注销
	 * @author jianguo.xu
	 * @param couponPloy
	 * @param operaotr
	 */
	public void logout(CouponPloy couponPloy,Operator operator)throws BusinessException;
	/**
	 * 修改活动
	 * @author jianguo.xu
	 * @param couponPloy
	 * @param operator
	 */
	public void modify(CouponPloy couponPloy,Operator operator)throws BusinessException;
	
	/**
	 * 查询营销活动
	 * 
	 * @param merchant
	 * @param pagination
	 * @return
	 */
	public List<CouponPloy> find(Map<String, Object> params, Pagination pagination);
	
	/**
	 * 根据商户查找能在此店使用的活动
	 * @param params
	 * @param pagination
	 * @return
	 */
	public List<CouponPloy> findByMerchant(Map<String, Object> params, Pagination pagination);
	
    /**
     * 查商户能使用有效活动
     * 根据CouponPloyApplyMerchant
     * @param merchant
     * @return
     */
	public List<CouponPloy> findValidPloy(Merchant merchant);
	
	/**
	 * 审核营销活动
	 * 
	 * @author jianguo.xu
	 * @param couponPloy 电子券活动
	 * @param pass	是否通过
	 * @param desc	审核原因
	 * @param operator	操作员
	 * @throws BusinessException
	 */
	public void auditPloy(CouponPloy couponPloy, boolean auditPass, String desc, Operator operator) throws BusinessException;
	/**
	 * 暂停活动
	 * @author jianguo.xu
	 * @param couponPloy
	 * @param pauseDesc 暂停原因
	 * @param operator	操作员
	 */
	public void pausePloy(CouponPloy couponPloy,String pauseDesc,Operator operator) throws BusinessException;
	
	/**
	 * 恢复活动
	 * @author jianguo.xu
	 * @param couponPloy
	 * @param restoreDesc
	 * @param operator
	 */
	public void restorePloy(CouponPloy couponPloy,String  restoreDesc,Operator operator) throws BusinessException;
	
	/**
	 * 查询过期活动
	 * 定时任务
	 * @param  maxResults 每次读取数
	 * @return
	 */
	public List<CouponPloy> findDuePloyToEnd();
	
	/**
	 * 更新到期的、非结束状态的活动为结束状态
	 * 定时任务
	 * @author jianguo.xu
	 */
	public void updateDuePloyToEnd(CouponPloy couponPloy)throws BusinessException;
	
	/**
	 * 获得指定城市的商户最新优惠券列表
	 * @param number 返回的最新优惠券张数
	 * @param city 城市
	 */
	public List<CouponPloy> getLastestPromotions(Area city, int number);
	
	/**
	 * 查询商户是否正在优惠中（根据优惠活动的状态和日期）
	 * @param merchant
	 * @return
	 * @throws BusinessException
	 */
	public Boolean isMerchantInPromotion(Merchant merchant);
	
	/**
	 * 获得商户的有效优惠券列表
	 * @Merchant merchant
	 */
	public List<CouponPloy> getMerchantCouponPloys(Merchant merchant);

}
