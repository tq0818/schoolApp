package com.yuxin.wx.controller.app;

import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.model.app.AppTagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/4/8.
 */
@Controller
@RequestMapping("/appTagManage")
public class AppTagController {

    @Autowired
    private ISysDictAppService sysDictAppService;
    /**
     * 查询appTag控制列表
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/queryAppTagList",method = RequestMethod.GET)
    public String queryAppTagList(HttpServletRequest request, HttpServletResponse response, Model model){
        List<AppTagVo> appTags = sysDictAppService.queryAppTagList();
        model.addAttribute("appTags",appTags);
        return "/app/queryAppTagList";
    }

    /**
     * 更改appTag开关状态
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeTagSwitch",method = RequestMethod.POST)
    public Object changeTagSwitch(HttpServletRequest request, HttpServletResponse response, Model model){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("isOpen","0".equals(request.getParameter("isOpen"))?"1":"0");
        params.put("id",request.getParameter("id"));
        try{
            sysDictAppService.changeTheSwith(params);
        }catch (Exception e){
            return "no";
        }

        return "OK";
    }

}
