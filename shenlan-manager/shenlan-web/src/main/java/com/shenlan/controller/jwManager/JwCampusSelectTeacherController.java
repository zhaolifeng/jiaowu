package com.shenlan.controller.jwManager;

import com.shenlan.api.jwManager.IJwCampusTeacherService;
import com.shenlan.controller.BaseController;
import com.shenlan.domain.bo.JwCampus;
import com.shenlan.domain.bo.JwCampusTeacher;
import com.shenlan.domain.bo.JwCourseTeacher;
import com.shenlan.service.jwManager.JwCourseTeacherService;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwCampusSelectCourse")
public class JwCampusSelectTeacherController extends BaseController {
	
	@Autowired
	private IJwCampusTeacherService jwCampusTeacherService;

	@Autowired
	private JwCourseTeacherService jwCourseTeacherService;

	@ResponseBody
	@RequestMapping(value = "/initTeacher", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initTeacher(JwCampus campus) {
		ModelAndView modelAndView = new ModelAndView("jwCampus/selectTeacher");
		List<String> teacherIds=jwCampusTeacherService.findCampusTeacher(campus.getId());
		List<JwCourseTeacher> jwCourseTeachers=jwCourseTeacherService.findCourseTeacherName();
		for(JwCourseTeacher courseTeacher:jwCourseTeachers){
			if(teacherIds.contains(courseTeacher.getTeacherId()+"")){
				courseTeacher.setSelected(true);
			}else {
				courseTeacher.setSelected(false);
			}
		}
		modelAndView.addObject("campusId",campus.getId());
		modelAndView.addObject("jwCourseTeachers",jwCourseTeachers);
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value = "/campusAddteacher", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView campusAddteacher(String campusId,String [] teacherIds) {
		ModelAndView modelAndView = new ModelAndView();
		List<JwCampusTeacher> campusTeachers=new ArrayList<JwCampusTeacher>();
		for(String teacherId:teacherIds){
			JwCampusTeacher campusTeacher=new JwCampusTeacher();
			campusTeacher.setTeacherId(Integer.parseInt(teacherId));
			campusTeacher.setCampusId(Integer.parseInt(campusId));
			campusTeachers.add(campusTeacher);
		}
		jwCampusTeacherService.deletBatchCampusTeacher(Long.parseLong(campusId));
		jwCampusTeacherService.addBatchCampusTeacher(campusTeachers);
		return modelAndView;
	}

}
