package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwClassesConfigService;
import com.shenlan.dao.jwManager.JwClassesConfigDao;
import com.shenlan.domain.bo.JwClassesConfigure;
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
@Service("jwClassesConfigService")
@Transactional(readOnly = false)
public class JwClassesConfigService extends BaseService<JwClassesConfigure> implements IJwClassesConfigService{

    @Autowired
    private JwClassesConfigDao classesConfig;

    public void addClassesConfig(JwClassesConfigure classes){
        classesConfig.addClassesConfig(classes);
    }

    public List<JwClassesConfigure> findClassesConfig(JwClassesConfigure jwClassesConfigure){
       return classesConfig.findClassesConfig(jwClassesConfigure);
    }

}
