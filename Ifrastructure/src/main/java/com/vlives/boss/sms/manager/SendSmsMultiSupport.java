package com.vlives.boss.sms.manager;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vlives.boss.sms.domain.SmsRecord.SmsSource;
import com.vlives.core.support.thread.ThreadPool;
import com.vlives.util.SendSMSUtils.SmsPriority;
/**
 * 多线程下发送短信
 * @author MrXu
 *
 */
@Service("sendSmsMultiSupport")
public class SendSmsMultiSupport extends ThreadPool {

	@Autowired
	private SendSmsSupport sendSmsSupport;

	/**
	 * 发送即时短信
	 * 
	 * @param mobile
	 * @param content
	 */
	public void sendTimelySMS(String mobile, String content, SmsSource smsSource) {
		String[] mobiles = new String[0];
		mobiles = (String[]) ArrayUtils.add(mobiles, mobile);
		Object[] params = new Object[] { mobiles, content, smsSource };
		add(params);
	}

	/**
	 * ThreadPool 调用的发送方法
	 * 
	 * @param params
	 */
	private void send(Object[] params) {
		sendSmsSupport.sendSms(String.valueOf(((String[]) params[0])[0]), (String) params[1], (SmsSource) params[2]);
	}

	public void sendSMS(String[] mobiles, String smsContent, SmsPriority smsPriority) {
		sendSMS(mobiles, smsContent, "", smsPriority);
	}

	public void sendSMS(String[] mobiles, String smsContent, String addSerial, SmsPriority smsPriority) {
		Object[] params = new Object[] { mobiles, smsContent, addSerial, smsPriority };
		add(params);
	}

	@Override
	public void execute(Object object) {
		if (object instanceof Object[]) {
			Object[] params = (Object[]) object;
			send(params);
			return;
		}
	}

}
