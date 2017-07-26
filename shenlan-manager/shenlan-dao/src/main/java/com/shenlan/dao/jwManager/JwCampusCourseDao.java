package com.shenlan.dao.jwManager;

import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwCampusCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwCampusCourseDao extends BaseDao<JwCampusCourse> {


	public void addBatchCampusCours(List<JwCampusCourse> list){
		this.getSqlSession().insert(this.namespace + ".addBatchCampusCourse", list);
	}

	public void batchDeletCampusCourse( List<JwCampusCourse> list){
		this.getSqlSession().delete(this.namespace + ".deletBatchCampusCourse",list);
	}

	public List<JwCampusCourse> findCampusCourse(){
		return this.getSqlSession().selectList(this.namespace + ".findCampusCourse");
	}

}
