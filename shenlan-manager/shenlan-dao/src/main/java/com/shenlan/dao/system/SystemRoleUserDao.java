package com.shenlan.dao.system;

import com.shenlan.dao.BaseDao;
import org.springframework.stereotype.Repository;

import com.shenlan.domain.system.SystemRoleUser;

@Repository
public class SystemRoleUserDao extends BaseDao<SystemRoleUser>
{

	/**
	 * 根据用户id删除所有角色关联表信息
	 */
	public void delAllRoleUsersByUserId(Long id)
	{
		this.getSqlSession().delete(this.namespace + ".delAllRoleUsersByUserId", id);
		
	}
}