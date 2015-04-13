package com.vlives.boss.sale.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.sale.domain.SalePloyLog;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

@Repository("salePloyLogDao")
public class SalePloyLogDao extends BaseDaoHibernateImpl<SalePloyLog, Long>{

}
