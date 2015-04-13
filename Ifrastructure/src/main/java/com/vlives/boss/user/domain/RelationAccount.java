/*
 * @(#)RelationAccount.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.core.support.hibernate.EnumType;

/**
 * 关联账户
 * @author  fyuan
 * @version 1.0,2011-7-5
 */
@TypeDefs({ @TypeDef(name = "accountType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.user.domain.AccountType") }),
		@TypeDef(name = "authenticationEntry", typeClass = AuthenticationEntryHibernateType.class)
	})
@Entity
@Table(name="RELATION_ACCOUNT") 
public class RelationAccount {

	/**
	 * 编号
	 */
	private Long id;
	
	/**
	 * 用户
	 */
	private User user;
	
	/**
	 * 关联帐号认证XML
	 */
	private AuthenticationEntry authenticationEntry;
	
	/**
	 * 是否有效
	 */
	private  boolean enable = true;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 账户类型
	 */
	private AccountType accountType;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_RELATION_ACCOUNT") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Type(type="authenticationEntry")
	@Column(name="AUTHENTICATION_ENTRY")
	public AuthenticationEntry getAuthenticationEntry() {
		return authenticationEntry;
	}

	public void setAuthenticationEntry(AuthenticationEntry authenticationEntry) {
		this.authenticationEntry = authenticationEntry;
	}

	@Column(name="ENABLE")
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Type(type="accountType")
	@Column(name="type")	
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	
	
	
}

