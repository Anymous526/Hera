/**
 * @(#)SmsController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sms.domain.MerchantSmsAccount;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

@Controller
@RequestMapping("/manager/sms")
public class MerchantSmsAccountController {

	@RequestMapping(value="/view.htm",method=RequestMethod.GET)
	public ModelAndView search(@ObjectConvertAnno Operator operator) {
		MerchantSmsAccount merchantSmsAccount = operator.getMerchant().getMerchantSmsAccount();
		return new ModelAndView("/manager/saleploy/sms_info.jsp","merchantSmsAccount",merchantSmsAccount);
	}
}
