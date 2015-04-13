package com.vlives.boss.border.domain;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

@TypeDefs({ @TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.border.domain.BorderType") }) })
@Entity
@Table(name="BORDER") 

public class Border extends BaseEntity{

	private Long id;
	
	/** 标题*/
	private String name;
	
	/** 内容*/
	private String content;
	
	/** 创建日期*/
	private Date createDate;
	
	/**
	 * 公告分类
	 */
	private BorderType type;


	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_BORDER") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Type(type="type")
	@Column(name="type")
	public BorderType getType() {
		return type;
	}

	public void setType(BorderType type) {
		this.type = type;
	}
	
	

}
