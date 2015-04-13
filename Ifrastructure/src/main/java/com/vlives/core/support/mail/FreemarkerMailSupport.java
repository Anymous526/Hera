/**
 * @(#)FreemarkerMailSupport.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.mail;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.vlives.core.support.thread.ThreadPool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 通过freemarker模板发送邮件类
 * @author  jianguo.xu
 * @version 1.0,2011-3-9
 */
public class FreemarkerMailSupport extends ThreadPool implements MailSender,
		JavaMailSender {
	
	private static final Log LOG = LogFactory
			.getLog(FreemarkerMailSupport.class);

	private MailSender mailSender;
	
	private Properties mailHeaders = new Properties();

	private String subject;

	private String from;
	private String fromTitle;
	private String templateName;
	
	private Configuration freemarkerConfiguration;
	 

	public Configuration getFreemarkerConfiguration() {
		return freemarkerConfiguration;
	}
	
	public void setFromTitle(String fromTitle) {
		this.fromTitle = fromTitle;
	}

	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Properties getMailHeaders() {
		return mailHeaders;
	}

	public void setMailHeaders(Properties mailHeaders) {
		this.mailHeaders = mailHeaders;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public JavaMailSender getJavaMailSender() {
		return (JavaMailSender) mailSender;
	}

	public MimeMessage createMimeMessage() {
		return getJavaMailSender().createMimeMessage();
	}

	public MimeMessage createMimeMessage(InputStream contentStream)
			throws MailException {
		return getJavaMailSender().createMimeMessage(contentStream);
	}

	public void send(MimeMessage mimeMessage) throws MailException {
		injectMailHeader(mimeMessage);
		 
		add(mimeMessage);
	}

	protected void injectMailHeader(MimeMessage mm) {
		
		for(Object key: mailHeaders.keySet()) {
			String name = (String) key;
			try {
				mm.setHeader(name, (String) mailHeaders.getProperty(name));
			} catch (MessagingException e) {
				LOG.error(e);
			}
		}
	}

	public void send(MimeMessage[] mimeMessages) throws MailException {
		for (int i = 0; i < mimeMessages.length; i++)
			injectMailHeader(mimeMessages[i]);
		add(mimeMessages);
	}

	public void send(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
		add(mimeMessagePreparator);
	}

	public void send(MimeMessagePreparator[] mimeMessagePreparators)
			throws MailException {
		add(mimeMessagePreparators);
	}

	public void send(SimpleMailMessage simpleMessage) throws MailException {
		add(simpleMessage);
	}

	public void send(SimpleMailMessage[] simpleMessages) throws MailException {
		add(simpleMessages);
	}

	public void sendMime(String nameOfTo, String emailOfTo, Map<String,Object> model)
			throws MailSendException {
		sendMime(getEncodeTo(nameOfTo)+ "<" + emailOfTo + ">", model);
	}

	public void sendMime(String to, Map<String,Object> model) throws MailException {
		sendMime(mergeSimpleMessage(to, "", model));
	}

	public void sendMail(String to, String subject, Map<String,Object> model)
			throws MailException {
		sendMime(mergeSimpleMessage(to, subject, model));
	}

	public void sendMime(SimpleMailMessage simpleMessage) throws MailException {
		send(toMimeMessage(simpleMessage));
	}

	public MimeMessage toMimeMessage(SimpleMailMessage simpleMailMessage) {
		MimeMessage mimeMessage = createMimeMessage();
		MimeMailMessage mmm = new MimeMailMessage(mimeMessage);
		simpleMailMessage.copyTo(mmm);
		return mmm.getMimeMessage();
	}

	public SimpleMailMessage mergeSimpleMessage(String to, String subject,
			Map<String,Object> model) {
		String text = null;
		try {
			text = renderText(model);
		} catch (IOException e) {
			 
			LOG.error(e);
		} catch (TemplateException e) {
			 
			LOG.error(e);
		}
		SimpleMailMessage message = new SimpleMailMessage();
		if (subject != null && !subject.equals(""))
			message.setSubject(subject);
		else
			message.setSubject(getSubject());
		String from = getEncodeFrom();
		message.setFrom(from);
		message.setTo(to);
		message.setText(text);
		return message;
	}
	
	private String getEncodeTo(String to) {
		try {
			return MimeUtility.encodeText(to);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
			return null;
		} 
	}
	
	private String getEncodeFrom() {
		try {
			return MimeUtility.encodeText(fromTitle)+"<" + getFrom() + ">";
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
			return null;
		} 
	}
	public String renderText(Map<String,Object> model) throws IOException, TemplateException {
		Template temp = getFreemarkerConfiguration().getTemplate(
				getTemplateName());
		return FreeMarkerTemplateUtils.processTemplateIntoString(temp, model);
	}

	public void doSend(SimpleMailMessage simpleMessage) throws MailException {
		mailSender.send(simpleMessage);
	}

	public void doSend(MimeMessage mimeMessage) throws MailException {
		((JavaMailSender) mailSender).send(mimeMessage);
	}

	public void doSend(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
		((JavaMailSender) mailSender).send(mimeMessagePreparator);
	}

	public void execute(Object object) {
		
		if (object instanceof SimpleMailMessage) {
			doSend((SimpleMailMessage) object);
		} else if (object instanceof MimeMessage) {
			doSend((MimeMessage) object);
		} else if (object instanceof MimeMessagePreparator) {
			doSend((MimeMessagePreparator) object);
		}
	}
	 
}

