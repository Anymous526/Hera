/**
 * @(#)MerchantReferenceStatistic.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.comment.domain.MerchantComment.MerchantGrade;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 商户相关统计
 * 
 * @author jianguo.xu
 * @version 1.0,2011-4-6
 */
@TypeDefs({ 
	@TypeDef(name = "creditGrade", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.merchant.domain.CreditGrade") })
	 
})
@Entity
@Table(name = "MERCHANT_REFERENCE_STATISTIC")
public class MerchantReferenceStatistic extends BaseEntity {
	private Long id;
	/**
	 * 对应商户
	 */
	private Merchant merchant;

	/**
	 * 评论数
	 */
	private int commentCount;
	/**
	 * 收藏数
	 */
	private int favCount;
	/**
	 * 商户消费数
	 */
	private int consumeCount;
	/**
	 * 信用积分
	 */
	private int creditScore;
	/**
	 * 商户信用等级
	 */
	private CreditGrade creditGrade;
	/**
	 * 一 颗星数(差评)
	 */
	private int oneGrade;
	/**
	 * 两 颗星数(中评)
	 */
	private int twoGrade;
	/**
	 * 三 颗星数(好评)
	 */
	private int threeGrade;

	/**
	 * 四 颗星数(好评)
	 */
	private int fourGrade;
	
	/**
	 * 五 颗星数(好评)
	 */
	private int fiveGrade;

	/**
	 * 创建日期
	 */
	private Date createDate;
	

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_MERCHANT_REF_STATISTIC") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Type(type = "creditGrade")
	@Column(name="CREDIT_GRADE")
	public CreditGrade getCreditGrade() {
		return creditGrade;
	}

	private void setCreditGrade(CreditGrade creditGrade) {
		this.creditGrade = creditGrade;
	}

	@ManyToOne
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Column(name = "CONSUME_COUNT")
	public int getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}

	@Column(name = "ONE_GRADE")
	public int getOneGrade() {
		return oneGrade;
	}

	public void setOneGrade(int oneGrade) {
		this.oneGrade = oneGrade;
	}

	@Column(name = "FAV_COUNT")
	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	@Column(name = "TWO_GRADE")
	public int getTwoGrade() {
		return twoGrade;
	}

	public void setTwoGrade(int twoGrade) {
		this.twoGrade = twoGrade;
	}

	@Column(name = "THREE_GRADE")
	public int getThreeGrade() {
		return threeGrade;
	}

	public void setThreeGrade(int threeGrade) {
		this.threeGrade = threeGrade;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "COMMENT_COUNT")
	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name = "CREDIT_SCORE")
	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	@Column(name = "FOUR_GRADE")
	public int getFourGrade() {
		return fourGrade;
	}

	public void setFourGrade(int fourGrade) {
		this.fourGrade = fourGrade;
	}

	@Column(name = "FIVE_GRADE")
	public int getFiveGrade() {
		return fiveGrade;
	}

	public void setFiveGrade(int fiveGrade) {
		this.fiveGrade = fiveGrade;
	}

	/**
	 * 根据商户评论增加评论数、打分数、信用数 如果好评信用, 一颗星减1，两颗星不变，三颗星加1，四颗星加2，五颗星加3
	 * 
	 * @param comment
	 */
	@Transient
	public void addCommentCount(MerchantComment comment) {
		this.commentCount++;
		MerchantGrade merchantGrade = comment.getMerchantGrade();
		this.creditScore+=merchantGrade.getValue();
		
		this.setCreditGrade(CreditGrade.getByCreditScore(creditScore));
		if (merchantGrade == MerchantGrade.ONE_GRADE) {
			this.oneGrade++;
			return;
		}
		if (merchantGrade == MerchantGrade.TWO_GRADE) {
			this.twoGrade++;
			return;
		}
		if (merchantGrade == MerchantGrade.THREE_GRADE) {
			this.threeGrade++;
			return;
		}
		if (merchantGrade == MerchantGrade.FOUR_GRADE) {
			this.fourGrade++;
			return;
		}
		if (merchantGrade == MerchantGrade.FIVE_GRADE) {
			this.fiveGrade++;
			return;
		}

	}
	
	
	/**
	 * 根据商户评论减少信用度，和评分
	 * @param comment
	 */
	@Transient
	public void removeCommentCount(MerchantComment comment) {
		this.commentCount--;
		MerchantGrade merchantGrade = comment.getMerchantGrade();
		this.creditScore-=merchantGrade.getValue();
		
		this.setCreditGrade(CreditGrade.getByCreditScore(creditScore));
		if (merchantGrade == MerchantGrade.ONE_GRADE) {
			this.oneGrade--;
			return;
		}
		if (merchantGrade == MerchantGrade.TWO_GRADE) {
			this.twoGrade--;
			return;
		}
		if (merchantGrade == MerchantGrade.THREE_GRADE) {
			this.threeGrade--;
			return;
		}
		if (merchantGrade == MerchantGrade.FOUR_GRADE) {
			this.fourGrade--;
			return;
		}
		if (merchantGrade == MerchantGrade.FIVE_GRADE) {
			this.fiveGrade--;
			return;
		}

	}


	/**
	 * 得到平商品打分的平均分 1、按所有效评分总和除以有效评分次数（后台对商品评分分类6个等级，每半颗星为一个等级）
	 * 2、根据计算的平均值采用四舍五法得到最终商品评分 （如：评分平均值为2.75, 则最终评分为3星; 评分平均值为2.60,
	 * 则最终评分为2.5星。）
	 * 
	 * @return
	 */
	@Transient
	public BigDecimal getAverageGrade() {
		int totalGradeCount = getTotalGradeCount();
		if (totalGradeCount == 0)
			return BigDecimal.ZERO;
		return new BigDecimal(getTotalGrade()).divide(new BigDecimal(totalGradeCount), 2, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(2)).setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(2))
				.setScale(1);
	}

	/**
	 * 得到评分次数
	 */
	@Transient
	private int getTotalGradeCount() {
		return oneGrade + twoGrade + threeGrade + fourGrade + fiveGrade;
	}

	/**
	 * 得到总评分
	 * 
	 * @return
	 */
	@Transient
	private int getTotalGrade() {
		return oneGrade * MerchantGrade.ONE_GRADE.getValue() + twoGrade * MerchantGrade.TWO_GRADE.getValue()
				+ threeGrade * MerchantGrade.THREE_GRADE.getValue() + fourGrade * MerchantGrade.FOUR_GRADE.getValue()
				+ fiveGrade * MerchantGrade.FIVE_GRADE.getValue();
	}

	/**
	 * 根据{@link #getAverageGrade()}方法 得到页面上的img src
	 * 
	 * @return
	 */
	@Transient
	public String getAverageGradeSrc() {
		BigDecimal averageGrade = getAverageGrade();
		int allStart = averageGrade.intValue();
		StringBuffer gradeSrc = new StringBuffer();
		for (int i = 0; i < allStart; i++) {
			gradeSrc.append(MerchantComment.getGradeSrc(MerchantComment.START_RED_SRC));
		}
		int noneStart = MerchantGrade.FIVE_GRADE.getValue() - allStart;
		if (averageGrade.compareTo(new BigDecimal(allStart)) == 1) {
			gradeSrc.append(MerchantComment.getGradeSrc(MerchantComment.START_HALF_SRC));
			noneStart--;
		}
		for (int i = 0; i < noneStart; i++) {
			gradeSrc.append(MerchantComment.getGradeSrc(MerchantComment.START_WHITE_SRC));
		}
		return gradeSrc.toString();
	}

	

	/**
	 * 得到商品打分在总打分的百分比率
	 * 
	 * @param grade
	 * @return
	 */
	@Transient
	private double getPercent(int grade) {
		if (grade == 0)
			return 0.00;
		return new BigDecimal(grade).divide(new BigDecimal(getTotalGradeCount()), 2, BigDecimal.ROUND_HALF_UP)
				.movePointRight(2).doubleValue();
	}

	@Transient
	public double getOneGradePercent() {
		return getPercent(oneGrade);
	}

	@Transient
	public double getTwoGradePercent() {
		return getPercent(twoGrade);
	}

	@Transient
	public double getThreeGradePercent() {
		return getPercent(threeGrade);
	}

	@Transient
	public double getFourGradePercent() {
		return getPercent(fourGrade);
	}

	@Transient
	public double getFiveGradePercent() {
		return getPercent(fiveGrade);
	}

	/**
	 * 好评论率
	 * 
	 * @return
	 */
	@Transient
	public double getBestPercent() {
		return this.getFourGradePercent() + this.getFiveGradePercent();
	}
	

}
