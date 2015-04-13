package com.justinmobile.bmp.pos.manager;

import java.util.List;

import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.core.manager.EntityPageManager;

public interface CityManager extends EntityPageManager<City>{

	List<City> getOpenCities() throws PlatformException;

}
