package com.justinmobile.endpoint.mina.domain;

/**
 * @author ThinkPad7
 *
 */
/**
 * @author ThinkPad7
 *
 */
public class Hs8583AdvertPrintFeedBackList {


	/**应用ID
	 * 广告应用的应用ID
	 */
	private String appId;
	/**应用版本号
	 * 当前广告应用的参数版本号
	 */
	private String appVersion;
	/**参数名
	 * 广告使用图片的文件名，左对齐右补空格
	 */
	private String paramName;
	/**打印次数
	 * 从上一次反馈完成开始到这一次反馈之间的次数
	 */
	private String printNo;
	/**广告说明
	 * 最多30个汉字，左对齐右补空格
	 */
	private String adsDescription;
	
	

	public String getAdsDescription() {
		return adsDescription;
	}

	public void setAdsDescription(String adsDescription) {
		this.adsDescription = adsDescription;
	}


	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getPrintNo() {
		return printNo;
	}

	public void setPrintNo(String printNo) {
		this.printNo = printNo;
	}
	
	
	
}
