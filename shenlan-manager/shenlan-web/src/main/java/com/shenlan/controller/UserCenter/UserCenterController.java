package com.shenlan.controller.UserCenter;

import com.alibaba.fastjson.JSON;
//import com.easto.api.integralCenter.IIntegralRecordService;
import com.shenlan.api.system.ISystemDictInfoService;
import com.shenlan.api.userCenter.IUserCenterService;
import com.shenlan.common.util.StringHelperTools;
import com.shenlan.common.utils.excel.ExcelCreate;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.controller.BaseController;
//import com.easto.domain.bo.IntegralRecord;
import com.shenlan.domain.bo.UserInfo;
import com.shenlan.domain.system.SystemMenu;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户中心
 * yuanquan
 */
@MonitoredWithSpring
@Controller
@RequestMapping("/userCenter")
public class UserCenterController extends BaseController {
    @Autowired
    private IUserCenterService userCenterService;

    @Autowired
    private ISystemDictInfoService systemDictInfoService;

//    @Autowired
//    private IIntegralRecordService integralRecordService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(SystemMenu menu, Model model) {
        model.addAttribute("menuId", menu.getId());
        return "userCenter/list";
    }

    @ResponseBody
    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView queryManager(UserInfo userInfo, HttpServletRequest request) {
        String a = request.getQueryString();
        logger.info("^^^^^^^^^^^^^^" + JSON.toJSONString(userInfo));
        logger.info("^^^^^^^userInfo.getCreateTime^^^^^^^" + userInfo.getCreateTime());
        ModelAndView modelAndView = new ModelAndView("userCenter/list");
        PageParameter page = this.getPageParameter(request);
        page.setLimit(10);
        page = userCenterService.queryUsers(page, userInfo);
        // 查询字典表的平台信息
        String dictType = "platform";
        List platformAllList = systemDictInfoService.getDictValue(dictType);
        List platformList = new ArrayList();
        if (platformAllList != null && !platformAllList.isEmpty()) {
            for (int i = 0; i < platformAllList.size(); i++) {
                Map<String, String> map2 = (Map<String, String>) platformAllList.get(i);
                platformList.add(map2);
            }
        }
        // 查询手机验证字典表信息
        String dictTypeCheckMobile = "check_mobile";
        List checkMobileList = systemDictInfoService.getDictValue(dictTypeCheckMobile);
        int TotalUserCount = userCenterService.queryTotalUserCount();
        int TotalForbiddenUserCount = userCenterService.queryForbiddenTotalUserCount();
        modelAndView.addObject("checkMobileList", checkMobileList);
        modelAndView.addObject("TotalForbiddenUserCount", TotalForbiddenUserCount);
        modelAndView.addObject("TotalUserCount", TotalUserCount);
        modelAndView.addObject("platformList", platformList);
        modelAndView.addObject("pageList", page);
        modelAndView.addObject("userInfo", userInfo);
        this.pageResponse(request, page);
        return modelAndView;
    }

    /**
     * 查询用户详情
     */
    @RequestMapping(value = "/getUserById", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserById(Model model, UserInfo userInfo) {
        ModelAndView modelAndView = new ModelAndView("userCenter/userList");
        userInfo = userCenterService.getUserById(userInfo.getId());
        modelAndView.addObject("userInfo", userInfo);
        return modelAndView;
    }

    /**
     * 查询用户积分详情
     */
    @RequestMapping(value = "/getUserIntegralById", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getUserIntegralById(Model model, UserInfo userInfo, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("userCenter/integral");
//        List<IntegralRecord> IntegralRecordList = integralRecordService.getUserIntegralById(userInfo);
        userInfo = userCenterService.getUserById(userInfo.getId());
        userInfo.setIsAdd(request.getParameter("isAdd"));
        // 查询积分增减状态
        String dictIntegral = "integral_status";
        List integralList = systemDictInfoService.getDictValue(dictIntegral);
//        modelAndView.addObject("IntegralRecordList", IntegralRecordList);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("integralList", integralList);
        return modelAndView;
    }

    @RequestMapping(value = "/DownLoadFiles", method = {RequestMethod.POST, RequestMethod.GET})
    public void DownLoadFiles(HttpServletRequest req, HttpServletResponse resp, HttpServletResponse response, UserInfo userInfo) throws IOException {
        req.setCharacterEncoding("UTF-8");
        logger.info("***userInfo**" + JSON.toJSONString(userInfo));
        PageParameter page = this.getPageParameter(req);
        DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("***userInfo**" + JSON.toJSONString(userInfo));
        List<UserInfo> sortLs = userCenterService.findByUser(userInfo);
        ExcelCreate excelCreate = new ExcelCreate();
        excelCreate.createRow(0);
        excelCreate.setCellTitle(0, "用户id");
        excelCreate.setCellTitle(1, "用户名");
        excelCreate.setCellTitle(2, "注册平台");
        excelCreate.setCellTitle(3, "状态");
        excelCreate.setCellTitle(4, "登陆次数");
        excelCreate.setCellTitle(5, "积分");
        excelCreate.setCellTitle(6, "注册时间");
        excelCreate.setCellTitle(7, "最后登录时间");
        if (sortLs != null) {
            for (int i = 0; i < sortLs.size(); i++) {
                UserInfo userInfos = (sortLs.get(i));
                excelCreate.createRow(i + 1);
                excelCreate.setCell(0, StringHelperTools.nvl(userInfos.getId()));
                excelCreate.setCell(1, StringHelperTools.nvl(userInfos.getUserName()));
                if ("plat_001".equals(StringHelperTools.nvl(userInfos.getPlatform()))) {
                    excelCreate.setCell(2, "portal");
                } else {
                    excelCreate.setCell(2, "app");
                }
                if ("0".equals(StringHelperTools.nvl(userInfos.getStatus()))) {
                    excelCreate.setCell(3, "黑名单");
                } else {
                    excelCreate.setCell(3, "正常");
                }
                DateFormat dfsd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 日期格式
                excelCreate.setCell(4, StringHelperTools.nvl(userInfos.getloginCount()));
                excelCreate.setCell(5, StringHelperTools.nvl(userInfos.getTotalIntegral()));
                excelCreate.setCell(6, dfsd.format(userInfos.getCreateTime()));
                excelCreate.setCell(7, dfsd.format(userInfos.getUpateTime()));
            }
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 日期格式
        Date date = new Date();
        String now = df.format(date);
        excelCreate.downloadExcel(excelCreate.getWorkbook(), resp, now + "用户中心数据" + ".xls");
    }

    @RequestMapping(value = "/addForbid", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addForbid(HttpServletRequest request, Model model) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong(request.getParameter("userId")));
        userInfo.setStatus(0);
        userCenterService.updateUserInfo(userInfo);
        userInfo = null;
        ModelAndView modelAndView = new ModelAndView("userCenter/list");
        PageParameter page = this.getPageParameter(request);
        page.setLimit(10);
        page = userCenterService.queryUsers(page, userInfo);
        // 查询字典表的平台信息
        String dictType = "platform";
        List platformAllList = systemDictInfoService.getDictValue(dictType);
        List platformList = new ArrayList();
        if (platformAllList != null && !platformAllList.isEmpty()) {
            for (int i = 0; i < platformAllList.size(); i++) {
                Map<String, String> map2 = (Map<String, String>) platformAllList.get(i);
                platformList.add(map2);
            }
        }
        int TotalUserCount = userCenterService.queryTotalUserCount();
        int TotalForbiddenUserCount = userCenterService.queryForbiddenTotalUserCount();
        modelAndView.addObject("TotalForbiddenUserCount", TotalForbiddenUserCount);
        modelAndView.addObject("TotalUserCount", TotalUserCount);
        modelAndView.addObject("platformList", platformList);
        modelAndView.addObject("pageList", page);
        modelAndView.addObject("userInfo", userInfo);
        this.pageResponse(request, page);
        return modelAndView;
    }


    @RequestMapping(value = "/moveForbid", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView moveForbid(HttpServletRequest request, Model model) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong(request.getParameter("userId")));
        userInfo.setStatus(1);
        userCenterService.updateUserInfo(userInfo);
        userInfo = null;
        ModelAndView modelAndView = new ModelAndView("userCenter/list");
        PageParameter page = this.getPageParameter(request);
        page.setLimit(10);
        page = userCenterService.queryUsers(page, userInfo);
        // 查询字典表的平台信息
        String dictType = "platform";
        List platformAllList = systemDictInfoService.getDictValue(dictType);
        List platformList = new ArrayList();
        if (platformAllList != null && !platformAllList.isEmpty()) {
            for (int i = 0; i < platformAllList.size(); i++) {
                Map<String, String> map2 = (Map<String, String>) platformAllList.get(i);
                platformList.add(map2);
            }
        }
        int TotalUserCount = userCenterService.queryTotalUserCount();
        int TotalForbiddenUserCount = userCenterService.queryForbiddenTotalUserCount();
        modelAndView.addObject("TotalForbiddenUserCount", TotalForbiddenUserCount);
        modelAndView.addObject("TotalUserCount", TotalUserCount);
        modelAndView.addObject("platformList", platformList);
        modelAndView.addObject("pageList", page);
        modelAndView.addObject("userInfo", userInfo);
        this.pageResponse(request, page);
        return modelAndView;
    }

    /**
     * 查询用户的累计数量（可按时间段进行查询）
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryUserDayCounts", method = {RequestMethod.POST, RequestMethod.GET})
    public String queryUserDayCounts(HttpServletRequest req) {
        String timeBegin = req.getParameter("timeBegin");
        String timeEnd = req.getParameter("timeEnd");
        Map map = new HashMap();
        map.put("timeBegin", timeBegin);
        map.put("timeEnd", timeEnd);
        List userDayCountList = userCenterService.queryUserDayCount(map);
        List userDayCountsList = userCenterService.queryUserDayCounts(map);
        Map<String, List> countmap = new HashMap<String, List>();
        countmap.put("userDayCount", userDayCountList);
        countmap.put("userDayCounts", userDayCountsList);
        logger.info("===用户累积数userDayCountsList===" + JSON.toJSONString(countmap));
        return JSON.toJSONString(countmap);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
