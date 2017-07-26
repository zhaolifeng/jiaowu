package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwCourse;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwCourseService extends IBaseService<JwCourse> {

	public void addCourse(JwCourse course);

	public List<JwCourse> findCourse();

	public PageParameter findCourse(PageParameter page,JwCourse course);

	public JwCourse getCourseById(Integer courseId);

	public void updateCourse(JwCourse course);
}
