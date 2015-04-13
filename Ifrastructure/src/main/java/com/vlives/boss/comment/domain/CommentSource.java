package com.vlives.boss.comment.domain;

import com.vlives.core.support.entity.EnumTypeInterface;


/**
 * 评论来源
 * @version 1.0,2011-7-5
 */
public enum CommentSource implements EnumTypeInterface {
	/**短信*/
	SOURCE_SMS(1, "短信"),
	/**会生活网站 */
	SOURCE_SITE(2, "会生活网站"),
	/**系统自动*/
	SOURCE_SYSTEM(3, "系统自动");
	private int value;
	private String desc;

	CommentSource(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static CommentSource get(int value) {
		for (CommentSource source : CommentSource.values()) {
			if (source.value == value) {
				return source;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}

}
