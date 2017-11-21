package com.yuxin.wx.controller.classes.appNewClasses;


import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.classes.FirstRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.model.app.SysDictApp;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 已上架课程
 */
@Controller
@RequestMapping("/appNewClasses")
public class shelvesCourses {
    @Autowired
    private ISysDictAppService sysDictAppServiceImpl;

    @Autowired
    private ICommodityService iCommodityService;
    @Autowired
    private IClassTypeService classTypeServiceImpl;

    /**
     * 跳转到已上架课程页面
     *
     * @return
     */
    @RequestMapping(value = "/shelvesCourses", method = RequestMethod.GET)
    public String gotoShelvesCourses(Model model, HttpServletRequest request) {
        //获取一级菜单
        Map<String, List<SysDictApp>> linked = new LinkedHashMap<String, List<SysDictApp>>();
        SysDictApp search = new SysDictApp();
        List<SysDictApp> slibMenus = sysDictAppServiceImpl.findSysDictAppByParentId(search);
        model.addAttribute("firstMenus", slibMenus);
        return "simpleClasses/appNewClasses/shelvesCourses";
    }

    /**
     * 跳转到上架信息编辑
     */
    @RequestMapping(value = "/InformationEditing")
    public String gotoInformationEditing() {
        return "simpleClasses/appNewClasses/informationEditing";
    }

    /**
     * 跳转到首页推荐专题列表
     */
    @RequestMapping(value = "/recommendedList")
    public String gotorecommendedList() {
        return "simpleClasses/appNewClasses/recommendedList";
    }

    /**
     * 跳转到批量首页推荐
     */
    @RequestMapping(value = "/pageRecommendation", method = RequestMethod.GET)
    public String gotopageRecommendation(Model model, Integer modelId, Integer parentId) throws UnsupportedEncodingException {
        //获取二级菜单
        List<SysDictApp> menusList = sysDictAppServiceImpl.getStudySectionById(modelId);

        //获取课程分类名称
        List<SysDictApp>grades = new ArrayList<SysDictApp>();
        List<SysDictApp>stages = new ArrayList<SysDictApp>();
        List<SysDictApp>types = new ArrayList<SysDictApp>();
        for(SysDictApp s  : menusList){
           if(s.getType().equals("STAGE")){
               stages.add(s);
           }else if(s.getType().equals("TYPE")){
               types.add(s);
           }else{
               grades.add(s);
           }
        }
        model.addAttribute("grades", grades);
        model.addAttribute("stages", stages);
        model.addAttribute("types", types);

        String  mokelName =  sysDictAppServiceImpl.getModelById(modelId);
        model.addAttribute("modelName", mokelName);
        model.addAttribute("modelId",modelId);

        //查询课程列表

        return "simpleClasses/appNewClasses/pageRecommendation";
    }

    /**
     * 跳转到推荐列表
     */
    @RequestMapping(value = "/recommendSpecialList", method = RequestMethod.POST)
    public String gotorecommendSpecialList(Model model) {

        return "simpleClasses/appNewClasses/recommendSpecialList";
    }

    /**
     * 跳转到首页推荐
     */
    @RequestMapping(value = "/homeRecommendation",method=RequestMethod.POST)
    public String gotohomeRecommendation(HttpServletRequest request,Model model) {
        String categerorId = request.getParameter("categerorId");
        String zhiboFlag = request.getParameter("zhiboFlag");
        String commodityId = request.getParameter("commodityId");
        SysDictApp search = new SysDictApp();
        List<SysDictApp> slibMenus = sysDictAppServiceImpl.findSysDictAppByParentId(search);
        if(null!=slibMenus && slibMenus.size()>0){
            for(SysDictApp sda : slibMenus){
                if(String.valueOf(sda.getId()).equals(categerorId)){
                    model.addAttribute("firstMenu",sda);
                    break;
                }
            }
        }
        ClassTypeVo searchAndResult = new ClassTypeVo();
        if("1".equals(zhiboFlag)){
            //查询直播课程信息
            searchAndResult.setId(Integer.parseInt(commodityId));
            searchAndResult = classTypeServiceImpl.querySingleLiveClassTypeInfo(searchAndResult);
        }else{
            //查询录播信息
            searchAndResult.setId(Integer.parseInt(commodityId));
            searchAndResult = classTypeServiceImpl.querySingOtherClassTypeInfo(searchAndResult);
        }
        model.addAttribute("searchAndResult",searchAndResult);

        return "simpleClasses/appNewClasses/homeRecommendation";
    }

    @ResponseBody
    @RequestMapping(value="/insertRcommondInfo",method=RequestMethod.POST)
    public String insertRcommondInfo(HttpServletRequest request,String ids,String sort,String appId){

        try {
            List<FirstRecommend> frs = new ArrayList<FirstRecommend>();
            if(null!=ids && !"".equals(ids)){
                String[]idStrs = ids.split(",");
                for(String idStr : idStrs){
                    FirstRecommend fr = new FirstRecommend();
                    fr.setAppShelvesId(appId);
                    fr.setGradeNo(idStr);

                    if("".equals(sort) || null==sort){
                        fr.setSort(null);
                    }else{
                        fr.setSort(sort);
                    }
                    frs.add(fr);
                }
                classTypeServiceImpl.insertFirstRecommond(frs);
            }
            return "1";
        }catch (Exception e){
            return "0";
        }
    }

}
