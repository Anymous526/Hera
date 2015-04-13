/**
 * @(#)CouponPloyType.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

import com.vlives.boss.coupon.domain.CouponPloyType;
import com.vlives.core.support.entity.EnumTypeInterface;

/**
 * 电子券活动类型
 * @author  jianguo.xu
 * @version 1.0,2011-9-6
 */
public enum CouponPloyType implements EnumTypeInterface {
	/**
	 * 群发优惠券
	 */
	GROUP_COUPONS(1,true, "群发优惠券"),
	/**
	 * 满额打折优惠
	 */
	QUOTA_DISCOUNT(2,false, "满额打折优惠"),
	/**
	 * 注册好礼相送
	 */
	REGISTER_GIVE_GIFTS(3,false, "注册好礼相送"),
	/**
	 * 节日生日推广
	 */
	BIRTHDAY_PROMOTION(4, true,"节日生日推广");
	private final int value;
	/**
	 * 是否是群发短信
	 */
	private final boolean groupSendType;
	private final String desc;

	CouponPloyType(int value, boolean groupSendType,String desc) {
		this.value = value;
		this.groupSendType = groupSendType;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}
	
	public boolean isGroupSendType() {
		return groupSendType;
	}

	public static CouponPloyType get(int value) {
		for (CouponPloyType type : CouponPloyType.values()) {
			if (type.value == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}
}
