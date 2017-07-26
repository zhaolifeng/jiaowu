package com.shenlan.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.shenlan.common.exception.SystemException;
import com.shenlan.domain.MessageBody;


public class Global
{

	public static boolean isAjaxRequest(HttpServletRequest request)
	{
		return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request)
	{
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(remoteAddr))
		{
			remoteAddr = request.getHeader("X-Forwarded-For");
		}
		else if (StringUtils.isNotBlank(remoteAddr))
		{
			remoteAddr = request.getHeader("Proxy-Client-IP");
		}
		else if (StringUtils.isNotBlank(remoteAddr))
		{
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	public static MessageBody getMessageBody(boolean success)
	{
		MessageBody body = new MessageBody();
		body.setSuccess(success);
		return body;
	}

	public static MessageBody getMessageBody(SystemException exception)
	{
		MessageBody body = new MessageBody();
		body.setSuccess(false);
		body.setErrorCode(exception.getErrorCode().getNumber());
		body.setErrorInfo(exception.getErrorInfo());
		body.setData(exception.getProperties());
		body.setStackTrace(exception.getStackTraceAsString()); // 设置异常信息
		return body;
	}

}
