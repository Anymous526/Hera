/**
 * @(#)ResourceManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.security.manager;

import java.util.List;

import com.vlives.boss.security.domain.Resource;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface ResourceManager {
	
	public void create(String uri,String desc);
	public Resource get(Long id);
	public List<Resource> findAll();
	public void delete(Resource resource);
}
