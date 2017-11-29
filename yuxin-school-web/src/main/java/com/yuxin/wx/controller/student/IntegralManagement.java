package com.yuxin.wx.controller.student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntegralManagement {

    /**
     * 配置积分规则
     */
    @RequestMapping(value = "/integralRule")
    public String integralRule(){


        return "student/integralManagement/integralRule";
    }


}
