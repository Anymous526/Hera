package com.vlives.boss.trade.manager;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.manager.OperatorManager;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.pos.manager.PosManager;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeBatch.Status;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.exception.PosBussinessException;

@Service("tradeBatchManager")
public class TradeBatchManagerImpl implements TradeBatchManager {

	@Resource
	private BaseDao<TradeBatch, Long> tradeBatchDao;
	@Autowired
	private PosManager posManager;
	@Autowired
	private OperatorManager operatorManager;
	

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TradeBatch get(Long id) {
		return tradeBatchDao.get(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TradeBatch getByBatchNo(Pos pos, Integer batchNumber) {
		if (batchNumber == null) {
			return null;
		}
		String hql = "from TradeBatch b  where  b.pos =:pos  and  b.batchNumber=:batchNumber";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pos", pos);
		map.put("batchNumber", batchNumber);
		Finder finder = new SimpleParametersFinder(hql, map);
		return tradeBatchDao.getBy(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int settleTrade(TradeBatch tradeBatch) throws PosBussinessException {		
		tradeBatch.setStatus(Status.TYPE_BUY_POINT);
		tradeBatch.setSettleDate(new Date());

		Operator operator = operatorManager.get(Operator.SYS_POS_ID);
		tradeBatch.addTradeBatchLog(operator, Status.TYPE_BUY_POINT, "结算");

		Pos pos = tradeBatch.getPos();
		pos.increaseBatchNumber();
		posManager.update(pos);
		tradeBatchDao.update(tradeBatch);
		return pos.getBatchNumber();
	}

	

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public TradeBatch createBatch(Pos pos, Integer batchNumber) {
		TradeBatch tradeBatch = this.getByBatchNo(pos, batchNumber);
		if (tradeBatch != null) {
			return tradeBatch;
		}
		Operator operator = operatorManager.get(Operator.SYS_POS_ID);
		tradeBatch = new TradeBatch();
		tradeBatch.setPos(pos);
		tradeBatch.setBatchNumber(batchNumber);
		tradeBatch.setCreateDate(new Date());
		tradeBatch.addTradeBatchLog(operator, Status.TYPE_REGISTER, "创建批次号");
		tradeBatch.setStatus(Status.TYPE_REGISTER);
		tradeBatchDao.save(tradeBatch);
		return tradeBatch;
	}

}
