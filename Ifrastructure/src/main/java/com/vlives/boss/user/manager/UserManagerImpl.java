/*
 * @(#)UserManagerImpl.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.user.manager;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.sms.manager.SmsSendManager;
import com.vlives.boss.user.dao.UserDao;
import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.RelationAccount;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserSource;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.security.AuthenticationException;
import com.vlives.core.security.Principal;
import com.vlives.core.security.Verifier;
import com.vlives.core.security.impl.VerifyCodeVerifier;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;
import com.vlives.util.RandomCodeUtils;
import com.vlives.util.RandomCodeUtils.RandomType;
import com.vlives.util.SmsTemplateUtils;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-3
 */
@Service("userManager")
public class UserManagerImpl implements UserManager{
	
	/**
	 * 激活帐户的最大有效日期(单位小时)
	 */
	private static final int MAX_ACTIVE_TIME=24;
	@Resource
	private UserDao userDao;
	
	@Autowired
	private SmsSendManager smsSendManager;
	@Autowired
	private RelationAccountManager relationAccountManager;
	@Override
	public User get(Long id) {
		return userDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(User user) {
		userDao.update(user);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User getByMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		return  userDao.getByProperty("mobile", mobile);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(User user) {
		user.setValid(true);
		user.setCreateDate(new Date());
		userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void sendVerifyCode(String mobile) throws BusinessException {
		User user = getByMobile(mobile);
		if(user == null)  {
			user = new User();
			user.setMobile(mobile);
			user.setPassword(DigestUtils.shaHex(User.USER_DEFAULT_PASSWORD));
			user.setSource(UserSource.SOURCE_WEB_SITE);
			save(user);
		}
		clearVerifyCodeCount(user);
		assertCanSendVerifyCode(user);
		String code = RandomCodeUtils.random(6, RandomType.NUMBER);
		smsSendManager.sendTimelySMS(user.getMobile(), SmsTemplateUtils.SMS_LOGIN_VERIFY_TEMPLATE+code);
		user.setVerifyCode(code);
		if(user.getCodeCreateDate()==null||user.getCodeCreateCount()==0)
			user.setCodeCreateDate(new Date());
		user.setCodeCreateCount(user.getCodeCreateCount()+1);
		userDao.update(user);
	}
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void sendVerifyCode(User user,String receiveMobile) throws BusinessException {
		
		clearVerifyCodeCount(user);
		assertCanSendVerifyCode(user);
		String code = RandomCodeUtils.random(6, RandomType.NUMBER);
		smsSendManager.sendTimelySMS(receiveMobile, SmsTemplateUtils.SMS_LOGIN_VERIFY_TEMPLATE+code);
		user.setVerifyCode(code);
		if(user.getCodeCreateDate()==null||user.getCodeCreateCount()==0)
			user.setCodeCreateDate(new Date());
		user.setCodeCreateCount(user.getCodeCreateCount()+1);
		userDao.update(user);
	}
	/**
	 * 清除验证码验证时间大于1天的验证次数
	 * @author jianguo.xu
	 * @param user
	 */
	private void clearVerifyCodeCount(User user) {
		Date createDate = user.getCodeCreateDate();
		if(createDate == null) return;
		long day = DateUtils.subtractDay(new Date(), createDate);
		if(day>1)
			user.setCodeCreateCount(0);
	}
	/**
	 * 断言可以发送验证码
	 * @author jianguo.xu
	 * @param user
	 * @throws BusinessException
	 */
	private void assertCanSendVerifyCode(User user)throws BusinessException {
		if(!user.isValid())
			throw new BusinessException("该用户状态无效");
		Date createDate = user.getCodeCreateDate();
		if(createDate == null) return;
		long second = DateUtils.subtractSecond(new Date(), createDate);
		if(second<120)
			throw new BusinessException("2分钟内请勿多次发送验证码");
		if(user.getCodeCreateCount()==5) {
			throw new BusinessException("抱歉，24小时内最多有五次获得验证码机会");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Principal get(Serializable id) {
		return this.get((Long)id);
	}
	/**
	 * 如果用户验证通过同时把验证码验证次数清零
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Principal authenticate(Verifier verifier)
			throws AuthenticationException {
		User user = null;
		if(verifier instanceof VerifyCodeVerifier) {
			user = userLogin((VerifyCodeVerifier)verifier);
		}
		if(verifier instanceof RelationAccountVerifier) {
			user = relationAccountLogin((RelationAccountVerifier)verifier);
		}
		user.setCodeCreateCount(0);
		user.setLastLoginDate(DateUtils.clear(new Date(), TimeUnit.MILLISECONDS));
		userDao.update(user);
		return user;
	}
	
	private User userLogin(VerifyCodeVerifier verifier) throws AuthenticationException {
		User user = getByMobile(verifier.getLoginName());
		try {
			assertUserLoginTrue(user, verifier.getVerifyCode());
		} catch (BusinessException e) {
			throw new AuthenticationException(e.getMessage());
		}
		return user;
	}
	
	private void assertUserLoginTrue(User user,String verifyCode) throws BusinessException{
		if(user == null) {
			throw new BusinessException("手机不存在");
		}
		if(!user.isValid()) {
			throw new BusinessException("用户账户被锁定");
		}
		if(!user.getVerifyCode().equals(verifyCode)) {
			throw new BusinessException("用户验证码错误");
		}
		Date tempDate = DateUtils.add(user.getCodeCreateDate(), TimeUnit.HOURS, MAX_ACTIVE_TIME);
		if(new Date().compareTo(tempDate)==1){
			throw new BusinessException("验证码过期，请在 "+MAX_ACTIVE_TIME+" 小时内验证登录");
		}
	}
	
	private User relationAccountLogin(RelationAccountVerifier verifier)throws AuthenticationException  {
		RelationAccount relationAccount = relationAccountManager.getBy(verifier.getAccountType(), verifier.getAuthenticationEntry());
		try {
			assertRelationAccountLoginTrue(relationAccount);
		} catch (BusinessException e) {
			throw new AuthenticationException(e.getMessage());
		}
		return relationAccount.getUser();
	}
	
	private void assertRelationAccountLoginTrue(RelationAccount relationAccount) throws BusinessException{
		if(relationAccount==null) {
			throw new BusinessException("关联帐号不存在");
		}
		if(!relationAccount.isEnable()) {
			throw new BusinessException("关联帐号被锁定");
		}
		User user = relationAccount.getUser();
		if(!user.isValid()) {
			throw new BusinessException("用户账户被锁定");
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void bindRelationAccount(User user, AccountType accountType,
			AuthenticationEntry authenticationEntry) throws BusinessException {
		
		RelationAccount relationAccount = relationAccountManager.getBy(accountType, authenticationEntry);
		if(relationAccount!=null&&relationAccount.getUser()!=user) {
			throw new BusinessException("该帐号已被手机尾号为："+relationAccount.getUser().getShortMobile()+" 的用户占用");
		}
		user.addRelationAccount(accountType, authenticationEntry);
		userDao.update(user);
	}

	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public User create(String mobileNo,UserSource userSource) {
		User user = getByMobile(mobileNo);
		if (user != null) {
			return user;
		}
		user = new User();
		user.setPassword(DigestUtils.shaHex(User.USER_DEFAULT_PASSWORD));
		user.setValid(true);
		user.setCreateDate(new Date());
		user.setMobile(mobileNo);
		user.setSource(userSource);
		userDao.save(user);
		return user;
		
	}
	
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public User create(TempMember tempMember, String mobile) {
		User user = getByMobile(mobile);
		if (user != null) {
			return user;
		}
		user = new User();
		user.setPassword(DigestUtils.shaHex(User.USER_DEFAULT_PASSWORD));
		user.setValid(true);
		user.setSource(UserSource.SOURCE_POS);

		user.setMobile(mobile);
		user.setName(tempMember.getName());			
		user.setPetName(tempMember.getPetName());
		user.setGender(tempMember.getGender());
		user.setAddress(tempMember.getAddress());
		user.setArea(tempMember.getArea());
		user.setBirthday(tempMember.getBirthday());
		user.setPost(tempMember.getPost());
		user.setEmail(tempMember.getEmail());
		user.setMsn(tempMember.getMsn());
		user.setQq(tempMember.getQq());
		user.setCardType(tempMember.getCardType());
		
		user.setCardNumber(tempMember.getCardNumber());
		user.setCreateDate(tempMember.getCreateDate());
		userDao.save(user);
		return user;
	}

}

