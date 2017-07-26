package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwClassRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwClassRoomDao extends BaseDao<JwClassRoom> {


	public void addClassRoom(JwClassRoom classRoom){
		this.getSqlSession().insert(this.namespace + ".addClassRoom", classRoom);
	}

	public List<JwClassRoom> findClassRooms(JwClassRoom classRoom){
		return this.getSqlSession().selectList(this.namespace + ".findClassesRoom", classRoom);
	}

	public PageParameter findClassRooms(PageParameter page,JwClassRoom classRoom){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwClassRoom> classRooms=this.getSqlSession().selectList(this.namespace + ".findClassesRoom", classRoom,pbp.getPageBounds());
		pbp.setPageParameter(classRooms);
		return page;
	}

	public JwClassRoom getClassRoomById(Integer classRoomId){
		return  this.getSqlSession().selectOne(this.namespace + ".getClassesRoom", classRoomId);
	}


	public void updateClassesRoom(JwClassRoom classRoom){
		this.getSqlSession().insert(this.namespace + ".updateClassesRoom", classRoom);
	}

}
