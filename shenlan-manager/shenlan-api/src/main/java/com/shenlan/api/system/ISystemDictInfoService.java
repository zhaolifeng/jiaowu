package com.shenlan.api.system;

import java.util.List;

import com.shenlan.api.IBaseService;
import com.shenlan.domain.system.SystemDictInfo;

public interface ISystemDictInfoService extends IBaseService<SystemDictInfo> {
	

	/**
	 * 查询字典信息
	 * @param dictType 字典类型
	 * @return
	 */
	public List getDictValue(String dictType);
	
	/**
	 * 查询某一父类型下的 子类型
	 * @param
	 * @param
	 * @return
	 */
	//public List<SystemDictInfo> getChildDictValue(SystemDictInfo systemDictInfo);
	public List getChildDictValue(String channelType);
	
	/**
	 * 根据字典code获取 name
	 */
	public String getDictNameByCode(String code);

	/**
	 * 根据频道和code 查询
	 */
	public String getDictNameByCodeAndChannel(SystemDictInfo systemDictInfo);
}
