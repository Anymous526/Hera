/*
 * @(#)MemberReportController.java
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
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.XlsView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * 会员报表统计
 * @author  fyuan
 * @version 1.0,2011-6-16
 */
@Controller
@RequestMapping("/manager/report")
public class MemberReportController {
	
	@RequestMapping("memberinfo/today.htm")
	public ModelAndView today(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/report/member/today.jsp");
		Date date = new Date();
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheDay(date),DateUtils.getLateInTheDay(date),merchant);
		List<Member> today = memberManager.find(params, pagination);
		if(merchant.isExistChildren()){
			model.addObject("children", merchant.getChildrenAndMySelf());
		}
		model.addObject("today",today);
		model.addObject("pagination",pagination);
		Merchant mer = (Merchant)params.get("createMerchant");
		if(mer != null)
		model.addObject("merchantId", mer.getId());
		return model;
	}
	
	@RequestMapping("memberinfo/today_export.htm")
	public ModelAndView todayExport(@ObjectConvertAnno Operator operator) throws BusinessException{
		Date date = new Date();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheDay(date),DateUtils.getLateInTheDay(date),operator.getMerchant());
		long count = memberManager.count(params);
		if(count > 5000)
			throw new BusinessException("导出数量大于5000条,不能导出");
		List<Member> list = memberManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("member.xls", map));
	}
	
	@RequestMapping("memberinfo/month.htm")
	public ModelAndView month(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/report/member/month.jsp");
		Date date = new Date();
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheMonth(date),DateUtils.getLateInTheDay(date),merchant);
		List<Member> month = memberManager.find(params, pagination);
		if(merchant.isExistChildren()){
			model.addObject("children", merchant.getChildrenAndMySelf());
		}
		model.addObject("month",month);
		model.addObject("pagination",pagination);
		Merchant mer = (Merchant)params.get("createMerchant");
		if(mer != null)
		model.addObject("merchantId", mer.getId());
		return model;
	}
	
	@RequestMapping("memberinfo/month_export.htm")
	public ModelAndView monthExport(@ObjectConvertAnno Operator operator) throws BusinessException{
		Date date = new Date();
		Map<String,Object> params = constParam(DateUtils.getEarlyInTheMonth(date),DateUtils.getLateInTheDay(date),operator.getMerchant());
		long count = memberManager.count(params);
		if(count > 5000)
			throw new BusinessException("导出数量大于5000条,不能导出");
		List<Member> list = memberManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("member.xls", map));
	}
	
	@RequestMapping("memberinfo/list.htm")
	public ModelAndView list(@ObjectConvertAnno Pagination pagination,@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/report/member/list.jsp");
		Merchant merchant = operator.getMerchant();
		Map<String,Object> params = constParam(null,null,merchant);
		List<Member> list = memberManager.find(params, pagination);
		if(merchant.isExistChildren()){
			model.addObject("children", merchant.getChildrenAndMySelf());
		}
		model.addObject("list",list);
		model.addObject("pagination",pagination);
		model.addAllObjects(params);
		return model;
	}
	
	@RequestMapping("memberinfo/list_export.htm")
	public ModelAndView listExport(@ObjectConvertAnno Operator operator) throws BusinessException{
		Map<String,Object> params = constParam(null,null,operator.getMerchant());
		long count = memberManager.count(params);
		if(count > 5000)
			throw new BusinessException("导出数量大于5000条,不能导出");
		List<Member> list = memberManager.find(params);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new ModelAndView(new XlsView("member.xls", map));
	}

	private Map<String,Object> constParam(Date startCreateDate,Date endCreateDate,Merchant merchant) {
		Map<String,Object> params = new HashMap<String, Object>();
		Long merchantId = HttpParameterParser.getLong("merchantId");
		String name = HttpParameterParser.getString("name");
		String mobile = HttpParameterParser.getString("mobile");
		int level = HttpParameterParser.getIntValue("level");
		Date startDate = HttpParameterParser.getDate("startCreateDate");
		Date endDate = HttpParameterParser.getDate("endCreateDate");
		if(merchantId != null && merchantId.longValue() != 0){
			Merchant mer = merchantManager.get(merchantId);
			if(mer != null)
				params.put("createMerchant", mer);
		}else{
			//同一组的商户共享会员
			params.put("memberGroup", merchant.getMemberGroup());
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
		params.put("startCreateDate",startCreateDate);
		params.put("endCreateDate",endCreateDate);
		if(startDate != null)
			params.put("startCreateDate", DateUtils.getEarlyInTheDay(startDate));
		if(endDate != null)
			params.put("endCreateDate", DateUtils.getLateInTheDay(endDate));
		return params;
	}
	
	@Autowired
	private MemberManager memberManager;
	
	@Autowired
	private MerchantManager merchantManager;
	
}

