/*
 * @(#)SalePloyManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sale.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.domain.SalePloy.SalePloyType;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-6-1
 */
public interface SalePloyManager {

	public SalePloy get(Long id);

	/**
	 * 创建营销活动
	 * 
	 * @param salePloy
	 * @param excludeMembers
	 *            排除的会员
	 * @param mechantCreate
	 *            是否是商户自己创建
	 * @param operator
	 * @return
	 */
	public SalePloy create(SalePloy salePloy, List<Member> excludeMembers, boolean mechantCreate, Operator operator)
			throws BusinessException;

	/**
	 * 修改营销活动
	 * 
	 * @param salePloy
	 * @param operator
	 * @return
	 */
	public void modity(SalePloy salePloy, String template, Operator operator) throws BusinessException;

	/**
	 * 注销营销活动
	 * 
	 * @param salePloy
	 * @param operator
	 * @return
	 */
	public void logOut(SalePloy salePloy, Operator operator) throws BusinessException;

	/**
	 * 查询营销活动
	 * 
	 * @param merchant
	 * @param pagination
	 * @return
	 */
	public List<SalePloy> find(Map<String, Object> params, Pagination pagination);

	/**
	 * 查询营销活动数
	 * 
	 * @author jianguo.xu
	 * @param params
	 * @return
	 */
	public long count(Map<String, Object> params);

	/**
	 * 求和
	 * 
	 * @author jianguo.xu
	 * @param params
	 * @return
	 */
	public long sum(Map<String, Object> params, String sumName);

	/**
	 * 查询营销活动,不分页
	 * 
	 * @param merchant
	 * @param pagination
	 * @return
	 */
	public List<SalePloy> find(Map<String, Object> params);

	/**
	 * 查询商户最新发布的10条优惠信息
	 * 
	 * @author unicorn
	 * @return
	 */
	public List<SalePloy> find();

	/**
	 * 分页查询指定商户的有效营销活动
	 * 
	 * @author unicorn
	 * @param pagination
	 * @return
	 */
	public List<SalePloy> find(Merchant merchant);

	/**
	 * 得到指定商户最新一条活动
	 * 
	 * @author unicorn
	 * @param merchant
	 * @return
	 */
	public List<SalePloy> getNewlySalePloy(Merchant merchant);

	/**
	 * 审核营销活动
	 * 
	 * @author jianguo.xu
	 * @param salePloy
	 * @param pass
	 * @param desc
	 * @param operator
	 * @throws BusinessException
	 */
	public void auditPloy(SalePloy salePloy, boolean pass, String desc, Operator operator) throws BusinessException;
 

	/**
	 * 获取活动短信发送完成的活动 定时任务
	 * 
	 * @param pagination
	 * @return
	 */
	public List<SalePloy> findBySendSuccess(Pagination pagination);

	/**
	 * 判断活动是否过期 定时任务
	 * 
	 * @param salePloy
	 */
	public void updateExpiredStatus(SalePloy salePloy);

	/**
	 * 更新活动短信发送数据量和结果 定时任务
	 * 
	 * @param salePloy
	 *            必须是发送完成活动
	 */
	public void updateSendResult(SalePloy salePloy);

	/**
	 * 更新活动为发送中状态
	 * 
	 * @param salePloy
	 */
	public void updateSendStatus(SalePloy salePloy);

	/**
	 * 获取用来判断是否过期的活动
	 */
	public List<SalePloy> getExpiredSalePloy();

	/**
	 * 根据营销活动类型判断是否存在该类型的活动
	 * 
	 * @author jianguo.xu
	 * @param salePloyType
	 * @return
	 */
	public boolean existSalePloyByType(Merchant merchant, SalePloyType salePloyType);

	/**
	 * 创建会员是发送短信
	 * 
	 * @author jianguo.xu
	 * @param member
	 */
	public void sendSmsByCreateMember(Member member, Operator operaotor);

	/**
	 * 交易时发送短信
	 * 
	 * @author jianguo.xu
	 * @param tradeDetail
	 */
	public void sendSmsByTrade(TradeDetail tradeDetail, Operator operaotor);

	/**
	 * 判断是否存在商户优惠活动
	 * 
	 * @author jianguo.xu
	 * @param merchant
	 * @return
	 */
	public boolean isExistMerchantPloy(Merchant merchant);
	
	/**
	 * 获取发送完成的活动
	 * 指活动下所有短信发送完成 
	 * @return
	 */
	public Object[] findSendFinallyPloy();
}
