package com.yuxin.wx.controller.institution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;

@Controller
@RequestMapping("/institutionClassType")
public class InstitutionClassTypeController {
	private static Log log = LogFactory.getLog("log");
	
	@Autowired
	private InstitutionClassTypeService institutionClassTypeService;

	@ResponseBody
	@RequestMapping(value = "/getClassTypeList", method = RequestMethod.POST)
	public PageFinder<InstitutionClassTypeVo> findInstitutionCategoryById(Integer status, int pageStart, int pageSize) {
		// PageFinder<InstitutionClassTypeVo>
		// return institutionCategoryService.findInstitutionCategoryById(id);
		return institutionClassTypeService.page(status, pageStart, pageSize);
	}
	
	
	// 跳转到统计直播页面中
	@RequestMapping(value = "/addClassType")
	public String addClassType(Model model,InstitutionClassTypeVo entity) {
		
		
		
		
		return "";
	}
	
	

}
