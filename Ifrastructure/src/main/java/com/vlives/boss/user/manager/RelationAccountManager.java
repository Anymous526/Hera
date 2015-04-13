/**
 * @(#)RelationAccountManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.manager;

import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.RelationAccount;
import com.vlives.boss.user.domain.User;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 * 
 */
public interface RelationAccountManager {
	public RelationAccount get(Long id);
	/**
	 * 根据账户类型、认证实体得到关联账户
	 * @author jianguo.xu
	 * @param accountType
	 * @param authenticationEntry
	 * @return
	 */
	public RelationAccount getBy(AccountType accountType,AuthenticationEntry authenticationEntry);
	/**
	 * 根据User,账户类型
	 * @author jianguo.xu
	 * @param user
	 * @param accountType
	 * @return
	 */
	public RelationAccount getBy(User user,AccountType accountType);
	
	
	/**
	 * 取销关联账户
	 * @param relationAccount
	 */
	public void delete(RelationAccount relationAccount);
	
}
