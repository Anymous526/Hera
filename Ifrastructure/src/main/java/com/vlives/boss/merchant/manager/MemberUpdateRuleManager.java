/**
 * @(#)MemberUpdateRoleManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.manager;

import java.util.List;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.MemberUpdateRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.UpdateRuleItem;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface MemberUpdateRuleManager {
	
	/**
	 * 根据id获得
	 * @param id
	 * @return
	 */
	public MemberUpdateRule get(Long id);
	/**
	 * 创建规则
	 * 包括创建规则、更新规则、删除规则
	 * @author jianguo.xu
	 * @param merchant	商户
	 * @param level 等级
	 * @param  rewardPoint  会员升级到当前等级赠送的积分
	 * @param items	规则明细
	 */
	public void synchronizeRule(Merchant merchant,Level level,int rewardPoint,List<UpdateRuleItem> items);
	
	/**
	 * 更新规则
	 * @param memberUpdateRule
	 */
	public void update(MemberUpdateRule memberUpdateRule);
	
	/**
	 * 通过商户和等级获得对象
	 * @return
	 */
	public MemberUpdateRule getByMerchantAndLevel(Merchant merchant,Level level);
	
	/**
	 * 计算会员赠送积分
	 * @param merchant
	 * @param member
	 */
	public void givePoint(Merchant merchant, Member member);
	
}
