package com.vlives.quartz.manager;

import com.vlives.quartz.domain.TimingTaskLog;
import com.vlives.quartz.domain.TimingTaskLog.TaskName;

public interface TimingTaskLogManager {
	
	 
	/**
	 * 创建定时任务日志记录
	 * @param taskName
	 * @return
	 */
	public TimingTaskLog create(TaskName taskName);
	
	/**
	 *更新定时任务日子
	 * @param timingTaskLog
	 * @param success
	 * @param desc
	 */
	public void result(TimingTaskLog timingTaskLog,boolean success,String desc);

}
