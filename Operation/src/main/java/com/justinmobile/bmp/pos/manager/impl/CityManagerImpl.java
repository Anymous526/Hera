package com.justinmobile.bmp.pos.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.dao.CityDao;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.bmp.pos.manager.CityManager;
import com.justinmobile.core.manager.EntityPageManagerImpl;

@Service("cityManager")
public class CityManagerImpl extends EntityPageManagerImpl<City,CityDao> implements CityManager{

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getOpenCities() throws PlatformException {
		try {
			return this.cityDao.findByProperty("status", City.STATUS_OPEN);
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.QUERY_ERROR , e);
		}
	}

}
