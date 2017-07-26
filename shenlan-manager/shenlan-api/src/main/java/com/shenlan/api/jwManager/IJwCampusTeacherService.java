package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.domain.bo.JwCampusTeacher;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwCampusTeacherService extends IBaseService<JwCampusTeacher> {


	public void addBatchCampusTeacher(List<JwCampusTeacher> list);

	public void deletBatchCampusTeacher( Long campusId);


	public List<String> findCampusTeacher(Long campusId);
}
