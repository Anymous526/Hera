package com.vlives.boss.member.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.PropertiesFinder;

@Service("memberPointDetailManager")
public class MemberPointDetailManagerImpl implements MemberPointDetailManager {
	@Resource
	private BaseDao<MemberPointDetail, Long> memberPointDetailDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MemberPointDetail get(Long id) {
		return memberPointDetailDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public MemberPointDetail createDetail(Member member, Merchant merchant, int point, MemberPointDetail.Type type,
			String desc) {
		MemberPointDetail detail = create(member, point, type, desc);
		detail.setMerchant(merchant);
		memberPointDetailDao.save(detail);
		return detail;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public MemberPointDetail createDetail(Member member, TradeOrder tradeOrder, int point, MemberPointDetail.Type type,
			String desc) {
		MemberPointDetail detail = create(member, point, type, desc);
		detail.setMerchant(tradeOrder.getMerchant());
		detail.setTradeOrder(tradeOrder);
		memberPointDetailDao.save(detail);
		return detail;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private MemberPointDetail create(Member member, int increasePoint, MemberPointDetail.Type changeType, String desc) {
		MemberPointDetail detail = new MemberPointDetail();
		detail.setMember(member);
		detail.setType(changeType);
		detail.setTotalPoint(member.getTotalPoint());
		detail.setPoint(increasePoint);
		detail.setUsablePoint(member.getPoint());
		detail.setCreateDate(new Date());
		detail.setDescription(desc);
		return detail;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MemberPointDetail getByMemberAndOrder(Member member, TradeOrder tradeOrder, MemberPointDetail.Type changeType) {
		PropertiesFinder finder = new PropertiesFinder(MemberPointDetail.class);
		finder.add("member", member);
		finder.add("type", changeType);
		finder.add("tradeOrder", tradeOrder);
		return memberPointDetailDao.getBy(finder);
	}

}
