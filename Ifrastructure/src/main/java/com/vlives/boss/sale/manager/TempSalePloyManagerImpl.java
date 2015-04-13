package com.vlives.boss.sale.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.sale.domain.TempSalePloy;
import com.vlives.core.dao.generic.BaseDao;

@Service("tempSalePloyManager")
public class TempSalePloyManagerImpl implements TempSalePloyManager {
	@Resource
	private BaseDao<TempSalePloy,Long> tempSalePloyDao;
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(TempSalePloy tempSalePloy) {
		tempSalePloyDao.save(tempSalePloy);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TempSalePloy get(Long id) {
		return tempSalePloyDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(TempSalePloy tempSalePloy) {
		tempSalePloyDao.update(tempSalePloy);
	}


}
