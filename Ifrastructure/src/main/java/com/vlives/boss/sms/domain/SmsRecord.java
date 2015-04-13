/*
 * @(#)SmsAccountDetail.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.sms.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

@TypeDefs({
		@TypeDef(name = "code", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sms.domain.SmsRecord$SmsReturnCode") }),
		@TypeDef(name = "smsSource", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.sms.domain.SmsRecord$SmsSource") }) })
@Entity
@Table(name = "SMS_RECORD")
public class SmsRecord extends BaseEntity {

	private Long id;
	/*** 短信来源 */
	private SmsSource smsSource;
	/*** ture:上行，false：下行 */
	private boolean method;
	/*** 手机号 */
	private String mobile;
	/** 短信内容 */
	private String content;
	/** 发送时间 */
	private Date sendDate;
	/**是否成功  true:成功;false:失败;*/
	private Boolean success;
	/** 返回码 */
	private SmsReturnCode returnCode;
	/** 返回描述 */
	private String returnDesc;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_SMS_RECORD") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "SEND_DATE")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Type(type = "code")
	@Column(name = "RETURN_CODE")
	public SmsReturnCode getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(SmsReturnCode returnCode) {
		this.returnCode = returnCode;
	}

	@Column(name = "RETURN_DESC")
	public String getReturnDesc() {
		return returnDesc;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	@Type(type = "smsSource")
	@Column(name = "SMS_SOURCE")
	public SmsSource getSmsSource() {
		return smsSource;
	}

	public void setSmsSource(SmsSource smsSource) {
		this.smsSource = smsSource;
	}

	public boolean isMethod() {
		return method;
	}

	public void setMethod(boolean method) {
		this.method = method;
	}

	public static enum SmsReturnCode {
		/** 成功 */
		SUCCESS(0, "成功"),
		/** 重复登录 */
		REUSE_SIGN(2, "重复登录，如在同一TCP/IP连接中连续两次以上请求登录"),
		/** 连接过多 */
		CONNECTION_TOO_MUCH(3, "连接过多，指单个节点要求同时建立的连接数过多"),
		/** 参数格式错 */
		ARGUMENT_FORMAT_ERROR(4, "参数格式错，指命令中参数值与参数类型不符或与协议规定的范围不符"),
		/** 消息ID错 */
		MESSAGE_ID_ERROR(7, "消息ID错"),
		/** 信息长度错 */
		MESSAGE_LENGTH_ERROR(8, "信息长度错"),
		/** 指令操作失败 */
		OPERATOR_COMMOD_FAIL(3, "指令操作失败"),
		/** 客户端注册失败 */
		CUSTOMER_REGISTER_FAIL(10, "客户端注册失败"),
		/** 企业信息注册失败 */
		ENTERPRISE_REGISTER_FAIL(3, "企业信息注册失败"),
		/** 指令操作失败 */
		SEND_MESSAGE_FAIL(17, "发送信息失败"),
		/** 发送定时信息失败 */
		EIGHTEEN_CODE(18, "发送定时信息失败"),
		/** 接收短信失败 */
		NINETEEN_CODE(19, "接收短信失败"),
		/** 审核失败 */
		TWENTY_CODE(20, "审核失败"),
		/** 客户端网络故障 */
		ONE_HUNDRED_AND_ONE(101, "客户端网络故障"),
		/** */
		THREE_HUNDRED_AND_FIVE(305, "服务器端返回错误，错误的返回值（返回值不是数字字符串）"),
		/** */
		THREE_HUNDRED_AND_seven(307, "目标电话号码不符合规则，电话号码必须是以0、1开头"),
		/** */
		NINE_HUNDRED_AND_NINETY_SEVEN(997, "平台返回找不到超时的短信，该信息是否成功无法确定"),
		/** */
		NINE_HUNDRED_AND_NINETY_eight(998, "由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定"),
		/** 操作频繁 */
		NINE_HUNDRED_AND_NINETY_nine(999, "操作频繁"),
		/** 注册企业不符合要求 */
		LOSE_ONE(-1, "注册企业信息不符合要求");

		private final int value;
		private final String desc;

		SmsReturnCode(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static SmsReturnCode get(int value) {
			for (SmsReturnCode type : SmsReturnCode.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	public static enum SmsSource implements EnumTypeInterface {
		/** 亿美短信网关 */
		SOURCE_EMAY_SMS(1, "亿美短信网关"),
		/** 麦讯通短信网关 */
		SOURCE_MXTONG_SMS(2, "麦讯通短信网关");

		private int value;
		private String desc;

		SmsSource(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static SmsSource get(int value) {
			for (SmsSource source : SmsSource.values()) {
				if (source.value == value) {
					return source;
				}
			}
			return null;
		}

	}

}
