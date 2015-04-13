package com.justinmobile.security.domain;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "SYS_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "USERNAME" }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser extends AbstractEntity {

	private static final long serialVersionUID = -7556275242283581365L;

	/** 主键 */
	private Long id;
	
	/** 登录名 */
	private String userName;
	
	/** 密码 */
	private String password;
	
	/** 真实姓名 */
	private String realName;
	
	/** 手机 */
	private String mobile;
	
	/** 电子邮件 */
	private String email;
	
	/** 状态:1,有效;0,无效 */
	private Integer status;
	
	/** 最后一次登录时间 */
	private Calendar latestLogin;
	
	/** 省代码 */
	private String provinceCode;
	
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	
	/** 密码加密随机数 */
	private String salt;

	private Set<City> citySet = new HashSet<City>();
	
	public enum USERSTATUS {
		ENABLED(1), DISABLED(0);
		
		private Integer val;

		USERSTATUS(Integer inVal) {
			this.val = inVal;
		}

		public Integer getValue() {
			return this.val;
		}
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SYS_USER") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Length(max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	 
	@Length(max = 32)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public static String[] propertiesToArray() {
		return new String[]{"id", "username", "password", "name", "mobile", "email"};
	}

	@Transient
	public boolean isEnable() {
		if (this.status == 1) {
			return true;
		} else {
			return false;
		}
	}

	@NotEmpty
	@Length(max = 32)
	@Column(unique = true)
	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Calendar getLatestLogin() {
		return latestLogin;
	}

	public void setLatestLogin(Calendar latestLogin) {
		this.latestLogin = latestLogin;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_CITY", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "CIT_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<City> getCitySet() {
		return citySet;
	}

	public void setCitySet(Set<City> citySet) {
		this.citySet = citySet;
	}

}
