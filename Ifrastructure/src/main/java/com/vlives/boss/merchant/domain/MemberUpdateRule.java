/*
 * @(#)MemberUpdateRole.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.merchant.domain;

import java.util.HashSet;
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

import com.vlives.boss.member.domain.Level;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 会员升级规则
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({@TypeDef(name = "level", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.Level") })})
@Entity
@Table(name="MEMBER_UPDATE_RULE")
public class MemberUpdateRule extends BaseEntity{

	/*** 编号*/
	private Long id;
	
	/*** 所属商户*/
	private Merchant merchant;
	/**
	 * 会员升级到当前等级赠送的积分
	 */
	private int rewardPoint;
	
	/**
	 * 备注
	 */
	private String desc;
	
	private Set<UpdateRuleItem> updateRuleItems;
	
	/**等级*/
	private Level level;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MEMBER_UPDATE_RULE") })
	
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
	@Column(name = "MEMBER_LEVEL")
	@Type(type="level")
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Column(name = "REWARD_POINT")
	public int getRewardPoint() {
		return rewardPoint;
	}

	public void setRewardPoint(int rewardPoint) {
		this.rewardPoint = rewardPoint;
	}
	@OneToMany(mappedBy = "memberUpdateRole",cascade={CascadeType.ALL})
	@OrderBy("type")
	public Set<UpdateRuleItem> getUpdateRuleItems() {
		return updateRuleItems;
	}

	public void setUpdateRuleItems(Set<UpdateRuleItem> updateRuleItems) {
		this.updateRuleItems = updateRuleItems;
	}
	
	
	@Column(name="DESCRIPTION")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 添加升级规则，如果升级规则已经存在则更新
	 * @author jianguo.xu
	 * @param discountRule
	 */
	@Transient
	public void addUpdateRuleItem(UpdateRuleItem updateRuleItem) {
		if(this.updateRuleItems == null) this.updateRuleItems = new HashSet<UpdateRuleItem>();
		for(UpdateRuleItem rule: this.updateRuleItems) {
			if(rule.getType()==updateRuleItem.getType()) {
				rule.setUpdatePoint(updateRuleItem.getUpdatePoint());
				return;
			}
		}
		updateRuleItem.setMemberUpdateRole(this);
		this.updateRuleItems.add(updateRuleItem);
	}
	
	/**
	 * 获得下一等级
	 * @return
	 */
	@Transient
	public Level getNextLevel(){
		if(level != null && level.getValue() < Level.DIAMOND.getValue()){
			return Level.get(level.getValue()+1);
		}
		return null;
	}
	
}

