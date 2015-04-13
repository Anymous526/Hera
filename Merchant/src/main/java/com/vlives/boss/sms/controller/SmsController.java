/**
 * @(#)SmsController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.boss.sms.domain.SmsAccountDetail;
import com.vlives.boss.sms.domain.SmsAccountDetail.Type;
import com.vlives.boss.sms.manager.MerchantSmsAccountManager;
import com.vlives.boss.sms.manager.SmsAccountDetailManager;
import com.vlives.boss.sms.manager.SmsSendManager;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.vaildator.ParamValidator;
import com.vlives.core.support.spring.vaildator.ParamValidators;
import com.vlives.core.support.spring.vaildator.ValidatorType;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.AcceptHashMap;
import com.vlives.util.DateUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-6-13
 */
@Controller
@RequestMapping("/manager/platform/sms")
public class SmsController {
	@Autowired
	private SmsAccountDetailManager smsAccountDetailManager;
	@Autowired
	private MerchantManager merchantManager;
	@Autowired
	private SalePloyManager salePloyManager;
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private SmsSendManager smsSendManager;

	@Autowired
	private MerchantSmsAccountManager merchantSmsAccountManager;

	@ParamValidators({
			@ParamValidator(param = "merchantCode", paramName = "商户编号", vaildatorTypes = ValidatorType.REQUIRED),
			@ParamValidator(param = "count", paramName = "短信数量", vaildatorTypes = ValidatorType.REQUIRED, min = 1, max = 99999),
			@ParamValidator(param = "type", paramName = "赠送类型", vaildatorTypes = ValidatorType.REQUIRED, min = 1, max = 2),
			@ParamValidator(param = "description", paramName = "描述信息", length = { 0, 50 }) })
	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_ADD_SMS)
	public ModelAndView addSmsFromManager(@ObjectConvertAnno Operator operator) {
		String merchantCode = HttpParameterParser.getString("merchantCode");
		int count = HttpParameterParser.getIntValue("count");
		int typeInt = HttpParameterParser.getIntValue("type");
		String description = HttpParameterParser.getString("description");
		Merchant merchant = merchantManager.getByCode(merchantCode);
		Type type = Type.get(typeInt);
		try {
			merchantSmsAccountManager.addSmsCount(merchant, count, type, description, operator);
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
		return new ModelAndView(new JsonView(true, "添加成功"));
	}

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public ModelAndView search(@ObjectConvertAnno Operator operator, @ObjectConvertAnno Pagination pagination) {
		List<SmsAccountDetail> smsAccountDetails = smsAccountDetailManager.find(getParams(), pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("smsAccountDetails", smsAccountDetails);
		model.put("pagination", pagination);
		return new ModelAndView(new JsonView("listSmsAccountDetail", model));
	}

	private Map<String, Object> getParams() {
		String merchantCode = HttpParameterParser.getString("merchantCode");
		Date startDate = HttpParameterParser.getDate("startDate");
		startDate = startDate == null ? null : DateUtils.getEarlyInTheDay(startDate);

		Date endDate = HttpParameterParser.getDate("endDate");
		endDate = endDate == null ? null : DateUtils.getLateInTheDay(endDate);
		AcceptHashMap<String, Object> params = AcceptHashMap.newInstance();
		params.acceptIf("merchantCode", merchantCode, merchantCode != null)
				.acceptIf("startDate", startDate, startDate != null).acceptIf("endDate", endDate, endDate != null);
		return params;
	}

	/**
	 * 发送营销活动类型是创建会员发送获得类型
	 * 
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value = "/sendsms_by_createmember.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_REDUCE_SMS)
	public ModelAndView sendSmsByCreatemember(@ObjectConvertAnno Operator operator) {
		Long memberId = HttpParameterParser.getLong("memberId");
		Member member = memberManager.get(memberId);
		salePloyManager.sendSmsByCreateMember(member, operator);
		return new ModelAndView(new JsonView(true, "已发送"));
	}

	/**
	 * 发送营销活动是单次交易满额的交易类型
	 * 
	 * @author jianguo.xu
	 * @param operator
	 * @return
	 */
	@RequestMapping(value = "/sendsms_by_trade.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_REDUCE_SMS)
	public ModelAndView sendSmsByTrade(@ObjectConvertAnno Operator operator) {
		Long tradeDetailId = HttpParameterParser.getLong("tradeDetailId");
		TradeDetail tradeDetail = tradeDetailManager.get(tradeDetailId);
		salePloyManager.sendSmsByTrade(tradeDetail, operator);
		return new ModelAndView(new JsonView(true, "已发送"));
	}
    
	/**
	 * 发送麦讯通短信
	 * 可以上行回复
	 * @param operator
	 * @return
	 */
	@RequestMapping(value = "/send_mxtong_sms", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_REDUCE_SMS)
	public ModelAndView sendMxtongSms(@ObjectConvertAnno Operator operator) {
		String mobile = HttpParameterParser.getString("mobile");
		String content = HttpParameterParser.getString("content");
		smsSendManager.sendTimelySMSByMxtong(mobile, content);
		return new ModelAndView(new JsonView(true, "已发送"));
	}
	
	/**
	 * 发送系统短信
	 * @param operator
	 * @return
	 */
	@RequestMapping(value = "/send_sys_sms", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_REDUCE_SMS)
	public ModelAndView sendSysSms(@ObjectConvertAnno Operator operator) {
		String mobile = HttpParameterParser.getString("mobile");
		String content = HttpParameterParser.getString("content");
		smsSendManager.sendTimelySMS(mobile, content);
		return new ModelAndView(new JsonView(true, "已发送"));
	}

	/**
	 * 查询短信剩余余额
	 * 
	 * @param operator
	 * @return
	 */
	@RequestMapping(value = "/get_sms_count.htm", method = RequestMethod.GET)
	public ModelAndView getSmsCount(@ObjectConvertAnno Operator operator) {
		try {
			Long tradeDetailId = smsSendManager.getSmsCount();
			return new ModelAndView(new JsonView(this.setResult(true, "", tradeDetailId)));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(this.setResult(false, e.getMessage(), null)));
		}
	}

	private Map<String, Object> setResult(boolean success, String msg, Long smsCount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("success", success);
		params.put("msg", msg);
		params.put("smsCount", smsCount);
		return params;

	}
}
