package com.vlives.quartz.manager;

public interface TimingTaskManager {
	
	/**
	 * 定时任务
	 * 扫描过期活动，电子卷
	 */
	public void timerScanExpiredPloy();
	
	/**
	 * 定时任务对消费15天后交易自动增加点评
	 */
	public void autoAddComment();
	
	/**
	 * 生成商户推荐排行规则
	 *
	 */
	public void createMerchantRecommend();
	/**
	 * 创建商户报表
	 * @author jianguo.xu
	 */
	public void createMerchantReoprt();

}
