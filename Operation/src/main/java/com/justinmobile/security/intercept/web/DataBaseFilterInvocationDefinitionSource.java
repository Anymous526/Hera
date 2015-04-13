package com.justinmobile.security.intercept.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.util.UrlMatcher;
import org.springframework.util.Assert;

import com.justinmobile.security.dao.SysResourceDao;
import com.justinmobile.security.domain.SysAuthority;
import com.justinmobile.security.domain.SysResource;
import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.resourcedetails.ResourceDetails;

/**
 * 拦截访问的URL
 * 
 * @author peak
 * 
 */
public class DataBaseFilterInvocationDefinitionSource implements InitializingBean,
		FilterInvocationDefinitionSource {

	private final Log logger = LogFactory.getLog(getClass());

	private final String defaultParam = "method";

	private UrlMatcher urlMatcher;

	private boolean stripQueryStringFromUrls = true;

	private List<String> retainParameters = new ArrayList<String>();

	private SecurityCacheManager securityCacheManager;

	private SysResourceDao sysResourceDao;

	public void setSysResourceDao(SysResourceDao sysResourceDao) {
		this.sysResourceDao = sysResourceDao;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.securityCacheManager, "A SecurityCacheManager is required");
		securityCacheManager.initResourceInCache();
	}

	public DataBaseFilterInvocationDefinitionSource(SecurityCacheManager securityCacheManager,
			List<String> retainParameters) {
		this.securityCacheManager = securityCacheManager;
		if (CollectionUtils.isEmpty(retainParameters)) {
			this.retainParameters.add(defaultParam);
		} else {
			this.retainParameters.addAll(retainParameters);
		}
	}

	public ConfigAttributeDefinition getAttributes(Object object) throws IllegalArgumentException {
		if ((object == null) || !this.supports(object.getClass())) {
			throw new IllegalArgumentException("Object must be a FilterInvocation");
		}

		String url = ((FilterInvocation) object).getRequestUrl();

		return lookupAttributes(url);
	}

	public ConfigAttributeDefinition lookupAttributes(String url) {

		// 仅保留必要的url，删除多余的url信息
		url = stripUrl(url);

		if (urlMatcher.requiresLowerCaseUrl()) {
			url = url.toLowerCase();

			if (logger.isDebugEnabled()) {
				logger.debug("Converted URL to lowercase, from: '" + url + "'; to: '" + url + "'");
			}
		}

		return lookupUrlInCache(securityCacheManager.getUrlResources(), url);
	}

	private String stripUrl(String url) {
		StringBuffer resultUrl = new StringBuffer(url);
		if (stripQueryStringFromUrls) {
			StringBuffer paramUrl = new StringBuffer();
			// Strip anything after a question mark symbol, as per SEC-161. See
			// also SEC-321
			int firstQuestionMarkIndex = resultUrl.indexOf("?");
			if (firstQuestionMarkIndex != -1) {
				resultUrl.delete(firstQuestionMarkIndex, resultUrl.length());
				if (CollectionUtils.isNotEmpty(retainParameters)) {
					String remainUrl = url.substring(firstQuestionMarkIndex + 1);
					if (StringUtils.isNotEmpty(remainUrl)) {
						String[] params = remainUrl.toString().split("&");
						if (!(params == null || params.length == 0)) {
							for (String str : params) {
								for (String param : retainParameters) {
									if (str.indexOf(param + "=") == 0) {
										paramUrl.append(str + "&");
									}
								}
							}
						}
					}
					if (paramUrl.length() > 0) {
						paramUrl.deleteCharAt(paramUrl.length() - 1);
						resultUrl.append("?" + paramUrl);
					}
				}
			}
		}
		return resultUrl.toString();
	}

	private ConfigAttributeDefinition lookupUrlInCache(List<String> urlResources, String url) {

		if (CollectionUtils.isEmpty(urlResources)) {
			List<SysResource> recs = sysResourceDao.findAll();
			for (SysResource rec : recs) {
				urlResources.add(rec.getFilterString());
			}
		}

		// 倒叙，将带*号的排后面
		Collections.sort(urlResources);
		Collections.reverse(urlResources);
		Iterator<String> urls = urlResources.iterator();
		boolean lowerCase = urlMatcher.requiresLowerCaseUrl();
		while (urls.hasNext()) {
			String urlResource = urls.next();
			String urlMatch = lowerCase?urlResource.toLowerCase():urlResource;
			boolean matched = urlMatcher.pathMatchesUrl(urlMatch, url);

			if (logger.isDebugEnabled()) {
				logger.debug("Candidate is: '" + url + "'; pattern is " + urlResource + "; matched="
						+ matched);
			}

			if (matched) {
				ResourceDetails detail = securityCacheManager.getResourcDetailFromCache(urlResource);
				if (null != detail) {
					return (ConfigAttributeDefinition) authorityToConfig(detail.getAuthorities());
				}
				SysResource sr = sysResourceDao.findByProperty("filterString", urlResource).get(0);
				Set<SysAuthority> authSet = sr.getSysAuthorities();
				return sysAurhorityToConfig(authSet);
			}
		}

		return null;
	}

	/**
	 * 将SysAuthority加入到ConfigAttributeDefinition中
	 * 
	 * @param authSet
	 * @return
	 */
	private ConfigAttributeDefinition sysAurhorityToConfig(Set<SysAuthority> authSet) {

		ConfigAttributeEditor attributeEditor = new ConfigAttributeEditor();
		StringBuffer buffer = new StringBuffer();
		for (SysAuthority auth : authSet) {
			buffer.append(auth.getAuthName() + ",");
		}
		if (buffer.length() > 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		attributeEditor.setAsText(buffer.toString());
		return (ConfigAttributeDefinition) attributeEditor.getValue();

	}

	/**
	 * 将grantedAuthorities加入到ConfigAttributeDefinition中
	 * 
	 * @param grantedAuthorities
	 * @return
	 */
	private ConfigAttributeDefinition authorityToConfig(GrantedAuthority[] grantedAuthorities) {
		ConfigAttributeEditor attributeEditor = new ConfigAttributeEditor();
		StringBuffer buffer = new StringBuffer();
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			buffer.append(grantedAuthority.getAuthority() + ",");
		}
		if (buffer.length() > 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		attributeEditor.setAsText(buffer.toString());
		return (ConfigAttributeDefinition) attributeEditor.getValue();
	}

	public Collection<ConfigAttributeDefinition> getConfigAttributeDefinitions() {
		GrantedAuthority[] grantedAuthorities = securityCacheManager.getAllGrantedAuthority();
		if (grantedAuthorities != null) {
			Collection<ConfigAttributeDefinition> c = new HashSet<ConfigAttributeDefinition>();
			ConfigAttributeDefinition config = authorityToConfig(grantedAuthorities);
			c.add(config);
			logger.info("System has GrantedAuthority "
					+ ArrayUtils.toString(config.getConfigAttributes().toArray()));
			return Collections.unmodifiableCollection(c);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	public boolean isConvertUrlToLowercaseBeforeComparison() {
		return urlMatcher.requiresLowerCaseUrl();
	}

	public void setStripQueryStringFromUrls(boolean stripQueryStringFromUrls) {
		this.stripQueryStringFromUrls = stripQueryStringFromUrls;
	}

	public void setUrlMatcher(UrlMatcher urlMatcher) {
		this.urlMatcher = urlMatcher;
	}

}
