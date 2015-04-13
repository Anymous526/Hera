package com.justinmobile.security.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManagerImpl;
import com.justinmobile.security.dao.SysMenuDao;
import com.justinmobile.security.dao.SysUserDao;
import com.justinmobile.security.domain.SysAuthority;
import com.justinmobile.security.domain.SysMenu;
import com.justinmobile.security.domain.SysRole;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SysMenuManager;

@Service("sysMenuManager")
public class SysMenuManagerImpl extends EntityPageManagerImpl<SysMenu, SysMenuDao> implements SysMenuManager {
	
	private SysUserDao sysUserDao;
	
	@SuppressWarnings("unused")
	private SysMenuDao sysMenuDao;
	
	public void setSysMenuDao(SysMenuDao sysMenuDao) {
		this.sysMenuDao = sysMenuDao;
	}

	/**
	 * 查询属于用户的所有菜单
	 */
	public List<SysMenu> queryMunesByUser(String username) throws Exception {		
		Set<SysMenu> menus= new HashSet<SysMenu>();
		List<SysMenu> menuslist= new ArrayList<SysMenu>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", username);
		List<SysUser> userList = sysUserDao.findBy(map);
		if(userList.size()<1){
			throw new BusinessException("user is not found by user's name!");
		}
		SysUser user = userList.get(0);
		Set<SysAuthority> auths = new HashSet<SysAuthority>();
		Set<SysRole> roles = user.getSysRoles();
		for(SysRole role:roles){
			auths.addAll(role.getSysAuthoritys());
		}
		for(SysAuthority auth:auths){
			 menus.addAll(auth.getSysMenus());
		} 
		for(SysMenu m: menus){
			menuslist.add(m);
		}
		return menuslist;
	}
	
	public Page<SysMenu> pagedQueryParentMenus(int pageNo, int pageSize){		
		StringBuffer hql = new StringBuffer("from " + SysMenu.class.getName() + " as entity ");
		hql.append(" where entity.id in (select m.parent.id from SysMenu m where m.parent is not null ) order by entity.id ");
		return pagedQuery(hql.toString(), pageNo, pageSize);
	}
	

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
}
