package com.yuxin.wx.controller.institution;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lym_gxm on 18/5/6.
 */
@Controller
@RequestMapping("/InsInfoBase")
public class InstitutionBaseController {

    @RequestMapping(value = "/organizationIndexZYT")
    public String findInsInfoList(){
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
    //基本信息管理
    @RequestMapping(value = "/basicInformation")
    public String basicInformation(){
        return "institution/basicInformation";
    }
}
