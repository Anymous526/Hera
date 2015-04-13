
package com.vlives.boss.member.domain;

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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.user.domain.CardType;
import com.vlives.core.support.entity.BaseEntity;
import com.vlives.core.support.hibernate.EnumType;

/**
 *	会员临时表
 * @author unicorn
 * @version 1.0,2011-6-2
 */
@TypeDefs({ @TypeDef(name = "cardType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.user.domain.CardType") }),
	@TypeDef(name = "level", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vlives.boss.member.domain.Level") })})
@Entity
@Table(name="TEMP_MEMBER")
public class TempMember extends BaseEntity {

	private Long id;
	/**
	 * 所属商户
	 */
	private Merchant merchant;
	/**
	 * 是否绑定了会员
	 */
	private boolean bind;
	/**
	 * 会员卡号
	 */
	private String cardNo;
	/**
	 * 会员等级
	 */
	private Level level;
	/**
	 * 会员积分
	 */
	private int point;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 昵称
	 */
	private String petName;
	/**
	 * 性别
	 */
	private int gender;
	/**
	 * 区域ID
	 */
	private Area area;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 邮编
	 */
	private String post;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * msn
	 */
	private String msn;
	/**
	 *QQ号
	 */
	private String qq;
	/**
	 * 证件类型
	 */
	private CardType cardType;
	/**
	 * 证件号
	 */
	private String cardNumber;
	/**
	 *  编创建日期
	 */
	private Date createDate;
	
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TEMP_MEMBER") })
	public Long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return merchant;
	}


	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Column(name="CARD_NO")
	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Type(type="level")
	@Column(name="MEMBER_LEVEL")
	public Level getLevel() {
		return level;
	}


	public void setLevel(Level level) {
		this.level = level;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name="NIKE_NAME")
	public String getPetName() {
		return petName;
	}


	public void setPetName(String petName) {
		this.petName = petName;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMsn() {
		return msn;
	}


	public void setMsn(String msn) {
		this.msn = msn;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}

	@Type(type="cardType")
	@Column(name="CARD_TYPE")
	public CardType getCardType() {
		return cardType;
	}


	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	@Column(name="CARD_NUMBER")
	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public boolean isBind() {
		return bind;
	}

	public void setBind(boolean bind) {
		this.bind = bind;
	}
	
	

}
