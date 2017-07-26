package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwClassRoomService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwClassRoomDao;
import com.shenlan.domain.bo.JwClassRoom;
import com.shenlan.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 积分任务service实现类
 * @author YANG
 *
 */
@Service("jwClassRoomService")
@Transactional(readOnly = false)
public class JwClassRoomService extends BaseService<JwClassRoom> implements IJwClassRoomService {

    @Autowired
    private JwClassRoomDao jwClassRoomDao;

    public void addClassRoom(JwClassRoom classRoom){
        jwClassRoomDao.addClassRoom(classRoom);
    }

    public List<JwClassRoom> findClassRooms(JwClassRoom classRoom){
        return jwClassRoomDao.findClassRooms(classRoom);
    }

    public PageParameter findClassRooms(PageParameter page,JwClassRoom classRoom){
        return jwClassRoomDao.findClassRooms(page,classRoom);
    }

    public JwClassRoom getClassRoomById(Integer classRoomId){
        return jwClassRoomDao.getClassRoomById(classRoomId);
    }

    public void updateClassesRoom(JwClassRoom classRoom){
        jwClassRoomDao.updateClassesRoom(classRoom);
    }
}
