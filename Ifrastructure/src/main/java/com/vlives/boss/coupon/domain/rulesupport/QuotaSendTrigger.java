/**
 * @(#)Quota.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain.rulesupport;

import java.math.BigDecimal;

/**
 * 满额发送电子券触发器
 * @author  jianguo.xu
 * @version 1.0,2011-9-11
 */
public class QuotaSendTrigger extends SendTrigger{
	
	private final BigDecimal consumMoney;

	protected QuotaSendTrigger(BigDecimal consumMoney) {
		this.consumMoney = consumMoney;
	}

	public BigDecimal getConsumMoney() {
		return consumMoney;
	}
	
	
}
