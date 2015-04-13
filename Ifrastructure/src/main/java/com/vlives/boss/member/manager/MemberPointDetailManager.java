package com.vlives.boss.member.manager;


import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.trade.domain.TradeOrder;



public interface MemberPointDetailManager {
	
	public  MemberPointDetail get(Long id);
	
	/**
	 * 创建会员积分变动日志
	 * @param member
	 * @param type
	 * @param desc
	 * @return
	 */
	public  MemberPointDetail createDetail(Member member,Merchant merchant,int point,MemberPointDetail.Type type,String desc);
	
	/**
	 * 创建
	 * @param member
	 * @param tradeOrder
	 * @param type
	 * @param desc
	 * @return
	 */
	public  MemberPointDetail createDetail(Member member,TradeOrder tradeOrder,int point,MemberPointDetail.Type type,String desc);
	
	
	/**
	 * 根据订单和会员查找原类型交易明细
	 * @param member
	 * @param tradeOrder
	 * @param type
	 * @return
	 */
	public  MemberPointDetail getByMemberAndOrder(Member member,TradeOrder tradeOrder,MemberPointDetail.Type  changeType);
	
	

}
