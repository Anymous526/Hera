package com.vlives.boss.merchant.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.merchant.domain.MerchantCategory;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * @author unicorn
 * @version 1.0,2011-6-2
 */
@Repository("merchantCategoryDao")
public class MerchantCategoryDao extends BaseDaoHibernateImpl<MerchantCategory, Long> {

}
