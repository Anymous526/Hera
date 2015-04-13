package com.vlives.util;

import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vlives.core.support.spring.anno.InitMethod;

import cn.emay.sdk.api.Client;
import cn.emay.sdk.api.Mo;
import cn.emay.sdk.api.StatusReport; 

public class SendSMSUtils {
	private static Client sdkclient = null;

	private static final Log LOG = LogFactory.getLog(SendSMSUtils.class);
    /**junit*/
	public static boolean TEST_JUNIT=false;

	public static enum SmsPriority {
		ONE_LOW_LEVEL(1), two_level(2), THREE_DEFAULT_LEVEL(3), FOUR_LEVEL(4), FIVE_HIGH_LEVEL(5);
		private int level;

		private SmsPriority(int level) {
			this.level = level;
		}

		public int getLevel() {
			return this.level;
		}

	}
	
	public static Client getClient(){
		return sdkclient;
	}
	
	static{
	   initialize();
	}
	
	@InitMethod(isStatic=true)
	public static void initialize() {
		if (sdkclient == null) {
			try {
				sdkclient = new Client(ConfigUtils.SMS_SOFTWARE_SERIAL_NO, ConfigUtils.SMS_PASSWORD);
			} catch (Exception e) {
				LOG.error("初始化EmaySMS接口：" + e.getMessage());
			}
			if (!ConfigUtils.SMS_ISREGIST_KEY) {
				int registReturn = sdkclient.registEx(ConfigUtils.SMS_KEY);
				LOG.info("TegistEx:" + registReturn);
			}
		}
	}

	/**
	 * 在使用账号手动自己注册key
	 */
	public static void registEx() {
		int registReturn = sdkclient.registEx(ConfigUtils.SMS_KEY);
		LOG.info("TegistEx:" + registReturn);
	}

	public static int sendSMS(String mobile, java.lang.String smsContent) {
	
		String[] mobiles = new String[0];
		mobiles = (String[]) ArrayUtils.add(mobiles, mobile);
		int status = sendSMS(mobiles, smsContent, SmsPriority.FIVE_HIGH_LEVEL);
		LOG.info("send message > mobile : "+mobile+" content : "+smsContent);
		return status;
	}

	public static int sendSMS(String[] mobiles, String smsContent, SmsPriority smsPriority) {
		return sendSMS(mobiles, smsContent, "", smsPriority);

	}

	public static int sendSMS(String[] mobiles, String smsContent, String addSerial, SmsPriority smsPriority) {
		return sendSMS( mobiles, smsContent, addSerial, smsPriority.getLevel());

	}
	
	public static int sendSMS(String[] mobiles, String smsContent, String addSerial, int smsPriority){
		return sdkclient.sendSMS("", mobiles, smsContent, addSerial, "GBK", smsPriority);
	}

	/**
	 * 手机用户向特服号码发送短信
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<Mo> getPartMO() throws Exception {
		return sdkclient.getMO();
	}

	public static List<StatusReport> getReport() throws Exception {
		return sdkclient.getReport();

	}

	/**
	 * 获取该账号的短信余额
	 * 
	 * @return
	 */
	public static double getBalance() {
		return sdkclient.getBalance();
	}

	/**
	 * 获取发送一条短信所需要的费用
	 */
	public static double getEachFee() {
		return sdkclient.getEachFee();
	}

	
	
	
}
