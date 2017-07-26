package com.shenlan.controller.system;

import java.util.ArrayList;
import java.util.List;

import net.bull.javamelody.MonitoredWithSpring;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenlan.api.system.ISystemMenuService;
import com.shenlan.common.utils.Global;
import com.shenlan.controller.BaseController;
import com.shenlan.domain.MessageBody;
import com.shenlan.domain.system.SystemMenu;
import com.shenlan.domain.system.SystemUser;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统菜单
 * @author Z
 *
 */
@MonitoredWithSpring
@Controller
@RequestMapping(value = "/systemMenu")
public class SystemMenuController extends BaseController{

	@Autowired
	private ISystemMenuService systemMenuService;
	
	/**
	 * 查询菜单
	 */
	@ResponseBody
	@RequestMapping(value = "getMenus")
	public MessageBody  getMenus(SystemUser systemUser,Model model)
	{
		MessageBody mb = Global.getMessageBody(true);
		
		SystemUser su = (SystemUser) SecurityUtils.getSubject().getPrincipal();
		//从redis中获取 data
		List<SystemMenu> list = new ArrayList<SystemMenu>();
		list = systemMenuService.getMenuList(su.getUsername());
		
		//如果data == null 则查询数据库，并把查询结果存放到redis中
		if(list == null || list.isEmpty())
		{
			list = systemMenuService.findRootMenusByUser(
					su.getUsername());
			systemMenuService.setMenuList(su.getUsername(), list);
		}
		mb.setData(list);
		return mb;
	}

	/**
	 * 查询菜单
	 */
	@ResponseBody
	@RequestMapping(value = "getMenu", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getMenu(SystemUser systemUser,ModelMap model)
	{
		SystemUser su = (SystemUser) SecurityUtils.getSubject().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView("common/menus");
		List<SystemMenu> menusList = new ArrayList<SystemMenu>();
		menusList = systemMenuService.findRootMenusByUser(su.getUsername());
		modelAndView.addObject("menusList",menusList);
		logger.info("**********************");
		return modelAndView;
	}
	
	/**
	 * 查询菜单
	 */
	@ResponseBody
	@RequestMapping(value = "getMenu2", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getMenu2(SystemMenu systemMenu,ModelMap model)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView("common/menus02");
		
		SystemUser su = (SystemUser) SecurityUtils.getSubject().getPrincipal();
		//从redis中获取 data
		List<SystemMenu> list = new ArrayList<SystemMenu>();
//		list = systemMenuService.getMenuList(su.getUsername());
		
		//如果data == null 则查询数据库，并把查询结果存放到redis中
		if(list == null || list.isEmpty())
		{
			list = systemMenuService.findRootMenusByUser(su.getUsername());
//			systemMenuService.setMenuList(su.getUsername(), list);
		}
		//-------------------------------
		List<SystemMenu> l = new ArrayList<SystemMenu>();
		for(SystemMenu sm:list)
		{
			sm.setUicon(sm.getIcon());//循环依次把 未选择的图片 放到页面展示的ucion中
			if(sm.getId().toString().trim().equals(systemMenu.getId().toString().trim()))//选中的菜单id匹配 选中的图片
			{
				sm.setUicon(sm.getSicon());
			}
			l.add(sm);
		}
		//-------------------------------
		modelAndView.addObject("menuList",l);
		modelAndView.addObject("menuId",systemMenu.getId());
		return modelAndView;
	}
}
