package com.vlives.boss.user.domain;

import com.vlives.core.support.entity.EnumTypeInterface;


/**
 * 账户类型
 * @author  fyuan
 * @version 1.0,2011-7-5
 */
public enum AccountType implements EnumTypeInterface {
	/** 新浪账户 */
	TYPE_SINA(1, "新浪账户"),
	/** 腾讯账户 */
	TYPE_QQ(2, "腾讯账户");
	private int value;
	private String desc;

	AccountType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static AccountType get(int value) {
		for (AccountType type : AccountType.values()) {
			if (type.value == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}

}
