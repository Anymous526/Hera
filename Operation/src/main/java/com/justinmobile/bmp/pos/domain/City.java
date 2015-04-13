package com.justinmobile.bmp.pos.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.justinmobile.core.domain.AbstractEntity;
import com.justinmobile.security.domain.SysUser;

@Entity
@Table(name = "CITY")
public class City extends AbstractEntity{

	private static final long serialVersionUID = -3118518263782048242L;
	
	public static final int STATUS_OPEN = 1;
	
	public static final int STATUS_CLOSED = 0;

	private Long id;
	
	private String cityCode;
	
	private String cityName;
	
	private int status;

	private Set<PosApp> appSet = new HashSet<PosApp>();
	
	private Set<SysUser> userSet = new HashSet<SysUser>();
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_CITY") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="CITY_CODE")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name="CITY_NAME")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@ManyToMany
	@JoinTable(name = "CITY_APP", joinColumns = { @JoinColumn(name = "CITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "POS_APP_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<PosApp> getAppSet() {
		return appSet;
	}

	public void setAppSet(Set<PosApp> appSet) {
		this.appSet = appSet;
	}
	
	@Transient
	public boolean isOpen(){
		return this.status == STATUS_OPEN;
	}

	@ManyToMany(mappedBy = "citySet", targetEntity = SysUser.class)
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysUser> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<SysUser> userSet) {
		this.userSet = userSet;
	}
	
}
