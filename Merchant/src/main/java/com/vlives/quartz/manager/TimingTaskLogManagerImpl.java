package com.vlives.quartz.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.core.dao.generic.BaseDao;
import com.vlives.quartz.domain.TimingTaskLog;
import com.vlives.quartz.domain.TimingTaskLog.TaskName;
@Lazy(false)
@Service("timingTaskLogManager")
public class TimingTaskLogManagerImpl implements TimingTaskLogManager{
	@Resource
	private BaseDao<TimingTaskLog, Long> timingTaskLogDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public TimingTaskLog create(TaskName taskName) {
		TimingTaskLog timingTaskLog = new TimingTaskLog();
		timingTaskLog.setStartDate(new Date());
		timingTaskLog.setTaskName(taskName);
		timingTaskLog.setSuccess(false);
		timingTaskLogDao.save(timingTaskLog);
		return timingTaskLog;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void result(TimingTaskLog timingTaskLog, boolean success, String desc) {
		timingTaskLog.setSuccess(success);
		timingTaskLog.setEndDate(new Date());
		timingTaskLog.setSuccess(success);
		timingTaskLog.setResultDesc(desc);
		timingTaskLogDao.update(timingTaskLog);
	}
	
	
 
	
	
}
