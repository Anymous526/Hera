package com.justinmobile.bmp.pos.manager;

import java.util.List;

import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.core.manager.EntityPageManager;

public interface PosAppVersionManager extends EntityPageManager<PosAppVersion>{
	
	
	/**
	 * 查询所有有效应用版本
	 * @return 所有有效的应用版本
	 * @throws PlatformException
	 */
	public List<PosAppVersion> getValidVersions() throws PlatformException;
	
	/**
	 * 查询指定应用下所有有效的应用版本
	 * @throws PlatformException
	 */
	public List<PosAppVersion> getValidVersionsByApp(PosApp app) throws PlatformException;
	
	/**
	 * 查询指定应用下所有有效的应用版本
	 * @throws PlatformException
	 */
	public List<PosAppVersion> getValidVersionsByLocation(int location) throws PlatformException;
	
	/**
	 * 根据应用编码查询有效的应用版本。
	 * @return 所有有效的应用版本
	 * @throws PlatformException
	 */
	public PosAppVersion getValidVersionsByAppcode(String version,PosApp posApp);
	/**
	 * 根据应用编码查询有效的应用版本。
	 * @return 所有有效的应用版本
	 * @throws PlatformException
	 */
	public PosAppVersion getValidVersionsByAppcodeNostate(String version,PosApp posApp);
	/**
	 * 创建应用版本
	 * @throws PlatformException
	 */
	public void createVersion(PosAppVersion version) throws PlatformException;
	
	/**
	 * 修改应用版本
	 * @throws PlatformException
	 */
	public void modifyVersion(PosAppVersion version) throws PlatformException;
	
	/**
	 * 审核应用版本
	 * @param version
	 * @param passFalg true为审核通过，false为审核不通过
	 * @throws PlatformException
	 */
	public void auditVersion(PosAppVersion version , boolean pass) throws PlatformException;
	
	/**
	 * 应用版本提交审核
	 * @param version
	 * @throws PlatformException
	 */
	public void commit(PosAppVersion version) throws PlatformException;
	
	/**
	 * 应用版本停用
	 * @param version
	 * @throws PlatformException
	 */
	public void disable(PosAppVersion version) throws PlatformException;
	
	/**
	 * 应用版本废除
	 * @param version
	 * @throws PlatformException
	 */
	public void setInvalid(PosAppVersion version) throws PlatformException;
	
}