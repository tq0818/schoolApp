package com.yuxin.wx.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class UserHandler implements HandlerInterceptor {
	Log log = LogFactory.getLog("log");
	@Autowired
	private InstitutionInfoService institutionInfoServiceImpl;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		if (!new UserOrRiseSchoolHandler().judgeUsers(request)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}

		//机构用户拦截
		Users user = WebUtils.getCurrentUser(request);
		if("INSTITUTION_MANAGE".equals(user.getUserType())){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId",user.getId());
			InstitutionInfoVo infoVo = institutionInfoServiceImpl.queryInstitutionByUserId(params);
			if(null==infoVo){
				if (!new UserOrRiseSchoolHandler().judgeUsers(request)) {
					response.sendRedirect(request.getContextPath()+"/login");
					return false;
				}
			}else{
				String id = request.getParameter("id");
				if(org.apache.commons.lang.StringUtils.isNotBlank(id)&&Integer.parseInt(id)!=infoVo.getId()){
					response.sendRedirect(request.getContextPath()+"/index");
				}

			}
		}

		return true;
	}

}
