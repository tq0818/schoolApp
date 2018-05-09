package com.yuxin.wx.controller.institution;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lym_gxm on 18/5/6.
 */
@Controller
@RequestMapping("/InsInfoBase")
public class InstitutionBaseController {
    //机构管理首页
    @RequestMapping(value = "/organizationIndex")
    public String organizationIndex(){
        return "institution/organizationIndex";
    }
    //课程管理
    @RequestMapping(value = "/course")
    public String course(){
        return "institution/course";
    }
    //机构分类管理
    @RequestMapping(value = "/classification")
    public String classification(){
        return "institution/classification";
    }

}
