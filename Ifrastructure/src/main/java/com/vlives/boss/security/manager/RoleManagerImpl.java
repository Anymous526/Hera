/**
 * @(#)RoleManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.security.manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.security.domain.Resource;
import com.vlives.boss.security.domain.Role;
import com.vlives.boss.security.domain.RoleResource;
import com.vlives.boss.security.domain.Role.RoleGroup;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.PropertiesFinder;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-8
 */
@Service("roleManager")
public class RoleManagerImpl implements RoleManager{
	@javax.annotation.Resource
	private BaseDao<Role, Long> roleDao;
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(Role role) {
		roleDao.save(role);
		
	}

	@Override
	@Transactional(readOnly = true,  propagation = Propagation.SUPPORTS)
	public Role get(Long id) {
		return roleDao.get(id);
	}

	@Override
	@Transactional(readOnly = true,  propagation = Propagation.SUPPORTS)
	public List<Role> findAll() {
		return roleDao.find(new PropertiesFinder(Role.class));
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void bindResource(Role role, Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void unBindResource(RoleResource roleResource) {
		// TODO Auto-generated method stub
		
	}
	@Override
	@Transactional(readOnly = true,  propagation = Propagation.SUPPORTS)
	public Map<RoleGroup,List<Role>> findRoleGroups() {
		Map<RoleGroup,List<Role>> map = new LinkedHashMap<RoleGroup,List<Role>>();
		List<Role> roles = findAll();
		for (RoleGroup roleGroup : RoleGroup.valuesBySort()) {
			List<Role> items = getRoleGroup(roleGroup,roles);
			if(items.size()>0){
				map.put(roleGroup, items);
			}
		}
		return map;
	}
	
	@Transient
	private List<Role> getRoleGroup(RoleGroup roleGroup,List<Role> roles) {
		List<Role> items = new ArrayList<Role>();
		for(Role role :  roles) {
			if(role.getRoleGroup() == roleGroup) {
				items.add(role);
			}
		}
		return items;
	}

}
