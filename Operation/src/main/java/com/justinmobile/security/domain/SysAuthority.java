package com.justinmobile.security.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;

import com.justinmobile.core.domain.AbstractEntity;
import com.justinmobile.core.exception.BusinessException;

@Entity
@Table(name = "SYS_AUTHORITY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysAuthority extends AbstractEntity {

	private static final long serialVersionUID = -1696933371379708151L;
	
	public static final int ENABLE = 1;
	
	public static final int UNENABLE = 0;

	private Long id;
	
	/** 权限名 */
	private String authName;
	
	/** 权限描述 */
	private String description;
	
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	
	private Set<SysResource> sysResources = new HashSet<SysResource>(0);
	
	private Set<SysMenu> sysMenus = new HashSet<SysMenu>(0);

	@ManyToMany(mappedBy = "sysAuthoritys", targetEntity = SysRole.class)
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_AUTH_RES", joinColumns = { @JoinColumn(name = "AUTH_ID") }, inverseJoinColumns = { @JoinColumn(name = "RES_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysResource> getSysResources() {
		return sysResources;
	}

	public void setSysResources(Set<SysResource> sysResources) {
		this.sysResources = sysResources;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_AUTH_MENU", joinColumns = { @JoinColumn(name = "AUTH_ID") }, inverseJoinColumns = { @JoinColumn(name = "MENU_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysMenu> getSysMenus() {
		return sysMenus;
	}
	
	public void setSysMenus(Set<SysMenu> sysMenus) {
		this.sysMenus = sysMenus;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SYS_AUTHORITY") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Length(max = 32)
	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	 
	@Length(max = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static GrantedAuthority[] toGrantedAuthority(
			Collection<SysAuthority> sysAuthorities) throws BusinessException {
		List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>();
		if (CollectionUtils.isNotEmpty(sysAuthorities)) {
			for (SysAuthority sysAuthority : sysAuthorities) {
				authsList.add(new GrantedAuthorityImpl(sysAuthority.getAuthName()));
			}
		}
		return authsList.toArray(new GrantedAuthority[authsList.size()]);
	}
	
	public static String[] propertiesToArray() {
		return new String[]{"id", "name", "description"};
	}
}
