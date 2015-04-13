/*
 * @(#)MerchantManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.merchant.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.merchant.domain.DiscountRule;
import com.vlives.boss.merchant.domain.MemberUpdateRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantExtendInfo;
import com.vlives.boss.merchant.domain.PointRule;
import com.vlives.boss.merchant.domain.UpdateRuleItem;
import com.vlives.boss.merchant.dto.MerchantSearchResult;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  unicorn
 * @version 1.0,2011-6-2
 */
public interface MerchantManager {

	/***
	 * 新建商户
	 * @param merchant 商户基本信息
	 * @param managerMobile 商户管理员的手机号
	 * @param operator 创建商户的操作员
	 * @throws BusinessException
	 */
	public String create(Merchant merchant,MerchantExtendInfo merchantExtendInfo,String managerMobile,Operator operater) throws BusinessException;
	
	/**
	 * 冻结
	 * @author jianguo.xu
	 * @param merchant
	 * @param operator
	 * @param desc 		状态变更原因
	 * @throws BusinessException 
	 */
	public void freeze(Merchant merchant,String desc,Operator operator)throws BusinessException;

	/**
	 * 解冻
	 * @author jianguo.xu
	 * @param merchant
	 * @param operator
	 * @param desc 		状态变更原因
	 * @throws BusinessException
	 */
	public void unFreeze(Merchant merchant,String desc,Operator operator)throws BusinessException;
	
	/**
	 * 注销
	 * @author jianguo.xu
	 * @param merchant
	 * @param operator
	 * @param desc 		状态变更原因
	 * @throws BusinessException
	 */
	public void logOut(Merchant merchant,String desc,Operator operator)throws BusinessException;
	
	/***
	 * 动态条件查询商户列表
	 * @param map 条件集
	 * @param pagination 分页参数
	 * @return 
	 */
	public List<Merchant> find(Map<String,Object> map,Pagination pagination);

	/**
	 * 搜索商户
	 * @author jianguo.xu
	 * @param map
	 * @param pagination
	 * @return
	 */
	public MerchantSearchResult search(Map<String,Object> map,Pagination pagination);

	/**
	 * 查询所有有效商户,不带分页的
	 * @return
	 */
	public List<Merchant> find(Map<String,Object> map);
	
	
	/**
	 * 根据商户名字、商户区域、商户分类、商户经纬度查询商户列表
	 * @param map 查询条件
	 * @return 
	 */
	public List<Merchant> findBy(Map<String,Object> map,Pagination pagination);
	
	/***
	 * 查找可以做父商户的商户列表
	 * @author unicorn
	 * @return
	 */
	public List<Merchant> findOtherParents(Map<String,Object> map,Pagination pagination);
	
	/***
	 * 根据商户id号查询商户详细信息
	 * @param id 商户id号
	 * @return
	 */
	public Merchant get(Long id);
	
	/***
	 * 根据商户编码获得商户
	 * @param code	商户编码
	 * @return
	 */
	public Merchant getByCode (String code);
	
	/***
	 * 更新商户基本信息
	 * @param merchant
	 */
	public void update(Merchant merchant,Operator operator)throws BusinessException;
	
	/**
	 * 审核商户
	 * @param merchant 商户
	 * @param pass 是否通过
	 * @param desc 原因
	 * @param operator 审核人
	 */
	public void aduitMerchant(Merchant merchant,boolean pass,String desc,Operator operator)throws BusinessException;
	
	/**
	 * 商户等级,折扣,升级管理
	 * @param pointRule
	 * @param discountRule
	 * @param items
	 * @param merchant
	 * @param memberUpdateRule
	 */
	public void levelManagement(PointRule pointRule,DiscountRule discountRule,List<UpdateRuleItem> items,Merchant merchant,MemberUpdateRule memberUpdateRule);
	
	/**
	 * 验证商户编码是否唯一
	 * @param code
	 * @return true 手机号存在，false 手机号不存在
	 */
	public boolean isExistCode(String code);
	
	/**
	 * 查询会员所属商户
	 * @return
	 */
	public List<Merchant> findMerchantsByMember(User user, Pagination pagination);

	/**
	 * 得到有效商户数
	 * @author jianguo.xu
	 * @return
	 */
	public long countMerchant();

	/**
	 * 得到指定城市的推荐商户
	 * @author jianguo.xu
	 * @param maxResult
	 * @param city 城市
	 * @return
	 */
	public List<Merchant> getTopMerchants(Area city, int maxResult);
	
	 
	/**
	 * 查找附近的商户
	 * @author jianguo.xu
	 * @param merchant
	 * @return
	 */
	public List<Merchant> findByNear(Merchant merchant,int maxResults);
	
	/**
	 * 判断是否存在商户短信编号
	 * @author jianguo.xu
	 * @param num
	 * @return
	 */
	public boolean isExistNum(long num);
	
	public Merchant getByNum(Long num);
}

