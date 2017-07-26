package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwTeacher;

import java.util.List;
import java.util.Map;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwTeacherService extends IBaseService<JwTeacher> {

	public void addTeacher(JwTeacher teacher);
	public List<JwTeacher> findTeaches();
	public PageParameter findTeaches(PageParameter page,JwTeacher teacher);
	public List<JwTeacher> findTeacherByCondtion(Map maps);
	public JwTeacher getTeacheById(Integer teacherId);
	public void updateTeacher(JwTeacher teacher);
	public List<Map> findTeacheByclassesId(Map map);
}
