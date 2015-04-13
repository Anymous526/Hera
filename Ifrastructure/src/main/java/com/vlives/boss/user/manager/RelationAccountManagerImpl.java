/**
 * @(#)RelationAccountManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.RelationAccount;
import com.vlives.boss.user.domain.User;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.PropertiesFinder;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 */
@Service("relationAccountManager")
public class RelationAccountManagerImpl implements RelationAccountManager{
	@Resource
	private BaseDao<RelationAccount, Long>  relationAccountDao;
	@Override
	public RelationAccount get(Long id) {
		return relationAccountDao.get(id);
	}

	@Override
	public RelationAccount getBy(AccountType accountType,
			AuthenticationEntry authenticationEntry) {
		PropertiesFinder finder = new PropertiesFinder(RelationAccount.class);
		finder.add("accountType", accountType);
		finder.add("authenticationEntry", authenticationEntry);
		return relationAccountDao.getBy(finder);
	}
	
	@Override
	public RelationAccount getBy(User user, AccountType accountType) {
		PropertiesFinder finder = new PropertiesFinder(RelationAccount.class);
		finder.add("user", user);
		finder.add("accountType", accountType);
		return relationAccountDao.getBy(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delete(RelationAccount relationAccount) {
		relationAccountDao.delete(relationAccount);
	}

	
}
