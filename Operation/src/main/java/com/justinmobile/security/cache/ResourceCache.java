package com.justinmobile.security.cache;

import java.util.List;

import com.justinmobile.security.resourcedetails.ResourceDetails;

/**
 * 仿照UserCache.class写的接口
 * 实现将资源放入到缓存中
 * @author peak
 *
 */
public interface ResourceCache {
	
	/**
	 * 获得所有Url的资源
	 * @return
	 */
	List<String> getUrlResources();
	
	/**
	 * 获得所有Method的资源
	 * @return
	 */
	List<String> getMethodResources();
	
	/**
	 * 从缓存中获得资源
	 * @param resourceString
	 * @return
	 */
	ResourceDetails getResourceFromCache(String resourceString);

	/**
	 * 将资源加入到缓存中
	 * @param resource
	 */
    void putResourceInCache(ResourceDetails resource);

    /**
     * 从缓存中删除资源
     * @param resourceString
     */
    void removeResourceFromCache(String resourceString);

}
