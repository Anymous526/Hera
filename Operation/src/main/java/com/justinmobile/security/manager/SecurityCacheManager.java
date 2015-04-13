package com.justinmobile.security.manager;

import java.util.List;

import org.springframework.security.GrantedAuthority;

import com.justinmobile.security.resourcedetails.ResourceDetails;

/**
 * 缓存中资源的管理等操作
 * @author peak
 *
 */
public interface SecurityCacheManager {

	public void initResourceInCache();

	public List<String> getUrlResources();
	
	public List<String> getMethodResources();

	public ResourceDetails getResourcDetailFromCache(String resourceString);
	
	public GrantedAuthority[] getAllGrantedAuthority();
	
	public void reloadResourceInCache();

}
