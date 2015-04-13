/*
 * @(#)MerchantCategory.java
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-7-5
 */
@Entity
@Table(name = "MERCHANT_CATEGORY")
public class MerchantCategory {
	
	public static final Long ROOT_ID = 0L ;

	/** 编号*/
	private Long id;
	
	/** 分类名称*/
	private String name;
	
	/** 父分类*/
	private MerchantCategory parent;
	
	/** 儿子*/
	private Set<MerchantCategory> childrens = new HashSet<MerchantCategory>();

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_CATEGORY") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public MerchantCategory getParent() {
		return parent;
	}

	public void setParent(MerchantCategory parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent",cascade = { CascadeType.ALL })
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<MerchantCategory> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<MerchantCategory> childrens) {
		this.childrens = childrens;
	}
	@Transient
	public MerchantCategory getChildrenByName(String name) {
		if(childrens==null||childrens.size()==0) return null;
		for(MerchantCategory chilren : childrens) {
			if(chilren.getName().equals(name)) return chilren;
		}
		return null;
	}
	
	
}

