package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwStudentAttendanceService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwStudentAttendanceDao;
import com.shenlan.domain.bo.JwStudentAttendance;
import com.shenlan.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("jwStudentAttendanceService")
@Transactional(readOnly = false)
public class JwStudentAttendanceService extends BaseService<JwStudentAttendance> implements IJwStudentAttendanceService {

    @Autowired
    private JwStudentAttendanceDao studentAttendanceDao;

    public int addStudentAttendance(JwStudentAttendance jwStudentAttendance){
       return studentAttendanceDao.addStudentAttendance(jwStudentAttendance);
    }

    public PageParameter findStudentAttendances(PageParameter page,JwStudentAttendance jwStudentAttendance){
        return studentAttendanceDao.findStudentAttendances(page,jwStudentAttendance);
    }

    public List<JwStudentAttendance> findStudentAttendances(JwStudentAttendance jwStudentAttendance){
        return studentAttendanceDao.findStudentAttendances(jwStudentAttendance);
    }

}
