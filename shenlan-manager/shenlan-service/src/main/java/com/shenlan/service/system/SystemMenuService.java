package com.shenlan.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.shenlan.api.system.ISystemMenuService;
import com.shenlan.dao.system.SystemMenuDao;
import com.shenlan.domain.system.SystemMenu;
import com.shenlan.service.BaseService;

@Service("systemMenuService")
@Transactional(readOnly = true)
public class SystemMenuService extends BaseService<SystemMenu> implements ISystemMenuService
{
	@Autowired
	private SystemMenuDao systemMenuDao; 
	
//	@Autowired
//	private JedisCluster jedisCluster;
	

	/**
	 * 根据用户查找所有权限(页面按钮权限)
	 */
	public List<SystemMenu> findMenusByUser(String username)
	{
		return systemMenuDao.findMenusByUser(username);
	}
	
	
	/**
	 * 根据用户查找所有菜单
	 */
	public List<SystemMenu> findRootMenusByUser(String username)
	{
		return systemMenuDao.findRootMenusByUser(username);
	}
	
	/**
	 * 根据用户名从redis中获取菜单列表
	 */
	public List<SystemMenu> getMenuList(String username)
	{
		String str = null;
		if(str == null || str == "")
		{
			return null;
		}
		List<SystemMenu> uu = JSON.parseArray(str, SystemMenu.class);
		return uu;
		
	}
	/**
	 * 根据用户名向redis中存放菜单列表
	 */
	public String setMenuList(String username,List<SystemMenu> list)
	{
//		return jedisCluster.set(username, JSON.toJSONString(list));
		return  null;
	}
	
	/**
	 * 添加菜单
	 */
	@Transactional(readOnly = false)
	public void addSystemMenu(SystemMenu systemMenu)
	{
		systemMenuDao.addSystemMenu(systemMenu);
	}
	
	/**
	 * 删除菜单
	 */
	@Transactional(readOnly = false)
	public void deleteSystemMenu(Long id)
	{
		systemMenuDao.deleteSystemMenu(id);
	}
	/**
	 * 修改菜单
	 */
	@Transactional(readOnly = false)
	public void updateSystemMenu(SystemMenu systemMenu)
	{
		systemMenuDao.updateSystemMenu(systemMenu);
	}
	
	/**
	 * 查询某个菜单
	 */
	public SystemMenu selectById(Long id) {
		return systemMenuDao.selectById(id);
	}

	/**
	 * 获取所有菜单
	 * 添加角色时，展示所有菜单
	 */
	public List<SystemMenu> getAllMenus()
	{
		return systemMenuDao.getAllMenus();
	}
}
