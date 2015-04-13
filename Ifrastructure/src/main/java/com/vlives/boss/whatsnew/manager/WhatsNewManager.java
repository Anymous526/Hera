package com.vlives.boss.whatsnew.manager;

import java.util.List;

import com.vlives.boss.whatsnew.domain.WhatsNew;

public interface WhatsNewManager {

	public List<WhatsNew> getWhatsNew(long cityId);
}
