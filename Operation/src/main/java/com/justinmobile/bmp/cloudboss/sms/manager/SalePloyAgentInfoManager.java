package com.justinmobile.bmp.cloudboss.sms.manager;

import com.justinmobile.bmp.cloudboss.sms.domain.SalePloyAgentInfo;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManager;

public interface SalePloyAgentInfoManager  extends EntityPageManager<SalePloyAgentInfo>{
	/***
	 * 添加记录
	 * @throws BusinessException
	 */
	public void create(Long salePloyId, String signer) throws BusinessException;
	
	/**
	 * 取唯一记录
	 */
	public SalePloyAgentInfo getById(Long salePloyId);
}
