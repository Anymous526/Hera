package com.justinmobile.security.manager.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.dao.CityDao;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManagerImpl;
import com.justinmobile.security.dao.SysUserDao;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SysUserManager;

@Service("sysUserManager")
public class SysUserManagerImpl extends EntityPageManagerImpl<SysUser, SysUserDao> implements SysUserManager {
	
	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private CityDao cityDao;
	
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public SysUser getUserByLoginName(String loginName) throws BusinessException {
	
		SysUser user = null;
		try {
			 user = sysUserDao.findUniqueEntity("from " + SysUser.class.getName()
					+ " as user where user.userName = ?", loginName);
		} catch (HibernateException e) {
			throw new BusinessException(e.getMessage());
			 
		}
		return user;
	}
	
	/**
	 * 将密码加密保存到用户
	 * @param user
	 * @param password
	 * @return
	 */
	public SysUser encodeWithSalt(SysUser user,String password){
		
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		// false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true
		// 表示：生成24位的Base64版
		md5.setEncodeHashAsBase64(false);
		//新的密码种子
		String salt = String.valueOf(new Random(Long.MAX_VALUE).nextLong());
		String pwd = md5.encodePassword(password, salt);
		user.setSalt(salt);
		user.setPassword(pwd);
		return user;
	}

	@Override
	public void setCities(SysUser user, String[] cityIds) throws PlatformException {
		try {
			Set<City> set = new HashSet<City>();
			for(String cityId : cityIds){
				City city = this.cityDao.get(Long.valueOf(cityId));
				set.add(city);
			}
			user.setCitySet(set);
			this.sysUserDao.save(user);
		} catch (NumberFormatException e) {
			throw new PlatformException(PlatformErrorCode.PARAM_ERROR , e);
		} catch (HibernateException e) {
			throw new PlatformException(PlatformErrorCode.DB_ERROR , e);
		}
		
	}
}
