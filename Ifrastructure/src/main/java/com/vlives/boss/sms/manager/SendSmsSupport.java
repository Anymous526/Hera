package com.vlives.boss.sms.manager;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.boss.sms.domain.SmsRecord.SmsSource;
import com.vlives.util.ConfigUtils;
import com.vlives.util.SendSMSUtils;
import com.vlives.util.WebServiceUtils;

/**
 * 短信发送组件
 * @author MrXu
 *
 */
@Component("sendSmsSupport")
public class SendSmsSupport {

	private static final Log LOG = LogFactory.getLog(SendSmsSupport.class);

	@Autowired
	private SmsRecordManager smsRecordManager;

	public boolean sendSms(String mobileNo, String content, SmsSource smsSource) {
		SmsRecord smsRecord = smsRecordManager.createSms(mobileNo, content, smsSource, false);
		if (smsSource.equals(SmsSource.SOURCE_EMAY_SMS)) {
			return sendSmsByemay(smsRecord);
		} else if (smsSource.equals(SmsSource.SOURCE_MXTONG_SMS)) {
			return sendSmsByMxt(smsRecord);
		}
		return false;
	}

	private boolean sendSmsByemay(SmsRecord smsRecord) {
		// TODO 测试开关
		int b = 17;
		try {
			if (ConfigUtils.SMS_SEND) {
				b = SendSMSUtils.sendSMS(smsRecord.getMobile(), smsRecord.getContent());
			} else {
				// TODO 测试返回成功
				b = 0;
			}
			if (b == 0) {
				smsRecordManager.updateResultCode(smsRecord, b);
				LOG.info("发送短信结果：" + smsRecord.getMobile() + ":  成功");
				return true;
			}
			smsRecordManager.updateResultCode(smsRecord, b);
			LOG.info("发送短信结果：" + smsRecord.getMobile() + ": 失败，错误编码：" + b);
			return false;
		} catch (Exception e) {

			smsRecordManager.updateResultCode(smsRecord, "连接异常" + this.getErrorMessage(e), false);
			LOG.info("emay关连接异常");
			LOG.error(e);
			return false;
		}
	}

	private String getErrorMessage(Exception e) {
		String msg = e.getMessage().length() > 100 ? e.getMessage().substring(0, 100) : e.getMessage();
		return msg;
	}

	private boolean sendSmsByMxt(SmsRecord smsRecord) {
		List<String> result = null;
		try {
			if (ConfigUtils.SMS_SEND) {
			result = WebServiceUtils.directSend(smsRecord.getMobile(), smsRecord.getContent());
			}else{
				result=new ArrayList<String>();
				result.add(0, "Sucess");
			}
			if (result.get(0).equals("Sucess")) {
				LOG.info("发送短信结果：" + smsRecord.getMobile() + ":  成功");
				smsRecordManager.updateResultCode(smsRecord, result.toString(), true);
				return true;
			}
			smsRecordManager.updateResultCode(smsRecord, result.toString(), false);
			LOG.info("发送短信结果：" + smsRecord.getMobile() + ": 失败，错误描述：" + result.toString());
			return false;

		} catch (Exception e) {
			smsRecordManager.updateResultCode(smsRecord, "连接异常" + this.getErrorMessage(e), false);
			LOG.info("麦讯通网关连接异常");
			LOG.error(e);
			return false;
		}

	}

}
