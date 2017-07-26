package com.shenlan.api.system;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.system.SystemUser;

public interface ISystemUserService extends IBaseService<SystemUser> {

	/**
	 * 根据用户名检查用户是否存在
	 */
	public SystemUser checkSystemUserByName(String username);
	
	/**
	 * 根据用户名查找用户
	 * 登陆使用该方法
	 */
	public SystemUser findSystemUserByName(String username);
	
	/**
	 * 添加管理员用户（有角色则添加角色关联表数据）
	 */
	public void addSystemUser(SystemUser systemUser);
	
	/**
	 * 删除管理员（有角色则删除角色关联表数据）
	 */
	public void deleteSystemUser(SystemUser systemUser);
	/**
	 * 修改管理员（有角色则修改角色关联表数据）
	 */
	public void updateSystemUser(SystemUser systemUser);
	
	/**
	 * 查询某个用户（有角色则包含角色）
	 * 修改某个用户时，回显数据
	 */
	public SystemUser selectById(Long id);
	
	/**
	 * 根据用户名模糊查询用户，分页展示
	 */
	public PageParameter querySystemUserByName(PageParameter page,SystemUser systemUser);
	
}
