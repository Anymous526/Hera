package com.justinmobile.bmp.pos.mina.branch;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.justinmobile.bmp.pos.mina.dto.PosManagerDto;
import com.justinmobile.bmp.pos.mina.dto.field.Field;
import com.justinmobile.bmp.pos.mina.dto.field.NumberToStriField;
import com.justinmobile.bmp.pos.mina.handler.AbstractMessageHandler;
import com.justinmobile.bmp.pos.mina.request.PosRequestCode;
import com.justinmobile.bmp.pos.mina.request.PosRequestCodeAnnotation;
import com.justinmobile.bmp.pos.mina.service.PosAppVersionPorcesser;
@Service("posManagerBrancher")
public class PosManagerBrancherImpl  extends AbstractMessageHandler implements PosManagerBrancher{

	@Autowired
	private PosAppVersionPorcesser posAppVersionPorcesser;
	
	@Override
	public PosManagerDto process(PosManagerDto message) {
		Map<Byte,Field> map=message.getFields();
		Byte bb=0x00;
		String code=((NumberToStriField)map.get(bb)).getStr();
		PosRequestCode  requestCode=PosRequestCode.get(code);
		Method method = this.messageMethodMap.get(requestCode);
		try {
			return (PosManagerDto) method.invoke(this, message);
		} catch (Exception e) {
			e.printStackTrace();
			return message;
		} 
	}

	@PosRequestCodeAnnotation(PosRequestCode.APP_VERSION_RESP)
	public PosManagerDto appVsersionResponse(PosManagerDto message){
		return posAppVersionPorcesser.appVsersionResponse(message);
	}
	
	
	@PosRequestCodeAnnotation(PosRequestCode.APP_VERSION_DOWNLOAD)
	public PosManagerDto appVsersionDownload(PosManagerDto message){
		return	posAppVersionPorcesser.appVsersionDownload(message);
		
	}
}
