package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwCourseDao extends BaseDao<JwCourse> {


	public void addCourse(JwCourse Course){
		this.getSqlSession().insert(this.namespace + ".addCourse", Course);
	}

	public List<JwCourse> findCourse(){
		return this.getSqlSession().selectList(this.namespace + ".findCourse");
	}

	public PageParameter findCourse(PageParameter page,JwCourse course){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwCourse> courses=this.getSqlSession().selectList(this.namespace + ".findCourse", course,pbp.getPageBounds());
		pbp.setPageParameter(courses);
		return page;
	}

	public JwCourse getCourseById(Integer campusId){
		return  this.getSqlSession().selectOne(this.namespace + ".getCourse", campusId);
	}

	public void updateCourse(JwCourse course){
		 this.getSqlSession().update(this.namespace + ".updateCourse", course);
	}

}
