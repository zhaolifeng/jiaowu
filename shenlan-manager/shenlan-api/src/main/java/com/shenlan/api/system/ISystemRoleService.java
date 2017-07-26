package com.shenlan.api.system;

import java.util.List;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.system.SystemRole;
import com.shenlan.domain.system.SystemUser;

public interface ISystemRoleService extends IBaseService<SystemRole> {

	/**
	 * 根据角色名检查角色是否存在
	 */
	public SystemRole checkSystemRoleByName(String name);
	
	/**
	 * 添加角色（有菜单则添加菜单关联表数据）
	 */
	public void addSystemRole(SystemRole systemRole);
	
	/**
	 * 删除角色（有菜单则删除菜单关联表数据）
	 */
	public void deleteSystemRole(SystemRole systemRole);
	/**
	 * 修改角色（有菜单则修改菜单关联表数据）
	 */
	public void updateSystemRole(SystemRole systemRole);
	
	/**
	 * 查询某个角色（有菜单则包含菜单）
	 * 修改某个角色时，回显数据使用
	 */
	public SystemRole selectById(Long id);
	
	/**
	 * 根据角色名模糊查询角色，分页展示
	 */
	public PageParameter querySystemRoleByName(PageParameter page,SystemRole systemRole);
	
	/**
	 * 获取所有角色
	 * 添加用户时，展示所有角色
	 */
	public List<SystemRole> getAllRoles();
	/**
	 * 检查角色是否被分配
	 */
	public List<SystemUser> checkRoleUser(SystemRole systemRole);
}
