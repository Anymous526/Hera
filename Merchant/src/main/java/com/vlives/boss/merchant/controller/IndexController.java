/**
 * @(#)RegisterControler.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.border.domain.Border;
import com.vlives.boss.border.manager.BorderManager;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sale.domain.SalePloy.Status;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.util.DateUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-3-22
 */
@Controller
@RequestMapping("/manager")
public class IndexController {
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private SalePloyManager salePloyManager;
	@Autowired
	private BorderManager borderManager;
	
	/**
	 * 登录后的首页
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping("/index.htm")
	public ModelAndView loginCommit(@ObjectConvertAnno Operator operator) {
		List<Border> borderList = borderManager.newsBorder(4);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("operator", operator);
		model.put("borderList", borderList);
		putMemberStat(model, operator);
		putTradeStat(model, operator);
		putSalePloyStat(model, operator);
		//评论数据
		model.put("statistic", operator.getMerchant().getMerchantReferenceStatistic());
		return new ModelAndView("/manager/index.jsp",model);
	}
	/**
	 * 统计会员
	 * @author jianguo.xu
	 * @param model
	 * @param operator
	 */
	private void putMemberStat(Map<String, Object> model,Operator operator) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberGroup", operator.getMerchant().getMemberGroup());
		long memberTotalCount = memberManager.count(params);
		params.put("startCreateDate", DateUtils.getEarlyInTheDay(new Date()));
		long memberTodayAddCount = memberManager.count(params);
		params.put("startCreateDate", DateUtils.getEarlyInTheMonth(new Date()));
		long memberToMonthAddCount = memberManager.count(params);
		model.put("memberTodayAddCount", memberTodayAddCount);
		model.put("memberToMonthAddCount", memberToMonthAddCount);
		model.put("memberTotalCount", memberTotalCount);
	}
	/**
	 * 统计交易
	 * @author jianguo.xu
	 * @param model
	 * @param operator
	 */
	private void putTradeStat(Map<String, Object> model,Operator operator) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("createMerchant", operator.getMerchant());
		long tradeTotalCount = tradeDetailManager.count(params);
		BigDecimal tradeTotalMoney = tradeDetailManager.sumTradeMoney(params);
		params.put("startTradeDate", DateUtils.getEarlyInTheDay(new Date()));
		long tradeTodayTradeCount = tradeDetailManager.count(params);
		params.put("startTradeDate", DateUtils.getEarlyInTheMonth(new Date()));
		long tradeToMonthTradeCount = tradeDetailManager.count(params);
		model.put("tradeTotalCount", tradeTotalCount);
		model.put("tradeTotalMoney", tradeTotalMoney);
		model.put("tradeTodayTradeCount", tradeTodayTradeCount);
		model.put("tradeToMonthTradeCount", tradeToMonthTradeCount);
	}
	
	/**
	 * put营销活动统计
	 * @author jianguo.xu
	 */
	private void putSalePloyStat(Map<String, Object> model,Operator operator) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", operator.getMerchant());
		params.put("status", Status.WAIT_AUDIT);
		long salePloyTotalCount = salePloyManager.count(params);
		params.put("status", Status.SEND_COMPLETE);
		long salePloySuccessCount = salePloyManager.count(params);
		
		model.put("salePloyTotalCount", salePloyTotalCount);
		model.put("salePloySuccessCount", salePloySuccessCount);
		 
	}

}	
