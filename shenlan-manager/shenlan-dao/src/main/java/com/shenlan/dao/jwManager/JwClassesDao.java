package com.shenlan.dao.jwManager;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.JwClasses;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分规则兑换Dao层
 * @author YANG
 *
 */
@Repository
public class JwClassesDao extends BaseDao<JwClasses> {


	public void addClasses(JwClasses classes){
		this.getSqlSession().insert(this.namespace + ".addClasses", classes);
	}

	public List<JwClasses> findClasses(){
		return this.getSqlSession().selectList(this.namespace + ".findClasses");
	}

	public PageParameter findClasses(PageParameter page,JwClasses classes){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwClasses> classeses=this.getSqlSession().selectList(this.namespace + ".findClasses", classes,pbp.getPageBounds());
		pbp.setPageParameter(classeses);
		return page;
	}
	public List<JwClasses> findClassesByCondtion(JwClasses classes){
		return this.getSqlSession().selectList(this.namespace + ".findClassesByCondtion",classes);
	}

	public PageParameter findClassesByCondtion(PageParameter page,JwClasses classes){
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<JwClasses> classeses=this.getSqlSession().selectList(this.namespace + ".findClassesByCondtion", classes,pbp.getPageBounds());
		pbp.setPageParameter(classeses);
		return page;
	}
	public JwClasses getClassesById(Integer classesId){
		return  this.getSqlSession().selectOne(this.namespace + ".getClasses", classesId);
	}


}
