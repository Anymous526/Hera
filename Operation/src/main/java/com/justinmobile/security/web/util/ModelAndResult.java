package com.justinmobile.security.web.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndResult extends ModelAndView {
	
	private Boolean success;
	
	private String message;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
