package com.vlives.boss.sale.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.sale.domain.SalePloyUser;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

@Repository("salePloyUserDao")
public class SalePloyUserDao extends BaseDaoHibernateImpl<SalePloyUser, Long>{

}
