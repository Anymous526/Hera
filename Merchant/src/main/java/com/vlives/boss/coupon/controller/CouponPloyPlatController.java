/**
 * @(#)CouponPloy.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.area.domain.Area.AreaType;
import com.vlives.boss.area.manager.AreaManager;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloy.CouponPloyStatus;
import com.vlives.boss.coupon.domain.CouponType;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.vaildator.ParamValidator;
import com.vlives.core.support.spring.vaildator.ParamValidators;
import com.vlives.core.support.spring.vaildator.ValidatorType;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * @author unicorn
 *运营平台接口
 */
@Controller
@RequestMapping("/manager/coupon/ploy/plat")
public class CouponPloyPlatController {

	@Autowired
	private CouponPloyManager couponPloyManager;
	
	@Autowired
	private AreaManager areaManager;
	
	/***
	 * 运营管理平台
	 * 审核活动
	 * @author unicorn
	 * @param operator
	 * @return
	 */
	@ParamValidators({
		@ParamValidator(param="id",paramName="活动ID",vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value="/audit.htm",method=RequestMethod.POST)
	public ModelAndView auditMerchant(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		boolean auditPass = HttpParameterParser.getBoolean("auditPass");
		String desc = HttpParameterParser.getString("desc");
		if(desc == null) {
			desc = "";
		}
		CouponPloy couponPloy = couponPloyManager.get(id);
		try {
			couponPloyManager.auditPloy(couponPloy, auditPass, desc, operator);
			return new ModelAndView(new JsonView(true, "电子券活动审核操作成功"));
		} catch (BusinessException e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false,"电子券活动审核操作失败"));
		}
	}
	/***
	 *查看活动详细
	 * @param pagination
	 * @return
	 */
	@ParamValidators({
		@ParamValidator(param="id",paramName="活动ID",vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value="/detail.htm",method=RequestMethod.GET)
	public ModelAndView getPloyDetail(@ObjectConvertAnno Pagination pagination) {
		long id = HttpParameterParser.getLongValue("id");
		CouponPloy couponPloy = couponPloyManager.get(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("couponPloy", couponPloy);
		return new ModelAndView(new JsonView("couponPloy",result));
	}
	
	/**
	 * 运营管理平台---查询审核列表
	 * @return
	 */
	@RequestMapping(value="/list.htm",method=RequestMethod.GET)
	public ModelAndView listCouponPloy(@ObjectConvertAnno Pagination pagination) {
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params = constParams(params);
			List<CouponPloy> couponPloys = couponPloyManager.find(params, pagination);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("couponPloies", couponPloys);
			result.put("pagination", pagination);
			ModelAndView model = new ModelAndView(new JsonView("couponPloies",result));
			 
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false,"查询电子券活动列表失败"));
		}
	}
	
	private Map<String,Object> constParams(Map<String,Object> params) {
		Date createStartDate = HttpParameterParser.getDate("createStartDate");
		Date createEndDate = HttpParameterParser.getDate("createEndDate");
		int[] couponPloyStatus = HttpParameterParser.getIntArray("couponPloyStatus");
		String merchantName = HttpParameterParser.getString("merchantName");
		String merchantCode = HttpParameterParser.getString("merchantCode");
		Long[] cityIds = HttpParameterParser.getLongWrapperArray("cityIds");
		int couponType = HttpParameterParser.getIntValue("couponType");
		if(couponPloyStatus != null) {
			List<CouponPloyStatus> list = new ArrayList<CouponPloy.CouponPloyStatus>();
			for(int status : couponPloyStatus) {
				list.add(CouponPloyStatus.get(status));
			}
			params.put("couponPloyStatus", list);
		}
		if(!StringUtils.isNullOrEmpty(merchantName)) {
			params.put("merchantName", "%"+merchantName+"%");
		}
		
		if(!StringUtils.isNullOrEmpty(merchantCode)) {
			params.put("code", merchantCode);
		}
		if(couponType != 0) {
			params.put("couponType", CouponType.get(couponType));
		}
		if(cityIds != null&&cityIds.length>0) {
			List<Area> areas = new ArrayList<Area>();
			for(Long id : cityIds) {
				Area area = areaManager.get(id);
				if(area.getAreaType()== AreaType.CITY) {
					areas.addAll(area.getChildrens());
				}
			}
			params.put("areas",areas);
		}
		if(createStartDate != null){
			params.put("createStartDate",  DateUtils.getEarlyInTheDay(createStartDate));
		}
		if(createEndDate != null){
			params.put("createEndDate",  DateUtils.getLateInTheDay(createEndDate));
		}
		return params;
	}
	
	
	
}
