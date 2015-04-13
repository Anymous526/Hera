/**
 * @(#)SalePloyController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.domain.SalePloy.Status;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.AcceptHashMap;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-19
 */
@Controller
@RequestMapping("/manager/saleploy")
public class SalePloyController {
	@Autowired
	private SalePloyManager salePloyManager;
	
	@Autowired
	private MerchantManager merchantManager;
	/**
	 * 审核活动
	 * @author jianguo.xu
	 * @param operator
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/audit.htm",method=RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_AUDIT_SALEPLOY)
	public ModelAndView auditPloy(@ObjectConvertAnno Operator operator) {
		boolean pass = HttpParameterParser.getBooleanValue("pass");
		String desc = HttpParameterParser.getString("desc");
		Long id = HttpParameterParser.getLong("id");
		SalePloy salePloy = salePloyManager.get(id);
		try {
			salePloyManager.auditPloy(salePloy, pass, desc, operator);
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
		return new ModelAndView(new JsonView(true,"审核成功"));
	}
	/**
	 * 查询活动
	 * @author jianguo.xu
	 * @param operator
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value="/list.htm",method=RequestMethod.GET)
	public ModelAndView list(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination) {
		Merchant merchant = operator.getMerchant();
		
		Map<String, Object> params = getSerachParams(merchant);
		
		params.put("notInstatus", new Status[]{Status.LOGOUT});
		List<SalePloy> salePloys = salePloyManager.find(params, pagination);
		
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("salePloys", salePloys);
		model.put("pagination", pagination);
		return new ModelAndView("/manager/saleploy/list.jsp",model);
	}
	/**
	 * 查询活动以JSON形式返回数据
	 * @author jianguo.xu
	 * @param operator
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value="/list_json.htm",method=RequestMethod.GET)
	public ModelAndView listJson(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination) {
		String merchantCode = HttpParameterParser.getString("merchantCode");
		Merchant merchant = null;
		if(!StringUtils.isNullOrEmpty(merchantCode))
			merchant = merchantManager.getByCode(merchantCode);		
		List<SalePloy> salePloys = salePloyManager.find(getSerachParams(merchant), pagination);
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("salePloys", salePloys);
		model.put("pagination", pagination);
		return new ModelAndView(new JsonView("saleploys",model));
	}
	
	private Map<String,Object> getSerachParams(Merchant merchant) {
		Integer statusInteger = HttpParameterParser.getInteger("status");
		String name = HttpParameterParser.getString("name");
		String merchantName = HttpParameterParser.getString("merchantName");
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");
		AcceptHashMap<String, Object> params = AcceptHashMap.newInstance();
		params.acceptIf("merchant", merchant, merchant!=null).acceptIf("name", name, !StringUtils.isNullOrEmpty(name));
		if(statusInteger!=null) {
			params.accept("status", Status.get(statusInteger));
		}
		if(!StringUtils.isNullOrEmpty(merchantName)) {
			try {
				params.accept("merchantName", URLDecoder.decode(merchantName,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
			}
		}

		if(startCreateDate!=null) {
			startCreateDate = DateUtils.getEarlyInTheDay(startCreateDate);
			params.accept("startCreateDate", startCreateDate);
		}
		if(endCreateDate!=null) {
			endCreateDate = DateUtils.getLateInTheDay(endCreateDate);
			params.accept("endCreateDate", endCreateDate);
		}
		return params;
	}
	
	/**
	 * 注销营销活动
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/logout.htm",method={RequestMethod.POST,RequestMethod.PUT})
	public ModelAndView logoutSalePloy(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		SalePloy salePloy = salePloyManager.get(id);
		try {
			salePloyManager.logOut(salePloy, operator);
			Map<String,Object> model = new HashMap<String, Object>();
			model.put("success", true);
			model.put("msg", "注销成功");
			model.put("status", salePloy.getStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
			
		}
	}
	
	/**
	 * 修改营销活动内容
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/modify.htm",method=RequestMethod.PUT)
	public ModelAndView modifyContent(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		String template = HttpParameterParser.getString("template");
		SalePloy salePloy = salePloyManager.get(id);
		try {
			salePloyManager.modity(salePloy, template, operator);
			Map<String,Object> model = new HashMap<String, Object>();
			model.put("success", true);
			model.put("msg", "修改成功,系统将重新审核你提交的内容");
			model.put("template", template);
			model.put("status", salePloy.getStatus().getDesc());
			return new ModelAndView(new JsonView(model));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
			
		}
	}
	
	
	 
}
