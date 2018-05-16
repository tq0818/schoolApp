package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/5/9.
 */
@Controller
@RequestMapping("/insCateManage")
public class InstitutionCategoryManageController {

    @Autowired
    private InstitutionCategoryManageService institutionCategoryManageServiceIml;


    /**
     * 查询所有分类数据
     * @param insCate
     * @return
     */
    @RequestMapping(value = "/queryAllInsCate",method= RequestMethod.POST)
    public String queryAllInsCate(InstitutionCategoryVo insCate,Model model){
        try{
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("search",insCate);
            List<InstitutionCategoryVo> insCates = institutionCategoryManageServiceIml.queryInstitutionCategorys(params);
            int recordCount = institutionCategoryManageServiceIml.queryInstitutionCategorysCount(params);
            //封装分页数据
            PageFinder<InstitutionCategoryVo> pageFinder = new PageFinder<InstitutionCategoryVo>(insCate.getPage(), insCate.getPageSize(), recordCount, insCates);

            model.addAttribute("insManageData",pageFinder);
            model.addAttribute("pageNo",pageFinder.getPageNo());
            model.addAttribute("count",recordCount);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "institution/classificationDetails";
    }


    /**
     * 查询单条分类数据信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/querySingleInsCate/{id}",method=RequestMethod.POST)
    public JSONObject querySingleInsCate(@PathVariable Integer id, HttpServletRequest request){
        JSONObject resultJson = new JSONObject();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        InstitutionCategoryVo insCates = institutionCategoryManageServiceIml.queryInstitutionCategoryByCondition(params);
        if(null!=insCates){
            resultJson.put("result",insCates);
        }else{
            resultJson.put("result","noData");
        }
        return resultJson;
    }

    /**
     * 修改分类数据
     * @param insCate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateInsCate",method=RequestMethod.POST)
    public JSONObject updateInsCate(HttpServletRequest request){
        JSONObject resultJson = new JSONObject();
        try{
            InstitutionCategoryVo insCate = new InstitutionCategoryVo();
            String flag = request.getParameter("flag");
            String ids = request.getParameter("ids");
            //启用禁用切换
            if("1".equals(flag)){
                String isEnable = request.getParameter("enable");
                if("1".equals(isEnable)){
                    insCate.setIsEnable(0);
                }else{
                    insCate.setIsEnable(1);
                }
            }else{
                String codeName = request.getParameter("codeName");
                insCate.setCodeName(codeName);
            }
            insCate.setIds(ids);
            institutionCategoryManageServiceIml.updateInstitutionCategoryInfo(insCate);
            //更新数据成功
            resultJson.put("flag","1");
        }catch (Exception e){
            //更新数据失败
            resultJson.put("flag","0");
        }
        return resultJson;
    }

    /**
     * 保存分类数据
     * @param insCate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveInsCate",method=RequestMethod.POST)
    public JSONObject saveInsCate(InstitutionCategoryVo insCate){
        JSONObject resultJson = new JSONObject();
        try{
            //初始化数据为禁用
            insCate.setIsEnable(0);
            //级别
            if(null==insCate.getParentId()){
                insCate.setCodeLevel(1);
                insCate.setIsEnable(0);
            }else{
                insCate.setCodeLevel(2);
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("id",insCate.getParentId());
                InstitutionCategoryVo insCates = institutionCategoryManageServiceIml.queryInstitutionCategoryByCondition(params);
                insCate.setIsEnable(insCates.getIsEnable());
            }
            insCate.setCodeType("0");
            insCate.setSort(999999);
            institutionCategoryManageServiceIml.saveInstitutionCategoryInfo(insCate);
            //保存数据成功
            resultJson.put("flag","1");
        }catch (Exception e){
            //保存数据失败
            resultJson.put("flag","0");
            e.printStackTrace();
        }
        return resultJson;
    }









}
