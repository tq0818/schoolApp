package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.system.ISysConfigVideoPriceService;
import com.yuxin.wx.model.system.SysConfigVideoPrice;

/**
 * Controller of SysConfigVideoPrice
 * @author wang.zx
 * @date 2015-4-28
 */
@Controller
@RequestMapping("/sysConfigVideoPrice")
public class SysConfigVideoPriceController {
	
	@Autowired
	private ISysConfigVideoPriceService sysConfigVideoPriceServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigVideoPrice search){
		if (search == null) {
			search = new SysConfigVideoPrice();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigVideoPriceServiceImpl.findSysConfigVideoPriceByPage(search));
		return "sysConfigVideoPrice/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigVideoPrice SysConfigVideoPrice) {
		sysConfigVideoPriceServiceImpl.insert(SysConfigVideoPrice);
		return "redirect:/sysConfigVideoPrice";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigVideoPrice SysConfigVideoPrice) {
		sysConfigVideoPriceServiceImpl.update(SysConfigVideoPrice);
		return "redirect:/sysConfigVideoPrice";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigVideoPriceServiceImpl.deleteSysConfigVideoPriceById(id);
		return "redirect:/sysConfigVideoPrice";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigVideoPrice getJson(Model model, @PathVariable Integer id){
		return sysConfigVideoPriceServiceImpl.findSysConfigVideoPriceById(id);
	}
	
	/**
	 * 后台接收Date转换
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
