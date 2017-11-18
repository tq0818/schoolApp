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
    /**
     * 跳转到首页推荐专题列表
     */
    @RequestMapping(value="/recommendedList")
    public String gotorecommendedList(){
        return "simpleClasses/appNewClasses/recommendedList";
    }
    /**
     * 跳转到批量首页推荐
     */
    @RequestMapping(value="/pageRecommendation")
    public String gotopageRecommendation(){
        return "simpleClasses/appNewClasses/pageRecommendation";
    }
    /**
     * 跳转到推荐列表
     */
    @RequestMapping(value="/recommendSpecialList")
    public String gotorecommendSpecialList(){
        return "simpleClasses/appNewClasses/recommendSpecialList";
    }



}
