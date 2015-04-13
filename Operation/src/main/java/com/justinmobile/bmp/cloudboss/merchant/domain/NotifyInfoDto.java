package com.justinmobile.bmp.cloudboss.merchant.domain;

public class NotifyInfoDto {
	
	public static final String STATUS_ACTIVE = "0";
	
	public static final String STATUS_FREEZED = "2";
	
	public static final String STATUS_DELETE = "3";

	private String merchantCode;
	
	private String merchantName;
	
	private String mechantShortName;
	
	private String merchantType;
	
	private String branchCode;
	
	private String status;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMechantShortName() {
		return mechantShortName;
	}

	public void setMechantShortName(String mechantShortName) {
		this.mechantShortName = mechantShortName;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
