/**
 * @(#)MerchantSmsAccountManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.sms.manager;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sms.domain.SmsAccountDetail.Type;
import com.vlives.core.exception.BusinessException;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-19
 */
public interface MerchantSmsAccountManager {
	/**
	 * 增加账户短信数
	 * @author jianguo.xu
	 * @param merchant
	 * @param quantity
	 * @param type
	 * @param desc
	 * @param operator
	 * @throws BusinessException 如果type不是增加的类型则抛出异常
	 */
	public void addSmsCount(Merchant merchant,int quantity,Type type,String desc,Operator operator)throws BusinessException;
	
	
	
	/**
	 * 回复短信把减少了的短信回复到总短信数中
	 * @author jianguo.xu
	 * @param merchant
	 * @param quantity
	 * @param type
	 * @param desc
	 * @param operator
	 * @throws BusinessException
	 */
	public void restore(Merchant merchant,int quantity,Type type,String desc,Operator operator)throws BusinessException;
	/**
	 * 减少账户短信数
	 * @author jianguo.xu
	 * @param merchant
	 * @param quantity
	 * @param type
	 * @param desc
	 * @param operator
	 */
	public void reduceSmsCount(Merchant merchant,int quantity,Type type,String desc,Operator operator) throws BusinessException;
	 
	
}
