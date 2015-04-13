package com.justinmobile.bmp.cloudboss.merchant.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.cloudboss.merchant.dao.MerchantContractInfoDao;
import com.justinmobile.bmp.cloudboss.merchant.domain.MerchantContractInfo;
import com.justinmobile.core.dao.EntityPageDaoHibernate;

@Repository("merchantContractInfoDao")
public class MerchantContractInfoDaoHibernate extends EntityPageDaoHibernate<MerchantContractInfo> implements MerchantContractInfoDao {

}