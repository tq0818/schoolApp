package com.yuxin.wx.controller.institution;

import com.yuxin.wx.api.institution.InsFeaturesService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.api.institution.InstitutionLabelService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InsFeaturesVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/InsInfo")
public class InstitutionInfoController {
    @Autowired
    private InstitutionInfoService institutionInfoService;
    @Autowired
    private InstitutionLabelService institutionLabelService;
    @Autowired
    private InsFeaturesService insFeaturesService;
    /**
     * 机构首页
     * @param model
     * @param insInfoVo
     * @return
     */
    @RequestMapping(value = "/organizationIndex")
    public String findIns(Model model, InstitutionInfoVo insInfoVo){
        PageFinder<InstitutionInfoVo> pageFinder = institutionInfoService.findInstitutionInfos(insInfoVo);
        model.addAttribute("pageFinder",pageFinder);
        return "institution/organizationIndex";
    }

    /**
     * 添加机构
     * @param insInfoVo
     */
    @ResponseBody
    @RequestMapping(value = "/addIns",method = RequestMethod.GET)
    public void addIns(InstitutionInfoVo insInfoVo){
        institutionInfoService.insert(insInfoVo);
    }


    /**
     * 机构基本信息
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/findInsById",method = RequestMethod.POST)
    public String  findInsById(Model model,InstitutionInfoVo institutionInfoVo){
        //机构信息
        InstitutionInfoVo ins = institutionInfoService.findInstitutionInfoById(institutionInfoVo.getId());
        //系统标签
        List<InstitutionLabelVo> sysLabel = institutionLabelService.findSysLabelByInsId(institutionInfoVo.getId());
        //自定义标签
        List<InstitutionLabelVo> customLabel = institutionLabelService.findCustomLabelByInsId(institutionInfoVo.getId());
        //特殊服务
        List<InstitutionLabelVo> specialSer = institutionLabelService.findSpecialServiceByInsId(institutionInfoVo.getId());
        model.addAttribute("ins",ins);
        model.addAttribute("sysLabel",sysLabel);
        model.addAttribute("customLabel",customLabel);
        model.addAttribute("specialSer",specialSer);

        return "";
    }

    /**
     * 特殊服务图片
     * @param model
     * @param institutionInfoVo
     * @return
     */
    @RequestMapping(value = "/findSpecialServiceImg",method = RequestMethod.POST)
    public String findSpecialServiceImg(Model model,InstitutionInfoVo institutionInfoVo){
        //特殊服务
        List<InsFeaturesVo> specialSer = insFeaturesService.findInsFeaturesVos();
        Integer specialSerCount = insFeaturesService.findInsFeaturesVosCount();

        PageFinder<InsFeaturesVo> specialSerPage = new PageFinder<>(institutionInfoVo.getPage(),institutionInfoVo.getPageSize(),specialSerCount,specialSer);
        model.addAttribute("specialSerPage",specialSerPage);
        return "";
    }


    @ResponseBody
    @RequestMapping("/updateInsById")
    public Integer updateInsById(InstitutionInfoVo insInfoVo){
        institutionInfoService.updateInsById(insInfoVo);
        return 0;
    }







}
