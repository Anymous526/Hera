/*
 * @(#)SmsAccountDetail.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.quartz.domain;

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
import com.vlives.core.support.hibernate.EnumType;


@TypeDefs({ @TypeDef(name = "task", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.quartz.domain.TimingTaskLog$TaskName") }) })
@Entity
@Table(name = "TIMING_TASK_LOG")
public class TimingTaskLog extends BaseEntity {

	private Long id;
	/**任务名称*/
	private TaskName taskName;	
	/** 开始时间 */ 
	private Date startDate;
	/** 结束时间 */ 
	private Date endDate;
	/** 变更数量 */
	private Boolean success;
	/** 返回码描述 */
	private String resultDesc;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TIMING_TASK_LOG") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Type(type="task")
	@Column(name="TASK_NAME")
	public TaskName getTaskName() {
		return taskName;
	}

	public void setTaskName(TaskName taskName) {
		this.taskName = taskName;
	}

	@Column(name="START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name="END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	@Column(name="RESULT_DESC")
	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}






	public static enum TaskName {
		/**短信*/
		SMS_SEND(1, "营销短信发送、更新营销活动发送短信数,群发电子卷"),
		/**会员评论*/
		MEMBER_COMMENT(2, "会员评论"),
		/**
		 * 
		 */
		MERCHANT_RECOMMEND(3, "推荐商户排序"),
		/**
		 * 商户报表统计
		 */
		MERCHANT_REPORT(4, "商户报表统计、电子券报表统计"),
		
		PLOY_EXPIRED(5, "营销活动过期状态修改、电子卷活动和电子卷过期,");

		private final int value;
		private final String desc;

		TaskName(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static TaskName get(int value) {
			for (TaskName type : TaskName.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

}
