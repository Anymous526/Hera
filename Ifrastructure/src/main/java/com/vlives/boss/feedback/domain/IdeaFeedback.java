package com.vlives.boss.feedback.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vlives.boss.user.domain.User;
import com.vlives.core.support.entity.BaseEntity;

/**
 * 意见反馈信息
 * @author Timmy Cheung
 * @version 创建时间：2011-6-28 上午03:09:01
 * 类说明
 */

@Entity
@Table(name="MEMBER_IDEA_FEEDBACK")
public class IdeaFeedback extends BaseEntity {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 反馈名称
	 */
	private String feedName;
	private String feedContent;//反馈内容
	private User user;//创建人
	private Date createDate;//创建日期
	private int dealStatus;//处理状态
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_IDEA_FEEDBACK") })
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="create_date",insertable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_user")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="deal_status")
	public int getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(int dealStatus) {
		this.dealStatus = dealStatus;
	}
	@Column(name="feed_content")
	public String getFeedContent() {
		return feedContent;
	}
	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}
	@Column(name="feed_name")
	public String getFeedName() {
		return feedName;
	}
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	
}
