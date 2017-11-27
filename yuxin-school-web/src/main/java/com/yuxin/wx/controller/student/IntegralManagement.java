package com.yuxin.wx.controller.student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class IntegralManagement {
    /**
     * 积分修改
     * @return
     */
    @RequestMapping(value = "/integralModification")
    public String integralModification(){


        return "student/integralManagement/integralModification";
    }

    /**
     * 配置积分规则
     */
    @RequestMapping(value = "/integralRule")
    public String integralRule(){


        return "student/integralManagement/integralRule";
    }


}
