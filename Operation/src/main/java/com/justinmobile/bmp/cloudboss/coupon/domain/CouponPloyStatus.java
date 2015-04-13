package com.justinmobile.bmp.cloudboss.coupon.domain;

public enum CouponPloyStatus {
	/** 待审核状态*/
	WAIT_AUDIT(0, "创建待审核"),
	/** 审核通过，活动进行中*/
	AUDIT_SUCCESS(1, "活动进行中"),
	/** 审核未通过*/
	AUDIT_FAIL(2, "审核未通过"),
	/** 发送失败 */
	PAUSE(3, "暂停"),
	/** 注销 */
	LOGOUT(4, "删除"),
	/** 活动结束 */
	COMPLETE(5, "活动结束");
	private int value;
	private String desc;

	CouponPloyStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static CouponPloyStatus get(int value) {
		for (CouponPloyStatus type : CouponPloyStatus.values()) {
			if (type.value == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}
		
}
