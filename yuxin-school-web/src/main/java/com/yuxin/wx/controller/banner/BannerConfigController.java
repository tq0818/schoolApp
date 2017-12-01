package com.yuxin.wx.controller.banner;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.yuxin.wx.api.certificate.ICertificateConfigService;
import com.yuxin.wx.api.certificate.ICertificateCourseRelationService;
import com.yuxin.wx.api.certificate.ICertificateUserRelationService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.certificate.CertificateConfig;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.utils.CertificateUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.certificate.CertificateConfigVo;
import com.yuxin.wx.vo.certificate.CertificateUserRelationVo;

/**
 * Controller of CertificateConfig
 * @author chopin
 * @date 2016-9-22
 */
@Controller
@RequestMapping("/Banner")
public class BannerConfigController extends BaseWebController{
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	Log log = LogFactory.getLog("log");
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String list(Model model, CertificateConfig search){
//		if (search == null) {
//			search = new CertificateConfig();
//			// search.setPageSize(20);
//		}
//		model.addAttribute("list", certificateConfigServiceImpl.findCertificateConfigByPage(search));
//		return "certificateConfig/list";
//	}
	
	@RequestMapping(value="/comBannerIndex", method = RequestMethod.GET)
	public String add(CertificateConfig CertificateConfig) {
		
		return "";
	}
	
	
}
