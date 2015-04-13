/**
 * @(#)DiscountRuleManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.manager;

import java.math.BigDecimal;
import java.util.List;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.PointRule;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface PointRuleManager {
	/**
	 * 同步折扣规则
	 * 包括折扣规则的创建、更新、更新
	 * @author jianguo.xu
	 * @param merchant
	 * @param discountRules
	 */
	public void synchronizeRule(Merchant merchant,List<PointRule> pointRules);
	
	/**
	 * 查询积分规则明细
	 * @param merchant
	 */
	public List<PointRule> findPointRule(Merchant merchant);
	
	public PointRule get(Long id);
	
	/**
	 * 根据商户和等级获取积分计算规则
	 * @param merchant
	 * @param level
	 * @return
	 */
	public PointRule  getByMerAndLevel(Merchant merchant,Level level);
	
	/**
	 * 根据业务规则来取
	 * 子商户没有等级规则，
	 * 则找父商户
	 * @param merchant
	 * @param level
	 * @return
	 */
	public  PointRule getByBuss(Merchant merchant, Level level);
	
	
	/**
	 * 获取会员所得积分
	 * 根据积分等级规则计算积分。
	 * @param member
	 * @param amount
	 * @return
	 */
	public int getIncreasePoint(Member member,BigDecimal amount,Merchant merchant);
}
