package com.vlives.boss.trade.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.trade.dao.TradeTransactionDao;
import com.vlives.boss.trade.domain.TradeTransaction;
import com.vlives.boss.trade.domain.TradeTransaction.TradeReqType;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;

@Service("tradeTransactionManager")
public class TradeTransactionManagerImpl implements TradeTransactionManager {
	@Resource
	private TradeTransactionDao tradeTransactionDao;

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public TradeTransaction create(TradeTransaction tradeTransaction) {
		TradeTransaction trade = this.getTrade(tradeTransaction.getPosNo(), tradeTransaction.getBatchNo(),
				tradeTransaction.getTradeSerialNo());
		if (trade != null) {
			return trade;
		}
		tradeTransactionDao.save(tradeTransaction);
		return tradeTransaction;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TradeTransaction getTrade(String posNo, int batchNo, String tradeSerialNo) {
		String hql = "from TradeTransaction b  where  b.posNo=:posNo and  b.batchNo=:batchNo and b.tradeSerialNo=:tradeSerialNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("posNo", posNo);
		map.put("batchNo", batchNo);
		map.put("tradeSerialNo", tradeSerialNo);
		Finder finder = new SimpleParametersFinder(hql, map);
		return tradeTransactionDao.getBy(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateResult(TradeTransaction trade, String respCode, String respDesc) {
		trade.setRespCode(respCode);
		trade.setRespDesc(respDesc);
		tradeTransactionDao.update(trade);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Object[] getConsumeTotal(String posNo, int batchNo) {
		return this.getTotalCount(posNo, batchNo, true);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Object[] getCancelTradeTotal(String posNo, int batchNo) {
		return this.getTotalCount(posNo, batchNo, false);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private Object[] getTotalCount(final String posNo, final int batchNo, final boolean consumeTrade) {
		String hql = "select count(*) as totalCount, sum(AMOUNT) as totalAoumt from   TRADE_TRANSACTION T  where T.POS_NO=?  and T.BATCH_NO = ?  ";
		if (consumeTrade) {
			hql += " and T.TRADE_REQ_TYPE<>?";
		} else {
			hql += " and T.TRADE_REQ_TYPE=?";
		}
		final String sql = hql;
		return (Object[]) tradeTransactionDao.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Object[] object = (Object[]) session.createSQLQuery(sql).setString(0, posNo).setInteger(1, batchNo)
						.setInteger(2, TradeReqType.CANCEL_TRADE.getValue()).uniqueResult();
				return object;
			}
		});
	}

}
