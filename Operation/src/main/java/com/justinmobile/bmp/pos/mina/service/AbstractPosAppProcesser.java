package com.justinmobile.bmp.pos.mina.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.proxy.utils.ByteUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.bmp.pos.domain.PosAppVersionRecord;
import com.justinmobile.bmp.pos.manager.PosAppManager;
import com.justinmobile.bmp.pos.manager.PosAppVersionManager;
import com.justinmobile.bmp.pos.manager.PosAppVersionRecordManager;
import com.justinmobile.bmp.pos.mina.dto.PosManagerDto;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException;
import com.justinmobile.bmp.pos.mina.dto.field.PosAppDownloadInfoField;
import com.justinmobile.bmp.pos.mina.dto.field.PosAppInfosField;
import com.justinmobile.bmp.pos.mina.dto.field.StringField;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException.ExceptionCode;
import com.justinmobile.bmp.pos.mina.log.PosManagerLog;

public class AbstractPosAppProcesser {
	
	public static  Byte by=null;
	
	@Autowired
	private PosAppVersionRecordManager posAppVersionRecordManager;
	@Autowired
	private PosAppManager posAppManager;
	@Autowired
	private PosAppVersionManager posAppVersionManager;
	
	public   byte[]   readVersionFile(String path)   throws   FieldException   { 		
		try{
//		String path="H:\\HUISH_HISUN(v1.1).bin";
		File file=new File(path);
        InputStream   is   =   new   FileInputStream(file); 
        long   length   =   file.length(); 
        if   (length   >   Integer.MAX_VALUE)   { 
            throw   new   IOException("File is too large"+path+file.getName()); 
        } 
        byte[]   bytes   =   new   byte[(int)length]; 
        //   Read   in   the   bytes 
        int   offset   =   0; 
        int   numRead   =   0; 
        while   (offset   <   bytes.length 
                      &&   (numRead=is.read(bytes,   offset,   bytes.length-offset)) >= 0)   { 
                offset   +=   numRead; 
        } 
        //   Ensure   all   the   bytes   have   been   read   in 
        if   (offset   <   bytes.length)   { 
			PosManagerLog.logMsg( "Could not completely read file"+path+file.getName(),PosManagerLog.LOG_FILE_PLATFORMFILE);
             throw   new   IOException("Could not completely read file"+path+file.getName()); 
        } 
        is.close(); 
        return   bytes; 
		}catch(Exception ex){
            throw   new   FieldException(ExceptionCode.B_FILE_READ_NOCOMPLEATE); 
		}
        
	} 
	/**过滤非法的版本
	 * @param confList
	 * @return
	 */
	public Map<String,MerchantAppConfig> filterInvalidVersion(List<MerchantAppConfig> confList){
		Map<String,MerchantAppConfig> configMap=new HashMap<String,MerchantAppConfig>();
		if(confList==null||confList.isEmpty()){return null;}
		for(MerchantAppConfig eac:confList){
			Set<PosAppVersion> versionSet=eac.getPosApp().getVersionSet();
			if(versionSet.isEmpty())continue;
			PosAppVersion defaultPav=null;
			for(PosAppVersion pav:versionSet){
				if(pav.getStatus()==PosAppVersion.STATUS_VALID){
					//生效的
					if(defaultPav==null){
						defaultPav=pav;
					}else{
						BigDecimal defaultVersion=	new BigDecimal(defaultPav.getVersion());
						BigDecimal version=	new BigDecimal(pav.getVersion());
						switch(defaultVersion.compareTo(version)){
						case -1://小于
							defaultPav=pav;
							break;
						case 0://等于
							break;
						case 1://大于
							break;
						}
					}
				}
			}
			versionSet.clear();
			if(defaultPav!=null){
				versionSet.add(defaultPav);
			}
			eac.getPosApp().setVersionSet(versionSet);
			configMap.put(eac.getPosApp().getCode().trim(), eac);
		}
		return configMap;
	}
	
	public PosAppVersion  getPosVersionFromReqDto(PosManagerDto reqDto) throws FieldException{
		PosAppDownloadInfoField downloadInfo= (PosAppDownloadInfoField) reqDto.getFields().get(by=0x05);

		PosApp posApp=posAppManager.findPosAppByCode(downloadInfo.getAppCode().trim());
		if(posApp==null)throw new FieldException(ExceptionCode.B_APP_INVALID);
		
		PosAppVersion posAppVersion=posAppVersionManager.getValidVersionsByAppcode(downloadInfo.getVersion().trim(), posApp);
		if(posAppVersion==null)throw new FieldException(ExceptionCode.B_APPVERSION_INVALID);
		
		return posAppVersion;
	}
	
	protected byte[] genarateExtraData(byte[] fileData,PosAppDownloadInfoField respDownload){
		if(respDownload.getOffset()+PosAppDownloadInfoField.EXDATA_MAX_LENT>=fileData.length){
			return ArrayUtils.subarray(fileData, respDownload.getOffset(), fileData.length);
		}else{
			return ArrayUtils.subarray(fileData, respDownload.getOffset(),respDownload.getOffset()+PosAppDownloadInfoField.EXDATA_MAX_LENT);
		}
	}
	
	/**生成返回 的 5域 
	 * @param reqDto
	 * @return
	 * @throws FieldException
	 */
	protected PosAppDownloadInfoField genarateRespVersion(PosManagerDto reqDto,byte[] fileData){
		PosAppDownloadInfoField respdowloadInfo=new PosAppDownloadInfoField(by=(byte) 0x05, false);
		PosAppDownloadInfoField downloadInfo= (PosAppDownloadInfoField) reqDto.getFields().get(by=0x05);

		respdowloadInfo.setLocation(downloadInfo.getLocation());
		respdowloadInfo.setAppCode(downloadInfo.getAppCode());
		respdowloadInfo.setVersion(downloadInfo.getVersion());
		respdowloadInfo.setHasExtraData((fileData==null||fileData.length==0)?false:true);
			//任务 大小 
		 respdowloadInfo.setSize(fileData.length);
		 respdowloadInfo.setOffset(downloadInfo.getOffset());
		 respdowloadInfo.setDone(downloadInfo.getOffset()+PosAppDownloadInfoField.EXDATA_MAX_LENT>=fileData.length?true:false);
	
		return respdowloadInfo;
	}
	
	public PosAppInfosField genarateRespVsersionList(Map<String,MerchantAppConfig> validVersionConfig,PosManagerDto req) throws FieldException{
		PosAppInfosField versionInfo= (PosAppInfosField) req.getFields().get(by=0x04);
		PosAppInfosField respInfo=new PosAppInfosField(by=(byte) 0x04, false);
	
				for(JSONObject json : versionInfo.getInfos()){
					if(validVersionConfig!=null){
					//生效应用版本列表是否存在
						if(validVersionConfig.containsKey(json.getString(PosAppInfosField.APPCODE))){
							//应用有效
							MerchantAppConfig mac=validVersionConfig.get(json.getString(PosAppInfosField.APPCODE));
							if(mac==null||mac.getPosApp()==null||mac.getPosApp().getVersionSet().size()==0){
								respInfo.addInfo(json.getInt(PosAppInfosField.LOCATION), json.getString(PosAppInfosField.APPCODE), json.getString(PosAppInfosField.VERSION), PosAppInfosField.FLAG_STOP);
							}
							for(PosAppVersion posAppVersion: mac.getPosApp().getVersionSet()){
								if(posAppVersion.getVersion().trim().equals(json.getString(PosAppInfosField.VERSION))){
									//版本号一样
									respInfo.addInfo(mac.getPosApp().getLocation(), mac.getPosApp().getCode(), posAppVersion.getVersion(), PosAppInfosField.FLAG_NO);
								}else{
									//版本号不一致
									PosApp posApp=posAppManager.findPosAppByCode(json.getString(PosAppInfosField.APPCODE));
									if(posApp!=null){
										PosAppVersion reqPosAppVersion=posAppVersionManager.getValidVersionsByAppcodeNostate(json.getString(PosAppInfosField.VERSION), posApp);
										if(reqPosAppVersion==null)throw new FieldException(ExceptionCode.B_CURRENT_APPVERSION_INVALID);
										//自身有效且有高版本
										if(reqPosAppVersion.isValid()&&compareVersion(posAppVersion,reqPosAppVersion)){
											respInfo.addInfo(mac.getPosApp().getLocation(), mac.getPosApp().getCode(), posAppVersion.getVersion(), PosAppInfosField.FLAG_CHOOSE);
										}
										if(!reqPosAppVersion.isValid()&&!compareVersion(posAppVersion,reqPosAppVersion)){
											respInfo.addInfo(mac.getPosApp().getLocation(), mac.getPosApp().getCode(), posAppVersion.getVersion(), PosAppInfosField.FLAG_STOP);
										}
										if(!reqPosAppVersion.isValid()&&compareVersion(posAppVersion,reqPosAppVersion)){
											respInfo.addInfo(mac.getPosApp().getLocation(), mac.getPosApp().getCode(), posAppVersion.getVersion(), PosAppInfosField.FLAG_YES);
										}
									}
								}
							}
							validVersionConfig.remove(json.getString(PosAppInfosField.APPCODE));
						}else{
		
						//不存在可用版本停止使用,加入C。该应用 没有有效的版本
						respInfo.addInfo(json.getInt(PosAppInfosField.LOCATION), json.getString(PosAppInfosField.APPCODE), json.getString(PosAppInfosField.VERSION), PosAppInfosField.FLAG_STOP);
						}
					}else{
						respInfo.addInfo(json.getInt(PosAppInfosField.LOCATION), json.getString(PosAppInfosField.APPCODE), json.getString(PosAppInfosField.VERSION), PosAppInfosField.FLAG_STOP);

					}
				}
		
		if(validVersionConfig!=null&&!validVersionConfig.isEmpty()){
			for(MerchantAppConfig mac:validVersionConfig.values()){
				for(PosAppVersion posAppVersion: mac.getPosApp().getVersionSet()){
					respInfo.addInfo(mac.getPosApp().getLocation(), mac.getPosApp().getCode(), posAppVersion.getVersion(), PosAppInfosField.FLAG_YES);
				}
			}
		}
		return respInfo;
	}
	
	public boolean compareVersion(PosAppVersion newv,PosAppVersion oldv){
		BigDecimal newb=new BigDecimal(newv.getVersion().trim());
		BigDecimal oldb=new BigDecimal(oldv.getVersion().trim());
		switch(newb.compareTo(oldb)){
		case 1:
			return true;
		default:
			return false;
		}
		 
	}
	

	
	

	protected PosAppVersionRecord getAppVersionRecordManager(PosManagerDto message) throws FieldException{
		
		PosAppVersionRecord pavr=new PosAppVersionRecord();
		//终端序列号
		StringField posSerial= (StringField)message.getFields().get(by=0x01);
		//调用接口查询数据
		
			Map<String, String> merchant = posAppVersionRecordManager.getMerchantInfo(posSerial.getStr());
			if(merchant==null||merchant.isEmpty())throw new FieldException(ExceptionCode.B_POS_SERIAL_RONG);
			pavr.setPosCode(merchant.get("posCode"));
			pavr.setMerchantCode(merchant.get("merchantCode"));
			pavr.setMerchantName(merchant.get("merchantName"));
		
		
		//取出第四域
		PosAppInfosField appInfoField=(PosAppInfosField) message.getFields().get(by=0x04);
		pavr.setPosSerialno(posSerial.getStr());
		
		List<JSONObject> josonList=appInfoField.getInfos();
		if(josonList!=null&&josonList.size()!=0){
			pavr.setPosAppversionInfo(josonList.toString());
		}
		return pavr;
	}
	
}
