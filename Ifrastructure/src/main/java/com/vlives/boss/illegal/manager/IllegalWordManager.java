package com.vlives.boss.illegal.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.vlives.boss.illegal.domain.IllegalWord;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

public interface IllegalWordManager {

	public IllegalWord getId(Long id);
	/***
	 * 创建非法字符
	 * @param illegalWord 非法字符
	 * @throws BusinessException
	 */
	public void create(IllegalWord illegalWord) throws BusinessException, IOException, DocumentException;
	
	/**
	 * 查询非法字符
	 * @param content
	 * @param pagination
	 * @return 
	 */
	public List<IllegalWord> find(Map<String, Object> map, Pagination pagination);
	
	/**
	 * 删除违禁字
	 * @param illegalWord
	 */
	public void delete(IllegalWord illegalWord) throws IOException, DocumentException;
	
	public List<IllegalWord> findAll();
	
	
}
