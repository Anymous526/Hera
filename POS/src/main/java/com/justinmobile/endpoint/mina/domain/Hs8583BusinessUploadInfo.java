package com.justinmobile.endpoint.mina.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.util.DateUtils;

public class Hs8583BusinessUploadInfo extends Hs8583BaseDto {
	/** 手机号 */
	private String mobileNo;
	/** 金额 */
	private Long amount;
	/** 终端交易日期 */
	private String terminalTransDate;
	/** 终端交易时间 */
	private String terminalTransTime;
	/** 终端编号 */
	private String terminalNo;
	/** 终端流水号 */
	private String terminalSerial;
	/** 原终端编号 */
	private String originalTerminalNo;
	/** 原终端流水号 */
	private String originalSerial;
	/** 原批次号 */
	private String originalBatchNo;
	/** 银行卡号 */
	private String BankCardNo;
	/** 交易类型 */
	private Integer transType;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTerminalTransDate() {
		return terminalTransDate;
	}

	public void setTerminalTransDate(String terminalTransDate) {
		this.terminalTransDate = terminalTransDate;
	}

	public String getTerminalTransTime() {
		return terminalTransTime;
	}

	public void setTerminalTransTime(String terminalTransTime) {
		this.terminalTransTime = terminalTransTime;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = String.format("%6s", terminalSerial).replace(" ", "0");
	}

	public String getOriginalTerminalNo() {
		return originalTerminalNo;
	}

	public void setOriginalTerminalNo(String originalTerminalNo) {
		this.originalTerminalNo = originalTerminalNo;
	}

	public String getOriginalSerial() {
		return originalSerial;
	}

	public void setOriginalSerial(String originalSerial) {
		this.originalSerial = String.format("%6s", originalSerial).replace(" ", "0");
	}

	public String getOriginalBatchNo() {
		return originalBatchNo;
	}

	public void setOriginalBatchNo(String originalBatchNo) {
		this.originalBatchNo = String.format("%6s", originalBatchNo).replace(" ", "0");
	}

	public String getBankCardNo() {
		return BankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		BankCardNo = bankCardNo;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	/**
     * 取得交易时间
     * @author MrXu
     * @return
     */
	public Date getTradeDate(){		
		 return  DateUtils.parserDate(this.terminalTransDate+this.terminalTransTime, "yyyyMMddHHmmss");		
	}
	/**
	 * 获取交易金额
	 * @author MrXu
	 * @return
	 */
	public BigDecimal getTradeAmount(){
		return  new BigDecimal(this.amount.toString()).movePointLeft(2);
	}
	
	/**
	 * 获取交易类型
	 * @author MrXu
	 * @return
	 */
	public TradeOrder.Type  getEnumTradeType(){
		if (this.transType== 0) {
		  return TradeOrder.Type.TYPE_BRUSH;
		} else if(this.transType== 1) {
		  return TradeOrder.Type.TYPE_CASH;
		}
		  return null;
	}

	

	
	
	
}
