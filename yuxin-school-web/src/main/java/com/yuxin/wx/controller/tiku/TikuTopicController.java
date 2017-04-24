package com.yuxin.wx.controller.tiku;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.model.tiku.TikuTopic;

/**
 * Controller of TikuTopic
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuTopic")
public class TikuTopicController {
	
	@Autowired
	private ITikuTopicService tikuTopicServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuTopic search){
		if (search == null) {
			search = new TikuTopic();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuTopicServiceImpl.findTikuTopicByPage(search));
		return "tikuTopic/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuTopic TikuTopic) {
		tikuTopicServiceImpl.insert(TikuTopic);
		return "redirect:/tikuTopic";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TikuTopic TikuTopic) {
		tikuTopicServiceImpl.update(TikuTopic);
		return "redirect:/tikuTopic";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuTopicServiceImpl.deleteTikuTopicById(id);
		return "redirect:/tikuTopic";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuTopic getJson(Model model, @PathVariable Integer id){
		return tikuTopicServiceImpl.findTikuTopicById(id);
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
	
	@RequestMapping(value="testEdit")
	public String testEdit(HttpServletRequest request){
		return "tiku/question/testEdit";
	}
}
