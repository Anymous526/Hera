package com.vlives.boss.feedback.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.feedback.domain.IdeaFeedback;
import com.vlives.core.pagination.Pagination;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-6-28 上午09:09:15
 * 类说明
 * 	定义一个反馈信息的接口
 */

public interface IdeaFeedbackManager {
	
	//获取反馈信息
	public IdeaFeedback get(Long id);
	
	/**
	 * 创建 IdeaFeedback对象，保存入数据库中
	 * @param idea
	 * @param opr
	 * @return
	 */
	public void create(IdeaFeedback idea);
	//查找意见反馈信息
	public List<IdeaFeedback> find(Map<String, Object> params,@ObjectConvertAnno Pagination pagination) ;
	
}
