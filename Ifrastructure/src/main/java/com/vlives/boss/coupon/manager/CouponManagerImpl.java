/**
 * @(#)CouponManagerImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.coupon.manager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.coupon.dao.CouponDao;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.Coupon.CouponStatus;
import com.vlives.boss.coupon.domain.Coupon.ReceiveChannel;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloy.CouponPloyStatus;
import com.vlives.boss.coupon.domain.rulesupport.SendTrigger;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.dao.OperatorDao;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.sms.domain.SmsAccountDetail;
import com.vlives.boss.sms.manager.MerchantSmsAccountManager;
import com.vlives.boss.sms.manager.SmsSendManager;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.trade.domain.TradeOrder.Type;
import com.vlives.boss.user.domain.User;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PlaceholderFinder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.thread.ExecutorServiceUtil;
import com.vlives.util.RandomCodeUtils;
import com.vlives.util.RandomCodeUtils.RandomType;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-9-7
 */
@Service("couponManager")
public class CouponManagerImpl implements CouponManager {

	private static final Log LOG = LogFactory.getLog(CouponManagerImpl.class);

	@Resource
	private CouponDao couponDao;
	@Autowired
	private SmsSendManager smsSendManager;
	@Autowired
	private MerchantSmsAccountManager merchantSmsAccountManager;	 
	@Resource
	private OperatorDao operatorDao;
	@Resource
	private BaseDao<CouponPloy, Long> couponPloyDao;
	 
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Coupon get(Long id) {
		return couponDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void receiveCouponBySite(User user, CouponPloy couponPloy) throws BusinessException {
		checkCanReceiveCoupon(user, couponPloy,true);
		Coupon coupon = createCoupon(user, couponPloy, ReceiveChannel.WEB_SITE_DOWNLOAD);
		sendCoupon(coupon);

	}
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void receiveCouponBySendRule(User user,CouponPloy couponPloy,SendTrigger sendTrigger)throws BusinessException{
		if(couponPloy.getCouponSendRule()==null) return;
		int sendCount = couponPloy.getCouponSendRule().getSendRule().calculatSendCount(sendTrigger);
		if(sendCount==0) {
			return;
		}
		for(int i =0;i<sendCount;i++) {
			try {
				checkCanReceiveCoupon(user, couponPloy, false);
				Coupon coupon = createCoupon(user, couponPloy, ReceiveChannel.SEND_RULE_TRIGGER);
				sendCouponSms(coupon);
			}
			catch (Exception e) {
				LOG.info("通过下发规则下发电子券失败.",e);
			}
			 
		}
		
		
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void createCoupon(final CouponPloy couponPloy,final List<User> ployUsers) {
		Runnable command = new Runnable() {
			@Override
			public void run() {
				 for(User user : ployUsers) {
					 try {
						createCoupon(user, couponPloy, ReceiveChannel.SMS_GROUP_SEND);
					} catch (BusinessException e) {
						LOG.error("创建电子券失败 : "+e.getMessage());
					}
				 }
			}
		};
		ExecutorServiceUtil.execute(command);
	}

	private Coupon createCoupon(User user, CouponPloy couponPloy, ReceiveChannel receiveChannel)
			throws BusinessException {
		String code = createCouponCode();
		Coupon coupon = new Coupon();
		coupon.setCode(code);
		coupon.setCouponPloy(couponPloy);
		coupon.setCouponStatus(CouponStatus.UN_SENT);
		coupon.setCreateDate(new Date());
		coupon.setSendDate(new Date());
		coupon.setReceiveChannel(receiveChannel);
		coupon.setSendCount(0);
		coupon.setUser(user);
		coupon.addStatusLog(null, CouponStatus.UN_SENT, "创建电子券");
		couponDao.save(coupon);
		return coupon;
	}

	private String createCouponCode() throws BusinessException {
		for (int i = 0; i < 50; i++) {
			String code = RandomCodeUtils.random(8, RandomType.NUMBER);
			Coupon coupon = getByCode(code);
			if (coupon == null)
				return code;
		}
		throw new BusinessException("计算电子券验证码时间过长，放弃创建");
	}

	private void checkCanReceiveCoupon(User user, CouponPloy couponPloy,boolean siteReceive) throws BusinessException {
		if(couponPloy.getMerchant().getMerchantSmsAccount().getRemainCount()<=0) {
			throw new BusinessException("商户短信不足,无法领用");
		}
		if (!couponPloy.isActive()) {
			throw new BusinessException("活动状态无效");
		}
		
		if (new Date().compareTo(couponPloy.getSendStartDate())<0) {
			throw new BusinessException("活动还未开始发券");
		}
		if (new Date().compareTo(couponPloy.getSendEndDate())>0) {
			throw new BusinessException("优惠券已发放完毕");
		}
		
		if (couponPloy.isExpired()) {
			throw new BusinessException("活动已过期");
		}
		if (couponPloy.getMaxSendCount() != null && couponPloy.getSentCount() >= couponPloy.getMaxSendCount()) {
			throw new BusinessException("活动优惠券已发完");
		}
		if(siteReceive) {
			if (!couponPloy.isDisplayInWeb()) {
				throw new BusinessException("该活动不能领用优惠券");
			}
			if (couponPloy.getMaxReceiveCountByEveryUser() == 0) {
				throw new BusinessException("该活动不能领用优惠券");
			}
			if (countBy(user, couponPloy) >= couponPloy.getMaxReceiveCountByEveryUser()) {
				throw new BusinessException("你已经领取了" + couponPloy.getMaxReceiveCountByEveryUser() + " 张电子券");
			}
		}
		

	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void verifyCoupon(Coupon coupon) throws PosBussinessException {
		if (new Date().compareTo(coupon.getCouponPloy().getValidEndDate()) > 0) {
			throw new PosBussinessException(PosReturnCode.COUPON_EXPIRED_ERROR);
		}
		if (coupon.getCouponStatus() != CouponStatus.UN_CONSUME) {
			throw new PosBussinessException(PosReturnCode.COUPON_NOT_VALID);
		}
		coupon.addStatusLog(coupon.getCouponStatus(), CouponStatus.CONSUMED, "POS检券成功");
		coupon.setCouponStatus(CouponStatus.CONSUMED);
		couponDao.update(coupon);
	}

	private void checkCouponExist(Coupon coupon) throws PosBussinessException {
		if (coupon == null) {
			throw new PosBussinessException(PosReturnCode.COUPON_NOT_EXIST);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Coupon verfyCoupon(User user, CouponPloy couponPloy) throws PosBussinessException {
		Coupon coupon = this.getByUserAndPloy(user, couponPloy);
		checkCouponExist(coupon);
		this.verifyCoupon(coupon);
		return coupon;
	}
   
	/**
    * 查询用户未使用，未过期电子卷
    * @param user
    * @param couponPloy
    * @return
    */
	private Coupon getByUserAndPloy(User user, CouponPloy couponPloy) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Coupon c where c.couponStatus=:couponStatus and c.couponPloy.validEndDate>:today   and    c.user=:user and c.couponPloy=:couponPloy ";
		map.put("couponStatus", CouponStatus.UN_CONSUME);
		map.put("user", user);
		map.put("couponPloy", couponPloy);
		map.put("today", new Date());
		Finder finder = new SimpleParametersFinder(hql, map);
		List<Coupon> list = couponDao.find(finder, 1);
		return (list == null || list.size() == 0) ? null : list.get(0);
	}
	
	private static final String DYNAMIC_QUERY_STRING="select distinct(c) from Coupon c ,CouponPloy ploy ,CouponPloyApplyMerchant cpam where cpam.couponPloy = ploy and c.couponPloy = ploy" +
			"{ and cpam.merchant = :applyMerchant}" +
			"{ and c.user.mobile = :mobile}"+
			"{ and c.couponPloy = :couponPloy}" +
			"{ and c.couponPloy.id = :couponployid}"+
			"{ and c.code= :code}" +
			"{ and c.couponStatus in(:couponStatus)}" +
			"{ and c.couponPloy.couponType = :couponType}"+
			"{ and c.couponPloy.title = :title}"+
			"{ and c.couponPloy.merchant = :merchant}" +
			"{ order by ?sortRule}";

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Coupon> getUserCoupons(User user, CouponStatus[] statuses, Long[] merchantIds, int sorting, Pagination pagination) {
		String hql = "from Coupon c where c.user = :user"
			+ "{ and c.couponStatus in (:couponStatus)}"
			+ "{ and c.couponPloy.merchant.id in (:merchantId)}"
			+ "{ order by ?sortRule}";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		if (statuses != null && statuses.length > 0) {
			params.put("couponStatus", statuses);
		}
		if (merchantIds != null && merchantIds.length > 0) {
			params.put("merchantId", merchantIds);
		}
		if (sorting == 1) {
			params.put("sortRule", "c.createDate");
		} else {
			params.put("sortRule", "c.couponPloy.validEndDate");
		}
	
		Finder finder = new DynamicFinder(hql, params);
		return couponDao.find(finder, pagination);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Coupon> find(Map<String, Object> params, Pagination pagination) {
		params.put("sortRule", "c.createDate desc");
		Finder finder = new DynamicFinder(DYNAMIC_QUERY_STRING, params);
		return couponDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Coupon getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		PropertiesFinder finder = new PropertiesFinder(Coupon.class);
		finder.add("code", code.trim());
		return couponDao.getBy(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Coupon> findNeedSendCoupon(Date sendDate, int maxCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Coupon c where c.couponStatus=:couponStatus " 
			       + " and c.couponPloy.validEndDate>=:today "
			       + " and c.couponPloy.couponPloyStatus=:couponPloyStatus"
				  + "  and  ( c.sendDate is null or c.sendDate<:sendDate ) order by c.sendDate asc ";
		map.put("couponStatus", CouponStatus.UN_SENT);
		map.put("sendDate", sendDate);
		map.put("today", new Date());
		map.put("couponPloyStatus", CouponPloyStatus.AUDIT_SUCCESS);
		Finder finder = new SimpleParametersFinder(hql, map);
		return couponDao.find(finder, maxCount);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void sendCoupon(Coupon coupon) {
		try {
			sendCouponSms(coupon);
		} catch (BusinessException e) {
			LOG.error(e);
			coupon.setSendDate(new Date());
			couponDao.update(coupon);
		}
	}

	private void sendCouponSms(Coupon coupon) throws BusinessException {
		if (coupon.getCouponPloy().getMerchant().getMerchantSmsAccount().getRemainCount() == 0) {
			LOG.info("商户ID：" + coupon.getCouponPloy().getMerchant().getId() + "短信数为0");
			coupon.setSendDate(new Date());
			couponDao.update(coupon);
			return;
		}
		String smsContent = coupon.getCouponPloy().getContent()+" 券号:"+coupon.getCode()+"【会生活】";
		boolean flag = smsSendManager.sendSms(coupon.getUser().getMobile(), smsContent);
		if (!flag) {
			sendCouponFail(coupon);
			return;
		}

		Merchant merchant = coupon.getCouponPloy().getMerchant();
		Operator operator = operatorDao.get(Operator.SYS_OPERATOR_ID);
		merchantSmsAccountManager.reduceSmsCount(merchant, 1, SmsAccountDetail.Type.TYPE_COUPON_SEND, "发送电子卷,扣除商户短信费",
				operator);
		CouponPloy couponPloy = coupon.getCouponPloy();
		couponPloy.increaseSentCount();
		couponPloyDao.update(couponPloy);

		coupon.increaseSendCount();
		coupon.addStatusLog(coupon.getCouponStatus(), CouponStatus.UN_CONSUME, "发送优惠券短信成功，待用户使用");
		coupon.setCouponStatus(CouponStatus.UN_CONSUME);
		coupon.setSendDate(new Date());
		couponDao.update(coupon);
	}

	private void sendCouponFail(Coupon coupon) {
		coupon.increaseSendCount();
		if (coupon.getSendCount() >= 5) {
			coupon.addStatusLog(coupon.getCouponStatus(), CouponStatus.SENT_ERROR, "发送优惠券短信失败");
			coupon.setCouponStatus(CouponStatus.SENT_ERROR);
		}
		coupon.setSendDate(new Date());
		couponDao.update(coupon);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Coupon> findDueCoupon() {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Coupon c where c.couponStatus in (0,1,3) and c.couponPloy.validEndDate<:today ";
		map.put("today", new Date());
		Finder finder = new SimpleParametersFinder(hql, map);
		return couponDao.find(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updateDueCoupon(Coupon coupon) throws BusinessException {
		if (new Date().compareTo(coupon.getCouponPloy().getValidEndDate()) <= 0) {
			throw new BusinessException("电子劵未过期");
		}
		coupon.addStatusLog(coupon.getCouponStatus(), CouponStatus.EXPIRED, "系统自动任务");
		coupon.setCouponStatus(CouponStatus.EXPIRED);
		couponDao.update(coupon);
	}



	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void relatedTradeOrder(Coupon coupon, TradeOrder tradeOrder) throws BusinessException {
		this.checkCouponExist(coupon);
		if (coupon.getCouponStatus() != CouponStatus.CONSUMED) {
			throw new BusinessException("该电子卷未消费");
		}
		if(coupon.getTradeOrder()!=null){
			return;
		}
		coupon.setTradeOrder(tradeOrder);
		couponDao.update(coupon);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long countBy(User user, CouponPloy couponPloy) {
		String hql = "from Coupon c where c.user=? and c.couponPloy = ?";
		Finder finder = new PlaceholderFinder(hql, new Object[] { user, couponPloy });
		return couponDao.count(finder);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long countBy(CouponPloy couponPloy) {
		return couponDao.count("couponPloy", couponPloy);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Coupon> findByUserAndMer(User user, Merchant merchant) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "select c from Coupon c ,CouponPloyApplyMerchant a where c.couponStatus=:couponStatus and c.couponPloy.validEndDate<=:today "
				+ " and c.user=:user   and a.couponPloy=c.couponPloy and a.merchant=:merchant";
		map.put("today", new Date());
		map.put("couponStatus", CouponStatus.UN_CONSUME);
		map.put("user", user);
		map.put("merchant", merchant);
		Finder finder = new SimpleParametersFinder(hql, map);
		return couponDao.find(finder);
	}

	@Override
	public BigDecimal sumConsumeMoney(CouponPloy couponPloy,Merchant tradeMerchant,Type tradeType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("couponPloy", couponPloy);
		if(tradeMerchant!=null) {
			params.put("merchant", tradeMerchant);
		}
		
		if(tradeType !=null) {
			params.put("type", tradeType);
		}
		String hql ="from Coupon c where c.couponPloy = :couponPloy and  c.tradeOrder.merchant = :merchant" +
				"{ and c.tradeOrder.type=:tradeType}";

		Finder finder = new DynamicFinder(hql,params);
		Serializable money = couponDao.sum(finder, "c.tradeOrder.money");
		
		return  money ==null?BigDecimal.ZERO:(BigDecimal)money;
	}

}
