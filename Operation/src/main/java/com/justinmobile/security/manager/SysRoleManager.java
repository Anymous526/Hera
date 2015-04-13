package com.justinmobile.security.manager;

import com.justinmobile.core.manager.EntityPageManager;
import com.justinmobile.security.domain.SysRole;

public interface SysRoleManager extends EntityPageManager<SysRole> {

	public SysRole getRoleByName(String roleName);
}
