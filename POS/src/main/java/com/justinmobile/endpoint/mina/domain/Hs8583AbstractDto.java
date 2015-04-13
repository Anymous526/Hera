package com.justinmobile.endpoint.mina.domain;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.justinmobile.endpoint.mina.TransferProtocol;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.service.PosServiceException;
import com.justinmobile.util.ByteUtil;
import com.vlives.core.exception.PosReturnCode;

public abstract class Hs8583AbstractDto {

	protected Object message;
	protected TransferProtocol protocol;
	protected MtRequestCode requestCode;
	protected String requestCodeString;
	
	protected static String VALUE_NO="0";
	protected static String VALUE_YES="1";
	protected static boolean AREA1_ISLONG=false;
	protected static int offs=0;
	
	public static String MEMBER_POINT_ADD="1";
	public static String MEMBER_POINT_MINUS="0";

	



	protected static String getPartOfLength(String origenal,int lent){
		if(origenal.length()>lent){
			return origenal.substring(0, lent);
		}else{
			return origenal;
		}
	}

	/**积分纪录特殊解析
	 * @param resouce
	 * @param offset
	 * @return
	 * @throws PosServiceException 
	 */
	protected List<Hs8583PointDto> parseContainPointRecord(byte[] resouce,int offset) throws PosServiceException{
		try{
			int messLen=40;
			byte[] arr0=new byte[2];
			System.arraycopy(resouce, offset, arr0, 0, 2);
			//arr0=ByteUtil.exchangeHLSequence(arr0); //转换为本地字节序
			Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0,"gbk"));
			offs=offs+2;
			
			arr0=new byte[messLen];
			List<Hs8583PointDto> pointList=new ArrayList<Hs8583PointDto>();
			Hs8583PointDto pointdto=null;
			for(int i=0;i<leng/40;i++){
				pointdto=new Hs8583PointDto();
				
				System.arraycopy(resouce, offs, arr0, 0, messLen);
				offs=offs+40;
				String point=new String(arr0,"gbk");
				pointdto.setTransTime(point.substring(0, 14));
				pointdto.setTransAmount(point.substring(14, 24));
				pointdto.setTransScale(point.substring(24, 26));
				pointdto.setTransPoint(point.substring(26, 32));
				pointdto.setAcceptPos(point.substring(32, 40));
				pointList.add(pointdto);
				
			}
			return pointList;
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);

		}
	}
	
	
	/**结算数据
	 * @param resouce
	 * @param offset
	 * @return
	 * @throws PosServiceException 
	 */
	protected Hs8583ClosingCostDto parseClosingCost(byte[] resouce,int offset) throws PosServiceException{
		try{
			int messLen=64;
			byte[] arr0=new byte[messLen];
			Hs8583ClosingCostDto closedto=new Hs8583ClosingCostDto();
				System.arraycopy(resouce, offset, arr0, 0, messLen);
				
				String point=new String(arr0,"gbk");
				closedto.setConsumeCount(Long.valueOf(point.substring(0, 6)));
				closedto.setConsumeAmount(Long.valueOf(point.substring(6, 16)));
				closedto.setReturnCount(Long.valueOf(point.substring(16, 22)));
				closedto.setReturnAmount(Long.valueOf(point.substring(22, 32)));
				closedto.setIntegralConsumeCount(Long.valueOf(point.substring(32, 38)));
				closedto.setIntegralConsumeAmount(Long.valueOf(point.substring(38, 48)));
				closedto.setIntegralReturnCount(Long.valueOf(point.substring(48, 54)));
				closedto.setIntegralReturnAmount(Long.valueOf(point.substring(54, 64)));
				offs=offset+messLen;
			return closedto;
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);

		}
	}
	
	/**会员信息列表
	 * @param resource
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	protected List<Hs8583MemberMess> parseMemberMessList(byte[] resource,int offset)throws PosServiceException{
		try{
			int lent=16;  //单个元素的长度
			byte[] arr0=new byte[2];
			System.arraycopy(resource, offset, arr0, 0, 2);
			//总长度
			Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0,"gbk"));
			offs=offs+2;
			arr0=new byte[lent];
			List<Hs8583MemberMess> memberList=new ArrayList<Hs8583MemberMess>();
			Hs8583MemberMess memberMess=null;
			for(int i=0;i<leng/lent;i++){
				memberMess=new Hs8583MemberMess();
				
				System.arraycopy(resource, offs, arr0, 0, lent);
				offs=offs+lent;
				String list=ByteUtil.arrayShortHexString(arr0);
				memberMess.setMemberMobile(list.substring(0, 11));
				memberMess.setBankCardNo(list.substring(11,31));
				memberMess.setMemberLevelNo(list.substring(31,32));
				memberList.add(memberMess);
			}
			return memberList;
		}catch(Exception e){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
	}
	
	
	/**会员等级列表解析
	 * @param resource
	 * @param offset
	 * @return
	 * @throws PosServiceException
	 */
	protected List<Hs8583MemberLevelInfo> parseMemberLevelList(byte[] resource,int offset)throws PosServiceException{
		try{
				int lent=44;
				byte[] arr0=new byte[2];
				System.arraycopy(resource, offset, arr0, 0, 2);
				//arr0=ByteUtil.exchangeHLSequence(arr0); //转换为本地字节序
				//总长度
				Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0,"gbk"));
				offs=offs+2;
				//读取毫无意义的个数
				System.arraycopy(resource, offset, arr0, 0, 2);
				int sum=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));
				leng=leng-2;//总长度减去个数的长度
				offs=offs+2;
				
				arr0=new byte[lent];
				List<Hs8583MemberLevelInfo> levelList=new ArrayList<Hs8583MemberLevelInfo>();
				Hs8583MemberLevelInfo levelInfo=null;
				for(int i=0;i<leng/lent;i++){
					levelInfo=new Hs8583MemberLevelInfo();
					System.arraycopy(resource, offs, arr0, 0, lent);
					offs=offs+lent;
					String level=new String(arr0,"gbk");
					levelInfo.setLevelName(level.substring(0, 20));
					levelInfo.setLevelMemo(level.substring(20, 40));
					levelInfo.setLevelDiscount(level.substring(40, 44));
					
					levelList.add(levelInfo);
				}
				return levelList;
		}catch(Exception e){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);

		}
	}
	
	/**交易上送信息
	 * @param resource
	 * @param offset
	 * @return
	 * @throws PosServiceException
	 */
	protected List<Hs8583BusinessUploadInfo> parseBusinessInfo(byte[] resource,int offset)throws PosServiceException{
		try{
				int lent=45;
				byte[] arr0=new byte[2];
				System.arraycopy(resource, offset, arr0, 0, 2);
				//总长度
				Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0,"gbk"));
				offs=offs+2;
				arr0=new byte[lent];
				List<Hs8583BusinessUploadInfo> uploadInfoList=new ArrayList<Hs8583BusinessUploadInfo>();
				Hs8583BusinessUploadInfo uploadInfo=null;
				for(int i=0;i<leng/lent;i++){
					uploadInfo=new Hs8583BusinessUploadInfo();
					
					System.arraycopy(resource, offs, arr0, 0, lent);
					offs=offs+lent;
					String info=ByteUtil.arrayShortHexString(arr0);
					
					uploadInfo.setMobileNo(info.substring(0, 11));
					uploadInfo.setAmount(Long.valueOf(info.substring(11, 21)));
					uploadInfo.setTerminalTransDate(info.substring(21, 29));
					uploadInfo.setTerminalTransTime(info.substring(29, 35));
					uploadInfo.setTerminalNo(info.substring(35, 43));
					uploadInfo.setTerminalSerial(info.substring(43, 49));
					uploadInfo.setOriginalTerminalNo(info.substring(49, 57));
					uploadInfo.setOriginalSerial(info.substring(57,63 ));
					uploadInfo.setOriginalBatchNo(info.substring(63,69 ));
					uploadInfo.setBankCardNo(info.substring(69, 89));
					uploadInfo.setTransType(Integer.valueOf(info.substring(89, 90)));				
					uploadInfoList.add(uploadInfo);
				}
				return uploadInfoList;
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
	}
	
	/**券上送信息
	 * @param resource
	 * @param offset
	 * @return
	 * @throws PosServiceException
	 */
	protected List<Hs8583CouponUploadInfo> parsCouponUpload(byte[] resource,int offset)throws PosServiceException{
		try{
				int lent=14;
				byte[] arr0=new byte[2];
				System.arraycopy(resource, offset, arr0, 0, 2);
				//总长度
				Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0,"gbk"));
				offs=offs+2;
				arr0=new byte[lent];
				List<Hs8583CouponUploadInfo> uploadInfoList=new ArrayList<Hs8583CouponUploadInfo>();
				Hs8583CouponUploadInfo uploadInfo=null;
				for(int i=0;i<leng/lent;i++){
					uploadInfo=new Hs8583CouponUploadInfo();
					System.arraycopy(resource, offs, arr0, 0, lent);
					offs=offs+lent;
					String info=ByteUtil.arrayShortHexString(arr0);
					uploadInfo.setCouponSerialNo(info.substring(0, 8));
					uploadInfo.setTerminalTransDate(info.substring(8, 16));
					uploadInfo.setTerminalTransTime(info.substring(16, 22));
					uploadInfo.setTerminalSerial(info.substring(22, 28));
					uploadInfoList.add(uploadInfo);
				}
				return uploadInfoList;
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
	}
	
	/**广告打印反馈列表
	 * @param resouce
	 * @param offset
	 * @return
	 * @throws PosServiceException 
	 */
	protected Hs8583AdvertPrintFeedBackList parseAdvertPrintFeedBackList(byte[] resouce,int offset) throws PosServiceException{
		try{
			int leng=114;
			byte[] arr0=new byte[leng];
			Hs8583AdvertPrintFeedBackList advert=new Hs8583AdvertPrintFeedBackList();
				System.arraycopy(resouce, offs, arr0, 0, leng);
				
				String advetContent=new String(arr0,"gbk");
				advert.setAppId(advetContent.substring(0, 10));
				advert.setAppVersion(advetContent.substring(10, 16));
				advert.setParamName(advetContent.substring(16, 48));
				advert.setPrintNo(advetContent.substring(48, 54));
				advert.setAdsDescription(advetContent.substring(54, advetContent.length()));
				offs=offset+leng;
			return advert;
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);

		}
	}
	
	protected List<Hs8583CouponList> parseCouponList(byte[] resource,int offset,int lnum){
		try{
			int messLen=22	;
			byte[] arr0=new byte[2];
			System.arraycopy(resource, offset, arr0, 0, 2);
			//arr0=ByteUtil.exchangeHLSequence(arr0); //转换为本地字节序
			Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0,"gbk"));
			offs=offs+2;
			arr0=new byte[messLen];
			List<Hs8583CouponList> pointList=new ArrayList<Hs8583CouponList>();
			Hs8583CouponList couponList=null;
			for(int i=0;i<leng/22;i++){
				couponList=new Hs8583CouponList();
				
				System.arraycopy(resource, offs, arr0, 0, messLen);
				offs=offs+22;
				couponList.setCouponPloySerialNo(Long.valueOf(ByteUtil.arrayShortHexString(ArrayUtils.subarray(arr0, 0, 4))));
				couponList.setCouponName(new String(ArrayUtils.subarray(arr0, 4, 18),"gbk"));
				couponList.setCouponNum(Long.valueOf(ByteUtil.arrayShortHexString(ArrayUtils.subarray(arr0, 18, 22))));
				pointList.add(couponList);
				
			}
			return pointList;
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);

		}
	}
	
	
	/**优惠券信息列表解析
	 * @param resource
	 * @param offset
	 * @param lnum
	 * @return
	 */
	protected Hs8583CouponSketchList parseCouponSketchList(byte[] resource,int offset,int lnum){
		byte[] arr0= parseContainLLSub( resource, offset, lnum);
		Hs8583CouponSketchList hcslist=new Hs8583CouponSketchList();
		nestInvoke( hcslist,arr0);
		return hcslist;
	}
	
	/**嵌套解析优惠券信息
	 * @param hcslist
	 * @param arr0
	 * @return
	 */
	private Hs8583CouponSketchList nestInvoke( Hs8583CouponSketchList hcslist,byte[] arr0){
		  try{
			   if(arr0.length>8){
				   	byte[] couponNo=new byte[8];
					System.arraycopy(arr0, 0, couponNo, 0, 8);
					String mapKey= new String(arr0,"gbk");
					//去掉了券编号
					arr0=ArrayUtils.subarray(arr0, 7, arr0.length);
					//
					byte[] arr1=ArrayUtils.subarray(arr0, 0, 2);
					Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0));
					//去掉了长度
					arr0=ArrayUtils.subarray(arr0, 2, arr0.length);
					arr1=ArrayUtils.subarray(arr0, 0, leng);
					//去掉了券内容
					arr0=ArrayUtils.subarray(arr0, leng, arr0.length);
					String mapValue= new String(arr1,"gbk");
					hcslist.addCouponType(mapKey, mapValue)	;
					if(arr0.length>8){
						return 	nestInvoke(hcslist,arr0);
					}
			   }
			    return hcslist; 
	   }catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
			
	   }
		
	}
	
	
	
	public static void main(String args[]){
		byte[] mm=new byte[]{0x00,0x01,0x03,0x05};
		byte[] m=org.apache.commons.lang.ArrayUtils.subarray(mm, 1, mm.length);
		System.out.println();
	}
	
	
	/**解析包括L长度报文头 的报文
	 * @param resouce 原始报文
	 * @param offset  总便宜量
	 * @param lnum  L的 个数
	 * @return
	 * @throws PosServiceException 
	 */
	protected String parseContainLL(byte[] resouce,int offset,int lnum) throws PosServiceException{
		try{
			byte[] arr0= parseContainLLSub(resouce, offset, lnum);
			return new String(arr0,"gbk");
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);

		}
	}

	private byte[] parseContainLLSub(byte[] resouce,int offset,int lnum){
		byte[] arr0=new byte[lnum];
		System.arraycopy(resouce, offset, arr0, 0, lnum);
		//arr0=ByteUtil.exchangeHLSequence(arr0); //转换本地字节序
		Integer leng=Integer.valueOf(ByteUtil.arrayShortHexString(arr0));//Integer.valueOf(new String(arr0));
		arr0=new byte[leng];
		offs=offset+lnum;
		System.arraycopy(resouce, offs, arr0, 0, leng);
		offs=offs+leng;
		return arr0;
	}
	
	/**解析 普通ASSC域
	 * @param resouce  原始 报文
	 * @param offset  起始偏移
	 * @param lent   长度 
	 * @return
	 * @throws PosServiceException 
	 */
	protected String parseAscArea(byte[] resouce,int offset,int lent) throws PosServiceException{
		try{
			byte[] arr0=new byte[lent];
			System.arraycopy(resouce, offset, arr0, 0, lent);
			offs=offset+lent;
			return new String(arr0,"gbk");
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
		
	}
	
	/**解析 普通BCD域
	 * @param resouce  原始 报文
	 * @param offset  起始偏移
	 * @param lent   长度 
	 * @return
	 * @throws PosServiceException 
	 */
	protected String parseBcdArea(byte[] resouce,int offset,int lent) throws PosServiceException{
		try{
			byte[] arr0=new byte[lent];
			System.arraycopy(resouce, offset, arr0, 0, lent);
			offs=offset+lent;
			return ByteUtil.arrayShortHexString(arr0);
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
		
	}
	protected String parseBm1(byte[] resouce,int offset,int lent) throws PosServiceException{
		//BM #1主位元表
		try{
		byte[] arr1=new byte[lent];
		System.arraycopy(resouce, offset, arr1, 0, lent);
		offs=offset+lent;
		String bm1str=Long.toBinaryString(Long.valueOf(ByteUtil.toHexString(arr1),16));
	    bm1str=String.format("%"+lent*2*4+"s", bm1str).replace(" ", "0");
		
	    return bm1str;
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
	}
	
	
	
	protected void addHs8583AdvertPrintFeedBackList(Hs8583AdvertPrintFeedBackList feedback){
		message=ByteUtil.contactArray((byte[]) message,feedback.getAppId().getBytes());
		message=ByteUtil.contactArray((byte[]) message,feedback.getAppVersion().getBytes());
		message=ByteUtil.contactArray((byte[]) message,feedback.getParamName().getBytes());
		message=ByteUtil.contactArray((byte[]) message,feedback.getPrintNo().getBytes());
		try {
			message=ByteUtil.contactArray((byte[]) message,feedback.getAdsDescription().getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	protected void addClosingCostDto(Hs8583ClosingCostDto closeCost){
		message=ByteUtil.contactArray((byte[]) message,closeCost.getConsumeCountString().getBytes());
		message=ByteUtil.contactArray((byte[]) message,closeCost.getConsumeAmountString().getBytes());
		message=ByteUtil.contactArray((byte[]) message,closeCost.getReturnCountString().getBytes());
		message=ByteUtil.contactArray((byte[]) message,closeCost.getReturnAmountString().getBytes());
		message=ByteUtil.contactArray((byte[]) message,closeCost.getIntegralConsumeCountString().getBytes());
		message=ByteUtil.contactArray((byte[]) message,closeCost.getIntegralConsumeAmountString().getBytes());
		message=ByteUtil.contactArray((byte[]) message,closeCost.getIntegralReturnCountString().getBytes());
		message=ByteUtil.contactArray((byte[]) message,closeCost.getIntegralReturnAmountString().getBytes());
	}
	
	protected void addListToMessage(List<Hs8583PointDto> pointlist){
		for(Hs8583PointDto point:pointlist){
			message=ByteUtil.contactArray((byte[]) message,point.getTransTime().getBytes());
			message=ByteUtil.contactArray((byte[]) message,point.getTransAmount().getBytes());
			message=ByteUtil.contactArray((byte[]) message,point.getTransScale().getBytes());
			message=ByteUtil.contactArray((byte[]) message,point.getTransPoint().getBytes());
			message=ByteUtil.contactArray((byte[]) message,point.getAcceptPos().getBytes());
		}
	}
	
	protected void addTradUploadMess(List<Hs8583BusinessUploadInfo>  uploadlist){
		int lent=uploadlist.size()*45;
		message=ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", lent)));
		StringBuffer upload=new StringBuffer();
		for(Hs8583BusinessUploadInfo uploadInfo:uploadlist){	
			upload.append(uploadInfo.getMobileNo());
			upload.append(String.format("%010d", uploadInfo.getAmount()));
			upload.append(uploadInfo.getTerminalTransDate());
			upload.append(uploadInfo.getTerminalTransTime());
			upload.append(uploadInfo.getTerminalNo());
			upload.append(uploadInfo.getTerminalSerial());
			upload.append(uploadInfo.getOriginalTerminalNo());
			upload.append(uploadInfo.getOriginalSerial());
			upload.append(uploadInfo.getOriginalBatchNo());
			upload.append(addBland(uploadInfo.getBankCardNo(),20,'F'));
			upload.append(String.valueOf(uploadInfo.getTransType()));
		}
		message=ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(	upload.toString()));

	}
	
	protected void addCouponUploadMess(List<Hs8583CouponUploadInfo>  uploadlist){
		int lent=uploadlist.size()*14;
		message=ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", lent)));
		StringBuffer upload=new StringBuffer();
		for(Hs8583CouponUploadInfo uploadInfo:uploadlist){	
			upload.append(uploadInfo.getCouponSerialNo());
			upload.append(uploadInfo.getTerminalTransDate());
			upload.append(uploadInfo.getTerminalTransTime());
			upload.append(uploadInfo.getTerminalSerial());
		}
		message=ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(	upload.toString()));

	}
	
	
	
	protected String addTranseCoupon(String[] strarry){
		StringBuffer coupon=new StringBuffer();
		if(strarry.length!=0){
			for(int i=0;i<strarry.length;i++){
				coupon.append(strarry[i]);
			}
		}
		return 	addBland(coupon.toString(),40,' ');
	}
	
	protected void addCouponSketchList(Hs8583CouponSketchList bm45CouponSketchList){
		try{
			if(bm45CouponSketchList.getCouponSketchList()!=null&&bm45CouponSketchList.getCouponSketchList().size()!=0){
					byte[]  temp=new byte[0];
					for(String key:bm45CouponSketchList.getCouponSketchList().keySet()){
						temp=ByteUtil.contactArray((byte[]) temp,key.getBytes("gbk"));
						String value=bm45CouponSketchList.getCouponSketchList().get(key);
						byte[] valuebyte=value.getBytes("gbk");
						temp = ByteUtil.contactArray((byte[]) temp,ByteUtil.asciiToBcd(String.format("%04d", valuebyte.length)));
						temp = ByteUtil.contactArray((byte[]) temp,value.getBytes("gbk"));
					}
					message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", temp.length)));
					message = ByteUtil.contactArray((byte[]) message,temp);
			}
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
	}
	
	
	protected void addCouponPloyList(List<Hs8583CouponList> bm46CouponList){
		try{
			if(bm46CouponList.size()!=0){
				byte[]  temp=new byte[0];
				for(Hs8583CouponList ploy:bm46CouponList){
					temp=ByteUtil.contactArray((byte[]) temp,ByteUtil.asciiToBcd(String.format("%08d", ploy.getCouponPloySerialNo())));
					temp = ByteUtil.contactArray((byte[]) temp,addBland(ploy.getCouponName(),14,' ').getBytes("gbk"));
					temp=ByteUtil.contactArray((byte[]) temp,ByteUtil.asciiToBcd(String.format("%08d", ploy.getCouponNum())));
				}
				message = ByteUtil.contactArray((byte[]) message,ByteUtil.asciiToBcd(String.format("%04d", temp.length)));
				message = ByteUtil.contactArray((byte[]) message,temp);
			}
		}catch(Exception ex){
			throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
	}
	
	
	
	protected byte[] grenarateOneArea(Hs8583Dto hs){
		Class<?> clazz = hs.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String bbm1="";
		for (Field field : fields) {
			try {
				if(field.getName().startsWith("bm")){
					Method m = clazz.getDeclaredMethod("get" + StringUtils.capitalize(field.getName()));
					Object o=m.invoke(hs);
					if(o instanceof List){
						if(o!=null&&((List) o).size()==0){
							bbm1+=VALUE_NO;
						}else{
							bbm1+=VALUE_YES;
						}
					}else
					if(o instanceof Hs8583ClosingCostDto){
						if(o==null||((Hs8583ClosingCostDto) o).getConsumeAmount()==null){
							bbm1+=VALUE_NO;
						}else{
							bbm1+=VALUE_YES;
						}
					}else
					if(o instanceof Hs8583AdvertPrintFeedBackList){
						if(o!=null&&((Hs8583AdvertPrintFeedBackList) o).getAppId().equals("")){
							bbm1+=VALUE_NO;
						}else{
							bbm1+=VALUE_YES;
						}
					}else{
					
						if(o==null||o.equals("")){
							bbm1+=VALUE_NO;
						}else{
							bbm1+=VALUE_YES;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		int zeroNum=64-(bbm1.length()-2);
		String bb=bbm1.substring(1, bbm1.length()-1)+String.format("%0"+zeroNum+"d", 1);
		return ByteUtil.hexStringToBytes(Long.toHexString(Long.valueOf(bb, 2)));

//		String b1=Long.toHexString(Long.valueOf(bb.substring(0,32), 2));
//		String b2=Long.toHexString(Long.valueOf(bb.substring(32,64), 2));
//		return ByteUtil.hexStringToBytes(b1+String.format("%8s", b2).replace(" ", "0"));
	}
	

	
	protected static String addBland(String ss,int lent,char a){
		StringBuffer sb=new StringBuffer(ss);
		int count=0;
		try {
			count = lent-ss.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 for(int i=0;i<count;i++){
			 sb.append(a);
		 }
		 return sb.toString();
		
	}
	
	
	protected static String addBland(String ss,int lent){
		int leng=0;
		try {
			leng = ss.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(leng>10){
			return getPartOfLength(ss,5);
		}else {
			return addBland( ss, lent,' ');
		}
	}
}
