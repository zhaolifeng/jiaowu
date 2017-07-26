package com.shenlan.controller.jwManager;

import com.shenlan.api.jwManager.IJwCampusCourseService;
import com.shenlan.controller.BaseController;
import com.shenlan.domain.bo.JwTeacher;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwCampusSelectCourse")
public class JwCampusSelectCourseController extends BaseController {
	
	@Autowired
	private IJwCampusCourseService jwCampusCourseService;

	@ResponseBody
	@RequestMapping(value = "/initCourse", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initCourse(JwTeacher teacher) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/selectCourse");
//		List<JwCourseTeacher> jwCourseTeachers=jwCourseTeacherService.findCourseTeacher();
//		for(JwCourseTeacher courseTeacher:jwCourseTeachers){
//			if(courseTeacher.getTeacherId()!=null&&!courseTeacher.getTeacherId().equals(teacher.getId())){
//				courseTeacher.setTeacherId(null);
//			}
//		}
//		modelAndView.addObject("jwCourseTeachers",jwCourseTeachers);
//		modelAndView.addObject("teacherId",teacher.getId());
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value = "/teacheAddCourse", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView teacheAddCourse(String teacherId,String [] courseIds) {
		ModelAndView modelAndView = new ModelAndView();
//		List<JwCourseTeacher> courseTeachers=new ArrayList<JwCourseTeacher>();
//		for(String courseId:courseIds){
//			JwCourseTeacher courseTeacher=new JwCourseTeacher();
//			courseTeacher.setTeacherId(Long.parseLong(teacherId));
//			courseTeacher.setCourseId(Long.parseLong(courseId));
//			courseTeachers.add(courseTeacher);
////			jwCourseTeacherService.addCourseTeacher(courseTeacher);
//		}
//		jwCourseTeacherService.addBatchCourseTeacher(courseTeachers);
		return modelAndView;
	}

}
