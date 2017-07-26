package com.shenlan.dao.jwManager;

import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwCampusTeacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwCampusTeacherDao extends BaseDao<JwCampusTeacher> {


	public void addBatchCampusTeacher(List<JwCampusTeacher> list){
		this.getSqlSession().insert(this.namespace + ".addBatchCampusTeacher", list);
	}

	public void deletBatchCampusTeacher( Long campusId){
		this.getSqlSession().delete(this.namespace + ".deletBatchCampusTeacher",campusId);
	}


	public List<String> findCampusTeacher(Long campusId){
		return this.getSqlSession().selectList(this.namespace + ".findCampusTeacher",campusId);
	}

}
