package com.justinmobile.boss.manager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.justinmobile.base.BaseTest;
import com.justinmobile.util.HttpClientUtil;
import com.justinmobile.util.HttpClientUtil.MethodType;
import com.vlives.boss.member.dao.MemberDao;
import com.vlives.boss.sms.manager.SmsTemplateManager;
import com.vlives.boss.user.dao.UserDao;

@TransactionConfiguration(defaultRollback = false)
public class SmsSendManagerTest extends BaseTest {

	// private static final Hs8583Dto request = null;

	@Autowired
	private SmsTemplateManager smsSendManager;
	@Resource
	private UserDao userDao;
	@Resource
	private MemberDao memberDao;

	/**
	 * 测试数据 merchant id：1 ，code 123321， Pos测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testResignSms() throws Exception {
		try {
			smsSendManager.registerMemberSms(memberDao.get(1003L));
		} catch (UncategorizedSQLException e) {
			System.out.println("sssssssss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testResignSmsTwo() throws Exception {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mobile", "15982827844");
			params.put("content", "jeioeiooi");
			try {
				// PlatformCaller.invoke(null,
				// params,PlatformCaller.HttpAddress.SEND_MXTONG_NOTICE_SMS );
			} catch (Exception e) {
				// LOG.error(e);
			}
		} catch (UncategorizedSQLException e) {
			System.out.println("sssssssss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// http://www.mxtong.net.cn/GateWay/Services.asmx/DirectFetchSMS?UserID=899332&Account=admin&Password=333222
	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testCreateSms() throws Exception {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("UserID", "873705");
			paramMap.put("Account", "admin");
			paramMap.put("Password", "873705");
			String str = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectFetchSMS";
			JSONObject obj = HttpClientUtil.getResponseByJson(str, null, paramMap, MethodType.GET);
			System.out.print(obj);
		} catch (UncategorizedSQLException e) {
			System.out.println("sssssssss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
