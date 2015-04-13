package com.vlives.boss.user.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserFavMerchant;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-7 上午03:34:31
 * 类说明
 */

public interface UserFavoriteManager {

	/**
	 * 根据编号获取收藏信息
	 * @param id
	 * @return
	 */
	public UserFavMerchant get(Long id);
	/**
	 * 通过商户和用户得到收藏夹
	 * @param merchant
	 * @param user
	 * @return
	 */
	public UserFavMerchant get(Merchant merchant,User user);
	
	/**
	 * 添加收藏信息
	 * @param merchant 商户信息
	 * @param user 用户信息
	 */
	public void create(Merchant merchant,User user) throws BusinessException;
	
	/**
	 * 用户删除收藏信息
	 * @param  memberFavorite  用户收藏对象
	 */
	public void delete(UserFavMerchant userFavMerchant);

	/**
	 * 查询用户的所有收藏信息
	 * @param user 用户对象
	 * @param pagination 分页参数
	 * @return 商户相关统计相关信息
	 */
	public List<UserFavMerchant> find(User user,Pagination pagination);
	
	/**
	 * 判断某用户是否已经收藏该商户
	 * @param user 用户对象
	 * @param merchants 商户对象
	 * @return
	 */
	public boolean isRelevanceMerchants(User user,Merchant merchant);
	
	/**
	 * 根据用户、商户集合判断商户是否被该用户收藏
	 * @author jianguo.xu
	 * @param user
	 * @param merchantIds
	 * @return map  key:商户ID value:是否被收藏
	 */
	public Map<Long, Boolean> isFavorite(User user,List<Long> merchantIds);

	public long count(User user);
	
}
