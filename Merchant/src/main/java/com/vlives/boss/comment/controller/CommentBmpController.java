package com.vlives.boss.comment.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.comment.domain.MerchantComment.MerchantGrade;
import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * BMP 系统接口
 * @author MrXu
 *
 */
@Controller
@RequestMapping("/manager/platform/comment/")
public class CommentBmpController {

	@Autowired
	private MerchantCommentManager merchantCommentManager;
	@Autowired
	private MerchantManager merchantManager;
    /**
     * 所有评论
     * @param pagination
     * @param operator
     * @return
     * @throws ParseException
     */
	@RequestMapping("list.htm")
	public ModelAndView find(@ObjectConvertAnno Pagination pagination, @ObjectConvertAnno Operator operator)
			throws ParseException {
		Map<String, Object> params = constParams(pagination, operator.getMerchant());
		List<MerchantComment> comments = merchantCommentManager.find(params, pagination);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("comments", comments);
		map.put("success", true);

		return new ModelAndView(new JsonView("bmp_merchant_comment", map));
	}
    /**
     * 商户统计评论
     * @param pagination
     * @param operator
     * @return
     * @throws ParseException
     */
	@RequestMapping("merchant_report.htm")
	public ModelAndView queryComments(@ObjectConvertAnno Pagination pagination, @ObjectConvertAnno Operator operator)
			throws ParseException {
		Map<String, Object> params = constParams(pagination, operator.getMerchant());
		String contactTelephone = HttpParameterParser.getString("mobile");
		if (contactTelephone != null) {
			params.put("contactTelephone", contactTelephone);
		}
		List<Merchant> merchants = merchantManager.findBy(params, pagination);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("merchants", merchants);
		map.put("success", true);

		return new ModelAndView(new JsonView("bmp_merchant_report", map));
	}

	private Map<String, Object> constParams(Pagination pagination, Merchant merchant) {
	
		int grade = HttpParameterParser.getIntValue("grade");
		String merchantName = HttpParameterParser.getString("merchantName");
		String merchantCode = HttpParameterParser.getString("merchantCode");
        int level=HttpParameterParser.getIntValue("level");
		String mobile = HttpParameterParser.getString("mobile");
		Date startDate = HttpParameterParser.getDate("startDate");
		Date endDate = HttpParameterParser.getDate("endDate");

		Map<String, Object> params = new HashMap<String, Object>();
	//	params.put("merchant", merchant);
		if (grade != 0) {
			params.put("merchantGrade", MerchantGrade.get(grade));
		}
		if(level !=0){
			params.put("level", Level.get(level));
		}
		if (merchantName != null) {
			params.put("like_merchantName", merchantName);
			//满足merchant查询
			params.put("name", merchantName);
		}
		if (merchantCode != null) {
			params.put("merchantCode", merchantCode);
		}
		if (mobile != null) {
			params.put("mobile", mobile);
		}
		if (startDate != null)
			params.put("startCreateDate", DateUtils.getEarlyInTheDay(startDate));
		if (endDate != null)
			params.put("endCreateDate", DateUtils.getLateInTheDay(endDate));

		return params;
	}

}
