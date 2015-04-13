package com.justinmobile.security.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

import com.justinmobile.core.domain.AbstractEntity;
/**
 * @author peak
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu extends AbstractEntity {

	private static final long serialVersionUID = 1147606384496817951L;

	/** 主键 */
	private Long id;

	/** 名称 */
	private String menuName;

	/** url */
	private String url;
	
	/** 显示顺序 */
	private Integer orderNo;

	/** 父MENU */
	private SysMenu parent;

	/** 包含子MENU */
	private Set<SysMenu> childMenu;
	
	/** 对应权限 */
	private Set<SysAuthority> sysAuthority = new HashSet<SysAuthority>(0);
 
	@ManyToOne
	@JoinColumn(name = "PARENTID")
	@Cascade(value={CascadeType.PERSIST, CascadeType.MERGE})
	public SysMenu getParent() {
		return parent;
	}

	public void setParent(SysMenu parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	@Cascade(value = CascadeType.ALL)
	public Set<SysMenu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(Set<SysMenu> childMenu) {
		this.childMenu = childMenu;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SYS_MENU") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Length(max = 32)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Length(max = 100)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_AUTH_MENU", joinColumns = { @JoinColumn(name = "MENU_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTH_ID") })
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysAuthority> getSysAuthority() {
		return sysAuthority;
	}

	public void setSysAuthority(Set<SysAuthority> sysAuthority) {
		this.sysAuthority = sysAuthority;
	}
	
	@NotNull
	@Column(columnDefinition ="NUMBER default 0") 
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


}
