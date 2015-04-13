package com.vlives.site.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserFavMerchant;
import com.vlives.boss.user.manager.UserFavoriteManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.site.entity.PromotionObject;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

@Controller
public class MerchantController {

	@Autowired
	private CouponPloyManager couponPloyManager;

	@Autowired
	private MerchantManager merchantManager;

	@Autowired
	private UserFavoriteManager userFavoriteManager;

	@Autowired
	private MerchantCommentManager merchantCommentManager;

	@Autowired
	private TradeDetailManager tradeDetailManager;

	/**
	 * 加载商户页面显示的信息
	 * 
	 * @param id
	 *            商户编号
	 * @param user
	 *            用户对象
	 * @return
	 */
	@RequestMapping(value = "/merchant/{id}", method = RequestMethod.GET)
	public ModelAndView redirectMerchantInfo(@PathVariable("id") Long id,
			@ObjectConvertAnno(required = false) User user) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 获取商户信息
		Merchant merchant = merchantManager.get(id);

		// 获取某商户的优惠活动信息
		List<CouponPloy> couponPloys = couponPloyManager
				.getMerchantCouponPloys(merchant);
		List<PromotionObject> promotions = new ArrayList<PromotionObject>();
		for (CouponPloy couponPloy : couponPloys) {
			PromotionObject promotion = new PromotionObject(couponPloy);
			promotions.add(promotion);
		}
		map.put("promotions", promotions);
		
		// 优惠状态
		merchant.setFavourPloy((couponPloys != null && couponPloys.size() > 0));
		map.put("merchant", merchant);

		String redirectUrl = "/merchant.jsp";
		return new ModelAndView(redirectUrl, map);
	}

	/**
	 * 是否收藏商户
	 * 
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value = "/user/ajax/merchant/favorite", method = RequestMethod.GET)
	public ModelAndView isFavorite(
			@ObjectConvertAnno(required = false) User user) {
		Long[] merchantIds = HttpParameterParser
				.getLongWrapperArray("merchantId");
		Map<String, Boolean> model = new HashMap<String, Boolean>();
		if (user == null) {
			for (Long id : merchantIds) {
				model.put(id.toString(), false);
			}
		} else {
			Map<Long, Boolean> map = userFavoriteManager.isFavorite(user,
					Arrays.asList(merchantIds));
			for (Long id : map.keySet()) {
				model.put(id.toString(), map.get(id));
			}
		}
		return new ModelAndView(new JsonView(model));
	}

	/**
	 * 添加收藏
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/ajax/merchant/favorite", method = RequestMethod.POST)
	public ModelAndView createFavorite(@ObjectConvertAnno User user) {
		// 商户编号
		Long merchantId = HttpParameterParser.getLong("merchantId");
		// 获取商户对象
		Merchant merchant = merchantManager.get(merchantId);
		try {
			userFavoriteManager.create(merchant, user);
			return new ModelAndView(new JsonView(true, String.valueOf(merchant
					.getMerchantReferenceStatistic().getFavCount())));
		} catch (BusinessException be) {
			return new ModelAndView(new JsonView(false, be.getMessage()));
		}
	}

	/**
	 * 删除收藏
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/ajax/merchant/favorite", method = RequestMethod.DELETE)
	public ModelAndView cancelFavorite(@ObjectConvertAnno User user) {
		Long merchantId = HttpParameterParser.getLong("merchantId");
		UserFavMerchant userFavMerchant = userFavoriteManager.get(
				merchantManager.get(merchantId), user);
		userFavoriteManager.delete(userFavMerchant);
		return new ModelAndView(new JsonView(true,
				String.valueOf(userFavMerchant.getMerchant()
						.getMerchantReferenceStatistic().getFavCount())));
	}

	/**
	 * 商户点评
	 * 
	 * @return
	 */
	@RequestMapping("/merchant/ajax/{merchantId}/comments")
	public ModelAndView getComments(@PathVariable Long merchantId,
			@ObjectConvertAnno Pagination pagination) {
		Merchant merchant = merchantManager.get(merchantId);
		Map<String, Object> params = new HashMap<String, Object>();

		// TODO - This is too hack, need to be changed.
		params.put("merchant", merchant);

		List<MerchantComment> list = merchantCommentManager.find(params,
				pagination);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("comments", list);
		map.put("success", true);
		return new ModelAndView(new JsonView("merchant.comments", map));
	}

	/**
	 * 查询登录会员的最新一条未点评消费记录
	 */
	@RequestMapping(value = "/user/ajax/merchant/lastestUncommentedConsume", method = RequestMethod.GET)
	public ModelAndView findunComment(@ObjectConvertAnno User user) {
		Long merchantId = HttpParameterParser.getLong("merchantId");

		Merchant merchant = merchantManager.get(merchantId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("merchant", merchant);
		Pagination pagination = new Pagination();
		pagination.setPageSize(1);
		List<TradeDetail> tradeDetails = tradeDetailManager
				.findNoCommentTradeDetails(params, pagination);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeDetails", tradeDetails);
		map.put("success", CollectionUtils.isEmpty(tradeDetails) ? false
				: true);
		return new ModelAndView(new JsonView("merchant.uncommentOrder", map));
	}
}
