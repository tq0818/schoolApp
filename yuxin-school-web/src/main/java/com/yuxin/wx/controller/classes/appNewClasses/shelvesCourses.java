package com.yuxin.wx.controller.classes.appNewClasses;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 已上架课程
 */
@Controller
@RequestMapping("/appNewClasses")
public class  shelvesCourses {

    /**
     * 跳转到已上架课程页面
     * @return
     */
    @RequestMapping(value="/shelvesCourses")
    public String gotoShelvesCourses(){
        return "simpleClasses/appNewClasses/shelvesCourses";
    }
    /**
     * 跳转到上架信息编辑
     */
    @RequestMapping(value="/InformationEditing")
    public String gotoInformationEditing(){
        return "simpleClasses/appNewClasses/informationEditing";
    }



}
