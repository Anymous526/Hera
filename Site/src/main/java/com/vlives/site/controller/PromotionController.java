package com.vlives.site.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.site.entity.PromotionObject;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

@Controller
public class PromotionController {
	
	@Autowired
	CouponPloyManager couponPloyManager;

	@Autowired
	CouponManager couponManager;

	@RequestMapping(value = "/promotion/{promotionId}", method = RequestMethod.GET)
	public ModelAndView getPromotion(@PathVariable("promotionId") Long promotionId) {
		CouponPloy couponPloy = couponPloyManager.get(promotionId);
		PromotionObject promotion = new PromotionObject(couponPloy);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("promotion", promotion);

		String redirectUrl = "/promotion.jsp";
		return new ModelAndView(redirectUrl, map);
	}
	
	/**
	 * 所有优惠券列表
	 * @param pagination
	 * @return
	 */
//	@RequestMapping(value = "/promotions", method = RequestMethod.GET)
//	public ModelAndView getPromotions() {
//		String redirectUrl = "/promotion_list.jsp";
//		return new ModelAndView(redirectUrl);
//	}
//
//	@RequestMapping(value = "/promotions/ajax", method = RequestMethod.GET)
//	public ModelAndView getPromotionsAjax(@ObjectConvertAnno Pagination pagination) {
//		List<CouponPloy> ploys = couponPloyManager.getCouponPloys(pagination);
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("promotions", ploys);
//		map.put("pagination", pagination);
//
//		return new ModelAndView(new JsonView("promotion.promotions", map));
//	}

	/**
	 * 用户领用优惠券
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/ajax/promotion/{promotionId}", method = RequestMethod.POST)
	public ModelAndView applyCoupon(@PathVariable("promotionId") Long promotionId, @ObjectConvertAnno User user) {
		try {
			CouponPloy couponPloy = couponPloyManager.get(promotionId);
			couponManager.receiveCouponBySite(user, couponPloy);
			
			return new ModelAndView(new JsonView(true, String.valueOf(couponPloy.getSentCount()))); //Empty string for legacy.
		} catch (BusinessException ex) {
			return new ModelAndView(new JsonView(false, ex.getMessage()));
		}
	}
}
