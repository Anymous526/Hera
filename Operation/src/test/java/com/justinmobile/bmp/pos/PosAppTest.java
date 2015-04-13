package com.justinmobile.bmp.pos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.bmp.pos.domain.PosAppVersionRecord;
import com.justinmobile.bmp.pos.manager.MerchantAppConfigManager;
import com.justinmobile.bmp.pos.manager.PosAppManager;
import com.justinmobile.bmp.pos.manager.PosAppVersionManager;
import com.justinmobile.bmp.pos.manager.PosAppVersionRecordManager;
import com.justinmobile.core.test.BaseAbstractTest;

public class PosAppTest extends BaseAbstractTest{

	@Autowired
	private PosAppManager posAppManager;
	
	@Autowired
	private PosAppVersionManager posAppVersionManager;
	
	@Autowired
	private MerchantAppConfigManager merchantAppConfigManager;
	
	@Autowired
	private PosAppVersionRecordManager posAppVersionRecordManager;
	
	@Test
	public void testPosApp(){
		PosApp posApp = new PosApp();
		setSimpleProperties(posApp);
		for(int i=0; i<10; i++){
			PosAppVersion posAppVersion = new PosAppVersion();
			setSimpleProperties(posAppVersion);
			posAppVersion.setVersion("version"+i);
			posAppVersion.setPosApp(posApp);
			posApp.getVersionSet().add(posAppVersion);
			MerchantAppConfig merchantAppConfig = new MerchantAppConfig();
			setSimpleProperties(merchantAppConfig);
			merchantAppConfig.setPosApp(posApp);
			posApp.getCofigSet().add(merchantAppConfig);
		}
		this.posAppManager.save(posApp);
		
	}
	
	@Test
	public void testPosAppRecord(){
		PosAppVersionRecord posAppVersionRecord = new PosAppVersionRecord();
		setSimpleProperties(posAppVersionRecord);
		this.posAppVersionRecordManager.save(posAppVersionRecord);
	}
	
	@Test
	public void posAppVersionManager(){
		try {
			for(PosAppVersion version : this.posAppVersionManager.getValidVersions()){
				System.out.println("应用编号："+version.getPosApp().getCode());
				System.out.println("版本号："+version.getVersion());
			}
		} catch (PlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
