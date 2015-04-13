package com.vlives.boss.security.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.security.util.AntUrlPathMatcher;

import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;
import com.vlives.util.ObjectComparatorUtils;

/**
 * @author unicorn
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "roleGroup", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.security.domain.Role$RoleGroup") })})

@Entity
@Table(name = "ROLE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role extends BaseEntity {
	
	private static final AntUrlPathMatcher urlMatcher = new AntUrlPathMatcher();
	
	/** ID*/
	private Long id;
	/** 规则名称*/
	private String name;
	/** 描述信息*/
	private String description;
	/**
	 * 所属规则组
	 */
	private RoleGroup roleGroup;
	
	private Set<RoleResource>  roleResources;
	/**
	 * 规则入口
	 */
	private String entryIndex;
	/**
	 * 图标
	 */
	private String logo;
	/**
	 * 是否是快速导航
	 */
	private boolean shortcut;
	/**
	 * 规则组
	 * @author tiger
	 *
	 */
	public static enum RoleGroup implements EnumTypeInterface {
		/** 会员管理*/
		MEMBER_MANAGER(1,1, "会员管理"),
		/** 短信营销管理*/
		SALE_MANAGER(2,3, "短信营销管理"),
		/** 门店查询*/
		MERCHANT_MANAGER(3,4, "门店查询"),
		/** 点评管理*/
		COMMENT_MANAGER(4,5, "点评管理"),
		/** 内部管理*/
		INSIDE_MANAGER(5,6, "内部管理"),
		/** 权限管理*/
		SECURITY_MANAGER(6,7,"权限管理"),
		/** 统计报表管理*/
		REPORT_MANAGER(7,8, "统计报表管理 "),
		/**
		 * 优惠营销管理
		 */
		COUPON_MANAGER(8,2, "优惠券营销管理 ");

		private final int value;
		/**
		 * 排序值
		 */
		private final int sort;
		private final String desc;

		RoleGroup(int value,int sort, String desc) {
			this.value = value;
			this.sort = sort;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}
		
		public int getSort() {
			return sort;
		}
		/**
		 * 得到排序后的规则列表
		 * @author jianguo.xu
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static RoleGroup[] valuesBySort() {
			RoleGroup[] roleGroups = values();
			List<RoleGroup> list = Arrays.asList(roleGroups);
			String[][] rules = new String[][] {new String[] {"sort",ObjectComparatorUtils.ASCE}};
			Collections.sort(list, new ObjectComparatorUtils(RoleGroup.class,rules));
			return (RoleGroup[]) list.toArray();
		}

		public static RoleGroup get(int value) {
			for (RoleGroup roleGroup : RoleGroup.values()) {
				if (roleGroup.value == value) {
					return roleGroup;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_ROLE") })
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "role")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<RoleResource> getRoleResources() {
		return roleResources;
	}


	public void setRoleResources(Set<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}
	
	@Type(type="roleGroup")
	@Column(name="ROLE_GROUP")
	public RoleGroup getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(RoleGroup roleGroup) {
		this.roleGroup = roleGroup;
	}
	@Column(name="ENTRY_INDEX")
	public String getEntryIndex() {
		return entryIndex;
	}
	public void setEntryIndex(String entryIndex) {
		this.entryIndex = entryIndex;
	}
	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Column(name="SHORT_CUT")
	public boolean isShortcut() {
		return shortcut;
	}


	public void setShortcut(boolean shortcut) {
		this.shortcut = shortcut;
	}


	/**
	 *判断该角色是否有指定url的权限
	 * @author jianguo.xu
	 * @return
	 */
	 @Transient
	public boolean isPermissible(String url) {
		urlMatcher.setRequiresLowerCaseUrl(true);
		for(RoleResource roleResource : roleResources) {
			if(urlMatcher.pathMatchesUrl(roleResource.getResource().getPath(), url)) return true;
		}
		return false;
	}
	 
	
}
