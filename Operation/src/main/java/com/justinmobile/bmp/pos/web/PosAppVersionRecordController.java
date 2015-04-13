package com.justinmobile.bmp.pos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.justinmobile.bmp.pos.manager.PosAppVersionRecordManager;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("posAppVersionRecordController")
public class PosAppVersionRecordController extends BaseAjaxController{

	@SuppressWarnings("unused")
	@Autowired
	private PosAppVersionRecordManager posAppVersionRecordManager;
}