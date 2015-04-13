package com.vlives.boss.area.manager;

import java.util.List;

import com.vlives.boss.area.domain.Area;
import com.vlives.core.exception.BusinessException;

public interface AreaManager {
	public Area get(Long id);

	/**
	 * 根据parent查询其子类
	 * @param parent 可以为null表示查询一级
	 * @return
	 * @throws BusinessException
	 */
	public List<Area> getChildren(Area parent) throws BusinessException;
	
}