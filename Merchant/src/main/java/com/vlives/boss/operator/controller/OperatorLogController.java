/**
 * @(#)OperatorLogController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog;
import com.vlives.boss.operator.manager.OperatorLogManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.AcceptHashMap;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-9
 */
@Controller
@RequestMapping("/manager/security/log")
public class OperatorLogController {
	@Autowired
	private OperatorLogManager operatorLogManager;
	
	@RequestMapping("/list.htm")
	public ModelAndView modifyPassword(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination) {
		List<OperatorLog> logs = operatorLogManager.find(getDynamicParam(operator), pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("logs", logs);
		model.put("pagination", pagination);
		return new ModelAndView("/manager/security/log/list.jsp",model);
	}
	
	private Map<String,Object> getDynamicParam(Operator operator) {
		AcceptHashMap<String,Object> params = AcceptHashMap.newInstance();
		String mobile = HttpParameterParser.getString("mobile");
		String name = HttpParameterParser.getString("name");
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
		params.accept("merchant", operator.getMerchant())
		.acceptIf("mobile", mobile+"%", !StringUtils.isNullOrEmpty(mobile))
		.acceptIf("name", name, !StringUtils.isNullOrEmpty(name));
		if(startCreateDate!=null) {
			startCreateDate = DateUtils.getEarlyInTheDay(startCreateDate);
			params.put("startCreateDate", startCreateDate);
		}
		if(endCreateDate!=null) {
			endCreateDate =  DateUtils.getLateInTheDay(endCreateDate);
			params.put("endCreateDate", endCreateDate);
		}
		return params;
	}
}
