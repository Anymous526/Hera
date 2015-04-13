/*
 * @(#)MemberGroup.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.core.support.entity.BaseEntity;

/**
 * 商户会员组
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@Entity
@Table(name="MEMBER_GROUP")
public class MemberGroup extends BaseEntity{

	/*** 编号*/
	private Long id;
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MEMBER_GROUP") })
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 商户集合
	 */
	private Set<Merchant> merchants;
	
	/**
	 * 会员数
	 */
	private int memberCount;

	@OneToMany(mappedBy = "memberGroup")
	@OrderBy("id")
	public Set<Merchant> getMerchants() {
		return merchants;
	}
	public void setMerchants(Set<Merchant> merchants) {
		this.merchants = merchants;
	}
	
	@Column(name="MEMBER_COUNT")
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	/**
	 * 增加会员数
	 */
	@Transient
	public void addMemberCount(){
		this.memberCount++;
	}
	
	
	
}

