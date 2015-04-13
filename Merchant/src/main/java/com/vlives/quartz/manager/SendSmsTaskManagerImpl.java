package com.vlives.quartz.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.domain.SalePloyUser;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.boss.sale.manager.SalePloyUserManager;
import com.vlives.boss.sms.manager.SmsSendManager;
import com.vlives.core.support.hibernate.HibernateLazyResolver;
import com.vlives.quartz.domain.TimingTaskLog.TaskName;
import com.vlives.quartz.support.QuartzLog;

@Service("sendSmsTaskManager")
public class SendSmsTaskManagerImpl implements SendSmsTaskManager {

	private static final Log LOG = LogFactory.getLog(SendSmsTaskManagerImpl.class);

	@Autowired
	private HibernateLazyResolver hibernateLazyResolver;
	@Autowired
	private CouponManager couponManager;
	@Autowired
	private SalePloyUserManager salePloyUserManager;
	@Autowired
	private SalePloyManager salePloyManager;
	@Autowired
	private SmsSendManager smsSendManager;

	private static long sendCount = 0;

	/** 每次查询取得最大记录数 */
	private static final int EVERY_MAX_COUNT = 3000;

	@Override
	@QuartzLog(name = TaskName.SMS_SEND)
	public void timerSendSms() {
		Date sendDate = new Date();
		//为避免任务轮换，先判读关闭session
		LOG.info("before close session");
		releaseSession();
		
		LOG.info("群发短信：");
		sendCount = 0;
		groupSendSms(sendDate);
		LOG.info("已发送条数:" + sendCount);

		LOG.info("更新短信发送结果：");
		updateSendResult();

		LOG.info("扫描群发电子卷：");
		sendCount = 0;
		sendCouponSms(sendDate);
		
		

	}

	private void releaseSession() {
		try {
			hibernateLazyResolver.releaseSession();
		} catch (Exception e) {
			LOG.info("System close seesion error on timing!");
		}
	}

	/**
	 * 更新所有活动的发送的数量和结果状态
	 * 
	 * @param pagination
	 */
	public void updateSendResult() {
		try {
			hibernateLazyResolver.openSession();

			Object[] ids = salePloyManager.findSendFinallyPloy();
			for (Object id : ids) {
				SalePloy salePloy = salePloyManager.get(((BigDecimal) id).longValue());
				salePloyManager.updateSendResult(salePloy);
			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			releaseSession();
		}

	}

	private void groupSendSms(Date sendDate) {
		try {
			hibernateLazyResolver.openSession();

			List<SalePloyUser> users = salePloyUserManager.findBySms(EVERY_MAX_COUNT, sendDate);
			if (CollectionUtils.isEmpty(users)) {
				return;
			}
			for (SalePloyUser salePloyUser : users) {
				sendCount++;				
			//	Thread.sleep(1000);
				String mobileNo = salePloyUser.getMember().getUser().getMobile();
				SalePloy salePloy = salePloyUser.getSalePloy();
				String content = salePloyUser.getSalePloy().getTemplate();
				boolean flag = smsSendManager.sendSms(mobileNo, content);
				salePloyManager.updateSendStatus(salePloy);
				salePloyUserManager.updatePloyUserResult(salePloyUser, flag);
			}
			LOG.info("已发送短信数：" + sendCount);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			releaseSession();
		}

		// groupSendSms(sendDate);
	}

	/**
	 * 群发电子卷
	 */
	private void sendCouponSms(Date sendDate) {
		try {
			hibernateLazyResolver.openSession();

			List<Coupon> coupons = couponManager.findNeedSendCoupon(sendDate, EVERY_MAX_COUNT);
			LOG.info("查询发送数：" + coupons.size());
			if (CollectionUtils.isEmpty(coupons)) {
				return;
			}
			for (Coupon coupon : coupons) {
				try {
					sendCount++;
					couponManager.sendCoupon(coupon);
				} catch (Exception e) {
					LOG.error("发送错误, 电子卷ID:" + coupon.getId(), e);
				}
			}
			LOG.info("已发送电子劵数：" + sendCount);

		} catch (Exception e) {
			LOG.error(e);
		} finally {
			releaseSession();
		}

	}

}
