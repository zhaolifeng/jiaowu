package com.shenlan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bull.javamelody.MonitoredWithSpring;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shenlan.api.system.ISystemMenuService;
import com.shenlan.domain.system.SystemMenu;
import com.shenlan.domain.system.SystemUser;

@MonitoredWithSpring
@Controller
@RequestMapping("/")
public class LoginController extends  BaseController{


	@Autowired
	private ISystemMenuService systemMenuService;

	@RequestMapping(value ="login", method=RequestMethod.GET)
	public String login()
	{
		return "login";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value ="login" , method=RequestMethod.POST)
	public ModelAndView login(SystemUser systemUser,HttpSession session, HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView();

		Subject subject = SecurityUtils.getSubject();
		//tbuser.setPwd((Digests.encrypt(tbuser.getPwd())));
		UsernamePasswordToken token = new UsernamePasswordToken(systemUser.getUsername(), systemUser.getPassword());
		token.setRememberMe(true);

		try {
			subject.login(token);
		} catch (DisabledAccountException e){
			modelView.addObject("message", "用户已被禁用");
			e.printStackTrace();
		} catch (UnknownAccountException e){
			modelView.addObject("message", "用户名不存在");
			e.printStackTrace();
		} catch (AuthenticationException e) {
			modelView.addObject("message", "用户名或密码错误");
			e.printStackTrace();
		}

		if(subject.isAuthenticated()){
//			//从后台代码获取国际化信息
//            RequestContext requestContext = new RequestContext(request);
//            modelView.addObject("welcome", requestContext.getMessage("welcome"));
//			//国际化结束
			session.setAttribute("userinfo", systemUser);
			session.setAttribute("userName",systemUser.getUsername());

//			List<SystemMenu> ml = systemMenuService.findRootMenusByUser(
//					systemUser.getUsername());
//			session.setAttribute("menuList",ml);
			SystemUser su = (SystemUser) SecurityUtils.getSubject().getPrincipal();
			List<SystemMenu> menusList = null;
			menusList = systemMenuService.findRootMenusByUser(su.getUsername());
			modelView.addObject("menusList", menusList);
			logger.info("**********************");
			modelView.setViewName("/index");
		}else{
			modelView.setViewName("/login");
		}
		return modelView;
	}

	/**
	 * 退出登录
	 * 若配置了/logout = logout ，则不会再进入改方法，除非/logout = anon
	 * @return
	 */
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public ModelAndView logout() {
		Subject subject = SecurityUtils.getSubject();
		ModelAndView modelAndView = new ModelAndView("login");
		try {
			subject.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();

		}
		return modelAndView;
	}

}
