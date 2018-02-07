package com.yuxin.wx.controller.riseschool;

import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lym_gxm on 18/2/5.
 */
@Controller
@RequestMapping("/riseschoolback")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EarlyLitreController {
    @Autowired
    private RiseSchoolManageService riseSchoolInfoServiceImpl;
    //私立校后台-学校管理
    @RequestMapping(value = "/earlyLitre")
    public String earlyLitre(HttpServletRequest request, Model model,RiseSchoolManageVo riseSchoolManageVo){
        PageFinder<RiseSchoolManageVo> pageFinder = riseSchoolInfoServiceImpl.queryRiseSchoolInfo(riseSchoolManageVo);
        model.addAttribute("result",pageFinder.getData());
        model.addAttribute("pageNo",riseSchoolManageVo.getPage());
        model.addAttribute("rowCount",pageFinder.getRowCount());
        return "/riseschool/earlyLitre";
    }
    //学校详情
    @RequestMapping(value = "/schoolDetails")
    public String schoolDetails(){

        return "/riseschool/schoolDetails";
    }
    //基本信息
    @RequestMapping(value = "/essential")
    public String essential(){

        return "/riseschool/essential";
    }



}

