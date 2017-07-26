package com.shenlan.controller.system.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;


import com.shenlan.api.system.ISystemMenuService;
import com.shenlan.api.system.ISystemUserService;
import com.shenlan.domain.system.SystemMenu;
import com.shenlan.domain.system.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("shiroRealm")
public class ShiroRealm extends AuthorizingRealm 
{
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private ISystemMenuService systemMenuService;
	
	public ShiroRealm() {
		super();
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SystemUser tbuser = (SystemUser) getAvailablePrincipal(principals); //缓存获取方式
		//TBUser tbuser = ((TBUser)principals.getPrimaryPrincipal());
		List<SystemMenu> menusList = null;
		if(tbuser != null)
		{
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			menusList = systemMenuService.findMenusByUser(tbuser.getUsername());
			for(SystemMenu sm : menusList)
			{
				info.addStringPermission(sm.getPermission());
			}
			
			return info;
		}
		return null;
	}

	/**
	 * 验证
	 * 把登陆用户的密码加密后与根据用户查的密码进行比较
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken) throws AuthenticationException
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationtoken;
		//user.setPassword(EncryptUtils.encryptMD5("admin"));
		SystemUser systemUser = null;

		systemUser = systemUserService.findSystemUserByName(token.getUsername());
//		systemUser.setUsername(token.getUsername());
//		systemUser.setPassword(new String(token.getPassword()));
	    if(systemUser==null)
	    {
	    	throw new UnknownAccountException();
	    }
		if(systemUser!=null)
		{
			if ("0".equals(systemUser.getIsValid().toString()))
			{
				throw new DisabledAccountException();
			}
			return new SimpleAuthenticationInfo(systemUser,
					systemUser.getPassword(), getName());
		}


		return null;
	}

	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal)
	{
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	
}
