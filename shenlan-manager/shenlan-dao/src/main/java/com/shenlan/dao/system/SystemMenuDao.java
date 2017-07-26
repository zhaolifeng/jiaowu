package com.shenlan.dao.system;

import java.util.List;

import com.shenlan.dao.BaseDao;
import org.springframework.stereotype.Repository;

import com.shenlan.domain.system.SystemMenu;

@Repository
public class SystemMenuDao extends BaseDao<SystemMenu>
{
	
	/**
	 * 根据用户查找所有权限(页面按钮权限)
	 */
	public List<SystemMenu> findMenusByUser(String username)
	{
		List<SystemMenu> menuList = this.getSqlSession().selectList(this.namespace + ".findMenusByUser", username);
		return menuList.isEmpty() ? null : menuList;
	}
	
	/**
	 * 根据用户查找所有菜单
	 */
	public List<SystemMenu> findRootMenusByUser(String username)
	{
		List<SystemMenu> menuList = this.getSqlSession().selectList(this.namespace + ".findRootMenusByUser", username);
		return menuList.isEmpty() ? null : menuList;
	}
	
	/**
	 * 根据角色id查找所有菜单
	 * 点击修改角色时做回显所有菜单使用
	 */
	public List<SystemMenu> findAllMenusByRoleId(Long id)
	{
		List<SystemMenu> menuList = this.getSqlSession().selectList(this.namespace + ".findAllMenusByRoleId", id);
		return menuList.isEmpty() ? null : menuList;
	}
	
	/**
	 * 添加菜单
	 */
	public void addSystemMenu(SystemMenu systemMenu)
	{
		this.getSqlSession().insert(namespace + ".addSystemMenu", systemMenu);
	}
	
	/**
	 * 删除菜单
	 */
	public void deleteSystemMenu(Long id)
	{
		this.getSqlSession().delete(namespace + ".deleteSystemMenu", id);
	}
	/**
	 * 修改菜单
	 */
	public void updateSystemMenu(SystemMenu systemMenu)
	{
		this.getSqlSession().update(namespace + ".updateSystemMenu", systemMenu);
	}
	
	/**
	 * 获取所有菜单
	 * 添加角色时，展示所有菜单
	 */
	public List<SystemMenu> getAllMenus()
	{
		List<SystemMenu> menuList = this.getSqlSession().selectList(this.namespace + ".getAllMenus", null);
		return menuList.isEmpty() ? null : menuList;
	}
}
