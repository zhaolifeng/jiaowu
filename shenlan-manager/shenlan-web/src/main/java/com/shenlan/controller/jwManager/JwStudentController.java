package com.shenlan.controller.jwManager;

import com.shenlan.common.util.StringHelperTools;
import com.shenlan.common.utils.excel.ExcelCreate;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwStudent")
public class JwStudentController extends BaseController {
	@Autowired
	private IJwStudentService jwStudentService;
	@Autowired
	private IJwCampusService jwCampusService;
	@Autowired
	private IJwClassesService jwClassesService;
	@Autowired
	private IJwStudentClassesService jwStudentClassesService;
	@Autowired
	private IJwCourseService jwCourseService;
	@Autowired
	private IJwStudentAttendanceService jwStudentAttendanceService;




	@ResponseBody
	@RequestMapping(value = "/students", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView students(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/studentList");
//		List<JwStudent> students=jwStudentService.findStudent(null);
		List<JwCampus> campuses=jwCampusService.findCampus(null);
//		modelAndView.addObject("students",students);
		PageParameter page = this.getPageParameter(request);
		page=jwStudentService.findStudent(page, null);
		modelAndView.addObject("students",page.getData());
		modelAndView.addObject("campuses",campuses);
		this.pageResponse(request,page);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initStudent", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initStudent(JwStudent student) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/student");
		List<JwCampus> campuses=jwCampusService.findCampus(null);
		modelAndView.addObject("campuses",campuses);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/addStudent", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addStudent(JwStudent student,String birthdayParam) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/student");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		try {
			student.setBirthday(sdf.parse(birthdayParam));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		jwStudentService.addStudent(student);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/getStudent", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getStudent(JwStudent student) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/editStudent");
		student= jwStudentService.getStudenteById(student);
		List<JwCampus> campuses=jwCampusService.findCampus(null);
		modelAndView.addObject("campuses",campuses);
		modelAndView.addObject("student",student);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(student.getBirthday()!=null){
			modelAndView.addObject("birthday",sdf.format(student.getBirthday()));
		}
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/editStudent", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView editCampus(JwStudent student,String birthdayParam) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/editStudent");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		try {
			student.setBirthday(sdf.parse(birthdayParam));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jwStudentService.updateStudent(student);
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value = "/classes", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryClassRooms(JwStudent student) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/selectClasses");
		student= jwStudentService.getStudenteById(student);
		JwStudentClass studentClass=new JwStudentClass();
		studentClass.setStudentId(Integer.parseInt(student.getId()+""));
		List<JwStudentClass> studentClasseses=jwStudentClassesService.findStudentClasses(studentClass);
		JwClasses classesParam=new JwClasses();
		classesParam.setCampusId(student.getCampusId());
		List<JwClasses> classeses=jwClassesService.findClassesByCondtion(classesParam);
		List<JwCampus> jwCampuses=jwCampusService.findCampus(Integer.parseInt("1"));
		List<JwClasses> jwClasses=new ArrayList<JwClasses>();
		for(JwClasses classes:classeses){
			boolean flag=true;
			for(JwStudentClass studentClasse:studentClasseses){
				logger.info("*****id*********"+classes.getId());
				logger.info("*****ClassesId*********"+studentClasse.getClassesId());
				if((studentClasse.getClassesId()+"").equals(classes.getId()+"")){
					flag=false;
				}
			}
			if(flag){
				jwClasses.add(classes);
			}
		}
		modelAndView.addObject("classes",jwClasses);
		modelAndView.addObject("jwCampuses",jwCampuses);
		modelAndView.addObject("student",student);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/addStudentClasses", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addStudentClasses(Integer studentId,String [] classesIds) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/student");
		for(String classesId:classesIds){
			JwStudentClass studentClass=new JwStudentClass();
			studentClass.setClassesId(Integer.parseInt(classesId));
			studentClass.setStudentId(studentId);
			studentClass.setPayStatus(0);
			jwStudentClassesService.addStudentClasses(studentClass);
		}
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value = "/initQuery", method = {RequestMethod.GET, RequestMethod.POST})
	public List initQuery() {
		ModelAndView modelAndView = new ModelAndView("jwStudent/queryStudentClasses");
		List<JwCampus> campuses=jwCampusService.findCampus(null);
		modelAndView.addObject("campuses",campuses);
		return campuses;
	}

	@ResponseBody
	@RequestMapping(value = "/queryClassByCampus", method = {RequestMethod.GET, RequestMethod.POST})
	public List queryClassByCampus(JwClasses classes) {
		List<JwClasses> classeses=jwClassesService.findClassesByCondtion(classes);
		return classeses;
	}

	@ResponseBody
	@RequestMapping(value = "/queryStudentClass", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryStudentClass(JwStudentClass studentClass,JwStudent student,JwClasses classes,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/queryStudentClasses");
		List<JwCampus> campuses=jwCampusService.findCampus(null);

		List<JwClasses> allClasses=jwClassesService.findClassesByCondtion(classes);
		classes=jwClassesService.getClassesById(studentClass.getClassesId());
		List<JwStudent> students=jwStudentService.findStudent(student);
		Map paramMap=new HashMap();
		List studentIds=new ArrayList();
		if(classes!=null &&classes.getId()!=null){
			paramMap.put("classesId",classes.getId());
		}

		for(JwStudent studentTemp: students){
			studentIds.add(studentTemp.getId());
		}
		if(studentIds.size()>0){
			paramMap.put("studentIds",studentIds);
		}
		if(studentClass.getPayStatus()!=null){
			paramMap.put("payStatus",studentClass.getPayStatus());
		}
		PageParameter page = this.getPageParameter(request);
		List<JwStudentClass> studentClasseses=new ArrayList<JwStudentClass>();
		page=jwStudentClassesService.findStudentClassesBycondtion(page,paramMap);
		if(page.getData()!=null){
			studentClasseses= (List<JwStudentClass>) page.getData();
		}
		for(JwStudentClass studentClasses:studentClasseses){
			JwStudent student1=new JwStudent();
			student1.setId(Long.parseLong(studentClasses.getStudentId()+""));
			JwStudent studentTemp=jwStudentService.getStudenteById(student1);
			JwClasses classesTemp=jwClassesService.getClassesById(studentClasses.getClassesId());
			JwCourse courseTemp=jwCourseService.getCourseById(classesTemp.getCourseId());
			studentClasses.setStudent(studentTemp);
			studentClasses.setClasses(classesTemp);
			studentClasses.setCourse(courseTemp);
		}
		modelAndView.addObject("studentClasseses",studentClasseses);
		modelAndView.addObject("studentClass",studentClass);
		modelAndView.addObject("student",student);
		modelAndView.addObject("classes",classes);
		modelAndView.addObject("allClasses",allClasses);
		modelAndView.addObject("campuses",campuses);
		this.pageResponse(request,page);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initFee", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initFee(JwStudentClass studentClass) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/fee");
		modelAndView.addObject("id",studentClass.getId());
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/fee", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView classesDetail(JwStudentClass studentClass) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/fee");
		studentClass.setPayStatus(1);
		jwStudentClassesService.updateStudentClasses(studentClass);
		return modelAndView;
	}

	@RequestMapping(value = "/initCheckIn", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initCheckIn(JwStudentAttendance jwStudentAttendance,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("jwStudent/checkIn");
		JwStudent student=new JwStudent();
		student.setId(Long.parseLong(jwStudentAttendance.getStudentId() + ""));
		student=jwStudentService.getStudenteById(student);
		modelAndView.addObject("jwStudentAttendance", jwStudentAttendance);
		modelAndView.addObject("student", student);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/isCheckIn", method = {RequestMethod.GET, RequestMethod.POST})
	public String isCheckIn(JwStudentAttendance jwStudentAttendance,HttpServletRequest request,HttpServletResponse response){
		jwStudentAttendance.setCreateTime(new Date());
		List<JwStudentAttendance> JwStudentAttendances=jwStudentAttendanceService.findStudentAttendances(jwStudentAttendance);
        if(JwStudentAttendances.isEmpty()){
			return "ok";
		}else {
			return  "fail";
		}

	}

	@ResponseBody
	@RequestMapping(value = "/checkIn", method = {RequestMethod.GET, RequestMethod.POST})
	public String checkIn(JwStudentAttendance jwStudentAttendance,JwStudentClass studentClasses,String dateTime,HttpServletRequest request,HttpServletResponse response){
		String flag="ok";
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			jwStudentAttendance.setCreateTime(sdf.parse(dateTime));
			int isOk=jwStudentAttendanceService.addStudentAttendance(jwStudentAttendance);
			if(isOk>0){
				JwStudentClass studentClass=jwStudentClassesService.getStudentClassesById(studentClasses);
				studentClass.setAttendanceCount(studentClass.getAttendanceCount()+1);
				jwStudentClassesService.updateStudentClassesCheckIn(studentClass);
			}else {
				flag="fail";
			}
		}catch (Exception e){
			flag="fail";
			e.printStackTrace();
		}
		return flag;
	}

	@ResponseBody
	@RequestMapping(value = "/checkInDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView checkInDetail(HttpServletRequest request,Integer studentId,Integer campusId,Integer classesId) {
		ModelAndView modelAndView = new ModelAndView("jwStudent/studentCheckInList");
		PageParameter page = this.getPageParameter(request);
		JwStudentAttendance studentAttendance=new JwStudentAttendance();
		studentAttendance.setStudentId(studentId);
		studentAttendance.setClassesId(classesId);
		page=jwStudentAttendanceService.findStudentAttendances(page, studentAttendance);
		JwClasses classes=new JwClasses();
		classes.setCampusId(campusId);
		List<JwClasses> classeses=jwClassesService.findClassesByCondtion(classes);
		modelAndView.addObject("checkIns",page.getData());
		modelAndView.addObject("classeses",classeses);
		this.pageResponse(request,page);
		return modelAndView;
	}


	@RequestMapping(value = "downLoadQuery", method = {RequestMethod.POST, RequestMethod.GET})
	public void downLoadQuery( HttpServletRequest request,Integer studentId,Integer courseId,Integer classesId, HttpServletResponse response) throws IOException {
		JwStudentAttendance studentAttendance=new JwStudentAttendance();
		studentAttendance.setStudentId(studentId);
		studentAttendance.setClassesId(classesId);
		List<JwStudentAttendance> attendances=jwStudentAttendanceService.findStudentAttendances(studentAttendance);
		JwClasses classes=jwClassesService.getClassesById(classesId);
		JwCourse course=jwCourseService.getCourseById(courseId);
		JwStudent student=new JwStudent();
		student.setId(Long.parseLong(studentId+""));
		JwStudent jwStudent=jwStudentService.getStudenteById(student);
		DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ExcelCreate excelCreate = new ExcelCreate();
		excelCreate.createRow(0);
		excelCreate.setCellTitle(0, "日期");
		excelCreate.setCellTitle(1, "班级");
		excelCreate.setCellTitle(2, "课程");
		excelCreate.setCellTitle(3, "学习内容");
		if (attendances != null) {
			for (int i = 0; i < attendances.size(); i++) {
				JwStudentAttendance attendance =attendances.get(i);
				excelCreate.createRow(i + 1);
				excelCreate.setCell(0, dfs.format(attendance.getCreateTime()));
				excelCreate.setCell(1, StringHelperTools.nvl(classes.getClassesName()));
				excelCreate.setCell(2, StringHelperTools.nvl(course.getCourseName()));
				excelCreate.setCell(3, StringHelperTools.nvl(attendance.getNote()));
			}
		}
		excelCreate.downloadExcel(excelCreate.getWorkbook(), response, jwStudent.getName()+"学习进度.xls");
	}
}
