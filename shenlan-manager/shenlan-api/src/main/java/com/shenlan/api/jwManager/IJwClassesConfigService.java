package com.shenlan.api.jwManager;

import com.shenlan.api.IBaseService;
import com.shenlan.domain.bo.JwClassesConfigure;

import java.util.List;

/**
 * 奖品管理服务接口
 * @author YANG
 *
 */
public interface IJwClassesConfigService extends IBaseService<JwClassesConfigure> {

	public void addClassesConfig(JwClassesConfigure classesConfigure);
	public List<JwClassesConfigure> findClassesConfig(JwClassesConfigure classesConfigure);
}
