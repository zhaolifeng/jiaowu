package com.shenlan.service.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenlan.api.system.ISystemRoleService;
import com.shenlan.common.utils.Com;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.system.SystemMenuDao;
import com.shenlan.dao.system.SystemRoleDao;
import com.shenlan.dao.system.SystemRoleMenuDao;
import com.shenlan.domain.system.SystemMenu;
import com.shenlan.domain.system.SystemRole;
import com.shenlan.domain.system.SystemRoleMenu;
import com.shenlan.domain.system.SystemUser;
import com.shenlan.service.BaseService;

@Service("systemRoleService")
@Transactional(readOnly = true)
public class SystemRoleService extends BaseService<SystemRole> implements ISystemRoleService
{
	@Autowired
	private SystemRoleDao systemRoleDao; 
	
	@Autowired
	private SystemRoleMenuDao systemRoleMenuDao; 
	
	@Autowired
	private SystemMenuDao systemMenuDao; 
	
	/**
	 * 根据角色名检查角色是否存在
	 */
	@Transactional(readOnly = false)
	public SystemRole checkSystemRoleByName(String name)
	{
		return systemRoleDao.checkSystemRoleByName(name);
	}
	
	/**
	 * 添加角色（有菜单则添加菜单关联表数据）
	 */
	@Transactional(readOnly = false)
	public void addSystemRole(SystemRole systemRole)
	{
		systemRoleDao.addSystemRole(systemRole);//添加主表角色数据
		systemRoleMenuDao.batchSave(this.toRoleMenuList(systemRole));//添加关系表角色菜单数据
	}
	/**
	 * 将systemRole抽取出List<SystemRoleMenu>
	 * @param systemRole
	 * @return
	 */
	protected List<SystemRoleMenu> toRoleMenuList(SystemRole systemRole)
	{
		List<SystemRoleMenu> list = new ArrayList<SystemRoleMenu>();
		
		List<SystemMenu> mList = systemRole.getSystemMenu();
		
		if(mList != null && mList.size() != 0)
		{
			for(SystemMenu sm : mList)
			{
				SystemRoleMenu rm = new SystemRoleMenu();
				rm.setMenuId(sm.getId());
				rm.setRoleId(systemRole.getId());
				list.add(rm);
			}
		}
		
		return list;
	}
	/**
	 * 删除角色（有菜单则删除菜单关联表数据）
	 */
	@Transactional(readOnly = false)
	public void deleteSystemRole(SystemRole systemRole)
	{
		if(Com.hv(systemRole.getId()))
		{
			//systemRoleDao.deleteSystemRole(id);//删除主表角色数据,该方法为物理删除,目前不采用	
			systemRoleDao.updateSystemRole(systemRole);//逻辑删除
			systemRoleMenuDao.delAllRoleMenusByRoleId(systemRole.getId());//删除子表角色菜单关联表数据
		}
		
	}
	/**
	 * 修改角色（有菜单则修改菜单关联表数据）
	 */
	@Transactional(readOnly = false)
	public void updateSystemRole(SystemRole systemRole)
	{
		systemRoleDao.updateSystemRole(systemRole);//修改主表角色数据
		//因目前不能页面修改角色权限关系，所有注释掉
		//先清空子表数据
		systemRoleMenuDao.delAllRoleMenusByRoleId(systemRole.getId());
		//在添加子表数据
		systemRoleMenuDao.batchSave(this.toRoleMenuList(systemRole));//修改子表角色菜单数据
	}
	
	/**
	 * 查询某个角色（有菜单则包含菜单）
	 */
	public SystemRole selectById(Long id)
	{
		SystemRole systemRole = systemRoleDao.selectById(id);
		List<SystemMenu> list = systemMenuDao.findAllMenusByRoleId(id);
		systemRole.setSystemMenu(list);
		return systemRole;
	}
	/**
	 * 根据角色名模糊查询角色，分页展示
	 */
	public PageParameter querySystemRoleByName(PageParameter page,
			SystemRole systemRole) {
	   return systemRoleDao.querySystemRoleByName(page,systemRole);
	}
	
	/**
	 * 获取所有角色
	 * 添加用户时，展示所有角色
	 */
	public List<SystemRole> getAllRoles() 
	{
		return systemRoleDao.getAllRoles();
	}
	/**
	 * 检查角色是否被分配
	 */
	public List<SystemUser> checkRoleUser(SystemRole systemRole)
	{
		return systemRoleDao.checkRoleUser(systemRole);
	}
}
