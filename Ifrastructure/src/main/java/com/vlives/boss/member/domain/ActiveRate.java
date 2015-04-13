/**
 * @(#)ActiveRate.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.member.domain;

import java.util.Date;

import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.util.DateUtils;

/**
 * 会员活跃度
 * @author  jianguo.xu
 * @version 1.0,2011-9-10
 */
public enum ActiveRate implements EnumTypeInterface {
	RECENT_NULL_CONSUME(5, 9999,"一次未消费"),
	 RECENT_ONE_WEEK(1, 7,"一个星期未消费"),
	 RECENT_ONE_MONTH(2,30, "一月未消费"),
	 RECENT_TWO_MONTH(3, 90,"三个月未消费"),
	 RECENT_HALF_YEAR(4, 183,"半年未消费");
	private int value;
	private int day;
	private String desc;

	ActiveRate(int value,int day, String desc) {
		this.value = value;
		this.day = day;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}
	/**
	 * 得到活跃度开始的日期，一个月为31天
	 * @author jianguo.xu
	 * @return
	 */
	public Date getStartDate() {
		return DateUtils.subtractDay(new Date(), day);
	}

	public static ActiveRate get(int value) {
		for (ActiveRate activeRate : ActiveRate.values()) {
			if (activeRate.value == value) {
				return activeRate;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}
	/**
	 * 根据传入的日期得到用户活跃度
	 * 如果没有对应的活跃度则返回 null
	 * @author jianguo.xu
	 * @param consumeDate
	 * @return
	 */
	public static ActiveRate getByDate(Date consumeDate) {
		if(consumeDate == null) return RECENT_NULL_CONSUME;
		long days = DateUtils.subtractDay(new Date(), consumeDate);
		ActiveRate[] activeRates = ActiveRate.values();
		for(int i =activeRates.length-1;i>=0;i--) {
			ActiveRate activeRate = activeRates[i];
			if(days>=activeRate.day) return activeRate;
		}
		return null;
	}
}
