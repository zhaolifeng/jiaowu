package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwCampusCourseService;
import com.shenlan.dao.jwManager.JwCampusCourseDao;
import com.shenlan.domain.bo.JwCampusCourse;
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
@Service("jwCampusCourseService")
@Transactional(readOnly = false)
public class JwCampusCourseService extends BaseService<JwCampusCourse> implements IJwCampusCourseService {

    @Autowired
    private JwCampusCourseDao jwCampusCourseDao;

    @Override
    public void addBatchCampusCours(List<JwCampusCourse> list) {
        jwCampusCourseDao.addBatchCampusCours(list);
    }

    @Override
    public void batchDeletCampusCourse(List<JwCampusCourse> list) {
        jwCampusCourseDao.batchDeletCampusCourse(list);
    }

    @Override
    public List<JwCampusCourse> findCampusCourse() {
        return jwCampusCourseDao.findCampusCourse();
    }
}
