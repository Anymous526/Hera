package com.justinmobile.boss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vlives.boss.sms.manager.SmsTemplateManager;
import com.vlives.core.web.WebApplicationContextUtils;

public class SmsSendServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9035367337808397488L;

	private static final Log LOG = LogFactory.getLog(SmsSendServlet.class);

	private SmsTemplateManager smsTemplateManager;

	@Override
	public void init() throws ServletException {
		smsTemplateManager = (SmsTemplateManager) WebApplicationContextUtils.getService("smsTemplateManager",
				super.getServletContext());
		LOG.info("sms send servlet init!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		try {
		 
			String method = request.getParameter("method");
			if (StringUtils.equals("createMember", method)) {
				Long memberId = Long.valueOf(request.getParameter("memberId"));
				smsTemplateManager.registerMemberSms(memberId);
			} else if (StringUtils.equals("posTrade", method)) {
				Long tradeDetailId = Long.valueOf(request.getParameter("tradeDetailId"));
				smsTemplateManager.tradeSms(tradeDetailId);
			} else {
				return;
			}
		} catch (Exception e) {
		}

	}

	@Override
	public void destroy() {
		LOG.info("destroy!");
	}

}
