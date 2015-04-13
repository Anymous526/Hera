/**
 * @(#)CouponPloyType.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

import com.vlives.boss.coupon.domain.CouponType;
import com.vlives.core.support.entity.EnumTypeInterface;

/**
 * 电子券类型
 * @author  jianguo.xu
 * @version 1.0,2011-9-6
 */
public enum CouponType implements EnumTypeInterface {
	CASH_COUPON(1, "代金券"),
	DISCOUNT_COUPON(2, "折扣券"),
	conversion_COUPON(3, "兑换券"),
	BARGAIN_PRICE_COUPON(4, "特价券");
	private int value;
	private String desc;

	CouponType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static CouponType get(int value) {
		for (CouponType type : CouponType.values()) {
			if (type.value == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}
}
