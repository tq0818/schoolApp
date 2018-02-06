package com.yuxin.wx.controller.riseschool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lym_gxm on 18/2/5.
 */
@Controller
@RequestMapping("/riseschoolback")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EarlyLitreController {
    //私立校后台-学校管理
    @RequestMapping(value = "/earlyLitre")
    public String earlyLitre(){

        return "/riseschool/earlyLitre";
    }
    //学校详情
    @RequestMapping(value = "/schoolDetails")
    public String schoolDetails(){

        return "/riseschool/schoolDetails";
    }
    //基本信息
    @RequestMapping(value = "/essential")
    public String essential(){

        return "/riseschool/essential";
    }



}

