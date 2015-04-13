
package com.vlives.boss.trade.domain;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.entity.EnumTypeInterface;
import com.vlives.core.support.hibernate.EnumType;

/**
 * 交易批次
 * @author  fyuan
 * @version 1.0,2011-6-1
 */
@TypeDefs({ @TypeDef(name = "status", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.trade.domain.TradeBatch$Status") }) })
@Entity
@Table(name="TRADE_BATCH")
public class TradeBatch extends BaseEntity{

	/** 编号*/
	private Long id;
	
	/** pos机*/
	private Pos pos;
	
	/** 结算状态*/
	private Status status;
	
	/** 交易批次号*/
	private int batchNumber;
	
	/** 创建时间*/
	private Date createDate;
	
	/** 结算时间*/
	private Date settleDate;
	
	private Set<TradeBatchLog> batchLogs;
	
	public static enum Status implements EnumTypeInterface{
		/** 未结算*/
		TYPE_REGISTER(0, "未结算"),
		/** 结算成功*/
		TYPE_BUY_POINT(1, "结算成功"),
		/** 结算失败 */
		TYPE_CANCEL_POLY(2, "结算失败");

		private int value;
		private String desc;

		Status(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static Status get(int value) {
			for (Status status : Status.values()) {
				if (status.value == value) {
					return status;
				}
			}
			throw new IllegalArgumentException("argument error: " + value);
		}
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TRADE_BATCH") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POS_ID")
	public Pos getPos() {
		return pos;
	}

	public void setPos(Pos pos) {
		this.pos = pos;
	}
	
	@Type(type="status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name="BATCH_NUMBER")
	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="SETTLE_DATE")
	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}
	
	
	
	@OneToMany(mappedBy = "tradeBatch",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<TradeBatchLog> getBatchLogs() {
		return batchLogs;
	}

	public void setBatchLogs(Set<TradeBatchLog> batchLogs) {
		this.batchLogs = batchLogs;
	}

	/**
	 * 添加交易批次变更日志
	 * @param operator
	 * @param endStatus
	 * @param desc
	 */
	@Transient
	public void addTradeBatchLog(Operator operator,Status endStatus,String desc) {
		TradeBatchLog log = new TradeBatchLog();
		log.setStartStatus(this.getStatus());
		log.setEndStatus(endStatus);
		log.setCreateDate(new Date());
		log.setDescription(desc);
		log.setTradeBatch(this);
		log.setOperator(operator);
		if(batchLogs==null){
			batchLogs=new HashSet<TradeBatchLog>();
		}
		batchLogs.add(log);
	}
	
	
	
}


