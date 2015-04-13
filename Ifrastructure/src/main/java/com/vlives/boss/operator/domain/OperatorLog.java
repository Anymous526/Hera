/**
 * 
 */
package com.vlives.boss.operator.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 管理员的操作日志
 * @author unicorn
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "operatorType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.operator.domain.OperatorLog$OperatorType") })})

@Entity
@Table(name = "OPERATOR_LOG")
public class OperatorLog extends BaseEntity {
	/** ID**/
	private Long id;
	/** 管理员**/
	private Operator operator;
	/** 操作类型**/
	private OperatorType operatorType;
	/** 创建时间**/
	private Date createDate;
	
	public static enum OperatorType implements EnumTypeInterface {

		TYPE_CREATE_MEMBER(1, "创建会员"),

		TYPE_UPDATE_MEMBER(2, "更新会员"),

		TYPE_FREEZE_MEMBER(3, "冻结会员"),
		
		TYPE_UNFREEZE_MEMBER(4, "解冻会员"),

		TYPE_CREATE_BORDER(5, "创建公告"),

		TYPE_DELETE_BORDER(6, "删除公告"),

		TYPE_SET_POINT_SENDSMS(7, "设置积分交易是否发送短信"),
		
		TYPE_CREATE_ILLEGALWORD(8, "创建非法字"),

		TYPE_DELETE_ILLEGALWORD(9, "删除非法字"),
		
		TYPE_UPDATE_LEVEL(10, "更新商户等级,折扣,升级"),
		
		TYPE_IMPORT_MEMBER(11, "批量导入会员"),
		
		TYPE_OPERATOR_LOGIN(12, "操作员登录"),
		
		TYPE_UPDATE_MERCHANT(13, "操作员登录"),
		
		TYPE_DISABLE_MERCHANT(14, "注销商户"),
		
		TYPE_FREEZE_MERCHANT(15, "冻结商户"),
		
		TYPE_UNFREEZE_MERCHANT(16, "解冻商户"),
		
		TYPE_AUDIT_MERCHANT(17, "审核商户"),
		
		TYPE_UPDATE_PASSWORD(18, "修改密码"),
		
		TYPE_CREATEORUPDATE_TEMPSALEPLOY(19, "创建或更新临时营销活动"),
		
		TYPE_CREATE_SALEPLOY(20, "创建营销活动"),
		
		TYPE_AUDIT_SALEPLOY(21, "审核营销活动"),
		
		TYPE_ADD_SMS(22, "购买短信"),

		TYPE_REDUCE_SMS(23, "使用短信"),
		
		;
		private int value;
		private String desc;

		OperatorType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}
		
		public static OperatorType get(int value) {
			for (OperatorType type : OperatorType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_OPERATOR_LOG") })
	public Long getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	@Cascade(value = CascadeType.ALL)	
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	@org.hibernate.annotations.Type(type="operatorType")
	@Column(name="type")
	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
