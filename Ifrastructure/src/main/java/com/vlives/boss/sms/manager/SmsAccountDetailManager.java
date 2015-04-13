/**
 * @(#)SmsAccountDetailManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sms.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sms.domain.MerchantSmsAccount;
import com.vlives.boss.sms.domain.SmsAccountDetail;
import com.vlives.boss.sms.domain.SmsAccountDetail.Type;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface SmsAccountDetailManager {
	/**
	 * 创建短信变更明细
	 * @author jianguo.xu
	 * @param merchant 商户
	 * @param quantity	变更数量(如果是减少则为负)
	 * @param type	类型 
	 * @param desc	描述
	 * @param operator	操作员
	 */
	public void create(MerchantSmsAccount merchantSmsAccount,int quantity,Type type,String desc,Operator operator);
	
	public List<SmsAccountDetail> find(Map<String, Object> params,Pagination pagination);
}
