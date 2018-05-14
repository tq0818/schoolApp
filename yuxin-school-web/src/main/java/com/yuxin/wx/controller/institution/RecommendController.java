package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.api.institution.InstitutionCategoryService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.IndexRecommendVo;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/institutionRecommend")
public class RecommendController {
    private static Log log = LogFactory.getLog("log");



    @Autowired
    private InstitutionCategoryManageService institutionCategoryService;


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



//========================== 首页分类推荐部分


    /**
     * 获取分类推荐列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/typeList",method = RequestMethod.POST)
    public JSONObject getTypeList(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            Integer page = Integer.valueOf(request.getParameter("pageStart"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            json.put("data",institutionCategoryService.queryCateList(page,pageSize));
            json.put("num",institutionCategoryService.queryRecommendCount());
            return json;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * change recommend status , if true ? false : true
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
    public JSONObject changeRecommendStatus(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            InstitutionCategoryVo entity = institutionCategoryService.getCateById(id);


            if(null == entity){
                json.put("status",0);
                json.put("msg","参数错误");
                return json;
            }

            institutionCategoryService.updateRecommendStatusById(entity.getThirdRecommend() == 1 ? 0 : 1 ,entity.getId(),entity.getSort());

            json.put("status",1);
            json.put("msg","操作成功");
            return json;

        }catch (Exception e){
            e.printStackTrace();
            json.put("status",0);
            json.put("msg","操作异常");
            return json;
        }
    }

    /**
     * 更新某个分类的排序
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeSort",method = RequestMethod.POST)
    public JSONObject changeRecommendSort(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            String method = request.getParameter("method");
            Integer id = Integer.valueOf(request.getParameter("id"));
            if(null == method || ( !"up".equals(method) && !"down".equals(method))){
                json.put("status",0);
                json.put("msg","参数错误");
                return json;
            }
            InstitutionCategoryVo entity = institutionCategoryService.getCateById(id);

            if(null == entity){
                json.put("status",0);
                json.put("msg","参数错误");
                return json;
            }

            boolean flag = institutionCategoryService.updateSort(entity,"up".equals(method));
            if(flag){
                json.put("status",1);
                json.put("msg","操作成功");
            }else{
                json.put("status",0);
                json.put("msg","操作失败");
            }

            return json;

        }catch (Exception e){
            e.printStackTrace();
            json.put("status",0);
            json.put("msg","操作异常");
            return json;
        }
    }





    /**
     * 获取所有首页分类列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/typeListAll",method = RequestMethod.POST)
    public List<InstitutionCategoryVo> getTypeListAll() {
        try{
            return   institutionCategoryService.queryInstitutionCategorysEnabled();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
