/*
 * @(#)UserManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.user.manager;


import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserSource;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.security.AuthenticationProvider;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
public interface UserManager extends AuthenticationProvider {

	
	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	public void save(User user);
	
	public void saveOrUpdate(User user);

	/**
	 * 通过id获得用户
	 * 
	 * @param id
	 */
	public User get(Long id);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void update(User user);

	/**
	 * 通过手机号会的用户
	 * 
	 * @param mobile
	 * @return
	 */
	public User getByMobile(String mobile);

	/**
	 * 给手机发送验证码 如果手机对应的用户不存在则先保存手机号 如果验证嘛在一分钟内多次发送抛出异常 如果验证码发送次数操过5次则抛出异常
	 * 
	 * @author jianguo.xu
	 * @param user
	 * @throws BusinessException
	 */
	public void sendVerifyCode(String mobile) throws BusinessException;
	/**
	 * 
	 * @param user 
	 * @param mobile 取验证码的手机号
	 * @throws BusinessException
	 */
	public void sendVerifyCode(User user,String receiveMobile) throws BusinessException;

	/**
	 * 绑定用户的关联账户 如果关联账户类型不存在则保存关联账户否则更新关联账户
	 * 
	 * @author jianguo.xu
	 * @param user
	 * @param accountType
	 * @param authenticationEntry
	 */
	public void bindRelationAccount(User user, AccountType accountType, AuthenticationEntry authenticationEntry)throws BusinessException;
	

	/**
	 * 创建新会员 检查该手机对象是否存在，如果存在则返回
	 */
	public User create(String mobileNo, UserSource userSource);
	
	/**
	 * 创建零时会员绑定
	 * @param tempMember
	 * @param mobile
	 * @return
	 */
	public User create(TempMember tempMember, String mobile);
}
