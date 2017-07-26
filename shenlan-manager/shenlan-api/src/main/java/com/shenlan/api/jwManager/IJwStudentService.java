package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwStudent;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwStudentService extends IBaseService<JwStudent> {

	public void addStudent(JwStudent student);


	public List<JwStudent> findStudent(JwStudent student);

	public JwStudent getStudenteById(JwStudent student);

	public void updateStudent(JwStudent student);
	public PageParameter findStudent(PageParameter page,JwStudent student);
}
