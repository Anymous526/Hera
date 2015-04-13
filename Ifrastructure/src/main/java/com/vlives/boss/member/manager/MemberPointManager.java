/*
 * @(#)MemberPointManager.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.core.pagination.Pagination;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-20
 */
public interface MemberPointManager {

	/**
	 * 查询营销活动 分页
	 * @param params
	 * @param pagination
	 * @return
	 */
	public List<MemberPointDetail> find(Map<String,Object> params,Pagination pagination);
	

	/**
	 * 查询营销活动 ,未分页
	 * @param params
	 * @return
	 */
	public List<MemberPointDetail> find(Map<String,Object> params);
}

