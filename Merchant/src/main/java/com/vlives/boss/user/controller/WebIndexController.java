/**
 * @(#)WebIndexController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.illegal.domain.IllegalWord;
import com.vlives.boss.illegal.manager.IllegalWordManager;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.filter.cache.Cache;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-7-9
 */
@Controller
public class WebIndexController {
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private MerchantCommentManager merchantCommentManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MerchantManager merchantManager;
	@Autowired
	private IllegalWordManager illegalWordManager;

	/**
	 * 得到网站统计信息
	 * @author jianguo.xu
	 * @return
	 */
	@Cache(ignoreParams=true)
	@RequestMapping("/web_stat.htm")
	public ModelAndView webStatistic(HttpServletRequest request) {
		 
		long consumeCount = tradeDetailManager.count(new HashMap<String, Object>());
		long commentCount = merchantCommentManager.count(new HashMap<String, Object>());
		long memberCount = memberManager.countTotalMemberCount();
		long merchantCount = merchantManager.countMerchant();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("consumeCount", consumeCount);
		model.put("commentCount", commentCount);
		model.put("memberCount", memberCount);
		model.put("merchantCount", merchantCount);
		return new ModelAndView(new JsonView(model));
	}
	
 
	@Cache(ignoreParams=true)
	@RequestMapping("/illegal/findAll.htm")
	public ModelAndView getIllegalWords() {
		List<IllegalWord> illegalWords = illegalWordManager.findAll();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("illegalWords", illegalWords);
		return new ModelAndView(new JsonView("illegalWords",result));
	}
}
