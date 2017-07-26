package com.shenlan.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shenlan.controller.BaseController;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shenlan.api.Token;
import com.shenlan.api.system.ISystemRoleService;
import com.shenlan.api.system.ISystemUserService;
import com.shenlan.common.utils.Com;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.MessageBody;
import com.shenlan.domain.system.SystemRole;
import com.shenlan.domain.system.SystemUser;
import com.shenlan.domain.system.TreeNode;

/**
 * 管理员管理
 * @author Z
 *
 */
@MonitoredWithSpring
@Controller
@RequestMapping(value = "/systemUser")
public class SystemUserController extends BaseController {
	
	@Autowired
	private ISystemUserService systemUserService;
	
	@Autowired
	private ISystemRoleService systemRoleService;
	
	/**
	 * 管理员界面
	 * @return
	 */
	/*
	@RequestMapping(value ="list", method=RequestMethod.GET)
	public String list() 
	{
		return "systemManage/list";
	}*/
	@RequestMapping(value ="list", method = {RequestMethod.POST, RequestMethod.GET})
	public String list(SystemUser systemUser,HttpServletRequest request,Model model)
	{
		PageParameter page = this.getPageParameter(request);
		//page.setLimit(2);
		page = systemUserService.querySystemUserByName(page, systemUser);
		model.addAttribute("pageList", page);
		model.addAttribute("systemUser", systemUser);
		this.pageResponse(request,page);
		//request.setAttribute("url","/systemUser/list");
		return "systemManage/list";
	}
	
	/**
	 * 添加管理员界面
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value ="addManager",  method = {RequestMethod.POST, RequestMethod.GET})
	public String addManager(SystemUser systemUser,Model model) 
	{
		List<SystemRole> list = systemRoleService.getAllRoles();
		systemUser.setSystemRole(list);
		
		List<TreeNode> lTree = new ArrayList<TreeNode>();
		
		TreeNode root = new TreeNode();
		root.setId(-1L);
		root.setParentId(-2L);
		root.setOpen(true);
		root.setName("角色列表");
		lTree.add(root);
		
		for(SystemRole l : list)
		{
			TreeNode treeNode = new TreeNode();
			treeNode.setId(l.getId());
			treeNode.setParentId(-1L);
			treeNode.setName(l.getName());
			lTree.add(treeNode);
		}
		model.addAttribute("systemUser", systemUser);
		model.addAttribute("lTree", JSON.toJSON(lTree).toString().replace("parentId", "pId"));
		return "systemManage/addManager";
	}
	
	/**
	 * 修改管理员界面
	 * @return
	 */
	@RequestMapping(value ="updateManager",  method = {RequestMethod.POST, RequestMethod.GET})
	public String updateManager(SystemUser systemUser,Model model) 
	{
		systemUser = systemUserService.selectById(systemUser.getId());
//		//修改只能选中一个角色
//		if(systemUser.getSystemRole() != null && systemUser.getSystemRole().size()!=0)
//		{
//			if(systemUser.getSystemRole().get(0) != null)
//			{
//				systemUser.setSrId(systemUser.getSystemRole().get(0).getId());
//			}
//		}
		
		List<SystemRole> list = systemRoleService.getAllRoles();
		//------------------------------------
		List<TreeNode> lTree = new ArrayList<TreeNode>();
		
		TreeNode root = new TreeNode();
		root.setId(-1L);
		root.setParentId(-2L);
		root.setOpen(true);
		root.setName("角色列表");
		lTree.add(root);
		
		for(SystemRole l : list)
		{
			TreeNode treeNode = new TreeNode();
			treeNode.setId(l.getId());
			treeNode.setParentId(-1L);
			treeNode.setName(l.getName());
			for(SystemRole r : systemUser.getSystemRole())
			{
				if(l.getId().equals(r.getId()))
				{
					treeNode.setChecked(true);
				}
			}
			lTree.add(treeNode);
		}
		//---------------------------------------
		model.addAttribute("systemUser", systemUser);
		model.addAttribute("systemRole", list);//返回全部角色
		model.addAttribute("lTree", JSON.toJSON(lTree).toString().replace("parentId", "pId"));
		return "systemManage/updateManager";
	}
	
	/**
	 * 分页模糊查询管理员信息
	 */
	/*
	@ResponseBody
	@RequestMapping(value = "queryManager", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryManager(SystemUser systemUser,HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView("systemManage/managerContent");
		PageParameter page = this.getPageParameter(request);
		//page.setLimit(2);
		page = systemUserService.querySystemUserByName(page, systemUser);
		modelAndView.addObject("pageList", page);
		this.pageResponse(request,page);
//		logger.info("url"+request.getRequestURI()+"");
//		logger.info("currPage"+page.getPage());
//		logger.info("pageSize"+page.getLimit());
//		logger.info("total"+page.getTotalCount());
		return modelAndView;
	}
	*/
	/**
	 * 添加管理员
	 */
	//@Token(remove = true)
	@ResponseBody
	@RequestMapping(value = "saveManager", method = {RequestMethod.POST, RequestMethod.GET})
	public MessageBody saveManager(SystemUser systemUser)
	{
		
		SystemUser user = systemUserService.checkSystemUserByName(systemUser.getUsername());
		if(Com.hv(user))
		{
			MessageBody body = new MessageBody();
			body.setSuccess(false);
			return body;
		}
		
		systemUser.setIsValid(1);
		systemUser.setUserNo(systemUser.getUsername()+"001");
		//目前仅是单选某个角色，通过srid来控制
		List<SystemRole> list = new ArrayList<SystemRole>();
		//SystemRole role = new SystemRole();
		//role.setId(systemUser.getSrId());
		//role.setId(1L);//强制绑定超级管理员
		//list.add(role);
		//---------------
		String[] roleId = systemUser.getRoleStr().split(",");
		for(String rId :roleId)
		{
			SystemRole role = new SystemRole();
			role.setId(Long.parseLong(rId));
			list.add(role);
		}
		//---------------
		systemUser.setSystemRole(list);
		//---------------------------
		
		systemUserService.addSystemUser(systemUser);
		return DEFAULT_MESSAGE_BODY;
	}

	/**
	 * 删除管理员
	 */
	@ResponseBody
	@RequestMapping(value = "deleteManager", method = {RequestMethod.POST, RequestMethod.GET})
	public MessageBody deleteManager(SystemUser systemUser)
	{
		systemUser.setIsValid(0);//逻辑删除
		systemUserService.deleteSystemUser(systemUser);
		return DEFAULT_MESSAGE_BODY;
	}
	
	/**
	 * 修改管理员
	 */
	@ResponseBody
	@RequestMapping(value = "updManager", method = {RequestMethod.POST, RequestMethod.GET})
	public MessageBody updManager(SystemUser systemUser)
	{
		SystemUser user = systemUserService.checkSystemUserByName(systemUser.getUsername());
		if(Com.hv(user) && !user.getId().equals(systemUser.getId()))
		{
			MessageBody body = new MessageBody();
			body.setSuccess(false);
			return body;
		}
		
		//目前仅是单选某个角色，通过srid来控制
		List<SystemRole> list = new ArrayList<SystemRole>();
		//SystemRole role = new SystemRole();
		//role.setId(systemUser.getSrId());
		//role.setId(1L);//强制绑定超级管理员
		//list.add(role);
		//---------------
		String[] roleId = systemUser.getRoleStr().split(",");
		for(String rId :roleId)
		{
			SystemRole role = new SystemRole();
			role.setId(Long.parseLong(rId));
			list.add(role);
		}
		//---------------
		systemUser.setSystemRole(list);
		
		systemUserService.updateSystemUser(systemUser);
		return DEFAULT_MESSAGE_BODY;
	}
	
}
