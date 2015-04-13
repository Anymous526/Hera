package com.vlives.boss.user.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.merchant.dao.MerchantStatisticDao;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantReferenceStatistic;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.domain.UserFavMerchant;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PlaceholderFinder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-7 上午06:03:11
 * 类说明
 */

@Service("userFavoriteManager")
public class UserFavoriteManagerImp implements UserFavoriteManager {
	
	@Resource
	private BaseDao<UserFavMerchant, Long> userFavMerchantDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MerchantStatisticDao merchantStatisticDao;
	
	
	
	/**
	 * 添加收藏信息
	 * @param  memberFavorite用户收藏对象
	 */
	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(Merchant merchant,User user) throws BusinessException{
		//判断该收藏对象是否已经存在
		UserFavMerchant userFavMerchant = get(merchant,user);
		if( userFavMerchant != null){
			return ;
		}

		userFavMerchant = new UserFavMerchant();
		userFavMerchant.setMerchant(merchant);
		userFavMerchant.setUser(user);
		userFavMerchant.setCreateDate(new Date());
		MerchantReferenceStatistic mrs = merchant.getMerchantReferenceStatistic();
		mrs.setFavCount(mrs.getFavCount()+1);
		merchantStatisticDao.saveOrUpdate(mrs);
		userFavMerchantDao.save(userFavMerchant);
	}
	
	/**
	 * 通过商户对象和用户对象获取用户收藏商户关系对象
	 * @param merchant
	 * @param user
	 * @return
	 */
	@Override
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public  UserFavMerchant get(Merchant merchant,User user){
		PropertiesFinder propertiesFinder = new PropertiesFinder(UserFavMerchant.class);
		propertiesFinder.add("merchant", merchant);
		propertiesFinder.add("user", user);
		return userFavMerchantDao.getBy(propertiesFinder);
	}
	
	/**
	 * 用户删除收藏信息
	 * @param  memberFavorite  用户收藏对象
	 */
	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public void delete(UserFavMerchant userFavMerchant){
		if(userFavMerchant == null){
			return;
		}
		Merchant merchant = userFavMerchant.getMerchant();
		MerchantReferenceStatistic mrs = merchant.getMerchantReferenceStatistic();
		mrs.setFavCount(mrs.getFavCount()-1);
		merchantStatisticDao.saveOrUpdate(mrs);
		userFavMerchantDao.delete(userFavMerchant);
	}
	

	/**
	 * 查询用户的所有收藏信息
	 * @param user_id 用户编号
	 * @param pagination 分页参数
	 * @return 商户相关统计相关信息
	 */
	@Override
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public List<UserFavMerchant> find(User user,Pagination pagination){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		String _hsql = "from UserFavMerchant umf where 1=1 "
				+"{ and umf.user=:user} order by create_date desc";
		Finder finder = new DynamicFinder(_hsql,map); 
		List <UserFavMerchant> list = userFavMerchantDao.find(finder, pagination);
		return list;
	}
	
	/**
	 * 获取商户收藏对象
	 */
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public UserFavMerchant get(Long id) {
		return  userFavMerchantDao.get(id);
	}
	

	/**
	 *  判断商户是否已经被某用户收藏
	 */
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public boolean isRelevanceMerchants(User user,Merchant merchant){
		String hsql = "from UserFavMerchant ufm where ufm.user=:user and ufm.merchant=:merchant";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("merchant", merchant);
		Finder finder = new SimpleParametersFinder(hsql, map);
		return userFavMerchantDao.isExisted(finder);
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public Map<Long, Boolean> isFavorite(final User user, final List<Long> merchantIds) {
		String merchantIdStr = "";
		for(Long id : merchantIds) {
			merchantIdStr+=id+",";
		}
		merchantIdStr = merchantIdStr.substring(0,merchantIdStr.length()-1);
		String sql="SELECT A.ID MID,B.USER_ID USERID FROM " +
				"MERCHANT A LEFT JOIN USER_FAV_MERCHANT B ON A.ID=B.MERCHANT_ID " +
				"AND B.USER_ID = ? WHERE A.ID IN("+merchantIdStr+")";
	 
		return (Map<Long, Boolean>)jdbcTemplate.query(sql, new Object[]{user.getId()}, new ResultSetExtractor() {
			Map<Long, Boolean> result = new HashMap<Long, Boolean>();
			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				 while (rs.next()) {
					Long mid = rs.getLong("MID");
					Long uid = rs.getLong("USERID");
 
					result.put(mid, uid==null||uid==0?false:true);
				}
				return result;
			}
		});
	}
	
	@Override
	public long count(User user) {
		String hql = " from UserFavMerchant u where u.user = ? ";
		Finder finder = new PlaceholderFinder(hql, user);
		return userFavMerchantDao.count(finder);
	}
}
