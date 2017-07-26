package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwCampusService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwCampusDao;
import com.shenlan.domain.bo.JwCampus;
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
@Service("jwCampusService")
@Transactional(readOnly = false)
public class JwCampusService extends BaseService<JwCampus> implements IJwCampusService {

    @Autowired
    private JwCampusDao jwCampusDao;

    @Override
    @Transactional(readOnly = false)
    public void addCampus(JwCampus campus) {
        jwCampusDao.addCampus(campus);
    }

    @Override
    public List<JwCampus> findCampus(Integer campusId) {
        return jwCampusDao.findCampus(campusId);
    }
    public PageParameter findCampus(PageParameter page,Integer campusId){
        return jwCampusDao.findCampus(page,campusId);
    }
    public JwCampus getCampusById(Integer campusId){
        return jwCampusDao.getCampusById(campusId);
    }

    public void udpateCampus(JwCampus campus){
        jwCampusDao.updateCampus(campus);
    }
}
