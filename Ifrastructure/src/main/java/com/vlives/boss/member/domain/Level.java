/**
 * @(#)Level.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.member.domain;

import com.vlives.core.support.entity.EnumTypeInterface;


/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-1
 */
public enum Level implements EnumTypeInterface {
	/** 普通会员*/
	GENERAL(1, "普通会员"),
	/** 银卡会员*/
	SILVER(2, "银卡会员"),
	/** 金卡会员 */
	GOLD(3, "金卡会员"),
	/*** 钻石会员 */
	DIAMOND(4, "钻石会员");

	private int value;
	private String desc;

	Level(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static Level get(int value) {
		for (Level status : Level.values()) {
			if (status.value == value) {
				return status;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}

	public static Level get(String desc) {
		for (Level level : Level.values()) {
			if (level.desc.equals(desc)) {
				return level;
			}
		}
		throw new IllegalArgumentException("argument error: " + desc);
	}
	

}
