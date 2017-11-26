package com.yuxin.wx.controller.student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class IntegralManagement {

    @RequestMapping(value = "/integralModification")
    public String integralModification(){


        return "student/integralManagement/integralModification";
    }
}
