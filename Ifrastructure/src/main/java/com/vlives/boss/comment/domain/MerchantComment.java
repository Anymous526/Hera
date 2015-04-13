package com.vlives.boss.comment.domain;

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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
/**
 * 商户评论
 * @author tiger
 *
 */

@TypeDefs({ 
	@TypeDef(name = "merchantGrade", typeClass = EnumType.class,    parameters = { @Parameter(name = "class", value = "com.vlives.boss.comment.domain.MerchantComment$MerchantGrade") ,@Parameter(name = "method" , value = "get" )}),
	@TypeDef(name = "commentSource", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.comment.domain.CommentSource") })
})

@Entity
@Table(name = "MERCHANT_COMMENT")
public class MerchantComment extends BaseEntity {
	/**
	 * 一颗星图片地址
	 */
	public static final String START_RED_SRC="/manager/image/icon/all_star.png";
	/**
	 * 半颗星图片地址
	 */
	public static final String START_HALF_SRC="/manager/image/icon/half_star.png";
	/**
	 * 白星图片地址
	 */
	public static final String START_WHITE_SRC="/manager/image/icon/blank_star.png";	
	
	
	/** 商户评论ID */
	private Long Id;

	/** 被评论的商户 */
	private Merchant merchant;
	/** 评论的用户 */
	private Member member;	
	/** 订单 */
	private TradeDetail tradeDetail;

	/** 评论 */
	private String comments;

	/** 回复 */
	private String reply;

	/** 评论时间 */
	private Date createDate;
	
	/** 评论来源*/
	private CommentSource source;

	/** 回复时间 */
	private Date replyDate;
	/**是否已修改，true：已修改，false 未修改**/
	private boolean modifyed;

	private MerchantGrade merchantGrade;
	
	

	public static enum MerchantGrade implements EnumTypeInterface {
		ONE_GRADE(1, "1 颗星","差评","差得太离谱，非常不满"),
		TWO_GRADE(2, "2 颗星","中评","还行吧，马马虎虎"),
		THREE_GRADE(3, "3 颗星","好评","好，值得一试"),
		FOUR_GRADE(4, "4 颗星","很好","很好，非常满意"),
		FIVE_GRADE(5, "5 颗星","推荐","真的非常不错，向大家推荐");;
		private final int value;
		private final String desc;
		private final String label;
		private final String  comments;
		
		private MerchantGrade(final int value ,final String desc,final String label,final String comments) {
			this.value = value;
			this.desc = desc;
			this.label = label;
			this.comments=comments;
			
		}
		public int getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
		public String getLabel() {
			return label;
		}
		
		public String getComments() {
			return comments;
		}
		public static MerchantGrade get(int value) {
			switch (value) {
			case 1:
				return ONE_GRADE;
			case 2:
				return TWO_GRADE;
			case 3:
				return THREE_GRADE;
			case 4:
				return FOUR_GRADE;
			case 5:
				return FIVE_GRADE;
			default:
				throw new IllegalArgumentException("参数不正确");
			}
		}
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_COMMENT") })
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "REPLY_DATE")
	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	
	
	@Type(type="merchantGrade")
	@Column(name = "MERCHANT_GRADE")
	public MerchantGrade getMerchantGrade() {
		return merchantGrade;
	}

	public void setMerchantGrade(MerchantGrade merchantGrade) {
		this.merchantGrade = merchantGrade;
	}

	/**
	 * 判段是否回复
	 * @return
	 */
	@Transient
	public boolean isHaveReply(){
		return StringUtils.isNullOrEmpty(reply) ? false : true;
	}
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_DETAIL_ID")
	public TradeDetail getTradeDetail() {
		return tradeDetail;
	}

	public void setTradeDetail(TradeDetail tradeDetail) {
		this.tradeDetail = tradeDetail;
	}
	
	@Type(type="commentSource")
	@Column(name="SOURCE")
	public CommentSource getSource() {
		return source;
	}

	public void setSource(CommentSource source) {
		this.source = source;
	}

	/**
	 * 得到具体一条评论的评论图片展示html代码
	 * @return
	 */
	@Transient
	public String getMerchantGradeSrc() {
		StringBuffer gradeSrc = new StringBuffer();
		for(int i = 0;i<merchantGrade.getValue();i++) {
			gradeSrc.append(MerchantComment.getGradeSrc(START_RED_SRC));
		}
		for(int i = 0;i<MerchantGrade.FIVE_GRADE.getValue()-merchantGrade.getValue();i++) {
			gradeSrc.append(MerchantComment.getGradeSrc(START_WHITE_SRC));
		}
		return gradeSrc.toString();
	}
	
	
	/**
	 * 根据商品的评分得到img src
	 * 
	 * @param start
	 * @return
	 */
	public static final String getGradeSrc(String startSrc) {
		return "<img src='" + startSrc + "' width='14' height='14' />";
	}
	
	/**
	 * 获得时间差的描述
	 * @return
	 */
	@Transient
	public String getTimeOutString(){
		return DateUtils.getTimeOutString(this.createDate);
	}

	public boolean isModifyed() {
				return modifyed;
	}

	public void setModifyed(boolean modifyed) {
		this.modifyed = modifyed;
	}

 
	
	
}
