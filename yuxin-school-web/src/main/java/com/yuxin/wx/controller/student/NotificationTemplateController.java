package com.yuxin.wx.controller.student;


import com.yuxin.wx.api.company.INoticeTemplateService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.NoticeTemplatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        ntv.setPageSize(8);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ntv",ntv);
        PageFinder<NoticeTemplatVo> ntvList = noticeTemplateService.queryAllNoticeTemplateByCondition(map);
        model.addAttribute("pageFinder",ntvList);
        return "student/notificationMessage/notificationTemplate";
    }

    @ResponseBody
    @RequestMapping(value="/updateNoticeTemplatStatus",method= RequestMethod.POST)
    public String updateNoticeTemplatStatus(HttpServletRequest request, NoticeTemplatVo ntv){
        String result="0";
        String ststus = ntv.getNoticeStatus();
        //启用状态  需要禁用
        if("1".equals(ststus)){
            ntv.setNoticeStatus("0");
        }else{
            ntv.setNoticeStatus("1");
        }
        try{
           int i = noticeTemplateService.updateTemplateStatus(ntv);
            if(1==i){
                result="1";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/updateNoticeTemplateContent",method= RequestMethod.POST)
    public String updateNoticeTemplateContent(HttpServletRequest request, NoticeTemplatVo ntv){
        String result="0";
        try{
            int i = noticeTemplateService.updateTemplateStatus(ntv);
            if(1==i){
                result="1";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/subscribeArticles")
    public String subscribeArticles(){


        return "student/notificationMessage/subscribeArticles";
    }
}
