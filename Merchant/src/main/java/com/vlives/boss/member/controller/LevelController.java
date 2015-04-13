/*
 * @(#)PointController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.merchant.domain.DiscountRule;
import com.vlives.boss.merchant.domain.MemberUpdateRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.PointRule;
import com.vlives.boss.merchant.domain.UpdateRuleItem;
import com.vlives.boss.merchant.domain.DiscountRule.Type;
import com.vlives.boss.merchant.manager.MemberUpdateRuleManager;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-13
 */
@Controller
@RequestMapping("/manager/member")
public class LevelController {

	/**
	 * 查看商户对应的等级
	 */
	@RequestMapping("/level/view.htm")
	public ModelAndView view(@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/member/level.jsp");
		Merchant merchant = operator.getMerchant();
		model.addObject("merchant", merchant);
		return model;
	}
	
	/**
	 * 获得商户升级规则和积分规则
	 * @param operator
	 * @param id
	 * @return
	 */
	@RequestMapping("/level/{id}/modify.htm")
	public ModelAndView modify(@ObjectConvertAnno Operator operator,@PathVariable Long id){
		ModelAndView model = new ModelAndView("/manager/member/modify_level.jsp");
		MemberUpdateRule memberUpdateRule = memberUpdateRuleManager.get(id);
		model.addObject("memberUpdateRule", memberUpdateRule);
		model.addObject("merchant", operator.getMerchant());
		return model;
	}
	/**
	 * 修改商户升级规则和积分规则
	 * @param operator
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/level/{id}/modify_commit.htm",method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_UPDATE_LEVEL)
	public ModelAndView modifyCommit(@ObjectConvertAnno Operator operator,@PathVariable Long id){
		int pointScale = HttpParameterParser.getIntValue("pointScale");
		int discountScale = HttpParameterParser.getIntValue("discountScale");
		int rewardPoint = HttpParameterParser.getIntValue("rewardPoint");
		int once = HttpParameterParser.getIntValue("once");
		int total = HttpParameterParser.getIntValue("total");
		String desc = HttpParameterParser.getString("desc");
		int level = HttpParameterParser.getIntValue("level");
		Merchant merchant = operator.getMerchant();
		if(level > Level.GENERAL.getValue() && level<Level.DIAMOND.getValue()){
			int le =level -1;
			MemberUpdateRule rule = memberUpdateRuleManager.getByMerchantAndLevel(merchant, Level.get(le));
			Set<UpdateRuleItem> set = rule.getUpdateRuleItems();
			for (UpdateRuleItem updateRuleItem : set) {
				if(updateRuleItem.getType() == com.vlives.boss.merchant.domain.UpdateRuleItem.Type.TYPE_ONCE_CONSUME){
					if(updateRuleItem.getUpdatePoint()>= once){
						ModelAndView model = new ModelAndView("/manager/member/level/view.htm");
						model.addObject("msg",Level.get(level).getDesc()+"一次性消费升级不能比上一等级升级低");
						return model;
					}
				}else if(updateRuleItem.getType() == com.vlives.boss.merchant.domain.UpdateRuleItem.Type.TYPE_POINT){
					if(updateRuleItem.getUpdatePoint()>= total){
						ModelAndView model = new ModelAndView("/manager/member/level/view.htm");
						model.addObject("msg",Level.get(level).getDesc()+"累计消费升级不能比上一等级升级低");
						return model;
					}
				}
			}
		}
		MemberUpdateRule memberUpdateRule = memberUpdateRuleManager.get(id);
		memberUpdateRule.setRewardPoint(rewardPoint);
		memberUpdateRule.setDesc(desc);
		DiscountRule discountRule = constDiscountRule(discountScale, level);
		PointRule pointRule = constPontRule(pointScale, level);
		List<UpdateRuleItem> items = null;
		if(level != Level.DIAMOND.getValue())
			items= constUpdateRuleItem(once, total);
		merchantManager.levelManagement(pointRule, discountRule, items, merchant, memberUpdateRule);
		return new ModelAndView(new RedirectView("/manager/member/level/view.htm"));
	}

	private DiscountRule constDiscountRule(int discountScale, int level) {
		DiscountRule discountRule = new DiscountRule();
		discountRule.setParamerOne(level);
		discountRule.setType(Type.BY_MEMBER_LEVEL);
		discountRule.setParamerTwo(discountScale);
		return discountRule;
	}

	private PointRule constPontRule(int pointScale, int level) {
		PointRule pointRule = new PointRule();
		pointRule.setParamerOne(level);
		pointRule.setParamerTwo(pointScale);
		pointRule.setType(com.vlives.boss.merchant.domain.PointRule.Type.BY_MEMBER_LEVEL);
		return pointRule;
	}

	private List<UpdateRuleItem> constUpdateRuleItem(int once, int total) {
		UpdateRuleItem itemOnce = new UpdateRuleItem();
		itemOnce.setType(com.vlives.boss.merchant.domain.UpdateRuleItem.Type.TYPE_ONCE_CONSUME);
		itemOnce.setUpdatePoint(once);
		UpdateRuleItem itemTotal = new UpdateRuleItem();
		itemTotal.setType(com.vlives.boss.merchant.domain.UpdateRuleItem.Type.TYPE_POINT);
		itemTotal.setUpdatePoint(total);
		List<UpdateRuleItem> items = new ArrayList<UpdateRuleItem>();
		items.add(itemOnce);
		items.add(itemTotal);
		return items;
	}

	@Autowired
	private MemberUpdateRuleManager memberUpdateRuleManager;
	
	@Autowired
	private MerchantManager merchantManager;
	
}

