package com.vlives.boss.merchant.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-7 上午10:15:44
 * 类说明
 */
@Entity
@Table(name="MERCHANT_RECOMMEND")
public class MerchantRecommend {

	private Long id;
	private Long sortId;
	private Date curDate;
	private Set<Merchant> merchants;
	
	@Column(name="CUR_DATE")
	public Date getCurDate() {
		return curDate;
	}
	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}
	@Id
	@GeneratedValue(generator="sequence")
	@GenericGenerator(name = "sequence",strategy = "sequence",parameters = {@Parameter(name="sequence",value="SEQ_MER_RECOMMEND")})
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="SORT_ID")
	public Long getSortId() {
		return sortId;
	}
	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}
	@SuppressWarnings("unused")
	@OneToMany(mappedBy = "merchantRecommend")
	private Set<Merchant> getMerchants() {
		return merchants;
	}
	@SuppressWarnings("unused")
	private void setMerchants(Set<Merchant> merchants) {
		this.merchants = merchants;
	}
	@Transient
	public Merchant getMerchant(){
		if(merchants == null || merchants.size() == 0){
			return null;
		}
		return merchants.iterator().next();
	}
	
	@Transient
	public void setMerchant(Merchant merchant){
		merchants = new HashSet<Merchant>();
		merchants.add(merchant);
		merchant.setMerchantRecommend(this);		
	}
		
}
