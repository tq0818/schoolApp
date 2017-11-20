package com.yuxin.wx.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuxin.wx.api.app.IShelvesCourseService;
import com.yuxin.wx.model.app.ShelvesCourse;
import com.yuxin.wx.model.commodity.CommoditySpecial;
@Controller
@RequestMapping("/shelvesCourse")
public class ShelvesCourseController {	
	private Log log=LogFactory.getLog("log");
	@Autowired
	private IShelvesCourseService shelvesCourseService;
	
	@RequestMapping("/findShelvesCourseByapge")
	public String findShelvesCourseByapge(HttpServletRequest request,HttpServletResponse response,Model model){
		try{
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			String categoryName = request.getParameter("categoryName");
			String gradeName = request.getParameter("gradeName");
			String subjectName = request.getParameter("subjectName");
			String knowledgeName = request.getParameter("knowledgeName");
			String knowledgeProName = request.getParameter("knowledgeProName");
			String stageName = request.getParameter("stageName");
			String typeCode = request.getParameter("typeCode");
			List<ShelvesCourse> courseList = shelvesCourseService.queryShelvesCoursesByPage(
					categoryName,
					gradeName,
					subjectName,
					knowledgeName,
					knowledgeProName,
					stageName,
					typeCode
					);
			
//			int count = commoditySpecialServiceImpl.findSpecialByPageCount();
			model.addAttribute("courseList", courseList);
//			model.addAttribute("count", count);
			model.addAttribute("pageNum", pageNum);
		}catch(Exception e){
			log.error("queryShelvesCoursesByPage is error", e);
		}
		//js页面跳转用的方法为queryshelvesCoursesApp
		return "simpleClasses/appNewClasses/shelvesCoursesAjax";
	}
}
