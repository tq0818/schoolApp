package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    @RequestMapping(value = "/queryAllInsCate",method= RequestMethod.GET)
    public JSONObject queryAllInsCate(InstitutionCategoryVo insCate){
        JSONObject resultJson = new JSONObject();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("search",insCate);
        List<InstitutionCategoryVo> insCates = institutionCategoryManageServiceIml.queryInstitutionCategorys(params);
        if(null!=insCates && insCates.size()>0){
            resultJson.put("result",insCates);
        }else{
            System.out.println(1111);
            resultJson.put("result","noData");
        }
        return resultJson;
    }


    /**
     * 查询单条分类数据信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/querySingleInsCate/{id}",method=RequestMethod.GET)
    public JSONObject querySingleInsCate(@PathVariable Integer id){
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
    @RequestMapping(value = "/updateInsCate",method=RequestMethod.GET)
    public JSONObject updateInsCate(InstitutionCategoryVo insCate){
        JSONObject resultJson = new JSONObject();
        try{
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
    @RequestMapping(value = "/saveInsCate",method=RequestMethod.GET)
    public JSONObject saveInsCate(InstitutionCategoryVo insCate){
        JSONObject resultJson = new JSONObject();
        try{
            institutionCategoryManageServiceIml.updateInstitutionCategoryInfo(insCate);
            //保存数据成功
            resultJson.put("flag","1");
        }catch (Exception e){
            //保存数据失败
            resultJson.put("flag","0");
        }
        return resultJson;
    }









}
