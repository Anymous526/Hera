package com.justinmobile.bmp.cloudboss.merchant.manager;

import net.sf.json.JSONObject;

public interface MerchantInfoManager {
	
	/**
	 * 创建商户成功、冻结、解冻或注销商户成功后，需要通知PMS系统，如通知失败，记录相关信息
	 */
	public boolean notifyPms(JSONObject json); 
	
	/**
	 * 将通知失败的信息定时再次通知，失败记录累计通知超过20次仍未成功的，不再通知
	 */
	public void autoNotify();
	
}