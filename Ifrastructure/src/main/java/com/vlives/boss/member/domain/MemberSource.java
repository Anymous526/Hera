package com.vlives.boss.member.domain;

import com.vlives.core.support.entity.EnumTypeInterface;


/**
 * 会员来源
 * @author  fyuan
 * @version 1.0,2011-7-5
 */
public enum MemberSource implements EnumTypeInterface {
	/** 用户来源pos机 */
	SOURCE_POS(1, "POS机"),
	/** 运营管理平台 */
	SOURCE_PLATFORM(2, "运营管理平台"),
	/** 会生活网站 */
	SOURCE_WEB_SITE(3, "会生活网站"),
	/** 短信*/
	SOURCE_SMS(4, "短信"),
	/**商户管理平台*/
	SOURCE_CLOUDBOSS(5,"商户管理平台");
	private int value;
	private String desc;

	MemberSource(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static MemberSource get(int value) {
		for (MemberSource source : MemberSource.values()) {
			if (source.value == value) {
				return source;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}

}
