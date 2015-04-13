package com.justinmobile.bmp.pos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.util.Calendar;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "POS_APP_VERSION")
public class PosAppVersion extends AbstractEntity{

	private static final long serialVersionUID = -1862489909L;

	/**状态值： 0-初始,1-生效待审核,2-生效,3-停用待审核,4-停用,5-作废待审核,6-作废 */
	public static final int STATUS_INITIAL = 0;
	public static final int STATUS_VALID_AUD = 1;
	public static final int STATUS_VALID = 2;
	public static final int STATUS_DISABLED_AUD = 3;
	public static final int STATUS_DISABLED = 4;
	public static final int STATUS_INVALID_AUD = 5;
	public static final int STATUS_INVALID = 6;
	
	private Long id;

	private PosApp posApp;

	/** 版本号	 */
	private String version;

	/** 版本文件路径 */
	private String filePath;

	/** 版本大小 */
	private double fileSize;

	/** 版本说明	 */
	private String comments;
	
	/** 0-初始 ,1-生效待审核 ,2-生效,3-停用待审核,4-停用,5-作废待审核,6-作废 */
	private int status;

	/** 创建时间	 */
	private Calendar createTime;

	/** 创建人 */
	private String createOper;

	/** 发布时间 */
	private Calendar validTime;

	/** 发布人	 */
	private String validOper;

	/** 发布审核时间	 */
	private Calendar auditValidTime;

	/** 发布审核人 */
	private String auditValidOper;

	/** 停用时间	 */
	private Calendar stopTime;

	/** 停用人	 */
	private String stopOper;

	/** 停用审核时间	 */
	private Calendar auditStopTime;

	/** 停用审核人	 */
	private String auditStopOper;

	/** 作废时间	 */
	private Calendar invalidTime;

	/** 作废人	 */
	private String invalidOper;

	/** 作废审核时间	 */
	private Calendar auditInvalidTime;

	/** 作废审核人	 */
	private String auditInvalidOper;
	
	/** 审核意见 */
	private String auditInfo;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_POS_APP_VERSION") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name="FILE_PATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name="FILE_SIZE")
	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	@Column(name="VALID_TIME")
	public Calendar getValidTime() {
		return validTime;
	}

	public void setValidTime(Calendar validTime) {
		this.validTime = validTime;
	}

	@Column(name="VALID_OPER")
	public String getValidOper() {
		return validOper;
	}

	public void setValidOper(String validOper) {
		this.validOper = validOper;
	}

	@Column(name="AUDIT_VALID_TIME")
	public Calendar getAuditValidTime() {
		return auditValidTime;
	}

	public void setAuditValidTime(Calendar auditValidTime) {
		this.auditValidTime = auditValidTime;
	}

	@Column(name="AUDIT_VALID_OPER")
	public String getAuditValidOper() {
		return auditValidOper;
	}

	public void setAuditValidOper(String auditValidOper) {
		this.auditValidOper = auditValidOper;
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

	@Column(name="INVALID_TIME")
	public Calendar getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Calendar invalidTime) {
		this.invalidTime = invalidTime;
	}

	@Column(name="INVALID_OPER")
	public String getInvalidOper() {
		return invalidOper;
	}

	public void setInvalidOper(String invalidOper) {
		this.invalidOper = invalidOper;
	}

	@Column(name="AUDIT_INVALID_TIME")
	public Calendar getAuditInvalidTime() {
		return auditInvalidTime;
	}

	public void setAuditInvalidTime(Calendar auditInvalidTime) {
		this.auditInvalidTime = auditInvalidTime;
	}

	@Column(name="AUDIT_INVALID_OPER")
	public String getAuditInvalidOper() {
		return auditInvalidOper;
	}

	public void setAuditInvalidOper(String auditInvalidOper) {
		this.auditInvalidOper = auditInvalidOper;
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
	
	@Transient
	public boolean isDisabled(){
		return this.status == STATUS_DISABLED;
	}
	
	@Transient
	public boolean isInvalidAud(){
		return this.status == STATUS_INVALID_AUD;
	}
	
	@Transient
	public boolean isInvalid(){
		return this.status == STATUS_INVALID;
	}
	
	@Transient
	public boolean canComit(){
		return this.status == STATUS_INITIAL;
	}
	
	@Transient
	public boolean canDisable(){
		return this.status == STATUS_VALID;
	}
	
	@Transient
	public boolean canSetInvalid(){
		return this.status == STATUS_DISABLED;
	}
	
	@Transient
	public boolean canAudit(){
		return this.status == STATUS_VALID_AUD || this.status == STATUS_DISABLED_AUD || this.status == STATUS_INVALID_AUD;
	}
}