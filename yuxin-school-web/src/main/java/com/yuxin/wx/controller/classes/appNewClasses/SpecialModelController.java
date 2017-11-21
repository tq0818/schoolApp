package com.yuxin.wx.controller.classes.appNewClasses;


import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.vo.commodity.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/specialModel")
public class SpecialModelController {

    @Autowired
    private ICommodityService iCommodityService;
    @Autowired
    private ISysDictAppService iSysDictAppService;


    /**
     * 去专题模块的模块列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getModelList")
    public String getModelList(Model model) {
        List<CommodityVo> list = iCommodityService.getModelList();
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
     * @param modell
     * @param modelId        模块ID
     * @param modelName      模块名称
     * @param studySectionId 学段ID
     * @param subjectId      科目ID
     * @param itemId         知识点ID
     * @param topicsId       知识点专题ID
     * @param typeId         类型ID
     * @param stageId        阶段ID
     * @return
     */
    @RequestMapping(value = "/getModelListByIds")
    public String getModelListByIds(Model modell, Integer modelId, String modelName, Integer studySectionId,
                                    Integer subjectId, Integer itemId, Integer topicsId, Integer typeId, Integer stageId) {
        //根据modelid查找学段list
        List<SysDictApp> list = iSysDictAppService.getStudySectionById(modelId);

        //根据传入的各个ID查询课程list
        Map<String, Object> param = new HashMap<String, Object>();
        if (studySectionId != null && studySectionId.toString() != "") {
            param.put("studySectionId", studySectionId);
        }
        if (subjectId != null && subjectId.toString() != "") {
            param.put("subjectId", subjectId);
        }
        if (itemId != null && itemId.toString() != "") {
            param.put("itemId", itemId);
        }
        if (topicsId != null && topicsId.toString() != "") {
            param.put("topicsId", topicsId);
        }
        if (typeId != null && typeId.toString() != "") {
            param.put("typeId", typeId);
        }
        if (stageId != null && stageId.toString() != "") {
            param.put("stageId", stageId);
        }

        List<CommodityVo> commList = iCommodityService.getModelListByIds(param);

        modell.addAttribute("modelName", modelName);
        modell.addAttribute("list", list);
        modell.addAttribute("commList", commList);
        return "simpleClasses/appNewClasses/pageRecommendation";
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
                List<SysDictApp> subjct = new ArrayList<>();
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

        //获取课程列表



        return linked;
    }
}
