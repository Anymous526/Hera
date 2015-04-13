package com.justinmobile.bmp.cloudboss.merchant.manager;

import com.justinmobile.bmp.cloudboss.merchant.domain.MerchantContractInfo;
import com.justinmobile.core.manager.EntityPageManager;

public interface MerchantContractInfoManager extends EntityPageManager<MerchantContractInfo>{

	void saveOrUpdate(MerchantContractInfo merchantContractInfo);
	
	boolean isContractNumberExist(String contractNumber);

	boolean isContractNumberExist(MerchantContractInfo merchantContractInfo, String contractNumber);
	
}