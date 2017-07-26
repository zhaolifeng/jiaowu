package com.shenlan.dao.system;

import java.util.List;

import com.shenlan.dao.BaseDao;
import org.springframework.stereotype.Repository;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.system.SystemUser;

@Repository
public class SystemUserDao extends BaseDao<SystemUser>
{
	
	/**
	 * 根据用户名检查用户是否存在
	 */
	public SystemUser checkSystemUserByName(String username)
	{
		List<SystemUser> userList = this.getSqlSession().selectList(this.namespace + ".checkSystemUserByName", username);
		return userList.isEmpty() ? null : userList.get(0);
	}
	
	/**
	 * 根据用户名查找用户
	 */
	public SystemUser findSystemUserByName(String username)
	{
		List<SystemUser> userList = this.getSqlSession().selectList(this.namespace + ".findSystemUserByName", username);
		return userList.isEmpty() ? null : userList.get(0);
	}
	
	/**
	 * 添加管理员用户
	 */
	public void addSystemUser(SystemUser systemUser)
	{
		this.getSqlSession().insert(namespace + ".addSystemUser", systemUser);
	}
	
	/**
	 * 删除管理员
	 */
	public void deleteSystemUser(Long id)
	{
		this.getSqlSession().delete(namespace + ".deleteSystemUser", id);
	}
	
	/**
	 * 修改管理员
	 */
	public void updateSystemUser(SystemUser systemUser)
	{
		this.getSqlSession().update(namespace + ".updateSystemUser", systemUser);
	}
	
	/**
	 * 根据用户名模糊查询用户，分页展示
	 */
	public PageParameter querySystemUserByName(PageParameter page,SystemUser systemUser)
	{
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<SystemUser> userList = this.getSqlSession().selectList(namespace + ".querySystemUserByName", systemUser,pbp.getPageBounds());
		pbp.setPageParameter(userList.isEmpty() ? null : userList);
		return page;
	}
}
