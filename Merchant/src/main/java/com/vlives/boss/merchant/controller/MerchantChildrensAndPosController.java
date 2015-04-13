package com.vlives.boss.merchant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.dto.MerchantDto;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.pos.manager.PosManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;


/**
 *	描述：门店查询、pos机查询
 */
@Controller
@RequestMapping("/manager")
public class MerchantChildrensAndPosController {

	@Resource
	private MemberManager memberManager;
	
	@Resource(name="posManager")
	private PosManager posManager;
	List<Merchant> merchantList=null;
	List<Pos> posList=null;
	
	/**
	 * 获取商户门店
	 * create by datao.wang 2011-6-9
	 *@param operator
	 *@param request
	 *@param response
	 *@return
	 */
	@RequestMapping("/child/list.htm")
	public ModelAndView findChildens(@ObjectConvertAnno Operator operator){
		Merchant merchant = operator.getMerchant();
		List<Merchant> merchants = new ArrayList<Merchant>();
		merchants.add(merchant);
		if(merchant.isExistChildren()) {
			Set<Merchant> merchantSet = merchant.getChildrens();
			merchants.addAll(merchantSet);
		}
		List<MerchantDto> merchantDtos = createMemberCount(merchants);
		//传递参数
		Map<String ,Object> params=new HashMap<String, Object>();
		params.put("merchantDtos", merchantDtos);
		return new ModelAndView("/manager/stores/list.jsp",params);	
	}
	
	/**
	 * 获取商户pos机
	 * create by datao.wang 2011-6-9
	 *@param operator
	 *@param request
	 *@param response
	 *@return
	 */
	@RequestMapping("/internal/pos/list.htm")
	public ModelAndView findPoses(@ObjectConvertAnno Operator operator,@ObjectConvertAnno Pagination pagination){
		String code=HttpParameterParser.getString("code");
		String status=HttpParameterParser.getString("status");
		Date fromDate = HttpParameterParser.getSqlDate("fromDate");
		Date toDate = HttpParameterParser.getSqlDate("toDate");

		boolean flag = true;
		Merchant merchant = operator.getMerchant();
		if(merchant.isExistChildren()) {
			flag = true;
		}
		//set pos机查询条件
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("merchant",operator.getMerchant());
		if(!StringUtils.isNullOrEmpty(code))
			map.put("code", code);
		if(!StringUtils.isNullOrEmpty(status))
			map.put("status", Pos.Status.get(Integer.parseInt(status)));
		if(fromDate!=null)
			map.put("beginCreateDate", DateUtils.getEarlyInTheDay(fromDate));
		if(toDate!=null)
			map.put("endCreateDate", DateUtils.getLateInTheDay(toDate));
		pagination.setPageSize(10);
		//获得商户的pos机，包括门店的pos
		posList=new ArrayList<Pos>();
		posList=posManager.find(map, pagination);
		
		//传递参数
		Map<String ,Object> params=new HashMap<String, Object>();
		params.put("posList", posList);
		params.put("flag", flag);
		params.put("pagination", pagination);
		return new ModelAndView("/manager/selfinfo/list.jsp",params);	
	}

	private List<MerchantDto> createMemberCount(List<Merchant> merchants){
		Long countMembers;
		List<MerchantDto> merchantDtos = new ArrayList<MerchantDto>();
		for(Merchant mer : merchants){
			MerchantDto merchantDto = new MerchantDto();
			countMembers = memberManager.countMembers(mer);
			merchantDto.setMerchant(mer);
			merchantDto.setCreateMemberCount(countMembers);
			merchantDtos.add(merchantDto);
		}
		return merchantDtos;
	}
	
}
