package com.vlives.boss.area.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.area.domain.Area;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;


@Repository("areaDao")
public class AreaDao extends BaseDaoHibernateImpl<Area,Long> {

}
