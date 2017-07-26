package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwCampus;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwCampusService extends IBaseService<JwCampus> {
	public void addCampus(JwCampus campus);
	public List<JwCampus> findCampus(Integer campusId);
	public PageParameter findCampus(PageParameter page,Integer campusId);
	public JwCampus getCampusById(Integer campusId);
	public void udpateCampus(JwCampus campus);
}
