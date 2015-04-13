package com.justinmobile.security.manager.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.GrantedAuthority;

import com.justinmobile.security.cache.EhCacheBaseResourceCache;
import com.justinmobile.security.cache.ResourceCache;
import com.justinmobile.security.dao.SysAuthorityDao;
import com.justinmobile.security.dao.SysResourceDao;
import com.justinmobile.security.domain.SysAuthority;
import com.justinmobile.security.domain.SysResource;
import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.resourcedetails.Resource;
import com.justinmobile.security.resourcedetails.ResourceDetails;

public class SecurityCacheManagerImpl implements SecurityCacheManager {

	private ResourceCache resourceCache;
	
	private SysResourceDao sysResourceDao;
	
	private SysAuthorityDao sysAuthorityDao;

	public void initResourceInCache() {
		if (CollectionUtils.isEmpty(resourceCache.getUrlResources())) {
			List<SysResource> resources = sysResourceDao.findAll();
			if (CollectionUtils.isNotEmpty(resources)) {
				for (SysResource resource : resources) {
					Set<SysAuthority> sysAuthorities = resource.getSysAuthorities();
					if (CollectionUtils.isNotEmpty(sysAuthorities)) {
						this.putResourceDetailInCache(resource);
					}
				}
			}
		}
	}
	
	private void putResourceDetailInCache(SysResource resource) {
		GrantedAuthority[] authorities = SysAuthority.toGrantedAuthority(resource.getSysAuthorities());
		ResourceDetails resourcDetail = new Resource(resource.getFilterString(),
				resource.getType(), authorities);
		resourceCache.putResourceInCache(resourcDetail);
	}
	
	public void clearResourceInCache() {
		List<String> resources = resourceCache.getUrlResources();
		if (CollectionUtils.isNotEmpty(resources)) {
			for (String resourceString : resources) {
				resourceCache.removeResourceFromCache(resourceString);
			}
		}
	}
	
	public void reloadAuthentication() {
//		Authentication authentication = SecurityContextHolder.
	}
	
	public void reloadResourceInCache() {
		this.clearResourceInCache();
		this.initResourceInCache();
	}

	public List<String> getMethodResources() {
		return resourceCache.getMethodResources();
	}

	public ResourceDetails getResourcDetailFromCache(String resourceString) {
		return resourceCache.getResourceFromCache(resourceString);
	}

	public List<String> getUrlResources() {
		return resourceCache.getUrlResources();
	}

	public void setResourcCache(EhCacheBaseResourceCache resourceCache) {
		this.resourceCache = resourceCache;
	}

	public void setSysResourceDao(SysResourceDao sysResourceDao) {
		this.sysResourceDao = sysResourceDao;
	}

	public void setResourceCache(ResourceCache resourceCache) {
		this.resourceCache = resourceCache;
	}

	public GrantedAuthority[] getAllGrantedAuthority() {
		List<SysAuthority> list = sysAuthorityDao.findAll();
		if (CollectionUtils.isNotEmpty(list)) {
			return SysAuthority.toGrantedAuthority(list);
		}
		return null;
	}

	public void setSysAuthorityDao(SysAuthorityDao sysAuthorityDao) {
		this.sysAuthorityDao = sysAuthorityDao;
	}

}
