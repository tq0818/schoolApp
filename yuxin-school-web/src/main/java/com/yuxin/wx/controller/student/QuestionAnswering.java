package com.yuxin.wx.controller.student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class QuestionAnswering {

    @RequestMapping(value = "/labelManagement")
    public String labelManagement(){


        return "student/questionAnswering/labelManagement";
    }
}
