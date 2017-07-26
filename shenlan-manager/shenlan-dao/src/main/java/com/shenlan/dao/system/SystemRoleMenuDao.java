package com.shenlan.dao.system;

import com.shenlan.dao.BaseDao;
import org.springframework.stereotype.Repository;

import com.shenlan.domain.system.SystemRoleMenu;


@Repository
public class SystemRoleMenuDao extends BaseDao<SystemRoleMenu>
{
	
	/**
	 * 根据角色id删除所有菜单关联表信息
	 */
	public void delAllRoleMenusByRoleId(Long id)
	{
		this.getSqlSession().delete(this.namespace + ".delAllRoleMenusByRoleId", id);
		
	}
}