package com.yuxin.wx.controller.riseschool;

import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.riseschool.RiseSchoolStyleService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.*;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import com.yuxin.wx.api.riseschool.IRiseSchoolDetailsUpService;
import com.yuxin.wx.api.riseschool.IRiseSchoolDynamicService;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lym_gxm on 18/2/5.
 */
@Controller
@RequestMapping("/riseschoolback")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EarlyLitreController extends BaseRiseSchoolController{
	@Autowired
	private IRiseSchoolDetailsUpService riseSchoolDetailsUpImpl;
	@Autowired
    private RiseSchoolManageService riseSchoolInfoServiceImpl;
    @Autowired
	private IRiseSchoolDynamicService riseSchoolDynamicImpl;
	@Autowired
	private RiseSchoolStyleService riseSchoolStyleServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
    //私立校后台-学校管理
    @RequestMapping(value = "/earlyLitre")
    public Object earlyLitre(HttpServletRequest request, Model model,RiseSchoolManageVo riseSchoolManageVo){
		ModelAndView mv = new ModelAndView();
		Users user = WebUtils.getCurrentUser(request);
		if("RISE_SCHOOL_MANAGER".equals(user.getUserType())){
			Map<String,Object>params = new HashMap<String,Object>();
			params.put("userId",user.getId());
			RiseSchoolManageVo rsieSchool = riseSchoolManageServiceImpl.queryCurrentRiseSchoolInfo(params);
			mv.setViewName("redirect:/riseschoolback/essential?schoolId="+rsieSchool.getId()+"&schoolName="+rsieSchool.getSchoolName());
			return mv;
		}
        PageFinder<RiseSchoolManageVo> pageFinder = riseSchoolManageServiceImpl.queryRiseSchoolInfo(riseSchoolManageVo);
        model.addAttribute("result",pageFinder.getData());
        model.addAttribute("pageNo",riseSchoolManageVo.getPage());
        model.addAttribute("rowCount",pageFinder.getRowCount());
        return "/riseschool/earlyLitre";
    }
    //学校详情
    @RequestMapping(value = "/schoolDetails")
    public String schoolDetails(HttpServletRequest request,Model model,Integer schoolId,String schoolName){
    	//暂时写个死数据
		//schoolId  =1;
		if(schoolId == null){
			return null;
		}
		//如果输入非法参数调回首页
		String isReturn = this.returnIndex(request, model, schoolId, schoolName);
    	if(null!=isReturn){
    		return isReturn;
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
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("schoolName", schoolName);
        return "/riseschool/schoolDetails";
    }
    //升学
    @RequestMapping(value = "/upgradeSchools")
    public String upgradeSchools(HttpServletRequest request,Model model,Integer schoolId,String schoolName) {
    	//暂时写个死数据
    	//schoolId  =1;
    	if(schoolId == null){
			return null;
		}
    	//如果输入非法参数调回首页
		String isReturn = this.returnIndex(request, model, schoolId, schoolName);
    	if(null!=isReturn){
    		return isReturn;
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
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("schoolName", schoolName);
		 return "/riseschool/upgradeSchools";
	}
    //基本信息
    @RequestMapping(value = "/essential")
    public String essential(HttpServletRequest request,Model model,Integer schoolId,String schoolName){
//		Users user = WebUtils.getCurrentUser(request);
    	//如果输入非法参数调回首页
		String isReturn = this.returnIndex(request, model, schoolId, schoolName);
    	if(null!=isReturn){
    		return isReturn;
    	}
    	RiseSchoolManageVo riseSchoolManageVo = new RiseSchoolManageVo();
    	riseSchoolManageVo.setId(schoolId);
		PageFinder<RiseSchoolManageVo> pageFinder = riseSchoolManageServiceImpl.queryRiseSchoolInfo(riseSchoolManageVo);
		List<RiseSchoolManageVo> list = pageFinder.getData();
		model.addAttribute("result",(RiseSchoolManageVo)list.get(0));
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("schoolName",schoolName);
//		model.addAttribute("userType",user.getUserType());
		RiseSchoolManageVo riseSchoolManageVo1 = (RiseSchoolManageVo)list.get(0);
		Map map = new HashMap();
		//查询区域
		map.clear();
		map.put("itemType","DISTRICT");
		map.put("parentCode",riseSchoolManageVo1.getCityCode());
		List<SysDictVo> areaList = riseSchoolManageServiceImpl.queryRiseSchoolDict(map);
		model.addAttribute("areaList",areaList);
        return "/riseschool/essential";
    }
	//动态
	@RequestMapping(value = "/dynamic")
	public String dynamic(HttpServletRequest request,Model model,Integer schoolId,String schoolName){
    	if(schoolId == null){
			return null;
		}
    	//如果输入非法参数调回首页
		String isReturn = this.returnIndex(request, model, schoolId, schoolName);
    	if(null!=isReturn){
    		return isReturn;
    	}
    	model.addAttribute("riseSchoolId", schoolId);
    	model.addAttribute("schoolId", schoolId);
    	model.addAttribute("schoolName", schoolName);
		return "/riseschool/dynamic";
	}
	//学校风采
	@RequestMapping(value = "/mien")
	public String mien(HttpServletRequest request,Model model,Integer schoolId,String schoolName){
		//查询不是封面图片
		//如果输入非法参数调回首页
		String isReturn = this.returnIndex(request, model, schoolId, schoolName);
    	if(null!=isReturn){
    		return isReturn;
    	}
		RiseSchoolStyleVo riseSchoolStyleVo = new RiseSchoolStyleVo();
		riseSchoolStyleVo.setRiseSchoolId(schoolId);
		riseSchoolStyleVo.setIsCover(0);
		riseSchoolStyleVo.setPageSize(6);
		PageFinder<RiseSchoolStyleVo> pageFinder = riseSchoolStyleServiceImpl.queryRiseSchoolStyle(riseSchoolStyleVo);
		String url = "http://"+propertiesUtil.getProjectImageUrl()+"/";
		//处理图片
		if (pageFinder.getData() != null && pageFinder.getData().size() > 0){
			List<RiseSchoolStyleVo> list = pageFinder.getData();
			for(RiseSchoolStyleVo r:list){
				r.setImgUrl(url + r.getImgUrl());
			}
		}
		//查询封面图
		Map map = new HashMap();
		map.put("schoolId",schoolId);
		map.put("isCover",1);
		RiseSchoolStyleVo coverVo = riseSchoolStyleServiceImpl.queryRiseSchoolStyleById(map);
		if (coverVo != null){
			coverVo.setImgUrl(url+coverVo.getImgUrl());
		}
		model.addAttribute("result",pageFinder.getData());
		model.addAttribute("coverVo",coverVo);
		model.addAttribute("pageNo",riseSchoolStyleVo.getPage());
		model.addAttribute("rowCount",pageFinder.getRowCount());
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("schoolName",schoolName);
		return "/riseschool/mien";
	}

}

