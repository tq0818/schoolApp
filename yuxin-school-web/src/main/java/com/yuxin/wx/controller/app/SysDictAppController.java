package com.yuxin.wx.controller.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/sysDictApp")
public class SysDictAppController {
	@Autowired
	private ISysDictAppService sysDictAppServiceImpl;
	
	/*@RequestMapping(value="/showSys",method=RequestMethod.POST)
	public String showClassTypePage(Model model,Integer id,HttpServletRequest request){
		List<SysDictApp> list = sysDictAppServiceImpl.findSysDictAppByParentId(id);
		model.addAttribute("list", list);
//		return "WWWW";
		return "appNewClasses/shelvesCourses";
	}*/
}
