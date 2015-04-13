/*
 * @(#)TradeController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.site.controller;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.comment.domain.CommentSource;
import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.comment.domain.MerchantComment.MerchantGrade;
import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.Coupon.CouponStatus;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.AuthenticationEntry.EntryItem;
import com.vlives.boss.user.domain.CardType;
import com.vlives.boss.user.domain.RelationAccount;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserFavMerchant;
import com.vlives.boss.user.manager.RelationAccountManager;
import com.vlives.boss.user.manager.UserFavoriteManager;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.core.web.filter.cache.Cache;
import com.vlives.site.util.ImageHepler;
import com.vlives.util.ConfigUtils;
import com.vlives.util.FileUploadUtils;
import com.vlives.util.ImageUtils;
import com.vlives.util.JSONUtils;
import com.vlives.util.ImageUtils.FormatType;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private MemberManager memberManager;

	@Autowired
	private RelationAccountManager relationAccountManager;

	@Autowired
	private MerchantCommentManager merchantCommentManager;

	@Autowired
	private MerchantManager merchantManager;
	
	@Autowired
	private CouponManager couponManager;

	@Autowired
	private UserFavoriteManager userFavoriteManager;

	@Autowired
	private TradeDetailManager tradeDetailManager;

	/**
	 * 用户页面 - 用户统计信息(ajax)
	 */
	@Cache
	@RequestMapping("/ajax/statistics")
	public ModelAndView getUserStatistics(@ObjectConvertAnno User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		// 用户点评总数
		Long commentCount = merchantCommentManager.count(params);
		// 会员商户总数
		Long memberMerchantCount = memberManager.countMerchants(user);
		// 收藏商户总数
		Long favoriteMerchantCount = userFavoriteManager.count(user);
		Map<String, Long> model = new HashMap<String, Long>();
		model.put("commentCount", commentCount);
		model.put("memberMerchantCount", memberMerchantCount);
		model.put("favoriteMerchantCount", favoriteMerchantCount);
		// return new ModelAndView("userStatistics", model);
		return new ModelAndView(new JsonView("user.statistics", model));
	}

	/**
	 * 用户页面 - 会员商户
	 * 
	 * @return
	 */
	@RequestMapping("/memberMerchants")
	public ModelAndView getMemberMerchants(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		return new ModelAndView("/user/member_merchants.jsp",
				getUserModel(user));
	}

	/**
	 * 用户页面 - 会员商户 (Ajax)
	 * 
	 */
	@RequestMapping("/ajax/memberMerchants")
	public ModelAndView getMemberMerchantsAjax(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		// 会员商户列表
		List<Merchant> merchants = merchantManager.findMerchantsByMember(user,
				pagination);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pagination", pagination);
		model.put("merchants", merchants);
		return new ModelAndView(new JsonView("user.memberMerchants", model));
	}

	/**
	 * 用户页面 - 收藏商户
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/favoriteMerchants")
	public ModelAndView getFavoriteMerchants(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		return new ModelAndView("/user/favorite_merchants.jsp",
				getUserModel(user));
	}

	/**
	 * 用户页面 - 收藏商户(Ajax)
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/ajax/favoriteMerchants")
	public ModelAndView getFavoriteMerchantsAjax(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {

		// 收藏商户列表
		List<UserFavMerchant> userFavMerchants = userFavoriteManager.find(user,
				pagination);
		List<Merchant> merchants = new ArrayList<Merchant>();
		for (UserFavMerchant userFavMerchant : userFavMerchants) {
			merchants.add(userFavMerchant.getMerchant());
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pagination", pagination);
		model.put("merchants", merchants);
		return new ModelAndView(new JsonView("user.favoriteMerchants", model));
	}
	
	/**
	 * 我的优惠券
	 * 
	 */
	@RequestMapping("/coupons")
	public ModelAndView getCoupons(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		return new ModelAndView("/user/coupons.jsp", getUserModel(user));
	}
	
	/**
	 * 我的优惠券
	 * 
	 */
	@RequestMapping("/ajax/coupons")
	public ModelAndView getCouponsAjax(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {

		// 优惠券状态
		CouponStatus[] couponStatuses = new CouponStatus[] {
				CouponStatus.UN_CONSUME,
				CouponStatus.CONSUMED,
				CouponStatus.EXPIRED
		};
		Integer status = HttpParameterParser.getInteger("couponStatus");
		if (status != null) {
			couponStatuses = new CouponStatus[] { CouponStatus.get(status) } ;
		}
		
		// 商户
		Long[] merchantIds = HttpParameterParser.getLongWrapperArray("couponMerchant");

		// 排序
		int sortingOption = HttpParameterParser.getIntValue("couponSortingOption", 1);

		List<Coupon> coupons = couponManager.getUserCoupons(user, couponStatuses, merchantIds, sortingOption, pagination);
		
		// 获得优惠券所属商户和状态集合
		List<Merchant> merchants = getCouponMerchants(coupons);
		List<CouponStatus> statuses = getCouponStatuses(coupons);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pagination", pagination);
		model.put("coupons", coupons);
		model.put("merchants", merchants);
		model.put("statuses", statuses);
		return new ModelAndView(new JsonView("user.coupons", model));
	}

	/**
	 * 用户页面 - 消费列表
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/consumes")
	public ModelAndView getUserConsumes(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		return new ModelAndView("/user/consumes.jsp", getUserModel(user));
	}

	/**
	 * 用户页面 - 消费列表(Ajax)
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/ajax/consumes")
	public ModelAndView getUserConsumesAjax(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("consumeTrade", true);
		List<TradeDetail> tradeDetails = tradeDetailManager.find(params,
				pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tradeDetails", tradeDetails);
		model.put("pagination", pagination);
		model.put("success", CollectionUtils.isEmpty(tradeDetails) ? false
				: true);
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("user", user);
		// params.put("consumeTrade", true);
		// List<TradeDetail> tradeDetails = tradeDetailManager.find(params,
		// pagination);
		//
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("pagination", pagination);
		// model.put("consumes", tradeDetails);
		return new ModelAndView(new JsonView("user.consumes", model));
	}

	/**
	 * 用户页面 - 未点评消费列表
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/uncommentedConsumes")
	public ModelAndView getUncommentedConsumes(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		return new ModelAndView("/user/uncommented_consumes.jsp",
				getUserModel(user));
	}

	/**
	 * 用户页面 - 未点评消费列表(Ajax)
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/ajax/uncommentedConsumes")
	public ModelAndView getUncommentedConsumesAjax(
			@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		List<TradeDetail> trades = tradeDetailManager.getUncommentTrades(user,
				pagination);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tradeDetails", trades);
		model.put("pagination", pagination);
		return new ModelAndView(new JsonView("user.consumes", model));
	}

	/**
	 * 用户页面 - 点评列表
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/comments")
	public ModelAndView getComments(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		return new ModelAndView("/user/comments.jsp", getUserModel(user));
	}

	/**
	 * 用户页面 - 点评列表(Ajax)
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/ajax/comments")
	public ModelAndView getCommentsAjax(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		List<MerchantComment> comments = merchantCommentManager.find(params,
				pagination);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pagination", pagination);
		model.put("comments", comments);
		return new ModelAndView(new JsonView("user.comments", model));
	}
	
	/**
	 * 点评商户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/merchant/comment", method = RequestMethod.POST)
	public ModelAndView createComment(@ObjectConvertAnno User user) {
		Long tradeDetailId = HttpParameterParser.getLong("detailId");
		MerchantGrade merchantGrade = MerchantGrade.get(HttpParameterParser
				.getInteger("merchantGrade"));
		String comments = HttpParameterParser.getString("comments");
		TradeDetail tradeDetail = tradeDetailManager.get(tradeDetailId);
		try {
			Member member = tradeDetail.getTradeOrder().getMember();
			if (!member.getUser().equals(user)) {
				throw new BusinessException("该消费记录不该会员的消费");
			}
			if (merchantCommentManager.getByTradeDetail(tradeDetail) != null) {
				throw new BusinessException("该消费记录已经评论");
			}
			MerchantComment comment = new MerchantComment();
			comment.setTradeDetail(tradeDetail);
			comment.setMerchant(tradeDetail.getTradeOrder().getMerchant());
			comment.setCreateDate(new Date());
			comment.setMerchantGrade(merchantGrade);
			comment.setMember(tradeDetail.getTradeOrder().getMember());
			comment.setComments(comments);
			comment.setSource(CommentSource.SOURCE_SITE);
			comment.setCreateDate(new Date());
			merchantCommentManager.create(comment);
			return new ModelAndView(new JsonView(true, "创建评论成功！"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false, "创建评论失败！"));
		}
	}
	
	/**
	 * 修改点评
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/merchant/comment", method = RequestMethod.PUT)
	public ModelAndView modifyComment(@ObjectConvertAnno User user)throws BusinessException  {
		Long commentId = HttpParameterParser.getLong("commentId");
		String comments = HttpParameterParser.getString("comments");
		MerchantGrade merchantGrade = MerchantGrade.get(HttpParameterParser.getInteger("merchantGrade"));
		MerchantComment merchantComment = merchantCommentManager.get(commentId);
		
		if (!merchantComment.getMember().getUser().equals(user)) {
			throw new BusinessException("该消费记录不该会员的消费");
		}
		
		try {
			merchantComment.setComments(comments);
			merchantCommentManager.modifyComment(merchantComment,merchantGrade);
			return new ModelAndView(new JsonView(true, "修改评论成功！"));
		} catch (Exception e) {
			return new ModelAndView(new JsonView(false, "修改评论失败！"));
		}
	}

	/**
	 * 用户页面 - 积分列表
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/points")
	public ModelAndView getPoints(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		return new ModelAndView("/user/points.jsp", getUserModel(user));
	}

	/**
	 * 用户页面 - 积分列表(Ajax)
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/ajax/points")
	public ModelAndView getPointsAjax(@ObjectConvertAnno User user,
			@ObjectConvertAnno Pagination pagination) {
		List<Member> memberPoints = memberManager.findPoint(user, pagination);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("memberPoints", memberPoints);
		model.put("pagination", pagination);

		return new ModelAndView(new JsonView("user.points", model));
	}

	/**
	 * 用户首页 - 我的设置，帐号绑定
	 * 
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView getUserInfo(@ObjectConvertAnno User user) {
		return new ModelAndView("/user/user_info.jsp", getUserModel(user));
	}

	/**
	 * 用户首页 - 我的设置，帐号绑定
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/info", method = RequestMethod.GET)
	public ModelAndView getUserInfoAjax(@ObjectConvertAnno User user) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		return new ModelAndView(new JsonView("user", model));
	}

	/**
	 * 用户修改基本信息(Ajax)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/info", method = RequestMethod.POST)
	public ModelAndView saveUserInfoAjax(@ObjectConvertAnno User user) {
		String name = HttpParameterParser.getString("xingming");
		// String head = HttpParameterParser.getString("head");
		String petName = HttpParameterParser.getString("nickname");
		// String areaId = HttpParameterParser.getString("areaId");
		int gender = HttpParameterParser.getIntValue("gender");
		Date birthday = HttpParameterParser.getDate("birthday");
		String email = HttpParameterParser.getString("email");
		int cardType = HttpParameterParser.getIntValue("cardType");
		String cardNumber = HttpParameterParser.getString("cardNumber");

		// Area area = areaManager.get(Long.parseLong(areaId));

		user.setName(name);
		// user.setHead(head);
		user.setPetName(petName);
		// user.setArea(area);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setEmail(email);
		user.setCardType(CardType.get(cardType));
		user.setCardNumber(cardNumber);

		userManager.update(user);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("user", user);
		return new ModelAndView(new JsonView("user", model));
	}

	/**
	 * 绑定新浪微博帐号(Ajax)
	 * 
	 * @author 李季
	 * @return
	 */
	@RequestMapping(value = "/ajax/sina", method = RequestMethod.POST)
	public ModelAndView bindSinaAjax(@ObjectConvertAnno User user) {
		int accountTypeId = HttpParameterParser.getIntValue("accountType");
		AccountType accountType = AccountType.get(accountTypeId);

		try {
			userManager.bindRelationAccount(user, accountType,
					createEntry(accountType));
			return new ModelAndView(new JsonView(true, "绑定成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
	}

	/**
	 * 取消绑定新浪微博
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ajax/sina", method = RequestMethod.DELETE)
	public ModelAndView removeSinaBinding(@ObjectConvertAnno User user) {
		int accountTypeId = HttpParameterParser.getIntValue("accountType");
		AccountType accountType = AccountType.get(accountTypeId);
		RelationAccount relationAccount = relationAccountManager.getBy(user,
				accountType);
		relationAccountManager.delete(relationAccount);
		return new ModelAndView(new JsonView(true, "绑定已经取消！"));
	}

	/**
	 * 变更手机号
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/ajax/mobile", method = RequestMethod.POST)
	public ModelAndView changeUserMobile(@ObjectConvertAnno User user) {
		String mobile = HttpParameterParser.getString("mobile");
		String verifyCode = HttpParameterParser.getString("verifyCode");
		if (user.getMobile() != null && user.getMobile().equals(mobile)) {
			return new ModelAndView(new JsonView(false, "两次手机号码一致，不需要修改！"));
		}
		// 判断验证码是否正确
		if (!verifyCode.equals(user.getVerifyCode())) {
			return new ModelAndView(new JsonView(false, "您输入的验证码不正确！"));
		}
		// 判定手机号码是否已经被其他人使用
		User _user = userManager.getByMobile(mobile);
		if (_user != null && !user.equals(_user)) {
			return new ModelAndView(new JsonView(false, "该手机号码已经被其他用户使用！"));
		}
		// 更改用户手机号
		user.setMobile(mobile);
		userManager.update(user);
		return new ModelAndView(new JsonView(true, "修改成功"));
	}

	/**
	 * 用户首页 - 我的设置，用户头像上传跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/avatar", method = RequestMethod.GET)
	public ModelAndView getUserImage(@ObjectConvertAnno User user) {
		return new ModelAndView("/user/avatar_upload.jsp", getUserModel(user));
	}

	/**
	 * 用户头像上传 - 我的设置，用户头像修改
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	public void undateimage(HttpServletRequest request,
			@ObjectConvertAnno User user, HttpServletResponse response)
			throws IOException {
		String head = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			HttpParameterParser.init(request);

			String tempHead = null;
			try {
				tempHead = FileUploadUtils.uploadImage(multipartRequest,
						"importFile");
			} catch (Exception e) {
				// 上传图片大小超过限制
				response.getWriter().write("error:(1)");
				response.getWriter().close();
				return;
				// return new ModelAndView(new RedirectView(
				// "/user/userimage?error="
				// + URLEncoder.encode(e.getMessage(), "utf-8")));
			}

			String webRoot = ConfigUtils.WEB_ROOT.endsWith("/") ? ConfigUtils.WEB_ROOT
					: ConfigUtils.WEB_ROOT + "/";
			head = ConfigUtils.UPLOAD_IMAGE_PATH + "/"
					+ System.currentTimeMillis();
			BufferedImage image = ImageIO.read(new File(webRoot + tempHead));
			if (image == null) {
				// 非法图片格式
				response.getWriter().write("error:(2)");
				response.getWriter().close();
				return;
			}
			if (image.getWidth() > 400 || image.getHeight() > 400) {
				ImageUtils.zoom(webRoot + tempHead, webRoot + head, 400, 400,
						true);
				head += "." + FormatType.JPG.getName();
			} else {
				head = tempHead;
			}
		} catch (Exception e) {
			// 不可预知的错误
			response.getWriter().write("error:(3)");
			response.getWriter().close();
			return;
		}

		response.getWriter().write("success:(" + head + ")");
		response.getWriter().close();
		return;
	}

	/**
	 * 用户头像上传 - 我的设置，用户头像剪切
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/cropimage", method = RequestMethod.POST)
	public ModelAndView cutimage(@ObjectConvertAnno User user)
			throws UnsupportedEncodingException {
		String head = null;
		String descDir = null;
		try {
			head = HttpParameterParser.getString("cutimage");
			String webRoot = ConfigUtils.WEB_ROOT.endsWith("/") ? ConfigUtils.WEB_ROOT
					: ConfigUtils.WEB_ROOT;
			int x = HttpParameterParser.getIntValue("input_top");
			int y = HttpParameterParser.getIntValue("input_left");
			int width = HttpParameterParser.getIntValue("input_width");
			int height = HttpParameterParser.getIntValue("input_height");
			Rectangle rec = new Rectangle(y, x, width, height);
			String prefix = (webRoot + head).substring((webRoot + head)
					.lastIndexOf(".") + 1);
			descDir = ConfigUtils.UPLOAD_IMAGE_PATH + "/"
					+ System.currentTimeMillis() + "." + prefix;
			ImageHepler.cut(webRoot + head, webRoot + descDir, rec);
			user.setHead(descDir);
			userManager.update(user);
		} catch (Exception e) {
			Map<String, String> model = new HashMap<String, String>();
			model.put("error", e.getMessage().toString());
			return new ModelAndView(new RedirectView("/user/avatar"), model);
		}
		return new ModelAndView(new RedirectView("/user/info"));
	}
	
	private Map<String, String> getUserModel(User user) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		String userJsonString = JSONUtils.serialize(model, "user");
		Map<String, String> result = new HashMap<String, String>();
		result.put("user", userJsonString);
		return result;
	}

	private AuthenticationEntry createEntry(AccountType accountType) {
		AuthenticationEntry authenticationEntry = new AuthenticationEntry();
		if (accountType == AccountType.TYPE_SINA) {
			String oauth_token = HttpParameterParser.getString("oauth_token");
			String oauth_token_secret = HttpParameterParser
					.getString("oauth_token_secret");
			String userName = HttpParameterParser.getString("userName");
			String userid = HttpParameterParser.getString("userid");
			authenticationEntry.addItem(new EntryItem("oauth_token",
					oauth_token));
			authenticationEntry.addItem(new EntryItem("oauth_token_secret",
					oauth_token_secret));
			authenticationEntry.addItem(new EntryItem("userName", userName));
			authenticationEntry.addItem(new EntryItem("userid", userid));
		}
		if (accountType == AccountType.TYPE_QQ) {
			String openid = HttpParameterParser.getString("openid");
			String token = HttpParameterParser.getString("token");
			String secret = HttpParameterParser.getString("secret");
			authenticationEntry.addItem(new EntryItem("openid", openid));
			authenticationEntry.addItem(new EntryItem("token", token));
			authenticationEntry.addItem(new EntryItem("secret", secret));
		}
		return authenticationEntry;
	}

	/**
	 * 获得优惠券所属商户的集合
	 * @param coupons 优惠券
	 * @return
	 */
	private List<Merchant> getCouponMerchants(List<Coupon> coupons) {
		Map<Long, Long> map = new HashMap<Long, Long>();
		List<Merchant> merchants = new ArrayList<Merchant>();
		
		for(Coupon coupon : coupons) {
			Merchant merchant = coupon.getCouponPloy().getMerchant();
			if (!map.containsKey(merchant.getId())) {
				map.put(merchant.getId(), merchant.getId());
				merchants.add(merchant);
			}
		}
		
		return merchants;
	}
	
	/**
	 * 获得优惠券状态集合
	 * @param coupons 优惠券
	 * @return
	 */
	private List<CouponStatus> getCouponStatuses(List<Coupon> coupons) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<CouponStatus> statues = new ArrayList<CouponStatus>();
		
		for(Coupon coupon : coupons) {
			CouponStatus status = coupon.getCouponStatus();
			if (!map.containsKey(status.getValue())) {
				map.put(status.getValue(), status.getValue());
				statues.add(status);
			}
		}
		
		return statues;
	}
}
