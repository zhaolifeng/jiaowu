package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwCourseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwCourseDao;
import com.shenlan.domain.bo.JwCourse;
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
@Service("jwCourseService")
@Transactional(readOnly = false)
public class JwCourseService extends BaseService<JwCourse> implements IJwCourseService {

    @Autowired
    private JwCourseDao jwCourseDao;

    public void addCourse(JwCourse course){
        jwCourseDao.addCourse(course);
    }

    public List<JwCourse> findCourse(){
        return  jwCourseDao.findCourse();
    }

    public PageParameter findCourse(PageParameter page,JwCourse course){
        return  jwCourseDao.findCourse(page,course);
    }

    public JwCourse getCourseById(Integer courseId){
        return jwCourseDao.getCourseById(courseId);
    }

    public void updateCourse(JwCourse course){
        jwCourseDao.updateCourse(course);
    }

}
