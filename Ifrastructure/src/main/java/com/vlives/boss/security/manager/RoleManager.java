/**
 * @(#)ResourceManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.security.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.security.domain.Resource;
import com.vlives.boss.security.domain.Role;
import com.vlives.boss.security.domain.RoleResource;
import com.vlives.boss.security.domain.Role.RoleGroup;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface RoleManager {
	public void create(Role role);
	public Role get(Long id);
	public List<Role> findAll();
	public void delete(Role role);
	/**
	 * 规则绑定资源
	 * @author jianguo.xu
	 * @param role
	 * @param resource
	 */
	public void bindResource(Role role,Resource resource);
	/**
	 * 规则解除与资源的绑定
	 * @author jianguo.xu
	 * @param roleResource
	 */
	public void unBindResource(RoleResource roleResource);
	/**
	 * 得到管理员的规则组集合
	 * @author jianguo.xu
	 * @return
	 */
	public Map<RoleGroup,List<Role>> findRoleGroups();
}
