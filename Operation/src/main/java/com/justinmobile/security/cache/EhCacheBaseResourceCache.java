package com.justinmobile.security.cache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.providers.dao.cache.EhCacheBasedUserCache;
import org.springframework.util.Assert;

import com.justinmobile.security.domain.SysResource;
import com.justinmobile.security.resourcedetails.ResourceDetails;

public class EhCacheBaseResourceCache implements ResourceCache, InitializingBean {

    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(EhCacheBasedUserCache.class);

    //~ Instance fields ================================================================================================

    private Ehcache cache;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cache, "cache mandatory");
    }

    public Ehcache getCache() {
        return cache;
    }
    
    public void setCache(Ehcache cache) {
        this.cache = cache;
    }
    
	public List<String> getUrlResources() {
		return getResourcesByType(SysResource.URL_TYPE);
	}
	public List<String> getMethodResources() {
		return getResourcesByType(SysResource.METHOD_TYPE);
	}

	/**
	 * 根据类型得到所有的资源
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<String> getResourcesByType(String type) {
		List<String> resources = new ArrayList<String>();
		List<String> keysList = this.cache.getKeys();
		for (String resourceString : keysList) {
			ResourceDetails detail = getResourceFromCache(resourceString);
			if (detail != null && detail.getResourceType().equals(type)) {
				resources.add(detail.getResourceString());
			}
		}
		return resources;
	}

	public ResourceDetails getResourceFromCache(String resourceString) {
		Element element = null;

		try {
			element = cache.get(resourceString);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("Cache failure: "
					+ cacheException.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Cache hit: " + (element != null) + "; resourceString: "
					+ resourceString);
		}

		if (element == null) {
			return null;
		} else {
			return (ResourceDetails) element.getValue();
		}
	}
	public void putResourceInCache(ResourceDetails resource) {
		Element element = new Element(resource.getResourceString(), resource);

        if (logger.isDebugEnabled()) {
            logger.debug("Cache put: " + element.getKey());
        }

        cache.put(element);
	}
	public void removeResourceFromCache(String resourceString) {
		if (logger.isDebugEnabled()) {
            logger.debug("Cache remove: " + resourceString);
        }

		cache.remove(resourceString);
	}

}
