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
    //推荐机构管理
    @RequestMapping(value = "/recommendationOrganization")
    public String recommendationOrganization(){
        return "institution/recommendationOrganization";
    }
    //风采管理
    @RequestMapping(value = "/elegantDemeanor")
    public String elegantDemeanor(){
        return "institution/elegantDemeanor";
    }
    //新增线下课程
    @RequestMapping(value = "/newLineCourse")
    public String newLineCourse(){
        return "institution/newLineCourse";
    }
    //首页推荐分类管理
    @RequestMapping(value = "/recommendationHomeC")
    public String recommendationHomeClassification(){
        return "institution/recommendationHomeC";
    }


}
