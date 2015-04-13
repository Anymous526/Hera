package com.justinmobile.bmp.pos.mina.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.bmp.pos.domain.PosAppVersionRecord;
import com.justinmobile.bmp.pos.manager.MerchantAppConfigManager;
import com.justinmobile.bmp.pos.manager.PosAppVersionRecordManager;
import com.justinmobile.bmp.pos.mina.dto.PosManagerDto;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException;
import com.justinmobile.bmp.pos.mina.dto.field.NumberToStriField;
import com.justinmobile.bmp.pos.mina.dto.field.PosAppDownloadInfoField;
import com.justinmobile.bmp.pos.mina.dto.field.StringField;
import com.justinmobile.bmp.pos.mina.dto.field.FieldException.ExceptionCode;
import com.justinmobile.core.exception.BusinessException;


@Service("posAppVersionPorcesser")
public class PosAppVersionPorcesser extends AbstractPosAppProcesser{

	@Autowired
	private PosAppVersionRecordManager posAppVersionRecordManager;
	@Autowired
	private MerchantAppConfigManager merchantAppConfigManager;

	/**应用版本回响
	 * @param message
	 * @return
	 */
	public PosManagerDto appVsersionResponse(PosManagerDto reqDto){
		PosManagerDto respDto=new PosManagerDto();
			try {
				respDto.addField(reqDto.getFields().get(by=0x00));
				respDto.addField(reqDto.getFields().get(by=0x01));
				//记录回响报文
				PosAppVersionRecord pavr=getAppVersionRecordManager(reqDto);
				posAppVersionRecordManager.save(pavr);
				//商户的应用列表
				List<MerchantAppConfig> confList=merchantAppConfigManager.getAppConfigByMerno(pavr.getMerchantCode());
				//可用应用版本信息列表。过滤后每个应用只对应一个版本
				 Map<String,MerchantAppConfig> validVersionConfig= filterInvalidVersion(confList);
				 
				 respDto.addField(genarateRespVsersionList(validVersionConfig,reqDto));
				 respDto.addField(new NumberToStriField(by=(byte) 0xfe, false,FieldException.SUCESS_RESP));
				 respDto.addField(new StringField(by=(byte) 0xff, false,ExceptionCode.B_SUCESS_RESP.getLogMsg()));

			} catch (FieldException e) {
				e.printStackTrace();
				respDto.addField(new NumberToStriField(by=(byte) 0xfe, false,e.getExceptionCode()));
				respDto.addField(new StringField(by=(byte) 0xff, false,e.getExceptionMsg()));
			}
		return respDto;
	}
	
	
	
	/**应用版本下载
	 * @param message
	 * @return
	 */
	public PosManagerDto appVsersionDownload(PosManagerDto reqDto){
		PosManagerDto respDto=new PosManagerDto();
		try {
			respDto.addField(reqDto.getFields().get(by=0x00));
			respDto.addField(reqDto.getFields().get(by=0x01));
			//终端序列号
			StringField posSerial= (StringField)reqDto.getFields().get(by=0x01);
			Map<String, String> merchant = posAppVersionRecordManager.getMerchantInfo(posSerial.getStr());
			if(merchant==null||merchant.isEmpty())throw new FieldException(ExceptionCode.B_POS_SERIAL_RONG);
			PosAppVersion posAppVersion=getPosVersionFromReqDto(reqDto);
			byte[] fileData=readVersionFile(posAppVersion.getFilePath());
			PosAppDownloadInfoField respDownload=genarateRespVersion(reqDto,fileData);
			
			
			respDto.addField(respDownload );
			if(fileData!=null&&fileData.length!=0){
				byte[] extraData=genarateExtraData(fileData,respDownload);
				respDto.setExtraData(extraData);
				if(respDownload.getOffset()+extraData.length<respDownload.getLength()){
					respDto.setDong(false);
				}
			}
			
			respDto.addField(new NumberToStriField(by=(byte) 0xfe, false,ExceptionCode.B_SUCESS_RESP.getCode()));
			respDto.addField(new StringField(by=(byte) 0xff, false,ExceptionCode.B_SUCESS_RESP.getLogMsg()));

		}catch(FieldException ex){
			respDto.addField(new NumberToStriField(by=(byte) 0xfe, false,ex.getExceptionCode()));
			respDto.addField(new StringField(by=(byte) 0xff, false,ex.getExceptionMsg()));
		}
		return respDto;
	}
	
}
