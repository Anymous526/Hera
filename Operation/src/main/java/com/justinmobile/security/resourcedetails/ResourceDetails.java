package com.justinmobile.security.resourcedetails;

import java.io.Serializable;

import org.springframework.security.GrantedAuthority;

/**
 * 仿照UserDetails.class编写的资源部分
 * 用处在缓存上
 * @author peak
 *
 */
public interface ResourceDetails extends Serializable {

	/** 获得资源的字符串 */
	String getResourceString();
	/** 获得资源的类型：URL,METHOD */
	String getResourceType();
	/** 资源对应的权限 */
	GrantedAuthority[] getAuthorities();

}
