package com.justinmobile.security.resourcedetails;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;
import org.springframework.util.Assert;

import com.justinmobile.security.domain.SysResource;

public class Resource implements ResourceDetails {

	private static final long serialVersionUID = 3446385469869507484L;
	
	private String resourceString;
	
	private String resourceType;
	
	private GrantedAuthority[] authorities;
	
	public Resource(String resourceString, String resourceType,
			GrantedAuthority[] authorities) {
		if (StringUtils.isEmpty(resourceString) || StringUtils.isEmpty(resourceType)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
		if (!(SysResource.URL_TYPE.equals(resourceType) 
				|| SysResource.METHOD_TYPE.equals(resourceType))) {
			throw new IllegalArgumentException("Cannot pass type not in {URL, METHOD}");
		}
		this.resourceString = resourceString;
		this.resourceType = resourceType;
		setAuthorities(authorities);
	}

	public String getResourceString() {
		return this.resourceString;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public GrantedAuthority[] getAuthorities() {
		return this.authorities;
	}
	
    protected void setAuthorities(GrantedAuthority[] authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");
        // Ensure array iteration order is predictable (as per ResourceDetails.getAuthorities() contract and SEC-xxx)
        SortedSet<GrantedAuthority> sorter = new TreeSet<GrantedAuthority>();
        for (int i = 0; i < authorities.length; i++) {
            Assert.notNull(authorities[i],
                "Granted authority element " + i + " is null - GrantedAuthority[] cannot contain any null elements");
            sorter.add(authorities[i]);
        }
        
        this.authorities = (GrantedAuthority[]) sorter.toArray(new GrantedAuthority[sorter.size()]);
    }

	@Override
	public int hashCode() {
		int code = 168;
        if (getAuthorities() != null) {
            for (int i = 0; i < getAuthorities().length; i++)
                code *= getAuthorities()[i].hashCode() % 7;
        }
        if (getResourceString() != null)
            code *= getResourceString().hashCode() % 7;
        return code;
	}

	@Override
	public boolean equals(Object rhs) {
		if (!(rhs instanceof User) || (rhs == null)) {
			return false;
		}
		
		final Resource res = (Resource) rhs;
		
		// We rely on constructor to guarantee any Resource has non-null and >0
        // authorities
		if (!(res.getAuthorities().length == getAuthorities().length 
				&& Arrays.equals(authorities, res.authorities))) {
			return false;
		}
		
		// We rely on constructor to guarantee non-null resourceString and resourceType
		if (!(this.resourceString.equals(res.resourceString) 
				&& this.resourceType.equals(res.resourceType))) {
			return false;
		}
		return true;
	}

}
