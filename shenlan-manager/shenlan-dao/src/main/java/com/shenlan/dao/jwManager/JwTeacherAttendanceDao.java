package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwCourse;
import com.shenlan.domain.bo.JwTeacherAttendance;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwTeacherAttendanceDao extends BaseDao<JwTeacherAttendance> {
	public void addTeacherAttendance(JwTeacherAttendance teacherAttendance){
		this.getSqlSession().insert(this.namespace + ".addTeacherAttendance", teacherAttendance);
	}

	public PageParameter findTeacherAttendances(PageParameter page,JwTeacherAttendance teacherAttendance){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwTeacherAttendance> teacherAttendances=this.getSqlSession().selectList(this.namespace + ".findTeacherAttendances", teacherAttendance,pbp.getPageBounds());
		pbp.setPageParameter(teacherAttendances);
		return page;
	}

	public List<JwTeacherAttendance> getJwTeacherAttendance(JwTeacherAttendance teacherAttendance){
		return this.getSqlSession().selectList(this.namespace + ".findTeacherAttendances",teacherAttendance);
	}
}
