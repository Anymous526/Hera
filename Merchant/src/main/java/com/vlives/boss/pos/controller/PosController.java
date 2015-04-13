/**
 * @(#)PosController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.pos.controller;

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
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.pos.dto.PosInfo;
import com.vlives.boss.pos.manager.PosManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-26
 */
@Controller
@RequestMapping("/manager/platform/pos")
public class PosController {
	@Autowired
	private MerchantManager merchantManager;
	@Autowired
	private PosManager posManager;
	/**
	 * 创建POS
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/create.htm",method=RequestMethod.POST)
	public ModelAndView create(@ObjectConvertAnno Operator operator){
		String merchantCode =  HttpParameterParser.getString("merchantCode");
		Merchant merchant = merchantManager.getByCode(merchantCode);
		String code = HttpParameterParser.getString("posCode");
		String posSerialNumber = HttpParameterParser.getString("posSerialNumber");
		String posDesc = HttpParameterParser.getString("posDesc");
		try {
			posManager.create(operator, posSerialNumber, code, merchant, posDesc);
			return new ModelAndView(new JsonView(true,"创建POS机成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	/**
	 * 冻结POS
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/freeze.htm",method=RequestMethod.POST)
	public ModelAndView freeze(@ObjectConvertAnno Operator operator){
		
		String code = HttpParameterParser.getString("posCode");
		Pos pos = posManager.getByCode(code);
		if(pos == null) 
			return new ModelAndView(new JsonView(false,"pos 编号不存在"));
		try {
			posManager.freeze(pos, operator);
			return new ModelAndView(new JsonView(true,"冻结POS成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	/**
	 * 解冻POS
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/unfreeze.htm",method=RequestMethod.POST)
	public ModelAndView unFreeze(@ObjectConvertAnno Operator operator){
		
		String code = HttpParameterParser.getString("posCode");
		Pos pos = posManager.getByCode(code);
		if(pos == null) 
			return new ModelAndView(new JsonView(false,"pos 编号不存在"));
		try {
			posManager.unFreeze(pos, operator);
			return new ModelAndView(new JsonView(true,"解冻POS成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	/**
	 * 注销POS
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/logout.htm",method=RequestMethod.POST)
	public ModelAndView logout(@ObjectConvertAnno Operator operator){
		
		String code = HttpParameterParser.getString("posCode");
		Pos pos = posManager.getByCode(code);
		if(pos == null) 
			return new ModelAndView(new JsonView(false,"pos 编号不存在"));
		try {
			posManager.logOut(pos, operator);
			return new ModelAndView(new JsonView(true,"注销成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	/**
	 * 查询POS所属商户信息
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value="/find.htm",method=RequestMethod.GET)
	public ModelAndView find(@ObjectConvertAnno Operator operator, @ObjectConvertAnno Pagination pagination){
		
		String serialNumber = HttpParameterParser.getString("serialNumber");
		String code = HttpParameterParser.getString("code");
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(serialNumber==null)
			return new ModelAndView(new JsonView(false,"POS机序列号不能为空"));
		map.put("serialNumber",serialNumber);
		if(code!=null)
			map.put("code",code);
		List<PosInfo> posInfo = posManager.findPosInfo(map,pagination);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("posInfo", posInfo);
		result.put("pagination", pagination);

		return new ModelAndView(new JsonView(result));
	}
}
