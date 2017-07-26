package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.domain.bo.JwCourseTeacher;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwCourseTeacherService extends IBaseService<JwCourseTeacher> {

	public void addBatchCourseTeacher(List<JwCourseTeacher> courseTeachers);

	public void batchDeletCourseTeacher(List<JwCourseTeacher> list);

	public List<JwCourseTeacher> findCourseTeacher(JwCourseTeacher courseTeacher);

	public void addCourseTeacher(JwCourseTeacher courseTeacher);

	public List<JwCourseTeacher> findCourseTeacherName();
}
