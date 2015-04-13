package com.justinmobile.endpoint.mina.domain;

import java.util.Date;

import com.vlives.util.DateUtils;

/**券信息上送
 * @author ThinkPad7
 *
 */
public class Hs8583CouponUploadInfo extends Hs8583BaseDto {

	private String couponSerialNo;
	/** 终端交易日期 */
	private String terminalTransDate;
	/** 终端交易时间 */
	private String terminalTransTime;
	/** 终端流水号 */
	private String terminalSerial;
	
	
	public String getCouponSerialNo() {
		return couponSerialNo;
	}

	public void setCouponSerialNo(String couponSerialNo) {
		this.couponSerialNo = couponSerialNo;
	}

	public String getTerminalTransDate() {
		return terminalTransDate;
	}

	public void setTerminalTransDate(String terminalTransDate) {
		this.terminalTransDate = terminalTransDate;
	}
	
	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = String.format("%6s", terminalSerial).replace(" ", "0");
	}

	public String getTerminalTransTime() {
		return terminalTransTime;
	}

	public void setTerminalTransTime(String terminalTransTime) {
		this.terminalTransTime = terminalTransTime;
	}

	/**
     * 取得交易时间
     * @author MrXu
     * @return
     */
	public Date getTradeDate(){		
		 return  DateUtils.parserDate(this.terminalTransDate+this.terminalTransTime, "yyyyMMddHHmmss");		
	}
	
	
}
