package com.yuxin.wx.controller.institution;


import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.common.JsonMsg;
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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/institutionClassType")
public class InstitutionClassTypeController {
	private static Log log = LogFactory.getLog("log");

	private final int MAX_RECOMMEND_NUM = 2;

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
		 try{
			 getInstitution(model,insId);
			 return "institution/course";
		 }catch (Exception e){
		 	e.printStackTrace();
		 	return "404";
		 }

		 
	}
	
	 /**
	  * 获取课程分页列表信息
	  * @return
	  */
	@ResponseBody
	@RequestMapping(value = "/getClassTypeList",method = RequestMethod.POST)
	public PageFinder<InstitutionClassTypeVo> findInstitutionCategoryById(HttpServletRequest request) {
		try{
			Integer insId = Integer.valueOf(request.getParameter("insId"));
			Integer status = Integer.valueOf(request.getParameter("status"));
			Integer pageStart = Integer.valueOf(request.getParameter("pageStart"));
			Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
			return institutionClassTypeService.page(insId,status, pageStart, pageSize);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	

	@RequestMapping(value = "/addClassType/{insId}")
	public String addClassType(Model model,@PathVariable Integer insId) {
		try{
			getInstitution(model,insId);
			return "institution/course";
		}catch (Exception e){
			e.printStackTrace();
			return "404";
		}

	}

	/**
	 *
	 * @param model
	 * @param insId
	 * @throws Exception
	 */
	private void getInstitution(Model model,Integer insId) throws Exception{
		InstitutionInfoVo insEntity =  institutionInfoService.findInstitutionInfoById(insId);
		model.addAttribute("insEntity",insEntity);
	}


	/**
	 * 课程信息上线或者下线
	 * @param request 请求信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upDownClass",method = RequestMethod.POST)
	public String upDownClass(HttpServletRequest request) {
		try{
			Integer cid = Integer.valueOf(request.getParameter("cid"));
			InstitutionClassTypeVo entity = institutionClassTypeService.findById(cid);
			if(null == entity){
				return JsonMsg.ERROR;
			}
			entity.setIsShelves(entity.getIsShelves() == 1 ? 0 : 1);
			//将下架的课程置为不推荐
			if(entity.getIsShelves() == 0){
				entity.setIsReser(0);
			}
			entity.setUpdateTime(new java.util.Date());

			//update to database
			institutionClassTypeService.update(entity);

		}catch (Exception e){
			e.printStackTrace();
			return JsonMsg.ERROR;
		}
		return JsonMsg.SUCCESS;
	}

	/**
	 * 删除某个课程
	 * @param request 默认请求
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delClass",method = RequestMethod.POST)
	public String delClass(HttpServletRequest request) {
		try{
			Integer cid = Integer.valueOf(request.getParameter("cid"));
			InstitutionClassTypeVo entity = institutionClassTypeService.findById(cid);
			if(null == entity){
				return JsonMsg.ERROR;
			}

			//del from database
			institutionClassTypeService.deleteById(entity.getId());

		}catch (Exception e){
			e.printStackTrace();
			return JsonMsg.ERROR;
		}
		return JsonMsg.SUCCESS;
	}

	/**
	 * 推荐课程或者取消推荐课程
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend",method = RequestMethod.POST)
	public String recommendClass(HttpServletRequest request) {
		try{
			Integer cid = Integer.valueOf(request.getParameter("cid"));
			Integer insId = Integer.valueOf(request.getParameter("insId"));

			InstitutionClassTypeVo entity = institutionClassTypeService.findById(cid);
			if(null == entity){
				return JsonMsg.ERROR;
			}

			if(entity.getIsReser() == 0){
				//当前课程是处于未推荐状态，要推荐该课程，则需要判断库中推荐课程数量是否超过最大推荐量
				int num = institutionClassTypeService.getRecommendCountByClassTypeId(insId);
				if(num >= MAX_RECOMMEND_NUM){
					return "该机构推荐课程已经达到最大个数"+MAX_RECOMMEND_NUM;
				}
			}

			entity.setIsReser(entity.getIsReser() == 0 ? 1 : 0);
			entity.setUpdateTime(new java.util.Date());

			//update to database
			institutionClassTypeService.update(entity);

		}catch (Exception e){
			e.printStackTrace();
			return JsonMsg.ERROR;
		}
		return JsonMsg.SUCCESS;
	}


	/**
	 * 获取指定机构已有推荐课程数量
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommendCount",method = RequestMethod.POST)
	public JSONObject recommendClassCount(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try{
			Integer insId = Integer.valueOf(request.getParameter("insId"));
			int num = institutionClassTypeService.getRecommendCountByClassTypeId(insId);
			json.put("num",num);
			json.put("status",1);
		}catch (Exception e){
			e.printStackTrace();
			json.put("status",0);
			json.put("msg","操作异常");
			return json;
		}
		return json;
	}


}
