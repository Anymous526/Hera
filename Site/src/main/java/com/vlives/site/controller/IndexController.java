package com.vlives.site.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.feedback.domain.IdeaFeedback;
import com.vlives.boss.feedback.manager.IdeaFeedbackManager;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.whatsnew.domain.WhatsNew;
import com.vlives.boss.whatsnew.manager.WhatsNewManager;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.site.util.CityHelper;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * @version 创建时间：2011-6-28 上午05:54:43 类说明
 */

@Controller
public class IndexController {

	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private MerchantCommentManager merchantCommentManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MerchantManager merchantManager;
	@Autowired
	private WhatsNewManager whatsNewManager;
	@Autowired
	private CouponPloyManager couponPloyManager;
	@Autowired
	private IdeaFeedbackManager ideaFeedbackManager;

	/**
	 * 得到网站统计信息
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("/index.jsp");
	}

	/**
	 * 得到网站统计信息
	 * 
	 * @return
	 */
	@RequestMapping("/ajax/statistics")
	public ModelAndView statistic() {

		long consumeCount = tradeDetailManager
				.count(new HashMap<String, Object>());
		long commentCount = merchantCommentManager
				.count(new HashMap<String, Object>());
		long memberCount = memberManager.countTotalMemberCount();
		long merchantCount = merchantManager.countMerchant();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("consumeCount", consumeCount);
		model.put("commentCount", commentCount);
		model.put("memberCount", memberCount);
		model.put("merchantCount", merchantCount);
		return new ModelAndView(new JsonView(model));
	}

	/**
	 * 看新鲜
	 * 
	 * @return
	 */
	@RequestMapping("/ajax/whatsnew")
	public ModelAndView whatsNew() {
		return new ModelAndView(new JsonView("index.whatsnew",
				getWhatsNew(CityHelper.getCurrentCity().getCity().getId())));
	}

	private List<WhatsNew> getWhatsNew(long city) {
		List<WhatsNew> list = whatsNewManager.getWhatsNew(city);
		List<WhatsNew> ret = new LinkedList<WhatsNew>();
		for (WhatsNew whatsNew : list) {
			whatsNew.getMerchant().setFavourPloy(
					couponPloyManager.isMerchantInPromotion(whatsNew
							.getMerchant()));
			ret.add(whatsNew);
		}

		return ret;
	}

	/**
	 * 推荐商户
	 * 
	 * @return
	 */
	@RequestMapping("/ajax/recommend")
	public ModelAndView recommend() {
		List<Merchant> merchants = merchantManager.getTopMerchants(
				CityHelper.getCurrentCity().getCity(), 5);
		for (Merchant merchant : merchants) {
			merchant.setFavourPloy(couponPloyManager
					.isMerchantInPromotion(merchant));
		}
		return new ModelAndView(new JsonView("index.recommend", merchants));
	}

	/**
	 * 最新优惠
	 * 
	 * @return
	 */
	@RequestMapping("/ajax/promotions")
	public ModelAndView getPromotions() {
		List<CouponPloy> list = couponPloyManager.getLastestPromotions(
				CityHelper.getCurrentCity().getCity(), 5);
		for (CouponPloy couponPloy : list) {
			couponPloy.getMerchant().setFavourPloy(true);
		}
		return new ModelAndView(new JsonView("index.promotions", list));
	}

	/**
	 * 意见反馈
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/feedback/", method = RequestMethod.POST)
	public ModelAndView feedback(@ObjectConvertAnno(required = false) User user) {
		String feed_content = HttpParameterParser.getString("template");// 反馈意见内容
		IdeaFeedback objFeedback = new IdeaFeedback();
		objFeedback.setFeedContent(feed_content);
		objFeedback.setUser(user);
		objFeedback.setCreateDate(new java.util.Date());
		ideaFeedbackManager.create(objFeedback);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "反馈意见已经递交成功！");
		return new ModelAndView(new JsonView(model));
	}
}
