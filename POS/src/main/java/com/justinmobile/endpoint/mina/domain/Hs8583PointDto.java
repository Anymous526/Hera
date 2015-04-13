package com.justinmobile.endpoint.mina.domain;

public class Hs8583PointDto extends Hs8583BaseDto {

	
	/**
	 *交易时间 
	 */
	private String transTime;
	/**
	 * 交易金额
	 */
	private String transAmount;
	
	/**
	 *交易 折扣
	 */
	private String transScale;
	/**
	 *交易积分 
	 */
	private String transPoint;
	/**
	 * 受理终端
	 */
	private String acceptPos;
	
	
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransScale() {
		return transScale;
	}
	public void setTransScale(String transScale) {
		this.transScale = transScale;
	}
	public String getTransPoint() {
		return transPoint;
	}
	public void setTransPoint(String transPoint) {
		this.transPoint = transPoint;
	}
	public String getAcceptPos() {
		return acceptPos;
	}
	public void setAcceptPos(String acceptPos) {
		this.acceptPos = acceptPos;
	}
	
}
