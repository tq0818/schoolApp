package com.yuxin.wx.controller.riseschool;

import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import com.yuxin.wx.api.riseschool.IRiseSchoolDetailsUpService;
import com.yuxin.wx.api.riseschool.IRiseSchoolDynamicService;
import com.yuxin.wx.model.riseschool.RiseSchoolDetailsUp;
import com.yuxin.wx.model.riseschool.RiseSchoolDynamicVo;
import com.yuxin.wx.model.riseschool.SysDictVo;

/**
 * Created by lym_gxm on 18/2/5.
 */
@Controller
@RequestMapping("/riseschoolback")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EarlyLitreController {
	@Autowired
	private IRiseSchoolDetailsUpService riseSchoolDetailsUpImpl;
    @Autowired
    private RiseSchoolManageService riseSchoolInfoServiceImpl;
    @Autowired
	private IRiseSchoolDynamicService riseSchoolDynamicImpl;
    //私立校后台-学校管理
    @RequestMapping(value = "/earlyLitre")
    public String earlyLitre(HttpServletRequest request, Model model,RiseSchoolManageVo riseSchoolManageVo){
        PageFinder<RiseSchoolManageVo> pageFinder = riseSchoolInfoServiceImpl.queryRiseSchoolInfo(riseSchoolManageVo);
        model.addAttribute("result",pageFinder.getData());
        model.addAttribute("pageNo",riseSchoolManageVo.getPage());
        model.addAttribute("rowCount",pageFinder.getRowCount());
        return "/riseschool/earlyLitre";
    }
    //学校详情
    @RequestMapping(value = "/schoolDetails")
    public String schoolDetails(HttpServletRequest request,Model model,Integer schoolId){
    	//暂时写个死数据
		//schoolId  =1;
		if(schoolId == null){
			return null;
		}
		//学校对应填写了学校详情哪些信息
		Map mapList = new HashMap<>();
		mapList.put("shcoolId", schoolId);
		mapList.put("itemType", "DETAILITEM");
		List<RiseSchoolDetailsUp> queryAllRiseDetails = riseSchoolDetailsUpImpl.queryAllRiseDetails(mapList);
		model.addAttribute("riseDetails", queryAllRiseDetails);
		//加载学校简称或俗称
		Map mapDict = new HashMap<>();
		mapDict.put("itemName", "学校简称或俗称");
		mapDict.put("itemType", "DETAILITEM");
		SysDictVo sysDictVo = riseSchoolDetailsUpImpl.findsysDictByName(mapDict);
		Map map = new HashMap<>();
		map.put("itemCode", sysDictVo.getItemCode());
		map.put("riseSchoolId", schoolId);
		RiseSchoolDetailsUp riseSchoolDetailsUp = riseSchoolDetailsUpImpl.findByidAndCode(map);
		model.addAttribute("riseSchoolDetailsUp", riseSchoolDetailsUp);
		model.addAttribute("riseSchoolId", schoolId);
        return "/riseschool/schoolDetails";
    }
    //升学
    @RequestMapping(value = "/upgradeSchools")
    public String upgradeSchools(HttpServletRequest request,Model model,Integer schoolId) {
    	//暂时写个死数据
    	//schoolId  =1;
    	if(schoolId == null){
			return null;
		}
    	//学校对应填写了升学哪些信息
		Map mapList = new HashMap<>();
		mapList.put("shcoolId", schoolId);
		mapList.put("itemType", "UPSCHOOL");
		List<RiseSchoolDetailsUp> queryAllRiseDetails = riseSchoolDetailsUpImpl.queryAllRiseDetails(mapList);
		model.addAttribute("riseDetails", queryAllRiseDetails);
		//加载学校简称或俗称
		Map mapDict = new HashMap<>();
		mapDict.put("itemName", "招生方式");
		mapDict.put("itemType", "UPSCHOOL");
		SysDictVo sysDictVo = riseSchoolDetailsUpImpl.findsysDictByName(mapDict);
		Map map = new HashMap<>();
		map.put("itemCode", sysDictVo.getItemCode());
		map.put("riseSchoolId", schoolId);
		RiseSchoolDetailsUp riseSchoolDetailsUp = riseSchoolDetailsUpImpl.findByidAndCode(map);
		model.addAttribute("riseSchoolDetailsUp", riseSchoolDetailsUp);
		model.addAttribute("riseSchoolId", schoolId);
		 return "/riseschool/upgradeSchools";
	}
    //基本信息
    @RequestMapping(value = "/essential")
    public String essential(){

        return "/riseschool/essential";
    }
	//动态
	@RequestMapping(value = "/dynamic")
	public String dynamic(HttpServletRequest request,Model model,Integer schoolId){
    	if(schoolId == null){
			return null;
		}
    	model.addAttribute("riseSchoolId", schoolId);
		return "/riseschool/dynamic";
	}
	//学校风采
	@RequestMapping(value = "/mien")
	public String mien(){
		return "/riseschool/mien";
	}

}

