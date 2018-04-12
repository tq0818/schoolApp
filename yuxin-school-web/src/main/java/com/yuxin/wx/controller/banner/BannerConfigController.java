package com.yuxin.wx.controller.banner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.banner.IBannerService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.banner.Banner;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyPicsVo;

import net.sf.json.JSONObject;

/**
 * Controller of CertificateConfig
 * @author chopin
 * @date 2016-9-22
 */
@Controller
@RequestMapping("/Banner")
public class BannerConfigController extends BaseWebController{
	
	@Autowired
	private IBannerService bannerService;
	 @Autowired
    private PropertiesUtil propertiesUtil;
	Log log = LogFactory.getLog("log");
	

	//跳转首页banner
	@RequestMapping(value="/comBannerIndex", method = RequestMethod.GET)
	public String search(HttpServletRequest request,Model model,Banner banner) {

		return "banner/bannerIndex";
	}
	//跳转小升初banner
	@RequestMapping(value="/riseBannerIndex", method = RequestMethod.GET)
	public String riseBannerIndex(HttpServletRequest request,Model model,Banner banner) {
		
		return "riseschool/riseSchoolBanner";
	}
	//跳转首页通栏Banner
	@RequestMapping(value="/acrcoBannerIndex", method = RequestMethod.GET)
	public String acrcoBannerIndex(HttpServletRequest request,Model model,Banner banner) {
		
		return "riseschool/acrcoBannerIndex";
	}
	
	/**
	 * 
	 * @author jishangyang 2017年12月1日 下午4:54:17
	 * @Method: qiYong 
	 * @Description: 异步加载启用
	 * @param request
	 * @param model
	 * @param banner
	 * @return 
	 * @throws
	 */
    @RequestMapping("/qiYong")
    public String qiYong(HttpServletRequest request,Model model,Banner banner) {
		banner.setIsState(1);
		List <Banner> qiyongList=bannerService.findBannerAll(banner);
		model.addAttribute("msgPage", qiyongList);
		model.addAttribute("bannerType", banner.getBannerType());
		return "banner/qiyong/qiyong";
    }
    @RequestMapping("/acrcoQiyong")
    public String acrcoQiyong(HttpServletRequest request,Model model,Banner banner) {
    	PageFinder <Banner> acrcoList=bannerService.findAcrcoBanner(banner);
		model.addAttribute("msgPage", acrcoList);
    	model.addAttribute("bannerType", banner.getBannerType());
    	return "banner/qiyong/acrcoQiyong";
    }
	/**
	 * 
	 * @author jishangyang 2017年12月1日 下午4:53:27
	 * @Method: jinyongAjax 
	 * @Description: 异步加载禁用
	 * @param request
	 * @param model
	 * @param banner
	 * @return 
	 * @throws
	 */
    @RequestMapping("/jinYong")
    public String jinyongAjax(HttpServletRequest request,Model model,Banner banner) {
		banner.setIsState(0);
		PageFinder <Banner> jinyongList=bannerService.findBannerPage(banner);
		model.addAttribute("msgPage", jinyongList);
		return "banner/jinyong/jinyong";
    }
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午2:28:45
	 * @Method: changeStatu 
	 * @Description:修改禁用 启用状态
	 * @param request
	 * @param model
	 * @param banner
	 * @param id
	 * @param biaoshi
	 * @return 
	 * @throws
	 */
    @RequestMapping("/changeStatu")
    public String changeStatu(HttpServletRequest request,Model model,Banner banner,Integer id,Integer biaoshi) {
    	banner.setId(id);
    	banner.setUpdateTime(new Date());
    	if(biaoshi==1){
			//已启用状态  修改为禁用
			banner.setIsState(0);
			bannerService.update(banner);
			banner.setIsState(1);
			List <Banner> qiyongList=bannerService.findBannerAll(banner);
			model.addAttribute("msgPage", qiyongList);
			return "banner/qiyong/qiyong";
		}else{
			//禁用状态 修改为启用
			banner.setIsState(1);
			bannerService.update(banner);
			banner.setIsState(0);
			PageFinder <Banner> jinyongList=bannerService.findBannerPage(banner);
			model.addAttribute("msgPage", jinyongList);
			return "banner/jinyong/jinyong";
		}
    	
    }
    @ResponseBody
    @RequestMapping(value="/changeStatuAcrco", method = RequestMethod.POST)
    public String changeStatuAcrco(HttpServletRequest request,Model model,Banner banner,Integer id,Integer biaoshi) {
    	banner.setId(id);
    	banner.setUpdateTime(new Date());
    	if(biaoshi==1){
    		//已启用状态  修改为禁用
    		banner.setIsState(0);
    		bannerService.update(banner);
    		/*banner.setIsState(1);
    		List <Banner> qiyongList=bannerService.findBannerAll(banner);
    		model.addAttribute("msgPage", qiyongList);
    		return "banner/qiyong/qiyong";*/
    	}else{
    		//先禁用原来的banner
    		bannerService.forbiddenBanner();
    		//禁用状态 修改为启用
    		banner.setIsState(1);
    		bannerService.update(banner);
    		/*banner.setIsState(0);
    		PageFinder <Banner> jinyongList=bannerService.findBannerPage(banner);
    		model.addAttribute("msgPage", jinyongList);
    		return "banner/jinyong/jinyong";*/
    	}
    	return "banner/qiyong/acrcoQiyong";
    }
    /**
     * 
     * 
     * @author jishangyang 2017年12月2日 下午4:46:20
     * @Method: update 
     * @Description: 修改保存
     * @param request
     * @param model
     * @param banner
     * @return 
     * @throws
     */
    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(HttpServletRequest request,Model model,Banner banner)  {
    	JSONObject json = new JSONObject();
    	
    	try {
    		Resource resource = new ClassPathResource("config.properties");
        	Properties props=PropertiesLoaderUtils.loadProperties(resource);
        	String url=props+banner.getBannerContentUrl();
        	File file = new File(url);
        	if (file.exists()) {
                file.delete();
            }
        	if(banner.getDetailType() == 2){
        		String htmlUrl=writeHtml(banner.getBannerContent());
    			banner.setBannerContentUrl(htmlUrl);
        	}
			
		} catch (Exception e) {
			log.error("修改保存失败", e);
			e.printStackTrace();
		}
    	if(banner.getDetailType() == 0){
    		banner.setSearchClass("");
    		banner.setSearchClassName("");
    		banner.setBannerContentUrl("");
    		banner.setBannerContent("");
    	}
    	if(banner.getDetailType() == 1){
    		banner.setLinkHref("");
    		banner.setBannerContentUrl("");
    		banner.setBannerContent("");
    	}
    	if(banner.getDetailType() == 2){
    		banner.setLinkHref("");
    		banner.setSearchClass("");
    		banner.setSearchClassName("");
    	}
    	banner.setUpdateTime(new Date());
    		bannerService.update(banner);
    	json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
    	return json;
    }
    /**
     * 
     * @author jishangyang 2017年12月2日 上午11:09:46
     * @Method: addBanner 
     * @Description: 跳转添加页面
     * @param model
     * @param banner
     * @return 
     * @throws
     */
	@RequestMapping(value="/addBanner/{id}" , method = RequestMethod.GET)
	public String addBanner(Model model,Banner banner,@PathVariable Integer id) {
		if(null!=banner.getId()){
			Banner bannerVo =bannerService.findBannerById(banner.getId());
			model.addAttribute("msgPage", bannerVo);	
		}
		model.addAttribute("bannerType", id);
		return "banner/bannerAdd";
	}
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午2:28:13
	 * @Method: editBanner 
	 * @Description: 跳转修改页面
	 * @param model
	 * @param banner
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/editBanner/{id}" )
	public String editBanner(Model model,@PathVariable Integer id) {
		Banner bannerVo =bannerService.findBannerById(id);
    	try {
    		String newUrl=bannerVo.getBannerImgUrl();
    		bannerVo.setRealyBannerImgUrl(newUrl);
    		Resource resource = new ClassPathResource("config.properties");
			Properties props=PropertiesLoaderUtils.loadProperties(resource);
			
			newUrl=props.getProperty("imageServicePath")+newUrl;
			bannerVo.setBannerImgUrl(newUrl);
		} catch (IOException e) {
			log.error("跳转修改页面报错", e);
			e.printStackTrace();
		}
		model.addAttribute("msgPage", bannerVo);
		model.addAttribute("bannerType", bannerVo.getBannerType());
		model.addAttribute("detailType", bannerVo.getDetailType());
		return "banner/bannerEdit";
	}
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午8:53:07
	 * @Method: seachDetail 
	 * @Description: 数据库以后的预览
	 * @param model
	 * @param id
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/seachDetail/{id}" )
	public String seachDetail(Model model,@PathVariable Integer id) {
		Banner bannerVo =bannerService.findBannerById(id);
		model.addAttribute("msgPage", bannerVo);	
		return "banner/searchDetail";
	}
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午8:55:13
	 * @Method: yulan 
	 * @Description: 新添加 没有保存预览
	 * @param model
	 * @param bannerContent
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/yulan" )
	public String yulan(Model model, Banner bannerVo) {
		model.addAttribute("msgPage", bannerVo);	
		return "banner/searchDetail";
	}
	
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午8:05:39
	 * @Method: sort 
	 * @Description: 排序
	 * @param request
	 * @param model
	 * @param banner
	 * @param id
	 * @param biaoshi
	 * @return 
	 * @throws
	 */
	@RequestMapping("/sort")
    public String sort(HttpServletRequest request,Model model,Banner banner,Integer id,Integer biaoshi) {
		banner.setId(id);
    	if(biaoshi==1){
			//已启用状态  修改为禁用
			bannerService.sort(banner,1);//上升
		}else{
			bannerService.sort(banner,0);//下降
		}
    	banner.setIsState(1);
    	List <Banner> qiyongList=bannerService.findBannerAll(banner);
    	model.addAttribute("msgPage", qiyongList);
		model.addAttribute("bannerType", banner.getBannerType());
		return "banner/qiyong/qiyong";
    }
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午2:28:30
	 * @Method: addBanner 
	 * @Description: 添加banner
	 * @param request
	 * @param bannerName
	 * @param bannerContent
	 * @param bannerDescribe
	 * @param bannerImgUrl
	 * @return 
	 * @throws
	 */
	@ResponseBody
    @RequestMapping("/addBanner")
    public JSONObject addBanner(HttpServletRequest request, String bannerName,String bannerContent,String bannerDescribe,String bannerImgUrl,Integer bannerType,Integer detailType,String linkHref,String searchClass,String searchClassName) {
        JSONObject json = new JSONObject();
        try {
        	Banner banner =new Banner();
        	if(detailType == 0){
        		banner.setLinkHref(linkHref);
        	}
        	if(detailType == 1){
        		banner.setSearchClass(searchClass);
        		banner.setSearchClassName(searchClassName);
        	}
        	if(detailType == 2){
        		String htmlUrl=writeHtml(bannerContent);
        		banner.setBannerContentUrl(htmlUrl);
        	}
        	banner.setBannerImgUrl(bannerImgUrl);
        	banner.setBannerName(bannerName);
        	banner.setBannerContent(bannerContent);
        	banner.setBannerDescribe(bannerDescribe);
        	banner.setUpdateTime(new Date());
        	banner.setDetailType(detailType);
        	if(bannerType == 2){
        		banner.setIsState(0);
        	}else{
        		banner.setIsState(1);
        	}
        	banner.setBannerType(bannerType);
        	bannerService.addBanner(banner);
        	if(bannerType == 0){
        		json.put(JsonMsg.MSG, "success0");
        	}
        	if(bannerType == 1){
        		json.put(JsonMsg.MSG, "success1");
        	}
        	if(bannerType == 2){
        		json.put(JsonMsg.MSG, "success2");
        	}
            return json;
            
        } catch (Exception e) {
            log.error("qa：保存banner异常:" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }
	
	/**
	  * 
	  * @author jishangyang 2017年12月1日 下午7:51:16
	  * @Method: saveCutPic 
	  * @Description: 
	  * @param request
	  * @param itemOneid
	  * @param path
	  * @param x
	  * @param y
	  * @param w
	  * @param h
	  * @return 
	  * @throws
	  */
	@ResponseBody
	@RequestMapping(value="/saveCutPic")
	public CompanyPicsVo saveCutPic(HttpServletRequest request,Integer itemOneid, String path,double x,double y,double w,double h,String bannerType){
		log.info("初始化截图开始：");
		Resource resource = new ClassPathResource("config.properties");
		Properties props=null;
		try{
			props=PropertiesLoaderUtils.loadProperties(resource);
		}catch(Exception e){
			log.error(e,e);
			e.printStackTrace();
		}
		String fileName=path.substring(path.lastIndexOf("/")+1);
		String tempPath=props.getProperty("server.imageupload.tempPath")+"/source/"+fileName;
		String target=props.getProperty("server.imageupload.tempPath")+"/target/"+fileName;
		
		
        File tempPathFile = new File(props.getProperty("server.imageupload.tempPath") + "/source/");
        if(!tempPathFile.exists()){
            tempPathFile.mkdirs();
        }
        File targetFile = new File(props.getProperty("server.imageupload.tempPath") + "/target/");
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
		
        String header="http://"+props.getProperty("yunduoketang.oss.imagedomain")+"/";
		path=path.replace(header, "");
		System.out.println("oss临时文件路径["+path+"]=====本地磁盘临时文件路径["+tempPath+"]======切图后临时文件路径["+target+"]");
		FileUtil.download("temp", path,tempPath);
		//选中尺寸
		BufferedImage img =null;
		try{
			 img = ImageIO.read(new File(tempPath));
		}catch(Exception e){
			log.error("读取图片失败:"+e,e);
			e.printStackTrace();
		}
		//原图尺寸
		double realW=img.getWidth();
		double realH=img.getHeight();
		//示例图尺寸
		double slW=0;
		double slH=0;
		// 为2 是通栏banner，反之则不是
		if(!StringUtils.isEmpty(bannerType)&&"2".equals(bannerType)){
			if(realW > realH){//横图
				if(realW/realH>516/123.84){
					//过宽
					slH=516 * realH/realW;
					slW=516;
				}else{
					//过高
					slH=123.84;
					slW=123.84 * realW/realH;
				}
			}else{
				if(realW/realH>1175/282){
					//过宽
					slH=1175 * realH/realW;
					slW=1175;
				}else{
					//过高
					slH=282;
					slW=282 * realW/realH;
				}
			}

		}else{
			if(realW > realH){//横图
				if(realW/realH>516/206.4){
					//过宽
					slH=516 * realH/realW;
					slW=516;
				}else{
					//过高
					slH=206.4;
					slW=206.4 * realW/realH;
				}
			}else{
				if(realW/realH>705/282){
					//过宽
					slH=705 * realH/realW;
					slW=705;
				}else{
					//过高
					slH=282;
					slW=282 * realW/realH;
				}
			}
		}

		//原图所选中位置和区域
		
		int xx=(new   Double(x*realW/slW)).intValue();	
		int yy=(new   Double(y*realH/slH)).intValue();
		int ww=(new   Double(w*realW/slW)).intValue();
		int hh=(new   Double(h*realH/slH)).intValue();
		System.out.println("选中区域:["+x+","+y+","+w+","+h+"]----原图选中区域:["+xx+","+yy+","+ww+","+hh+"]");
		//在原图中切图
		String cutImgPath=ImageUtils.cutImage(tempPath,target,xx,yy,ww,hh);
		//切好的图缩放到规定比例
//		ImageUtils.scale2(target,target,241,446,true);
		ImageUtils.resize(target, target, 446);
		log.info("截图完成");
		log.info("上传图片开始：");
		String realPath=null;
		try {
			realPath=FileUtil.upload(cutImgPath,"banner", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("上传文件失败",e);
			e.printStackTrace();
		}
		log.info("上传图片后路径："+realPath);
		FileUtil.deleteFile(target);
		FileUtil.deleteFile(cutImgPath);
		
		String picUrl="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		log.info("图片回显路径："+picUrl);
		CompanyPicsVo pics=new CompanyPicsVo();
		pics.setPicOriginalUrl(picUrl);
		pics.setRealPath(realPath);
		
		return pics;
	}
	
	public String writeHtml(String content) throws Exception{
		String picUrl=propertiesUtil.getProjectImageUrl();
		content="<html>" +
				//"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">"+
				"<style>"+
				"img{ max-width:100% !important;} "+
				".loading{height: 100%;position: relative;}"+
				".loading img{width: 50%;position: fixed;top: 50%;left: 50%;margin-left: -25%;margin-top: -25%;}"+
				"#details{overflow-x: hidden;}"+
				"#details::-webkit-scrollbar {display: none;}"+
			"</style>"+
			"<body>"+
				"<div id=\"details\">"+content+
				"</div>";
		content+="<script src= https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js></script><script>"
				+ "function buttonClick(content){var u = navigator.userAgent;var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;"
				+ "var isiOS = !!u.match(/"+"\\"+"(i[^;]+;( U;)? CPU.+Mac OS X/);var x = content.split(\",\");"
				+ "var josn = '{\"classTypeId\":'+x[0]+',\"commodityName\":\"'+x[1]+'\",\"liveFlag\":'+x[2]+',\"commodityId\":'+x[3]+',\"teacherName\":\"'+x[4]+'\",\"coverUrl\":\"http://"+picUrl+"/'+x[5]+'\"}'"
				+ ";if (isiOS) {window.webkit.messageHandlers.openCourseDetail.postMessage(josn);}else{window.android.openCourseDetail(josn);}}"
				+ "function hrefClassName(content){var u = navigator.userAgent;"
				+ "var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;"
				+ "var isiOS = !!u.match(/"+"\\"+"(i[^;]+;( U;)? CPU.+Mac OS X/);var x = content.split(\",\");"
				+ ";if (isiOS) {window.webkit.messageHandlers.classNameCourse.postMessage(content);}else{window.android.classNameCourse(content);}}</script></body>" +
				"</html>";
		OutputStreamWriter pw = null;
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhss");
    	String date = df.format(new Date());
    	Resource resource = new ClassPathResource("config.properties");
    	Properties props=PropertiesLoaderUtils.loadProperties(resource);
    	String rtUrl="bannerHtml/"+date+".html";
    	String url=props.getProperty("imageServiceRealPath") + "/bannerHtml/"+date+".html";
    	
        File tempPathFile = new File(props.getProperty("imageServiceRealPath") + "/bannerHtml/");
        if(!tempPathFile.exists()){
            tempPathFile.mkdirs();
        }
    	
    	pw = new OutputStreamWriter(new FileOutputStream(url),"GBK");
		pw.write(content);
    	pw.close();//关闭流
	    return rtUrl;
	}
	@ResponseBody
    @RequestMapping("/queryClass")
    public List<ClassType> queryClass(HttpServletRequest request, String className) {
        try { 
        	List<ClassType> listClass = bannerService.findClassByName(className);
            return listClass;
        } catch (Exception e) {
            log.error("qa：查询课程异常:" + e.getMessage(), e);
            e.printStackTrace();
            List<ClassType> listClass = new ArrayList<ClassType>();
            return listClass;
        }
    }
}
