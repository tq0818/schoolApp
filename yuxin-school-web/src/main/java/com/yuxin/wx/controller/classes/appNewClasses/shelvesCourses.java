package com.yuxin.wx.controller.classes.appNewClasses;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.model.app.SysDictApp;


/**
 * 已上架课程
 */
@Controller
@RequestMapping("/appNewClasses")
public class  shelvesCourses {
	@Autowired
	private ISysDictAppService sysDictAppServiceImpl;
    /**
     * 跳转到已上架课程页面
     * @return
     */
    @RequestMapping(value="/shelvesCourses",method=RequestMethod.GET)
    public String gotoShelvesCourses(Model model,HttpServletRequest request){
        //获取一级菜单
        Map<String,List<SysDictApp>> linked = new LinkedHashMap<String,List<SysDictApp>>();
        SysDictApp search = new SysDictApp();
        List<SysDictApp> slibMenus = sysDictAppServiceImpl.findSysDictAppByParentId(search);
        model.addAttribute("firstMenus",slibMenus);
        return "simpleClasses/appNewClasses/shelvesCourses";
    }
    /**
     * 跳转到上架信息编辑
     */
    @RequestMapping(value="/InformationEditing")
    public String gotoInformationEditing(){
        return "simpleClasses/appNewClasses/informationEditing";
    }
    /**
     * 跳转到首页推荐专题列表
     */
    @RequestMapping(value="/recommendedList")
    public String gotorecommendedList(){
        return "simpleClasses/appNewClasses/recommendedList";
    }
    /**
     * 跳转到批量首页推荐
     */
    @RequestMapping(value="/pageRecommendation")
    public String gotopageRecommendation(){
        return "simpleClasses/appNewClasses/pageRecommendation";
    }
    /**
     * 跳转到推荐列表
     */
    @RequestMapping(value="/recommendSpecialList")
    public String gotorecommendSpecialList(){
        return "simpleClasses/appNewClasses/recommendSpecialList";
    }

	/**
	 * 跳转到首页推荐
	 */
	@RequestMapping(value="/homeRecommendation")
	public String gotohomeRecommendation(){
		return "simpleClasses/appNewClasses/homeRecommendation";
	}



}
