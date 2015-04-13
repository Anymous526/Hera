package com.justinmobile.bmp.pos.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.dao.CityDao;
import com.justinmobile.bmp.pos.dao.PosAppDao;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.bmp.pos.manager.MerchantAppConfigManager;
import com.justinmobile.bmp.pos.manager.PosAppManager;
import com.justinmobile.bmp.util.SpringSecurityUtils;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.manager.EntityPageManagerImpl;

@Service("posAppManager")
public class PosAppManagerImpl extends EntityPageManagerImpl<PosApp, PosAppDao> implements PosAppManager {

	@Autowired
	private PosAppDao posAppDao;
	
	
	@Autowired
	private CityDao cityDao;
	
	@Autowired
	private MerchantAppConfigManager merchantAppConfigManager;
	
	@Override
	public List<PosApp> getValidApps() throws PlatformException {
		try {
			return this.posAppDao.findByProperty("status", PosApp.STATUS_VALID);
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.QUERY_ERROR ,e);
		}
	}

	@Override
	public List<PosApp> getValidAppsByLocation(int location) throws PlatformException {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", PosApp.STATUS_VALID);
			map.put("location", location);
			return this.posAppDao.findBy(map);
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.QUERY_ERROR ,e);
		}
	}

	@Override
	public void citySetApps(City city, String[] appIds) throws PlatformException {
		try {
//			Set<PosApp> appSet = new HashSet<PosApp>();
			for(String appId : appIds){
				PosApp app = this.posAppDao.get(Long.valueOf(appId));
				this.checkStatus(app);
				city.getAppSet().add(app);
			}
			this.cityDao.save(city);
		} catch (NumberFormatException e) {
			throw new PlatformException(PlatformErrorCode.PARAM_ERROR , e);
		} catch (BusinessException e) {
			throw new PlatformException(PlatformErrorCode.DB_ERROR , e);
		}
		
	}

	@Override
	public void merchantSetApps(String merchantCode, String[] appIds) throws PlatformException {
		try {
			List<MerchantAppConfig> oldList = this.merchantAppConfigManager.getAllConfigByMerchantCode(merchantCode);
			for(MerchantAppConfig config : oldList){
				this.merchantAppConfigManager.remove(config);
			}
			for(String appId : appIds){
				PosApp app = this.posAppDao.get(Long.valueOf(appId));
				this.checkStatus(app);
				MerchantAppConfig merchantAppConfig = new MerchantAppConfig();
				merchantAppConfig.setConfigOper(SpringSecurityUtils.getCurrentUserName());
				merchantAppConfig.setConfigTime(Calendar.getInstance());
				merchantAppConfig.setMerchantCode(merchantCode);
				merchantAppConfig.setPosApp(app);
				this.merchantAppConfigManager.save(merchantAppConfig);
			}
		} catch (PlatformException e) {
			throw e;
		} catch (NumberFormatException e) {
			throw new PlatformException(PlatformErrorCode.PARAM_ERROR , e);
		} catch (BusinessException e) {
			throw new PlatformException(PlatformErrorCode.DB_ERROR , e);
		}
	}

	@Override
	public void appSetCities(PosApp app, String[] cityIds) throws PlatformException {
		try {
			this.checkStatus(app);
			for(String cityId : cityIds){
				City city = this.cityDao.get(Long.valueOf(cityId));
				app.getCitySet().add(city);
			}
			this.posAppDao.save(app);
		} catch (NumberFormatException e) {
			throw new PlatformException(PlatformErrorCode.PARAM_ERROR , e);
		} catch (BusinessException e) {
			throw new PlatformException(PlatformErrorCode.DB_ERROR , e);
		}
	}

	@Override
	public void appSetMerchants(PosApp app, String[] merchantCodes) throws PlatformException {
		try {
			this.checkStatus(app);
			outer:
			for(String merchantCode : merchantCodes){
				for(MerchantAppConfig config : app.getCofigSet()){
					if(merchantCode.equals(config.getMerchantCode())){
						continue outer;
					}
				}
				MerchantAppConfig merchantAppConfig = new MerchantAppConfig();
				merchantAppConfig.setConfigOper(SpringSecurityUtils.getCurrentUserName());
				merchantAppConfig.setConfigTime(Calendar.getInstance());
				merchantAppConfig.setMerchantCode(merchantCode);
				merchantAppConfig.setPosApp(app);
				this.merchantAppConfigManager.save(merchantAppConfig);
			}
		} catch (BusinessException e) {
			throw new PlatformException(PlatformErrorCode.DB_ERROR , e);
		}
		
	}

	@Override
	public void disableApp(PosApp app) throws PlatformException {
		this.checkCanDisable(app);
		app.setStatus(PosApp.STATUS_DISABLED_AUD);
		app.setStopOper(SpringSecurityUtils.getCurrentUserName());
		app.setStopTime(Calendar.getInstance());
		this.posAppDao.save(app);
	}

	private void checkCanDisable(PosApp app) {
		this.checkStatus(app);
		for(PosAppVersion version : app.getVersionSet()){
			if(!(version.isDisabled() || version.isInvalid() || version.isInvalidAud())){
				throw new PlatformException(PlatformErrorCode.POSAPPVERSION_NOT_DISABLED_ALL);
			}
		}
	}

	@Override
	public void auditApp(PosApp app, boolean pass) throws PlatformException {
		this.checkCanAudit(app);
		app.setAuditStopOper(SpringSecurityUtils.getCurrentUserName());
		app.setAuditStopTime(Calendar.getInstance());
		if(pass){
			app.setStatus(PosApp.STATUS_DISABLED);
			app.setAuditInfo("");
		}else{
			app.setStatus(PosApp.STATUS_VALID);
		}
		this.posAppDao.save(app);
	}
	
	private void checkCanAudit(PosApp app) {
		if(app.getStatus() != PosApp.STATUS_DISABLED_AUD){
			throw new PlatformException(PlatformErrorCode.POSAPP_STATUS_ERROR);
		}
	}

	private void checkStatus(PosApp app){
		if(!app.isValid()){
			throw new PlatformException(PlatformErrorCode.POSAPP_STATUS_ERROR);
		}
	}
	
	
	public boolean validPosApp(String appCode){
		String hql="from PosApp pa where pa.code=? and status=?";
		PosApp posApp=posAppDao.findUniqueEntity(hql, appCode,PosApp.STATUS_VALID);
		return posApp!=null;
	}

	
	public PosApp findPosAppByCode(String appCode){
		String hql="from PosApp pa where pa.code=? and status=?";
		return posAppDao.findUniqueEntity(hql, appCode,PosApp.STATUS_VALID);
	}

	@Override
	public List<PosApp> getValidAppsByCity(City city) throws PlatformException {
		List<PosApp> list = new ArrayList<PosApp>();
		for(PosApp app : city.getAppSet()){
			if(app.isValid()){
				list.add(app);
			}
		}
		return list;
	}

	@Override
	public List<City> getCitiesByApp(PosApp app) throws PlatformException {
		List<City> list = new ArrayList<City>();
		for(City city : app.getCitySet()){
			if(city.isOpen()){
				list.add(city);
			}
		}
		return list;
	}

	@Override
	public List<String> getMerchantsByApp(PosApp app) throws PlatformException {
		List<String> list = new ArrayList<String>();
		for(MerchantAppConfig config : app.getCofigSet()){
			list.add(config.getMerchantCode());
		}
		return list;
	}

	@Override
	public List<PosApp> getValidAppsByMerchant(String merchantCode) throws PlatformException {
		List<PosApp> appList = new ArrayList<PosApp>();
		List<MerchantAppConfig> list = this.merchantAppConfigManager.getAppConfigByMerno(merchantCode);
		for(MerchantAppConfig config : list){
			appList.add(config.getPosApp());
		}
		return appList;
	}

	@Override
	public PosApp getAppByCode(String code) throws PlatformException {
		try {
			String hql = "from PosApp app where app.code = ?" ;
			return this.posAppDao.findUniqueEntity(hql, code);
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.QUERY_ERROR ,e);
		}
	}
}