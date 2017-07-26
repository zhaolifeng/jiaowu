package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwStudentClass;

import java.util.List;
import java.util.Map;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwStudentClassesService extends IBaseService<JwStudentClass> {
	public void addStudentClasses(JwStudentClass studentClass);

	public List<JwStudentClass> findStudentClasses(JwStudentClass studentClasses);

	public List<JwStudentClass> findStudentClassesBycondtion(Map condtions);

	public PageParameter findStudentClassesBycondtion(PageParameter page,Map condtions);

	public JwStudentClass getStudentClassesById(JwStudentClass studentClasses);

	public void updateStudentClasses(JwStudentClass studentClasses);

	public void updateStudentClassesCheckIn(JwStudentClass studentClasses);
}
