package com.shenlan.controller.ueditorPlugin;

import com.alibaba.fastjson.JSON;
import com.shenlan.common.util.FileUtils;
import com.shenlan.controller.BaseController;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@MonitoredWithSpring
@Controller
@RequestMapping("/ueditor")
public class UeditorController extends BaseController {

	@RequestMapping(value ="show" , method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView show(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		String action=request.getParameter("action");
		String noCache=request.getParameter("noCache");
		modelView.addObject("action",action);
		modelView.addObject("noCache",noCache);
		modelView.setViewName("ueditorPlugin/controller");
		return modelView;
	}

	@RequestMapping(value ="init" , method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView init(HttpServletRequest request,HttpServletResponse response) {
	    ModelAndView modelView = new ModelAndView();
		modelView.setViewName("ueditorPlugin/ueditor");
		return modelView;
	}

	/**
	 * 多文件上传支持
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "uploadfile",method = {RequestMethod.POST,RequestMethod.GET})
	public Map uploadFile(@RequestParam(value = "imgFile") CommonsMultipartFile[] files,String fileDir, HttpServletRequest req,HttpServletResponse response) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		if (files != null && files.length > 0){
			FileUtils fileUtils =new FileUtils();
			map = fileUtils.uploadAndReturnStatus(files, req, fileDir);
		}
		return map;
	}


	/**
	 * 文件空间
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @return json
	 */
	@RequestMapping(value = "/fileManager")
	@ResponseBody
	public Object fileManager(HttpServletRequest request, HttpServletResponse response) {
		FileUtils fileUtils =new FileUtils();
		//根目录路径，可以指定绝对路径
		String rootPath =fileUtils.getPropertiesValue("resourceUri");
		//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl  = request.getContextPath() + rootPath.substring(2);
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp","mp4"};
		String currentUrl="";
		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media","insertvideo", "file"}).contains(dirName)){
				return "Invalid Directory name.";
			}
			if(dirName.equals("image")){
				dirName="kindeditor_img";
			}
			if(dirName.equals("insertvideo")){
				dirName="kindeditor_video";
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			currentUrl+=dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
//		String currentUrl = rootUrl + path;
		String dirStr = this.getPropertiesValue("imagResUri");
		currentUrl=dirStr+currentUrl;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			return "Access is not allowed.";
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			return "Parameter is not valid.";
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			return "Directory does not exist.";
		}

		//遍历目录取的文件信息
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
					currentUrl+="/"+file.getName()+"/";
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl+currentDirPath+"/");
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		logger.debug("+++++++fileManager++++++++"+ JSON.toJSONString(result));
		return result;
	}

	private class NameComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}

	private class SizeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	private class TypeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}
}
