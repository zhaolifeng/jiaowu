package com.shenlan.dao.jwManager;

import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwClassesConfigure;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwClassesConfigDao extends BaseDao<JwClassesConfigure> {


	public void addClassesConfig(JwClassesConfigure classesConfigure){
		this.getSqlSession().insert(this.namespace + ".addClassesConfig", classesConfigure);
	}

	public List<JwClassesConfigure> findClassesConfig(JwClassesConfigure classesConfigure){
		return this.getSqlSession().selectList(this.namespace + ".findClassesConfig",classesConfigure);
	}
}
