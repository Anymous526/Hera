package com.vlives.boss.area.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;
@TypeDefs({ @TypeDef(name = "areaType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.area.domain.Area$AreaType") }) })
@Entity
@Table(name = "AREA")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Area extends BaseEntity {

	private Long id;

	/** 区域名称名称 */
	private String name;
	/** 区域类型 */
	private AreaType areaType;

	private Area parent;
	/**
	 * 区域查询码
	 */
	private String searchCode;
	private Set<Area> childrens = new HashSet<Area>();
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_AREA") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static enum AreaType implements EnumTypeInterface {
		COUNTY(1, "国家"), PROVINCE(2, "省"), CITY(3, "市"),DISTRICT(4,"县(区)");
		private int value;

		private String desc;

		private AreaType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public static AreaType get(int value) {
			for (AreaType areaType : AreaType.values()) {
				if (value == areaType.getValue()) {
					return areaType;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}

		public int getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

	}

	@Column(name="AREA_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Type(type="areaType")
   @Column(name="AREA_TYPE")
	public AreaType getAreaType() {
		return areaType;
	}

	public void setAreaType(AreaType areaType) {
		this.areaType = areaType;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<Area> getChildrens() {
		return childrens;
	}
	  @Column(name="SEARCH_CODE")
	private String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public void setChildrens(Set<Area> childrens) {
		this.childrens = childrens;
	}
	
	@Transient
	public boolean isExitParent() {
		return this.getParent() != null;
	}
	/**
	 * 判断是否是叶子节点
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isLeaf() {
		return this.getChildrens()==null||this.getChildrens().size()==0;
	}
	
	
	/**
	 * 获得区域从上自下名称
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public String getAddress() {
		List<Area> areas = getAreaPath();
		StringBuilder sb = new StringBuilder("");
		if(areas.size()<=1)return sb.toString();
		for(int i = 1;i<areas.size();i++) {
			Area area = areas.get(i);
			sb.append(area.getName()+" ");
		}
		return sb.toString();
	}
	/**
	 * 得到区域的路径（从国家往后推）
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public List<Area> getAreaPath() {
		List<Area> areas = new ArrayList<Area>();
		Area area = this;
		areas.add(area);
		while(true) {
			if(area.isExitParent()) {
				area = area.getParent();
				areas.add(area);
			}
			else {
				break;
			}
		}
		Collections.reverse(areas);
		return areas;
		
	}
	 
	/**
	 * 判断是否是街道
	 * @author jianguo.xu
	 * @return
	 */
	@Transient
	public boolean isDistrict() {
		return this.getAreaType() == AreaType.DISTRICT;
	}
	@Transient
	public String getAreaSearchCode() {
		return this.isLeaf()?this.getSearchCode():this.getSearchCode()+"_%";
	}

}
