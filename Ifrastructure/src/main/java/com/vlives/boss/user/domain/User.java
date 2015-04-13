/*
 * @(#)User.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.user.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.area.domain.Area;
import com.vlives.core.security.Principal;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;
import com.vlives.util.StringUtils;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({
		@TypeDef(name = "cardType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.user.domain.CardType") }),
		@TypeDef(name = "userSource", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.user.domain.UserSource") }) })
@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements Principal {
	
	
	
	/**
	 * 默认密码的明文
	 */
	public static final String USER_DEFAULT_PASSWORD = "111111";

	/*** 编号 */
	private Long id;
	/*** 手机号 */
	private String mobile; // 唯一判断
	/*** 密码 */
	private String password; // 预设密码111111
	/*** 是否有效 */
	private boolean valid;
	/*** 姓名 */
	private String name;
	/*** 昵称 */
	private String petName;
	/**
	 * 性别
	 * 0、保密或未知
	 * 1、男
	 * 2、女 
	 * */
	private int gender;
	/*** 区域 */
	private Area area;
	/*** 地址 */
	private String address;
	/*** 出生日期 */
	private Date birthday;
	/*** 邮编 */
	private String post;
	/*** 电子邮件 */
	private String email; // 唯一判断
	/*** msn */
	private String msn;
	/*** QQ号 */
	private String qq;
	/*** 证件类型 */
	private CardType cardType=CardType.CARDTYPE_IDENTITY;
	/*** 证件号 */
	private String cardNumber; // 证件类型加证件号 唯一判断
	/*** 编创建日期 */
	private Date createDate;
	/*** 最后登录日期 */
	private Date lastLoginDate;

	/** 用户来源 */
	private UserSource source;

	/** 用户头像 */
	private String head;

	/** 用户验证码 */
	private String verifyCode;

	/** 验证码创建时间 */
	private Date codeCreateDate;

	/** 验证码创建次数 */
	private int codeCreateCount;
	/**
	 * 用户关联账户
	 */
	private Set<RelationAccount> relationAccounts;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_USERS") })
	public Long getId() {
		return id;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "VALID")
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NIKE_NAME")
	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	@Column(name = "GENDER")
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "POST")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MSN")
	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "QQ")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Type(type = "cardType")
	@Column(name = "CARD_TYPE")
	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	@Column(name = "CARD_NUMBER")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "LAST_LOGIN_DATE")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Type(type = "userSource")
	@Column(name = "SOURCE")
	public UserSource getSource() {
		return source;
	}

	public void setSource(UserSource source) {
		this.source = source;
	}

	@Column(name = "HEAD")
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	@Column(name = "SECURITY_CODE")
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Column(name = "CODE_CREATE_DATE")
	public Date getCodeCreateDate() {
		return codeCreateDate;
	}

	public void setCodeCreateDate(Date codeCreateDate) {
		this.codeCreateDate = codeCreateDate;
	}

	@Column(name = "CODE_CREATE_COUNT")
	public int getCodeCreateCount() {
		return codeCreateCount;
	}

	public void setCodeCreateCount(int codeCreateCount) {
		this.codeCreateCount = codeCreateCount;
	}

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	public Set<RelationAccount> getRelationAccounts() {
		return relationAccounts;
	}

	public void setRelationAccounts(Set<RelationAccount> relationAccounts) {
		this.relationAccounts = relationAccounts;
	}

	/**
	 * 根据管理账户类型获得关联账户， 如果关联帐号不存在返回null
	 * 
	 * @author jianguo.xu
	 * @param accountType
	 * @return
	 */
	@Transient
	public RelationAccount getRelationAccountByType(AccountType accountType) {
		if (relationAccounts == null || relationAccounts.size() == 0)
			return null;
		for (RelationAccount relationAccount : relationAccounts) {
			if (relationAccount.getAccountType() == accountType)
				return relationAccount;
		}
		return null;
	}

	/**
	 * 添加关联账户，如果关联账户对应的类型已经存在，则更新authenticationEntry
	 * 
	 * @author jianguo.xu
	 * @param accountType
	 * @param authenticationEntry
	 */
	public void addRelationAccount(AccountType accountType,
			AuthenticationEntry authenticationEntry) {
		RelationAccount relationAccount = getRelationAccountByType(accountType);
		if (relationAccount == null) {
			relationAccount = new RelationAccount();
			relationAccount.setAuthenticationEntry(authenticationEntry);
			relationAccount.setCreateDate(new Date());
			relationAccount.setEnable(true);
			relationAccount.setAccountType(accountType);
			relationAccount.setUser(this);
			this.relationAccounts.add(relationAccount);
		} else {
			relationAccount.setAuthenticationEntry(authenticationEntry);
			relationAccount.setCreateDate(new Date());
		}
	}

	/**
	 * 获得性别的描述
	 * 
	 * @return
	 */
	@Transient
	public String getGenderDesc() {
		if (gender == 0)
			return "保密";
		if (gender == 1)
			return "男";
		if (gender == 2)
			return "女";
		return "";
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
	 * 取得用户手机号码后4位
	 * 
	 * @return
	 */
	@Transient
	public String getShortMobile() {
		if (StringUtils.isNullOrEmpty(this.mobile))
			return null;
		return mobile.substring(mobile.length() - 4);
	}
}
