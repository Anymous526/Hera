/**
 * @(#)DiscountRuleManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.manager;

import java.util.List;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.merchant.domain.DiscountRule;
import com.vlives.boss.merchant.domain.Merchant;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface DiscountRuleManager {
	/**
	 * 同步折扣规则 包括折扣规则的创建、更新、更新
	 * 
	 * @author jianguo.xu
	 * @param merchant
	 * @param discountRules
	 */
	public void synchronizeRule(Merchant merchant, List<DiscountRule> discountRules);

	/**
	 * 根据会员等级取到商户折扣
	 * 
	 * @param merchant
	 * @param level
	 *            会员等级
	 * @return
	 */
	public DiscountRule getByLevel(Merchant merchant, DiscountRule.Type discountType, Level level);

	/**
	 * 根据业务规则， 子商户没的，找父商户的规则
	 * 
	 * @param merchant
	 * @param discountType
	 * @param level
	 * @return
	 */
	public DiscountRule getByBuss(Merchant merchant, DiscountRule.Type discountType, Level level);

}
