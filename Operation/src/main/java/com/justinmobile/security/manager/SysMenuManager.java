package com.justinmobile.security.manager;

import java.util.List;


import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.manager.EntityPageManager;

import com.justinmobile.security.domain.SysMenu;

public interface SysMenuManager extends EntityPageManager<SysMenu> {
	
	
	/**
	 * 查询指定菜单下的指定用户的菜单
	 * @param id 菜单ID
	 * @param userid 用户ID
	 * @return 菜单列表
	 * @throws Exception
	 */
	public List<SysMenu> queryMunesByUser(String username) throws Exception;

	/**
	 * 查询有子菜单的所有菜单
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<SysMenu> pagedQueryParentMenus(int pageNo, int pageSize);
	
}
