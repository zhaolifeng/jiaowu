package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.domain.bo.JwCampusCourse;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwCampusCourseService extends IBaseService<JwCampusCourse> {

	public void addBatchCampusCours(List<JwCampusCourse> list);

	public void batchDeletCampusCourse( List<JwCampusCourse> list);

	public List<JwCampusCourse> findCampusCourse();
}
