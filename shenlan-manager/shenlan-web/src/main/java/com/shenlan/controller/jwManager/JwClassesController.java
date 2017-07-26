package com.shenlan.controller.jwManager;

import com.alibaba.fastjson.JSON;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.controller.BaseController;
import com.shenlan.api.jwManager.*;
import com.shenlan.domain.bo.*;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwClasses")
public class JwClassesController extends BaseController {
	@Autowired
	private IJwClassRoomService jwClassRoomService;
	@Autowired
	private IJwCampusService jwCampusService;
	@Autowired
	private IJwCourseService jwCourseService;
	@Autowired
	private IJwClassesService jwClassesService;
	@Autowired
	private IJwTeacherService jwTeacherService;
	@Autowired
	private IJwClassesConfigService jwClassesConfigService;


	@ResponseBody
	@RequestMapping(value = "/classes", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryClassRooms(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwClasses/classesList");
//		List<JwClasses> classes=jwClassesService.findClasses();
//		modelAndView.addObject("classes",classes);
		List<JwCampus> jwCampuses=jwCampusService.findCampus(Integer.parseInt("1"));
		modelAndView.addObject("jwCampuses",jwCampuses);
		PageParameter page = this.getPageParameter(request);
		page=jwClassesService.findClasses(page, null);
		modelAndView.addObject("classes",page.getData());
		this.pageResponse(request,page);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initClasses", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initClassRoom() {
		ModelAndView modelAndView = new ModelAndView("jwClasses/classes");
		List<JwCampus> jwCampuses=jwCampusService.findCampus(Integer.parseInt("1"));
		modelAndView.addObject("jwCampuses",jwCampuses);
		List<JwCourse> jwCourses=jwCourseService.findCourse();
		modelAndView.addObject("jwCourses",jwCourses);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/addClasses", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addClasses(HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView("jwClasses/classes");
		String classesName = req.getParameter("classesName");
		String campusId = req.getParameter("campusId");
		String courseId = req.getParameter("courseId");
		String fee = req.getParameter("fee");
		JwClasses classes=new JwClasses();
		classes.setClassesName(classesName);
		classes.setCampusId(Integer.parseInt(campusId));
		classes.setCourseId(Integer.parseInt(courseId));
		if(fee !=null){
			BigDecimal bigDecimal=new BigDecimal(fee);
			bigDecimal.toString();
			classes.setFee(bigDecimal.doubleValue());
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		jwClassesService.addClasses(classes);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initClassesConfig", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initClassesConfig(JwClasses classes) {
		ModelAndView modelAndView = new ModelAndView("jwClasses/configClasses");
		classes=jwClassesService.getClassesById(Integer.parseInt(classes.getId() + ""));
		modelAndView.addObject("classes",classes);
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value = "/selectRoom", method = {RequestMethod.GET, RequestMethod.POST},produces ="text/html;charset=UTF-8")
	public String  selectTeacherRoom(JwClasses classes,JwClassesConfigure jwClassesConfigure,String startTimeParam,String endTimeParam) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		try {
			jwClassesConfigure.setStartTime(sdf.parse(startTimeParam));
			jwClassesConfigure.setEndTime(sdf.parse(endTimeParam));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jwClassesConfigure.setCampusId(null);
		jwClassesConfigure.setClassesId(null);
		List<JwClassesConfigure> classesConfigures=jwClassesConfigService.findClassesConfig(jwClassesConfigure);
		Map conditons=new HashMap();
		conditons.put("campusId",classes.getCampusId());
		conditons.put("courseId",classes.getCourseId());
		//排除离职的老师
		conditons.put("teacherStatus",0);
		List<JwTeacher> teachers= jwTeacherService.findTeacherByCondtion(conditons);

		JwClassRoom classRoomParam =new JwClassRoom();
		classRoomParam.setCampusId(classes.getCampusId());
		classRoomParam.setStatus(0);
		List<JwClassRoom> classRooms=jwClassRoomService.findClassRooms(classRoomParam);

		Map<String, List> resourse = new HashMap<String, List>();

		List<JwTeacher> newTeachers= new ArrayList<JwTeacher>();
		List<JwClassRoom> newClassRooms= new ArrayList<JwClassRoom>();

		if(classesConfigures.size()==0){
			resourse.put("teachers", teachers);
			resourse.put("classRooms", classRooms);
		}else{
			for(JwTeacher teacher:teachers){
				boolean flag=false;
				for(JwClassesConfigure classesConfigure:classesConfigures){
					if((classesConfigure.getTeacherId()+"").equals(teacher.getId()+"")){
						flag=true;
					}
				}
				if(!flag){
					newTeachers.add(teacher);
				}
			}
			for(JwClassRoom classRoom:classRooms){
				boolean flag=false;
				for(JwClassesConfigure classesConfigure:classesConfigures){
					if((classesConfigure.getClassRoomId()+"").equals(classRoom.getId()+"")){
						flag=true;
					}
				}
				if(!flag){
					newClassRooms.add(classRoom);
				}
			}
			resourse.put("teachers", newTeachers);
			resourse.put("classRooms", newClassRooms);

		}

		logger.info("===老师教室资源===" + JSON.toJSONString(resourse));
		return JSON.toJSONString(resourse);
	}


	@ResponseBody
	@RequestMapping(value = "/addClassesConfig", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addClassesConfig(JwClassesConfigure jwClassesConfigure,String startTimeParam,String endTimeParam) {
		ModelAndView modelAndView = new ModelAndView("jwClasses/configClasses");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		try {
			jwClassesConfigure.setStartTime(sdf.parse(startTimeParam));
			jwClassesConfigure.setEndTime(sdf.parse(endTimeParam));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jwClassesConfigService.addClassesConfig(jwClassesConfigure);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/classesDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView classesDetail(JwClasses classes) {
		ModelAndView modelAndView = new ModelAndView("jwClasses/classesDetail");
		classes=jwClassesService.getClassesById(Integer.parseInt(classes.getId() + ""));
		JwClassesConfigure jwClassesConfigure=new JwClassesConfigure();
		jwClassesConfigure.setClassesId(Integer.parseInt(classes.getId()+""));
		List<JwClassesConfigure> classesConfigures=jwClassesConfigService.findClassesConfig(jwClassesConfigure);
		JwCourse course=jwCourseService.getCourseById(classes.getCourseId());
		JwCampus campus=jwCampusService.getCampusById(classes.getCampusId());
		JwClassRoom classRoom=new JwClassRoom();
		classRoom.setCampusId(classes.getCampusId());
		List<JwClassRoom> classRooms=jwClassRoomService.findClassRooms(classRoom);
		Map conditons=new HashMap();
		conditons.put("campusId",classes.getCampusId());
		conditons.put("courseId",classes.getCourseId());
		List<JwTeacher> teachers= jwTeacherService.findTeacherByCondtion(conditons);
		modelAndView.addObject("classes",classes);
		modelAndView.addObject("classesConfigures",classesConfigures);
		modelAndView.addObject("course",course);
		modelAndView.addObject("campus",campus);
		modelAndView.addObject("classRooms",classRooms);
		modelAndView.addObject("teachers",teachers);
		return modelAndView;
	}


}
