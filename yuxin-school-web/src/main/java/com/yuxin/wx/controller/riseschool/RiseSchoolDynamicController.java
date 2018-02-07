package com.yuxin.wx.controller.riseschool;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.riseschool.IRiseSchoolDynamicService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolDynamicVo;


/**
 * 学校动态
 */
@Controller
@RequestMapping("/riseSchoolDynamic")
public class RiseSchoolDynamicController {
	@Autowired
	private IRiseSchoolDynamicService riseSchoolDynamicImpl;
	
	
	/**
	 * 根据schoolId查询当前学校的所有动态
	 * @param request
	 * @param schoolId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryAllDynamic")
	public JSONObject queryAllDynamic(HttpServletRequest request,Integer schoolId){
		JSONObject json = new JSONObject();
		if(schoolId == null){
			return null;
		}
		RiseSchoolDynamicVo riseSchoolDynamic = new RiseSchoolDynamicVo();
		riseSchoolDynamic.setRiseSchoolId(schoolId);
		PageFinder<RiseSchoolDynamicVo> pageFinder = riseSchoolDynamicImpl.queryAllDynamic(riseSchoolDynamic);
        json.put("result",pageFinder);
        return json;
	}
	
	/***
	 * 新增或修改动态
	 * @param request
	 * @param riseSchoolDynamic
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addDynamic")
	public String  addDynamic(HttpServletRequest request,RiseSchoolDynamicVo riseSchoolDynamic){
		Integer id = riseSchoolDynamic.getId();
		try {
			//根据id判断是新增还是修改
			if(id == null){
				//新增
				riseSchoolDynamicImpl.insert(riseSchoolDynamic);
				return "success";
			}else{
				//修改
				riseSchoolDynamicImpl.update(riseSchoolDynamic);
				return "success";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "false";
		}
	}
	/**
	 * 查看动态
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkDynamic")
	public RiseSchoolDynamicVo checkDynamic(HttpServletRequest request,Integer id){
		if(id == null){
			return null;
		}
		try {
			//根据id查询动态
			RiseSchoolDynamicVo dynamicVo = riseSchoolDynamicImpl.checkDynamic(id);
			return dynamicVo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	
	}
	/**
	 * 删除
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteDynamic")
	public String deleteDynamic(HttpServletRequest request,Integer id){
		if(id == null){
			return "false";
		}
		try {
			//根据id删除动态
			riseSchoolDynamicImpl.deleteDynamic(id);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "false";
		}
	}

	
}
