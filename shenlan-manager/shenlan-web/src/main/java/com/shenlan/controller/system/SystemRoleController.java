package com.shenlan.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shenlan.api.Token;
import com.shenlan.domain.system.SystemUser;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenlan.api.system.ISystemMenuService;
import com.shenlan.api.system.ISystemRoleService;
import com.shenlan.common.utils.Com;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.controller.BaseController;
import com.shenlan.domain.MessageBody;
import com.shenlan.domain.system.SystemMenu;
import com.shenlan.domain.system.SystemRole;
import com.shenlan.domain.system.TreeNode;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统角色
 * @author Z
 *
 */
@MonitoredWithSpring
@Controller
@RequestMapping(value = "/systemRole")
public class SystemRoleController extends BaseController{

	@Autowired
	private ISystemRoleService systemRoleService;
	
	@Autowired
	private ISystemMenuService systemMenuService;
	
	/**
	 * 角色界面
	 * @return
	 */
//	@RequestMapping(value ="roleList", method=RequestMethod.GET)
//	public String roleList()
//	{
//		return "systemManage/roleList";
//	}
	@RequestMapping(value ="roleList",  method = {RequestMethod.POST, RequestMethod.GET})
	public String roleList(SystemRole systemRole,HttpServletRequest request,Model model)
	{
		PageParameter page = this.getPageParameter(request);
		page = systemRoleService.querySystemRoleByName(page, systemRole);
		model.addAttribute("pageList", page);
		model.addAttribute("systemRole", systemRole);
		this.pageResponse(request,page);
		return "systemManage/roleList";
	}
	
	/**
	 * 添加角色界面
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value ="addRole",  method = {RequestMethod.POST, RequestMethod.GET})
	public String addRole(SystemRole systemRole,Model model) 
	{
		
		List<SystemMenu> list = systemMenuService.getAllMenus();
		systemRole.setSystemMenu(list);
		
		List<TreeNode> lTree = new ArrayList<TreeNode>();
		
		TreeNode root = new TreeNode();
		root.setId(-1L);
		root.setParentId(-2L);
		root.setOpen(true);
		root.setName("权限列表");
		lTree.add(root);
		
		for(SystemMenu l : list)
		{
			TreeNode treeNode = new TreeNode();
			treeNode.setId(l.getId());
			treeNode.setParentId(Long.valueOf(l.getParentId()));
			treeNode.setName(l.getName());
			lTree.add(treeNode);
		}
		model.addAttribute("systemRole", systemRole);
		model.addAttribute("lTree", JSON.toJSON(lTree).toString().replace("parentId", "pId"));
		
		
		return "systemManage/addRole";
	}
	
	/**
	 * 修改角色界面
	 * @return
	 */
	@RequestMapping(value ="updateRole",  method = {RequestMethod.POST, RequestMethod.GET})
	public String updateRole(SystemRole systemRole,Model model) 
	{
		systemRole = systemRoleService.selectById(systemRole.getId());
		
		List<SystemMenu> list = systemMenuService.getAllMenus();
		//systemRole.setSystemMenu(list);
		
		List<TreeNode> lTree = new ArrayList<TreeNode>();
		
		TreeNode root = new TreeNode();
		root.setId(-1L);
		root.setParentId(-2L);
		root.setOpen(true);
		root.setName("权限列表");
		lTree.add(root);
		
		for(SystemMenu l : list)
		{
			TreeNode treeNode = new TreeNode();
			treeNode.setId(l.getId());
			treeNode.setParentId(Long.valueOf(l.getParentId()));
			treeNode.setName(l.getName());
			treeNode.setOpen(true);
			for(SystemMenu m : systemRole.getSystemMenu())
			{
				if(l.getId().equals(m.getId()))
				{
					treeNode.setChecked(true);
				}
			}
			lTree.add(treeNode);
		}
		
		model.addAttribute("systemRole", systemRole);
		model.addAttribute("lTree", JSON.toJSON(lTree).toString().replace("parentId", "pId"));
		return "systemManage/updateRole";
	}
	
	/**
	 * 查询角色界面
	 * @return
	 */
	@RequestMapping(value ="viewRole",  method = {RequestMethod.POST, RequestMethod.GET})
	public String viewRole(SystemRole systemRole,Model model) 
	{
		systemRole = systemRoleService.selectById(systemRole.getId());
		
		List<SystemMenu> list = systemRole.getSystemMenu();
		
		List<TreeNode> lTree = new ArrayList<TreeNode>();
		
		TreeNode root = new TreeNode();
		root.setId(-1L);
		root.setParentId(-2L);
		root.setOpen(true);
		root.setName("权限列表");
		lTree.add(root);
		
		for(SystemMenu l : list)
		{
			TreeNode treeNode = new TreeNode();
			treeNode.setId(l.getId());
			treeNode.setParentId(Long.valueOf(l.getParentId()));
			treeNode.setName(l.getName());
			treeNode.setOpen(true);
			lTree.add(treeNode);
		}
		
		model.addAttribute("systemRole", systemRole);
		model.addAttribute("lTree", JSON.toJSON(lTree).toString().replace("parentId", "pId"));
		return "systemManage/viewRole";
	}
	/**
	 * 分页模糊查询角色信息
	 */
//	@ResponseBody
//	@RequestMapping(value = "queryRole", method = {RequestMethod.POST, RequestMethod.GET})
//	public ModelAndView queryRole(SystemRole systemRole,HttpServletRequest request)
//	{
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView = new ModelAndView("systemManage/roleContent");
//
//		PageParameter page = this.getPageParameter(request);
//		page = systemRoleService.querySystemRoleByName(page, systemRole);
//
//		modelAndView.addObject("pageList",page);
//		return modelAndView;
//	}
	
	/**
	 * 添加角色
	 */
	//@Token(remove = true)
	@ResponseBody
	@RequestMapping(value = "saveRole", method = {RequestMethod.POST, RequestMethod.GET})
	public MessageBody saveRole(SystemRole systemRole)
	{
		SystemRole role = systemRoleService.checkSystemRoleByName(systemRole.getName());
		if(Com.hv(role))
		{
			MessageBody body = new MessageBody();
			body.setSuccess(false);
			return body;
		}
		
		systemRole.setIsValid(1);
		systemRole.setRoleNo(systemRole.getName()+"001");
		//目前添加全部的权限，不可配
		//List<SystemMenu> list = systemMenuService.getAllMenus();
		//---------------
		List<SystemMenu> list = new ArrayList<SystemMenu>();
		String[] menuId = systemRole.getMenuStr().split(",");
		for(String mId :menuId)
		{
			SystemMenu menu = new SystemMenu();
			menu.setId(Long.parseLong(mId));
			list.add(menu);
		}
		systemRole.setSystemMenu(list);
		//----------------------------
		systemRoleService.addSystemRole(systemRole);
		
		return DEFAULT_MESSAGE_BODY;
	}
	
	/**
	 * 删除角色
	 */
	@ResponseBody
	@RequestMapping(value = "deleteRole", method = {RequestMethod.POST, RequestMethod.GET})
	public MessageBody deleteRole(SystemRole systemRole)
	{
		systemRole.setIsValid(0);//逻辑删除
		systemRoleService.deleteSystemRole(systemRole);
		return DEFAULT_MESSAGE_BODY;
	}
	
	/**
	 * 修改角色
	 */
	@ResponseBody
	@RequestMapping(value = "updRole", method = {RequestMethod.POST, RequestMethod.GET})
	public MessageBody updRole(SystemRole systemRole)
	{
		SystemRole role = systemRoleService.checkSystemRoleByName(systemRole.getName());
		if(Com.hv(role) && !role.getId().equals(systemRole.getId()))
		{
			MessageBody body = new MessageBody();
			body.setSuccess(false);
			return body;
		}
		
		List<SystemMenu> list = new ArrayList<SystemMenu>();
		String[] menuId = systemRole.getMenuStr().split(",");
		for(String mId :menuId)
		{
			SystemMenu menu = new SystemMenu();
			menu.setId(Long.parseLong(mId));
			list.add(menu);
		}
		systemRole.setSystemMenu(list);
		systemRoleService.updateSystemRole(systemRole);
		return DEFAULT_MESSAGE_BODY;
	}
	
	/**
	 * 检查角色是否被分配
	 */
	@ResponseBody
	@RequestMapping(value = "checkRoleUser", method = {RequestMethod.POST, RequestMethod.GET})
	public MessageBody checkRoleUser(SystemRole systemRole)
	{
		List<SystemUser> list = systemRoleService.checkRoleUser(systemRole);
		MessageBody body = new MessageBody();
		body.setSuccess(false);
		
		StringBuffer userList = new StringBuffer(); 
		String str = "";
		if(Com.hv(list))
		{
			body.setSuccess(true);
			for(SystemUser user : list)
			{
				userList.append(user.getUsername()+",");
			}
			
			if(list.size()>3)
			{
				str = list.get(0).getUsername()+","+list.get(1).getUsername()+","+list.get(2).getUsername()+"...";
			}else{
				str = userList.toString().substring(0, userList.toString().length()-1);
			}
			body.setData(str);
		}
		
		return body;
	}
}
