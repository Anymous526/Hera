package com.justinmobile.bmp.pos.dao.hibernate;


import org.springframework.stereotype.Repository;

import com.justinmobile.bmp.pos.dao.CityDao;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.core.dao.EntityPageDaoHibernate;

@Repository("cityDao")
public class CityDaoHibernate extends EntityPageDaoHibernate<City> implements CityDao {

}
