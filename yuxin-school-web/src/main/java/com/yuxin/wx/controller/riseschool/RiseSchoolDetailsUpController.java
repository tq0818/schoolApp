package com.yuxin.wx.controller.riseschool;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.riseschool.IRiseSchoolDetailsUpService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.riseschool.RiseSchoolDetailsUp;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.utils.WebUtils;

/**
 * 学校详情及升学情况
 */
@Controller
@RequestMapping("/riseSchoolDetailsUp")
public class RiseSchoolDetailsUpController {
	@Autowired
	private IRiseSchoolDetailsUpService riseSchoolDetailsUpImpl;
	/**
	 * 跳转学校详情页面
	 * @param request
	 * @param model
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/schoolDetails")
    public String schoolDetails(HttpServletRequest request,Model model,Integer schoolId) {
		//暂时写个死数据
		schoolId  =1;
		//学校对应填写了学校详情哪些信息
		Map mapList = new HashMap<>();
		mapList.put("schoolId", schoolId);
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
	/**
	 * 跳转升学页面
	 * @param request
	 * @param model
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/upgradeSchool")
	public String upgradeSchool(HttpServletRequest request,Model model,Integer schoolId) {
		//暂时写个死数据
		schoolId  =1;
		//学校对应填写了升学哪些信息
		Map mapList = new HashMap<>();
		mapList.put("schoolId", schoolId);
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
		return "banner/jinyong/jinyong";
	}
	
	/**
	 * 新增或者修改学校详细
	 * @param request
	 * @param riseSchoolDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrupdateSchoolDetails")
	public String saveOrupdateSchoolDetails(HttpServletRequest request,RiseSchoolDetailsUp riseSchoolDetails) {
		//学校id
		String riseSchoolId = riseSchoolDetails.getRiseSchoolId();
		//内容
		String itemDiscrible = riseSchoolDetails.getItemDiscrible();
		String itemName = riseSchoolDetails.getItemName();
		//查询字典表的学校详情
		Map mapDict = new HashMap<>();
		mapDict.put("itemName", itemName);
		mapDict.put("itemType", "DETAILITEM");
		SysDictVo sysDictVo = riseSchoolDetailsUpImpl.findsysDictByName(mapDict);
		if(sysDictVo == null){
			return "false";
		}
		try {
			Map map = new HashMap<>();
			map.put("itemCode", sysDictVo.getItemCode());
			map.put("riseSchoolId", riseSchoolId);
			RiseSchoolDetailsUp riseDetaile = riseSchoolDetailsUpImpl.findByidAndCode(map);
			if(riseDetaile == null){
				//新增
				riseSchoolDetails.setItemCode(sysDictVo.getItemCode());
				riseSchoolDetailsUpImpl.insert(riseSchoolDetails);
			}else{
				//修改
				riseDetaile.setItemDiscrible(itemDiscrible);
				riseSchoolDetailsUpImpl.update(riseDetaile);
			}
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "false";
		}
	}
	/**
	 * 新增或者修改升学
	 * @param request
	 * @param riseSchoolDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrupdateUpgradeSchool")
	public String saveOrupdateUpgradeSchool(HttpServletRequest request,RiseSchoolDetailsUp riseSchoolDetails) {
		//学校id
		String riseSchoolId = riseSchoolDetails.getRiseSchoolId();
		//内容
		String itemDiscrible = riseSchoolDetails.getItemDiscrible();
		String itemName = riseSchoolDetails.getItemName();
		//查询字典表的学校详情
		Map mapDict = new HashMap<>();
		mapDict.put("itemName", itemName);
		mapDict.put("itemType", "UPSCHOOL");
		SysDictVo sysDictVo = riseSchoolDetailsUpImpl.findsysDictByName(mapDict);
		if(sysDictVo == null){
			return "false";
		}
		try {
			Map map = new HashMap<>();
			map.put("itemCode", sysDictVo.getItemCode());
			map.put("riseSchoolId", riseSchoolId);
			RiseSchoolDetailsUp riseDetaile = riseSchoolDetailsUpImpl.findByidAndCode(map);
			if(riseDetaile == null){
				//新增
				riseSchoolDetails.setItemCode(sysDictVo.getItemCode());
				riseSchoolDetailsUpImpl.insert(riseSchoolDetails);
			}else{
				//修改
				riseDetaile.setItemDiscrible(itemDiscrible);
				riseSchoolDetailsUpImpl.update(riseDetaile);
			}
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "false";
		}
	}
	
	
	/**
	 * 加载学校详细内容
	 * @param request
	 * @param riseSchoolDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryRiseDetails")
	public RiseSchoolDetailsUp queryRiseDetails(HttpServletRequest request,RiseSchoolDetailsUp riseSchoolDetails) {
		//学校id
		String riseSchoolId = riseSchoolDetails.getRiseSchoolId();
		//字典表名称
		String itemName = riseSchoolDetails.getItemName();
		Map mapDict = new HashMap<>();
		mapDict.put("itemName", itemName);
		mapDict.put("itemType", "DETAILITEM");
		SysDictVo sysDictVo = riseSchoolDetailsUpImpl.findsysDictByName(mapDict);
		if(sysDictVo == null){
			return null;
		}
		Map map = new HashMap<>();
		map.put("itemCode", sysDictVo.getItemCode());
		map.put("riseSchoolId", riseSchoolId);
		RiseSchoolDetailsUp riseDetaile = riseSchoolDetailsUpImpl.queryRiseDetails(map);
		if(riseDetaile == null ){
			return null;
		}else{
			return riseDetaile;
		}
	}
	/**
	 * 加载升学内容
	 * @param request
	 * @param riseSchoolDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryUpgradeSchool")
	public RiseSchoolDetailsUp queryUpgradeSchool(HttpServletRequest request,RiseSchoolDetailsUp riseSchoolDetails) {
		//学校id
		String riseSchoolId = riseSchoolDetails.getRiseSchoolId();
		//字典表名称
		String itemName = riseSchoolDetails.getItemName();
		Map mapDict = new HashMap<>();
		mapDict.put("itemName", itemName);
		mapDict.put("itemType", "UPSCHOOL");
		SysDictVo sysDictVo = riseSchoolDetailsUpImpl.findsysDictByName(mapDict);
		if(sysDictVo == null){
			return null;
		}
		Map map = new HashMap<>();
		map.put("itemCode", sysDictVo.getItemCode());
		map.put("riseSchoolId", riseSchoolId);
		RiseSchoolDetailsUp riseDetaile = riseSchoolDetailsUpImpl.queryRiseDetails(map);
		if(riseDetaile == null ){
			return null;
		}
		return riseDetaile;
	}
}
