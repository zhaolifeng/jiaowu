package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwTeacherAttendanceService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwTeacherAttendanceDao;
import com.shenlan.domain.bo.JwTeacherAttendance;
import com.shenlan.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("jwTeacherAttendanceService")
@Transactional(readOnly = false)
public class JwTeacherAttendanceService extends BaseService<JwTeacherAttendance> implements IJwTeacherAttendanceService {

    @Autowired
    private JwTeacherAttendanceDao teacherAttendanceDao;

    public void addTeacherAttendance(JwTeacherAttendance teacherAttendance){
        teacherAttendanceDao.addTeacherAttendance(teacherAttendance);
    }

    public PageParameter findTeacherAttendances(PageParameter page,JwTeacherAttendance teacherAttendance){
        return teacherAttendanceDao.findTeacherAttendances(page,teacherAttendance);
    }
    public List<JwTeacherAttendance> getJwTeacherAttendance(JwTeacherAttendance teacherAttendance){
        return teacherAttendanceDao.getJwTeacherAttendance(teacherAttendance);
    }


}
