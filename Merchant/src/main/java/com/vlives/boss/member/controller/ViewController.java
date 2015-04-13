/*
 * @(#)View.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-15
 */
@Controller
@RequestMapping("/manager/member")
public class ViewController {

	@RequestMapping("info/{id}/view.htm")
	public ModelAndView view(@PathVariable Long id){
		ModelAndView model = new ModelAndView("/manager/member/view.jsp");
		Member member = memberManager.get(id);
		model.addObject("member", member);
		return model;
		
	}
	
	@Autowired
	private MemberManager memberManager;
}

