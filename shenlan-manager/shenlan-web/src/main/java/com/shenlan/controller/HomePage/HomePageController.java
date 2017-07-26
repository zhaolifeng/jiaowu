package com.shenlan.controller.HomePage;

import com.shenlan.controller.BaseController;
import com.shenlan.domain.system.SystemUser;
import net.bull.javamelody.MonitoredWithSpring;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 管理员管理
 */
@MonitoredWithSpring
@Controller
@RequestMapping("/homepage")
public class HomePageController extends BaseController {
	/**
	 * 首页显示
	 */
	@ResponseBody
	@RequestMapping(value = "home", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView home(SystemUser systemUser,ModelMap model)
	{
		SystemUser su = (SystemUser) SecurityUtils.getSubject().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView("home");
		logger.info("*********home*************");
		return modelAndView;
	}
	
	
}
