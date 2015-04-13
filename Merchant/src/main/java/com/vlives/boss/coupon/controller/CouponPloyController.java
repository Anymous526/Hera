/**
 * @(#)CouponPloy.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloy.CouponPloyStatus;
import com.vlives.boss.coupon.domain.CouponPloyType;
import com.vlives.boss.coupon.domain.CouponType;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.trade.domain.TradeOrder.Type;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * @author unicorn
 *
 */
@Controller
@RequestMapping("/manager/coupon/ploy")
public class CouponPloyController {

	@Autowired
	private CouponPloyManager couponPloyManager;
	
	 
	
	@Autowired
	private MerchantManager merchantManager;
	
	@Autowired
	private CouponManager couponManager;
	
	@RequestMapping(value="/list.htm",method=RequestMethod.GET)
	public ModelAndView listCouponPloy(@ObjectConvertAnno Operator operator, @ObjectConvertAnno Pagination pagination) {
		ModelAndView model = new ModelAndView("/manager/couponploy/camp.jsp");
		Map<String,Object> params = new HashMap<String, Object>();
		List<CouponPloyStatus> status = new ArrayList<CouponPloy.CouponPloyStatus>();
		params = constParams(params);
		Integer ploystatus = HttpParameterParser.getInteger("ploystatus");
		if(ploystatus !=null){
			status.add(CouponPloyStatus.get(ploystatus));
		} else {
			status.add(CouponPloyStatus.AUDIT_SUCCESS);
			status.add(CouponPloyStatus.AUDIT_FAIL);
			status.add(CouponPloyStatus.COMPLETE);
			status.add(CouponPloyStatus.PAUSE);
			status.add(CouponPloyStatus.WAIT_AUDIT);
		}
		params.put("couponPloyStatus", status);
		long merchantId = HttpParameterParser.getLongValue("merchantId");
		Merchant merchant = operator.getMerchant();
		if(merchantId != 0 ) {
			Merchant merhcant = merchantManager.get(merchantId);
			params.put("applyMerchant", merhcant);
		} else if(merchant.isExistParent()){
			params.put("applyMerchant", merchant);
		} else {
			params.put("merchant",merchant);
		}
		List<CouponPloy> list = couponPloyManager.find(params, pagination);
		params = new HashMap<String, Object>();
		params.put("pagination",pagination);
		params.put("list", list);
		params.put("operator", operator);
		model.addAllObjects(params);
		return model;
	}
	
	
	private Map<String,Object> constParams(Map<String,Object> params) {
		int coupontype = HttpParameterParser.getIntValue("coupontype");
		int ploytype = HttpParameterParser.getIntValue("ploytype");
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
		if(coupontype!=0){
			params.put("couponType", CouponType.get(coupontype));
		} 
		if(ploytype!=0){
			params.put("couponPloyType", CouponPloyType.get(ploytype));
		} 
		if(startCreateDate != null) {
			params.put("validStartDate", DateUtils.getEarlyInTheDay(startCreateDate));
		}
		if(endCreateDate != null) {
			params.put("validEndDate", DateUtils.getLateInTheDay(endCreateDate));
		}
		return params;
	}
	
	@RequestMapping(value="/pause.htm",method=RequestMethod.PUT)
	public ModelAndView pausePloy(@ObjectConvertAnno Operator operator) {
		Map<String,Object> model = new HashMap<String, Object>();
		long couponPloyId = HttpParameterParser.getLongValue("couponPloyId");
		String pauseDsec = HttpParameterParser.getString("pauseDsec");
		try {
			CouponPloy couponPloy = couponPloyManager.get(couponPloyId);
			couponPloyManager.pausePloy(couponPloy, pauseDsec, operator);
			model.put("success", true);
			model.put("msg", "暂停活动成功");
			model.put("status", couponPloy.getCouponPloyStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (Exception e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	@RequestMapping(value="/restore.htm",method=RequestMethod.PUT)
	public ModelAndView restorePloy(@ObjectConvertAnno Operator operator) {
		Map<String,Object> model = new HashMap<String, Object>();
		long couponPloyId = HttpParameterParser.getLongValue("couponPloyId");
		String pauseDsec = HttpParameterParser.getString("pauseDsec");
		CouponPloy couponPloy = couponPloyManager.get(couponPloyId);
		try {
			couponPloyManager.restorePloy(couponPloy, pauseDsec, operator);
			model.put("success", true);
			model.put("msg", "恢复活动成功");
			model.put("status", couponPloy.getCouponPloyStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	@RequestMapping(value="/{id}/detail.htm")
	public ModelAndView getCouponPloy(@PathVariable Long id,@ObjectConvertAnno Operator operator) {
		ModelAndView model = new ModelAndView("/manager/couponploy/detail.jsp");
		CouponPloy couponPloy = couponPloyManager.get(id);
		BigDecimal totalMoney  = getMoney(couponPloy,null);
		BigDecimal brushMoney = getMoney(couponPloy,Type.TYPE_BRUSH);
		BigDecimal cashMoney = getMoney(couponPloy,Type.TYPE_CASH);
		BigDecimal brushrate = getMoneyPercent(brushMoney,totalMoney);
		BigDecimal cashrate = getMoneyPercent(cashMoney,totalMoney);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("totalMoney", totalMoney);
		result.put("brushMoney", brushMoney);
		result.put("cashMoney", cashMoney);
		result.put("brushrate", brushrate);
		result.put("cashrate", cashrate);
		model.addObject("couponPloy",couponPloy);
		model.addAllObjects(result);
		return model;
	}
	
	private BigDecimal getMoneyPercent(BigDecimal money,BigDecimal totalmoney) {
		if(totalmoney==BigDecimal.ZERO || money == BigDecimal.ZERO) {
			return BigDecimal.ZERO;
		}
		return  money.divide(totalmoney,4, RoundingMode.HALF_UP).movePointRight(2);
	}
	
	private BigDecimal getMoney(CouponPloy couponPloy,TradeOrder.Type tradeType) {
		 return couponManager.sumConsumeMoney(couponPloy, null, tradeType);
	}
	
	@RequestMapping(value="/{id}/modifyFind.htm",method=RequestMethod.GET)
	public ModelAndView modifyCouponPloy(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("/manager/couponploy/change.jsp");
		CouponPloy couponPloy = couponPloyManager.get(id);
		model.addObject("couponPloy",couponPloy);
		return model;
	}
	
	@RequestMapping(value="/{id}/save.htm")
	public ModelAndView save(@PathVariable Long id, @ObjectConvertAnno Operator operator) throws BusinessException {
		ModelAndView model = new ModelAndView(new RedirectView("/manager/coupon/ploy/"+id+"/detail.htm"));
		
		String title = HttpParameterParser.getString("title");
		String content = HttpParameterParser.getString("content");
		Date validStartDate = HttpParameterParser.getDate("validStartDate");
		Date validEndDate = HttpParameterParser.getDate("validEndDate");
		
		CouponPloy couponPloy = couponPloyManager.get(id);
		couponPloy.setTitle(title);
		couponPloy.setValidStartDate(validStartDate);
		couponPloy.setValidEndDate(validEndDate);
		couponPloy.setContent(content);
		
		couponPloyManager.modify(couponPloy, operator);
		
		model.addObject("couponPloy",couponPloy);
		return model;
	}
	
	
	@RequestMapping(value = "/logout.htm")
	public ModelAndView delCouponPoly(@ObjectConvertAnno Operator operator ) {
		Long id = HttpParameterParser.getLong("id");
		Map<String,Object> model = new HashMap<String, Object>();
		CouponPloy couponPloy = couponPloyManager.get(id);
		try {
			couponPloyManager.logout(couponPloy, operator);
			model.put("success", true);
			model.put("msg", "删除活动成功");
			model.put("status",couponPloy.getCouponPloyStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return  new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	 
}
