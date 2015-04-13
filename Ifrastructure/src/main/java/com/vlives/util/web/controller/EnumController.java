/**
 * @(#)EnumController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.merchant.domain.DiscountRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantEnumType;
import com.vlives.boss.merchant.domain.PointRule;
import com.vlives.boss.merchant.domain.UpdateRuleItem;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.security.domain.Role;
import com.vlives.boss.sms.domain.SmsAccountDetail;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.user.domain.CardType;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
/**
 * 得到多个枚举的集合
 * @author  jianguo.xu
 * @version 1.0,2011-6-13
 */
@Controller
@RequestMapping("/manager/enum")
public class EnumController {
	
	@RequestMapping("/enum.htm")
	public ModelAndView execute() throws Exception {
		 String[] enumNames = HttpParameterParser.getStringArray("name");
		 EnumTye enumTye = null;
		 List<Object> list = new ArrayList<Object>();
		 
		 if(enumNames != null){
			 for(int i = 0; i < enumNames.length; i++){
				 enumTye =  EnumTye.get(enumNames[i]);
				 Class<? extends EnumTypeInterface> clazz = enumTye.getEnumClazz();
				 list.add(clazz.getEnumConstants());
			 }
		 } 
		 else {
			for (EnumTye enumTyes : EnumTye.values()) {
				enumTye = EnumTye.get(enumTyes.name());
				Class<? extends EnumTypeInterface> clazz = enumTye.getEnumClazz();
				list.add(clazz.getEnumConstants());
			}
		 }
		 return new ModelAndView(new JsonView(list));
	}
	
	/**
	 * 指向枚举的枚举
	 * @author tiger
	 *
	 */
	public static enum EnumTye {
		
		TRADEORDER_TYPE(TradeOrder.Type.class),
		USER_CARDTYPE(CardType.class),
		LEVEL_LEVEL(Level.class),
		TRADEBATCH_STATUS(TradeBatch.Status.class),
		SMSACCOUNTDETAIL_TYPE(SmsAccountDetail.Type.class),
		ROLE_ROLEGROUP(Role.RoleGroup.class),
		SALEPLOY_TYPE(SalePloy.Type.class),
		SALEPLOY_STATUS(SalePloy.Status.class),
		POS_STATUS(Pos.Status.class),
		OPERATORLOG_TYPE(OperatorLog.OperatorType.class),
		OPERATOR_TYPE(Operator.Type.class),
		OPERATOR_STATUS(Operator.Status.class),
		UPDATERULEITEM_TYPE(UpdateRuleItem.Type.class),
		OINTRULE_TYPE(PointRule.Type.class),
		MERCHANT_TRANSACTIONTYPE(MerchantEnumType.TransactionType.class),
		MERCHANT_PAYTYPPE(MerchantEnumType.PayType.class),
		MERCHANT_PROPERTY(MerchantEnumType.MerchantProperty.class),
		MERCHANT_BUSINESSLANDNATURE(MerchantEnumType.BusinessLandNature.class),
		MERCHANT_BUSINESSLOCATION(MerchantEnumType.BusinessLocation.class),
		MERCHANT_REGIONS(MerchantEnumType.Regions.class),
		MERCHANT_STATUS(Merchant.Status.class),
		DISCOUNTRULE_TYPE(DiscountRule.Type.class),
		MEMBERPOINTDETAIL_TYPE(MemberPointDetail.Type.class),
		MEMBER_STATUS(Member.Status.class),
		MERCHANTCOMMENT_GRADE(MerchantComment.MerchantGrade.class),
		AREA_TYPE(Area.AreaType.class);
		 
		private final Class<? extends EnumTypeInterface> enumClazz;

		 
		private EnumTye(Class<? extends EnumTypeInterface> enumClazz) {
			this.enumClazz = enumClazz;
		}


		public Class<? extends EnumTypeInterface> getEnumClazz() {
			return enumClazz;
		}


		public static EnumTye get(String name) {
			for (EnumTye enumTye : EnumTye.values()) {
				if (enumTye.name().equals(name)) {
					return enumTye;
				}
			}
			throw new IllegalArgumentException("argument error: " + name);
		}
		
	}

}
