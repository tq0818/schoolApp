package com.yuxin.wx.controller.institution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.riseschool.InstitutionStyleService;
import com.yuxin.wx.common.CCVideoConstant;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.model.course.VideoTag;
import com.yuxin.wx.model.institution.InstitutionStyle;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
/**
 * 机构风采
 * @author hello
 *
 */
@Controller
@RequestMapping("/institutionStyle")
public class InstitutionStyleController {
	@Autowired
	private PropertiesUtil propertiesUtil ;
	@Autowired
	private InstitutionStyleService institutionStyleServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigServiceImpl;
    @Autowired
    private InstitutionInfoService institutionInfoService;
	//查询风采图上半部分
	@RequestMapping(value = "/queryInstitutionStyle")// ,method = RequestMethod.POST
    public String elegantDemeanor(HttpServletRequest request,HttpServletResponse response,Model model,InstitutionStyle institutionStyle){
		//TODO 这里先做一个测试
		Integer page = institutionStyle.getPage();
		institutionStyle.setRelationId(1);
		institutionStyle.setPageSize(9);
		if (page != null && page.intValue() == 0) {
			institutionStyle.setPage(1);
		}
		//查询风采图@
		institutionStyle.setSourceFlag(0);
		institutionStyle.setType(2);
//		List<InstitutionStyle> styleInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
		//查询风采总数
		Integer count = institutionStyleServiceImpl.queryInstitutionStyleCount(institutionStyle);
//		PageFinder<InstitutionStyle> pageFinder = new PageFinder<>(institutionStyle.getPage(), institutionStyle.getPageSize(), count, styleInfoList);
		//查询封面图,取出page和type做出相应的查询
		institutionStyle.setType(0);
		institutionStyle.setPage(0);
		List<InstitutionStyle> coverInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
		if (coverInfoList != null && coverInfoList.size() >0) {
			for (InstitutionStyle institutionStyle2 : coverInfoList) {
				institutionStyle2.setImgUrl("http://"+propertiesUtil.getProjectImageUrl()+"/"+institutionStyle2.getImgUrl());
			}
		}
		//查询视频
		institutionStyle.setType(1);
		List<InstitutionStyle> videoInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
		if (videoInfoList != null && videoInfoList.size() >0) {
			for (InstitutionStyle institutionStyle2 : videoInfoList) {
				institutionStyle2.setImgUrl("http://"+propertiesUtil.getProjectImageUrl()+"/"+institutionStyle2.getImgUrl());
			}
		}


		model.addAttribute("coverInfo",coverInfoList.size() > 0 ?coverInfoList.get(0):new InstitutionStyle());
		model.addAttribute("videoInfo",videoInfoList.size() > 0 ?videoInfoList.get(0):new InstitutionStyle());
//		model.addAttribute("result",styleInfoList);
		model.addAttribute("pageNo",page);
        model.addAttribute("rowCount",count);
        model.addAttribute("institutionId", institutionStyle.getRelationId());
        InstitutionInfoVo institutionInfoVo = institutionInfoService.findInstitutionInfoById(institutionStyle.getRelationId());
        model.addAttribute("ins", institutionInfoVo);
		return "/institution/elegantDemeanor";
    }
	
	//查询风采图下半部分
	@RequestMapping(value = "/queryInsStyle")// ,method = RequestMethod.POST
    public String queryInsStyle(HttpServletRequest request,HttpServletResponse response,Model model,InstitutionStyle institutionStyle){
		//TODO 这里先做一个测试
		Integer page = institutionStyle.getPage();
		institutionStyle.setRelationId(1);
		institutionStyle.setPageSize(9);
		if (page != null && page.intValue() == 0) {
			institutionStyle.setPage(1);
			page = page + 1;
		}
		//查询风采图@
		institutionStyle.setSourceFlag(0);
		institutionStyle.setType(2);
		List<InstitutionStyle> styleInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
		if (styleInfoList == null || styleInfoList.size() ==0) {
			if (page.intValue() - 1 > 0) {
				page = page.intValue() - 1;
				institutionStyle.setPage(page);
				styleInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
			}
		}
		if (styleInfoList != null && styleInfoList.size() > 0) {
			for (InstitutionStyle institutionStyle2 : styleInfoList) {
				institutionStyle2.setImgUrl("http://"+propertiesUtil.getProjectImageUrl()+"/"+institutionStyle2.getImgUrl());
			}
		}
		//查询风采总数
		Integer count = institutionStyleServiceImpl.queryInstitutionStyleCount(institutionStyle);
		model.addAttribute("result",styleInfoList);
		model.addAttribute("pageNo",page);
        model.addAttribute("rowCount",count);
        model.addAttribute("relationId", institutionStyle.getRelationId());
		return "/institution/insStyleInfo";
    }
	
	/**
     * 上传图片 上传临时图片 2为风采  1为视频 0为封面
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upLoadInsStyleImg",method = RequestMethod.POST)
    public JSONObject upLoadInsStyleImg(HttpServletRequest request,MultipartRequest multiPartRquest){
        JSONObject json = new JSONObject();
        MultipartFile multipartFile = multiPartRquest.getFile("imgData");
        String realPath=null;
        try {
            realPath = FileUtil.upload(multipartFile, "temp", WebUtils.getCurrentCompanyId()+"");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("flag","0");
            json.put("msg","上传失败");
            return json;
        }
        json.put("flag","1");
        json.put("msg","上传成功");
        json.put("realPath","http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath);
        return json;
    }

    @ResponseBody
    @RequestMapping(value="/saveCutPic")
    public JSONObject saveCutPic(HttpServletRequest request,InstitutionStyle institutionStyle,Integer updateId, String path, double x, double y, double w, double h){
//        log.info("初始化截图开始：");
        JSONObject jsonObject = new JSONObject();
        Resource resource = new ClassPathResource("config.properties");
        Map<String,Object>params = new HashMap<String,Object>();
        params.put("id",updateId==null?-1:updateId);
        String realPath=null;
        String cssStyle = request.getParameter("cssStyle");
        String windowFlag = request.getParameter("windowFlag");
        String imgType = null;//1 竖图  2横图

//        if(flag){
            Properties props=null;
            try{
                props= PropertiesLoaderUtils.loadProperties(resource);
            }catch(Exception e){
//            log.error(e,e);
                e.printStackTrace();
                jsonObject.put("flag","0");
                jsonObject.put("msg","获取配置文件失败");
                return jsonObject;
            }
            String fileName=path.substring(path.lastIndexOf("/")+1);
            //下载图片到这个文件夹下
            String tempPath=props.getProperty("server.imageupload.tempPath")+"/source/"+fileName;
            //剪切的图片
            String target=props.getProperty("server.imageupload.tempPath")+"/target/"+fileName;
            String header="http://"+props.getProperty("yunduoketang.oss.imagedomain")+"/";
            File tempPathFile = new File(props.getProperty("server.imageupload.tempPath") + "/source/");
            if(!tempPathFile.exists()){
                tempPathFile.mkdirs();
            }
            File targetFile = new File(props.getProperty("server.imageupload.tempPath") + "/target/");
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            path=path.replace(header, "");
            System.out.println("oss临时文件路径["+path+"]=====本地磁盘临时文件路径["+tempPath+"]======切图后临时文件路径["+target+"]");
            FileUtil.download("temp", path,tempPath);
            //选中尺寸
            BufferedImage img =null;
            try{
                img = ImageIO.read(new File(tempPath));
            }catch(Exception e){
//            log.error("读取图片失败:"+e,e);
                e.printStackTrace();
            }
            //原图尺寸
            double realW=img.getWidth();
            double realH=img.getHeight();
            //示例图尺寸
            double slW=0;
            double slH=0;
            double scale=0;//根据不同类型的图片有不同的比例

            if ("1".equals(windowFlag) || "2".equals(windowFlag) ){//1 ,2都是风采图,反之则是封面图
                if ("0".equals(cssStyle)){//0是竖图，反之则是横图
                    if(realW/realH>186.57/300.00){
                        //过宽
                        slH=186.57 * realH/realW;
                        slW=186.57;
                    }else{
                        //过高
                        slH=300;
                        slW=300 * realW/realH;
                    }
                    imgType = "1";
                }else{
                    if(realW/realH>300.00/188){
                        //过宽
                        slH=300 * realH/realW;
                        slW=300.00;
                    }else{
                        //过高
                        slH=188;
                        slW=188 * realW/realH;
                    }
                    imgType="2";
                }
            }else{
                if(realW/realH>300/120){
                    //过宽
                    slH=300 * realH/realW;
                    slW=300;
                }else{
                    //过高
                    slH=120;
                    slW=120 * realW/realH;
                }
                imgType="2";
            }

            //原图所选中位置和区域

            int xx=(new   Double(x*realW/slW)).intValue();
            int yy=(new   Double(y*realH/slH)).intValue();
            int ww=(new   Double(w*realW/slW)).intValue();
            int hh=(new   Double(h*realH/slH)).intValue();
            System.out.println("选中区域:["+x+","+y+","+w+","+h+"]----原图选中区域:["+xx+","+yy+","+ww+","+hh+"]");
            //在原图中切图
            String cutImgPath= ImageUtils.cutImage(tempPath,target,xx,yy,ww,hh);
            //切好的图缩放到规定比例
//            ImageUtils.resize(target, target, 446);
            try {
                realPath=FileUtil.upload(cutImgPath,"institutionStyle", WebUtils.getCurrentCompanyId()+"");
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("flag","0");
                jsonObject.put("msg","裁剪框过大");
                return jsonObject;
            }
            FileUtil.deleteFile(target);
            FileUtil.deleteFile(cutImgPath);
//            CompanyPicsVo pics=new CompanyPicsVo();
//            pics.setRealPath(realPath);
//        }

        //判断是更新还是新增
        Date date = new Date();
        institutionStyle.setImgUrl(realPath);
        institutionStyle.setCreateTime(date);
        institutionStyle.setUpdateTime(date);
        if (updateId != null) {
			institutionStyle.setId(updateId);
			institutionStyleServiceImpl.updateInsStyle(institutionStyle);
		}else{
			institutionStyle.setIsTop(0);
	        institutionStyle.setIsVideo(institutionStyle.getType().intValue() == 1 ?1:0);
	        institutionStyleServiceImpl.insertInsStyle(institutionStyle);
		}
        jsonObject.put("flag","1");
        return jsonObject;
    }
    
    //置顶或取消置顶
    @ResponseBody
    @RequestMapping(value="/updateIsTop")
    public String updateIsTop(HttpServletRequest request,InstitutionStyle institutionStyle){
    	institutionStyle.setUpdateTime(new Date());
    	institutionStyleServiceImpl.updateInsStyle(institutionStyle);
    	return "success";
    }
    
    //删除风采
    @ResponseBody
    @RequestMapping(value="/deleteStyle")
    public String deleteStyle(HttpServletRequest request,Integer primaryId){
    	institutionStyleServiceImpl.deleteInsStyle(primaryId);
    	return "success";
    }
   //判断视频大小 
  //添加视频
  //回写视频中的状态
    /**
     * @Description: 判断当前上传的文件是否大于所剩的空间大小
     * @author wzx
     * @date 2015-6-12 下午4:09:36
     * @version 1.0
     * @param request
     * @param totalSize
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "judgeVideoStorage", method = RequestMethod.POST)
//    public String judgeVideoStorage(HttpServletRequest request, String totalSize) {
//        Users user = WebUtils.getCurrentUser();
//        if (user == null) {
//            return "fail";
//        }
//        Integer companyId = user.getCompanyId();
//        CompanyPayConfig config = this.companyPayConfigService.findByCompanyId(companyId);
//        long time = System.currentTimeMillis();
//        String salt = config.getCcApiKey();
//
//        // 获取当前视频是否满足空间大小条件
//        String videoInfo = this.getCCVideoUserInfo(config, totalSize, companyId, time, salt);
//        if ("over_storage".equals(videoInfo) || "date_over".equals(videoInfo)) {
//            return videoInfo;
//        }
//        return "no_storage";
//    }
//    
    /**
     * @Description: H5异步上传视频
     * @author zx
     * @date 2015-7-22 下午4:35:08
     * @version 1.0
     * @param request
     * @param fileName
     * @param fileSize
     * @param tag
     * @param itemOneId
     * @param itemSecondId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public JSONObject uploadVieo(HttpServletRequest request, String fileName, String fileSize, String tag, Integer itemOneId, Integer itemSecondId) {
        Users user = WebUtils.getCurrentUser();
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
        	jsonObject.put("flag", 0);
        	jsonObject.put("msg", "未获取到用户");
            return jsonObject;
        }

        Integer companyId = user.getCompanyId();

        CompanyPayConfig config = companyPayConfigServiceImpl.findByCompanyId(companyId);

//        this.log.info("CC视频上传，当前公司ID：" + companyId + ", 对应的配置信息为：" + config);

        String description = fileName;
        String notify_url = this.propertiesUtil.getHostUrl() + "/video/ccNotify";
        String format = "json";
        long time = System.currentTimeMillis();
        String salt = config.getCcApiKey();

        // 获取当前视频是否满足空间大小条件
//        String videoInfo = this.getCCVideoUserInfo(config, fileSize, companyId, time, salt);
//        this.log.info("CC视频上传，当前公司ID：" + companyId + ", 当前公司视频的使用情况：" + videoInfo);
//        if ("over_storage".equals(videoInfo) || "date_over".equals(videoInfo)) {
//            return videoInfo;
//        }
        // 创建视频ID接口
        String url = CCVideoConstant.CC_VIDEO_CREATE;

        String title = fileName;
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("userid", config.getCcUserId());
        paramsMap.put("title", title);// new
                                      // String(title.getBytes("ISO-8859-1"),
                                      // "UTF-8"));
        paramsMap.put("tag", tag);// new String(tag.getBytes("ISO-8859-1"),
                                  // "UTF-8"));
        paramsMap.put("description", description);// new
                                                  // String(description.getBytes("ISO-8859-1"),
                                                  // "UTF-8"));
        paramsMap.put("filename", fileName);
        paramsMap.put("filesize", fileSize);
        paramsMap.put("notify_url", notify_url);
        paramsMap.put("format", format);

        String hash = APIServiceFunction.createHashedQueryString(paramsMap, time, salt);

//        this.log.info("CC视频上传，当前公司ID：" + companyId + ", hash为：" + hash);

        url += hash;
        String detail;

        NumberFormat nf = new DecimalFormat("###0.0");

        try {
            detail = HttpPostRequest.get(url);
            Integer index = detail.indexOf("videoid");

//            this.log.info("CC视频上传，当前公司ID：" + companyId + ", detail为：" + detail);
            // 将字符串转换为json对象，获取cc视频的ID，保存到数据库中
            if (index > 0) {
                // 保存到视频表中
                Video video = new Video();

                video.setCompanyId(user.getCompanyId());
                video.setItemOneId(itemOneId);
                video.setItemSecondId(itemSecondId);
                video.setStorageType("VIDEO_STORAGE_TYPE_CC");
                String fileSizeM = nf.format(Double.parseDouble(fileSize) / 1024 / 1024);
                video.setVodeoSize(Double.parseDouble(fileSizeM));
                video.setVideoName(fileName.substring(0, fileName.lastIndexOf(".")));
                video.setVideoTag(tag);
                video.setCreateTime(new Date());
                video.setCreator(user.getId());
                video.setSchoolId(user.getSchoolId());
                video.setVideoCcId(detail.substring(index + 10, index + 42));
                video.setVideoStatus(Constant.VIDEO_PROCESS_UPLOAD);
                video.setOriginType(1);
//                this.videoServiceImpl.insert(video);

                jsonObject.put("flag", "1");
                jsonObject.put("msg", "成功");
//                jsonObject.put("ccvid", value)；
//                jsonObject.put("", value)
                return jsonObject;
            } else {
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return jsonObject;
        }
    }
//    
//    @ResponseBody
//    @RequestMapping(value = "/udpateStatus/{ccvid}", method = RequestMethod.POST)
//    public String udpateStatus(@PathVariable String ccvid) {
//        Video video = new Video();
//        video.setVideoCcId(ccvid);
//        List<Video> videos = this.videoServiceImpl.findVideoByPage(video);
//        if (videos != null && videos.size() > 0) {
//            Video video1 = videos.get(0);
//            video1.setVideoStatus(Constant.VIDEO_PROCESS_INHAND);
//            this.videoServiceImpl.update(videos.get(0));
//        }
//        return JsonMsg.SUCCESS;
//    }
    
    
}

