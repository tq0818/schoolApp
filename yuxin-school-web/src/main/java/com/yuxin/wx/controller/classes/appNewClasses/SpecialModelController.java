package com.yuxin.wx.controller.classes.appNewClasses;


import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.vo.classes.ClassVo;
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
     * @param model
     * @param categoryid        模块ID
     * @param gradeId 学段ID
     * @param subjectid      科目ID
     * @param knowId         知识点ID
     * @param knowledgeid       知识点专题ID
     * @param typeId         类型ID
     * @param stageid        阶段ID
     * @return
     */
    @RequestMapping(value = "/getModelListByIds",method = RequestMethod.POST)
    public String getModelListByIds(Model model,Integer categoryid, Integer gradeid,
                                    Integer subjectid, Integer knowledgeProid, Integer knowledgeid, Integer typeCode, Integer stageid,Integer page,Integer pageSize) {
        //根据传入的各个ID查询课程list
        Map<String, Object> param = new HashMap<>();
        if (categoryid != null && categoryid.toString() != "") {
            param.put("categoryid", categoryid);
        }
        if (gradeid != null && gradeid.toString() != "") {
            param.put("studySectionId", gradeid);
        }
        if (subjectid != null && subjectid.toString() != "") {
            param.put("subjectId", subjectid);
        }
        if (knowledgeProid != null && knowledgeProid.toString() != "") {
            param.put("itemId", knowledgeProid);
        }
        if (knowledgeid != null && knowledgeid.toString() != "") {
            param.put("topicsId", knowledgeid);
        }
        if (typeCode != null && typeCode.toString() != "") {
            param.put("typeId", typeCode);
        }
        if (stageid != null && stageid.toString() != "") {
            param.put("stageId", stageid);
        }
        List<CommodityVo> commList = iCommodityService.getModelListByIds(param);
//        int count = iCommodityService.getModelListByIdsCount(param);
//        PageFinder<CommodityVo> classVoPage = new PageFinder<>(page,pageSize,count,commList);
        model.addAttribute("commList",commList);
        return "simpleClasses/appNewClasses/classListAjax";
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
