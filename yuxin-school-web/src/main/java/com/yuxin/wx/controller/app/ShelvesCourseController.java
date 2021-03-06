package com.yuxin.wx.controller.app;

import com.yuxin.wx.api.app.IShelvesCourseService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/shelvesCourse")
public class ShelvesCourseController {	
	private Log log=LogFactory.getLog("log");
	@Autowired
	private IShelvesCourseService shelvesCourseService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/findShelvesCourseByapge")
	public String findShelvesCourseByapge(HttpServletRequest request,HttpServletResponse response,Model model){
		try{

			String commodityPicUrl="http://"+propertiesUtil.getProjectImageUrl()+"/";
			model.addAttribute("commodityPicUrl", commodityPicUrl);

			String pageNum = request.getParameter("page");
//			String pageSize = request.getParameter("pageSize");
			ClassTypeVo ctv = new ClassTypeVo();
			ctv.setPageSize(8);

			if(null!=pageNum && !"".equals(pageNum)){
				ctv.setPage(Integer.parseInt(pageNum));
				ctv.setPageSize(8);
			}
			//课程分类
			String categoryid = request.getParameter("categoryid");
			//学段
			String gradeid = request.getParameter("gradeid");
			//学科
			String subjectid = request.getParameter("subjectid");
			//专题
			String knowledgeid = request.getParameter("knowledgeid");
			//知识点
			String knowledgeProid = request.getParameter("knowledgeProid");
			//阶段
			String stageid = request.getParameter("stageid");
			//类型
			String typeCode = request.getParameter("typeCode");
			PageFinder<ClassTypeVo> courseList = shelvesCourseService.queryShelvesCoursesByPage(
					categoryid,
					gradeid,
					subjectid,
					knowledgeid,
					knowledgeProid,
					stageid,
					typeCode,
					ctv.getPage(),
					ctv.getPageSize(),
					ctv.getFirstIndex()
					);
			if(courseList.getPageNo()==0){
				courseList.setPageNo(courseList.getPageNo()+1);
			}
			model.addAttribute("courseList", courseList);
			model.addAttribute("typeId", request.getParameter("typeId"));
			model.addAttribute("typeStr", request.getParameter("typeStr"));
		}catch(Exception e){
			log.error("queryShelvesCoursesByPage is error", e);
		}
		//js页面跳转用的方法为queryshelvesCoursesApp
		return "simpleClasses/appNewClasses/shelvesCoursesAjax";
	}
}
