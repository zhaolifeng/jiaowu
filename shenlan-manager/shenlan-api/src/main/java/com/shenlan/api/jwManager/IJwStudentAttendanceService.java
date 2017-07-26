package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwStudentAttendance;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwStudentAttendanceService extends IBaseService<JwStudentAttendance> {

	public int addStudentAttendance(JwStudentAttendance jwStudentAttendance);

	public PageParameter findStudentAttendances(PageParameter page,JwStudentAttendance jwStudentAttendance);

	public List<JwStudentAttendance> findStudentAttendances(JwStudentAttendance jwStudentAttendance);
}
