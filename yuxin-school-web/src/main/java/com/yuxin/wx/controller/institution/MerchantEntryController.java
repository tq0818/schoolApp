package com.yuxin.wx.controller.institution;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.institution.MerchantEntryService;
import com.yuxin.wx.model.institution.DimQueryMerchantVo;
import com.yuxin.wx.model.institution.MerchantEntryVo;

@Controller
@RequestMapping("/merchantEntry")
public class MerchantEntryController {
	@Autowired
	private MerchantEntryService merchantEntryServiceImpl;
	//商家入驻申请
    @RequestMapping(value = "/businessEntry")
    public String businessEntry(){
    	return "institution/businessEntry";  
    }
    
    /**
     * 查询机构申请
     * @param merchantEntryVo
     * @param model
     * @return
     */
    @RequestMapping(value="/queryMerchantEntry")
    public String queryMerchantEntry(MerchantEntryVo merchantEntryVo,Model model){
    	Integer page = merchantEntryVo.getPage();
    	if(page == null || page.intValue() == 0){
    		page = 1;
    		merchantEntryVo.setPage(page);
    	}
//    	merchantEntryVo.setPageSize(10);
    	List<MerchantEntryVo> list = merchantEntryServiceImpl.queryMerchantEntry(merchantEntryVo);
    	Integer count = merchantEntryServiceImpl.merchantEntryCount();
    	model.addAttribute("result", list);
    	model.addAttribute("rowCount", count);
    	model.addAttribute("pageNo", page);
    	return "institution/merchantEntry";
    }
    
    /**
     * 模糊查询
     * @param merchantEntryVo
     * @param model
     * @return
     */
    @RequestMapping(value="/dimMerchantEntry",method=RequestMethod.POST)
    public String dimMerchantEntry(DimQueryMerchantVo merchantEntryVo,Model model){
    	Integer page = merchantEntryVo.getPage();
    	if(page == null || page.intValue() == 0){
    		page = 1;
    		merchantEntryVo.setPage(page);
    	}
    	List<MerchantEntryVo> list = merchantEntryServiceImpl.dimMerchantEntry(merchantEntryVo);
    	Integer count = merchantEntryServiceImpl.dimMerchantEntryCount(merchantEntryVo);
    	if (page.intValue() - 1 == 1 && list !=null && list.size() == 0) {
    		page = page.intValue() - 1;
    		merchantEntryVo.setPage(page);
    		list = merchantEntryServiceImpl.dimMerchantEntry(merchantEntryVo);
    		count = merchantEntryServiceImpl.dimMerchantEntryCount(merchantEntryVo);
		}
    	model.addAttribute("result", list);
    	model.addAttribute("rowCount", count);
    	model.addAttribute("pageNo", page);
    	return "institution/merchantEntry";
    }
    
    /**
     * 更新商家入驻申请
     * @param merchantEntryVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateMerchantEntry",method=RequestMethod.POST)
    public String updateMerchantEntry(MerchantEntryVo merchantEntryVo){
    	merchantEntryVo.setUpdateTime(new Date());
    	merchantEntryServiceImpl.updateMerchanrEntry(merchantEntryVo);
    	return "success";
    }
    
    
    /**
     * 更新商家入驻申请备注
     * @param merchantEntryVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateMerchanrEntryNote",method=RequestMethod.POST)
    public String updateMerchanrEntryNote(MerchantEntryVo merchantEntryVo){
    	merchantEntryVo.setUpdateTime(new Date());
    	merchantEntryServiceImpl.updateMerchanrEntryNote(merchantEntryVo);
    	return "success";
    }
}
