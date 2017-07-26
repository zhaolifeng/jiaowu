package com.shenlan.dao.system;

import java.util.List;

import com.shenlan.dao.BaseDao;
import org.springframework.stereotype.Repository;

import com.shenlan.domain.system.SystemDictInfo;

@Repository
public class SystemDictInfoDao extends BaseDao<SystemDictInfo> {

	/**
	 * 查询字典信息
	 * @param dictType
	 * @return
	 */
	public List getDictValue(String dictType){
		return this.getSqlSession().selectList(this.namespace + ".getDictValue",dictType);
	}
	
	/**
	 * 查询某一父类型下的 子类型
	 * @return
	 */
	/*public List<SystemDictInfo> getChildDictValue(SystemDictInfo systemDictInfo){
		return this.getSqlSession().selectList(this.namespace + ".getChildDictValue",systemDictInfo);
	}*/
	public List getChildDictValue(String channelType){
		return this.getSqlSession().selectList(this.namespace + ".getChildDictValue",channelType);
	}
	
	/**
	 * 根据字典code获取 name
	 */
	public String getDictNameByCode(String code){
		return this.getSqlSession().selectOne(this.namespace + ".getDictNameByCode",code);
	}

	/**
	 * 根据频道和code 查询
	 */
	public String getDictNameByCodeAndChannel(SystemDictInfo systemDictInfo){
		return this.getSqlSession().selectOne(this.namespace + ".getDictNameByCodeAndChannel",systemDictInfo);
	}
}
