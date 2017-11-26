package com.yuxin.wx.controller.student;


import com.yuxin.wx.api.company.INoticeTemplateService;
import com.yuxin.wx.model.company.NoticeTemplatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class NotificationTemplateController {

    @Autowired
    private INoticeTemplateService noticeTemplateService;

    @RequestMapping(value = "/notificationTemplate")
    public String notificationTemplate(Model model, HttpServletRequest request, NoticeTemplatVo ntv){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ntv",ntv);
        List<NoticeTemplatVo> ntvList = noticeTemplateService.queryAllNoticeTemplateByCondition(map);
        model.addAttribute("ntvList",ntvList);
        return "student/notificationMessage/notificationTemplate";
    }

    @RequestMapping(value = "/subscribeArticles")
    public String subscribeArticles(){


        return "student/notificationMessage/subscribeArticles";
    }
}
