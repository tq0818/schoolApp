package com.yuxin.wx.controller.institution;

import cn.jpush.api.report.UsersResult;
import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.api.institution.ReServApplyService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.institution.ReServApply;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/InsStudent")
public class StudentManagementController {
@Autowired
private ReServApplyService reServApplyService;
@Autowired
private InstitutionClassTypeService institutionClassTypeService;
@Autowired
private InstitutionInfoService institutionInfoService;

    /**
     * 预约机构下拉列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/studentManagementIndex")
    public String studentManagement(Model model){
        //预约机构下拉框
        try{
            model.addAttribute("insList",reServApplyService.findReServApplyIns());
            Users users = WebUtils.getCurrentUser();
            //机构管理官
            if("INSTITUTION_MANAGE".equals(users.getUserType())){
                //获取所管理的机构
                Map<String,Object> map = new HashMap<>();
                map.put("userId",users.getId());
                InstitutionInfoVo institutionInfoVo = institutionInfoService.queryInstitutionByUserId(map);

                //根据机构id查找该机构下的课程
                InstitutionClassTypeVo institutionClassTypeVo = new InstitutionClassTypeVo();
                institutionClassTypeVo.setId(institutionInfoVo.getId());
                model.addAttribute("insId",institutionInfoVo.getId());
                model.addAttribute("insClassList",institutionClassTypeService.queryReServApplyAllByIns(institutionClassTypeVo));
            }
            model.addAttribute("usesType",users.getUserType());

        }catch (Exception e){
            e.printStackTrace();
        }
        return "institution/studentManagement";
    }


    /**
     * 查找机构下的课程
     * @param model
     * @param insId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findReServApplyClassByInsId",method = RequestMethod.POST)
    public List<InstitutionClassTypeVo> findReServApplyClassByInsId(Model model,Integer insId){
        InstitutionClassTypeVo institutionClassTypeVo = new InstitutionClassTypeVo();
        institutionClassTypeVo.setId(insId);

        return institutionClassTypeService.queryReServApplyAllByIns(institutionClassTypeVo);
    }

    /**
     * 查找预约列表
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findReServApplyList",method = RequestMethod.POST)
    public PageFinder<ReServApply> findReServApplyList(String mobile,Integer dealStatus,Integer insId,Integer insClassId,String startTime,String endTime,Integer page,Integer pageSize) throws ParseException {
        ReServApply reServApply = new ReServApply();
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        reServApply.setMobile(mobile);
        reServApply.setDealStatus(dealStatus);
        reServApply.setInsId(insId);
        reServApply.setInsClassId(insClassId);
        reServApply.setPage(page);
        reServApply.setPageSize(pageSize);
        reServApply.setStartTime(startTime);
        reServApply.setEndTime(endTime);

        return reServApplyService.findReServApplyList(reServApply);
    }

    @ResponseBody
    @RequestMapping(value = "/updateReServApply" ,method = RequestMethod.POST)
    public Integer update(ReServApply reServApply){
        reServApplyService.update(reServApply);
        return 1;
    }


    @RequestMapping(value = "/exportStudent")
    public ModelAndView exportStudent(String mobile,Integer dealStatus,Integer insId,Integer insClassId,String startTime,String endTime,Integer page,Integer pageSize) throws ParseException {
        List<Map<Object,Object>> list = null;
        ReServApply reServApply = new ReServApply();
        reServApply.setMobile(mobile);
        reServApply.setDealStatus(dealStatus);
        reServApply.setInsId(insId);
        reServApply.setInsClassId(insClassId);
        reServApply.setPage(page);
        reServApply.setPageSize(pageSize);
        reServApply.setEndTime(endTime);
        reServApply.setStartTime(startTime);
        if(null != insClassId && !"".equals(insClassId)){
            list = reServApplyService.findReServApplyMapByClass(reServApply);
        }else{
            list = reServApplyService.findReServApplyMap(reServApply);
        }


        /*if(null != insClassId && !"".equals(insClassId)){
            for(int i = 0;i<list.size();i++){
                if(null == list.get(i).get("className") || "".equals(list.get(i).get("className"))){
                    list.remove(i);
                    i--;
                }
            }
        }*/


        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (Map v : list) {
            lists.add(v);
        }
        String titles = "";
        titles = "序号:id,手机号:mobile,预约机构:insName,预约课程:className,课程价格(元):price,提交时间:createTime,处理状态:dealStatus,备注:note";

        StringBuffer title = new StringBuffer(
                titles);
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);

    }

}
