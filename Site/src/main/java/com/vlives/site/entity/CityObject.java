package com.vlives.site.entity;

import com.vlives.boss.area.domain.Area;

public class CityObject {
	
	public static final int STATUS_OPEN = 1;
	public static final int	STATUS_OPENING = 2;

	private String nameInPinYin; //城市名（拼音）
	private int status; // 状态：已开通/即将开通
	
	private boolean isCurrent;

	private Area domain;
	
	public CityObject(long cityId, String cityName, String nameInPinYin, int status) {
		domain = new Area();
		domain.setId(cityId);
		domain.setName(cityName);

		this.nameInPinYin = nameInPinYin;
		this.status = status;
	}
	
	public Area getCity() {
		return domain;
	}

	public String getNameInPinYin() {
		return nameInPinYin;
	}

	public int getStatus() {
		return status;
	}
	
	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
}
