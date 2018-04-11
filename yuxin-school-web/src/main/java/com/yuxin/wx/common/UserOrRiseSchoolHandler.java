package com.yuxin.wx.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.utils.GetInjectionClassForSpringContainerUtil;
import com.yuxin.wx.utils.WebUtils;

public class UserOrRiseSchoolHandler extends GetInjectionClassForSpringContainerUtil{
	private IUsersService usersServiceImpl = application.getBean(IUsersService.class) ;

	public boolean judgeUsers(HttpServletRequest request) {
		String url = request.getServletPath();
		Integer userId = WebUtils.getCurrentUserId(request);
		boolean flag = true;
		//查询用户是否属于小升初后台登录用户
		if (StringUtils.isNotBlank(url)) {
			Integer count = usersServiceImpl.queryUserOrRiseSchool(userId);
			if (count.intValue() == 1) {
				if (url.indexOf("riseschoolback") == -1) {
					flag = false;
				}
			}
		}else{
			flag = false;
		}
		return flag;
	}
	
}
