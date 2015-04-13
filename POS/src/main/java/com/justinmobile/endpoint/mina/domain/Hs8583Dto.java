package com.justinmobile.endpoint.mina.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.justinmobile.endpoint.mina.TransferProtocol;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.service.PosServiceException;
import com.justinmobile.util.ByteUtil;
import com.vlives.util.DateUtils;

/**
 * @author ThinkPad7
 *
 */
public class Hs8583Dto extends Hs8583AbstractDto {

	/**消息类型*/
	private String bm0MessageType; 
	/** 主位元表*/
	private String bm1HostBitElement;
	/** 会员编号（手机号）*/
	private String bm2MemberMobile;
	/** 交易处理码*/
	private String bm3TradeCode;
	/** 商户编号*/
	private String bm4MerchantNo;
	/** 终端编号*/
	private String bm5TerminalNo;
	/** 交易流水号*/
	private String bm6TradeSerialNo;
	/**交易金额 */
	private Long bm7TradeAmount;
	/** 会员积分余额*/
	private Long bm8MemberIntegralAmount;
	/** 会员积分发生额*/
	private Long bm9MemberActiveAmount;
	/** */
	private String bm10;
	/** 终端交易日期*/
	private String bm11TerminalTradeDate;
	/** 终端交易时间*/
	private String bm12TerminalTradeTime;
	/** 商户简称*/
	private String bm13MerchantName;
	/** 商户地址*/
	private String bm14MerchantAdress;
	/** 会员级别名称*/
	private String bm15MemberLevelName;
	/** 会员折扣*/
	private String bm16MemberDiscount;
	/** 商户电话	*/
	private String bm17MerchantMobile;
	/** POS机序列号*/
	private String bm18PosSerialNo;
	/** POS业务列表*/
	private String bm19PosBussList;
//	/** 积分记录*/
//	private List<Hs8583PointDto> bm20IntegralRecord = new ArrayList<Hs8583PointDto>();
	private String bm20MobileValidRule;
	/** 结算数据*/
	private Hs8583ClosingCostDto bm21ClosingCost;
	/** */ 
	private String bm22;
	/** 应答码*/
	private String bm23RespCode;
	/** 附加响应数据（错误信息描述）*/
	private String bm24RespMess;
	/**会员申请日期 */
	private String bm25MemberApplyDate;
	/** */
	private Hs8583AdvertPrintFeedBackList bm26;
	/** */
	private String bm27;
	/** */
	private String bm28;
	/** */
	private String bm29OriginalTradSerial;
	/** 原交易流水号*/
	private String bm30BatchNo;
	/** 原批次号*/
	private String bm31OriginalBatchNo;
	/***/
	private String bm32;
	/** 会员信息列表 */
	private List<Hs8583MemberMess> bm33MemberMess=new ArrayList<Hs8583MemberMess>();
	/**级别信息 */
	private List<Hs8583MemberLevelInfo> bm34MemberLevelInfo=new ArrayList<Hs8583MemberLevelInfo>();
	/** 会员可用积分余额*/
	private Long bm35MemberUseableIntegral;
	/** 交易上送信息*/
	private List<Hs8583BusinessUploadInfo> bm36BusinessUploadInfoList=new ArrayList<Hs8583BusinessUploadInfo>();
	/** 报文序号*/
	private String bm37MessageSerial;
	/** 报文后续标志*/
	private String bm38MessageFollowFlag;
	/** 原会员编号*/
	private String bm39OriginalMemberSerial;
	/** 会员姓名*/
	private String bm40MemberName;
	/**POS计算折扣*/
	private String bm41DiscountFlag;
	
	private String bm42AppSeriaNo;

	private String bm43AppVersionNo;
	/**券序列号*/
	private String bm44CouponSeriano;
	/**券信息列表*/
	private Hs8583CouponSketchList bm45CouponSketchList;
	/**券活动信息列表*/
	private List<Hs8583CouponList> bm46CouponList=new ArrayList<Hs8583CouponList>();
	/** 券交易上送信息*/
	private List<Hs8583CouponUploadInfo> bm47CouponUploadList=new ArrayList<Hs8583CouponUploadInfo>();
	
	
	
	
	private String bm64 = "00000000";

	public void setBm0MessageType(String bm0MessageType) {
		this.bm0MessageType = bm0MessageType;
	}

	public void setBm1HostBitElement(String bm1HostBitElement) {
		this.bm1HostBitElement = bm1HostBitElement;
	}

	public void setBm2MemberMobile(String bm2MemberMobile) {
		this.bm2MemberMobile = bm2MemberMobile;
	}

	public void setBm3TradeCode(String bm3TradeCode) {
		this.bm3TradeCode = bm3TradeCode;
	}

	public void setBm4MerchantNo(String bm4MerchantNo) {
		//this.bm4MerchantNo = bm4MerchantNo;
		if(StringUtils.isNotBlank(bm4MerchantNo)){
			this.bm4MerchantNo =String.format("%15s", bm4MerchantNo).replace(" ", "0") ;
		}
	}

	public void setBm5TerminalNo(String bm5TerminalNo) {
		this.bm5TerminalNo = bm5TerminalNo;
	}

	public void setBm6TradeSerialNo(String bm6TradeSerialNo) {
		this.bm6TradeSerialNo = bm6TradeSerialNo;
	}

	public void setBm7TradeAmount(Long bm7TradeAmount) {
		this.bm7TradeAmount = bm7TradeAmount;
	}

	public void setBm8MemberIntegralAmount(Long bm8MemberIntegralAmount) {
		
		this.bm8MemberIntegralAmount = bm8MemberIntegralAmount;
	}

	public void setBm9MemberActiveAmount(Long bm9MemberActiveAmount) {
		this.bm9MemberActiveAmount = bm9MemberActiveAmount; 
	}

	public void setBm10(String bm10) {
		this.bm10 = bm10;
	}

	public void setBm11TerminalTradeDate(String bm11TerminalTradeDate) {
		this.bm11TerminalTradeDate = bm11TerminalTradeDate;
	}

	public void setBm12TerminalTradeTime(String bm12TerminalTradeTime) {
		this.bm12TerminalTradeTime = bm12TerminalTradeTime;
	}

	public void setBm13MerchantName(String bm13MerchantName) {
		this.bm13MerchantName = getPartOfLength(bm13MerchantName,20);;
	}

	public void setBm14MerchantAdress(String bm14MerchantAdress) {
		this.bm14MerchantAdress =getPartOfLength(bm14MerchantAdress,20);
	}

	public void setBm15MemberLevelName(String bm15MemberLevelName) {
		this.bm15MemberLevelName = bm15MemberLevelName;
	}

	public void setBm16MemberDiscount(String bm16MemberDiscount) {
		if(StringUtils.isNotBlank(bm16MemberDiscount)){
		this.bm16MemberDiscount =String.format("%4s", bm16MemberDiscount).replace(" ", "0") ;
		}
	}

	public void setBm17MerchantMobile(String bm17MerchantMobile) {
		if(StringUtils.isNotBlank(bm17MerchantMobile)){
		this.bm17MerchantMobile =addBland(bm17MerchantMobile,20);
		}
	}

	public void setBm18PosSerialNo(String bm18PosSerialNo) {
		if(StringUtils.isNotBlank(bm18PosSerialNo)){
			this.bm18PosSerialNo =String.format("%16s", bm18PosSerialNo).replace(" ", "0") ;
		}
	}


	public void setBm19PosBussList(String bm19PosBussList) {
		if(StringUtils.isNotBlank(bm19PosBussList)){
			this.bm19PosBussList = String.format("%4s", bm19PosBussList).replace(" ", "0");
		}
	}

//	public void setBm20IntegralRecord(List<Hs8583PointDto> bm20IntegralRecord) {
//		this.bm20IntegralRecord = bm20IntegralRecord;
//	}

	public void setBm20MobileValidRule(String bm20MobileValidRule) {
		this.bm20MobileValidRule = bm20MobileValidRule;
	}

	public void setBm21ClosingCost(Hs8583ClosingCostDto bm21ClosingCost) {
		this.bm21ClosingCost = bm21ClosingCost;
	}

	public void setBm22(String bm22) {
		this.bm22 = bm22;
	}

	public void setBm23RespCode(String bm23RespCode) {
		this.bm23RespCode = bm23RespCode;
	}

	public void setBm24RespMess(String bm24RespMess) {
		this.bm24RespMess = bm24RespMess;
	}

	public void setBm25MemberApplyDate(String bm25MemberApplyDate) {
		this.bm25MemberApplyDate = bm25MemberApplyDate;
	}

	public void setBm26(Hs8583AdvertPrintFeedBackList bm26) {
		this.bm26 = bm26;
	}

	public void setBm64(String bm64) {
		this.bm64 = bm64;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public TransferProtocol getProtocol() {
		return protocol;
	}

	public void setProtocol(TransferProtocol protocol) {
		this.protocol = protocol;
	}

	public MtRequestCode getRequestCode() {
		return requestCode;
	}

	public String getRequestCodeString() {
		return this.bm0MessageType+this.bm3TradeCode;
	}


	public void setRequestCodeString(String requestCodeString) {
		this.requestCodeString = requestCodeString;
	}
	
	public void setRequestCode(MtRequestCode requestCode) {
		this.requestCode = requestCode;
	}

	public Hs8583AdvertPrintFeedBackList getBm26() {
		return bm26;
	}

	public String getBm0MessageType() {
		return bm0MessageType;
	}

	public String getBm1HostBitElement() {
		return bm1HostBitElement;
	}

	public String getBm2MemberMobile() {
		return bm2MemberMobile;
	}

	public String getBm3TradeCode() {
		return bm3TradeCode;
	}

	public String getBm4MerchantNo() {
		return bm4MerchantNo;
	}

	public String getBm5TerminalNo() {
		return bm5TerminalNo;
	}

	public String getBm6TradeSerialNo() {
		return bm6TradeSerialNo;
	}

	public Long getBm7TradeAmount() {
		return bm7TradeAmount;
	}
	public String getBm7TradeAmountString() {
		if(bm7TradeAmount!=null&&StringUtils.isNotBlank(bm7TradeAmount.toString())){
		return String.format("%10s", bm7TradeAmount.toString()).replace(" ", "0"); 
		}else return null;
	}

	public Long getBm8MemberIntegralAmount() {
		return bm8MemberIntegralAmount;
	}

	public String getBm8MemberIntegralAmountString() {
		if(bm8MemberIntegralAmount!=null&&StringUtils.isNotBlank(bm8MemberIntegralAmount.toString())){
		return String.format("%10s", bm8MemberIntegralAmount.toString()).replace(" ", "0");
		}else return null;
	}

	public Long getBm9MemberActiveAmount() {
		return bm9MemberActiveAmount;
	}
	
	public String getBm9MemberActiveAmountString() {
		if(bm9MemberActiveAmount!=null&&StringUtils.isNotBlank(bm9MemberActiveAmount.toString())){
		return String.format("%6s", bm9MemberActiveAmount.toString()).replace(" ", "0");
		}else return null;
	}

	public String getBm10() {
		return bm10;
	}

	public String getBm11TerminalTradeDate() {
		return bm11TerminalTradeDate;
	}

	public String getBm12TerminalTradeTime() {
		return bm12TerminalTradeTime;
	}

	public String getBm13MerchantName() {
		return bm13MerchantName;
	}

	public String getBm14MerchantAdress() {
		return bm14MerchantAdress;
	}

	public String getBm15MemberLevelName() {
		return bm15MemberLevelName;
	}

	public String getBm16MemberDiscount() {
		return bm16MemberDiscount;
	}

	public String getBm17MerchantMobile() {
		return bm17MerchantMobile;
	}

	public String getBm18PosSerialNo() {
		return bm18PosSerialNo;
	}

	public String getBm19PosBussList() {
		return bm19PosBussList;
	}

//	public List<Hs8583PointDto> getBm20IntegralRecord() {
//		return bm20IntegralRecord;
//	}
	public String getBm20MobileValidRule() {
		return bm20MobileValidRule;
	}

	public Hs8583ClosingCostDto getBm21ClosingCost() {
		return bm21ClosingCost;
	}



	public String getBm22() {
		return bm22;
	}

	public String getBm23RespCode() {
		return bm23RespCode;
	}

	public String getBm24RespMess() {
		return bm24RespMess;
	}

	public String getBm25MemberApplyDate() {
		return bm25MemberApplyDate;
	}

	public String getBm27() {
		return bm27;
	}

	public void setBm27(String bm27) {
		this.bm27 = bm27;
	}

	public String getBm28() {
		return bm28;
	}

	public void setBm28(String bm28) {
		this.bm28 = bm28;
	}

	public String getBm29OriginalTradSerial() {
		return bm29OriginalTradSerial;
	}

	public void setBm29OriginalTradSerial(String bm29OriginalTradSerial) {
		this.bm29OriginalTradSerial = bm29OriginalTradSerial;
	}

	public String getBm30BatchNo() {
		return bm30BatchNo;
	}

	public void setBm30BatchNo(String bm30BatchNo) {
		if(bm30BatchNo!=null&&StringUtils.isNotBlank(bm30BatchNo)){
			this.bm30BatchNo = String.format("%6s", bm30BatchNo).replace(" ", "0");
		}
	}

	public String getBm31OriginalBatchNo() {
		return bm31OriginalBatchNo;
	}

	public void setBm31OriginalBatchNo(String bm31OriginalBatchNo) {
		this.bm31OriginalBatchNo = bm31OriginalBatchNo;
	}

	public String getBm32() {
		return bm32;
	}

	public void setBm32(String bm32) {
		this.bm32 = bm32;
	}

	public List<Hs8583MemberMess> getBm33MemberMess() {
		return bm33MemberMess;
	}

	public void setBm33MemberMess(List<Hs8583MemberMess> bm33MemberMess) {
		this.bm33MemberMess = bm33MemberMess;
	}

	public List<Hs8583MemberLevelInfo> getBm34MemberLevelInfo() {
		return bm34MemberLevelInfo;
	}

	public void setBm34MemberLevelInfo(List<Hs8583MemberLevelInfo> bm34MemberLevelInfo) {
		this.bm34MemberLevelInfo = bm34MemberLevelInfo;
	}

	public Long getBm35MemberUseableIntegral() {
		return bm35MemberUseableIntegral;
	}
	
	public String getBm35MemberUseableIntegralString() {
		if(bm35MemberUseableIntegral!=null&&StringUtils.isNotBlank(bm35MemberUseableIntegral.toString())){
		return String.format("%10s", bm35MemberUseableIntegral.toString()).replace(" ", "0");
		}else{
			return null;
		}
	}

	public void setBm35MemberUseableIntegral(Long bm35MemberUseableIntegral) {
		this.bm35MemberUseableIntegral = bm35MemberUseableIntegral;
	}
	
	public List<Hs8583BusinessUploadInfo> getBm36BusinessUploadInfoList() {
		return bm36BusinessUploadInfoList;
	}

	public void setBm36BusinessUploadInfoList(List<Hs8583BusinessUploadInfo> bm36BusinessUploadInfoList) {
		this.bm36BusinessUploadInfoList = bm36BusinessUploadInfoList;
	}

	
	
	public String getBm37MessageSerial() {
		return bm37MessageSerial;
	}

	public void setBm37MessageSerial(String bm37MessageSerial) {
		if(StringUtils.isNotBlank(bm37MessageSerial)){
			this.bm37MessageSerial =  String.format("%6s", bm37MessageSerial).replace(" ", "0");
		}
	}

	public String getBm38MessageFollowFlag() {
		return bm38MessageFollowFlag;
	}

	public void setBm38MessageFollowFlag(String bm38MessageFollowFlag) {
		this.bm38MessageFollowFlag = bm38MessageFollowFlag;
	}

	public String getBm39OriginalMemberSerial() {
		return bm39OriginalMemberSerial;
	}

	public void setBm39OriginalMemberSerial(String bm39OriginalMemberSerial) {
		if(StringUtils.isNotBlank(bm39OriginalMemberSerial)){
			this.bm39OriginalMemberSerial = addBland(bm39OriginalMemberSerial,20);
		}
	}

	public String getBm40MemberName() {
		return bm40MemberName;
	}

	public void setBm40MemberName(String bm40MemberName) {
		if(StringUtils.isNotBlank(bm40MemberName)){
		this.bm40MemberName = addBland(bm40MemberName,10);
		}
	}

	public String getBm41DiscountFlag() {
		return bm41DiscountFlag;
	}

	public void setBm41DiscountFlag(String bm41DiscountFlag) {
		this.bm41DiscountFlag = bm41DiscountFlag;
	}
	
	
	public String getBm42AppSeriaNo() {
		return bm42AppSeriaNo;
	}

	public void setBm42AppSeriaNo(String bm42AppSeriaNo) {
		if(StringUtils.isNotBlank(bm42AppSeriaNo)){
			this.bm42AppSeriaNo = addBland(bm42AppSeriaNo.toString(),10);
		}
	}

	public String getBm43AppVersionNo() {
		return bm43AppVersionNo;
	}

	public void setBm43AppVersionNo(String bm43AppVersionNo) {
		if(StringUtils.isNotBlank(bm42AppSeriaNo.toString())){
			this.bm43AppVersionNo = addBland(bm43AppVersionNo,10);
		}
	}
	
	public String getBm44CouponSeriano() {
		return bm44CouponSeriano;
	}


	public void setBm44CouponSeriano(String bm44CouponSeriano) {
		this.bm44CouponSeriano = bm44CouponSeriano;
	}


	public Hs8583CouponSketchList getBm45CouponSketchList() {
		return bm45CouponSketchList;
	}

	public void setBm45CouponSketchList(Hs8583CouponSketchList bm45CouponSketchList) {
		this.bm45CouponSketchList = bm45CouponSketchList;
	}

	public List<Hs8583CouponList> getBm46CouponList() {
		return bm46CouponList;
	}

	public void setBm46CouponList(List<Hs8583CouponList> bm46CouponList) {
		this.bm46CouponList = bm46CouponList;
	}

	public void addBm46CouponList(Hs8583CouponList couponList) {
		this.bm46CouponList.add(couponList);
	}
	

	public List<Hs8583CouponUploadInfo> getBm47CouponUploadList() {
		return bm47CouponUploadList;
	}

	public void setBm47CouponUploadList(
			List<Hs8583CouponUploadInfo> bm47CouponUploadList) {
		this.bm47CouponUploadList = bm47CouponUploadList;
	}

	public String getBm64() {
		return bm64;
	}
	
	


	/**
	 * 设置终端时间
	 * @author MrXu
	 * @param date
	 */
	public void  setTradeDate(Date date){
		this.setBm11TerminalTradeDate(DateUtils.format(date, "yyyyMMdd"));
		 this.setBm12TerminalTradeTime(DateUtils.format(date, "HHmmss"));
	}
	
	
	

	public void decode(Object message, TransferProtocol protocol) throws PosServiceException {
		this.protocol = protocol;
		this.message = message;
		System.out.println(ByteUtil.toHexString((byte[]) this.message));
		decode();
	}

	protected void decode() throws PosServiceException {
	
			if (protocol == TransferProtocol.HS8583_BINARY_PROTOCOL) {
				byte[] message = (byte[]) this.message;
				byte[] head=new byte[2];
				System.arraycopy(message, 0, head, 0, 2);
				if(ByteUtil.toHexString(head).endsWith("0000")){
					this.bm0MessageType = parseAscArea(message, 5, 4);
					//this.bm0MessageType = ByteUtil.arrayShortHexString(ByteUtil.subArray(message, 7, 9), null);
					offs=9;
				}else{
				this.bm0MessageType = parseAscArea(message, 7, 4);
				//this.bm0MessageType = ByteUtil.arrayShortHexString(ByteUtil.subArray(message, 7, 9), null);
					offs=11;
				}
				this.bm1HostBitElement = parseBm1(message, offs,8);
				decodeFromBm1(message, bm1HostBitElement);
			//	System.out.println(this.toString());
			}
		
	}

	protected void decodeFromBm1(Object message, String bm1HostBitElement) throws PosServiceException {
		for (int i = 1; i <= bm1HostBitElement.length(); i++) {
			if (String.valueOf(bm1HostBitElement.charAt(i - 1)).equals(VALUE_NO))
				continue;
			switch (i) {
			case 1:
				bm1HostBitElement += parseBm1((byte[]) message, 15,8); // 扩展#1域
				AREA1_ISLONG = true;
				offs += 8;
				break;
			case 2: // 会员编号
				this.bm2MemberMobile = parseAscArea((byte[]) message, offs, 11);
				break;
			case 3: // 交易处理码
				this.bm3TradeCode = parseAscArea((byte[]) message, offs, 6);
				break;
			case 4: // 商户编码
				this.bm4MerchantNo = parseAscArea((byte[]) message, offs, 15);
				break;
			case 5:// 终端编号
				this.bm5TerminalNo = parseAscArea((byte[]) message, offs, 8);
				break;
			case 6:// 交易流水号
				this.bm6TradeSerialNo = parseAscArea((byte[]) message, offs, 6);
				break;
			case 7:// 消费金额
				this.bm7TradeAmount = Long.valueOf(parseAscArea((byte[]) message, offs, 10));
				break;
			case 8:// 会员积分余额
				this.bm8MemberIntegralAmount =Long.valueOf( parseAscArea((byte[]) message, offs, 10));
				break;
			case 9:// 会员积分发生额
				this.bm9MemberActiveAmount =Long.valueOf( parseAscArea((byte[]) message, offs, 6));
				break;
			case 10:// 会员积分更改标志
				this.bm10 = parseAscArea((byte[]) message, offs, 1);
				break;
			case 11:// 终端交易日期
				this.bm11TerminalTradeDate = parseAscArea((byte[]) message, offs, 8);
				break;
			case 12:// 终端交易时间
				this.bm12TerminalTradeTime = parseAscArea((byte[]) message, offs, 6);
				break;
			case 13:// 商户名称
				this.bm13MerchantName = parseContainLL((byte[]) message, offs, 2);
				break;
			case 14:// 商户地址
				this.bm14MerchantAdress = parseContainLL((byte[]) message, offs, 2);
				break;
			case 15:// 会员级别名称
				this.bm15MemberLevelName = parseContainLL((byte[]) message, offs, 2);
				break;
			case 16:// 会员折扣
				this.bm16MemberDiscount = parseAscArea((byte[]) message, offs, 4);
				break;
			case 17:// 商户电话
				this.bm17MerchantMobile = parseAscArea((byte[]) message, offs, 20);
				break;
			case 18:// 实际交易金额
				this.bm18PosSerialNo = parseAscArea((byte[]) message, offs, 16);
				break;
			case 19:// 退货金额
				this.bm19PosBussList = parseAscArea((byte[]) message, offs, 2);
				break;
			case 20:// 积分纪录
				this.bm20MobileValidRule =parseContainLL((byte[]) message, offs, 2);// parseContainPointRecord((byte[]) message, offs);
				break;
			case 21:// 结算数据
				this.bm21ClosingCost = parseClosingCost((byte[]) message, offs);
				break;
			case 22:// 校验码
				this.bm22 = parseAscArea((byte[]) message, offs, 4);
				break;
			case 23:// 应答码
				this.bm23RespCode = parseAscArea((byte[]) message, offs, 2);
				break;
			case 24:// 响应数据
				this.bm24RespMess = parseContainLL((byte[]) message, offs, 2);
				break;
			case 25:// 会员申请日期
				this.bm25MemberApplyDate = parseAscArea((byte[]) message, offs, 14);
				break;
			case 26:// 广告打印反馈列表
				this.bm26 = parseAdvertPrintFeedBackList((byte[]) message, offs);
				break;
			case 27:// 待升级会员级别名称
				this.bm27 = parseContainLL((byte[]) message, offs, 2);
				break;	
			case 28:// 最近会员等级积分差额
				this.bm28 =parseAscArea((byte[]) message, offs, 12);
				break;	
			case 29://原交易流水号
				this.bm29OriginalTradSerial =parseAscArea((byte[]) message, offs, 6);
				break;	
			case 30://批次号
				this.bm30BatchNo =parseAscArea((byte[]) message, offs, 6);
				break;	
			case 31://原批次号
				this.bm31OriginalBatchNo =parseAscArea((byte[]) message, offs, 6);
				break;
			case 32://会员激活码
				this.bm32 =parseAscArea((byte[]) message, offs, 4);
				break;
			case 33://会员信息列表
				this.bm33MemberMess=parseMemberMessList((byte[])message,offs);
				break;
			case 34://级别信息
				this.bm34MemberLevelInfo=parseMemberLevelList((byte[])message,offs);
				break;	
			case 35:// 会员可用积分余额
				this.bm35MemberUseableIntegral = Long.valueOf(parseAscArea((byte[]) message, offs, 10));
				break;
			case 36://交易上送信息
				this.bm36BusinessUploadInfoList = parseBusinessInfo((byte[])message,offs);
				break;
			case 37:// 会员可用积分余额
				this.bm37MessageSerial = parseAscArea((byte[]) message, offs, 6);
				break;
			case 38: //报文后续标志
				this.bm38MessageFollowFlag=parseAscArea((byte[]) message, offs, 1);
				break;
			case 39://原会员编号（非手机号）
				this.bm39OriginalMemberSerial=parseAscArea((byte[]) message, offs, 20);
				break;
			case 40://会员姓名
				this.bm40MemberName=parseAscArea((byte[]) message, offs, 10);
				break;
			case 41://折扣计算标志
				this.bm41DiscountFlag=parseAscArea((byte[]) message, offs, 1);
				break;
			case 42://POS应用编号
				this.bm42AppSeriaNo=parseAscArea((byte[]) message, offs, 10);
				break;
			case 43://应用版本号
				this.bm43AppVersionNo=parseAscArea((byte[]) message, offs, 10);
				break;
			case 44://券序列号
				this.bm44CouponSeriano=parseAscArea((byte[]) message, offs, 8);
				break;
			case 45://券信息列表
				this.bm45CouponSketchList=parseCouponSketchList((byte[])message,offs,2);
				break;
			case 46://券活动信息列表
				this.bm46CouponList=parseCouponList((byte[]) message, offs, 10);
				break;
			case 47:
				this.bm47CouponUploadList=parsCouponUpload((byte[])message,offs);
				break;
			case 64: // mac
				if (!AREA1_ISLONG){
					byte[] arr1=new byte[8];
					System.arraycopy((byte[]) message, offs, arr1, 0, 8);
				//	this.bm64 =ByteUtil.arrayShortHexString(arr1);
				
					this.bm64 = parseAscArea((byte[]) message, offs, 8);
				}
				break;
			}
		}
	}

	public Object encode(Hs8583Dto hs,TransferProtocol protocol) throws Exception {
	
		hs.bm1HostBitElement="";
		//System.out.println(hs.toString());
		byte[] tpdu=ByteUtil.asciiToBcd("0000000000");
		this.message = hs.bm0MessageType.getBytes();//ByteUtil.asciiToBcd(hs.bm0MessageType);
		message = ByteUtil.contactArray(tpdu,(byte[]) message);
		message = ByteUtil.contactArray((byte[]) message, grenarateOneArea(hs));
		if(hs.bm2MemberMobile!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm2MemberMobile.getBytes());
		if(hs.bm3TradeCode!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm3TradeCode.getBytes());
		if(hs.bm4MerchantNo!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm4MerchantNo.getBytes());
		if(hs.bm5TerminalNo!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm5TerminalNo.getBytes());
		if(hs.bm6TradeSerialNo!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm6TradeSerialNo.getBytes());
		if(hs.getBm7TradeAmountString()!=null)message = ByteUtil.contactArray((byte[]) message, hs.getBm7TradeAmountString().getBytes());
		if(hs.getBm8MemberIntegralAmountString()!=null)message = ByteUtil.contactArray((byte[]) message, hs.getBm8MemberIntegralAmountString().getBytes());
		if(hs.getBm9MemberActiveAmountString()!=null)message = ByteUtil.contactArray((byte[]) message, hs.getBm9MemberActiveAmountString().getBytes());
		if(hs.bm10!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm10.getBytes());
		if(hs.bm11TerminalTradeDate!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm11TerminalTradeDate.getBytes());
		if(hs.bm12TerminalTradeTime!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm12TerminalTradeTime.getBytes());
		int listsize=0;
		if(hs.bm13MerchantName!=null){	
				 listsize =  hs.bm13MerchantName.getBytes("gbk").length;
				 message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", listsize)));
//				 message=ByteUtil.contactArray((byte[]) message,ByteUtil.toLLH(listsize));
				 message = ByteUtil.contactArray((byte[]) message,
					hs.bm13MerchantName.getBytes("gbk"));
		}
		if(hs.bm14MerchantAdress!=null){	
			listsize =  hs.bm14MerchantAdress.getBytes("gbk").length;
			message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", listsize)));
//			message =ByteUtil.contactArray((byte[]) message,ByteUtil.toLLH(listsize));
			message = ByteUtil.contactArray((byte[]) message,
					hs.bm14MerchantAdress.getBytes("gbk"));
		}
		if(hs.bm15MemberLevelName!=null){	
			listsize = hs.bm15MemberLevelName.getBytes("gbk").length;
			message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", listsize)));
//			message = ByteUtil.contactArray((byte[]) message,ByteUtil.toLLH(listsize));
			message = ByteUtil.contactArray((byte[]) message,
					hs.bm15MemberLevelName.getBytes("gbk"));
		}
		if(hs.bm16MemberDiscount!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm16MemberDiscount.getBytes());
		if(hs.bm17MerchantMobile!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm17MerchantMobile.getBytes());
		if(hs.bm18PosSerialNo!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm18PosSerialNo.getBytes());
		if(hs.bm19PosBussList!=null)message = ByteUtil.contactArray((byte[]) message, ByteUtil.asciiToBcd(hs.bm19PosBussList));
//		if(hs.bm20IntegralRecord!=null&&hs.bm20IntegralRecord.size()!=0){
//			listsize = hs.bm20IntegralRecord.size() * 42;
//			message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", listsize)));
////			message = ByteUtil.contactArray((byte[]) message,ByteUtil.toLLLH(listsize));
//			addListToMessage(hs.bm20IntegralRecord);
//		}
		if(hs.bm20MobileValidRule!=null){	
			listsize = hs.bm20MobileValidRule.getBytes("gbk").length;
			message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", listsize)));
			message = ByteUtil.contactArray((byte[]) message,
					hs.bm20MobileValidRule.getBytes("gbk"));
		}
		if(hs.bm21ClosingCost!=null){
			addClosingCostDto(hs.bm21ClosingCost);
		}
		if(hs.bm22!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm22.getBytes());
		if(hs.bm23RespCode!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm23RespCode.getBytes());
		if(hs.bm24RespMess!=null){
			listsize = hs.bm24RespMess.getBytes("gbk").length;
			message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", listsize)));
			message = ByteUtil.contactArray((byte[]) message,
					hs.bm24RespMess.getBytes("gbk"));
		}
		if(hs.bm25MemberApplyDate!=null)
			message = ByteUtil.contactArray((byte[]) message, hs.bm25MemberApplyDate.getBytes());
		if(hs.bm26!=null)
			addHs8583AdvertPrintFeedBackList(hs.bm26);
		if(hs.bm27!=null){
			listsize = hs.bm27.getBytes("gbk").length;
			message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", listsize)));
//			message = ByteUtil.contactArray((byte[]) message,ByteUtil.toLLH(listsize));
			message = ByteUtil.contactArray((byte[]) message,
					hs.bm27.getBytes("gbk"));
		}
		if(hs.bm28!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm28.getBytes());
		if(hs.bm29OriginalTradSerial!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm29OriginalTradSerial.getBytes());
		if(hs.bm30BatchNo!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm30BatchNo.getBytes());
		if(hs.bm31OriginalBatchNo!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm31OriginalBatchNo.getBytes());
		if(hs.bm32!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm32.getBytes());
		
		if(hs.getBm35MemberUseableIntegralString()!=null)message= ByteUtil.contactArray((byte[]) message,  hs.getBm35MemberUseableIntegralString().toString().getBytes());
		if(hs.bm36BusinessUploadInfoList!=null&&hs.bm36BusinessUploadInfoList.size()!=0){
			addTradUploadMess(hs.bm36BusinessUploadInfoList);			
		}
		if(hs.bm37MessageSerial!=null)message =ByteUtil.contactArray((byte[]) message,hs.bm37MessageSerial.getBytes());
		if(hs.bm38MessageFollowFlag!=null)message =ByteUtil.contactArray((byte[]) message,hs.bm38MessageFollowFlag.getBytes());
		if(hs.bm39OriginalMemberSerial!=null)message =ByteUtil.contactArray((byte[]) message,hs.bm39OriginalMemberSerial.getBytes());
		if(hs.bm40MemberName!=null)message =ByteUtil.contactArray((byte[]) message,hs.bm40MemberName.getBytes("gbk"));
		if(hs.bm41DiscountFlag!=null)message=ByteUtil.contactArray((byte[]) message, hs.getBm41DiscountFlag().toString().getBytes());
		if(hs.bm42AppSeriaNo!=null)message =ByteUtil.contactArray((byte[]) message,hs.getBm42AppSeriaNo().toString().getBytes("gbk"));
		if(hs.bm43AppVersionNo!=null)message =ByteUtil.contactArray((byte[]) message,hs.getBm43AppVersionNo().getBytes("gbk"));
		if(hs.bm44CouponSeriano!=null)message=ByteUtil.contactArray((byte[]) message,hs.getBm44CouponSeriano().getBytes("gbk"));
		if(hs.bm45CouponSketchList!=null){
			addCouponSketchList(hs.bm45CouponSketchList);
		}
		if(hs.bm46CouponList!=null){
			addCouponPloyList(hs.bm46CouponList);
		}
		if(hs.bm47CouponUploadList!=null&&hs.bm47CouponUploadList.size()!=0){
			addCouponUploadMess(hs.bm47CouponUploadList);			
		}
		if(hs.bm64!=null)message = ByteUtil.contactArray((byte[]) message, hs.bm64.getBytes());
		//if(hs.bm64!=null)message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(hs.bm64));
		
		int messageLent=((byte[]) message).length;
		message=ByteUtil.contactArray(ByteUtil.hexStringToBytes(String.format("%4s", Integer.toHexString(messageLent)).replace(" ", "0")),(byte[]) message);
		return message;
	}

	public static void main(String args[]) {
		try {
			String aa="01";
		//	byte[] bb=ByteUtil.hexStringToBytes(Long.toHexString(Long.valueOf(aa)));
			byte[] cc=ByteUtil.hexStringToBytes(aa);
			Integer ll=1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	    
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("bm0=["+this.bm0MessageType+"]\n");
		sb.append("bm1=["+this.bm1HostBitElement+"]\n");
		sb.append("bm2=["+this.bm2MemberMobile+"]\n");
		sb.append("bm3=["+this.bm3TradeCode+"]\n");
		sb.append("bm4=["+this.bm4MerchantNo+"]\n");
		sb.append("bm5=["+this.bm5TerminalNo+"]\n");
		sb.append("bm6=["+this.bm6TradeSerialNo+"]\n");
		sb.append("bm7=["+this.getBm7TradeAmountString()+"]\n");
		sb.append("bm8=["+this.getBm8MemberIntegralAmountString()+"]\n");
		sb.append("bm9=["+this.getBm9MemberActiveAmountString()+"]\n");
	//	sb.append("bm10=["+this.bm10+"]\n");
		sb.append("bm11=["+this.bm11TerminalTradeDate+"]\n");
		sb.append("bm12=["+this.bm12TerminalTradeTime+"]\n");
		sb.append("bm13=["+this.bm13MerchantName+"]\n");
		sb.append("bm14=["+this.bm14MerchantAdress+"]\n");
		sb.append("bm15=["+this.bm15MemberLevelName+"]\n");
		sb.append("bm16=["+this.bm16MemberDiscount+"]\n");
		sb.append("bm17=["+this.bm17MerchantMobile+"]\n");
		sb.append("bm18=["+this.bm18PosSerialNo+"]\n");
		sb.append("bm19=["+this.bm19PosBussList+"]\n");
		sb.append("bm20=["+this.bm20MobileValidRule+"]\n");
//		for(Hs8583PointDto hpd:this.bm20IntegralRecord){
//			sb.append("bm20=[--------------]\n");
//			sb.append("bm20=["+hpd.getTransTime()+"]\n");
//			sb.append("bm20=["+hpd.getTransAmount()+"]\n");
//			sb.append("bm20=["+hpd.getTransScale()+"]\n");
//			sb.append("bm20=["+hpd.getTransPoint()+"]\n");
//			sb.append("bm20=["+hpd.getAcceptPos()+"]\n");
//			sb.append("bm20=[--------------]\n");
//		}
		if(this.bm21ClosingCost!=null){
		sb.append("bm21=[Hs8583ClosingCostDto--------------]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getConsumeCount()+"]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getConsumeAmount()+"]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getReturnCount()+"]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getReturnAmount()+"]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getIntegralConsumeCount()+"]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getIntegralConsumeAmount()+"]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getIntegralReturnCount()+"]\n");
		sb.append("bm21=["+this.bm21ClosingCost.getIntegralReturnAmount()+"]\n");
		sb.append("bm21=[Hs8583ClosingCostDto--------------]\n");
		}
	//	sb.append("bm22=["+this.bm22+"]\n");
		sb.append("bm23=["+this.bm23RespCode+"]\n");
		sb.append("bm24=["+this.bm24RespMess+"]\n");
		sb.append("bm25=["+this.bm25MemberApplyDate+"]\n");
		if(this.bm26!=null){
		sb.append("bm26=[Hs8583AdvertPrintFeedBackList--------------]\n");
		sb.append("bm26=["+this.bm26.getAppId()+"]\n");
		sb.append("bm26=["+this.bm26.getAppVersion()+"]\n");
		sb.append("bm26=["+this.bm26.getParamName()+"]\n");
		sb.append("bm26=["+this.bm26.getPrintNo()+"]\n");
		sb.append("bm26=["+this.bm26.getAdsDescription()+"]\n");
		sb.append("bm26=[Hs8583AdvertPrintFeedBackList--------------]\n");
		}
	//	sb.append("bm27=["+this.bm27+"]\n");
	//	sb.append("bm28=["+this.bm28+"]\n");
		sb.append("bm29=["+this.bm29OriginalTradSerial+"]\n");
		sb.append("bm30=["+this.bm30BatchNo+"]\n");
		sb.append("bm31=["+this.bm31OriginalBatchNo+"]\n");
	//	sb.append("bm32=["+this.bm32+"]\n");
		
		sb.append("bm35=["+this.getBm35MemberUseableIntegralString()+"]\n");
		if(this.bm36BusinessUploadInfoList!=null&&this.bm36BusinessUploadInfoList.size()!=0){
			for(Hs8583BusinessUploadInfo Info: this.bm36BusinessUploadInfoList){
				sb.append("bm36=[Hs8583AdvertPrintFeedBackList--------------]\n");
				sb.append("bm36=["+Info.getMobileNo()+"]\n");
				sb.append("bm36=["+Info.getAmount()+"]\n");
				sb.append("bm36=["+Info.getTerminalTransDate()+"]\n");
				sb.append("bm36=["+Info.getTerminalTransTime()+"]\n");
				sb.append("bm36=["+Info.getTerminalNo()+"]\n");
				sb.append("bm36=["+Info.getTerminalSerial()+"]\n");
				sb.append("bm36=["+Info.getOriginalTerminalNo()+"]\n");
				sb.append("bm36=["+Info.getOriginalSerial()+"]\n");
				sb.append("bm36=["+Info.getOriginalBatchNo()+"]\n");
				sb.append("bm36=["+Info.getBankCardNo()+"]\n");
				sb.append("bm36=["+Info.getTransType()+"]\n");
				sb.append("bm36=[Hs8583AdvertPrintFeedBackList--------------]\n");
			}
		}
		
		sb.append("bm37=["+this.bm37MessageSerial+"]\n");
		sb.append("bm38=["+this.bm38MessageFollowFlag+"]\n");
		sb.append("bm39=["+this.bm39OriginalMemberSerial+"]\n");
		sb.append("bm40=["+this.bm40MemberName+"]\n");
		sb.append("bm41=["+this.bm41DiscountFlag+"]\n");
		sb.append("bm42=["+this.bm42AppSeriaNo+"]\n");
		sb.append("bm43=["+this.bm43AppVersionNo+"]\n");
		sb.append("bm44=["+this.bm44CouponSeriano+"]\n");
		sb.append("bm45=["+this.bm45CouponSketchList+"]\n");
		if(this.bm46CouponList!=null&&this.bm46CouponList.size()!=0){
			for(Hs8583CouponList Info: this.bm46CouponList){
				sb.append("bm46=[bm46CouponList--------------]\n");
				sb.append("bm46=["+Info.getCouponPloySerialNo()+"]\n");
				sb.append("bm46=["+Info.getCouponName()+"]\n");
				sb.append("bm46=["+Info.getCouponNum()+"]\n");
				sb.append("bm46=[bm46CouponList--------------]\n");
			}
		}
		if(this.bm47CouponUploadList!=null&&this.bm47CouponUploadList.size()!=0){
			for(Hs8583CouponUploadInfo Info: this.bm47CouponUploadList){
				sb.append("bm47=[bm47CouponUploadList--------------]\n");
				sb.append("bm47=["+Info.getCouponSerialNo()+"]\n");
				sb.append("bm47=["+Info.getTerminalTransDate()+"]\n");
				sb.append("bm47=["+Info.getTerminalTransTime()+"]\n");
				sb.append("bm47=["+Info.getTerminalSerial()+"]\n");
				sb.append("bm47=[bm47CouponUploadList--------------]\n");
			}
		}
		sb.append("bm64=["+this.bm64+"]\n");
		return sb.toString();
	}

}
