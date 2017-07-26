package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwTeacherService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwTeacherDao;
import com.shenlan.domain.bo.JwTeacher;
import com.shenlan.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 积分任务service实现类
 * @author YANG
 *
 */
@Service("jwTeacherService")
@Transactional(readOnly = false)
public class JwTeacherService extends BaseService<JwTeacher> implements IJwTeacherService {

    @Autowired
    private JwTeacherDao jwTeacherDao;

    public void addTeacher(JwTeacher teacher){
        jwTeacherDao.addTeacher(teacher);
        teacher.setTeacherNo("sl_t_" + teacher.getId());
        jwTeacherDao.updateTeacher(teacher);
    }

    public List<JwTeacher> findTeaches(){
        return jwTeacherDao.findTeaches();
    }
    public PageParameter findTeaches(PageParameter page,JwTeacher teacher){
        return jwTeacherDao.findTeaches(page,teacher);
    }
    public List<JwTeacher> findTeacherByCondtion(Map maps){
        return jwTeacherDao.findTeacherByCondtion(maps);
    }

    public JwTeacher getTeacheById(Integer teacherId){
        return  jwTeacherDao.getTeacheById(teacherId);
    }

    public void updateTeacher(JwTeacher teacher){
        jwTeacherDao.updateTeacher(teacher);
    }

    public List<Map> findTeacheByclassesId(Map map){
        return  jwTeacherDao.findTeacheByclassesId(map);
    }

}
