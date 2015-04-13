/**
 * @(#)RequestCode.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.request;


/**
 * posp交易请求类型
 * @author ThinkPad7
 *
 */
public enum MtRequestCode {
	
	
	POS_SIGN_RESPONSE("0010100200","POS签到响应",null),
	POS_SIGN_REQUEST( "0000100200","POS签到请求",POS_SIGN_RESPONSE),

	
	MEMBER_INFO_QUERY_RESPONSE("0110100200","POS查询会员信息响应",null),
	MEMBER_INFO_QUERY_REQUEST("0100100200","POS查询会员信息请求",MEMBER_INFO_QUERY_RESPONSE),

	POS_MEMBER_AUTH_RESPONSE("0130100200","POS会员认证响应",null),
	POS_MEMBER_AUTH_REQUEST("0120100200","POS会员认证请求",POS_MEMBER_AUTH_RESPONSE),

	
	POS_TRANSQUERY_RESPONSE("0210100200","POS交易查询响应",null),
	POS_TRANSQUERY_REQUEST("0200100200","POS交易查询请求",POS_TRANSQUERY_RESPONSE),

	

	MEMBER_JOIN_RESPONSE("0310100200", "POS新增会员响应",null),
	MEMBER_JOIN_REQUEST( "0300100200", "POS新增会员请求",MEMBER_JOIN_RESPONSE),

	
	TRADE_BIND_RESPONSE("0330100200", "绑定交易响应",null),
	TRADE_BIND_REQUEST("0320100200", "绑定交易请求",TRADE_BIND_RESPONSE),

	
	CONSUME_DISC_RESP("0150100200","折扣消费计算",null),
	CONSUME_DISC_REQ("0140100200","折扣消费计算",CONSUME_DISC_RESP),

	
	TRADE_BATCH_RESPONSE("0610100200", "交易记录上送响应",null),
	TRADE_BATCH_REQUEST("0600100200", "交易记录上送请求",TRADE_BATCH_RESPONSE),

	
	TRADE_SETTLE_RESPONSE("0810100200", "结算交易响应",null),
	TRADE_SETTLE_REQUEST("0800100200", "结算交易请求",TRADE_SETTLE_RESPONSE),
	
	USER_COUPON__QUREY_RESPONSE("0110200200","用户券信息响应",null),
	USER_COUPON__QUREY_REQUEST("0100200200","用户券信息请求",USER_COUPON__QUREY_RESPONSE),
	
	MERCHANT_COUPON__QUREY_RESPONSE("0130200200","商户券活动响应",null),
	MERCHANT_COUPON__QUREY_REQUEST("0120200200","商户券活动请求",MERCHANT_COUPON__QUREY_RESPONSE),
	
	USER_COUPON_CHECK_RESPONSE("0210200200","用户券消费响应",null),
	USER_COUPON_CHECK_REQUEST("0200200200","用户券消费请求",USER_COUPON_CHECK_RESPONSE),
	

	;	
	private final String code;
	private final String desc;
	private final MtRequestCode valid;
	
	private MtRequestCode(String code, String desc,MtRequestCode valid) {
		this.code = code;
		this.desc = desc;
		this.valid=valid;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public MtRequestCode getRespEnum() {
		return valid;
	}

	public static MtRequestCode get(String code) {
		for (MtRequestCode requestCode : MtRequestCode.values()) {
			if (requestCode.getCode().equals(code))
				return requestCode;
		}
		throw new IllegalArgumentException("request code is not exist : " + code);
	}
}
