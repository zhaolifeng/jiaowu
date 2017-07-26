package com.shenlan.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenlan.api.system.ISystemDictInfoService;
import com.shenlan.dao.system.SystemDictInfoDao;
import com.shenlan.domain.system.SystemDictInfo;
import com.shenlan.service.BaseService;

@Service("systemDictInfoService")
@Transactional(readOnly = true)
public class SystemDictInfoService extends BaseService<SystemDictInfo> implements ISystemDictInfoService {

	@Autowired
	private SystemDictInfoDao systemDictInfoDao;
	
	/**
	 * 查询字典信息
	 */
	public List getDictValue(String dictType) {
		return systemDictInfoDao.getDictValue(dictType);
	}

	/**
	 * 查询某一父类型下的 子类型
	 */
	/*public List<SystemDictInfo> getChildDictValue(SystemDictInfo systemDictInfo) {
		return systemDictInfoDao.getChildDictValue(systemDictInfo);
	}*/
	public List getChildDictValue(String channelType) {
		return systemDictInfoDao.getChildDictValue(channelType);
	}

	/**
	 * 根据字典code获取name
	 */
	public String getDictNameByCode(String code) {
		return systemDictInfoDao.getDictNameByCode(code);
	}

	/**
	 * 根据频道和code 查询
	 */
	public String getDictNameByCodeAndChannel(SystemDictInfo systemDictInfo){
		return systemDictInfoDao.getDictNameByCodeAndChannel(systemDictInfo);
	}
}
