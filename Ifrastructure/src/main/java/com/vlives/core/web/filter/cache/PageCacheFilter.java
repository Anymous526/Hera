/**
 * @(#)PageCacheFilter.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.web.filter.cache;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;
import net.sf.ehcache.constructs.web.GenericResponseWrapper;
import net.sf.ehcache.constructs.web.PageInfo;
import net.sf.ehcache.constructs.web.ResponseHeadersNotModifiableException;
import net.sf.ehcache.constructs.web.ResponseUtil;
import net.sf.ehcache.constructs.web.SerializableCookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import com.vlives.core.web.WebApplicationContextUtils;
import com.vlives.core.web.filter.cache.Cache;
import com.vlives.core.web.filter.cache.PageCacheFilter;
import com.vlives.util.ConfigUtils;
import com.vlives.util.StringUtils;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-7-19
 */

public class PageCacheFilter implements Filter {
	private ServletContext ctx;
	private AbstractUrlHandlerMapping requestMapping;
	private CacheManager cacheManager;
	private Map<String, Cache> cacheMapping = new HashMap<String, Cache>();
	private Map<String, BlockingCache> cacheMap;
	private static final String DEFAULT_CACHE_NAME = "response-cache";
	private static final String CACHE_RESOURCE = "/base/conf/ehcache.xml";
	private static final Log LOG = LogFactory.getLog(PageCacheFilter.class);
	private static final Random RANDOM = new Random();
	private PathMatcher pathMatcher = new AntPathMatcher();

	@Override
	public void init(FilterConfig config) throws ServletException {
		ctx = config.getServletContext();
		requestMapping = (AbstractUrlHandlerMapping) WebApplicationContextUtils
				.getService("requestMapping", ctx);
		createCacheMapping();
		createDefultCache();
		printLog();
	}

	private void printLog() {
		for (String url : cacheMapping.keySet()) {
			LOG.info("use controller cache : " + url);
		}
	}

	/**
	 * 创建默认的 cache
	 * 
	 * @author jianguo.xu
	 */
	private void createDefultCache() {
		URL url = ConfigUtils.class.getResource(CACHE_RESOURCE);
		cacheManager = CacheManager.create(url);
		Ehcache ehcache = cacheManager.getEhcache(DEFAULT_CACHE_NAME);
		if (ehcache == null) {
			throw new RuntimeException("response cache not exist default cache");
		}
		if (!(ehcache instanceof BlockingCache)) {
			BlockingCache newBlockingCache = new BlockingCache(ehcache);
			cacheManager.replaceCacheWithDecoratedCache(ehcache,
					newBlockingCache);
		}
		BlockingCache blockingCache = (BlockingCache) cacheManager
				.getEhcache(DEFAULT_CACHE_NAME);
		cacheMap = new HashMap<String, BlockingCache>();
		cacheMap.put(DEFAULT_CACHE_NAME, blockingCache);
	}

	/**
	 * 得到spring mvc controller对象列表
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	private Set<Object> getControllers() {
		Set<Object> controllers = new HashSet<Object>();
		for (String url : requestMapping.getHandlerMap().keySet()) {
			Object value = requestMapping.getHandlerMap().get(url);
			if (value instanceof String)
				continue;
			controllers.add(value);
		}
		return controllers;
	}

	/**
	 * 初始化 cache mapping 映射列表
	 * 
	 * @author jianguo.xu
	 */
	private void createCacheMapping() {
		Set<Object> controllers = getControllers();
		for (Object clazz : controllers) {
			Method[] methods = clazz.getClass().getDeclaredMethods();
			for (Method method : methods) {
				Cache cache = method.getAnnotation(Cache.class);
				RequestMapping methodMapping = method
						.getAnnotation(RequestMapping.class);
				if (cache == null || methodMapping == null)
					continue;

				RequestMapping classMapping = clazz.getClass().getAnnotation(
						RequestMapping.class);
				String url = fromRequestMappingInUrlMapping(classMapping,
						methodMapping);
				if (url != null)
					cacheMapping.put(url, cache);
			}
		}
	}

	/**
	 * 根据controller 类上的RequestMapping 和方法上的
	 * RequestMapping匹配出和requestMapping.getHandlerMap一致的url地址
	 * 
	 * @author jianguo.xu
	 * @param classMapping
	 * @param methodMapping
	 * @return
	 */
	private String fromRequestMappingInUrlMapping(RequestMapping classMapping,
			RequestMapping methodMapping) {
		Set<String> mappingUrls = combineMapping(classMapping, methodMapping);
		for (String url : requestMapping.getHandlerMap().keySet()) {
			for (String value : mappingUrls) {
				if (url.equals(value))
					return url;
			}
		}
		return null;
	}

	private Set<String> combineMapping(RequestMapping classMapping,
			RequestMapping methodMapping) {
		Set<String> urls = new HashSet<String>();
		if (classMapping != null) {
			String[] typeLevelPatterns = classMapping.value();
			String[] methodPatterns = methodMapping.value();
			if (typeLevelPatterns.length > 0 && methodPatterns.length > 0) {
				for (String tPaggern : typeLevelPatterns) {
					if (!tPaggern.startsWith("/")) {
						tPaggern = "/" + tPaggern;
					}
					for (String mPaggern : methodPatterns) {
						urls.add(pathMatcher.combine(tPaggern, mPaggern));
					}
				}
			}
		} else {
			String[] methodPatterns = methodMapping.value();
			for (String mPaggern : methodPatterns) {
				if (!mPaggern.startsWith("/")) {
					mPaggern = "/" + mPaggern;
				}
				urls.add(mPaggern);
			}
		}
		return urls;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if(!ConfigUtils.USE_PAGE_CACHE) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		Cache cache = getCache(httpRequest);
		if (cache == null) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		BlockingCache ehCache = fromBlockingCache(cache);
		PageInfo pageInfo = buildPageInfo(httpRequest, httpResponse, chain,
				cache, ehCache);
		if (pageInfo == null) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}

		int statusCode = pageInfo.getStatusCode();
		if (statusCode != 200) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		} else {
			writeResponse(httpRequest, httpResponse, pageInfo);
			return;
		}
	}
	/**
	 * 把缓存后的数据写入响应中
	 * @author jianguo.xu
	 * @param request
	 * @param response
	 * @param pageInfo
	 * @throws IOException
	 */
	private void writeResponse(HttpServletRequest request,
			HttpServletResponse response, PageInfo pageInfo) throws IOException {
		boolean requestAcceptsGzipEncoding = acceptsGzipEncoding(request);
		response.setBufferSize(8192);
		if (requestAcceptsGzipEncoding)
			response.setHeader("Content-Encoding", "gzip");
		setStatus(response, pageInfo);
		setHeaders(pageInfo, requestAcceptsGzipEncoding, response);
		setCookies(pageInfo, response);
		setContentType(response, pageInfo);
		writeContent(request, response, pageInfo);
	}

	protected void writeContent(HttpServletRequest request,
			HttpServletResponse response, PageInfo pageInfo)
			throws IOException, ResponseHeadersNotModifiableException {
		byte body[];
		if (acceptsGzipEncoding(request)) {
			ResponseUtil.addGzipHeader(response);
			body = pageInfo.getGzippedBody();
			if (ResponseUtil.shouldGzippedBodyBeZero(body, request))
				body = new byte[0];
		} else {
			body = pageInfo.getUngzippedBody();
		}
		boolean shouldBodyBeZero = ResponseUtil.shouldBodyBeZero(request,
				pageInfo.getStatusCode());
		if (shouldBodyBeZero)
			body = new byte[0];
		response.setContentLength(body.length);
		OutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(body);
		out.flush();
	}

	protected void setContentType(HttpServletResponse response,
			PageInfo pageInfo) {
		response.setContentType(pageInfo.getContentType());
	}

	protected void setCookies(PageInfo pageInfo, HttpServletResponse response) {
		Collection<?> cookies = pageInfo.getSerializableCookies();
		javax.servlet.http.Cookie cookie;
		for (Iterator<?> iterator = cookies.iterator(); iterator.hasNext(); response
				.addCookie(cookie))
			cookie = ((SerializableCookie) iterator.next()).toCookie();

	}

	protected void setHeaders(PageInfo pageInfo,
			boolean requestAcceptsGzipEncoding, HttpServletResponse response) {
		Collection<?> headers = pageInfo.getHeaders();
		 
		String headerPair[];
		for (Iterator<?> iterator = headers.iterator(); iterator.hasNext(); response
				.setHeader(headerPair[0], headerPair[1]))
			headerPair = (String[]) iterator.next();

	}

	protected void setStatus(HttpServletResponse response, PageInfo pageInfo) {
		response.setStatus(pageInfo.getStatusCode());
	}

	private boolean acceptsGzipEncoding(HttpServletRequest request) {

		for (@SuppressWarnings("unchecked")
		Enumeration<String> accepted = request.getHeaders("Accept-Encoding"); accepted
				.hasMoreElements();) {
			String headerValue = accepted.nextElement();
			if (headerValue.indexOf("gzip") != -1)
				return true;
		}
		return false;
	}
	/**
	 * 得到页面缓存信息
	 * @author jianguo.xu
	 * @param request
	 * @param response
	 * @param chain
	 * @param cache
	 * @param ehCache
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected PageInfo buildPageInfo(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain, Cache cache,
			BlockingCache ehCache) throws IOException, ServletException {
		PageInfo pageInfo = null;
		String key = calculateKey(request, cache);
		Element element = ehCache.get(key);
		if (element != null && element.getObjectValue() != null) {
			pageInfo = (PageInfo) element.getObjectValue();
		} else {
			pageInfo = buildPage(request, response, chain);
			if (pageInfo.isOk()) {
				ehCache.put(new Element(key, pageInfo));
			} else {
				ehCache.put(new Element(key, null));
			}
		}
		return pageInfo;
	}
	/**
	 * 创建页面缓存信息
	 * @author jianguo.xu
	 * @param request
	 * @param response
	 * @param chain
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	private PageInfo buildPage(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ByteArrayOutputStream outstr = new ByteArrayOutputStream();
		GenericResponseWrapper wrapper = new GenericResponseWrapper(response,
				outstr);
		chain.doFilter(request, wrapper);
		wrapper.flush();
		return new PageInfo(wrapper.getStatus(), wrapper.getContentType(),
				wrapper.getHeaders(), wrapper.getCookies(),
				outstr.toByteArray(), true);
	}
	/**
	 * 计算cache 的key
	 * @author jianguo.xu
	 * @param request
	 * @param cache
	 * @return
	 */
	private String calculateKey(HttpServletRequest request, Cache cache) {
		StringBuffer stringBuffer = new StringBuffer(request.getRequestURI());
		String queryString = request.getQueryString();
		if (!cache.ignoreParams() && !StringUtils.isNullOrEmpty(queryString))
			stringBuffer.append("?").append(queryString.trim());
		if (cache.random() != 0)
			stringBuffer.append(";random=").append(
					RANDOM.nextInt(cache.random()));
		String key = stringBuffer.toString();
		return key;
	}
	/**
	 * 根据请求地址得到 cache
	 * @author jianguo.xu
	 * @param request
	 * @return
	 */
	private Cache getCache(HttpServletRequest request) {
		String urlPath = request.getRequestURI();
		Cache cache = cacheMapping.get(urlPath);
		if (cache == null) {
			// Pattern match?
			List<String> matchingPatterns = new ArrayList<String>();
			for (String registeredPattern : cacheMapping.keySet()) {
				if (pathMatcher.match(registeredPattern, urlPath)) {
					matchingPatterns.add(registeredPattern);
				}
			}
			String bestPatternMatch = null;
			Comparator<String> patternComparator = pathMatcher.getPatternComparator(urlPath);
			if (!matchingPatterns.isEmpty()) {
				Collections.sort(matchingPatterns, patternComparator);
				bestPatternMatch = matchingPatterns.get(0);
			}
			if(bestPatternMatch == null) return null;
			cache = cacheMapping.get(bestPatternMatch);
			if(cache == null) return null;
		}
		if (StringUtils.isNullOrEmpty(cache.except()))
			return cache;
		@SuppressWarnings("unchecked")
		Enumeration<String> names = request.getParameterNames();
		if (names != null) {
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if (name.equals(cache.except()))
					return null;
			}
		}
		return cache;

	}

	/**
	 * 根据cacheMap中key得到BlockingCache 如果BlockingCache不存在就新建BlockingCache
	 * 
	 * @author jianguo.xu
	 * @param cache
	 * @return
	 */
	private BlockingCache fromBlockingCache(Cache cache) {
		BlockingCache ehcache = cacheMap.get(cache.cacheName());
		if (ehcache != null)
			return ehcache;
		Ehcache newEhcache = cacheManager.getEhcache(cache.cacheName());
		if (!(newEhcache instanceof BlockingCache)) {
			BlockingCache newBlockingCache = new BlockingCache(newEhcache);
			cacheManager.replaceCacheWithDecoratedCache(newEhcache,
					newBlockingCache);
		}
		BlockingCache blockingCache = (BlockingCache) cacheManager
				.getEhcache(cache.cacheName());
		cacheMap.put(cache.cacheName(), blockingCache);
		return blockingCache;
	}

	@Override
	public void destroy() {

	}

}
