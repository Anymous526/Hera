package com.vlives.core.exception;

public enum PosReturnCode {
	
		SUCCESS("00", "操作成功"),
		MERCHANT_NOT_EXIST("01", "商户不存在"),
		POS_NOT_EXIST("02", "POS终端不存在"),
		MERCHANT_NOT_VALID("03", "商户不是有效状态"),
		POS_NOT_VALID("04", "POS终端不是有效状态"),
		POS_NOT_RELATED_MERCHANT("05","POS没关联该商户"),
		MERCHANT_MEMBER_EXIST("06", "会员已存在"),
		MEMBER_CARDNO_NOT_EXIST("07", "原会员卡不存在"),
		MEMBER_CARDNO_BINDED("08", "会员卡已绑定"),
		MERCHANT_CARDNO_NOT_BINDED("09", "商户没有该会员卡"),
		MEMBER_NOT_EXIST("0A", "您尚不是我们会员"),
		MEMBER_NOT_VALID("0B", "会员不是有效状态"),
		TOTAL_COUNT_NOT_EQUAL("0C", "消费总笔数不等"),
		TOTAL_AMOUNT_NOT_EQUAL("0D", "消费总金额不等"),		
		C_TOTAL_COUNT_NOT_EQUAL("0F", "退货总笔数不等"),
		C_TOTAL_AMOUNT_NOT_EQUAL("10", "退货总金额不等"),
		BATCH_NUMBER_NOT_EXIST("11", "交易批次号不存在"),
		BATCH_STATUS_BUY_POINT("12", "该交易结算重复"),
		CANECL_TRADE_MEMBER_NOT_RIGHT("13","退货会员不是原消费会员"),
		BATCH_NO_NOT_VALID("14","交易批次号不正确"),
		POSSIGN_INVALID("15", "非法终端"),
		RETURNED_CONSUME_NOT_FOUND("1A", "已退货消费不存在"),
		INVALID_MESSAGE_MAC("1B","非法的报文"), 
		ORGINAL_TRANSE_NOTEXIST("1C","原交易不存在"),
		ORGINAL_TRANSE_RETURNED("1D","原交易已退货"),
		ORGINAL_TRANSE_MOBILE_INVALID("1E","原交易手机不符"),
		
		
		UNKNOWN_VERSION("20", "未知的POS版本"),
		UNSUPPORT_VERSION("21", "不支持的POS版本"),
		
		COUPON_NOT_EXIST("30", "该电子劵不存在"),
		COUPON_NOT_VALID("31", "该电子劵不是有效状态"),
		COUPON_SETTLE_ERROR("32","该电子劵结算失败"),
		COUPON_EXPIRED_ERROR("33","该电子劵已过期"),
		COUPONPLOY_NO_EXIST("34","该活动不存在"),
		MERCHANT_NOT_EXIST_COUPON("35","商户不存在该活动"),
		COUPON_NOT_BELONG_USER("36","没有可用电子券"),
		COUPONPLOY_NOT_BELONG_MERCHANT("37","没有可用电子券"),
		
		DATAGRAM_ERROR("FD","报文数据异常"),
		SYSTEM_ERROR("FE", "系统错误"),
		OTHER_ERROR("FF", "未知错误"),
		;

		private String code;

		private String message;

		PosReturnCode(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getErrorCode() {
			return this.code;
		}

		public String getMessage() {
			return message;
		}

}
