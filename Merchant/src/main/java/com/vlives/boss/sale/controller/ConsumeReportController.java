/*
 * @(#)ConsumeReportController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sale.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.XlsView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-18
 */
@Controller
@RequestMapping("/manager/report")
public class ConsumeReportController {

	@Autowired
	private TradeDetailManager tradeDetailManager;
	
	@Autowired
	private MerchantManager merchantManager;
	
	@RequestMapping("/memberconsume/today.htm")
	public ModelAndView today(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/report/consume/today.jsp");
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheDay(new Date()),DateUtils.getLateInTheDay(new Date()),merchant);
		List<TradeDetail> today = tradeDetailManager.find(params, pagination);
		Set<Merchant> set = null;
		 
		if(merchant.isExistChildren())
			set = merchant.getChildrenAndMySelf();
		if(set != null && set.size() > 1)
			model.addObject("children", set);
		model.addObject("today",today);
		model.addObject("pagination",pagination);
		Merchant mer = (Merchant)params.get("createMerchant");
		if(mer != null)
		model.addObject("merchantId", mer.getId());
		return model;
	}
	
	@RequestMapping("memberconsume/today_export.htm")
	public ModelAndView todayExport(@ObjectConvertAnno Operator operator) throws BusinessException{
		Date date = new Date();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheDay(date),DateUtils.getLateInTheDay(date),operator.getMerchant());
		long count = tradeDetailManager.count(params);
		if(count > 5000)
			throw new BusinessException("导出数量大于5000条,不能导出");
		List<TradeDetail> list = tradeDetailManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("consume.xls", map));
	}
	
	@RequestMapping("memberconsume/month.htm")
	public ModelAndView month(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/report/consume/month.jsp");
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheMonth(new Date()),DateUtils.getLateInTheDay(new Date()),merchant);
		List<TradeDetail> month = tradeDetailManager.find(params, pagination);
		Set<Merchant> set = null;
		if(merchant.isExistChildren())
			set = merchant.getChildrenAndMySelf();
		if(set != null && set.size() > 1)
			model.addObject("children", set);
		model.addObject("month",month);
		model.addObject("pagination",pagination);
		Merchant mer = (Merchant)params.get("createMerchant");
		if(mer != null)
		model.addObject("merchantId", mer.getId());
		return model;
	}
	
	@RequestMapping("memberconsume/month_export.htm")
	public ModelAndView monthExport(@ObjectConvertAnno Operator operator) throws BusinessException{
		Date date = new Date();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheMonth(date),DateUtils.getLateInTheDay(date),operator.getMerchant());
		long count = tradeDetailManager.count(params);
		if(count > 5000)
			throw new BusinessException("导出数量大于5000条,不能导出");
		List<TradeDetail> list = tradeDetailManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("consume.xls", map));
	}
	
	@RequestMapping("memberconsume/list.htm")
	public ModelAndView list(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/report/consume/list.jsp");
		Merchant merchant  = operator.getMerchant();
		Map<String,Object> params = constParam(null,null,merchant);
		List<TradeDetail> list = tradeDetailManager.find(params, pagination);
		Set<Merchant> set = null;
		if(merchant.isExistChildren())
			set = merchant.getChildrenAndMySelf();
		if(set != null && set.size() > 1)
			model.addObject("children", set);
		model.addObject("list",list);
		model.addObject("pagination",pagination);
		model.addAllObjects(params);
		return model;
	}
	
	@RequestMapping("memberconsume/list_export.htm")
	public ModelAndView listExport(@ObjectConvertAnno Operator operator) throws BusinessException{
		Map<String,Object> params = constParam(null,null,operator.getMerchant());
		long count = tradeDetailManager.count(params);
		if(count > 5000)
			throw new BusinessException("导出数量大于5000条,不能导出");
		List<TradeDetail> list = tradeDetailManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("consume.xls", map));
	}
	
	private Map<String,Object> constParam(Date startCreateDate,Date endCreateDate,Merchant merchant) {
		Map<String,Object> params = new HashMap<String, Object>();
		Long merchantId = HttpParameterParser.getLong("merchantId");
		String name = HttpParameterParser.getString("name");
		String mobile = HttpParameterParser.getString("mobile");
		int level = HttpParameterParser.getIntValue("level");
		Date startDate = HttpParameterParser.getDate("startTradeDate");
		Date endDate = HttpParameterParser.getDate("endTradeDate");
		if(merchantId != null && merchantId.longValue() != 0){
			Merchant mer = merchantManager.get(merchantId);
			if(mer != null)
				params.put("createMerchant", mer);
		}else{
			if(merchant.isExistChildren())
				params.put("memberGroup", merchant.getMemberGroup());
			else
				params.put("createMerchant",merchant);
		}
		if(!StringUtils.isNullOrEmpty(name)){
			params.put("name", name);
			params.put("myname", name);
		}
		if(!StringUtils.isNullOrEmpty(mobile))
			params.put("mobile", mobile);
		if(level != 0)
			params.put("level", Level.get(level));
		params.put("memberGroup",merchant.getMemberGroup());
		params.put("startTradeDate",startCreateDate);
		params.put("endTradeDate",endCreateDate);
		if(startDate != null)
			params.put("startTradeDate", DateUtils.getEarlyInTheDay(startDate));
		if(endDate != null)
			params.put("endTradeDate", DateUtils.getLateInTheDay(endDate));
		return params;
	}
	
}

