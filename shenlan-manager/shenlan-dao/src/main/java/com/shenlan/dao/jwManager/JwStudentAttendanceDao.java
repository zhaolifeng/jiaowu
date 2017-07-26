package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwStudentAttendance;
import com.shenlan.domain.bo.JwTeacherAttendance;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwStudentAttendanceDao extends BaseDao<JwStudentAttendance> {
	public int addStudentAttendance(JwStudentAttendance jwStudentAttendance){
		return this.getSqlSession().insert(this.namespace + ".addStudentAttendance", jwStudentAttendance);
	}

	public PageParameter findStudentAttendances(PageParameter page,JwStudentAttendance jwStudentAttendance){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwStudentAttendance> studentAttendances=this.getSqlSession().selectList(this.namespace + ".findStudentAttendances", jwStudentAttendance,pbp.getPageBounds());
		pbp.setPageParameter(studentAttendances);
		return page;
	}

	public  List<JwStudentAttendance> findStudentAttendances(JwStudentAttendance jwStudentAttendance){
		return  this.getSqlSession().selectList(this.namespace + ".findStudentAttendances", jwStudentAttendance);
	}



}
