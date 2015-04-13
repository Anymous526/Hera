package com.justinmobile.endpoint.mina.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.InitializingBean;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.util.ByteUtil;
import com.vlives.core.support.hibernate.HibernateLazyResolver;

public class MtRequestHandler extends IoHandlerAdapter implements InitializingBean {
	private static final Log LOG = LogFactory.getLog(MtRequestHandler.class);
	private PosMessageHandler posMessageHandler;
	private HibernateLazyResolver hibernateLazyResolverSupport;

	 
	public void setHibernateLazyResolverSupport(
			HibernateLazyResolver hibernateLazyResolverSupport) {
		this.hibernateLazyResolverSupport = hibernateLazyResolverSupport;
	}

	public PosMessageHandler getPosMessageHandler() {
		return posMessageHandler;
	}

	public void setPosMessageHandler(PosMessageHandler posMessageHandler) {
		this.posMessageHandler = posMessageHandler;
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		try {
			hibernateLazyResolverSupport.openSession();
			Hs8583Dto req = (Hs8583Dto) message;

			Hs8583Dto resp = posMessageHandler.process(req);// dispatchAction.executeMtRequest(req);

			com.justinmobile.endpoint.mina.logs.Log.logMsg(resp.toString(),
					com.justinmobile.endpoint.mina.logs.Log.LOG_FILE_BUSIFILE);

			Object respMess = resp.encode(resp, req.getProtocol());

			com.justinmobile.endpoint.mina.logs.Log.logMsg(ByteUtil.toHexString((byte[]) respMess),
					com.justinmobile.endpoint.mina.logs.Log.LOG_FILE_PLATFORMFILE);

			if (resp != null)
				session.write(respMess);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//考虑session在同其它线程中会使用，所以暂时关闭，以观后效!
			hibernateLazyResolverSupport.releaseSession();
			
		}

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
		session.close(true);
	}

	public void afterPropertiesSet() throws Exception {
		LOG.info("mina is start..");
	}

}
