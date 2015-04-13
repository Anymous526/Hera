package com.vlives.boss.sms.manager;

import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.boss.sms.domain.SmsRecord.SmsSource;

public interface SmsRecordManager {


	/**
	 * 更新短信发送结果 REQUIRES_NEW
	 * 
	 * @param smsRecord
	 * @param b
	 */
	public void updateResultCode(SmsRecord smsRecord, int b);

	/**
	 * 更新短信发送结果 REQUIRES_NEW
	 * 
	 * @param smsRecord
	 * @param result
	 * @param success
	 */
	public void updateResultCode(SmsRecord smsRecord, String result, boolean success);

	/**
	 * 创建短信记录 REQUIRES_NEW
	 * 
	 * @param mobileNo
	 * @param content
	 * @param smsSource
	 *            短信来源
	 * @param method
	 *            上行，下行
	 * @return
	 */
	public SmsRecord createSms(String mobileNo, String content, SmsSource smsSource, boolean method);

}
