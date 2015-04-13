/**
 * @(#)AreaManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.area.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vlives.boss.area.domain.Area;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.exception.BusinessException;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-3-25
 */
@Service("areaManager")
public class AreaManagerImpl implements AreaManager{
	@Resource
	private BaseDao<Area,Long> areaDao;
	@Override
	public Area get(Long id) {
		return areaDao.get(id);
	}

	@Override
	public List<Area> getChildren(Area parent) throws BusinessException {
		PropertiesFinder finder = new PropertiesFinder(Area.class,"parentArea",parent);
		finder.setCacheable(true);
		return areaDao.find(finder);
	}
	
}
