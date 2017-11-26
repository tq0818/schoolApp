package com.yuxin.wx.controller.student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class NotificationTemplateController {

    @RequestMapping(value = "/notificationTemplate")
    public String notificationTemplate(){


        return "student/notificationMessage/notificationTemplate";
    }

    @RequestMapping(value = "/subscribeArticles")
    public String subscribeArticles(){


        return "student/notificationMessage/subscribeArticles";
    }
}
