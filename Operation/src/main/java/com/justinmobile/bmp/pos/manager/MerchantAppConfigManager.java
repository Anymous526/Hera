package com.justinmobile.bmp.pos.manager;

import java.util.List;

import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.core.manager.EntityPageManager;

public interface MerchantAppConfigManager extends EntityPageManager<MerchantAppConfig>{
	
		
	/**根据商户编号获取所有应用列表
	 * @param merchantCode
	 * @return
	 * @throws PlatformException
	 */
	public List<MerchantAppConfig> getAllConfigByMerchantCode(String merchantCode) throws PlatformException;
	
	
	
	/**根据商户编号获取有效应用列表
	 * @param merNo
	 * @return
	 */
	public List<MerchantAppConfig> getAppConfigByMerno(String merNo);
	
	
}