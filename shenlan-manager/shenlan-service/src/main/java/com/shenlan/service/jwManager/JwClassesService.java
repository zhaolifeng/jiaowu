package com.shenlan.service.jwManager;

import com.shenlan.api.jwManager.IJwClassesService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.jwManager.JwClassesDao;
import com.shenlan.domain.bo.JwClasses;
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
@Service("jwClassesService")
@Transactional(readOnly = false)
public class JwClassesService extends BaseService<JwClasses> implements IJwClassesService {

    @Autowired
    private JwClassesDao jwClasses;

    public void addClasses(JwClasses classes){
        jwClasses.addClasses(classes);
    }

    public List<JwClasses> findClasses(){
       return jwClasses.findClasses();
    }
    public List<JwClasses> findClassesByCondtion(JwClasses classes){
        return jwClasses.findClassesByCondtion(classes);
    }
    public PageParameter findClassesByCondtion(PageParameter page,JwClasses classes){
        return jwClasses.findClassesByCondtion(page, classes);
    }

    public JwClasses getClassesById(Integer classesId){
        return  jwClasses.getClassesById(classesId);
    }
    public PageParameter findClasses(PageParameter page,JwClasses classes){
        return  jwClasses.findClasses(page,classes);
    }
}
