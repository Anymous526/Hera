package com.vlives.boss.sms.manager;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.trade.domain.TradeDetail;

public interface SmsTemplateManager {



	/**
	 * 注册会员短信
	 * 
	 * @param member
	 */
	public void registerMemberSms(Member member);

	/**
	 * 会员消费短信
	 * 
	 * @param tradeOrder
	 * @param merchant
	 */
	public void tradeSms(TradeDetail tradeDetail);

}
