package com.justinmobile.bmp.cloudboss.merchant.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.cloudboss.merchant.dao.MerchantContractInfoDao;
import com.justinmobile.bmp.cloudboss.merchant.domain.MerchantContractInfo;
import com.justinmobile.bmp.cloudboss.merchant.manager.MerchantContractInfoManager;
import com.justinmobile.core.manager.EntityPageManagerImpl;

@Service("merchantContractInfoManager")
public class MerchantContractInfoManagerImpl extends EntityPageManagerImpl<MerchantContractInfo, MerchantContractInfoDao>
		implements MerchantContractInfoManager {

	@Autowired
	private MerchantContractInfoDao merchantContractInfoDao;

	@Override
	public boolean isContractNumberExist(String contractNumber) {
		List<MerchantContractInfo> list = this.merchantContractInfoDao.findByProperty("contractNumber", contractNumber);
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean isContractNumberExist(MerchantContractInfo merchantContractInfo, String contractNumber) {
		if(contractNumber.equals(merchantContractInfo.getContractNumber())){
			return false;
		}
		return isContractNumberExist(contractNumber);
	}

	@Override
	public void saveOrUpdate(MerchantContractInfo merchantContractInfo) {
		this.merchantContractInfoDao.save(merchantContractInfo);
		
	}

}