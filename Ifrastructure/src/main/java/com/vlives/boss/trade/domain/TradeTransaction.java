package com.vlives.boss.trade.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.core.support.hibernate.EnumType;

@TypeDefs({ @TypeDef(name = "transReqType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.trade.domain.TradeTransaction$TradeReqType") }) })
@Entity
@Table(name = "TRADE_TRANSACTION")
public class TradeTransaction {

	private Long id;
	/** 手机号 */
	private String mobileNo;
	/** 金额 */
	private long amount;
	/** 终端交易日期 */
	private Date tradeDate;
	/** 终端编号 */
	private String posNo;
	/** 原批次号 */
	private Integer batchNo;
	/** 终端流水号 */
	private String tradeSerialNo;
	/** 原终端编号 */
	private String originalPosNo;
	/** 原终端流水号 */
	private String originalSerialNo;
	/** 原批次号 */
	private Integer originalBatchNo;
	/** 银行卡号 */
	private String BankCardNo;
	/** 交易类型 */
	private TradeReqType tradeReqType;
	/** 创建时间 */
	private Date createDate;
	/*** 响应码 */
	private String respCode;
	/*** 响应信息 */
	private String respDesc;

	public static enum TradeReqType {
		/** 刷卡交易 */
		TYPE_BRUSH(0, "刷卡交易"),
		/** 现金交易 */
		TYPE_CASH(1, "现金交易"),
		/** 退货交易 */
		CANCEL_TRADE(2, "退货交易");

		private int value;
		private String desc;

		TradeReqType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static TradeReqType get(int value) {
			for (TradeReqType type : TradeReqType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TRADE_DETAIL") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MOBILE_NO")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	@Column(name = "TRADE_DATE")
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "BATCH_NO")
	public Integer getBatchNo() {
		return batchNo;
	}

	@Column(name = "POS_NO")
	public String getPosNo() {
		return posNo;
	}

	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}

	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "TRADE_SERIAL_NO")
	public String getTradeSerialNo() {
		return tradeSerialNo;
	}

	public void setTradeSerialNo(String tradeSerialNo) {
		this.tradeSerialNo = tradeSerialNo;
	}

	@Column(name = "ORIGINAL_POS_NO")
	public String getOriginalPosNo() {
		return originalPosNo;
	}

	public void setOriginalPosNo(String originalPosNo) {
		this.originalPosNo = originalPosNo;
	}

	@Column(name = "ORIGINAL_SERIAL_NO")
	public String getOriginalSerialNo() {
		return originalSerialNo;
	}

	public void setOriginalSerialNo(String originalSerialNo) {
		this.originalSerialNo = originalSerialNo;
	}

	@Column(name = "ORIGINAL_BATCH_NO")
	public Integer getOriginalBatchNo() {
		return originalBatchNo;
	}

	public void setOriginalBatchNo(Integer originalBatchNo) {
		this.originalBatchNo = originalBatchNo;
	}

	@Column(name = "BANK_CARD_NO")
	public String getBankCardNo() {
		return BankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		BankCardNo = bankCardNo;
	}

	@Type(type = "transReqType")
	@Column(name = "TRADE_REQ_TYPE")
	public TradeReqType getTradeReqType() {
		return tradeReqType;
	}

	public void setTradeReqType(TradeReqType tradeReqType) {
		this.tradeReqType = tradeReqType;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "RESP_CODE")
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@Column(name = "RESP_DESC")
	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

}
