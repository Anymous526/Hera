package com.justinmobile.security.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "SYS_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRole extends AbstractEntity {

	private static final long serialVersionUID = 1170917280540937360L;

	private Long id;
	
	private String roleName;

	private String description;
	
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
	
	private Set<SysAuthority> sysAuthoritys = new HashSet<SysAuthority>(0);

	@ManyToMany(mappedBy = "sysRoles", targetEntity = SysUser.class)
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_ROLE_AUTH", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTH_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysAuthority> getSysAuthoritys() {
		return sysAuthoritys;
	}

	public void setSysAuthoritys(Set<SysAuthority> sysAuthoritys) {
		this.sysAuthoritys = sysAuthoritys;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SYS_ROLE") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Length(max = 32)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static String[] propertiesToArray() {
		return new String[]{"id", "name", "description"};
	}

}