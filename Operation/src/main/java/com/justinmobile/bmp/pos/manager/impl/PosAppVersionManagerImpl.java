package com.justinmobile.bmp.pos.manager.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.dao.PosAppVersionDao;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.bmp.pos.manager.PosAppVersionManager;
import com.justinmobile.bmp.util.SpringSecurityUtils;
import com.justinmobile.core.manager.EntityPageManagerImpl;

@Service("posAppVersionManager")
public class PosAppVersionManagerImpl extends EntityPageManagerImpl<PosAppVersion, PosAppVersionDao> implements PosAppVersionManager {

	@Autowired
	private PosAppVersionDao posAppVersionDao;

	@Override
	public List<PosAppVersion> getValidVersions() throws PlatformException {
		try {
			return this.posAppVersionDao.getValidVersions();
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.QUERY_ERROR,e);
		}
	}

	public PosAppVersion getValidVersionsByAppcode(String version,PosApp posApp){
		String hql="from PosAppVersion version where version.version=? and version.posApp=? and version.status=?";
		
		return posAppVersionDao.findUniqueEntity(hql, version,posApp,PosAppVersion.STATUS_VALID);
	}
	public PosAppVersion getValidVersionsByAppcodeNostate(String version,PosApp posApp){
		String hql="from PosAppVersion version where version.version=? and version.posApp=?";
		
		return posAppVersionDao.findUniqueEntity(hql, version,posApp);
	}
	
	@Override
	public void createVersion(PosAppVersion version) throws PlatformException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyVersion(PosAppVersion version) throws PlatformException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void auditVersion(PosAppVersion version, boolean pass) throws PlatformException {
		this.checkCanAudit(version);
		if(version.getStatus() == PosAppVersion.STATUS_VALID_AUD){
			if(pass){
				version.setStatus(PosAppVersion.STATUS_VALID);
				version.setAuditInfo("");
			}else{
				version.setStatus(PosAppVersion.STATUS_INITIAL);
			}
			version.setAuditValidOper(SpringSecurityUtils.getCurrentUserName());
			version.setAuditValidTime(Calendar.getInstance());
		}else if(version.getStatus() == PosAppVersion.STATUS_DISABLED_AUD){
			if(pass){
				version.setStatus(PosAppVersion.STATUS_DISABLED);
				version.setAuditInfo("");
			}else{
				version.setStatus(PosAppVersion.STATUS_VALID);
			}
			version.setAuditStopOper(SpringSecurityUtils.getCurrentUserName());
			version.setAuditStopTime(Calendar.getInstance());
		}else if(version.getStatus() == PosAppVersion.STATUS_INVALID_AUD){
			if(pass){
				version.setStatus(PosAppVersion.STATUS_INVALID);
				version.setAuditInfo("");
			}else{
				version.setStatus(PosAppVersion.STATUS_DISABLED);
			}
			version.setAuditInvalidOper(SpringSecurityUtils.getCurrentUserName());
			version.setAuditInvalidTime(Calendar.getInstance());
		}else{
			throw new PlatformException(PlatformErrorCode.UNKNOWN_ERROR);
		}
		this.posAppVersionDao.save(version);
	}

	private void checkCanAudit(PosAppVersion version) {
		if(!version.canAudit()){
			throw new PlatformException(PlatformErrorCode.POSAPP_STATUS_ERROR);
		}
	}

	@Override
	public void commit(PosAppVersion version) throws PlatformException {
		this.checkCanCommit(version);
		version.setStatus(PosAppVersion.STATUS_VALID_AUD);
		version.setValidOper(SpringSecurityUtils.getCurrentUserName());
		version.setValidTime(Calendar.getInstance());
		this.posAppVersionDao.save(version);
	}

	private void checkCanCommit(PosAppVersion version) {
		if(!version.canComit()){
			throw new PlatformException(PlatformErrorCode.POSAPP_STATUS_ERROR);
		}
		
	}

	@Override
	public void disable(PosAppVersion version) throws PlatformException {
		this.checkCanDisable(version);
		version.setStatus(PosAppVersion.STATUS_DISABLED_AUD);
		version.setStopOper(SpringSecurityUtils.getCurrentUserName());
		version.setStopTime(Calendar.getInstance());
		this.posAppVersionDao.save(version);
		
	}

	private void checkCanDisable(PosAppVersion version) {
		if(!version.canDisable()){
			throw new PlatformException(PlatformErrorCode.POSAPP_STATUS_ERROR);
		}
		
	}

	@Override
	public void setInvalid(PosAppVersion version) throws PlatformException {
		this.checkCanSetInvalid(version);
		version.setStatus(PosAppVersion.STATUS_INVALID_AUD);
		version.setInvalidOper(SpringSecurityUtils.getCurrentUserName());
		version.setInvalidTime(Calendar.getInstance());
		this.posAppVersionDao.save(version);
		
	}

	private void checkCanSetInvalid(PosAppVersion version) {
		if(!version.canSetInvalid()){
			throw new PlatformException(PlatformErrorCode.POSAPP_STATUS_ERROR);
		}
	}

	@Override
	public List<PosAppVersion> getValidVersionsByApp(PosApp app) throws PlatformException {
		String hql = "from PosAppVersion version where version.posApp = ? and version.status = ?";
		return this.posAppVersionDao.find(hql, app,PosAppVersion.STATUS_VALID);
	}

	@Override
	public List<PosAppVersion> getValidVersionsByLocation(int location) throws PlatformException {
		String hql = "from PosAppVersion version where version.posApp.location = ? and version.status = ?";
		return this.posAppVersionDao.find(hql, location,PosAppVersion.STATUS_VALID);
	}
	
}