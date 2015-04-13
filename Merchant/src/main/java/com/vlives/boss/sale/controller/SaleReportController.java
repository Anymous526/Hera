/*
 * @(#)SaleReportController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sale.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.XlsView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-21
 */
@Controller
@RequestMapping("/manager/report")
public class SaleReportController {

	@RequestMapping("saleploy/find.htm")
	public ModelAndView salePloy(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		ModelAndView model = new ModelAndView("/manager/report/sale/sale.jsp");
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params = constParam(merchant);
		List<SalePloy> list= salePloyManager.find(params, pagination);
		long smsCount = salePloyManager.sum(params, "smsCount");
		long sendCount = salePloyManager.sum(params, "sendCount");
		model.addObject("smsCount", smsCount);
		model.addObject("sendCount", sendCount);
		model.addObject("list", list);
		model.addObject("pagination", pagination);
		if(merchant.isExistChildren()){
			model.addObject("children", merchant.getChildrenAndMySelf());
		}
		Merchant mer = (Merchant)params.get("merchant");
		if(mer != null)
			model.addObject("merchantId", mer.getId());
		return model;
	}
	
	@RequestMapping("saleploy/list.htm")
	public ModelAndView list(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		ModelAndView model = new ModelAndView("/manager/report/sale/list.jsp");
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params = constParam(merchant);
		List<SalePloy> list= salePloyManager.find(params, pagination);
		long smsCount = salePloyManager.sum(params, "smsCount");
		long sendCount = salePloyManager.sum(params, "sendCount");
		model.addObject("smsCount", smsCount);
		model.addObject("sendCount", sendCount);
		model.addObject("list", list);
		model.addObject("pagination", pagination);
		if(merchant.isExistChildren()){
			model.addObject("children", merchant.getChildrenAndMySelf());
		}
		model.addAllObjects(params);
		return model;
	}
	
	@RequestMapping("saleploy/export.htm")
	public ModelAndView export(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		Map<String,Object> params =  constParam(operator.getMerchant());
		List<SalePloy> list = salePloyManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("sale_ploy.xls", map));
	}
	
	
	
	private Map<String,Object> constParam(Merchant merchant) {
		Map<String,Object> params = new HashMap<String, Object>();
		Long merchantId = HttpParameterParser.getLong("merchantId");
		String name = HttpParameterParser.getString("name");
		String mobile = HttpParameterParser.getString("mobile");
		int level = HttpParameterParser.getIntValue("level");
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
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
		if(level != 0){
			params.put("memberLevel", level);
			params.put("level", level);
		}
		if(startCreateDate != null)
			params.put("startCreateDate", DateUtils.getEarlyInTheDay(startCreateDate));
		if(endCreateDate != null)
			params.put("endCreateDate", DateUtils.getLateInTheDay(endCreateDate));
		return params;
	}
	
	
	@Autowired
	private SalePloyManager salePloyManager;
	
	@Autowired
	private MerchantManager merchantManager;
}

