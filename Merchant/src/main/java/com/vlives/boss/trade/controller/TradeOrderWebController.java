/**
 * @(#)TradeOrderController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.trade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.user.domain.User;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-7-10
 */
@Controller
public class TradeOrderWebController {
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private MerchantManager merchantManager;
	/**
	 * 得到当前用户未评论的订单数
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/user/un_comment_count.htm",method=RequestMethod.GET)
	public ModelAndView getCurrentUserUnCommentOrderCount(@ObjectConvertAnno User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		long count = tradeDetailManager.count(params);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("count", count);
		return new ModelAndView(new JsonView(model));
	}
	
	/**
	 * 查询会员的消费记录
	 */
	@RequestMapping(value="/user/tradeOrder/list.htm",method=RequestMethod.GET)
	public ModelAndView findOrderByUser(@ObjectConvertAnno User user,@ObjectConvertAnno Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);	
		params.put("consumeTrade", true);
		List<TradeDetail>  tradeDetails=tradeDetailManager.find(params, pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tradeDetails", tradeDetails);
		model.put("pagination", pagination);
		model.put("success",CollectionUtils.isEmpty(tradeDetails)?false:true);	
		return new ModelAndView(new JsonView("user_consume_order",model));
	}
	
	/**
	 * 查询会员的消费记录
	 * 只取一条
	 */
	@RequestMapping(value="/user/tradeOrder/newest_info.htm",method=RequestMethod.GET)
	public ModelAndView findunComment(@ObjectConvertAnno User user) {
		Long merchantId=HttpParameterParser.getLong("merchantId");
	
		Merchant merchant=merchantManager.get(merchantId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);	
		params.put("merchant", merchant);	
		Pagination pagination=new Pagination();
		pagination.setPageSize(1);
		List<TradeDetail>  tradeDetails=tradeDetailManager.findNoCommentTradeDetails(params, pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tradeDetails", tradeDetails);
		model.put("success", CollectionUtils.isEmpty(tradeDetails)?false:true);	
		return new ModelAndView(new JsonView("user_consume_order",model));
	}
	
	
	
}
