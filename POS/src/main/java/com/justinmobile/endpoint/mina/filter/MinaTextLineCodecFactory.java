package com.justinmobile.endpoint.mina.filter;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

public class MinaTextLineCodecFactory extends TextLineCodecFactory {

	public MinaTextLineCodecFactory(String charsetName) {
		super(Charset.forName(charsetName));
	}
	 
}