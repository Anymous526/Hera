package com.vlives.boss.trade.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.core.dao.generic.BaseDao;

@Service("posTradeLogManager")
public class PosTradeLogManagerImpl implements PosTradeLogManager {

	@Resource
	private BaseDao<PosTradeLog, Long> posTradeLogDao;

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateResult(PosTradeLog log) {		
		posTradeLogDao.update(log);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void createLog(PosTradeLog log) {
		log.setCreateDate(new Date());
		posTradeLogDao.save(log);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateResult(PosTradeLog log, String respCode, String respDesc) {
		log.setRespCode(respCode);
		log.setRespDesc(respDesc);
		posTradeLogDao.update(log);
	}

}
