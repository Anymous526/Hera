/**
 * @(#)SalePloyController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sale.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.coupon.domain.TempCouponPloy;
import com.vlives.boss.member.domain.ActiveRate;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.domain.SalePloy.SalePloyType;
import com.vlives.boss.sale.domain.TempSalePloy;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.boss.sale.manager.TempSalePloyManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-14
 */
@Controller
@RequestMapping("/manager/saleploycreate") 
public class CreateSalePloyController {
	@Autowired
	private SalePloyManager salePloyManager;
	@Autowired
	private TempSalePloyManager tempSalePloyManager;
	@Autowired
	private MerchantManager merchantManager;
	
	@Autowired
	private MemberManager memberManager;
	
	@RequestMapping(value="/create_one.htm",method=RequestMethod.GET)
	public ModelAndView createOne(@ObjectConvertAnno Operator operator) {
		Merchant merchant = operator.getMerchant();
		boolean existCreateMemberSend = salePloyManager.existSalePloyByType(operator.getMerchant(),SalePloyType.CREATE_MEMBER_SEND);
		boolean existtradeMoneySend = salePloyManager.existSalePloyByType(operator.getMerchant(),SalePloyType.TRADE_MONEY_SEND);
		Long tempId = HttpParameterParser.getLong("tempId");
		
		Map<String, Object> model = new HashMap<String, Object>();
		if(tempId!=null) {
			TempSalePloy tempSalePloy = tempSalePloyManager.get(tempId);
			model.put("tempSalePloy",tempSalePloy);
		}
		model.put("merchant",merchant);
		model.put("existCreateMemberSend",existCreateMemberSend);
		model.put("existtradeMoneySend",existtradeMoneySend);
		return new ModelAndView("/manager/saleploy/create_one.jsp",model);
	}
	@RequestMapping(value="/create_two.htm",method=RequestMethod.POST)
	public ModelAndView createTwo(@ObjectConvertAnno Operator operator) {
		String name = HttpParameterParser.getString("name");
		Date startDate = HttpParameterParser.getDate("startDate");
		startDate = DateUtils.getEarlyInTheDay(startDate);
		Date endDate = HttpParameterParser.getDate("endDate");
		endDate = DateUtils.getLateInTheDay(endDate);
		String applyMerchant = HttpParameterParser.getString("merchantName");
		String content = HttpParameterParser.getString("content");
		int salePloyTypeInt = HttpParameterParser.getIntValue("saleType",1);
		BigDecimal tradeMinMoney = HttpParameterParser.getBigDecimal("tradeMinMoney");
		Date timingTime = HttpParameterParser.getDate("timingTime");
//		StringBuilder sb = new StringBuilder(content);
		 
		 
		TempSalePloy tempSalePloy = new TempSalePloy();
		tempSalePloy.setValidStartDate(startDate);
		tempSalePloy.setValidEndDate(endDate);
		tempSalePloy.setApplyMerchant(applyMerchant);
		tempSalePloy.setMerchant(operator.getMerchant());
		tempSalePloy.setName(name);
//		tempSalePloy.setTemplate(sb.toString().trim());
		tempSalePloy.setSalePloyType(SalePloyType.get(salePloyTypeInt));
		tempSalePloy.setTimingTime(timingTime);
		tempSalePloy.setTradeMinMoney(tradeMinMoney);
		
		if(content.indexOf("有效期：")<=0) {
			StringBuilder sb = new StringBuilder(content+" 有效期：");
			sb.append(DateUtils.format(tempSalePloy.getValidStartDate(), "MM月dd日"));
			sb.append("到");
			sb.append(DateUtils.format(tempSalePloy.getValidEndDate(), "MM月dd日"));
			tempSalePloy.setTemplate(sb.toString());
		}
		
		tempSalePloyManager.create(tempSalePloy);
		return new ModelAndView(new RedirectView("/manager/saleploycreate/create_two.htm?tempId="+tempSalePloy.getId()));
	}
	
	@RequestMapping(value="/create_two.htm",method=RequestMethod.GET)
	public ModelAndView createTwoView(@ObjectConvertAnno Operator operator) {
		Long tempId = HttpParameterParser.getLong("tempId");
		TempSalePloy tempSalePloy = tempSalePloyManager.get(tempId);
		return new ModelAndView("/manager/saleploy/create_two.jsp","tempSalePloy",tempSalePloy);
	}
	
	
	@RequestMapping(value="/create_three.htm",method=RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_CREATE_SALEPLOY)
	public ModelAndView createThreeCommit(@ObjectConvertAnno Operator operator,HttpServletRequest request) {
		Long tempId = HttpParameterParser.getLong("tempId");
		TempSalePloy tempSalePloy = tempSalePloyManager.get(tempId);
		String template = HttpParameterParser.getString("template");
		if(tempSalePloy.getSalePloyType().isSinglePloy()) {
			tempSalePloy.setTemplate(template);
			tempSalePloyManager.update(tempSalePloy);
			return new ModelAndView(new RedirectView("/manager/saleploycreate/create_three_view.htm?tempId="+tempSalePloy.getId()));
		}
		else {
			SalePloy salePloy = new SalePloy();
			salePloy.setMerchant(operator.getMerchant());
			salePloy.setName(tempSalePloy.getName());
			salePloy.setSalePloyType(tempSalePloy.getSalePloyType());
			salePloy.setTemplate(template);
			salePloy.setTimingTime(tempSalePloy.getTimingTime());
			salePloy.setTradeMinMoney(tempSalePloy.getTradeMinMoney());
			salePloy.setValidStartDate(tempSalePloy.getValidStartDate());
			salePloy.setValidEndDate(tempSalePloy.getValidEndDate());
			salePloy.setApplyMerchant(tempSalePloy.getApplyMerchant());
			try {
				salePloyManager.create(salePloy, null,true, operator);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			return new ModelAndView(new RedirectView("/manager/saleploycreate/create_success.htm"));
		}
	}
	
	@RequestMapping(value="/create_three_view.htm")
	public ModelAndView createThreeView(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination) {
		List<Member> members = memberManager.findBySalePloy(getSalePloy(operator.getMerchant()), getExcludeMembers(), pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		 
		model.put("operator", operator);
		model.put("members", members);
		model.put("pagination", pagination);
		if(members==null||members.size() == 0||pagination.getCount()>operator.getMerchant().getMerchantSmsAccount().getRemainCount()) {
			model.put("canSubmit", false);
		}
		else {
			model.put("canSubmit", true);
		}
		return new ModelAndView("/manager/saleploy/create_three.jsp",model);
	}
	private SalePloy getSalePloy(Merchant merchant) {
		int activeRateInt = HttpParameterParser.getIntValue("activeRate");
		Integer maxPoint = HttpParameterParser.getInteger("maxPoint");
		Integer minPoint = HttpParameterParser.getInteger("minPoint");
		int[] memberLevelInts = HttpParameterParser.getIntArray("memberLevel");
		SalePloy salePloy = new SalePloy();
		if(activeRateInt!=0) {
			salePloy.setActiveRate(ActiveRate.get(activeRateInt));
		}
		if(maxPoint!=null) {
			salePloy.setMaxPoint(maxPoint);
		}
		if(minPoint!=null) {
			salePloy.setMinPoint(minPoint);
		}
		if(memberLevelInts!=null&&memberLevelInts.length>0) {
			List<Level> memberLevels = new ArrayList<Level>();
			for(int levelInt : memberLevelInts) {
				Level level = Level.get(levelInt);
				memberLevels.add(level);
			}
			salePloy.setMemberLevels(memberLevels);
		}
		salePloy.setMerchant(merchant);
		return salePloy;
	}
	
	private List<Member> getExcludeMembers() {
		String excludeMemberIdStr = HttpParameterParser.getString("excludeMemberId");
		List<Member> excludeMembers = new ArrayList<Member>();
		if(!StringUtils.isNullOrEmpty(excludeMemberIdStr)) {
			String[] idsStr = excludeMemberIdStr.split(",");
			for(String id : idsStr) {
				Member member = memberManager.get(new Long(id));
				excludeMembers.add(member);
			}
		}
		return excludeMembers;
	}
	
	@RequestMapping(value="/create_success.htm",method=RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_CREATE_SALEPLOY)
	public ModelAndView createSubmit(@ObjectConvertAnno Operator operator) {
		Long tempId = HttpParameterParser.getLong("tempId");
		TempSalePloy tempSalePloy = tempSalePloyManager.get(tempId);
		SalePloy salePloy = getSalePloy(operator.getMerchant());
		salePloy.setName(tempSalePloy.getName());
		salePloy.setTemplate(tempSalePloy.getTemplate());
		salePloy.setSalePloyType(tempSalePloy.getSalePloyType());
		salePloy.setValidStartDate(tempSalePloy.getValidStartDate());
		salePloy.setValidEndDate(tempSalePloy.getValidEndDate());
		salePloy.setApplyMerchant(tempSalePloy.getApplyMerchant());
		if(salePloy.isTimingSend()) {
			salePloy.setTimingTime(tempSalePloy.getTimingTime());
		}
		if(salePloy.isTradeMoneySend()) {
			salePloy.setTradeMinMoney(tempSalePloy.getTradeMinMoney());
		}
		List<Member> excludeMembers = getExcludeMembers();
		try {
			salePloyManager.create(salePloy, excludeMembers,true, operator);
		} catch (BusinessException e) {
			return new ModelAndView("/manager/saleploycreate/create_three_view.htm","errorMsg",e.getMessage());
		}
		return new ModelAndView(new RedirectView("/manager/saleploycreate/create_success.htm"));
	}
	
	@RequestMapping(value="/create_success.htm",method=RequestMethod.GET)
	public ModelAndView createSuccess(@ObjectConvertAnno Operator operator) {
		return new ModelAndView("/manager/saleploy/create_success.jsp");
	}
	
	
	/**
	 * 管理平台创建营销活动
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/create.htm",method=RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_CREATE_SALEPLOY)
	public ModelAndView createByPlatform(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLongValue("id");
		String name = HttpParameterParser.getString("name");
		String template = HttpParameterParser.getString("template");
		int salePloyTypeInt = HttpParameterParser.getIntValue("salePloyType",1);
		BigDecimal tradeMinMoney = HttpParameterParser.getBigDecimal("tradeMinMoney");
		Date  timingTime = HttpParameterParser.getDate("timingTime","yyyy-MM-dd HH:mm:ss");
		Date startDate = HttpParameterParser.getDate("startDate");
		startDate = DateUtils.getEarlyInTheDay(startDate);
		Date endDate = HttpParameterParser.getDate("endDate");
		endDate = DateUtils.getLateInTheDay(endDate);
		String applyMerchant = HttpParameterParser.getString("applyMerchant");
		
		Merchant merchant = merchantManager.get(id);
		SalePloy salePloy = getSalePloy(merchant);
		salePloy.setValidStartDate(startDate);
		salePloy.setValidEndDate(endDate);
		if(!StringUtils.isNullOrEmpty(applyMerchant)&&merchant.isExistValidChildren()) {
			String value = "";
			String[] childIds = applyMerchant.split(",");
			for(String childId : childIds) {
				if(merchant.getId().toString().equals(childId)) {
					value+=merchant.getName()+",";
				}
				for(Merchant children : merchant.getValidChildrens()) {
					if(children.getId().toString().equals(childId)) {
						value+=children.getName()+",";
					}
				}
			}
			salePloy.setApplyMerchant(value);
		}
		
		salePloy.setName(name);
		salePloy.setTemplate(template);
		salePloy.setSalePloyType(SalePloyType.get(salePloyTypeInt));
		if(salePloy.isTimingSend()) {
			timingTime = timingTime== null?DateUtils.add(new Date(), TimeUnit.DAYS, 2):timingTime;
			salePloy.setTimingTime(timingTime);
		}
		if(salePloy.isTradeMoneySend()) {
			salePloy.setTradeMinMoney(tradeMinMoney);
		}
		try {
			salePloyManager.create(salePloy, null, false,operator);
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "创建营销活动成功");
		model.put("id", salePloy.getId());
		return new ModelAndView(new JsonView(model));
	}
}