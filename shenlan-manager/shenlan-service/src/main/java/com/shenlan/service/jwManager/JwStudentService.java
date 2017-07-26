package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwStudentService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwStudentDao;
import com.shenlan.domain.bo.JwStudent;
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
@Service("jwStudentService")
@Transactional(readOnly = false)
public class JwStudentService extends BaseService<JwStudent> implements IJwStudentService {

    @Autowired
    private JwStudentDao jwStudentDao;

    public void addStudent(JwStudent student){
       jwStudentDao.addStudent(student);
        student.setStudentNo("sl_s_"+student.getId());
        jwStudentDao.updateStudent(student);
    }


    public List<JwStudent> findStudent(JwStudent student){
        return jwStudentDao.findStudent(student);
    }

    public JwStudent getStudenteById(JwStudent student){
        return jwStudentDao.getStudenteById(student);
    }

    public void updateStudent(JwStudent student){
        jwStudentDao.updateStudent(student);
    }

    public PageParameter findStudent(PageParameter page,JwStudent student){
        return jwStudentDao.findStudent(page,student);
    }

}
