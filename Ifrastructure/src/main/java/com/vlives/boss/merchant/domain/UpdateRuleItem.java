/*
 * @(#)UpdateRoleItem.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.merchant.domain;

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
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;


/**
 * 会员等级升级规则明细
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.UpdateRuleItem$Type") }) })
@Entity
@Table(name="UPDATE_RULE_ITEM")
public class UpdateRuleItem extends BaseEntity{

	/*** 编号*/
	private Long id;
	
	/*** 升级规则*/
	private MemberUpdateRule memberUpdateRole;
	
	/*** 升级类型*/
	private Type type;
	
	/*** 升级零界点*/
	private int updatePoint;
	
	
	public static enum Type implements EnumTypeInterface {
		/** 一次消费*/
		TYPE_ONCE_CONSUME(1, "一次消费"),
		/** 累计消费 */
		TYPE_ALL_CONSUME(2, "累计消费"),
		/** 积分*/
		TYPE_POINT(3, "累计积分");
		
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
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_UPDATE_RULE_ITEM") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATE_RULE_ID")
	public MemberUpdateRule getMemberUpdateRole() {
		return memberUpdateRole;
	}

	public void setMemberUpdateRole(MemberUpdateRule memberUpdateRole) {
		this.memberUpdateRole = memberUpdateRole;
	}
	
	@org.hibernate.annotations.Type(type = "type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name="UPDATE_CRITICAL_POINT")
	public int getUpdatePoint() {
		return updatePoint;
	}

	public void setUpdatePoint(int updatePoint) {
		this.updatePoint = updatePoint;
	}
	
	
}

