package com.vlives.boss.user.domain;

import com.vlives.core.support.entity.EnumTypeInterface;


/**
 * 证件类型
 * @author  unicorn
 * @version 1.0,2011-6-2
 */
public enum CardType implements EnumTypeInterface {
	/** 身份证 */
	CARDTYPE_IDENTITY(1, "身份证"),
	/** 驾驶证 */
	CARDTYPE_DRIVER(2, "驾驶证");
	private int value;
	private String desc;

	CardType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static CardType get(int value) {
		for (CardType cardType : CardType.values()) {
			if (cardType.value == value) {
				return cardType;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}

}
