/**
 * @(#)AuthenticationEntryHibernateType.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 */
public class AuthenticationEntryHibernateType implements UserType{

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}
	@Override
	public int hashCode(Object x) throws HibernateException {
		return x == null ? 0 : x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		String resultStr = (String) Hibernate.STRING.nullSafeGet(rs, names[0]);
		if (resultStr == null || resultStr.trim().equals(""))
			return null;
		return AuthenticationEntry.createAuthenticationEntry(resultStr);
	}

	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index)
		throws HibernateException, SQLException {
		if (value == null) {
			Hibernate.STRING.nullSafeSet(ps, null, index);
		}
		else {
			AuthenticationEntry entry = (AuthenticationEntry) value;
			Hibernate.STRING.nullSafeSet(ps, entry.toXml(), index);
		}
		
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
		throws HibernateException {
		return original;
	}

	@Override
	public Class<AuthenticationEntry> returnedClass() {
		return AuthenticationEntry.class;
	}

	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}
	
}
