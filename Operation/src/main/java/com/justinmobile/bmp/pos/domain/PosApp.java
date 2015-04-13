package com.justinmobile.bmp.pos.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "POS_APP")
public class PosApp extends AbstractEntity{

	private static final long serialVersionUID = -1898632371L;
	
	/**状态值：0-生效,1-停用待审核,2-停用 */
	public static final int STATUS_VALID = 0;
	public static final int STATUS_DISABLED_AUD = 1;
	public static final int STATUS_DISABLED = 2;
	
	/**应用属性 0-主应用，1-子应用 */
	public static final int PROPERTY_MAIN = 0;
	public static final int PROPERTY_SUB = 1;
	
	/** 应用位置(同界面应用类别)	0：会生活,1：收单 */
	public static final int LOCATION_VLIVES = 0;
	public static final int LOCATION_RECEIPT = 1;
	
	/** 应用类别数 */
	public static final int TOTALLOCATIONS = 2; 
	
	private Long id;

	/** 应用配置集合	 */
	private Set<MerchantAppConfig> cofigSet = new HashSet<MerchantAppConfig>();
	
	/** 应用版本集合	 */
	private Set<PosAppVersion> versionSet = new HashSet<PosAppVersion>();
	
	private Set<City> citySet = new HashSet<City>();
	
	/** 应用编号	 */
	private String code;

	/** 应用位置	0：会生活,1：收单 */
	private int location;

	/** 应用简称	 */
	private String name;

	/** 应用描述	 */
	private String comments;
	
	/**应用属性 0-主应用，1-子应用 */
	private int property;
	
	/** 0-生效,1-停用待审核,2-停用 */
	private int status;

	/** 创建时间	 */
	private Calendar createTime;

	/** 创建人 */
	private String createOper;

	/** 停用时间	 */
	private Calendar stopTime;

	/** 停用人	 */
	private String stopOper;

	/** 停用审核时间	 */
	private Calendar auditStopTime;

	/** 停用审核人	 */
	private String auditStopOper;

	/** 审核意见 */
	private String auditInfo;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_POS_APP") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name="CREATE_TIME")
	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	@Column(name="CREATE_OPER")
	public String getCreateOper() {
		return createOper;
	}

	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}

	@Column(name="STOP_TIME")
	public Calendar getStopTime() {
		return stopTime;
	}

	public void setStopTime(Calendar stopTime) {
		this.stopTime = stopTime;
	}

	@Column(name="STOP_OPER")
	public String getStopOper() {
		return stopOper;
	}

	public void setStopOper(String stopOper) {
		this.stopOper = stopOper;
	}

	@Column(name="AUDIT_STOP_TIME")
	public Calendar getAuditStopTime() {
		return auditStopTime;
	}

	public void setAuditStopTime(Calendar auditStopTime) {
		this.auditStopTime = auditStopTime;
	}

	@Column(name="AUDIT_STOP_OPER")
	public String getAuditStopOper() {
		return auditStopOper;
	}

	public void setAuditStopOper(String auditStopOper) {
		this.auditStopOper = auditStopOper;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "posApp")
	@Cascade(value = {CascadeType.ALL})
	public Set<MerchantAppConfig> getCofigSet() {
		return cofigSet;
	}

	public void setCofigSet(Set<MerchantAppConfig> cofigSet) {
		this.cofigSet = cofigSet;
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "posApp")
	@Cascade(value = {CascadeType.ALL})
	public Set<PosAppVersion> getVersionSet() {
		return versionSet;
	}

	public void setVersionSet(Set<PosAppVersion> versionSet) {
		this.versionSet = versionSet;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CITY_APP", joinColumns = { @JoinColumn(name = "POS_APP_ID") }, inverseJoinColumns = { @JoinColumn(name = "CITY_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<City> getCitySet() {
		return citySet;
	}

	public void setCitySet(Set<City> citySet) {
		this.citySet = citySet;
	}

	@Column(name="AUDIT_INFO")
	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	@Transient
	public boolean isValid(){
		return this.status == STATUS_VALID;
	}

	public int getProperty() {
		return property;
	}

	public void setProperty(int property) {
		this.property = property;
	}
		
}