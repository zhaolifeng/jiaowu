package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwTeacherAttendance;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwTeacherAttendanceService extends IBaseService<JwTeacherAttendance> {

	public void addTeacherAttendance(JwTeacherAttendance teacherAttendance);

	public PageParameter findTeacherAttendances(PageParameter page,JwTeacherAttendance teacherAttendance);

	public List<JwTeacherAttendance> getJwTeacherAttendance(JwTeacherAttendance teacherAttendance);
}
