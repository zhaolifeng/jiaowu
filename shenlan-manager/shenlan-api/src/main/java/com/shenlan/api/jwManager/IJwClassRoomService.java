package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwClassRoom;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwClassRoomService extends IBaseService<JwClassRoom> {
	public void addClassRoom(JwClassRoom classRoom);

	public List<JwClassRoom> findClassRooms(JwClassRoom classRoom);

	public JwClassRoom getClassRoomById(Integer classRoomId);

	public PageParameter findClassRooms(PageParameter page,JwClassRoom classRoom);

	public void updateClassesRoom(JwClassRoom classRoom);
}
