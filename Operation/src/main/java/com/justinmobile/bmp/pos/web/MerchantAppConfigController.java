package com.justinmobile.bmp.pos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.justinmobile.bmp.pos.manager.MerchantAppConfigManager;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("merchantAppConfigController")
public class MerchantAppConfigController extends BaseAjaxController{

	@SuppressWarnings("unused")
	@Autowired
	private MerchantAppConfigManager merchantAppConfigManager;
}