/**
 * @(#)Coupon.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.user.domain.User;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-9-6
 */

@TypeDefs({
		@TypeDef(name = "couponStatus", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.Coupon$CouponStatus") }),
		@TypeDef(name = "receiveChannel", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.coupon.domain.Coupon$ReceiveChannel") })

})
@Entity
@Table(name = "COUPON")
public class Coupon extends BaseEntity {
	private Long id;
	/**
	 * 用户
	 */
	private User user;
	/**
	 * 电子券活动
	 */
	private CouponPloy couponPloy;
	/**
	 * 电子券编码
	 */
	private String code;
	/**
	 * 电子券状态
	 */
	private CouponStatus couponStatus;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 交易订单
	 */
	private TradeOrder tradeOrder;
	/**
	 * 电子券领取渠道
	 */
	private ReceiveChannel receiveChannel;
	/**
	 * 短信下发次数
	 */
	private long sendCount;
	
	/**
	 * 发送时间
	 */
	private Date sendDate;

	private Set<CouponLog> couponLogs;

	/**
	 * 电子券状态
	 * 
	 * @author tiger
	 */
	public static enum CouponStatus implements EnumTypeInterface {
		UN_SENT(0, "未下发"), UN_CONSUME(1, "未消费"), CONSUMED(2, "已消费"), SENT_ERROR(3, "下发失败"), EXPIRED(4, "过期");
		private int value;
		private String desc;

		CouponStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static CouponStatus get(int value) {
			for (CouponStatus type : CouponStatus.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	/**
	 * 电子券领取渠道
	 * 
	 * @author tiger
	 * 
	 */
	public static enum ReceiveChannel implements EnumTypeInterface {
		WEB_SITE_DOWNLOAD(0, "网站下载"), SMS_GROUP_SEND(1, "短信群发"),
		SEND_RULE_TRIGGER(2, "触发规则下发");

		private int value;
		private String desc;

		ReceiveChannel(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static ReceiveChannel get(int value) {
			for (ReceiveChannel type : ReceiveChannel.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_COUPON") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUPON_PLOY_ID")
	public CouponPloy getCouponPloy() {
		return couponPloy;
	}

	public void setCouponPloy(CouponPloy couponPloy) {
		this.couponPloy = couponPloy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "STATUS")
	@Type(type = "couponStatus")
	public CouponStatus getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(CouponStatus couponStatus) {
		this.couponStatus = couponStatus;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_ORDER_ID")
	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	@Type(type = "receiveChannel")
	@Column(name = "RECEIVE_CHANNEL")
	public ReceiveChannel getReceiveChannel() {
		return receiveChannel;
	}

	public void setReceiveChannel(ReceiveChannel receiveChannel) {
		this.receiveChannel = receiveChannel;
	}

	@Column(name = "SEND_COUNT")
	public long getSendCount() {
		return sendCount;
	}

	public void setSendCount(long sendCount) {
		this.sendCount = sendCount;
	}

	@OneToMany(mappedBy = "coupon", cascade = { CascadeType.ALL })
	@OrderBy("createDate desc")
	public Set<CouponLog> getCouponLogs() {
		return couponLogs;
	}

	public void setCouponLogs(Set<CouponLog> couponLogs) {
		this.couponLogs = couponLogs;
	}

	/**
	 * 添加状态变更日志
	 * 
	 * @param startStatus
	 *            开始状态
	 * @param endStatus
	 *            结束状态
	 * @param desc
	 *            变更原因
	 */
	@Transient
	public void addStatusLog(CouponStatus startStatus, CouponStatus endStatus, String desc) {
		CouponLog log = new CouponLog();
		log.setStartStatus(startStatus);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setEndStatus(endStatus);
		log.setCoupon(this);
		if (couponLogs == null) {
			this.couponLogs = new HashSet<CouponLog>();
		}
		couponLogs.add(log);
	}
    
	/**
	 * 增加短信发送数
	 */
	@Transient
	public void increaseSendCount() {
		if (sendCount <= 5) {
			this.sendCount++;
		}
	}
    
	@Column(name="SEND_DATE")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	
	
	
	

}
