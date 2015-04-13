package com.vlives.boss.sms.manager;


import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.core.exception.BusinessException;

public interface SmsSendManager {
	/**
	 * 发送短信
	 * 
	 * @param mobileNo
	 * @param content
	 * @return
	 */
	public boolean sendSms(String mobileNo, String content);

	/**
	 * 获取亿美短信条数
	 * 
	 * @return
	 */
	public long getSmsCount() throws BusinessException;

	/**
	 * 及时短信发送
	 * 
	 * @param mobile
	 * @param content
	 */
	public void sendTimelySMS(String mobile, String content);


	/**
	 * 及时短信发送接口-麦讯通
	 * @param mobile  手机号码
	 * @param content 短信内容
	 * @param sendDate 发送日期
	 */
	public void sendTimelySMSByMxtong(String mobile, String content);
		
	/**
	 * 处理短信上行发送过来的数据
	 * @param mobile
	 * @param content
	 * @return
	 */
	public SmsRecord receive(String mobile,String content);

}
