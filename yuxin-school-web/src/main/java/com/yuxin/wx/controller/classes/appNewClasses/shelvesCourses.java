package com.yuxin.wx.controller.classes.appNewClasses;


import java.util.ArrayList;
import java.util.List;

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
    public String gotoShelvesCourses(Model model,HttpServletRequest request,Integer id){
    	/*List<SysDictApp> firstList = new ArrayList<SysDictApp>();
    	List<SysDictApp> secondList = new ArrayList<SysDictApp>();
    	List<SysDictApp> thirdList = new ArrayList<SysDictApp>();
    	List<SysDictApp> fourthList = new ArrayList<SysDictApp>();
//		Integer id = (Integer) request.getAttribute("id");
		
    	List<SysDictApp> list = sysDictAppServiceImpl.findSysDictAppByParentId(0);
    	for (SysDictApp sysDictApp : list) {
    		if (sysDictApp.getLevel() == 1) {
				firstList.add(sysDictApp);
    		}
		}
    	List<SysDictApp> list2 = sysDictAppServiceImpl.findSysDictAppByParentId(id);
    	
		for (SysDictApp sysDictApp : list2) {
			if (sysDictApp.getLevel() == 1) {
				firstList.add(sysDictApp);
			} else if (sysDictApp.getLevel() == 2) {
				secondList.add(sysDictApp);
			} else if (sysDictApp.getLevel() == 3) {
				thirdList.add(sysDictApp);
			} else if (sysDictApp.getLevel() == 4) {
//				sysDictApp.setListName(sysDictApp.getListCode());
				fourthList.add(sysDictApp);
//				System.out.println(re.getItemName());
			}
//			for (SysConfigItem na : names) {
//				if (re.getItemCode().equals(na.getItemCode())) {
//					re.setItemName(na.getItemName());
//					System.out.println(re.getItemName());
//					break;
//				}
//			}
		}
		model.addAttribute("firstList", firstList);
		model.addAttribute("secondList", secondList);
		model.addAttribute("thirdList", thirdList);
		model.addAttribute("fourthList", fourthList);*/
    	
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



}
