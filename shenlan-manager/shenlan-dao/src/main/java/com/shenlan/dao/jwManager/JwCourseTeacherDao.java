package com.shenlan.dao.jwManager;

import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwCourseTeacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwCourseTeacherDao extends BaseDao<JwCourseTeacher> {


	public void addBatchCourseTeacher(List<JwCourseTeacher> list){
		this.getSqlSession().insert(this.namespace + ".addBatchCourseTeacher", list);
	}

	public void addCourseTeacher(JwCourseTeacher courseTeacher){
		this.getSqlSession().insert(this.namespace + ".addCourseTeacher", courseTeacher);
	}

	public void batchDeletCourseTeacher( List<JwCourseTeacher> list){
		this.getSqlSession().delete(this.namespace + ".deletBatchCourseTeacher",list);
	}

	public List<JwCourseTeacher> findCourseTeacher(JwCourseTeacher courseTeacher){
		return this.getSqlSession().selectList(this.namespace + ".findCourseTeacher",courseTeacher);
	}

	public List<JwCourseTeacher> findCourseTeacherName(){
		return this.getSqlSession().selectList(this.namespace + ".findCourseTeacherName");
	}


}
