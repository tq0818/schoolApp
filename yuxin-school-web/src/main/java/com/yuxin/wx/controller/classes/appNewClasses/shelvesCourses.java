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
    @RequestMapping(value="/goShelvesCourses")
    public String goShelvesCourses(){
        return "simpleClasses/appNewClasses/shelvesCourses";
    }



}
