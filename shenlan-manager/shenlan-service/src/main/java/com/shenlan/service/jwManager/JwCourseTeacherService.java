package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwCourseTeacherService;
import com.shenlan.dao.jwManager.JwCourseTeacherDao;
import com.shenlan.domain.bo.JwCourseTeacher;
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
@Service("jwCourseTeacherService")
@Transactional(readOnly = false)
public class JwCourseTeacherService extends BaseService<JwCourseTeacher> implements IJwCourseTeacherService {

    @Autowired
    private JwCourseTeacherDao jwCourseTeacherDao;

    public void addBatchCourseTeacher(List<JwCourseTeacher> courseTeachers){
        jwCourseTeacherDao.batchDeletCourseTeacher(courseTeachers);
        jwCourseTeacherDao.addBatchCourseTeacher(courseTeachers);
    }

    public void batchDeletCourseTeacher(List<JwCourseTeacher> list){
        jwCourseTeacherDao.batchDeletCourseTeacher(list);
    }

    public List<JwCourseTeacher> findCourseTeacher(JwCourseTeacher courseTeacher){
        return  jwCourseTeacherDao.findCourseTeacher(courseTeacher);
    }

    public void addCourseTeacher(JwCourseTeacher courseTeacher){
        jwCourseTeacherDao.addCourseTeacher(courseTeacher);
    }

    public List<JwCourseTeacher> findCourseTeacherName(){
        return  jwCourseTeacherDao.findCourseTeacherName();
    }
}
