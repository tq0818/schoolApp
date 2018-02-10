package com.yuxin.wx.controller.riseschool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;

public class BaseRiseSchoolController {
	 @Autowired
	 public RiseSchoolManageService riseSchoolManageServiceImpl;
	 
	 public String returnIndex(HttpServletRequest request,Model model,Integer schoolId,String schoolName){
			Users user = WebUtils.getCurrentUser(request);
			if("RISE_SCHOOL_MANAGER".equals(user.getUserType())){
				 Map<String,Object>params = new HashMap<String,Object>();
			     params.put("userId",user.getId());
			     RiseSchoolManageVo rsieSchool = riseSchoolManageServiceImpl.queryCurrentRiseSchoolInfo(params);
			     if(schoolId!=rsieSchool.getId()){
			    	 return "redirect:/index";
			     }
			}
			return null;
	 }
}
