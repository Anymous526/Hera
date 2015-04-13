package com.vlives.boss.whatsnew.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vlives.boss.whatsnew.dao.WhatsNewDao;
import com.vlives.boss.whatsnew.domain.WhatsNew;

@Service("whatsNewManager")
public class WhatsNewManagerImpl implements WhatsNewManager {
	@Autowired
	private WhatsNewDao whatsNewDao;

	@Override
	public List<WhatsNew> getWhatsNew(long cityId) {
		return whatsNewDao.getWhatsNew(cityId);
	}
}
