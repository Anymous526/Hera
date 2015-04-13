package com.vlives.boss.border.manager;


import java.util.List;
import java.util.Map;

import com.vlives.boss.border.domain.Border;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

public interface BorderManager {

	public Border getId(Long id);
	/***
	 * 创建公告
	 * @param border 
	 * @throws BusinessException
	 */
	public void create(Border border) throws BusinessException;
	
	/**
	 * 查询公告
	 * @param map
	 * @param pagination
	 * @return 
	 */
	public List<Border> find(Map<String, Object> map, Pagination pagination);
	
	public List<Border> find(int count);
	
	/**
	 * 删除公告
	 * @param border
	 */
	public void delete(Border border);
	
	/**
	 * 首页显示最新公告
	 * @return
	 */
	public List<Border> newsBorder(int maxSize);
	
}
