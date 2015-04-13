package com.vlives.boss.trade.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="POS_TRADE_LOG")
public class PosTradeLog {
	
	private Long id;
	/***商户编号*/
	private String merchantNo;
	/***POS编号*/
	private String posNo;
	/***手机号*/
	private String mobileNo;
	/***请求数据*/
	private String requestData;
	/***响应码*/
	private String respCode;
	/***响应信息*/
	private String respDesc;
	/***创建时间*/
	private Date createDate;
	/***父pos请求接口*/
	private PosTradeLog parentPosTradeLog;
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_POS_TRADE_LOG") })
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="MERCHANT_NO")
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	@Column(name="POS_NO")
	public String getPosNo() {
		return posNo;
	}
	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}
	
	@Column(name="MOBILE_NO")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="REQUEST_DATA")
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	@Column(name="RESP_CODE")
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	@Column(name="RESP_DESC")
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public PosTradeLog getParentPosTradeLog() {
		return parentPosTradeLog;
	}
	public void setParentPosTradeLog(PosTradeLog parentPosTradeLog) {
		this.parentPosTradeLog = parentPosTradeLog;
	}
	
	
	

}
