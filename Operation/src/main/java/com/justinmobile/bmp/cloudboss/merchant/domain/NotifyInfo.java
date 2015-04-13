package com.justinmobile.bmp.cloudboss.merchant.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "NOTIFY_INFO")
public class NotifyInfo extends AbstractEntity{

	private static final long serialVersionUID = 2143112927216571846L;

	public static final int NOTIFY_SUCCESS = 1;
	
	public static final int NOTIFY_FAULT = 0;
	
	private Long id;
	
	private String merchantCode;
	
	private int notifySuccess;
	
	private Date notifyTime;

	private String remarks;
	
	private int notifyCount;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_NOTIFY_INFO") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="MERCHANT_CODE")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="NOTIFY_SUCCESS")
	public int getNotifySuccess() {
		return notifySuccess;
	}

	public void setNotifySuccess(int notifySuccess) {
		this.notifySuccess = notifySuccess;
	}

	@Column(name="NOTIFY_COUNT")
	public int getNotifyCount() {
		return notifyCount;
	}

	public void setNotifyCount(int notifyCount) {
		this.notifyCount = notifyCount;
	}

	@Column(name="NOTIFY_TIME")
	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	

}
