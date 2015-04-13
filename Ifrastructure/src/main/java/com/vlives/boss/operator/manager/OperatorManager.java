/**
 * @(#)OperatorManager.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.security.domain.Role;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.security.AuthenticationProvider;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-6-2
 */
public interface OperatorManager extends AuthenticationProvider{
	
	/**
	 * 根据手机号得到管理员
	 * @author jianguo.xu
	 * @param mobile
	 * @return
	 */
	public Operator getByMobile(String mobile);
	
	public Operator get(Long id);
	 
	/**
	 * 创建管理员,同时添加权限规则
	 * @author jianguo.xu
	 * @param operator 	管理员
	 * @param merchant	对应的商户
	 * @param operatorRoles	权限集合
	 * @param creator	创建者
	 * 如果管理员手机号已经存在则抛出异常
	 */
	public void create(Operator operator,Merchant merchant,List<Role> roles,Operator creator)throws BusinessException;
	/**
	 * 根据商户查询所有管理员
	 * @author jianguo.xu
	 * @param merchant
	 * @return
	 */
	public List<Operator> findByMerchant(Merchant merchant); 
	
	/**
	 * 修改密码
	 * @author jianguo.xu
	 * @param operaotr  管理员
	 * @param oldPass 	原密码
	 * @param newPass	新密码
	 * 如果原密码不一致则抛出异常
	 */
	public void updatePassword(Operator operator, String oldPass,String newPass) throws BusinessException;
	/**
	 * 重置密码
	 * @author jianguo.xu
	 * @param operaotr
	 */
	public void resetPassword(Operator operator);
	/**
	 * 冻结管理员
	 * @author jianguo.xu
	 * @param manager	被冻结的管理员
	 * @param operaotr	操作员
	 * 如果管理员状态不正常抛出异常
	 */
	public void freezeOperator(Operator manager,Operator operator)throws BusinessException;
	
	/**
	 * 解冻管理员
	 * @author jianguo.xu
	 * @param manager	被冻结的管理员
	 * @param operaotr	操作员
	 * 如果管理员状态不正常抛出异常
	 */
	public void unfreezeOperator(Operator manager,Operator operator)throws BusinessException;
	/**
	 * 绑定管理员规则
	 * @author jianguo.xu
	 * @param manager
	 * @param roles
	 * @param operaotr
	 */
	public void bindRole(Operator manager,List<Role> roles);
	/**
	 * 根据商户查询商户管理员
	 * @author jianguo.xu
	 * @param merchant
	 * @param pagination
	 */
	public List<Operator> find(Map<String,Object> params,Pagination pagination);
	
	/**
	 * 验证操作员手机号是否存在
	 * @param mobile
	 * @return
	 */
	public Boolean isExistMobile(String mobile);
	/***
	 * 查询商户的第一个商户管理员
	 * @author unicorn
	 * @param merchant
	 * @return
	 */
	public Operator findMerchantOperator(Merchant merchant); 
}
