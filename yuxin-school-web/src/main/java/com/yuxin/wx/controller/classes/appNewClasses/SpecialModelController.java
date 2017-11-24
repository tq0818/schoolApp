package com.yuxin.wx.controller.classes.appNewClasses;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.vo.commodity.CommodityDto;
import com.yuxin.wx.vo.commodity.CommoditySearchDto;
import com.yuxin.wx.vo.commodity.CommodityVo;

@Controller
@RequestMapping("/specialModel")
public class SpecialModelController {

    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private ISysDictAppService iSysDictAppService;
    private static Log log_specialmodel = LogFactory.getLog("specialModel");

    /**
     * 去专题模块的模块列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getModelList")
    public String getModelList(Model model) {
        List<CommodityVo> list = commodityServiceImpl.getModelList();
        List<CommodityVo> specialList = new ArrayList<>();
        int a = 1;
        for (CommodityVo c : list) {
            c.setSout(a);
            specialList.add(c);
            a++;
        }
        model.addAttribute("specialList", specialList);

        return "/simpleClasses/appNewClasses/recommendedList";
    }


    /**
     * 跳转到批量首页推荐
     *
     * @param model
     * @param categoryid        模块ID
     * @param gradeid 学段ID
     * @param subjectid      科目ID
     * @param knowledgeid         知识点ID
     * @param knowledgeProid       知识点专题ID
     * @param typeCode         类型ID
     * @param stageid        阶段ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getModelListByIds")
    public PageFinder2<CommodityDto> getModelListByIds(Model model,CommoditySearchDto search) {
        //根据传入的各个ID查询课程list
        Map<String, Object> param = new HashMap<>();
        if (search.getCategoryid()!= null && search.getCategoryid()!= "") {
            param.put("categoryid", search.getCategoryid());
        }
        if (search.getGradeid() != null && search.getGradeid() != "") {
            param.put("studySectionId", search.getGradeid());
        }
        if (search.getSubjectid() != null && search.getSubjectid()!= "") {
            param.put("subjectId",search.getSubjectid());
        }
        if (search.getKnowledgeProid() != null && search.getKnowledgeProid() != "") {
            param.put("itemId", search.getKnowledgeProid());
        }
        if (search.getKnowledgeid() != null && search.getKnowledgeid() != "") {
            param.put("topicsId", search.getKnowledgeid());
        }
        if (search.getTypeCode() != null && search.getTypeCode() != "") {
            param.put("typeId", search.getTypeCode());
        }
        if (search.getStageid() != null && search.getStageid()!= "") {
            param.put("stageId", search.getStageid());
        }
        param.put("modelCode",search.getModelCode());
        param.put("categoryid",search.getModelId());
        param.put("page",search.getPage());
        param.put("pageSize",search.getPageSize());
        PageFinder2<CommodityDto> commList = commodityServiceImpl.getModelListByIds(param);
        return commList;
    }
    @ResponseBody
    @RequestMapping(value = "/insertOrupdateTuiJian")
    public boolean insertOrupdateTuiJian(Model model,String gradeIds,String appShelvesIds){
    	if(gradeIds==null||gradeIds=="") return true;
    	try{
    		return commodityServiceImpl.insertOrUpdate(gradeIds,appShelvesIds);
    	}catch(Exception e){
    		log_specialmodel.error("insertOrupdateTuiJian(Model,String,String)",e);
    		return false;
    	}
    }
    @ResponseBody
    @RequestMapping(value = "/updateFirstRecommendationNum")
    public boolean updateFirstRecommendationNum(String appShelvesId,String sort){
    	if(appShelvesId==null||appShelvesId=="") return true;
    	try{
    		return commodityServiceImpl.updateFirstRecommend(appShelvesId, sort);
    	}catch(Exception e){
    		log_specialmodel.error("updateFirstRecommendationNum(String,String)",e);
    		return false;
    	}
    }
    @ResponseBody
    @RequestMapping(value = "/queryMenu", method = RequestMethod.POST)
    public Map<String, List<SysDictApp>> querySlibMenu(HttpServletRequest request, String parentId, String typeId) {
        Map<String, List<SysDictApp>> linked = new LinkedHashMap<>();
        SysDictApp search = new SysDictApp();
        search.setParentId(Integer.parseInt(parentId));
        List<SysDictApp> slibMenus = iSysDictAppService.findSysDictAppByParentId(search);
        if ("courseCaId".equals(typeId)) {
            if (null != slibMenus && slibMenus.size() > 0) {
                List<SysDictApp> grades = new ArrayList<>();
                List<SysDictApp> stages = new ArrayList<>();
                List<SysDictApp> types = new ArrayList<>();
                for (SysDictApp data : slibMenus) {
                    if ("GRADE".equals(data.getType())) {
                        grades.add(data);
                    } else if ("STAGE".equals(data.getType())) {
                        stages.add(data);
                    } else if ("TYPE".equals(data.getType())) {
                        types.add(data);
                    }
                }
                linked.put("comm", grades);
                linked.put("stages", stages);
                linked.put("types", types);
            }
        } else {
            linked.put("comm", slibMenus);
        }
        return linked;
    }
}
