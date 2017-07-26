package com.shenlan.api.system;

import java.util.List;

import com.shenlan.api.IBaseService;
import com.shenlan.domain.system.SystemMenu;

public interface ISystemMenuService extends IBaseService<SystemMenu> {

	/**
	 * 根据用户查找所有权限(页面按钮权限)
	 */
	public List<SystemMenu> findMenusByUser(String username);
	
	
	/**
	 * 根据用户查找所有菜单
	 */
	public List<SystemMenu> findRootMenusByUser(String username);
	
	/**
	 * 根据用户名从redis中获取菜单列表
	 */
	public List<SystemMenu> getMenuList(String username);
	/**
	 * 根据用户名向redis中存放菜单列表
	 */
	public String setMenuList(String username,List<SystemMenu> list);
	
	/**
	 * 添加菜单
	 */
	public void addSystemMenu(SystemMenu systemMenu);
	
	/**
	 * 删除菜单
	 */
	public void deleteSystemMenu(Long id);
	/**
	 * 修改菜单
	 */
	public void updateSystemMenu(SystemMenu systemMenu);
	
	/**
	 * 查询某个菜单
	 */
	public SystemMenu selectById(Long id);
	
	/**
	 * 获取所有菜单
	 * 添加角色时，展示所有菜单
	 */
	public List<SystemMenu> getAllMenus();
}
