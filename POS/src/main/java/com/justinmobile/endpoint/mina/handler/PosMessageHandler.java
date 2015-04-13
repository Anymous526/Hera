package com.justinmobile.endpoint.mina.handler;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;

public interface PosMessageHandler {

	

	public Hs8583Dto process(Hs8583Dto message);
}
