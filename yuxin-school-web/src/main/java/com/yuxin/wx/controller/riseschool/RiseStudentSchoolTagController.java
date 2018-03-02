package com.yuxin.wx.controller.riseschool;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuxin.wx.common.PageFinder;

/**
 * 学员管理
 */
@Controller
@RequestMapping(value = "/riseStudentSchoolTag")
public class RiseStudentSchoolTagController {
	/**
     * 查询信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryStudentSchoolTag",method=RequestMethod.POST)
    public String queryRiseSchoolInfo(HttpServletRequest request,Model model){
        return "/riseschool/studentManagementAjaxList";
    }

}
