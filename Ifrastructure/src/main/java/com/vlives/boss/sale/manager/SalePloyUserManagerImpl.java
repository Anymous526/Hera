package com.vlives.boss.sale.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.domain.SalePloy.SalePloyType;
import com.vlives.boss.sale.domain.SalePloy.Status;
import com.vlives.boss.sale.domain.SalePloyUser;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;

@Service("salePloyUserManager")
public class SalePloyUserManagerImpl implements SalePloyUserManager {

	@Resource
	private BaseDao<SalePloyUser, Long> salePloyUserDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SalePloyUser get(Long id) {
		return salePloyUserDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(List<SalePloyUser> salePloyUsers) {
		salePloyUserDao.saveAll(salePloyUsers);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long getSendCount(SalePloy salePloy) {
		String hql = "from SalePloyUser s where s.sendSuccess=:sendSuccess and s.salePloy=:salePloy   ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sendSuccess", true);
		map.put("salePloy", salePloy);
		Finder finder = new SimpleParametersFinder(hql, map);
		return salePloyUserDao.count(finder);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SalePloyUser> findBySms(int maxCount, Date sendDate) {
		String hql = "select s  from  SalePloyUser s inner join  s.salePloy p where ( s.sendSuccess is null ) and   p.salePloyType in (:audit,:timing)  and p.status in (:status ,:send )"
				+ " and  ( p.timingTime is null or  p.timingTime<=:timingTime ) "
				+ " and  ( s.sendDate is null or s.sendDate<=:sendTime) order by s.sendDate asc ";
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("audit", SalePloyType.AUDIT_PASS_SEND);
		map.put("timing", SalePloyType.TIMING_SEND);

		map.put("status", Status.AUDIT_SUCCESS);
		map.put("send", Status.SEND_SUCCESS);

		map.put("timingTime", new Date());

		map.put("sendTime", sendDate);
		Finder finder = new SimpleParametersFinder(hql, map);
		return salePloyUserDao.find(finder, maxCount);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updatePloyUserResult(SalePloyUser salePloyUser, boolean success) {
		int count = salePloyUser.getSendCount() + 1;
		salePloyUser.setSendCount(count);
		salePloyUser.setSendDate(new Date());
		if (success) {
			salePloyUser.setSendSuccess(true);
		} else {
			if (count >= 5) {
				salePloyUser.setSendSuccess(false);
			}
		}
		salePloyUserDao.update(salePloyUser);
	}

}
