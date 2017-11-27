package com.yuxin.wx.controller.student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class QuestionAnswering {
    /**
     *标签管理
     * @return
     */
    @RequestMapping(value = "/labelManagement")
    public String labelManagement(){


        return "student/questionAnswering/labelManagement";
    }

    /**
     *提问
     * @return
     */
    @RequestMapping(value = "/putQuestions")
    public String putQuestions(){


        return "student/questionAnswering/putQuestions";
    }

}
