package com.justinmobile.bmp.pos.manager;

import java.util.List;

import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.core.manager.EntityPageManager;

public interface PosAppManager extends EntityPageManager<PosApp>{
	
	/**验证是否 有效
	 * @param appCode
	 * @return
	 */
	public boolean validPosApp(String appCode);
	
	/**返回 应用
	 * @param appCode
	 * @return
	 */
	public PosApp findPosAppByCode(String appCode);
	/**
	 * 应用编号查询POS应用
	 * @return
	 * @throws PlatformException
	 */
	public PosApp getAppByCode(String code) throws PlatformException;
	
	/**
	 * 查询所有有效POS应用
	 * @return
	 * @throws PlatformException
	 */
	public List<PosApp> getValidApps() throws PlatformException;
	
	/**
	 * 查询指定应用位置下所有有效POS应用
	 * @return
	 * @throws PlatformException
	 */
	public List<PosApp> getValidAppsByLocation(int location) throws PlatformException;
	
	/**
	 * 查询当前城市关联的有效应用
	 * @return
	 * @throws PlatformException
	 */
	public List<PosApp> getValidAppsByCity(City city) throws PlatformException;
	
	/**
	 * 查询当前应用关联的已开通城市
	 * @return
	 * @throws PlatformException
	 */
	public List<City> getCitiesByApp(PosApp app) throws PlatformException;
	
	/**
	 * 查询当前应用关联的商户
	 * @return
	 * @throws PlatformException
	 */
	public List<String> getMerchantsByApp(PosApp app) throws PlatformException;
	
	/**
	 * 查询当前商户关联的有效应用
	 * @return
	 * @throws PlatformException
	 */
	public List<PosApp> getValidAppsByMerchant(String merchantCode) throws PlatformException;

	/**
	 * 城市绑定应用
	 * @param city
	 * @param appIds
	 * @throws PlatformException
	 */
	public void citySetApps(City city, String[] appIds) throws PlatformException;

	/**
	 * 商户绑定应用
	 * @param merchantCode
	 * @param appIds
	 * @throws PlatformException
	 */
	public void merchantSetApps(String merchantCode, String[] appIds) throws PlatformException;
 
	/**
	 * 应用绑定城市
	 * @param app
	 * @param cityIds
	 * @throws PlatformException
	 */
	public void appSetCities(PosApp app, String[] cityIds) throws PlatformException;

	/**
	 * 应用绑定商户
	 * @param app
	 * @param merchantCodes
	 * @throws PlatformException
	 */
	public void appSetMerchants(PosApp app, String[] merchantCodes) throws PlatformException;

	/**
	 * 应用停用
	 * @param app
	 * @throws PlatformException
	 */
	public void disableApp(PosApp app) throws PlatformException;

	/**
	 * 应用审核
	 * @param app
	 * @param pass true:审核通过，false：审核不通过
	 * @throws PlatformException
	 */
	public void auditApp(PosApp app, boolean pass) throws PlatformException;

	
	
}