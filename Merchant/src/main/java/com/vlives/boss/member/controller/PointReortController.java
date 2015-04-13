/*
 * @(#)PointReortController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.member.manager.MemberPointManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.trade.domain.TradeOrder.Type;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.XlsView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-20
 */
@Controller
@RequestMapping("/manager/report")
public class PointReortController {
	
	@RequestMapping("memberpoint/find.htm")
	public ModelAndView find(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		ModelAndView model = new ModelAndView("/manager/report/point/point.jsp");
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params =  constParam(merchant);
		List<MemberPointDetail> list = memberPointManager.find(params, pagination);
		model.addObject("list",list);
		model.addObject("pagination",pagination);
		if(merchant.isExistChildren()){
			model.addObject("children", merchant.getChildrenAndMySelf());
		}
		Merchant mer = (Merchant)params.get("merchant");
		if(mer != null)
			model.addObject("merchantId", mer.getId());
		return model;
	}
	@RequestMapping("memberpoint/export.htm")
	public ModelAndView export(@ObjectConvertAnno Operator operator){
		Map<String,Object> params =  constParam(operator.getMerchant());
		List<MemberPointDetail> list = memberPointManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("member_point.xls", map));
	}
	
	
	
	@RequestMapping("memberpoint/list.htm")
	public ModelAndView list(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		ModelAndView model = new ModelAndView("/manager/report/point/list.jsp");
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params =  constParam(merchant);
		pagination.setPageSize(10);
		List<MemberPointDetail> list = memberPointManager.find(params, pagination);
		model.addObject("list",list);
		model.addObject("pagination",pagination);
		if(merchant.isExistChildren()){
			model.addObject("children", merchant.getChildrenAndMySelf());
		}
		model.addAllObjects(params);
		return model;
	}
	
	private Map<String,Object> constParam(Merchant merchant) {
		Map<String,Object> params = new HashMap<String, Object>();
		Long merchantId = HttpParameterParser.getLong("merchantId");
		String name = HttpParameterParser.getString("name");
		String mobile = HttpParameterParser.getString("mobile");
		int level = HttpParameterParser.getIntValue("level");
		int pointType = HttpParameterParser.getIntValue("pointType"); //积分变更类型
		int type = HttpParameterParser.getIntValue("type");//交易方式
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
		Date startConsumeDate = HttpParameterParser.getDate("startConsumeDate");
		Date endConsumeDate = HttpParameterParser.getDate("endConsumeDate");
		if(merchantId != null && merchantId.longValue() != 0){
			Merchant mer = merchantManager.get(merchantId);
			if(mer != null)
				params.put("merchant", mer);
		}else{
			if(merchant.isExistChildren())
				params.put("memberGroup", merchant.getMemberGroup());
			else
				params.put("merchant",merchant);
		}
		if(!StringUtils.isNullOrEmpty(name)){
			params.put("name", name);
			params.put("myname", name);
		}
		if(!StringUtils.isNullOrEmpty(mobile))
			params.put("mobile", mobile);
		if(level != 0)
			params.put("level", Level.get(level));
		if(type != 0)
			params.put("type", Type.get(type));
		if(pointType != 0)
			params.put("pointType",com.vlives.boss.member.domain.MemberPointDetail.Type.get(pointType));
		if(startCreateDate != null)
			params.put("startCreateDate", DateUtils.getEarlyInTheDay(startCreateDate));
		if(endCreateDate != null)
			params.put("endCreateDate", DateUtils.getLateInTheDay(endCreateDate));
		if(startConsumeDate != null)
			params.put("startConsumeDate", DateUtils.getEarlyInTheDay(startConsumeDate));
		if(endConsumeDate != null)
			params.put("endConsumeDate", DateUtils.getLateInTheDay(endConsumeDate));
		return params;
	}
	
	@Autowired
	private MemberPointManager memberPointManager;
	
	@Autowired
	private MerchantManager merchantManager;
}

