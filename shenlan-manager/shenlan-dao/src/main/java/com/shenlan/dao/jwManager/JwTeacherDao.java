package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwTeacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwTeacherDao extends BaseDao<JwTeacher> {


	public void addTeacher(JwTeacher teacher){
		this.getSqlSession().insert(this.namespace + ".addTeacher", teacher);
	}

	public List<JwTeacher> findTeaches(){
		return this.getSqlSession().selectList(this.namespace + ".findTeacher");
	}

	public PageParameter findTeaches(PageParameter page,JwTeacher teacher){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwTeacher> teachers=this.getSqlSession().selectList(this.namespace + ".findTeacher", teacher,pbp.getPageBounds());
		pbp.setPageParameter(teachers);
		return page;
	}

	public List<JwTeacher> findTeacherByCondtion(Map maps){
		return this.getSqlSession().selectList(this.namespace + ".findTeacherByCondtion",maps);
	}

	public JwTeacher getTeacheById(Integer teacherId){
		return  this.getSqlSession().selectOne(this.namespace + ".getTeacher", teacherId);
	}

	public void updateTeacher(JwTeacher teacher){
		 this.getSqlSession().update(this.namespace + ".updateTeacher", teacher);
	}
	public List<Map> findTeacheByclassesId(Map map){
		return this.getSqlSession().selectList(this.namespace + ".findTeacheByclassesId",map);
	}


}
