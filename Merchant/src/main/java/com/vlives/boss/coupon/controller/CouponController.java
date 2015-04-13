package com.vlives.boss.coupon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.Coupon.CouponStatus;
import com.vlives.boss.coupon.domain.CouponType;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;



/**
 * @author unicorn
 *
 */
@Controller
@RequestMapping("/manager/coupon")
public class CouponController {
	
	@Autowired
	private CouponManager couponManager;
	
	@RequestMapping(value="/coupon/list.htm")
	public ModelAndView listCouponPloy(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination) {
		ModelAndView model = new ModelAndView("/manager/couponploy/coupon_list.jsp");
		Map<String,Object> params = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		Integer couponStatus = HttpParameterParser.getInteger("couponStatus");
		List<CouponStatus> status = new ArrayList<CouponStatus>();
		if(couponStatus != null) {
			status.add(CouponStatus.get(couponStatus));
		} else {
			status.add(CouponStatus.UN_CONSUME);
			status.add(CouponStatus.EXPIRED);
			status.add(CouponStatus.CONSUMED);
		}
		params.put("couponStatus", status);
		params = constParams(params);
		Merchant merchant = operator.getMerchant();
		if(merchant.isExistParent()){
			params.put("applyMerchant", merchant);
		} else {
			params.put("merchant",merchant);
		}
		List<Coupon> list = couponManager.find(params, pagination);
		result.put("list", list);
		result.put("pagination", pagination);
		model.addAllObjects(result);
		return model;
	}
	
	private Map<String,Object> constParams(Map<String,Object> map) {
		String code = HttpParameterParser.getString("code");
		String title = HttpParameterParser.getString("title");
		String mobile = HttpParameterParser.getString("mobile");
		long couponployid = HttpParameterParser.getLongValue("couponployid");
		int couponType = HttpParameterParser.getIntValue("couponType");
		
		if(!StringUtils.isNullOrEmpty(code)) {
			map.put("code", code);
		}
		if(!StringUtils.isNullOrEmpty(title)){
			map.put("title", title);
		}
		if(!StringUtils.isNullOrEmpty(mobile)) {
			map.put("mobile", mobile);
		}
		if(couponployid != 0 ) {
			map.put("couponployid", couponployid);
		}
		if(couponType != 0) {
			map.put("couponType", CouponType.get(couponType));
		}
		return map;
	}
}
