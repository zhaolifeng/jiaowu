package com.shenlan.dao.system;

import java.util.List;

import com.shenlan.dao.BaseDao;
import org.springframework.stereotype.Repository;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.system.SystemRole;
import com.shenlan.domain.system.SystemUser;

@Repository
public class SystemRoleDao extends BaseDao<SystemRole>
{
	/**
	 * 根据角色名检查角色是否存在
	 */
	public SystemRole checkSystemRoleByName(String name)
	{
		List<SystemRole> roleList = this.getSqlSession().selectList(this.namespace + ".checkSystemRoleByName", name);
		return roleList.isEmpty() ? null : roleList.get(0);
	}
	
	/**
	 * 根据用户查找所有角色
	 * 点击修改用户时做回显所有角色使用
	 */
	public List<SystemRole> findAllRolesByUserId(Long id)
	{
		List<SystemRole> roleList = this.getSqlSession().selectList(this.namespace + ".findAllRolesByUserId", id);
		return roleList.isEmpty() ? null : roleList;
	}
	
	/**
	 * 添加角色
	 */
	public void addSystemRole(SystemRole systemRole)
	{
		this.getSqlSession().insert(namespace + ".addSystemRole", systemRole);
	}
	
	/**
	 * 删除角色
	 */
	public void deleteSystemRole(Long id)
	{
		this.getSqlSession().delete(namespace + ".deleteSystemRole", id);
	}
	/**
	 * 修改角色
	 */
	public void updateSystemRole(SystemRole systemRole)
	{
		this.getSqlSession().update(namespace + ".updateSystemRole", systemRole);
	}
	
	/**
	 * 根据角色名模糊查询角色，分页展示
	 */
	public PageParameter querySystemRoleByName(PageParameter page,SystemRole systemRole)
	{
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<SystemRole> roleList = this.getSqlSession().selectList(namespace + ".querySystemRoleByName", systemRole,pbp.getPageBounds());
		pbp.setPageParameter(roleList.isEmpty() ? null : roleList);
		return page;
	}
	
	/**
	 * 获取所有角色
	 * 添加用户时，展示所有角色
	 */
	public List<SystemRole> getAllRoles() 
	{
		List<SystemRole> roleList = this.getSqlSession().selectList(this.namespace + ".getAllRoles", null);
		return roleList.isEmpty() ? null : roleList;
	}
	/**
	 * 检查角色是否被分配
	 */
	public List<SystemUser> checkRoleUser(SystemRole systemRole)
	{
		List<SystemUser> userList = this.getSqlSession().selectList(this.namespace + ".checkRoleUser", systemRole);
		return userList.isEmpty() ? null : userList;
	}
}
