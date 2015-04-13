package com.vlives.boss.coupon.manager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.coupon.dao.CouponVerifyTransactionDao;
import com.vlives.boss.coupon.domain.CouponVerifyTransaction;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;

@Service("couponTransManager")
public class CouponTransManagerImpl implements CouponTransManager {

	@Resource
	private CouponVerifyTransactionDao couponVerifyTransactionDao;

	private static final Log LOG = LogFactory.getLog(CouponTransManagerImpl.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private List<Object[]> getStatisticsByTradeBatch(final long tradeBatchId) {
		final String sql = "select  c.coupon_ploy_id, count(c.id)  from  coupon c left join coupon_verify_transaction t on t.coupon_id=c.id  where  "
				+ " t.trade_batch_id=?  group by c.coupon_ploy_id";
		return (List<Object[]>) couponVerifyTransactionDao.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Object[]> object = (List<Object[]>) session.createSQLQuery(sql).setLong(0, tradeBatchId).list();
				return object;
			}
		});

	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void settleCoupon(Map<Long, Long> params, TradeBatch tradeBatch) throws PosBussinessException {
		List<Object[]> statistics = this.getStatisticsByTradeBatch(tradeBatch.getId());
		for (Object[] array : statistics) {
			Long couponPloyId = ((BigDecimal) array[0]).longValue();
			Long couponTotal = ((BigDecimal) array[1]).longValue();
			if (params.get(couponPloyId) != null && (Long) params.get(couponPloyId) == couponTotal) {
				params.remove(couponPloyId);
			} else {
				LOG.error(StringUtils.center("统计结算", 40, "="));
				LOG.error("电子卷活动ID：" + couponPloyId + ";POS统计：" + params.get(couponPloyId) + ";系统统计：" + couponTotal);
				throw new PosBussinessException(PosReturnCode.COUPON_SETTLE_ERROR);
			}

		}
		if (MapUtils.isNotEmpty(params)) {
			throw new PosBussinessException(PosReturnCode.COUPON_SETTLE_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void createCouponTrans(CouponVerifyTransaction couponVerifyTransaction) {
		CouponVerifyTransaction trans = getCouponTrans(couponVerifyTransaction.getSerialNo(),
				couponVerifyTransaction.getTradeBatch());
		if (trans != null) {
			couponVerifyTransaction = trans;
		}
		couponVerifyTransactionDao.save(couponVerifyTransaction);
	}

	private CouponVerifyTransaction getCouponTrans(String serialNo, TradeBatch tradeBatch) {
		if (StringUtils.isBlank(serialNo)) {
			return null;
		}
		PropertiesFinder finder = new PropertiesFinder(CouponVerifyTransaction.class);
		finder.add("serialNo", serialNo);
		finder.add("tradeBatch", tradeBatch);
		return couponVerifyTransactionDao.getBy(finder);
	}

}
