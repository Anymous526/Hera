package com.vlives.site.util;

import javax.servlet.http.Cookie;

import com.vlives.site.entity.CityObject;
import com.vlives.util.web.cookie.CookieUtils;

public class CityHelper {

	public static final String CITY_COOKIE_NAME = "city_cookie";

	public static final CityObject[] CITIES = new CityObject[] {
			new CityObject(3l, "北京", "Beijing", CityObject.STATUS_OPENING),
			new CityObject(2222l, "深圳", "Shenzhen", CityObject.STATUS_OPEN),
			new CityObject(2195l, "广州", "Guangzhou", CityObject.STATUS_OPEN),
			new CityObject(2529l, "重庆", "Chongqing", CityObject.STATUS_OPEN),
			new CityObject(2044l, "长沙", "Changsha", CityObject.STATUS_OPEN)
	};

	public static final CityObject DEFAULT_CITY = CITIES[1];
	
	/**
	 * 获得当前城市
	 */
	public static CityObject getCurrentCity() {
		Cookie cityCookie = CookieUtils.getCookie(CITY_COOKIE_NAME);
		if (cityCookie == null) {
			cityCookie = createCityCookie(DEFAULT_CITY.getCity().getId());
			CookieUtils.writeCookie(cityCookie);
		}

		long cityId = Long.parseLong(cityCookie.getValue());
		for (CityObject city : CITIES) {
			if(cityId == city.getCity().getId().longValue()) {
				return city;
			}
		}
		
		return DEFAULT_CITY;
	}

	public static Cookie createCityCookie(long cityId) {
		Cookie cityCookie = new Cookie(CITY_COOKIE_NAME, String.valueOf(cityId));
		cityCookie.setMaxAge(60 * 60 * 24 * 365); // 1年
		cityCookie.setPath("/");
		return cityCookie;
	}
	
	public static void updateCityCookie(long cityId) {
		Cookie cityCookie = CookieUtils.getCookie(CITY_COOKIE_NAME);
		cityCookie.setValue(String.valueOf(cityId));
		cityCookie.setMaxAge(60 * 60 * 24 * 365);
		cityCookie.setPath("/");
		CookieUtils.writeCookie(cityCookie);
	}
}
