/**
 * @(#)SendRule.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain.rulesupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vlives.boss.coupon.domain.CouponSendRule.RuleType;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-11
 */
public   class SendRule implements Serializable {
	private static final String SEND_COUNT_NAME="sendCount";
	private static final String MIN_CONSUM_MONEY_NAME="minConsumMoney";
	/**
	 * 创建注册发送电子券的规则
	 * @author jianguo.xu
	 * @param sendCount
	 * @return
	 */
	public static SendRule createRegisterSendRule(int sendCount) {
		SendRule sendRule = new SendRule();
		sendRule.addItem(new RuleItem(SEND_COUNT_NAME, new Integer(sendCount).toString()));
		return sendRule;
	}
	/**
	 * 创建满额发送电子券规则
	 * @author jianguo.xu
	 * @param sendCount 发送数量
	 * @param sendCount	最小消费金额
	 * @return
	 */
	public static SendRule createQuotaSendRule(int sendCount,BigDecimal minConsumMoney) {
		SendRule sendRule = createRegisterSendRule(sendCount);
		sendRule.addItem(new RuleItem(MIN_CONSUM_MONEY_NAME, minConsumMoney.toString()));
		return sendRule;
	}
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected static class RuleItem {
		private String name;
		private String value;

		public RuleItem(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	private List<RuleItem> ruleItems;
	protected SendRule() {
		 
	}
	protected SendRule(List<RuleItem> ruleItems) {
		this.ruleItems = ruleItems;
	}
	
	protected void addItem(RuleItem ruleItem) {
		if(ruleItems == null) ruleItems = new ArrayList<RuleItem>();
		for(RuleItem item : ruleItems) {
			if(item.getName().equals(ruleItem.getName())) {
				item.setValue(ruleItem.getValue());
			}
		}
		ruleItems.add(ruleItem);
	}
	protected List<RuleItem> getRuleItems() {
		return ruleItems;
	}
	protected void setRuleItems(List<RuleItem> ruleItems) {
		this.ruleItems = ruleItems;
	}
	
	protected String toXml() {
		return SendRuleHandler.parseXML(this);
	}
	
	protected static SendRule createSendRule(String text) {
		return SendRuleHandler.parseSendRule(text);
	}
	/**
	 * 根据 短信下发触发器计算可下发的数量<br/>
	 * 如果数量为0表示不下发
	 * @author jianguo.xu
	 * @param sendTrigger
	 * @return
	 */
	public int calculatSendCount(SendTrigger sendTrigger) {
		int sendCount = getSendCount();
		 if(sendTrigger instanceof RegisterSendTrigger) {
			 return sendCount;
		 }
		 if(sendTrigger instanceof QuotaSendTrigger) {
			 QuotaSendTrigger quotaSendTrigger = (QuotaSendTrigger) sendTrigger;
			 BigDecimal minConsumMoney =getMinConsumMoney();
			 if(quotaSendTrigger.getConsumMoney().compareTo(minConsumMoney)>=0) {
				 return sendCount;
			 }
		 }
		 return 0;
	}
	
	private int getSendCount() {
		for(RuleItem item : ruleItems) {
			if(item.getName().equals(SEND_COUNT_NAME)) {
				return Integer.parseInt(item.getValue());
			}
		}
		return 0;
	}
	
	private BigDecimal getMinConsumMoney() {
		for(RuleItem item : ruleItems) {
			if(item.getName().equals(MIN_CONSUM_MONEY_NAME)) {
				return new BigDecimal(item.getValue());
			}
		}
		return BigDecimal.ZERO;
	}
	
	public String getRuleDescription(RuleType ruleType) {
		if(ruleType == RuleType.REGISTER_SEND) {
			return "注册会员发送 "+getSendCount()+"张优惠券";
		}
		else {
			return "会员消费满 "+getMinConsumMoney()+"元,送 "+getSendCount()+"张优惠券";
		}
	}
}
