package com.vlives.boss.illegal.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.illegal.domain.IllegalWord;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

@Repository("illegalWordDao")
public class IllegalWordDao extends BaseDaoHibernateImpl<IllegalWord, Long> {

}
