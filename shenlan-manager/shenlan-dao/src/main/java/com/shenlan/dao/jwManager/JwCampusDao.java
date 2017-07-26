package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwCampus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwCampusDao extends BaseDao<JwCampus> {


	public void addCampus(JwCampus campus){
		this.getSqlSession().insert(this.namespace + ".addCampus", campus);
	}

	public List<JwCampus> findCampus(Integer campusId){
		return this.getSqlSession().selectList(this.namespace + ".findCampus", campusId);
	}

	public PageParameter findCampus(PageParameter page,Integer campusId){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwCampus> campuses=this.getSqlSession().selectList(this.namespace + ".findCampus", campusId,pbp.getPageBounds());
		pbp.setPageParameter(campuses.isEmpty() ? null :campuses);
		return page;
	}
	public JwCampus getCampusById(Integer campusId){
		return  this.getSqlSession().selectOne(this.namespace + ".getCampus", campusId);
	}

	public void updateCampus(JwCampus campus){
		 this.getSqlSession().update(this.namespace + ".updateCampus", campus);
	}

}
