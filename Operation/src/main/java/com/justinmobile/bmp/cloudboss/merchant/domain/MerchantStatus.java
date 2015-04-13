package com.justinmobile.bmp.cloudboss.merchant.domain;

public enum MerchantStatus {
	/** 待审核*/
	STATUS_WAIT_AUDIT(0, "待审核"),
	/** 有效状态 */
	STATUS_ACTIVE(1, "有效"),
	/** 审核失败状态 */
	STATUS_AUDIT_FAIL(2, "审核不通过"),
	/** 冻结状态 */
	STATUS_FREEZED(3, "冻结"),
	/** 注销状态 */
	STATUS_DISABLE(4, "注销");
	
	private int value;
	private String desc;

	MerchantStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}
	
	public static MerchantStatus get(int value) {
		for (MerchantStatus status : MerchantStatus.values()) {
			if (status.value == value) {
				return status;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}
}
