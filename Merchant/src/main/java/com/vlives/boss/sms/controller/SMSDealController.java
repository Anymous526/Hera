package com.vlives.boss.sms.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.comment.manager.MerchantCommentManager;
import com.vlives.boss.illegal.domain.IllegalWord;
import com.vlives.boss.illegal.manager.IllegalWordManager;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.member.manager.exception.MemberException;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.boss.sms.manager.SmsSendManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.SmsTemplateUtils;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-22 上午09:10:11 类说明 用于处理短信网关并提供给外界调用的接口
 */
@Controller
public class SMSDealController {

	@Autowired
	private SmsSendManager smsSendManager;
	@Autowired
	private MerchantCommentManager merchantCommentManager;
	@Autowired
	private MerchantManager merchantManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private IllegalWordManager illegalWordManager;
	private static final Log LOG = LogFactory.getLog(SMSDealController.class);

	@RequestMapping("/sms/mxt/getsmsmsg.htm")
	public ModelAndView getUploadMsg() throws BusinessException {
		String mobile = HttpParameterParser.getString("Phone");// 手机号码
		String content = HttpParameterParser.getString("Content");// 短信内容
		String contentUtf = HttpParameterParser.getString("ContentUTF");// UTF编码的短信内容
		String postFixNum = HttpParameterParser.getString("PostFixNum");// 短信对应的任务号：该字段在发送短信的时候并没有使用到
		try {
			this.checkMobile(mobile);
		} catch (BusinessException e) {
			LOG.info("麦讯通上行接口：" + e.getMessage());
			return new ModelAndView(new JsonView("0"));
		}
		SmsRecord smsRecord = smsSendManager.receive(mobile, contentUtf);
		if (this.filterKey(smsRecord.getContent())) {

			return new ModelAndView(new JsonView("0"));
		}
		try {
			int i = Integer.parseInt(smsRecord.getContent().trim().substring(0, 1));
			if (1 <= i && i <= 5) {
				merchantCommentManager.createSmsComment(smsRecord);
			} else if ("91".equals(smsRecord.getContent().trim().substring(0, 2))) {
				this.regMemberBySms(smsRecord);
			} else {
				throw new RuntimeException("命令不正确");
			}
		} catch (Exception e) {
			String smsContent = SmsTemplateUtils.getSmsCommand(mobile);
			smsSendManager.sendTimelySMSByMxtong(mobile, smsContent);

		}
		return new ModelAndView(new JsonView("0"));
	}

	/**
	 * 短信注册
	 * 
	 * @param smsRecord
	 */
	private void regMemberBySms(SmsRecord smsRecord) {
		Merchant merchant = null;
		String mobileNo = smsRecord.getMobile();
		String smsContent = "";
		Member member = null;
		try {
			merchant = merchantManager.getByNum(Long.parseLong(smsRecord.getContent().trim().substring(2)));
			if (merchant == null) {
				throw new RuntimeException("商户num不正确");
			}

		} catch (Exception e) {
			smsContent = SmsTemplateUtils.getMerchantNum(mobileNo);
			smsSendManager.sendTimelySMSByMxtong(mobileNo, smsContent);
			return;
		}

		if (!merchant.getStatus().equals(Merchant.Status.STATUS_ACTIVE)) {
			smsContent = SmsTemplateUtils.getRegFail(mobileNo, merchant.getShortName());
			smsSendManager.sendTimelySMSByMxtong(mobileNo, smsContent);
			return;
		}

		try {
			member = memberManager.createBySms(mobileNo, merchant);
		} catch (BusinessException e) {
			return;
		} catch (MemberException e) {
			return;
		}
		smsContent = SmsTemplateUtils.getRegSuccess(mobileNo, merchant.getShortName(), member.getLevel().getDesc());
		smsSendManager.sendTimelySMSByMxtong(mobileNo, smsContent);
		return;
	}

	private void checkMobile(String mobile) throws BusinessException {
		Pattern pattern = Pattern.compile("(13|15|18)\\d{9}");
		Matcher matcher = pattern.matcher(mobile);
		if (!matcher.matches() || mobile.length() != 11) {
			throw new BusinessException("手机号码格式不正确");
		}
	}

	private boolean filterKey(String content) {
		if (StringUtils.isBlank(content)) {
			return true;
		}
		List<IllegalWord> words = illegalWordManager.findAll();
		for (IllegalWord word : words) {
			if (content.contains(word.getContent())) {
				LOG.info("麦讯通上行接口：" + content + "含有非法字：" + word.getContent());
				return true;
			}
		}
		return false;
	}

}
