/*
 * @(#)MemberManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberGroup;
import com.vlives.boss.member.domain.MemberSource;
import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.member.dto.MemberReport;
import com.vlives.boss.member.manager.exception.MemberException;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.pagination.Pagination;

/**
 * 会员接口
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
public interface MemberManager {
	/**
	 * 根据用户和会员组得到会员信息
	 * @author jianguo.xu
	 * @param user 用户
	 * @param memberGroup 商户会员组
	 * @return
	 */
	public Member getBy(User user,MemberGroup memberGroup);
	
	/**
	 * 通过id获得会员
	 * @param id
	 * @return 会员
	 */
	public Member get(Long id);

	/**
	 * 保存会员
	 * @param member 会员
	 * @param operator 操作员
	 * @throws BusinessException 如果商户的会员已经存在则抛出异常
	 */
	public void save(Member member,Operator operator)throws BusinessException;
	 
	
	/**
	 * 查询会员
	 * @param param 参数 键-值对
	 * @param pagination 分页信息
	 * @return 
	 */
	public List<Member> find(Map<String,Object> param,Pagination pagination);
	
	/**
	 * 查询会员不分页
	 * @param param
	 * @return
	 */
	public List<Member> find(Map<String,Object> param);
	
	/**
	 * 查询数量
	 * @param param
	 * @return
	 */
	public long count(Map<String,Object> param);
	/**
	 * 查询网站会员数
	 * @author jianguo.xu
	 * @return
	 */
	public long countTotalMemberCount();
	
	/**
	 * 冻结会员
	 * @author jianguo.xu
	 * @param member
	 * @param operator
	 * @throws BusinessException 如果该会员不是有效状态则抛出异常
	 */
	public void freeze(Member member,Operator operator)throws BusinessException;
	/**
	 * 解冻
	 * @author jianguo.xu
	 * @param member
	 * @param operator
	 * @throws BusinessException 如果该会员不是冻结状态则抛出异常
	 */
	public void unFreeze(Member member,Operator operator)throws BusinessException;
	
	/**
	 * 注销
	 * @author jianguo.xu
	 * @param member
	 * @param operator
	 * @throws BusinessException 如果该会员已经是注销状态则抛出异常
	 */
	public void logOut(Member member,Operator operator)throws BusinessException;
	/**
	 * 根据商户营销活动查询满足营销活动条件的会员
	 * @author jianguo.xu
	 * @param salePloy
	 * @return
	 */
	public List<Member> findBySalePloy(SalePloy salePloy,List<Member> excludeMembers);
	
	/**
	 * 根据商户营销活动查询满足营销活动条件的会员
	 * @author jianguo.xu
	 * @param salePloy
	 * @return
	 */
	public List<Member> findBySalePloy(SalePloy salePloy,List<Member> excludeMembers,Pagination pagination);
	/**
	 * 根据商户营销活动查询满足营销活动条件的会员数
	 * @author jianguo.xu
	 * @param salePloy
	 * @return
	 */
	public long countBySalePloy(SalePloy salePloy,List<Member> excludeMembers);
	
	/**
	 * 根据商户去查找到所以会员
	 * create by datao.wang 2011-6-10
	 *@param merchant
	 *@return
	 */
	public List<Member> findByMerchant(Merchant merchant);
	
	/**
	 * 添加会员
	 * @param user
	 * @throws BusinessException
	 * 
	 */
	public Member regist(Operator operator,User user,Merchant merchant,int point,Level level,MemberSource source) throws BusinessException;
	
	/**
	 * 通过时间统计会员
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> statByDate(Object args[]);
	
	/**
	 * 全部统计(按分类)
	 * @return
	 */
	public List<Map<String, Object>> statAll(Merchant merchant);
	
	/**
	 * 获得一个商户的会员数
	 *@param merchant
	 *@return
	 */
	public Long countMembers(Merchant merchant);
	
	/**
	 * 获得一个会员的商户数
	 *@param merchant
	 *@return
	 */
	public Long countMerchants(User user);
	
	/**
	 * 查询会员积分
	 * @param user 用户对象
	 * @param pagination 分页信息
	 * @return 
	 */
	public List<Member> findPoint(User user, Pagination pagination);
	
	/**
	 * 通过短信渠道创建会员
	 * @param mobileNo
	 * @param merchant
	 * @return
	 * @throws BusinessException
	 * @throws MemberException
	 */
	public Member createBySms(String mobileNo, Merchant merchant) throws BusinessException,MemberException;
	/**
	 * 查询商户的所有会员信息
	 * @param createStartDate
	 * @param createEndDate
	 * @return
	 */
	public List<MemberReport> findTotalMember(Map<String,Object> params,Pagination pagination);
	
	/**
	 * POS创建新会员
	 * 
	 * @param user
	 * @param merchant
	 */
	public Member createByPos(String mobileNo, Merchant merchant) throws PosBussinessException,MemberException;

	/**
	 * 把手机号和临时会员绑定起
	 * 
	 * @param user
	 *            临时态user对象
	 * @param merchant
	 *            商户
	 * @param tempMember
	 *            临时会员
	 * @throws BussniessException
	 */
	public Member bindMember(String mobileNo, TempMember tempMember) throws PosBussinessException,MemberException;
	
	
	public void update(Member member);
	
	
	/**
	 * 查询活动符合发送条件的会员信息
	 * @author jianguo.xu
	 * @param salePloy
	 * @return
	 */
	public List<Member> findByPloy(Merchant merchant,Map<String,Object> param);
	
	/**
	 * 查询活动符合发送条件的会员信息
	 * @author jianguo.xu
	 * @param salePloy
	 * @return
	 */
	public List<Member> findByPloy(Merchant merchant,Map<String,Object> param,Pagination pagination);
	/**
	 * 查询活动符合发送条件的会员信息的记录数
	 * @author jianguo.xu
	 * @param salePloy
	 * @return
	 */
	public long countByPloy(Merchant merchant,Map<String,Object> param);
	
	
	
}

