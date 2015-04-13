/**
 * @(#)OperatorLogManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface OperatorLogManager {
	/**
	 * 创建操作日志
	 * @author jianguo.xu
	 * @param operator 管理员
	 * @param operatorType	操作类型
	 */
	public void createLog(Operator operator,OperatorType operatorType);
	 
	/**
	 * 动态查询
	 * @author jianguo.xu
	 * @param merchant
	 * @param pagination
	 * @return
	 */
	public List<OperatorLog> find(Map<String, Object> params,Pagination pagination);
	
}
