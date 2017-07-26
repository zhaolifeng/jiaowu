package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwClasses;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwClassesService extends IBaseService<JwClasses> {

	public void addClasses(JwClasses classes);
	public List<JwClasses> findClasses();
	public List<JwClasses> findClassesByCondtion(JwClasses classes);
	public JwClasses getClassesById(Integer classesId);
	public PageParameter findClasses(PageParameter page,JwClasses classes);
	public PageParameter findClassesByCondtion(PageParameter page,JwClasses classes);
}
