package com.justinmobile.endpoint.mina.service.impl.a1;



import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.service.impl.AbstractPosProcesserA1;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;

@Service("posProcesserA1V1_1")
public class PosProcesserA1V1_1Impl extends AbstractPosProcesserA1{

	private static final Log LOG = LogFactory.getLog(PosProcesserA1V1_1Impl.class);
	
	
	@Override
	public Hs8583Dto posSign(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.POS_SIGN_RESPONSE);
		try {
			Pos pos = posManager.getByPosSNSerial(request.getBm18PosSerialNo());
			if (pos == null) {
				throw new PosBussinessException(PosReturnCode.POSSIGN_INVALID);
			}
			Merchant merchant = pos.getMerchant();
			checkValid(merchant, pos);
			checkRelated(merchant, pos);

			tradeBatchManager.createBatch(pos, pos.getBatchNumber());

			response.setBm4MerchantNo(merchant.getCode());
			response.setBm5TerminalNo(pos.getCode());
			response.setBm13MerchantName(merchant.getShortName());
			response.setBm19PosBussList(String.valueOf(Long.toHexString(pos.getMerchant().getPosBusInessType())));
			response.setBm41DiscountFlag(pos.getMerchant().isDiscountFlag()?"1":"0");

			response.setTradeDate(new Date());
			response.setBm30BatchNo(String.valueOf(pos.getBatchNumber()));
			returnSuccess(response);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

}
