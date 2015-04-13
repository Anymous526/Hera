package com.justinmobile.bmp.common.log.manager;

import com.justinmobile.bmp.common.log.domain.OperationLog;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManager;

public interface LogManager  extends EntityPageManager<OperationLog>{
	/***
	 * 向数据库插入一条数据
	 * @throws BusinessException
	 */
	public void insertLog(String opDescription) throws BusinessException;
}
