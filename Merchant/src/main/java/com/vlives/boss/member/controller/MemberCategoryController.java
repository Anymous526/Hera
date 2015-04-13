/*
 * @(#)MemberCategoryController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.util.DateUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-6-17
 */
@Controller
@RequestMapping("/manager/report")
public class MemberCategoryController {

	@Autowired
	private MemberManager memberManager;

	/**
	 * 通过时间统计会员
	 * @param operator
	 * @return
	 */
	@RequestMapping("memberinfo/category.htm")
	public ModelAndView category(@ObjectConvertAnno Operator operator){
		ModelAndView model = new ModelAndView("/manager/report/member/category.jsp");
		Object  args1 [] = {operator.getMerchant().getId(),DateUtils.getEarlyInTheDay(new Date()),DateUtils.getLateInTheDay(new Date())};
		Object  args2 [] = {operator.getMerchant().getId(),DateUtils.getEarlyInTheMonth(new Date()),DateUtils.getLateInTheDay(new Date())};
		List<Map<String,Object>> today = memberManager.statByDate(args1);
		List<Map<String,Object>> month = memberManager.statByDate(args2);
		List<Map<String,Object>> all = memberManager.statAll(operator.getMerchant());

		List<Object> list = new ArrayList<Object>();

		int total=0;
		for(int i=0; i<all.size(); i++){
			total += Integer.valueOf(all.get(i).get("count").toString());
		}
		
		NumberFormat numberFormat = NumberFormat.getInstance();  
		numberFormat.setMaximumFractionDigits(0); 
		 
		for(int i=0; i<all.size(); i++){
			String percentage = numberFormat.format((float)Integer.valueOf(all.get(i).get("count").toString())/(float)total*100);
			list.add(percentage);
		}

		model.addObject("today",today);
		model.addObject("month",month);
		model.addObject("all",all);
		model.addObject("list", list);
		
		return model;
	}
}
