/**
 * @(#)SendTrigger.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain.rulesupport;

import java.math.BigDecimal;

/**
 * 短信下发触发器
 * @author  jianguo.xu
 * @version 1.0,2011-9-11
 */
public abstract class SendTrigger {
	public static final RegisterSendTrigger createRegisterSendTrigger() {
		return new RegisterSendTrigger();
	} 
	
	public static final QuotaSendTrigger createQuotaSendTrigger(BigDecimal consumMoney) {
		return new QuotaSendTrigger(consumMoney);
	} 
}
