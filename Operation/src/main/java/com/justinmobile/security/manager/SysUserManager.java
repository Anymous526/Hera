package com.justinmobile.security.manager;

import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManager;
import com.justinmobile.security.domain.SysUser;

public interface SysUserManager extends EntityPageManager<SysUser> {
	
	SysUser getUserByLoginName(String loginName) throws BusinessException;
	SysUser encodeWithSalt(SysUser user,String password);
	void setCities(SysUser user, String[] cityIds) throws PlatformException;
}
