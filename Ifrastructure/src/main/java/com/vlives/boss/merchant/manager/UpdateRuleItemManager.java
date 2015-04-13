package com.vlives.boss.merchant.manager;

import java.math.BigDecimal;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.UpdateRuleItem;

public interface UpdateRuleItemManager {

	public UpdateRuleItem get(Long id);

	/**
	 * 根据商户和规则类型，获取升级规则
	 * 
	 * @param merchant
	 * @param ruleType
	 * @param level
	 * @return
	 */
	public UpdateRuleItem getByMerAndType(Merchant merchant, UpdateRuleItem.Type ruleType, Level level);
	
	/**
	 * 根据业务规则来取
	 * 子商户没的，找父商户
	 * @param merchant
	 * @param ruleType
	 * @param level
	 * @return
	 */
	public UpdateRuleItem getByBuss(Merchant merchant, UpdateRuleItem.Type ruleType, Level level);

	/**
	 * 根据会员等级升级规则， 更新会员等级
	 * 
	 * @param member
	 * @param amount
	 */
	public void upMemberGrade(Member member, BigDecimal amount,Merchant merchant);

}
