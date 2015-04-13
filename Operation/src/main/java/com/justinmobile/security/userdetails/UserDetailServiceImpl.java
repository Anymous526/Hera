package com.justinmobile.security.userdetails;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.justinmobile.security.domain.SysAuthority;
import com.justinmobile.security.domain.SysResource;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SysAuthorityManager;
import com.justinmobile.security.manager.SysUserManager;

/**
 * 实现SpringSecurity的UserDetailsService接口
 * 获取用户信息和用户对应的权限，进行认证
 * @author peak
 *
 */
public class UserDetailServiceImpl implements UserDetailsService {
	
	private SysUserManager sysUserManager;
	
	private SysAuthorityManager sysAuthorityManager;

	public void setSysAuthorityManager(SysAuthorityManager sysAuthorityManager) {
		this.sysAuthorityManager = sysAuthorityManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		SysUser sysUser = sysUserManager.getUserByLoginName(userName);
		if (sysUser == null) {
			throw new UsernameNotFoundException(userName + " not found");
		}
		List<SysAuthority> sysAuthorities = sysAuthorityManager.getSysAuthorityByUser(userName);
		Set<SysAuthority> set = new HashSet<SysAuthority>();
		set.addAll(sysAuthorities);
		for (SysAuthority sysAuthority : sysAuthorities) {
			Set<SysResource> resource = sysAuthority.getSysResources();
			for (SysResource sysResource : resource) {
				String str = sysResource.getFilterString();
				if (str.indexOf("*") == str.length() - 1) {
					List<SysAuthority> containAuths = sysAuthorityManager.getSysAuthorityByResource(str);
					set.addAll(containAuths);
				}
			}
			
		}
		
		UserWithSalt userDetail = new UserWithSalt(sysUser.getUserName(), sysUser.getPassword(), sysUser.isEnable(), 
                true, true, true, SysAuthority.toGrantedAuthority(set),sysUser.getSalt());

		return userDetail;
	}
}
