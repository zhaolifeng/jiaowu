package com.shenlan.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.shenlan.common.exception.SystemException;
import com.shenlan.common.utils.Global;




/**
 * 处理 MVC 异常信息
 * 
 */
public class GenericExceptionResolver extends SimpleMappingExceptionResolver
{

	private static ModelAndView	DEFAULT_MODEL_VIEW	= new ModelAndView();

	private static Logger		LOG					= LoggerFactory.getLogger(GenericExceptionResolver.class);

	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	{
		SystemException exception = SystemException.convertSystemException(ex);
		ModelAndView modelAndView = null;
		if (Global.isAjaxRequest(request))//
		{
			PrintWriter out = null;
			try
			{
				out = response.getWriter();
				out.print(Global.getMessageBody(exception));
			}
			catch (Exception e)
			{
				LOG.error("Write Msg Error");
			}
			finally
			{
				IOUtils.closeQuietly(out);
			}
			modelAndView = DEFAULT_MODEL_VIEW;
		}
		else
		{
			modelAndView = super.doResolveException(request, response, handler, ex);
			if (modelAndView != null)
			{
				Map<String, Object> model = modelAndView.getModel();
				model.put("errorCode", exception.getErrorCode());
				model.put("errorInfo", exception.getErrorInfo());
				model.put("data", exception.getProperties());
				model.put("message", exception.getMessage());
				model.put("stackTrace", exception.getStackTraceAsString()); // 设置异常信息
			}
		}
		if (!"ClientAbortException".equals(ex.getClass().getSimpleName()))
		{
			LOG.error("System Error", ex);
		}
		return modelAndView;
	}
}
