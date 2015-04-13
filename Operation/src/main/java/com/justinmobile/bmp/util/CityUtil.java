package com.justinmobile.bmp.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SysUserManager;

@Service("cityUtil")
public class CityUtil {

	@Autowired
	private SysUserManager sysUserManager;
	
	public String[] getCityArray(String cities) {
		String[] cityArray = null;
		if(StringUtils.isNotBlank(cities)){
			if(cities.indexOf(",") == -1){
				cityArray = new String[]{cities};
			}else{
				cityArray = cities.split(",");
			}
		}else{
			SysUser user = this.sysUserManager.getUserByLoginName(SpringSecurityUtils.getCurrentUserName());
			cityArray = new String[user.getCitySet().size()];
			int i = 0;
			for(City city : user.getCitySet()){
				cityArray[i] = city.getCityCode();
				i++;
			}

		}
		return cityArray;
	}
}
