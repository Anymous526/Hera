/**
 * @(#)ddVerifier.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.manager;

import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.core.security.KeepLoginStatusVerifier;

/**
 * 关联账户登录校验器
 * 
 * @author jianguo.xu
 * @version 1.0,2011-7-7
 */
public class RelationAccountVerifier extends KeepLoginStatusVerifier {
	private final AccountType accountType;
	private final AuthenticationEntry authenticationEntry;

	public AccountType getAccountType() {
		return accountType;
	}

	public AuthenticationEntry getAuthenticationEntry() {
		return authenticationEntry;
	}

	public RelationAccountVerifier(AccountType accountType,
			AuthenticationEntry authenticationEntry) {
		this(accountType, authenticationEntry, false);
	}

	public RelationAccountVerifier(AccountType accountType,
			AuthenticationEntry authenticationEntry, boolean keepLoginStatus) {
		this(accountType, authenticationEntry, keepLoginStatus, DEFAULT_KEEP_LOGIN_DAY);
	}

	public RelationAccountVerifier(AccountType accountType,
			AuthenticationEntry authenticationEntry, boolean keepLoginStatus,
			int keepLoginDay) {
		super(keepLoginStatus, keepLoginDay);
		this.accountType = accountType;
		this.authenticationEntry = authenticationEntry;
	}

}
