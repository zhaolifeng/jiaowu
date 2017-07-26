package com.shenlan.controller.jwManager;

import com.shenlan.api.jwManager.IJwCampusService;
import com.shenlan.api.jwManager.IJwClassRoomService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.controller.BaseController;
import com.shenlan.domain.bo.JwCampus;
import com.shenlan.domain.bo.JwClassRoom;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@MonitoredWithSpring
@Controller
@RequestMapping(value = "/jwClassRoom")
public class JwClassRoomController extends BaseController {
	
	@Autowired
	private IJwClassRoomService jwClassRoomService;
	@Autowired
	private IJwCampusService jwCampusService;




	@ResponseBody
	@RequestMapping(value = "/classRooms", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView queryClassRooms(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("jwClassRoom/classRoomList");
		JwClassRoom classRoom=new JwClassRoom();
		classRoom.setStatus(-1);
		List<JwCampus> jwCampuses=jwCampusService.findCampus(Integer.parseInt("1"));
		PageParameter page = this.getPageParameter(request);
		page=jwClassRoomService.findClassRooms(page,classRoom);
		modelAndView.addObject("classRooms",page.getData());
		modelAndView.addObject("jwCampuses",jwCampuses);
		this.pageResponse(request,page);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initClassRoom", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initClassRoom() {
		ModelAndView modelAndView = new ModelAndView("jwClassRoom/classeRoom");
		List<JwCampus> jwCampuses=jwCampusService.findCampus(Integer.parseInt("1"));
		modelAndView.addObject("jwCampuses",jwCampuses);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/initEditClassRoom", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView initEditClassRoom(Integer classRoomId) {
		ModelAndView modelAndView = new ModelAndView("jwClassRoom/editClassRoom");
		JwClassRoom classRoom=jwClassRoomService.getClassRoomById(classRoomId);
		JwCampus campus=jwCampusService.getCampusById(classRoom.getCampusId());
		modelAndView.addObject("classRoom",classRoom);
		modelAndView.addObject("campus",campus);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/addClassRoom", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addClassRoom(JwClassRoom classRoom) {
		ModelAndView modelAndView = new ModelAndView("jwClassRoom/classeRoom");
		jwClassRoomService.addClassRoom(classRoom);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/editClassRoom", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView editClassRoom(JwClassRoom classRoom) {
		ModelAndView modelAndView = new ModelAndView("jwClassRoom/editClassRoom");
		jwClassRoomService.updateClassesRoom(classRoom);
		modelAndView.addObject("classRoom",classRoom);
		return modelAndView;
	}
}
