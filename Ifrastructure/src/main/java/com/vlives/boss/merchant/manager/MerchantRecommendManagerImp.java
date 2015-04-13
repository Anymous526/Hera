package com.vlives.boss.merchant.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.merchant.dao.MerchantRecommendDao;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantRecommend;
import com.vlives.boss.merchant.domain.MerchantReferenceStatistic;
import com.vlives.core.pagination.Pagination;
import com.vlives.util.ObjectComparatorUtils;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-6 上午09:28:58 类说明 实现商户总推荐排行
 */

@Service("merchantRecommendManager")
public class MerchantRecommendManagerImp implements MerchantRecommendManager {
	@Autowired
	private MerchantManager merchantManager;

	@Autowired
	private MerchantRecommendDao merchantRecommendDao;

	 
	
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void createMerchantRecommend() {
		Pagination pagination = new Pagination();
		// 设置每次请求多少条数据
		pagination.setPageSize(1000);
		// 定义一个对象，存储排序好的对象
		List<Merchant> rtnList = new LinkedList<Merchant>();
		while (true) {
			List<Merchant> merchants = merchantManager.find(
					new HashMap<String, Object>(), pagination);
			rtnList.addAll(merchants);// 将List对象中的数据存入List
			if (pagination.isLastPage())
				break;
			pagination.setCurrentPage(pagination.getCurrentPage() + 1);
		}
		// 定义相关统计的集合，接受排序后的相关统计数据
		List<MerchantReferenceStatistic> mrsList = new ArrayList<MerchantReferenceStatistic>();
		// 对程序进行排序
		mrsList = sortMerchantByGrade(rtnList);
		//获取并更新所有的商户推荐信息
		MerchantRecommend merchantRecommend = null;
		for (int i = 0; i < mrsList.size(); i++) {
			merchantRecommend = mrsList.get(i).getMerchant().getMerchantRecommend();
			merchantRecommend.setCurDate(new Date());
			merchantRecommend.setSortId(new Long(i));
			merchantRecommendDao.update(merchantRecommend);
		}
	}

	/**
	 * 对用户推荐进行排行
	 * 
	 * @param sortList
	 *            上一轮排好的对象，如果第一次排序，就直接 new ArrayList<Merchant>()
	 * @param newList
	 *            通过程序获取的新的对象集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MerchantReferenceStatistic> sortMerchantByGrade(
			List<Merchant> dataList) {

		// /商户相关统计对象
		List<MerchantReferenceStatistic> mrsList = new LinkedList<MerchantReferenceStatistic>();
		// 将Merchant对象转换为MerchantReferenceStatistic对象
		for (int i = 0; dataList != null && i < dataList.size(); i++) {
			Merchant objMerchant = (Merchant) dataList.get(i);
			MerchantReferenceStatistic objMrs = objMerchant
					.getMerchantReferenceStatistic();
			mrsList.add(objMrs);
		}

		String[][] properties = new String[][] {
				new String[] { "averageGrade", ObjectComparatorUtils.DESC },
				new String[] { "totalGrade", ObjectComparatorUtils.DESC } };
		Collections.sort(mrsList, new ObjectComparatorUtils(
				MerchantReferenceStatistic.class, properties));

		return mrsList;
	}

}
