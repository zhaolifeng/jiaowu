package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwStudentDao extends BaseDao<JwStudent> {


	public void addStudent(JwStudent student){
		int a=this.getSqlSession().insert(this.namespace + ".addStudent", student);
		logger.info("&&&&&&&&&&&&&&&&&&&"+a);
	}


	public List<JwStudent> findStudent(JwStudent student){
		return this.getSqlSession().selectList(this.namespace + ".findStudent",student);
	}

	public JwStudent getStudenteById(JwStudent student){
		return  this.getSqlSession().selectOne(this.namespace + ".getStudent", student);
	}

	public void updateStudent(JwStudent student){
		 this.getSqlSession().update(this.namespace + ".updateStudent", student);
	}

	public PageParameter findStudent(PageParameter page,JwStudent student){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwStudent> students=this.getSqlSession().selectList(this.namespace + ".findStudent", student,pbp.getPageBounds());
		pbp.setPageParameter(students);
		return page;
	}

}
