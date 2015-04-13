package com.vlives.boss.pos.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.pos.dao.PosDao;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.pos.domain.Pos.Status;
import com.vlives.boss.pos.dto.PosInfo;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PlaceholderFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

/**
 * 
 */
@Service("posManager")
public class PosManagerImpl implements PosManager {

	@Resource
	private PosDao posDao;

	/**
	 * POS编号已经存在,系统提示POS机编号已存在。用例结束 商户编号不存在,系统提示商户不存在。用例结束
	 * 商户状态非有效状态,系统提示商户状态非有效状态
	 * 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(Operator operator, String posSerialNumber, String code,
			Merchant merchant, String posDesc) throws BusinessException {
		if (posDao.getByProperty("code", code) != null) {
			throw new BusinessException("POS机编号已存在!");
		}
		Pos activePos = getActivePos(posSerialNumber);
		if (activePos != null) {
			throw new BusinessException("POS机当前正在被以下商家使用 : "
					+ activePos.getMerchant().getName());
		}
		Pos pos = new Pos();
		pos.setSerialNumber(posSerialNumber);
		pos.setCode(code);
		pos.setMerchant(merchant);
		pos.setStatus(Pos.Status.STATUS_ACTIVE);
		pos.setCreateDate(new Date());
		pos.setDescription(posDesc);
		pos.setLastModityDate(new Date());
		pos.setBatchNumber(1);
		posDao.save(pos);
		pos.addStatusLog(operator, null, Pos.Status.STATUS_ACTIVE, "创建POS机 ");

	}

	private Pos getActivePos(String posSerialNumber) {
		String hql = "from Pos where serialNumber = ? and status=1";
		Finder finder = new PlaceholderFinder(hql, posSerialNumber);
		return posDao.getBy(finder);
	}

	/**
	 * 描述：冻结pos并记录操作日志
	 * 
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public void freeze(Pos pos, Operator operator) throws BusinessException {
		if (!pos.isActive()) {
			throw new BusinessException("非有效状态不能冻结");
		}
		Status beginStatus = pos.getStatus();
		pos.setLastModityDate(new Date());
		pos.setStatus(Pos.Status.STATUS_FREEZED);
		pos.addStatusLog(operator, beginStatus, Pos.Status.STATUS_FREEZED,
				"POS机冻结 ");
		posDao.update(pos);
	}

	/**
	 * 描述：解冻pos并记录操作日志
	 * 
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public void unFreeze(Pos pos, Operator operator) throws BusinessException {
		if (!pos.isFreeze()) {
			throw new BusinessException("非冻结状态不能解冻");
		}
		Status beginStatus = pos.getStatus();
		pos.setLastModityDate(new Date());
		pos.setStatus(Pos.Status.STATUS_ACTIVE);
		pos.addStatusLog(operator, beginStatus, Pos.Status.STATUS_ACTIVE,
				"POS机解冻 ");
		posDao.update(pos);

	}

	/**
	 * 描述：注销pos并记录操作日志
	 * 
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public void logOut(Pos pos, Operator operator) throws BusinessException {
		if (pos.isLogOut()) {
			throw new BusinessException("POS机已注销");
		}
		Status beginStatus = pos.getStatus();
		pos.setLastModityDate(new Date());
		pos.setStatus(Pos.Status.STATUS_DISABLE);
		pos.addStatusLog(operator, beginStatus, Pos.Status.STATUS_DISABLE,
				"POS机注销 ");
		posDao.update(pos);
	}

	/**
	 * 根据主键找对象
	 */
	@Transactional(readOnly = true)
	@Override
	public Pos get(Long id) {
		return posDao.get(id);
	}

	/**
	 * 根据条件查找pos return list
	 */
	@Override
	public List<Pos> find(Map<String, Object> map, Pagination pagination) {

		String dynamicString = "from Pos where 1=1"
				+ "{ and merchant = :merchant }" + "{ and code =:code}"
				+ "{ and status = :status}"
				+ "{ and createDate >= :beginCreateDate}"
				+ "{ and createDate <= :endCreateDate}";
		Finder finder = new DynamicFinder(dynamicString, map);
		return posDao.find(finder, pagination);

	}

	@Override
	public Pos getByCode(String code) {
		return posDao.getByProperty("code", code);
	}

	@Override
	public List<PosInfo> findPosInfo(Map<String, Object> map, Pagination pagination) {
		String dynamicString = "select p.merchant.code, p.merchant.name, p.code from Pos p where 1=1"
			+ "{ and p.merchant.code = :code }"
			+ "{ and p.serialNumber = :serialNumber }";
		Finder finder = new DynamicFinder(dynamicString, map);
		List<Object> list = posDao.findToObjectArray(finder,pagination);
		List<PosInfo> result = new ArrayList<PosInfo>();
		for(Object obj : list) {
			Object[] values = (Object[]) obj;
			PosInfo posInfo = new PosInfo();
			posInfo.setMerchantCode((String)values[0]);
			posInfo.setMerchantName((String)values[1]);
			posInfo.setPosCode((String)values[2]);
			result.add(posInfo);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(Pos pos) {
		posDao.update(pos);
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Pos getByPosSNSerial(String serialNumber){
		if (StringUtils.isBlank(serialNumber)) {
			return null;
		}
		return posDao.getByProperty("serialNumber", serialNumber);
	}

}
