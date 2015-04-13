package com.vlives.quartz.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.merchant.manager.MerchantRecommendManager;
import com.vlives.boss.report.manager.CityMerchantReportManager;
import com.vlives.boss.report.manager.CouponConsumReportManager;
import com.vlives.boss.report.manager.MerchantReportManager;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.core.support.hibernate.HibernateLazyResolver;
import com.vlives.quartz.domain.TimingTaskLog.TaskName;
import com.vlives.quartz.support.QuartzLog;

@Service("timingTaskManager")
public class TimingTaskManagerImpl implements TimingTaskManager {
	private static final Log LOG = LogFactory.getLog(TimingTaskManagerImpl.class);

	@Autowired
	private SalePloyManager salePloyManager;
	@Autowired
	private HibernateLazyResolver hibernateLazyResolver;
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private MerchantCommentManager merchantCommentManager;
	@Autowired
	private MerchantRecommendManager merchantRecommendManager;
	@Autowired
	private MerchantReportManager merchantReportManager;
	@Autowired
	private CouponManager couponManager;
	@Autowired
	private CouponPloyManager couponPloyManager;
	@Autowired
	private CouponConsumReportManager couponConsumReportManager;
	@Autowired
	private CityMerchantReportManager cityMerchantReportManager;



	/**
	 * 定时任务，发送短信 不需要事物
	 * 
	 * @author MrXu
	 */
	@QuartzLog(name = TaskName.PLOY_EXPIRED)
	public void timerScanExpiredPloy() {
		try {
			//为避免任务轮换，先判读关闭session
			releaseSession();
			hibernateLazyResolver.openSession();
			
			LOG.info("判断营销活动是否已过期：");
			scanExpiredPloy();

			LOG.info("扫描过期电子卷活动：");
			scanExpiredCouponPlog();

			LOG.info("扫描过期电子卷和电子卷活动：");
			scanExpiredCoupon();

		} finally {
			releaseSession();
		}
	}

	private void releaseSession() {
		try {
			hibernateLazyResolver.releaseSession();
		} catch (Exception e) {
			LOG.error("close seesion error on timing!");
		}
	}

	private void scanExpiredPloy() {
		try {			
			List<SalePloy> ploys = salePloyManager.getExpiredSalePloy();
			LOG.info("查询数：" + ploys.size());
			for (SalePloy salePloy : ploys) {
				try {
					salePloyManager.updateExpiredStatus(salePloy);
				} catch (Exception e) {
					LOG.error(e);
				}
			}		
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * 系统自动点评
	 */
	@QuartzLog(name = TaskName.MEMBER_COMMENT)
	public void autoAddComment() {
		try {
			//为避免任务轮换，先判读关闭session
			releaseSession();
			hibernateLazyResolver.openSession();
			List<TradeDetail> tradeDetails = tradeDetailManager.findByMerComment();
			for (int i = 0; i < tradeDetails.size(); i++) {
				try {
					TradeDetail tradeDetail = tradeDetails.get(i);
					merchantCommentManager.createSysComment(tradeDetail);
				} catch (Exception e) {
					LOG.error(e);
				}
			}
		} finally {
			releaseSession();
		}
	}

	/**
	 * 扫描过期活动
	 */
	private void scanExpiredCouponPlog() {
		try {		

			List<CouponPloy> couponPloys = couponPloyManager.findDuePloyToEnd();
			LOG.info("查询数：" + couponPloys.size());
			if (CollectionUtils.isEmpty(couponPloys)) {
				return;
			}
			for (CouponPloy couponPloy : couponPloys) {
				try {
					couponPloyManager.updateDuePloyToEnd(couponPloy);
				} catch (Exception e) {
					LOG.error("错误, 电子卷ID:" + couponPloy.getId(), e);
				}
			}

		
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * 扫描过期电子卷
	 */
	private void scanExpiredCoupon() {
		try {
		
			List<Coupon> coupons = couponManager.findDueCoupon();
			LOG.info("查询数：" + coupons.size());
			if (CollectionUtils.isEmpty(coupons)) {
				return;
			}
			for (Coupon coupon : coupons) {
				try {
					couponManager.updateDueCoupon(coupon);
				} catch (Exception e) {
					LOG.error("错误, 电子卷ID:" + coupon.getId(), e);
				}
			}
		
		} catch (Exception e) {
			LOG.error(e);
		}

		
	}

	@QuartzLog(name = TaskName.MERCHANT_RECOMMEND)
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public void createMerchantRecommend() {
		merchantRecommendManager.createMerchantRecommend();
	}

	@Override
	@QuartzLog(name = TaskName.MERCHANT_REPORT)
	public void createMerchantReoprt() {
		merchantReportManager.createMerchantReoprt();
		couponConsumReportManager.createReoprt();
		cityMerchantReportManager.createReoprt();
	}

}
