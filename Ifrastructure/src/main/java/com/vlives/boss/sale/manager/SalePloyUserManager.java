package com.vlives.boss.sale.manager;

import java.util.Date;
import java.util.List;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.domain.SalePloyUser;


public interface SalePloyUserManager {

	public SalePloyUser get(Long id);

	/**
	 * 创建营销活动接收用户
	 * 
	 * @param salePloyUser
	 * @return
	 */
	public void create(List<SalePloyUser> salePloyUsers);

	/**
	 * 查询需要连续发送5次定时活动短信
	 * 自动发送
	 * 状态，类型，未发送成功
	 * @param maxCount 每次取得大数
	 * @param sendDate 发送时间
	 * @return
	 */	
	public List<SalePloyUser> findBySms(int maxCount,Date sendDate);
	/**
	 * 查询营销发送成功短信条数
	 * @param salePloy
	 * @return
	 */
	public long  getSendCount(SalePloy salePloy);
	
	/**
	 * 更新活动会员的短信发送结果
	 * @param salePloyUser
	 * @param success
	 */
	public void updatePloyUserResult(SalePloyUser salePloyUser, boolean success);

}
