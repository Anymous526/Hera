package com.vlives.boss.user.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.user.domain.UserFavMerchant;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-7 上午06:17:16
 * 类说明
 */

@Repository("userFavMerchantDao")
public class UserFavMerchantDao extends BaseDaoHibernateImpl<UserFavMerchant, Long>  {

}
