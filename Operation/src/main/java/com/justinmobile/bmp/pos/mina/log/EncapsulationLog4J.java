package com.justinmobile.bmp.pos.mina.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;

public class EncapsulationLog4J {
	public static final String PROFILE = "/log4j.properties";
	private static EncapsulationLog4J impl = new EncapsulationLog4J();

	private Logger log4j;

	private Logger log4j_error;;

	private Logger log4_to_posp;

	private Logger log4_platform_receive;

	private Logger log4_busi;

	private EncapsulationLog4J() {
		log4j = LogManager.getLogger(EncapsulationLog4J.class);

		log4j_error = LogManager.getLogger("errorinfologfile");
		log4_to_posp = LogManager.getLogger("toposplogfile");
		log4_platform_receive = LogManager
				.getLogger("platformreceivelogfile");
		log4_busi = LogManager.getLogger("busilogfile");

		try {
			Properties pro = new Properties();
			InputStream is = getClass().getResourceAsStream(PROFILE);
			pro.load(is);
			PropertyConfigurator.configure(pro);
		} catch (IOException e) {
			BasicConfigurator.configure();
			e.printStackTrace();
		}
	}

	private Logger getLog(int type) {
		Logger log = null;
		switch (type) {
		case 1:
			log = log4j_error;
			break;
		case 2:
			log = log4_to_posp;
			break;
		case 3:
			log = log4_platform_receive;
			break;
		case 4:
			log = log4_busi;
			break;
		default:
			log = log4j;
		}
		return log;

	}

	public void log(String level, Object msg) {
		log(level, msg, null);
	}

	public void log(String level, Throwable e) {
		log(level, null, e);
	}

	public void log(String level, Object msg, java.lang.Throwable e) {
		if (log4j != null) {
			log4j.log((Priority) Level.toLevel(level), msg, e);
		}
	}

	public static EncapsulationLog4J getInstance() {
		if(impl==null){
			impl= new EncapsulationLog4J();
		}
		return impl;
	}

	//new
	public void log(String level, Object msg, int logfile) {
		log(level, msg, null, logfile);
	}

	public void log(String level, Throwable e, int logfile) {
		log(level, null, e, logfile);
	}

	public void log(String level, Object msg, java.lang.Throwable e, int logfile) {
		Logger log = getLog(logfile);
		if (log != null) {
			log.log((Priority) Level.toLevel(level), msg, e);
		}
	}

}
