package com.shenlan.controller.jwManager;

import com.shenlan.api.jwManager.IJwCourseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.controller.BaseController;
import com.shenlan.domain.bo.JwCourse;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwCourse")
public class JwCourseController extends BaseController {
	
	@Autowired
	private IJwCourseService jwCourseService;

	@ResponseBody
	@RequestMapping(value = "/course", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryManager(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwCourse/courseList");
		PageParameter page = this.getPageParameter(request);
		page=jwCourseService.findCourse(page,null);
		modelAndView.addObject("courses",page.getData());
		this.pageResponse(request,page);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initCourse", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initCampus(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwCourse/course");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/addCourse", method = {RequestMethod.GET, RequestMethod.POST},produces ="text/html;charset=UTF-8")
	public ModelAndView addCampus(JwCourse jwCourse) {
		ModelAndView modelAndView = new ModelAndView("jwCampus/course");
		jwCourseService.addCourse(jwCourse);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/getCourse", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getCampus(JwCourse jwCourse) {
		ModelAndView modelAndView = new ModelAndView("jwCourse/editCourse");
		JwCourse course= jwCourseService.getCourseById(Integer.parseInt(jwCourse.getId() + ""));
		modelAndView.addObject("course",course);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/editCourse", method = {RequestMethod.GET, RequestMethod.POST},produces ="text/html;charset=UTF-8")
	public ModelAndView editCampus(JwCourse jwCourse) {
		ModelAndView modelAndView = new ModelAndView("jwCourse/editCourse");
		jwCourseService.updateCourse(jwCourse);
		return modelAndView;
	}

}
