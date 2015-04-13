package com.justinmobile.bmp.pos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "POS_APP_VERSION_RECORD")
public class PosAppVersionRecord extends AbstractEntity{

	private static final long serialVersionUID = 2132475132L;


	private Long id;

	/** 所属商户名称*/
	private String merchantName;

	/** 所属商户编码*/
	private String merchantCode;

	/** pos编号*/
	private String posCode;

	/** pos终端序列号*/
	private String posSerialno;

	/** pos应用版本信息*/
	private String posAppversionInfo;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_POS_APP_VERSION_RECORD") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="MERCHANT_NAME")
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@Column(name="MERCHANT_CODE")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	@Column(name="POS_CODE")
	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	@Column(name="POS_SERIALNO")
	public String getPosSerialno() {
		return posSerialno;
	}

	public void setPosSerialno(String posSerialno) {
		this.posSerialno = posSerialno;
	}

	@Column(name="POS_APPVERSION_INFO")
	public String getPosAppversionInfo() {
		return posAppversionInfo;
	}

	public void setPosAppversionInfo(String posAppversionInfo) {
		this.posAppversionInfo = posAppversionInfo;
	}


}