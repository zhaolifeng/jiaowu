package com.shenlan.controller.system.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import com.shenlan.common.exception.SystemErrorCode;
import com.shenlan.common.exception.SystemException;



public class SystemAuthenticationException extends AuthenticationException
{
	private static final long	serialVersionUID	= 1665817853833917236L;

	private SystemException		exception			= null;

	public static SystemException convertSystemException(Throwable exception)
	{
		if (exception instanceof SystemException)
		{
			SystemException se = (SystemException) exception;
			return se;
		}
		else if (exception instanceof SystemAuthenticationException)
		{
			SystemAuthenticationException se = (SystemAuthenticationException) exception;
			return se.getSystemException();
		}
		else if (exception instanceof UnknownAccountException)
		{
			return new SystemException(exception, SystemErrorCode.NOT_USER_ERROR);
		}
		else if (exception instanceof IncorrectCredentialsException)
		{
			return new SystemException(exception, SystemErrorCode.USER_PASSWORD_ERROR);
		}
		//else if (exception instanceof IncorrectCredentialsException)
		else if (exception instanceof LockedAccountException)//账户不可用
		{
			return new SystemException(exception, SystemErrorCode.USER_PASSWORD_ERROR);
		}
		else
		{
			return new SystemException(exception, SystemErrorCode.SHIRO_LOGIN_FAILURE);
		}
	}

	public SystemAuthenticationException(SystemException exception)
	{
		this.exception = exception;
	}

	public SystemException getSystemException()
	{
		return this.exception;
	}
}
