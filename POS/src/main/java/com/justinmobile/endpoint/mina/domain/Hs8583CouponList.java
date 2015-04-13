package com.justinmobile.endpoint.mina.domain;

/**
 * @author ThinkPad7 优惠券
 */
public class Hs8583CouponList extends Hs8583BaseDto {

	/**
	 * 券活动编号1
	 */
	private Long couponPloySerialNo;

	/**
	 * 券活动名称
	 */
	private String couponName;

	/**
	 * 券数量
	 */
	private Long couponNum;

	public Hs8583CouponList() {
		super();
	}

	public Hs8583CouponList(Long couponPloySerialNo, String couponName, Long couponNum) {
		super();
		this.couponPloySerialNo = couponPloySerialNo;
		this.couponName = couponName;
		this.couponNum = couponNum;
	}

	public Long getCouponPloySerialNo() {
		return couponPloySerialNo;
	}

	public void setCouponPloySerialNo(Long couponPloySerialNo) {
		this.couponPloySerialNo = couponPloySerialNo;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Long getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Long couponNum) {
		this.couponNum = couponNum;
	}

}
