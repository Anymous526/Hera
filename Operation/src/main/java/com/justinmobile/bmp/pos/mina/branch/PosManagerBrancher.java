package com.justinmobile.bmp.pos.mina.branch;

import org.springframework.stereotype.Service;

import com.justinmobile.bmp.pos.mina.dto.PosManagerDto;


/**借口分发器
 * @author ThinkPad7
 *
 */

public interface PosManagerBrancher {
	
	public PosManagerDto process(PosManagerDto message);

}
