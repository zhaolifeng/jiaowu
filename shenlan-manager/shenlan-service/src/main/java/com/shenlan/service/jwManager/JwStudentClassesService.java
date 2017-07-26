package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwStudentClassesService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwStudentClassesDao;
import com.shenlan.domain.bo.JwStudentClass;
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
@Service("jwStudentClassesService")
@Transactional(readOnly = false)
public class JwStudentClassesService extends BaseService<JwStudentClass> implements IJwStudentClassesService {

    @Autowired
    private JwStudentClassesDao jwStudentClassesDao;

    public void addStudentClasses(JwStudentClass studentClass){
        jwStudentClassesDao.addStudentClasses(studentClass);
    }

    public List<JwStudentClass> findStudentClasses(JwStudentClass studentClasses){
        return jwStudentClassesDao.findStudentClasses(studentClasses);
    }

    public List<JwStudentClass> findStudentClassesBycondtion(Map condtions){
        return jwStudentClassesDao.findStudentClassesBycondtion(condtions);
    }

    public PageParameter findStudentClassesBycondtion(PageParameter page,Map condtions){
        return jwStudentClassesDao.findStudentClassesBycondtion(page,condtions);
    }
    public JwStudentClass getStudentClassesById(JwStudentClass studentClasses){
        return jwStudentClassesDao.getStudentClassesById(studentClasses);
    }

    public void updateStudentClasses(JwStudentClass studentClasses){
        jwStudentClassesDao.updateStudentClasses(studentClasses);
    }

    public void updateStudentClassesCheckIn(JwStudentClass studentClasses){
        jwStudentClassesDao.updateStudentClassesCheckIn(studentClasses);
    }

}
