package com.vlives.boss.border.domain;

import com.vlives.core.support.entity.EnumTypeInterface;


/**
 * 会员来源
 * @author  fyuan
 * @version 1.0,2011-7-5
 */
public enum BorderType implements EnumTypeInterface {
	/** 用户*/
	TYPE_USER(1, "用户公告"),
	/** 商户 */
	TYPE_MERCHANT(2, "商户公告"),
	/** 所有 */
	TYPE_ALL(3, "通用公告");
	private int value;
	private String desc;

	BorderType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static BorderType get(int value) {
		for (BorderType type : BorderType.values()) {
			if (type.value == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}

}
