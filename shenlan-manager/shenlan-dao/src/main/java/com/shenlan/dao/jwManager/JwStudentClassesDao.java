package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwStudentClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwStudentClassesDao extends BaseDao<JwStudentClass> {
	public void addStudentClasses(JwStudentClass studentClass){
		this.getSqlSession().insert(this.namespace + ".addStudentClasses", studentClass);
	}


	public List<JwStudentClass> findStudentClasses(JwStudentClass studentClasses){
		return this.getSqlSession().selectList(this.namespace + ".findStudentClasses",studentClasses);
	}


	public List<JwStudentClass> findStudentClassesBycondtion(Map condtions){
		return this.getSqlSession().selectList(this.namespace + ".findStudentClassesBycondtion",condtions);
	}

	public PageParameter findStudentClassesBycondtion(PageParameter page,Map condtions){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwStudentClass> studentClasses=this.getSqlSession().selectList(this.namespace + ".findStudentClassesBycondtion", condtions,pbp.getPageBounds());
		pbp.setPageParameter(studentClasses);
		return page;
	}


	public JwStudentClass getStudentClassesById(JwStudentClass studentClasses){
		return  this.getSqlSession().selectOne(this.namespace + ".getStudentClassesById", studentClasses);
	}

	public void updateStudentClasses(JwStudentClass studentClasses){
		 this.getSqlSession().update(this.namespace + ".updateStudentClasses", studentClasses);
	}

	public void updateStudentClassesCheckIn(JwStudentClass studentClasses){
		this.getSqlSession().update(this.namespace + ".updateStudentClassesCheckIn", studentClasses);
	}

}
