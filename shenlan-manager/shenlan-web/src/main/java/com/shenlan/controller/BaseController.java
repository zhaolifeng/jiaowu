package com.shenlan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shenlan.domain.system.SystemUser;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shenlan.common.utils.Global;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.common.utils.page.PageSort;
import com.shenlan.domain.MessageBody;


public class BaseController
{
	protected Logger logger	= LoggerFactory.getLogger(getClass());
	/**
	 * 默认ajax消息信息
	 */
	protected final static MessageBody	DEFAULT_MESSAGE_BODY	= Global.getMessageBody(true);
	/**
	 * 获取Http请求
	 *
	 * @return
	 */
	public HttpServletRequest getHttpServletRequest()
	{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();
	}

	/**
	 * 获取分页请求信息
	 * 
	 * @return
	 */
	protected PageParameter getPageParameter(HttpServletRequest request)
	{

		PageParameter page = new PageParameter();
		page.setPage(NumberUtils.toInt((request.getParameter("page")), 1));
		page.setLimit(NumberUtils.toInt((request.getParameter("rows")), 10));
		page.setSort(this.getSortParameter());
		if(request.getParameter("currPage")!=null){
			page.setPage(Integer.parseInt(request.getParameter("currPage")));
		}
		return page;
	}
	/**
	 * 获取分页信息
	 *
	 * @return
	 */
	protected void pageResponse(HttpServletRequest request,PageParameter page)
	{
		if(request.getParameter("currPage")!=null){
			page.setPage(Integer.parseInt(request.getParameter("currPage")));
		}
		request.setAttribute("currPage", page.getPage()+"");
		request.setAttribute("pageSize",page.getLimit()+"");
		request.setAttribute("url",request.getRequestURI()+"");
		request.setAttribute("total", page.getTotalCount() +"");
	}

	/**
	 * 获取分页信息
	 * start第几页
	 * limit每页条数
	 * @return
	 */
	protected PageParameter getPageParameter(Integer start, Integer limit)
	{
		PageParameter page = new PageParameter();
		page.setPage(start);
		page.setLimit(limit);
		page.setSort(this.getSortParameter());
		return page;
	}

	/**
	 * 获取排序信息
	 *
	 * @return
	 */
	protected PageSort getSortParameter()
	{
		HttpServletRequest request = this.getHttpServletRequest();
		PageSort sort = new PageSort();
		sort.setDir(request.getParameter("dir"));
		sort.setSort(request.getParameter("sort"));
		return sort;
	}

	public SystemUser getLoginUser()
	{
		return (SystemUser)SecurityUtils.getSubject().getPrincipal();
	}

	public  String getPropertiesValue(String key){
		Properties properties = new Properties();
		InputStream is = null;
		String configFilePath = "/conf.properties";
		try {
			logger.info("配置文件所在路径：" + getClass().getResource(configFilePath).getPath());
			is = getClass().getResourceAsStream(configFilePath);
			properties.load(is);
		} catch (Exception ex) {
			logger.error("读取配置conf.properties文件出错! 请检查文件路径是否有问题!ex=" + ex.toString());
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception ex) {
				logger.error("读取配置文件conf.properties文件出错! 请检查文件路径是否有问题!ex=" + ex.toString());
			}
		}
		return properties.getProperty(key);
	}

	public void backResult(HttpServletResponse response,String resultInfo)  {
		response.setContentType("text/html;charset=utf-8");
		String result = "<script type=\"text/javascript\" charset=\"utf-8\">" +
				"top.Dialog.alert(\""+resultInfo+"！\", function() {\n" +
				"parent.location.reload();\n" +
				"});" +
				"</script>";
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void backResult(HttpServletResponse response, String resultInfo,String path)  {
		response.setContentType("text/html;charset=utf-8");
		String result = "<script type=\"text/javascript\" charset=\"utf-8\">" +
				"top.Dialog.alert(\""+resultInfo+"！\", function() {\n" +
				"window.location.href='"+path+"';\n" +
				"});" +
				"</script>";
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
