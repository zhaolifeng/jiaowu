package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwCampusTeacherService;
import com.shenlan.dao.jwManager.JwCampusTeacherDao;
import com.shenlan.domain.bo.JwCampusTeacher;
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
@Service("jwCampusTeacherService")
@Transactional(readOnly = false)
public class JwCampusTeacherService extends BaseService<JwCampusTeacher> implements IJwCampusTeacherService {

    @Autowired
    private JwCampusTeacherDao jwCampusTeacherDao;


    public void addBatchCampusTeacher(List<JwCampusTeacher> list){
        jwCampusTeacherDao.addBatchCampusTeacher(list);
    }

    public void deletBatchCampusTeacher(Long campusId){
        jwCampusTeacherDao.deletBatchCampusTeacher(campusId);
    }


    public List<String> findCampusTeacher(Long campusId){
        return jwCampusTeacherDao.findCampusTeacher(campusId);
    }

}
