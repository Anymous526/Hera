/*
 * @(#)TradeController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.trade.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-9
 */
@Controller
@RequestMapping("/manager/member")
public class TradeDetailController {

	
	@RequestMapping("/consume/consume.htm")
	public ModelAndView find(@ObjectConvertAnno Operator operator, @ObjectConvertAnno Pagination pagination) throws ParseException{
		pagination.setPageSize(10);
		ModelAndView model = new ModelAndView("/manager/member/consume.jsp");
		String name = HttpParameterParser.getString("name");
		String mobile = HttpParameterParser.getString("mobile");
		int level = HttpParameterParser.getIntValue("level");
		Date startTradeDate = HttpParameterParser.getDate("startTradeDate");
		Date endTradeDate = HttpParameterParser.getDate("endTradeDate");
		Map<String,Object> params = new HashMap<String,Object>();
		if(!StringUtils.isNullOrEmpty(name))
			params.put("name", name);
		if(!StringUtils.isNullOrEmpty(mobile))
			params.put("mobile", mobile);		
		if(level != 0)
			params.put("level", Level.get(level));
		if(startTradeDate != null)
			params.put("startTradeDate", DateUtils.getEarlyInTheDay(startTradeDate));
		if(endTradeDate != null)
			params.put("endTradeDate", DateUtils.getLateInTheDay(endTradeDate));
		if(operator.getMerchant().isExistChildren())
			params.put("memberGroup", operator.getMerchant().getMemberGroup());
		else
			params.put("createMerchant",operator.getMerchant());
		List<TradeDetail> list = tradeDetailManager.find(params, pagination);
		model.addObject("list",list);
		model.addObject("pagination",pagination);
		model.addObject("myname",name);
		model.addAllObjects(params);
		return model;
	}
	
	@Autowired
	private TradeDetailManager tradeDetailManager;
}

