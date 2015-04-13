package com.vlives.boss.sms.manager;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.boss.sms.domain.SmsRecord.SmsSource;
import com.vlives.core.exception.BusinessException;
import com.vlives.util.SendSMSUtils;

@Service("smsSendManager")
public class SmsSendManagerImpl implements SmsSendManager {
	@Autowired
	private SendSmsMultiSupport sendSmsMultiSupport;
	@Autowired
	private SendSmsSupport sendSmsSupport;
	@Autowired
	private SmsRecordManager smsRecordManager;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public long getSmsCount() throws BusinessException {
		try {
			double amount = SendSMSUtils.getBalance();
			BigDecimal count = new BigDecimal(String.valueOf(amount)).movePointRight(1);
			return count.longValue();
		} catch (Exception e) {
			throw new BusinessException("查询余额失败");
		}
	}

	@Override
	public void sendTimelySMS(String mobile, String content) {
		sendSmsMultiSupport.sendTimelySMS(mobile, content, SmsSource.SOURCE_EMAY_SMS);
	}

	@Override
	public boolean sendSms(String mobileNo, String content) {
		return sendSmsSupport.sendSms(mobileNo, content, SmsSource.SOURCE_EMAY_SMS);
	}

	@Override
	public void sendTimelySMSByMxtong(String mobile, String content) {
		sendSmsMultiSupport.sendTimelySMS(mobile, content, SmsSource.SOURCE_MXTONG_SMS);

	}

	/**
	 * 处理从麦讯通接收到得上行信息
	 */
	@Override
	public SmsRecord receive(String mobile, String content) {
		return smsRecordManager.createSms(mobile, content, SmsSource.SOURCE_MXTONG_SMS, true);
	}
}
