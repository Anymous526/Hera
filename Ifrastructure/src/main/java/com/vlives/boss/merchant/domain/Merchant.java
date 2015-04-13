/*
 * @(#)Merchant.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.merchant.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.MemberGroup;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.sms.domain.MerchantSmsAccount;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;
import com.vlives.util.ConfigUtils;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ 
		@TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.Merchant$Status") })
		 
	})
@Entity
@Table(name = "MERCHANT")
public class Merchant extends BaseEntity {
	/** 主键 */
	private Long id;
	/** 父级商户 */
	private Merchant parent;
	/**
	 * 子商户集合
	 */
	private Set<Merchant> childrens;

	private Set<MerchantLog> merchantLogs;

	/** 会员组 */
	private MemberGroup memberGroup;
	/**
	 * 折扣规则
	 */
	private Set<DiscountRule> discountRules;
	/**
	 * 积分规则
	 */
	private Set<PointRule> pointRules;
	/**
	 * 升级规则
	 */
	private Set<MemberUpdateRule> memberUpdateRules;

	/***
	 * 商户的pos集合
	 */
	private Set<Pos> poses;
	/**
	 * 商户所在区域
	 */
	private Area area;

	/** 商户编号 */
	private String code;
	/** 商户名称 */
	private String name;
	/** 商户状态 */
	private Status status;

	/** 中文名简称 */
	private String shortName;
	/** 英文名称 */
	private String englishName;
	/** 隶属机构 */
	private String parentOrganization;
	/** 营业地址 */
	private String businessAddress;
	/** 营业地址邮编 */
	private String businessAddressCode;
	/** 公交信息 */
	private String busInformation;
	/** 消费环境 */
	private String restaurant;
	/** 停车信息 */
	private String parkingInformation;
	/** 是否有无线网络 */
	private boolean wifi;
	/** 营业时间 */
	private String businessTime;
	/** 商户电话 */
	private String merchantTelephone;
	/** 商户传真 */
	private String merchantFax;
	/** 商户网站 */
	private String merchantWeb;
	/** 联系人姓名 */
	private String contactName;
	/** 联系电话 */
	private String contactTelephone;

	/** 商户创建时间 **/
	private Date createDate;
	
	/** 商户分类*/
	private MerchantCategory category;
	
	/** 商户头像*/
	private String head;

	/** 商户所在地图经度*/
	private String longitude;
	
	/** 商户所在地图纬度*/
	private String latitude;	
	/**
	 * 简介
	 */
	private String info;
	
	/**
	 * 是否有消费活动 --临时字段,数据库无对应
	 */
	private boolean favourPloy;

	/**
	 * POS机业务类型
	 */
	private long posBusInessType;
	
	/**
	 * 销售人员
	 */
	private String sales;
	
	/**
	 * POS是否计算折扣，true：计算；false：不计算
	 */
	private boolean discountFlag;
	/**
	 * 商户短信编号
	 */
	private long num;
	
	/**
	 * 商户扩展信息 用一对多代替一对一
	 */
	private Set<MerchantExtendInfo> merchantExtendInfos;
	/**
	 * 商户的相关统计 用一对多代替一对一
	 */
	private Set<MerchantReferenceStatistic> merchantReferenceStatistics;
	/**
	 * 商户短信账户 用一对多代替一对一
	 */
	private Set<MerchantSmsAccount> merchantSmsAccounts;
	/**
	 * 商户推荐排行 多对一（实质上只是一对一）
	 */
	private MerchantRecommend merchantRecommend;
	

	/**
	 * POS机业务类型
	 * @author tiger
	 *
	 */
	public static enum PosBusInessType implements EnumTypeInterface {
		/** 支持会生活业务 */
		SUPPORT_BOSS(0x01, "支持会生活业务"),
		/** 支持银行务 */
		SUPPORT_BANK(0x02, "支持银行务");
		private int value;
		private String desc;

		PosBusInessType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static PosBusInessType get(int value) {
			for (PosBusInessType posBusInessType : PosBusInessType.values()) {
				if (posBusInessType.value == value) {
					return posBusInessType;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	public static enum Status implements EnumTypeInterface {
		/** 待审核 */
		STATUS_WAIT_AUDIT(0, "待审核"),
		/** 有效状态 */
		STATUS_ACTIVE(1, "有效"),
		/** 有效状态 */
		STATUS_AUDIT_FAIL(2, "审核不通过"),
		/** 冻结状态 */
		STATUS_FREEZED(3, "冻结"),
		/** 注销状态 */
		STATUS_DISABLE(4, "注销");

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

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Merchant getParent() {
		return parent;
	}

	public void setParent(Merchant parent) {
		this.parent = parent;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "MEMBER_GROUP_ID")
	public MemberGroup getMemberGroup() {
		return memberGroup;
	}

	public void setMemberGroup(MemberGroup memberGroup) {
		this.memberGroup = memberGroup;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Type(type = "status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Transient
	public boolean isFavourPloy() {
		return favourPloy;
	}

	public void setFavourPloy(boolean favourPloy) {
		this.favourPloy = favourPloy;
	}
	
	@Column(name = "POS_BUESSINESS_TYPE")
	public long getPosBusInessType() {
		return posBusInessType;
	}

	@SuppressWarnings("unused")
	private void setPosBusInessType(long posBusInessType) {
		this.posBusInessType = posBusInessType;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return shortName;
	}
	@Transient
	public PosBusInessType[] getPosBusInessTypes() {
		List<PosBusInessType> posBusInessTypes = new ArrayList<PosBusInessType>();
		for(PosBusInessType posType:PosBusInessType.values()){
			if((posBusInessType&posType.getValue())>0)
				posBusInessTypes.add(posType);
		}
		return posBusInessTypes.toArray(new PosBusInessType[]{});
	}

	public void setPosBusInessTypes(PosBusInessType[] posBusInessTypes) {
		posBusInessType=0;
		for(int i = 0;i<posBusInessTypes.length;i++) {
			posBusInessType = posBusInessType|posBusInessTypes[i].value;
		}
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "ENGLISH_NAME")
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Column(name = "PARENT_ORGANIZATION")
	public String getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(String parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

	@Column(name = "BUSINESS_ADDRESS")
	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	@Column(name = "BUSINESS_ADDRESS_CODE")
	public String getBusinessAddressCode() {
		return businessAddressCode;
	}

	public void setBusinessAddressCode(String businessAddressCode) {
		this.businessAddressCode = businessAddressCode;
	}

	@Column(name = "BUS_INFORMATION")
	public String getBusInformation() {
		return busInformation;
	}

	public void setBusInformation(String busInformation) {
		this.busInformation = busInformation;
	}

	@Column(name = "RESTAURANT")
	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	@Column(name = "PARKING_INFORMATION")
	public String getParkingInformation() {
		return parkingInformation;
	}

	public void setParkingInformation(String parkingInformation) {
		this.parkingInformation = parkingInformation;
	}

	@Column(name = "WIFI")
	public boolean getWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	@Column(name = "BUSINESS_TIME")
	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	@Column(name = "MERCHANT_TELEPHONE")
	public String getMerchantTelephone() {
		return merchantTelephone;
	}

	public void setMerchantTelephone(String merchantTelephone) {
		this.merchantTelephone = merchantTelephone;
	}

	@Column(name = "MERCHANT_FAX")
	public String getMerchantFax() {
		return merchantFax;
	}

	public void setMerchantFax(String merchantFax) {
		this.merchantFax = merchantFax;
	}

	@Column(name = "MERCHANT_WEB")
	public String getMerchantWeb() {
		return merchantWeb;
	}

	public void setMerchantWeb(String merchantWeb) {
		this.merchantWeb = merchantWeb;
	}

	@Column(name = "CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "CONTACT_TELEPHONE")
	public String getContactTelephone() {
		return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@OneToMany(mappedBy = "parent")
	@OrderBy("id")
	public Set<Merchant> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Merchant> childrens) {
		this.childrens = childrens;
	}

	@OneToMany(mappedBy = "merchant")
	public Set<Pos> getPoses() {
		return poses;
	}

	public void setPoses(Set<Pos> poses) {
		this.poses = poses;
	}

	@OneToMany(mappedBy = "merchant", cascade = { CascadeType.ALL })
	@OrderBy("paramerOne")
	public Set<DiscountRule> getDiscountRules() {
		return discountRules;
	}

	public void setDiscountRules(Set<DiscountRule> discountRules) {
		this.discountRules = discountRules;
	}

	@OneToMany(mappedBy = "merchant", cascade = { CascadeType.ALL })
	@OrderBy("paramerOne")
	public Set<PointRule> getPointRules() {
		return pointRules;
	}

	public void setPointRules(Set<PointRule> pointRules) {
		this.pointRules = pointRules;
	}

	@OneToMany(mappedBy = "merchant", cascade = { CascadeType.ALL })
	@OrderBy("createDate desc")
	public Set<MerchantLog> getMerchantLogs() {
		return merchantLogs;
	}

	public void setMerchantLogs(Set<MerchantLog> merchantLogs) {
		this.merchantLogs = merchantLogs;
	}

	@OneToMany(mappedBy = "merchant", cascade = { CascadeType.ALL })
	@OrderBy("level")
	public Set<MemberUpdateRule> getMemberUpdateRules() {
		return memberUpdateRules;
	}
	public void setMemberUpdateRules(Set<MemberUpdateRule> memberUpdateRules) {
		this.memberUpdateRules = memberUpdateRules;
	}

	@SuppressWarnings("unused")
	@OneToMany(mappedBy = "merchant", cascade = { CascadeType.ALL })
	private Set<MerchantExtendInfo> getMerchantExtendInfos() {
		return merchantExtendInfos;
	}

	@SuppressWarnings("unused")
	private void setMerchantExtendInfos(Set<MerchantExtendInfo> merchantExtendInfos) {
		this.merchantExtendInfos = merchantExtendInfos;
	}

	@Transient
	public MerchantExtendInfo getMerchantExtendInfo() {
		if (merchantExtendInfos == null || merchantExtendInfos.size() == 0)
			return null;
		return merchantExtendInfos.iterator().next();
	}

	public void setMerchantExtendInfo(MerchantExtendInfo merchantExtendInfo) {
		merchantExtendInfos = new HashSet<MerchantExtendInfo>();
		merchantExtendInfos.add(merchantExtendInfo);
		merchantExtendInfo.setMerchant(this);
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	public MerchantCategory getCategory() {
		return category;
	}

	public void setCategory(MerchantCategory category) {
		this.category = category;
	}

	@Column(name="HEAD")
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
	
	

	@Column(name="LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name="LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="INFO")
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	@Column(name="SALES")
	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}


	@SuppressWarnings("unused")
	@OneToMany(mappedBy = "merchant", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<MerchantReferenceStatistic> getMerchantReferenceStatistics() {
		return merchantReferenceStatistics;
	}

	@SuppressWarnings("unused")
	private void setMerchantReferenceStatistics(Set<MerchantReferenceStatistic> merchantReferenceStatistics) {
		this.merchantReferenceStatistics = merchantReferenceStatistics;
	}

	@Transient
	public MerchantReferenceStatistic getMerchantReferenceStatistic() {
		if (merchantReferenceStatistics == null || merchantReferenceStatistics.size() == 0) {
			MerchantReferenceStatistic referenceStatistic = new MerchantReferenceStatistic();
			referenceStatistic.setMerchant(this);
			referenceStatistic.setCreateDate(new Date());
			this.setMerchantReferenceStatistic(referenceStatistic);
			return referenceStatistic;
		}
		return merchantReferenceStatistics.iterator().next();
	}

	public void setMerchantReferenceStatistic(MerchantReferenceStatistic merchantReferenceStatistic) {
		this.merchantReferenceStatistics = new HashSet<MerchantReferenceStatistic>();
		this.merchantReferenceStatistics.add(merchantReferenceStatistic);
	}

	@SuppressWarnings("unused")
	@OneToMany(mappedBy = "merchant", cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
	private Set<MerchantSmsAccount> getMerchantSmsAccounts() {
		return merchantSmsAccounts;
	}

	@SuppressWarnings("unused")
	private void setMerchantSmsAccounts(Set<MerchantSmsAccount> merchantSmsAccounts) {
		this.merchantSmsAccounts = merchantSmsAccounts;
	}

	 
	@Transient
	public MerchantSmsAccount getMerchantSmsAccount() {
		if (merchantSmsAccounts == null || merchantSmsAccounts.size() == 0) {
			MerchantSmsAccount merchantSmsAccount = new MerchantSmsAccount();
			merchantSmsAccount.setMerchant(this);
			this.setMerchantSmsAccount(merchantSmsAccount);
			return merchantSmsAccount;
		}
		return merchantSmsAccounts.iterator().next();
	}

	public void setMerchantSmsAccount(MerchantSmsAccount merchantSmsAccount) {
		this.merchantSmsAccounts = new HashSet<MerchantSmsAccount>();
		this.merchantSmsAccounts.add(merchantSmsAccount);
	}

	/**
	 * 判断是否有父商户
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isExistParent() {
		return this.getParent() != null;
	}

	/**
	 * 判断是否有子商户
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isExistChildren() {
		return this.getChildrens() != null && this.getChildrens().size() > 0;
	}
	/**
	 * 得到有效的子商户
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public List<Merchant> getValidChildrens(){
		List<Merchant> childrens = new ArrayList<Merchant>();
		if(!isExistChildren()) return childrens;
		for(Merchant children : this.getChildrens()) {
			if(children.isActive()) 
				childrens.add(children) ;
		}
		return childrens;
	}
	/**
	 * 判断是否有有效的子商户
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isExistValidChildren() {
		return getValidChildrens().size()>0;
	}

	/**
	 * 添加状态变更日志
	 * 
	 * @param operator
	 *            操作者
	 * @param startStatus
	 *            开始状态
	 * @param endStatus
	 *            结束状态
	 * @param desc
	 *            变更原因
	 */
	@Transient
	public void addStatusLog(Operator operator, Status startStatus, Status endStatus, String desc) {
		MerchantLog log = new MerchantLog();
		log.setStartStatus(startStatus);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setEndStatus(endStatus);
		log.setMerchant(this);
		log.setOperator(operator);
		if (merchantLogs == null) {
			this.merchantLogs = new HashSet<MerchantLog>();
		}
		merchantLogs.add(log);
	}

	/**
	 * 判断商户是否有效
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isActive() {
		return this.getStatus() == Status.STATUS_ACTIVE;
	}

	@Transient
	public boolean isCanCreateOperator() {
		return isActive() || isWaitAduit();
	}

	/**
	 * 判断商户是否冻结
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isFreeze() {
		return this.getStatus() == Status.STATUS_FREEZED;
	}

	/**
	 * 判断商户是否注销
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isLogOut() {
		return this.getStatus() == Status.STATUS_DISABLE;
	}
	

	/**
	 * 判断是否等待审核
	 * 
	 * @return
	 */
	@Transient
	public boolean isWaitAduit() {
		return this.getStatus() == Status.STATUS_WAIT_AUDIT;
	}

	/**
	 * 判断是否审核失败
	 * 
	 * @return
	 */
	@Transient
	public boolean isAduitFail() {
		return this.getStatus() == Status.STATUS_AUDIT_FAIL;
	}

	/**
	 * 是否允许修改
	 * 
	 * @return
	 */
	@Transient
	public boolean isAllowModify() {
		return (this.status == Status.STATUS_ACTIVE || this.status == Status.STATUS_AUDIT_FAIL) ? true : false;
	}

	/**
	 * 添加折扣规则，如果折扣已经存在则更新
	 * 
	 * @author jianguo.xu
	 * @param discountRule
	 */
	@Transient
	public void addDiscountRule(DiscountRule discountRule) {
		if (this.discountRules == null)
			this.discountRules = new HashSet<DiscountRule>();
		for (DiscountRule rule : this.discountRules) {
			if (rule.getType() == discountRule.getType() && rule.getParamerOne() == discountRule.getParamerOne()) {
				rule.setParamerTwo(discountRule.getParamerTwo());
				return;
			}
		}
		discountRules.add(discountRule);
		discountRule.setMerchant(this);
	}

	/**
	 * 添加升级规则,如果规则已经存在则更新
	 * 
	 * @param updateRule
	 */
	public void addLevelRule(MemberUpdateRule updateRule) {
		if (memberUpdateRules == null)
			this.memberUpdateRules = new HashSet<MemberUpdateRule>();
		for (MemberUpdateRule rule : memberUpdateRules) {
			if (rule.getLevel() == updateRule.getLevel()) {
				rule.setDesc(updateRule.getDesc());
				rule.setRewardPoint(updateRule.getRewardPoint());
				rule.setUpdateRuleItems(updateRule.getUpdateRuleItems());
				return;
			}
		}
		memberUpdateRules.add(updateRule);
		updateRule.setMerchant(this);
	}

	/**
	 * 添加积分规则，如果积分已经存在则更新
	 * 
	 * @author jianguo.xu
	 * @param discountRule
	 */
	@Transient
	public void addPointRule(PointRule pointRule) {
		if (this.pointRules == null)
			this.pointRules = new HashSet<PointRule>();
		for (PointRule rule : this.pointRules) {
			if (rule.getType() == pointRule.getType() && rule.getParamerOne() == pointRule.getParamerOne()) {
				rule.setParamerTwo(pointRule.getParamerTwo());
				return;
			}
		}
		pointRules.add(pointRule);
		pointRule.setMerchant(this);

	}

	@Transient
	public int getChildCount() {
		if (this.getChildrens() == null) {
			return 0;
		} else {
			return this.getChildrens().size();
		}

	}

	@Transient
	public String getParentName() {
		if (this.isExistParent()) {
			return this.getParent().getName();
		} else {
			return "";
		}
	}

	/**
	 * 判断子商户是否能被冻结
	 * 
	 * @author unicorn
	 * @return true 能被冻结,false 不能被冻结，如果子商户为有效状态，父商户就不能冻结
	 */
	@Transient
	public boolean canFreese() {
		if (this.isExistChildren()) {
			for (Merchant child : this.getChildrens()) {
				if(child.isActive()||child.isWaitAduit()||child.isAduitFail()) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}
	
	/**
	 * 获得他的儿子和自己的集合<br/>
	 * 如果没有儿子就返回 null;
	 * @return
	 */
	@Transient
	public Set<Merchant> getChildrenAndMySelf(){
		if(!this.isExistChildren())
			return null;
		Set<Merchant> set = new LinkedHashSet<Merchant>();
		set.add(this);
		set.addAll(this.getChildrens());
		 
		return set;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="RECOMMEND_ID")
	public MerchantRecommend getMerchantRecommend() {
		return merchantRecommend;
	}

	public void setMerchantRecommend(MerchantRecommend merchantRecommend) {
		this.merchantRecommend = merchantRecommend;
	}

	@Column(name="DISCOUNT_FLAG")
	public boolean isDiscountFlag() {
		return discountFlag;
	}

	public void setDiscountFlag(boolean discountFlag) {
		this.discountFlag = discountFlag;
	}
	
	@Transient
	public MemberUpdateRule getMemberUpdateRule() {
		if (memberUpdateRules == null || memberUpdateRules.size() == 0) {
			MemberUpdateRule memberUpdateRule = new MemberUpdateRule();
			memberUpdateRule.setMerchant(this);
			this.setMemberUpdateRule(memberUpdateRule);
			return memberUpdateRule;
		}
		return memberUpdateRules.iterator().next();
	}

	public void setMemberUpdateRule(MemberUpdateRule memberUpdateRule) {
		this.memberUpdateRules = new HashSet<MemberUpdateRule>();
		this.memberUpdateRules.add(memberUpdateRule);
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}
	
	//升级规则
	@Transient
	public String getMemberUpdate() {
		String memberUpdate = "";
		Set<MemberUpdateRule> update = this.getMemberUpdateRules();
		Iterator<MemberUpdateRule> iteratorUpdate = update.iterator();
		while(iteratorUpdate.hasNext()) {
			MemberUpdateRule memberUpdateRule = (MemberUpdateRule)iteratorUpdate.next();
			if(memberUpdateRule.getId() != null){
				Iterator<?> iteratorUpdateRuleItems = memberUpdateRule.getUpdateRuleItems().iterator();
				while(iteratorUpdateRuleItems.hasNext()){
					UpdateRuleItem updateRuleItem = (UpdateRuleItem)iteratorUpdateRuleItems.next();
					String unit = " 元";
					if(updateRuleItem.getType().getValue() == UpdateRuleItem.Type.TYPE_POINT.getValue()){
						unit = " 分";
					}
					if(memberUpdateRule.getLevel().getValue() != Level.DIAMOND.getValue()){
						memberUpdate += updateRuleItem.getType().getDesc()+" 满"+ updateRuleItem.getUpdatePoint() + unit + "成为 " + memberUpdateRule.getNextLevel().getDesc() + "<br>";
					}
				}
			}
		}
		return memberUpdate;
	}

	//会员等级
	@Transient
	public String getMemberLevel() {
		String memberLevel = "";
		
		for(int i=1; i<=Level.values().length; i++){
			memberLevel += "<br>" + Level.get(i).getDesc();
		}
		if(memberLevel.length() > 1){
			memberLevel = memberLevel.substring(4);
		}
		return memberLevel;
	}
	
	//积分规则
	@Transient
	public String getMemberPoint() {
		String memberPoint = "";
		
		Set<PointRule> point = this.getPointRules();
		Iterator<PointRule> iteratorPoint = point.iterator();
		int count=0;
		while(iteratorPoint.hasNext()) {
			PointRule pointRule = (PointRule)iteratorPoint.next();
			Integer integer = pointRule.getParamerTwo();
			if(Integer.toString(integer).isEmpty()){
				integer = 100;
			}
			memberPoint +="<br>"+integer+"%" ;
			count++;
		}
		
		for(int i=Level.values().length; i>count; i--){
			memberPoint += "<br>100%";
		}
		
		if(memberPoint.length() > 1){
			memberPoint = memberPoint.substring(4);
		}
		return memberPoint;
	}
	
	//会员等级折扣
	@Transient
	public String getMemberDiscount() {
		String memberDiscount = "";
		
		Set<DiscountRule> discount = this.getDiscountRules();
		Iterator<DiscountRule> iteratorDiscount = discount.iterator();
		int count = 0;
		while(iteratorDiscount.hasNext()) {
			DiscountRule discountRule = (DiscountRule)iteratorDiscount.next();
			Integer integer = discountRule.getParamerTwo();
			if(Integer.toString(integer).isEmpty()){
				integer = 100;
			}
			memberDiscount +="<br>"+integer+"%" ;
			count++;
		}
		
		for(int i=Level.values().length; i>count; i--){
			memberDiscount += "<br>100%";
		}
		
		if(memberDiscount.length() > 1){
			memberDiscount = memberDiscount.substring(4);
		}
		return memberDiscount;
	}
	/**
	 * 得到商户的显示头像
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public String getShowHead() {
		return this.getHead() == null?ConfigUtils.MERCHANT_DEFAULT_HEAD:this.getHead();
	}
	/**
	 * 得到商户基于http的显示头像
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public String getHeadForWeb() {
		return ConfigUtils.WEB_CLOUDBOSS_IP_URL+getShowHead();
	}
	
}
