package com.shenlan.service.system;

import java.util.ArrayList;
import java.util.List;

import com.shenlan.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenlan.api.system.ISystemUserService;
import com.shenlan.common.utils.Com;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.system.SystemRoleDao;
import com.shenlan.dao.system.SystemRoleUserDao;
import com.shenlan.dao.system.SystemUserDao;
import com.shenlan.domain.system.SystemRole;
import com.shenlan.domain.system.SystemRoleUser;
import com.shenlan.domain.system.SystemUser;

@Service("systemUserService")
@Transactional(readOnly = true)
public class SystemUserService extends BaseService<SystemUser> implements ISystemUserService
{
	@Autowired
	private SystemUserDao systemUserDao; 

	@Autowired
	private SystemRoleUserDao systemRoleUserDao; 
	
	@Autowired
	private SystemRoleDao systemRoleDao; 
	
	/**
	 * 根据用户名检查用户是否存在
	 */
	@Transactional(readOnly = false)
	public SystemUser checkSystemUserByName(String username)
	{
		return systemUserDao.checkSystemUserByName(username);
	}
	
	/**
	 * 根据用户名查找用户
	 */
	@Transactional(readOnly = false)
	public SystemUser findSystemUserByName(String username) {
		return systemUserDao.findSystemUserByName(username);
	}
	/**
	 * 添加管理员用户（有角色则添加角色关联表数据）
	 */
	@Transactional(readOnly = false)
	public void addSystemUser(SystemUser systemUser) {
		systemUserDao.addSystemUser(systemUser);
		systemRoleUserDao.batchSave(this.toRoleUserList(systemUser));

	}
	/**
	 * 将systemUser抽取出List<SystemRoleUser>
	 * @param systemUser
	 * @return
	 */
	protected List<SystemRoleUser> toRoleUserList(SystemUser systemUser)
	{
		List<SystemRoleUser> list = new ArrayList<SystemRoleUser>();
		
		List<SystemRole> rList = systemUser.getSystemRole();
		
		if(rList != null && rList.size() != 0)
		{
			for(SystemRole sr : rList)
			{
				SystemRoleUser ru = new SystemRoleUser();
				ru.setRoleId(sr.getId());
				ru.setUserId(systemUser.getId());
				list.add(ru);
			}
		}
		
		return list;
	}
	/**
	 * 删除管理员（有角色则删除角色关联表数据）
	 */
	@Transactional(readOnly = false)
	public void deleteSystemUser(SystemUser systemUser) {
		if(Com.hv(systemUser.getId()))
		{
			//systemUserDao.deleteSystemUser(id);//该方法为物理删除,目前不采用	
			systemUserDao.updateSystemUser(systemUser);//逻辑删除
			systemRoleUserDao.delAllRoleUsersByUserId(systemUser.getId());
		}
		
	}
	/**
	 * 修改管理员（有角色则修改角色关联表数据）
	 */
	@Transactional(readOnly = false)
	public void updateSystemUser(SystemUser systemUser) {
		systemUserDao.updateSystemUser(systemUser);
		//先清空子表数据
		systemRoleUserDao.delAllRoleUsersByUserId(systemUser.getId());
		//在添加子表数据
		systemRoleUserDao.batchSave(this.toRoleUserList(systemUser));
	}
	/**
	 * 根据用户名模糊查询用户，分页展示
	 */
	public PageParameter querySystemUserByName(PageParameter page,SystemUser systemUser) {
		return systemUserDao.querySystemUserByName(page,systemUser);
	}
	/**
	 * 查询某个用户（有角色则包含角色）
	 * 修改某个用户时，回显数据
	 */
	public SystemUser selectById(Long id) {
		SystemUser systemUser = systemUserDao.selectById(id);
		List<SystemRole> list = systemRoleDao.findAllRolesByUserId(id);
		systemUser.setSystemRole(list);
		return systemUser;
	}
	
	
	
}
