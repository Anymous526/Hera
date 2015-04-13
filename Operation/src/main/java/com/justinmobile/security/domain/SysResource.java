package com.justinmobile.security.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.NotEmpty;

import com.justinmobile.core.domain.AbstractEntity;

@Entity
@Table(name = "SYS_RESOURCE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysResource extends AbstractEntity {

	private static final long serialVersionUID = -635789819157263051L;
	
	public static final String URL_TYPE = "URL";
	
	public static final String METHOD_TYPE = "METHOD";

	private Long id;
	/** 类型：URL、METHOD */
	private String type;
	/** 资源串 */
	private String filterString;
	/** 资源名称 */
	private String resName;
	
	private Set<SysAuthority> sysAuthorities = new HashSet<SysAuthority>(0);

	@ManyToMany(mappedBy = "sysResources", targetEntity = SysAuthority.class)
	@Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
	public Set<SysAuthority> getSysAuthorities() {
		return sysAuthorities;
	}

	public void setSysAuthorities(Set<SysAuthority> sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SYS_RESOURCE") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@NotEmpty
	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}
	
	public static String[] propertiesToArray() {
		return new String[]{"id", "type", "filterString"};
	}

	@NotEmpty
	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

}