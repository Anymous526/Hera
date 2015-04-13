package com.justinmobile.bmp.pos.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.proxy.utils.ByteUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import com.justinmobile.bmp.pos.mina.branch.PosManagerBrancher;
import com.justinmobile.bmp.pos.mina.dto.PosManagerDto;
import com.justinmobile.bmp.pos.mina.log.PosManagerLog;

public class PosManagerHandlerAdapter extends IoHandlerAdapter {

	@Autowired
	private PosManagerBrancher posManagerBrancher;
	
	private HibernateLazyResolver hibernateLazyResolver;
	
	
	public void setHibernateLazyResolver(HibernateLazyResolver hibernateLazyResolver) {
		this.hibernateLazyResolver = hibernateLazyResolver;
	}



	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		PosManagerDto dtoResp=null;
		try {
			hibernateLazyResolver.openSession();
			PosManagerDto req =  (PosManagerDto) message;
			
			 dtoResp =posManagerBrancher.process(req);
			
			PosManagerLog.logMsg(dtoResp.getFields().toString(),PosManagerLog.LOG_FILE_PLATFORMFILE);
			System.out.println(req.getFields());
			System.out.println(dtoResp.getFields());
			Object respMess=dtoResp.encode();
			
			 PosManagerLog.logMsg(ByteUtilities.asHex((byte[]) respMess), PosManagerLog.LOG_FILE_PLATFORMFILE);

			if(respMess != null) session.write(respMess);

		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			hibernateLazyResolver.releaseSession(dtoResp.isDong());
		}
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
	//	session.close(true);
	}
	
	public void afterPropertiesSet() throws Exception {
		PosManagerLog.logMsg("mina is start..........",PosManagerLog.LOG_FILE_PLATFORMFILE);
	}


}
