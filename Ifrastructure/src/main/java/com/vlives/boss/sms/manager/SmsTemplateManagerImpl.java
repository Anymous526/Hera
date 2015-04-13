package com.vlives.boss.sms.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.domain.CouponPloyType;
import com.vlives.boss.coupon.domain.rulesupport.SendTrigger;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.member.manager.MemberPointDetailManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.manager.OperatorManager;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.support.hibernate.HibernateLazyResolver;
import com.vlives.core.support.thread.ExecutorServiceUtil;
import com.vlives.util.SmsTemplateUtils;

@Service("smsTemplateManager")
public class SmsTemplateManagerImpl implements SmsTemplateManager {

	@Autowired
	private MemberPointDetailManager memberPointDetailManager;
	@Autowired
	private SalePloyManager salePloyManager;
	@Autowired
	private OperatorManager operatorManager;
	@Autowired
	private SmsSendManager smsSendManager;
	@Autowired
	private HibernateLazyResolver hibernateLazyResolver;
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private CouponPloyManager couponPloyManager;
	@Autowired
	private CouponManager couponManager;

	private static final Log LOG = LogFactory.getLog(SmsTemplateManagerImpl.class);

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void registerMemberSms(Member member) {
		// 发送注册营销活动短信
		Operator operator = operatorManager.get(Operator.SYS_POS_ID);
		salePloyManager.sendSmsByCreateMember(member, operator);
		/**
		 * 暂时屏蔽掉注册发短信 Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("partMobileNo",
		 * SmsTemplateUtils.getPartMobileNo(member.getUser().getMobile()));
		 * map.put("shortName", member.getCreateMerchant().getShortName());
		 * String content = SmsTemplateUtils.getStringMessage("pos_register",
		 * map); this.sysNoticeSms(member.getUser().getMobile(),
		 * content,CloudBossCaller.HttpAddress.SEND_SYS_NOTICE_SMS);
		 */
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void tradeSms(TradeDetail tradeDetail) {
		// 消费营销活动短信
		Operator operator = operatorManager.get(Operator.SYS_POS_ID);
		salePloyManager.sendSmsByTrade(tradeDetail, operator);

		this.sendTradeCouponToMember(tradeDetail);

		sendCommentSms(tradeDetail);
	}

	/**
	 * 发送消费点评通知短信
	 * 
	 * @param tradeDetail
	 */
	private void sendCommentSms(TradeDetail tradeDetail) {
		MemberPointDetail point = memberPointDetailManager.getByMemberAndOrder(tradeDetail.getTradeOrder().getMember(),
				tradeDetail.getTradeOrder(), MemberPointDetail.Type.TYPE_REGISTER);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("point", point.getPoint());
		String content = SmsTemplateUtils.getStringMessage("pos_consume", map);
		smsSendManager.sendTimelySMSByMxtong(tradeDetail.getTradeOrder().getMember().getUser().getMobile(), content);
	}

	private void sendTradeCouponToMember(final TradeDetail noSessionTradeDetail) {
		Runnable command = new Runnable() {
			@Override
			public void run() {
				try {
					hibernateLazyResolver.openSession();
					TradeDetail tradeDetail = tradeDetailManager.get(noSessionTradeDetail.getId());
					List<CouponPloy> couponPloys = couponPloyManager.findValidPloyByType(tradeDetail.getTradeOrder()
							.getMerchant(), CouponPloyType.QUOTA_DISCOUNT);
					if (couponPloys.size() == 0)
						return;
					for (CouponPloy couponPloy : couponPloys) {
						try {
							couponManager.receiveCouponBySendRule(tradeDetail.getTradeOrder().getMember().getUser(),
									couponPloy,
									SendTrigger.createQuotaSendTrigger(tradeDetail.getTradeOrder().getMoney()));
						} catch (BusinessException e) {
							LOG.error("消费满额会员发送电子券失败", e);
						}
					}
				} finally {
					//hibernateLazyResolver.flushSession();
				}
			}
		};
		ExecutorServiceUtil.execute(command);
	}

}
