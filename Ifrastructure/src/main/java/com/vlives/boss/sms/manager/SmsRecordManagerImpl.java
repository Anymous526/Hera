package com.vlives.boss.sms.manager;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.boss.sms.domain.SmsRecord.SmsReturnCode;
import com.vlives.boss.sms.domain.SmsRecord.SmsSource;
import com.vlives.core.dao.generic.BaseDao;

@Service("smsRecordManager")
public class SmsRecordManagerImpl implements SmsRecordManager {

	@Resource
	private BaseDao<SmsRecord, Long> smsRecordDao;
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public SmsRecord createSms(String mobileNo, String content, SmsSource smsSource, boolean method) {
		SmsRecord smsRecord = new SmsRecord();
		smsRecord.setSendDate(new Date());
		smsRecord.setMobile(mobileNo);
		smsRecord.setContent(content);
		smsRecord.setSmsSource(smsSource);
		smsRecord.setMethod(method);
		smsRecord.setSuccess(false);
		smsRecordDao.save(smsRecord);	
		return smsRecord;
	}



	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updateResultCode(SmsRecord smsRecord, int b) {
		SmsReturnCode code = SmsReturnCode.get(b);
		smsRecord.setReturnCode(code);
		smsRecord.setReturnDesc(code.getDesc());
		smsRecord.setSuccess(b == 0 ? true : false);
		smsRecordDao.update(smsRecord);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updateResultCode(SmsRecord smsRecord, String result, boolean success) {
		smsRecord.setReturnDesc(result);
		smsRecord.setSuccess(success);
		smsRecordDao.update(smsRecord);
	}

	

	
}
