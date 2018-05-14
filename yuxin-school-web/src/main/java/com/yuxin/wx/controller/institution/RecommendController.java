package com.yuxin.wx.controller.institution;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.IndexRecommendVo;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/institutionRecommend")
public class RecommendController {

    /**
     * 获取首页列表推荐信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRecommendList",method = RequestMethod.POST)
    public PageFinder<IndexRecommendVo> getRecommendList(HttpServletRequest request) {
        try{
            // TODO 获取首页列表推荐信息
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 取消推荐
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public PageFinder<IndexRecommendVo> cancelRecommend(HttpServletRequest request) {
        try{

            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer typeId = Integer.valueOf(request.getParameter("typeId"));
            Integer rid = Integer.valueOf(request.getParameter("rid"));

            // TODO 取消推荐
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 重新对推荐进行排序
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sort",method = RequestMethod.POST)
    public PageFinder<IndexRecommendVo> sortRecommend(HttpServletRequest request) {
        try{

            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer typeId = Integer.valueOf(request.getParameter("typeId"));
            Integer rid = Integer.valueOf(request.getParameter("rid"));
            String method = request.getParameter("method");

            // TODO 取消推荐
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
