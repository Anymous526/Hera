package com.justinmobile.bmp.cloudboss.merchant.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller.HTTP;
import com.justinmobile.bmp.cloudboss.merchant.dao.NotifyInfoDao;
import com.justinmobile.bmp.cloudboss.merchant.domain.MerchantStatus;
import com.justinmobile.bmp.cloudboss.merchant.domain.NotifyInfo;
import com.justinmobile.bmp.cloudboss.merchant.domain.NotifyInfoDto;
import com.justinmobile.bmp.cloudboss.merchant.manager.MerchantInfoManager;
import com.justinmobile.bmp.pms.caller.PmsCaller;

@Service("merchantInfoManager")
public class MerchantInfoManagerImpl implements MerchantInfoManager {
	
	@Autowired
	private NotifyInfoDao notifyInfoDao;

	private static final String SUCCESS = "0";
	
	@SuppressWarnings("unused")
	private static final String FAULT = "1";
	
	private static final String BRANCHCODE = "0000000000";
	
	@Override
	//通知失败，记录失败信息。
	//1.记录失败，前台页面提示该商户通知失败，手动通知；
	//2.记录成功，前台页面提示该商户通知失败，交由后台定时任务处理
	public boolean notifyPms(JSONObject json) {
		NotifyInfoDto notifyInfoDto = getNotifyInfoDtoByJson(json);
		if(!doNotify(notifyInfoDto)){
			NotifyInfo notifyInfo = notifyInfoDao.getByMerchantCode(notifyInfoDto.getMerchantCode());
			if(notifyInfo == null){
				notifyInfo = new NotifyInfo();
			}
			notifyInfo.setMerchantCode(notifyInfoDto.getMerchantCode());
			notifyInfo.setNotifySuccess(NotifyInfo.NOTIFY_FAULT);
			notifyInfo.setNotifyTime(new Date());
			notifyInfo.setNotifyCount(0);
			notifyInfoDao.save(notifyInfo);
			return false;
		}else{
			return true;
		}

	}
	
	private boolean doNotify(NotifyInfoDto notifyInfoDto) {
		try {
			Map<String ,Object> params = new HashMap<String ,Object>();
			params.put("merchantCode", notifyInfoDto.getMerchantCode());
			params.put("merchantName", notifyInfoDto.getMerchantName());
			params.put("mechantShortName", notifyInfoDto.getMechantShortName());
			params.put("merchantType", notifyInfoDto.getMerchantType());
			params.put("branchCode", BRANCHCODE);
			params.put("status", notifyInfoDto.getStatus());
			JSONObject json = PmsCaller.HTTP.MERCHANT_NOTIFY.invoke(null, params);
			if(SUCCESS.equals(json.getString("returnCode"))){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
	}

	private NotifyInfoDto getNotifyInfoDtoByJson(JSONObject json) {
		NotifyInfoDto notifyInfoDto = new NotifyInfoDto();
		JSONObject obj = json.getJSONObject("merchant");
		notifyInfoDto.setMechantShortName(obj.getString("shortName"));
		notifyInfoDto.setMerchantCode(obj.getString("code"));
		notifyInfoDto.setMerchantName(obj.getString("name"));
		if("null" != obj.getString("merchantExtendInfo")){
			String merchantType = obj.getJSONObject("merchantExtendInfo").getString("merchantType");
			if(!"null".equals(merchantType)){
				notifyInfoDto.setMerchantType(merchantType);
			}
		}
		String status = obj.getJSONObject("status").getString("value"); 
		if(String.valueOf(MerchantStatus.STATUS_ACTIVE.getValue()).equals(status)){
			notifyInfoDto.setStatus(NotifyInfoDto.STATUS_ACTIVE);
		}else if(String.valueOf(MerchantStatus.STATUS_FREEZED.getValue()).equals(status)){
			notifyInfoDto.setStatus(NotifyInfoDto.STATUS_FREEZED);
		}else if(String.valueOf(MerchantStatus.STATUS_DISABLE.getValue()).equals(status)){
			notifyInfoDto.setStatus(NotifyInfoDto.STATUS_DELETE);
		}
		return notifyInfoDto;
	}

	@Override
	public void autoNotify() {
		List<NotifyInfo> list = notifyInfoDao.getNotifyList();
		if(list == null || list.size() == 0){
			return;
		}
		boolean flag = false;
		NotifyInfoDto notifyInfoDto = null;
		for(NotifyInfo info : list){
			//通过商户编码取得待通知商户信息,通知PMS
			Map<String ,Object> params = new HashMap<String ,Object>();
			params.put("code", info.getMerchantCode());
			try {
				JSONObject json = HTTP.MERCHANT_READ.invoke(null,params);
				if("null".equals(json.getJSONObject("merchant").toString())){
					System.out.println("请求通知终端管理系统的商户[编号："+ info.getMerchantCode() + "]不存在！");
					continue;
				}
				notifyInfoDto = getNotifyInfoDtoByJson(json);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			} 
			flag = doNotify(notifyInfoDto);
			info.setNotifyCount(info.getNotifyCount()+1);
			if(flag){
				info.setNotifySuccess(NotifyInfo.NOTIFY_SUCCESS);
			}else{
				info.setNotifySuccess(NotifyInfo.NOTIFY_FAULT);
			}
			info.setNotifyTime(new Date());
			notifyInfoDao.save(info);
		}
	}

}