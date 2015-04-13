package com.justinmobile.bmp.cloudboss.sms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.cloudboss.sms.dao.SalePloyAgentInfoDao;
import com.justinmobile.bmp.cloudboss.sms.domain.SalePloyAgentInfo;
import com.justinmobile.bmp.cloudboss.sms.manager.SalePloyAgentInfoManager;
import com.justinmobile.bmp.util.SpringSecurityUtils;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManagerImpl;

@Service("salePloyAgentInfoManager")
public class SalePloyAgentInfoManagerImpl extends EntityPageManagerImpl<SalePloyAgentInfo, SalePloyAgentInfoDao>
		implements SalePloyAgentInfoManager {
	@Autowired
	private SalePloyAgentInfoDao salePloyAgentInfoDao;
	
	@Override
	public void create(Long salePloyId, String signer) throws BusinessException {
		String userName = SpringSecurityUtils.getCurrentUserName();
		SalePloyAgentInfo entity = new SalePloyAgentInfo();
		entity.setId(salePloyId);
		entity.setSigner(signer);
		entity.setOper(userName);
		salePloyAgentInfoDao.save(entity);
	}

	@Override
	public SalePloyAgentInfo getById(Long salePloyId) {
		return salePloyAgentInfoDao.findUniqueEntity(
				"from SalePloyAgentInfo e where e.id = ?", salePloyId
				);
	}
	
	
}
