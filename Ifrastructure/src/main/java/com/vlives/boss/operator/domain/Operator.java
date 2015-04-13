/*
 * @(#)Operator.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.operator.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.security.domain.Role;
import com.vlives.boss.security.domain.Role.RoleGroup;
import com.vlives.core.security.Principal;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 管理员
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.operator.domain.Operator$Status") }),
	@TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.operator.domain.Operator$Type") })})
@Entity
@Table(name = "MANAGER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Operator extends BaseEntity implements Principal{
	/**
	 * 默认密码的明文
	 */
	public static final String DEFAULT_PASSWORD="111111";
	public static final Long SYS_OPERATOR_ID=1L;
	public static final Long SYS_POS_ID=2L;
	
	/***编号*/
	private Long id;
	/***商户*/
	private Merchant merchant;
	/***类型*/
	private Type type;
	/***手机*/
	private String mobile;
	/***名字*/
	private String name;
	/***密码*/
	private String password;
	/***状态*/
	private Status status;
	/***创建者*/
	private Operator creator;
	/***创建日期*/
	private Date  createDate;
	/**
	 * 最后登录日期
	 */
	private Date lastLoginDate;
	
	private Set<OperatorRole>  operatorRoles;
	
	private Set<OperatorStatusLog> operatorStatusLogs;
	
	public static enum Status implements EnumTypeInterface {
		/** 有效状态 */
		STATUS_ACTIVE(1, "有效"),
		/** 冻结状态 */
		STATUS_FREEZED(0, "冻结");
		
		private int value;
		private String desc;

		Status(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}
		
		public static Status get(int value) {
			for (Status status : Status.values()) {
				if (status.value == value) {
					return status;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}
	
	public static enum Type implements EnumTypeInterface {
		/** 系统管理员 */
		TYPE_SYSTEM(1, "系统管理员"),
		/** 商户管理员*/
		TYPE_MERCHANT(2, "商户管理员");
		
		private int value;
		private String desc;

		Type(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}
		
		public static Type get(int value) {
			for (Type type : Type.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MANAGER") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	@org.hibernate.annotations.Type(type="type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@org.hibernate.annotations.Type(type="status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	public Operator getCreator() {
		return creator;
	}

	public void setCreator(Operator creator) {
		this.creator = creator;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@OneToMany(mappedBy = "operator",fetch=FetchType.LAZY,  cascade = CascadeType.ALL)
	@Cascade(value={ org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<OperatorRole> getOperatorRoles() {
		return operatorRoles;
	}

	public void setOperatorRoles(Set<OperatorRole> operatorRoles) {
		this.operatorRoles = operatorRoles;
	}
	@Column(name="LAST_LOGIN_DATE")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	@OneToMany(mappedBy = "manager",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	public Set<OperatorStatusLog> getOperatorStatusLogs() {
		return operatorStatusLogs;
	}

	public void setOperatorStatusLogs(Set<OperatorStatusLog> operatorStatusLogs) {
		this.operatorStatusLogs = operatorStatusLogs;
	}

	@Override
	@Transient
	public Serializable getIdentity() {
		return this.getId();
	}
	@Override
	@Transient
	public Long getLastLoginTime() {
		return this.getLastLoginDate().getTime();
	}
	@Override
	@Transient
	public String getLoginName() {
		return this.getMobile();
	}
	/**
	 *判断管理员是否有指定url的权限
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isPermissible(String uri) {
		if(operatorRoles == null||operatorRoles.size() == 0) return false;
		for(OperatorRole operatorRole : operatorRoles) {
			Role role = operatorRole.getRole();
			if(role.isPermissible(uri)) return true;
		}
		return false;
	}
	/**
	 * 判断管理员是否有效
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isEnable() {
		return this.status == Status.STATUS_ACTIVE;
	}
	/**
	 * 添加状态日志
	 * @author jianguo.xu
	 * @param operator
	 * @param startStatus
	 * @param endStatus
	 * @param desc
	 */
	@Transient
	public void addStatusLog(Operator operator,Status startStatus, Status endStatus,String desc) {
		if(operatorStatusLogs == null) operatorStatusLogs = new HashSet<OperatorStatusLog>();
		OperatorStatusLog log = new OperatorStatusLog();
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setEndStatus(endStatus);
		log.setManager(this);
		log.setOperator(operator);
		log.setStartSatus(startStatus);
		this.getOperatorStatusLogs().add(log);
	}
	/**
	 * 添加权限规则
	 * @author jianguo.xu
	 * @param role
	 */
	@Transient
	public void addRole(Role role) {
		if(this.operatorRoles == null) this.operatorRoles = new LinkedHashSet<OperatorRole>();
		for(OperatorRole opRole : operatorRoles) {
			if(opRole.getRole().equals(role))return;
		}
		OperatorRole opRole = new OperatorRole();
		opRole.setOperator(this);
		opRole.setRole(role);
		this.operatorRoles.add(opRole);
	}
	
	/**
	 * 得到存在规则组的权限规则
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public List<OperatorRole> getRolesByExistRoleGroup() {
		if(this.getOperatorRoles() == null||this.getOperatorRoles().size() == 0) return null;
		List<OperatorRole> items = new ArrayList<OperatorRole>();
		for(OperatorRole opRole : getOperatorRoles()) {
			 
			 if(opRole.getRole().getRoleGroup()!=null) {
				 items.add(opRole);
			 }
		}
		return items;
	}
	
	/**
	 * 得到管理员的规则组集合
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public Map<RoleGroup,List<OperatorRole>> getRoleGroups() {
		if(this.operatorRoles == null||this.operatorRoles.size() == 0) return null;
		Map<RoleGroup,List<OperatorRole>> map = new LinkedHashMap<RoleGroup,List<OperatorRole>>();
		for (RoleGroup roleGroup : RoleGroup.valuesBySort()) {
			List<OperatorRole> items = getRoleGroup(roleGroup);
			if(items.size()>0){
				map.put(roleGroup, items);
			}
		}
		return map;
	}
	@Transient
	private List<OperatorRole> getRoleGroup(RoleGroup roleGroup) {
		List<OperatorRole> items = new ArrayList<OperatorRole>();
		for(OperatorRole opRole : operatorRoles) {
			if(opRole.getRole().getRoleGroup() == roleGroup) {
				items.add(opRole);
			}
		}
		return items;
	}
	/**
	 * 得到管理员规则列表的规则描述字符串
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public String getRoleDesc() {
		if(this.operatorRoles == null||this.operatorRoles.size() == 0) return null;
		StringBuilder sb = new StringBuilder();
		for(OperatorRole opRole : operatorRoles) {
			sb.append(opRole.getRole().getName()).append("; ");
		}
		return sb.substring(0,sb.length()-1).toString();
	}

}

