package com.vlives.boss.border.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.border.domain.Border;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

@Repository("borderDao")
public class BorderDao extends BaseDaoHibernateImpl<Border, Long> {

}
