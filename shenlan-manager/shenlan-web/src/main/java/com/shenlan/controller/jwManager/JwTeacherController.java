package com.shenlan.controller.jwManager;

import com.shenlan.common.util.StringHelperTools;
import com.shenlan.common.utils.excel.ExcelCreate;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.controller.BaseController;
import com.shenlan.service.jwManager.JwCourseTeacherService;
import com.shenlan.api.jwManager.IJwCampusService;
import com.shenlan.api.jwManager.IJwClassesService;
import com.shenlan.api.jwManager.IJwTeacherAttendanceService;
import com.shenlan.api.jwManager.IJwTeacherService;
import com.shenlan.domain.bo.*;
import com.shenlan.service.jwManager.JwTeacherService;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwTeacher")
public class JwTeacherController extends BaseController {
	
	@Autowired
	private IJwTeacherService jwTeacherService;
	@Autowired
	private JwCourseTeacherService jwCourseTeacherService;
	@Autowired
	private IJwCampusService jwCampusService;
	@Autowired
	private IJwClassesService jwClassesService;
	@Autowired
	private IJwTeacherAttendanceService jwTeacherAttendanceService;




	@ResponseBody
	@RequestMapping(value = "/teacher", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryManager(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/teacherList");
		PageParameter page = this.getPageParameter(request);
		page=jwTeacherService.findTeaches(page,null);
		modelAndView.addObject("teachers",page.getData());
		this.pageResponse(request,page);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initTeacher", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initTeacher(JwTeacher teacher) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/teacher");
//		JwCourseTeacher courseTeacher=new JwCourseTeacher();
//		courseTeacher.setTeacherId(teacher.getId());
//		List<JwCourseTeacher> jwCourseTeachers=jwCourseTeacherService.findCourseTeacher(courseTeacher);
//		if(teacher.getId()!=null){
//			teacher=jwTeacherService.getTeacheById(Integer.parseInt(teacher.getId()+""));
//			modelAndView.addObject("teacher",teacher);
//		}
//		modelAndView.addObject("jwCourseTeachers",jwCourseTeachers);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/addTeacher", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addCampus(JwTeacher teacher,String entrytimeParam) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/teacher");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		try {
			teacher.setEntrytime(sdf.parse(entrytimeParam));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jwTeacherService.addTeacher(teacher);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/getTeacher", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getCampus(JwTeacher teacher) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/editTeacher");
		JwTeacher teachers= jwTeacherService.getTeacheById(Integer.parseInt(teacher.getId() + ""));
//		List<JwCourseTeacher> jwCourseTeachers=jwCourseTeacherService.findCourseTeacher();
//		modelAndView.addObject("jwCourseTeachers",jwCourseTeachers);
		modelAndView.addObject("teachers",teachers);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/editTeacher", method = {RequestMethod.GET, RequestMethod.POST},produces ="text/html;charset=UTF-8")
	public ModelAndView editCampus(JwTeacher teacher) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/editTeacher");
		jwTeacherService.updateTeacher(teacher);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initCourse", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initCourse(JwTeacher teacher) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/selectCourse");
		JwCourseTeacher courseTeacherParam=new JwCourseTeacher();
		courseTeacherParam.setTeacherId(teacher.getId());
		List<JwCourseTeacher> jwCourseTeachers=jwCourseTeacherService.findCourseTeacher(courseTeacherParam);
		for(JwCourseTeacher courseTeacher:jwCourseTeachers){
			if(courseTeacher.getTeacherId()!=null&&!courseTeacher.getTeacherId().equals(teacher.getId())){
				courseTeacher.setTeacherId(null);
			}
		}
		modelAndView.addObject("jwCourseTeachers",jwCourseTeachers);
		modelAndView.addObject("teacherId",teacher.getId());
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value = "/teacheAddCourse", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView teacheAddCourse(String teacherId,String [] courseIds) {
		ModelAndView modelAndView = new ModelAndView();
		List<JwCourseTeacher> courseTeachers=new ArrayList<JwCourseTeacher>();
		for(String courseId:courseIds){
			JwCourseTeacher courseTeacher=new JwCourseTeacher();
			courseTeacher.setTeacherId(Long.parseLong(teacherId));
			courseTeacher.setCourseId(Long.parseLong(courseId));
			courseTeachers.add(courseTeacher);
		}
		jwCourseTeacherService.batchDeletCourseTeacher(courseTeachers);
		jwCourseTeacherService.addBatchCourseTeacher(courseTeachers);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/queryClassTeacher", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryClassTeacher(Integer campusId,Integer classesId,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/queryClassTeacher");
		List<JwCampus> campuses=jwCampusService.findCampus(null);
		JwClasses classes=new JwClasses();
		if(campusId!=null){
			classes.setCampusId(campusId);
		}
		PageParameter page = this.getPageParameter(request);
		List<JwClasses> allClasses=new ArrayList<JwClasses>();
		page=jwClassesService.findClassesByCondtion(page,classes);
		if(page.getData()!=null){
			allClasses=(ArrayList<JwClasses>)page.getData();
		}
		if(allClasses.size()>0){
			Map params=new HashMap();
			params.put("campusId",campusId);
			params.put("classesId",classesId);
			List<Map> teachers=jwTeacherService.findTeacheByclassesId(params);
			modelAndView.addObject("teachers",teachers);

		}
		modelAndView.addObject("campusId",campusId);
		modelAndView.addObject("classesId",classesId);
		modelAndView.addObject("allClasses",allClasses);
		modelAndView.addObject("campuses",campuses);
		this.pageResponse(request,page);
		return modelAndView;
	}

	@RequestMapping(value = "/initCheckIn", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initCheckIn(JwTeacherAttendance jwTeacherAttendance,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("jwTeacher/checkIn");
		List<JwCampus> campuses=jwCampusService.findCampus(null);
		logger.info("--------------"+campuses);
		JwTeacher teacher=jwTeacherService.getTeacheById(jwTeacherAttendance.getTeacherId());
		modelAndView.addObject("campuses",campuses);
		modelAndView.addObject("teacher",teacher);
		modelAndView.addObject("jwTeacherAttendance",jwTeacherAttendance);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/checkIn", method = {RequestMethod.GET, RequestMethod.POST})
	public String checkIn(JwTeacherAttendance jwTeacherAttendance,HttpServletRequest request,HttpServletResponse response){
		String flag="ok";
		try{
			jwTeacherAttendanceService.addTeacherAttendance(jwTeacherAttendance);
			JwTeacher teacher=jwTeacherService.getTeacheById(jwTeacherAttendance.getTeacherId());
			teacher.setAttendanceCount(teacher.getAttendanceCount()+1);
			jwTeacherService.updateTeacher(teacher);
		}catch (Exception e){
			flag="fail";
		}
		return flag;
	}


	@ResponseBody
	@RequestMapping(value = "/isCheckIn", method = {RequestMethod.GET, RequestMethod.POST})
	public String isCheckIn(JwTeacherAttendance jwTeacherAttendance,HttpServletRequest request,HttpServletResponse response){
		String flag="";
		try{
			List<JwTeacherAttendance> jwTeacherAttendances=jwTeacherAttendanceService.getJwTeacherAttendance(jwTeacherAttendance);
			if(jwTeacherAttendances.size()>0){
				flag="fail";
			}else {
				flag="ok";
			}
		}catch (Exception e){
			e.printStackTrace();
			flag="fail";
		}
		return flag;
	}

	@ResponseBody
	@RequestMapping(value = "/checkInDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView checkInDetail(HttpServletRequest request,Integer teacherId,Integer campusId) {
		ModelAndView modelAndView = new ModelAndView("jwTeacher/teacherList");
		PageParameter page = this.getPageParameter(request);
		JwTeacherAttendance teacherAttendance=new JwTeacherAttendance();
		teacherAttendance.setTeacherId(teacherId);
		page=jwTeacherAttendanceService.findTeacherAttendances(page, teacherAttendance);
		JwClasses classes=new JwClasses();
		classes.setCampusId(campusId);
		List<JwClasses> classeses=jwClassesService.findClassesByCondtion(classes);
		modelAndView.addObject("checkIns",page.getData());
		modelAndView.addObject("classeses",classeses);
		this.pageResponse(request,page);
		return modelAndView;
	}

	@RequestMapping(value = "downLoadQuery", method = {RequestMethod.POST, RequestMethod.GET})
	public void downLoadQuery( JwTeacherAttendance jwTeacherAttendance,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map<String,String> campusMap=new HashMap<String,String>();
		Map<String,String> classesMap=new HashMap<String,String>();
		List<JwTeacherAttendance> attendances=jwTeacherAttendanceService.getJwTeacherAttendance(jwTeacherAttendance);
		List<JwCampus> campuses=jwCampusService.findCampus(null);
		List<JwClasses> classeses=jwClassesService.findClasses();
		JwTeacher jwTeacher=jwTeacherService.getTeacheById(jwTeacherAttendance.getTeacherId());
		for(JwCampus campus:campuses){
			campusMap.put(campus.getId()+"",campus.getName());
		}
		for(JwClasses classes:classeses){
			classesMap.put(classes.getId()+"",classes.getClassesName());
		}

		DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ExcelCreate excelCreate = new ExcelCreate();
		excelCreate.createRow(0);
		excelCreate.setCellTitle(0, "日期");
		excelCreate.setCellTitle(1, "校区");
		excelCreate.setCellTitle(2, "班级");
		excelCreate.setCellTitle(3, "备注");
		if (attendances != null) {
			for (int i = 0; i < attendances.size(); i++) {
				JwTeacherAttendance attendance =attendances.get(i);
				excelCreate.createRow(i + 1);
				excelCreate.setCell(0, dfs.format(attendance.getCreateTime()));
				excelCreate.setCell(1, campusMap.get(attendance.getCampusId()+""));
				excelCreate.setCell(2, classesMap.get(attendance.getClassesId()+""));
				excelCreate.setCell(3, StringHelperTools.nvl(attendance.getNote()));
			}
		}
		excelCreate.downloadExcel(excelCreate.getWorkbook(), response, jwTeacher.getName()+"出勤.xls");
	}
}
