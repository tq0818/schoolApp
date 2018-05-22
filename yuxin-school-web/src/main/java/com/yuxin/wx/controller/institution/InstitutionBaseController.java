package com.yuxin.wx.controller.institution;

import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lym_gxm on 18/5/6.
 */
@Controller
@RequestMapping("/InsInfoBase")
public class InstitutionBaseController {

    @Autowired
    private InstitutionInfoService institutionInfoService;

    @RequestMapping(value = "/organizationIndexZYT")
    public String findInsInfoList(){
        return "institution/organizationIndex";
    }
    //课程管理
    @RequestMapping(value = "/course")
    public String course(){
        return "institution/course";
    }
    //机构分类管理
    @RequestMapping(value = "/classification")
    public String classification(){

        return "institution/classification";
    }
    //基本信息管理
    @RequestMapping(value = "/basicInformation")
    public String basicInformation(){
        return "institution/basicInformation";
    }
    //推荐机构管理
    @RequestMapping(value = "/recommendationOrganization")
    public String recommendationOrganization(){
        return "institution/recommendationOrganization";
    }
    //风采管理
    @RequestMapping(value = "/elegantDemeanor")
    public String elegantDemeanor(){
        return "institution/elegantDemeanor";
    }
    //新增线下课程
    @RequestMapping(value = "/newLineCourse/{insId}/{id}")
    public String newLineCourse(Model model, @PathVariable Integer insId,@PathVariable Integer id){
        model.addAttribute("underLineId",id);
        model.addAttribute("insId",insId);
        return "institution/newLineCourse";
    }
    //首页推荐分类管理
    @RequestMapping(value = "/recommendationHomeC/{type}")
    public String recommendationHomeClassification(Model model, @PathVariable Integer type){
        model.addAttribute("recommendType",type);
        return "institution/recommendationHomeC";
    }
    //单独机构评价管理
    @RequestMapping(value = "/evaluation")
    public String evaluation(){
        return "institution/evaluation";
    }
    //学员管理
    @RequestMapping(value = "/studentManagement")
    public String studentManagement(){
        return "institution/studentManagement";
    }
    //商家入驻申请
    @RequestMapping(value = "/businessEntry")
    public String businessEntry(){        return "institution/businessEntry";  }
    //名师管理
    @RequestMapping(value = "/famousTeacher/{insId}")
    public String famousTeacher(Model model, @PathVariable Integer insId){
        InstitutionInfoVo ins = institutionInfoService.findInstitutionInfoById(insId);
        model.addAttribute("ins",ins);

        model.addAttribute("insId",insId);
        return "institution/famousTeacher";
    }
    //机构评价管理列表
    @RequestMapping(value = "/evaluationList")
    public String evaluationList(){
        return "institution/evaluationList";
    }
    //添加老师
    @RequestMapping(value = "/addFamousTeacher")
    public String addFamousTeacher(){
        return "institution/addFamousTeacher";
    }


}
