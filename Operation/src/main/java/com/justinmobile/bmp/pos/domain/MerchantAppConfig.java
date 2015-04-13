package com.justinmobile.bmp.pos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.util.Calendar;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "MERCHANT_APP_CONFIG")
public class MerchantAppConfig extends AbstractEntity{

	private static final long serialVersionUID = -831052869L;

	private Long id;
	
	/** 商户编号	 */
	private String merchantCode;

	private PosApp posApp;

	/** 配置时间	 */
	private Calendar configTime;

	/** 配置操作人	 */
	private String configOper;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_APP_CONFIG") })
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

	@Column(name="CONFIG_TIME")
	public Calendar getConfigTime() {
		return configTime;
	}

	public void setConfigTime(Calendar configTime) {
		this.configTime = configTime;
	}

	@Column(name="CONFIG_OPER")
	public String getConfigOper() {
		return configOper;
	}

	public void setConfigOper(String configOper) {
		this.configOper = configOper;
	}

	@ManyToOne
	@JoinColumn(name = "POS_APP_ID")
	@Cascade(value = { CascadeType.PERSIST, CascadeType.MERGE })
	public PosApp getPosApp() {
		return posApp;
	}

	public void setPosApp(PosApp posApp) {
		this.posApp = posApp;
	}

}