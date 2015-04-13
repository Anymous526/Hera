package com.vlives.boss.whatsnew.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.util.DateUtils;

@Entity
@Table(name = "WHATS_NEW")
public class WhatsNew {

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 会员信息
	 */
	private Member member;

	/**
	 * 商户信息
	 */
	private Merchant merchant;

	/**
	 * 评论信息（如果对应的是消费记录，则为空）
	 */
	private MerchantComment comment;

	/**
	 * 消费信息（如果对应的是评论记录，则为空）
	 */
	private TradeOrder tradeOrder;

	/**
	 * 类型（1、消费 2、评论）
	 */
	private int type;

	/**
	 * 时间
	 */
	private Date datetime;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_WHATS_NEW") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMMENT_ID")
	public MerchantComment getComment() {
		return comment;
	}

	public void setComment(MerchantComment comment) {
		this.comment = comment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_ORDER_ID")
	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	@Column(name = "TYPE")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "DATETIME")
	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	/**
	 * 返回该记录对应的是消费还是点评
	 * @return
	 */
	@Transient
	public String getTypeDesc() {
		if (this.type == 1) {
			return this.getTradeOrder().getType().getDesc();
		} else {
			return "点评";
		}
	}

	/**
	 * 返回消费金额或者消费点评内容
	 * @return
	 */
	@Transient
	public String getContent() {
		if (this.type == 1) {
			return this.getTradeOrder().getMoney().toString() + "元";
		} else {
			return this.getComment().getComments();
		}
	}

	/**
	 * 返回发生时间和现在之间之差
	 * @return
	 */
	@Transient
	public String getTimeFromNow() {
		if (this.type == 1) {
			return DateUtils.getTimeOutString(this.getTradeOrder()
					.getTradeDate());
		} else {
			return DateUtils
					.getTimeOutString(this.getComment().getCreateDate());
		}
	}
}
