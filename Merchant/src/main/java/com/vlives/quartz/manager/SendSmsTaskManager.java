package com.vlives.quartz.manager;

public interface SendSmsTaskManager {
	
	
    /**
     * 定时任务
     * 扫描过期电子卷和电子卷活动
     * 群发电子卷
     * 
     */
	public void timerSendSms();
}
