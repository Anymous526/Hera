package com.justinmobile.security.manager.impl;

import org.springframework.stereotype.Service;

import com.justinmobile.core.manager.EntityPageManagerImpl;
import com.justinmobile.security.dao.SysResourceDao;
import com.justinmobile.security.domain.SysResource;
import com.justinmobile.security.manager.SysResourceManager;

@Service("sysResourceManager")
public class SysResourceManagerImpl extends EntityPageManagerImpl<SysResource, SysResourceDao> implements SysResourceManager {
	
	@SuppressWarnings("unused")
	private SysResourceDao sysResourceDao;

	public void setSysResourceDao(SysResourceDao sysResourceDao) {
		this.sysResourceDao = sysResourceDao;
	}


}
