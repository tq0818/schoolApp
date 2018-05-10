package com.yuxin.wx.controller.institution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;

@Controller
@RequestMapping("/institutionClassType")
public class InstitutionClassTypeController {
	private static Log log = LogFactory.getLog("log");
	
	@Autowired
	private InstitutionClassTypeService institutionClassTypeService;

	@Autowired
	private InstitutionInfoService institutionInfoService;
	
	/**
	 * 进入课程管理首页
	 * @param model
	 * @param insId
	 * @return
	 */
	 @RequestMapping(value = "/classTypeMain/{insId}")
	 public String intoClassTypeMain(Model model,@PathVariable Integer insId){
	      //  PageFinder<InstitutionInfoVo> pageFinder = institutionInfoService.findInstitutionInfos(insInfoVo);
	      //  model.addAttribute("pageFinder",pageFinder);
	     //   return "institution/organizationIndex";
		 
		 InstitutionInfoVo insEntity =  institutionInfoService.findInstitutionInfoById(insId);
		 model.addAttribute("insEntity",insEntity);
		 return "institution/course";
		 
	}
	
	 /**
	  * 获取课程分页列表信息
	  * @param insId	机构ID
	  * @param status	课程状态
	  * @param pageStart	分页开始位置
	  * @param pageSize	分页大小
	  * @return
	  */
	@ResponseBody
	@RequestMapping(value = "/getClassTypeList", method = RequestMethod.POST)
	public PageFinder<InstitutionClassTypeVo> findInstitutionCategoryById(Integer insId,Integer status, int pageStart, int pageSize) {
		// PageFinder<InstitutionClassTypeVo>
		// return institutionCategoryService.findInstitutionCategoryById(id);
		return institutionClassTypeService.page(insId,status, pageStart, pageSize);
	}
	
	
	// 跳转到统计直播页面中
	@RequestMapping(value = "/addClassType")
	public String addClassType(Model model,InstitutionClassTypeVo entity) {
		
		
		
		
		return "";
	}
	
	

}
