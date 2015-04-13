package com.justinmobile.bmp.common.log.manager.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.common.log.dao.LogDao;
import com.justinmobile.bmp.common.log.domain.OperationLog;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.bmp.util.SpringSecurityUtils;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManagerImpl;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SysUserManager;

@Service("logManager")
public class LogManagerImpl extends EntityPageManagerImpl<OperationLog, LogDao>
		implements LogManager {
	@Autowired
	private LogDao logDao;
	@Autowired
	private SysUserManager sysUserManager;

	public void insertLog(String opDescription) throws BusinessException {
		// TODO Auto-generated method stub
		String userName = SpringSecurityUtils.getCurrentUserName();
		SysUser sysUser = sysUserManager.getUserByLoginName(userName);
		OperationLog log = new OperationLog();
		log.setOpId(sysUser.getId());
		log.setOpName(sysUser.getUserName());
		log.setOpMobile(sysUser.getMobile());
		log.setOpDescription(opDescription);
		log.setOpTime(Calendar.getInstance());
		logDao.save(log);
	}
}
