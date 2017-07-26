package com.shenlan.controller.jwManager;

import javax.servlet.http.HttpServletRequest;

import com.shenlan.api.jwManager.IJwCampusService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.JwCampus;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.shenlan.controller.BaseController;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwCampus")
public class JwCampusController extends BaseController {
	
	@Autowired
	private IJwCampusService jwCampusService;

	@ResponseBody
	@RequestMapping(value = "/campus", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryManager(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwCampus/campusList");
//		List<JwCampus> campuses=jwCampusService.findCampus(0);
		PageParameter page = this.getPageParameter(request);
		page=jwCampusService.findCampus(page,0);
		modelAndView.addObject("campuses",page.getData());
		this.pageResponse(request,page);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initCampus", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initCampus(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwCampus/campus");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/addCampus", method = {RequestMethod.GET, RequestMethod.POST,})
	public ModelAndView addCampus(JwCampus campus) {
		ModelAndView modelAndView = new ModelAndView("jwCampus/campus");
		jwCampusService.addCampus(campus);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/getCampus", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getCampus(JwCampus campus) {
		ModelAndView modelAndView = new ModelAndView("jwCampus/editCampus");
		JwCampus campuse= jwCampusService.getCampusById(Integer.parseInt(campus.getId() + ""));
		modelAndView.addObject("campuse",campuse);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/editCampus", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView editCampus(JwCampus campus) {
		ModelAndView modelAndView = new ModelAndView("jwCampus/editCampus");
		jwCampusService.udpateCampus(campus);
		return modelAndView;
	}

}
