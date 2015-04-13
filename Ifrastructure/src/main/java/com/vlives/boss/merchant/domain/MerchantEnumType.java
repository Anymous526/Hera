package com.vlives.boss.merchant.domain;

import com.vlives.core.support.entity.EnumTypeInterface;


public class MerchantEnumType {

	public static enum TransactionType implements EnumTypeInterface {

		/** 实地交易*/
		FIELD_TRAN(1, "实地交易"),
		/** 网上交易*/
		ONLINE_TRAN(2, "网上交易"),
		/** 电购交易*/
		POWER_TRAN(3, "电购交易"),
		/** 邮购交易*/
		MAIL_TRAN(4, "邮购交易"),
		/** 其它*/
		OTHER(5, "其它");

		private int value;
		private String desc;

		TransactionType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static TransactionType get(int value) {
			for (TransactionType type : TransactionType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	
	}

	public static enum PayType implements EnumTypeInterface {

		/** 预付款*/
		ADVANCES(1, "预付款"),
		/** 钱货两清*/
		TWOCLEAR(2, "钱货两清"),
		/** 分期付款*/
		INSTALLMENT(3, "分期付款"),
		/** 赊购*/
		CREDIT(4, "赊购");

		private int value;
		private String desc;

		PayType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static PayType get(int value) {
			for (PayType type : PayType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	
	}
	
	public static enum MerchantProperty implements EnumTypeInterface {

		STATE_RUN(1, "国营"),
		STOCK(2, "股份制"),
		COLLECTIVE(3, "集体"),
		JOINT(4, "中外合资、合作 "),
		FOREIGN_OWNED(5, "外商独资"),
		PRIVATE_PARTNERSHIP(6, "私营合伙"),
		PRIVATE(7, "私营独资"),
		INDIVIDUAL(8, "个体"),
		OTHER(9, "其它");

		private int value;
		private String desc;

		MerchantProperty(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static MerchantProperty get(int value) {
			for (MerchantProperty type : MerchantProperty.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}
	
	public static enum BusinessLandNature implements EnumTypeInterface {

		OWN(1, "自有"),
		HIRE(2, "租用");

		private int value;
		private String desc;

		BusinessLandNature(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static BusinessLandNature get(int value) {
			for (BusinessLandNature type : BusinessLandNature.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	
	}
	
	public static enum BusinessLocation implements EnumTypeInterface {

		BUSINESS(1, "商业区"),
		WORKING(2, "工业区"),
		HOME(3, "住宅区");

		private int value;
		private String desc;

		BusinessLocation(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static BusinessLocation get(int value) {
			for (BusinessLocation type : BusinessLocation.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}
	
	public static enum Regions implements EnumTypeInterface {

		CITY(1, "城区"),
		SUBURBS(2, "郊区"),
		REMOTE(3, "边远地区");

		private int value;
		private String desc;

		Regions(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static Regions get(int value) {
			for (Regions type : Regions.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}
	

}
