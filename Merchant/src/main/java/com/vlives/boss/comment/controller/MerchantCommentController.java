package com.vlives.boss.comment.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.comment.domain.MerchantComment.MerchantGrade;
import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

@Controller
@RequestMapping("/manager/comment/")
public class MerchantCommentController {

	@Autowired
	private MerchantCommentManager merchantCommentManager;

	@RequestMapping("list.htm")
	public ModelAndView list(@ObjectConvertAnno Pagination pagination, @ObjectConvertAnno Operator operator)
			throws ParseException {
		Map<String, Object> params = constParams(pagination, operator.getMerchant());
		List<MerchantComment> list = merchantCommentManager.find(params, pagination);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("list", list);
		map.put("success", true);
		map.put("localGrade", HttpParameterParser.getIntValue("grade"));

		map.put("statistic", operator.getMerchant().getMerchantReferenceStatistic());

		map.put("startCreateDate", params.get("startCreateDate"));
		map.put("endCreateDate", params.get("endCreateDate"));

		ModelAndView model = new ModelAndView("/manager/comment/list.jsp");
		model.addAllObjects(map);
		return model;
	}

	@RequestMapping("detail.htm")
	public ModelAndView loadDetail(@ObjectConvertAnno Operator operator) throws ParseException {
		Long commentId = HttpParameterParser.getLong("id");
		MerchantComment merchantComment = merchantCommentManager.get(commentId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchantComment", merchantComment);
		return new ModelAndView("/manager/comment/reply.jsp", map);
	}

	@RequestMapping(value = "replyCommit.htm", method = RequestMethod.POST)
	public ModelAndView replyCommit(@ObjectConvertAnno Operator operator) throws ParseException, BusinessException {
		Long commentId = HttpParameterParser.getLong("id");
		String replyContent = HttpParameterParser.getString("replyContent");
		MerchantComment merchantComment = merchantCommentManager.get(commentId);
		merchantComment.setReply(replyContent);

		merchantCommentManager.createReply(merchantComment, operator.getMerchant());
		return new ModelAndView(new JsonView(true, "回复成功"));

	}

	private Map<String, Object> constParams(Pagination pagination, Merchant merchant) {
		pagination.setPageSize(10);
		int grade = HttpParameterParser.getIntValue("grade");
		Date startCreateDate = HttpParameterParser.getDate("startCreateDate");
		Date endCreateDate = HttpParameterParser.getDate("endCreateDate");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", merchant);
		if (grade != 0)
			params.put("merchantGrade", MerchantGrade.get(grade));
		if (startCreateDate != null)
			params.put("startCreateDate", DateUtils.getEarlyInTheDay(startCreateDate));
		if (endCreateDate != null)
			params.put("endCreateDate", DateUtils.getLateInTheDay(endCreateDate));

		return params;
	}

}
