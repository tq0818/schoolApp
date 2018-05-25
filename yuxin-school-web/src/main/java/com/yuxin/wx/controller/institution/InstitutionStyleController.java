package com.yuxin.wx.controller.institution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.company.ICompanyVideoConfigService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.api.riseschool.InstitutionStyleService;
import com.yuxin.wx.common.CCVideoConstant;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.ccVideo.CcNotifyVo;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyVideoConfig;
import com.yuxin.wx.model.institution.InsVideo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
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
	 Log log = LogFactory.getLog("log");
	@Autowired
	private PropertiesUtil propertiesUtil ;
	@Autowired
	private InstitutionStyleService institutionStyleServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigServiceImpl;
    @Autowired
    private ICompanyVideoConfigService companyVideoConfigImpl;
    @Autowired
    private InstitutionInfoService institutionInfoService;
	//查询风采图上半部分
	@RequestMapping(value = "/queryInstitutionStyle")// ,method = RequestMethod.POST
    public String elegantDemeanor(HttpServletRequest request,HttpServletResponse response,Model model,InstitutionStyle institutionStyle){
		//TODO 这里先做一个测试
		Integer page = institutionStyle.getPage();
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
		//查询视频图片信息
		institutionStyle.setType(1);
		Integer videoId = new Integer("0");
		List<InstitutionStyle> videoInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
		if (videoInfoList != null && videoInfoList.size() >0) {
			for (InstitutionStyle institutionStyle2 : videoInfoList) {
				if (StringUtils.isEmpty(institutionStyle2.getImgUrl())) {
					String str = "/manage/images/overview_demo.jpg";
					institutionStyle2.setImgUrl(str);
				}else{
					institutionStyle2.setImgUrl("http://"+propertiesUtil.getProjectImageUrl()+"/"+institutionStyle2.getImgUrl());
				}
			}
			//查询视频id
			videoId = institutionStyleServiceImpl.queryInsVideoIdBySourceId(videoInfoList.get(0).getId());
		}
		model.addAttribute("coverInfo",coverInfoList.size() > 0 ?coverInfoList.get(0):new InstitutionStyle());
		model.addAttribute("videoInfo",videoInfoList.size() > 0 ?videoInfoList.get(0):new InstitutionStyle());
//		model.addAttribute("result",styleInfoList);
		model.addAttribute("pageNo",page);
        model.addAttribute("rowCount",count);
        model.addAttribute("institutionId", institutionStyle.getRelationId());
        model.addAttribute("videoId", videoId);
        InstitutionInfoVo institutionInfoVo = institutionInfoService.findInstitutionInfoById(institutionStyle.getRelationId());
        model.addAttribute("ins", institutionInfoVo);
		return "/institution/elegantDemeanor";
    }
	
	//查询风采图下半部分
	@RequestMapping(value = "/queryInsStyle")// ,method = RequestMethod.POST
    public String queryInsStyle(HttpServletRequest request,HttpServletResponse response,Model model,InstitutionStyle institutionStyle){
		Integer page = institutionStyle.getPage();
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
//        String cssStyle = request.getParameter("cssStyle");
//        String windowFlag = request.getParameter("windowFlag");
        String saveFlag = request.getParameter("saveFlag");
        boolean flag = false;
        if (StringUtils.isNotEmpty(path) && (x != 0 || y != 0 || w != 0|| h !=0)) {
        	Properties props=null;
            try{
                props= PropertiesLoaderUtils.loadProperties(resource);
            }catch(Exception e){
//                log.error(e,e);
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
//                log.error("读取图片失败:"+e,e);
                e.printStackTrace();
            }
            //原图尺寸
            double realW=img.getWidth();
            double realH=img.getHeight();
            //示例图尺寸
            double slW=0;
            double slH=0;
            if(realW > realH){
                    slH=300 * realH/realW;
                    slW=300;
                    //过高
//                    slH=120;
//                    slW=120 * realW/realH;
            }else{
              slH=300;
              slW=300 * realW/realH;
            }
//            if(realW/realH>300/120){
//                //过宽
//                slH=300 * realH/realW;
//                slW=300;
//            }else{
//                //过高
//                slH=120;
//                slW=120 * realW/realH;
//            }
            //原图所选中位置和区域
            int xx=(new   Double(x*realW/slW)).intValue();
            int yy=(new   Double(y*realH/slH)).intValue();
            int ww=(new   Double(w*realW/slW)).intValue();
            int hh=(new   Double(h*realH/slH)).intValue();
            System.out.println("选中区域:["+x+","+y+","+w+","+h+"]----原图选中区域:["+xx+","+yy+","+ww+","+hh+"]");
            //在原图中切图
            String cutImgPath= ImageUtils.cutImage(tempPath,target,xx,yy,ww,hh);
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
            flag = true;
		}
        
        //判断是更新还是新增
        Date date = new Date();
        if (flag) {
        	institutionStyle.setImgUrl(realPath);
		}
        institutionStyle.setCreateTime(date);
        institutionStyle.setContent(request.getParameter("content"));
        if ("1".equals(saveFlag)) {
			institutionStyle.setName(request.getParameter("videoName"));
		}
        if (updateId != null) {
			institutionStyle.setId(updateId);
			institutionStyleServiceImpl.updateInsStyle(institutionStyle);
		}else{
			institutionStyle.setUpdateTime(date);
			institutionStyle.setIsTop(0);
	        institutionStyle.setIsVideo(institutionStyle.getType().intValue() == 1 ?1:0);
	        institutionStyleServiceImpl.insertInsStyle(institutionStyle);
		}
        //视频，则需要将风采id，更新到视频表中,无论视频图片时更新还是新增
		if ("1".equals(saveFlag)) {
			//首先判断该风采下是否存在视频信息,存在则先删除  根据视频风采的id
			InsVideo insVideo = new InsVideo();
			Integer insVideoId = institutionStyleServiceImpl.queryInsVideoIdBySourceId(institutionStyle.getId());
			String oldVideoId = request.getParameter("oldVideoId");
			String videoId = request.getParameter("videoId");
			if (insVideoId != null && StringUtils.isNotEmpty(oldVideoId)&& StringUtils.isNotBlank(videoId)&&!videoId.equals(oldVideoId)) {
				//删除之前的视频信息
				delVideo(insVideoId);
			}
			insVideo.setId(new Integer(videoId));
			//查询视频信息
			InsVideo video = institutionStyleServiceImpl.queryInsVideo(insVideo);
			//存在更新
			if (video != null) {
				video.setSourceId(institutionStyle.getId());
				video.setVideoName(request.getParameter("videoName"));
				video.setUpdateTime(date);
				institutionStyleServiceImpl.updateInsVideo(video);
			}
		}
        jsonObject.put("flag","1");
        return jsonObject;
    }
    
    //置顶或取消置顶
    @ResponseBody
    @RequestMapping(value="/updateIsTop")
    public String updateIsTop(HttpServletRequest request,InstitutionStyle institutionStyle){
    	if (institutionStyle.getIsTop().intValue() == 1) {
    		institutionStyle.setUpdateTime(new Date());
		}
    	institutionStyleServiceImpl.updateInsStyle(institutionStyle);
    	return "success";
    }
    
    //删除风采
    @ResponseBody
    @RequestMapping(value="/deleteStyle")
    public String deleteStyle(HttpServletRequest request,Integer primaryId){
    	institutionStyleServiceImpl.deleteInsStyle(primaryId);
    	if ("1".equals(request.getParameter("deleteFlag"))) {
			String videoId = request.getParameter("videoId");
			if (!StringUtils.isEmpty(videoId)) {
				//删除视频表
				delVideo(Integer.parseInt(videoId));
			}
		}
    	return "success";
    }
    
    /**
     * 视频上传
     * @param request
     * @param fileName
     * @param fileSize
     * @param tag
     * @param itemOneId
     * @param itemSecondId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadVieo(HttpServletRequest request, String fileName, String fileSize, String tag, Integer itemOneId, Integer itemSecondId) {
        Users user = WebUtils.getCurrentUser();
        if (user == null) {
        	return "fail";
        }
        Integer companyId = user.getCompanyId();

        CompanyPayConfig config = companyPayConfigServiceImpl.findByCompanyId(companyId);

//        this.log.info("CC视频上传，当前公司ID：" + companyId + ", 对应的配置信息为：" + config);

        String description = fileName;
        //TODO
        String notify_url = this.propertiesUtil.getHostUrl() + "/video/ccNotify";
        String format = "json";
        long time = System.currentTimeMillis();
        String salt = config.getCcApiKey();

        // 获取当前视频是否满足空间大小条件
//        String videoInfo = this.getCCVideoUserInfo(config, fileSize, companyId, time, salt);
////        this.log.info("CC视频上传，当前公司ID：" + companyId + ", 当前公司视频的使用情况：" + videoInfo);
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
                // 保存到机构视频表中
//                Video video = new Video();
                InsVideo video = new InsVideo();
//                video.setCompanyId(user.getCompanyId());
//                video.setItemOneId(itemOneId);
//                video.setItemSecondId(itemSecondId);
                video.setStorageType("VIDEO_STORAGE_TYPE_CC");
                String fileSizeM = nf.format(Double.parseDouble(fileSize) / 1024 / 1024);
                video.setVideoSize(Double.parseDouble(fileSizeM));
//                video.setVideoName(fileName.substring(0, fileName.lastIndexOf(".")));
//                video.setVideoTag(tag);
                video.setCreateTime(new Date());
                video.setCreator(user.getId());
//                video.setSchoolId(user.getSchoolId());
                video.setVideoCcId(detail.substring(index + 10, index + 42));
                video.setVideoStatus(Constant.VIDEO_PROCESS_UPLOAD);
                video.setType(0);
                video.setWatchCount(0);
//                this.videoServiceImpl.insert(video);
                institutionStyleServiceImpl.insertInsVideo(video);
                //将插入的视频id拼接到detail后面，以便后面更新处理
                String videoId = ",\""+"videoId"+"\":"+video.getId()+"}";
                detail = detail.substring(0, detail.lastIndexOf("}")) + videoId;
//              
                return detail;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    /**
     * 更新视频相关信息
     * @param request
     * @param response
     * @param insVideo
     * @return
     */
    public String updateInsVideo(HttpServletRequest request,HttpServletResponse response,InsVideo insVideo){
    	institutionStyleServiceImpl.updateInsVideo(insVideo);	
    	return "success";
    }
    
    /**
     * 更新视频在状态
     * @param ccvid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/udpateStatus", method = RequestMethod.POST)
    public String udpateStatus(HttpServletRequest request) {
    	String ccvides = request.getParameter("ccvid");
        InsVideo videos = institutionStyleServiceImpl.queryInsVideoByCcId(ccvides);
        if (videos != null) {
        	videos.setVideoStatus(Constant.VIDEO_PROCESS_INHAND);
           institutionStyleServiceImpl.updateInsVideo(videos);
        }
        return JsonMsg.SUCCESS;
    }
    
    /**
     * 第三方回调，处理视频相关信息
     * @param request
     * @param notify
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ccNotify", method = RequestMethod.GET)
    public String ccNotify(HttpServletRequest request, CcNotifyVo notify) {
//        this.log.info("处理完毕回调到回来");
//        this.log.info("CC视频回调函数中 videoid：" + request.getParameter("videoid") + " status为：" + request.getParameter("status") + " duration为："
//                + request.getParameter("duration") + "image为：" + request.getParameter("image"));
        // CcNotify notify = JSONObject.parseObject(CcNotifyVo.class);
        if (notify != null && notify.getVideoid() != null) {
//            this.log.info("视频ID为：" + notify.getVideoid());
            InsVideo videos = institutionStyleServiceImpl.queryInsVideoByCcId(notify.getVideoid());
            if (videos != null) {
                Double videoSize = videos.getVideoSize();
                if (notify.getStatus().equals("OK")) {
                	videos.setVideoStatus(Constant.VIDEO_PROCESS_NOMAL);
                } else {
                	videos.setVideoStatus(Constant.VIDEO_PROCESS_FAIL);
                }
                // 返回的时常以秒为单位，需要将秒转换为 时分秒的形式
                if (notify.getDuration() != null && notify.getDuration().length() > 0) {
                    long durat = Long.parseLong(notify.getDuration());
                    long hour = durat / 3600; // !小时
                    long minute = durat % 3600 / 60; // !分钟
                    long second = durat % 60; // !秒

                    // 格式化视频时间长度
                    String hourStr = "";
                    String minuteStr = "";
                    String secondStr = "";
                    if (String.valueOf(hour).length() == 1) {
                        hourStr += "0" + hour;
                    } else {
                        hourStr = String.valueOf(hour);
                    }
                    if (String.valueOf(minute).length() == 1) {
                        minuteStr += "0" + minute;
                    } else {
                        minuteStr = String.valueOf(minute);
                    }
                    if (String.valueOf(second).length() == 1) {
                        secondStr += "0" + second;
                    } else {
                        secondStr = String.valueOf(second);
                    }

                    videos.setVideoTime(hourStr + ":" + minuteStr + ":" + secondStr);
                }

                // 调用cc视频接口 修改视频文件大小 处理公共账号的公司（公共账号&&收费用户） hanrb
                // 查询用户信息
                // 根据账号请求cc接口获取问价大小
                Map<String, String> queryMap = new HashMap<String, String>();

                CompanyPayConfig config = companyPayConfigServiceImpl.findByCompanyId(18113);
                String userUrl = "http://spark.bokecc.com/api/video/v2?";
                config.getCcApiKey();
                long time = System.currentTimeMillis();
                String salt = config.getCcApiKey();
                queryMap.put("videoid", videos.getVideoCcId());
                queryMap.put("userid", config.getCcUserId());
                queryMap.put("format", "json");
                String hashUser = APIServiceFunction.createHashedQueryString(queryMap, time, salt);
                userUrl += hashUser;
                String userInfo;
                try {
                    userInfo = HttpPostRequest.get(userUrl);
                    JSONObject obj = JSONObject.parseObject(userInfo);
                    JSONObject videoObj = obj.getJSONObject("video");
                    JSONArray arr = videoObj.getJSONArray("definition");
                    long fileSize = 0L;
                    for (int i = 0; i < arr.size(); i++) {
                        JSONObject definition = (JSONObject) arr.get(i);
                        fileSize += definition.getLong("filesize");
                    }
                    Double doubleSize = Double.parseDouble(String.valueOf(fileSize)) / 1024 / 1024;
                    doubleSize = new BigDecimal(doubleSize).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue() + (videoSize == null ? 0.0 : videoSize);
                    // 修改video视频的大小 改为M
                    videos.setVideoSize(doubleSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                institutionStyleServiceImpl.updateInsVideo(videos);
            }
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "\n" + "<video>OK</video>";
    }
    
    /**
     * 视频删除
     * @param request
     * @param model
     * @param relationId  实际是指视频的id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delVideo",method = RequestMethod.POST)
    public String delVideo(Integer relationId) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyPayConfig config = companyPayConfigServiceImpl.findByCompanyId(companyId);
        CompanyVideoConfig cvc = companyVideoConfigImpl.findConfigByCompanyId(companyId);
        String userUrl = CCVideoConstant.CC_DELETE_VIDEO;
        //ccId
//        String videoid = request.getParameter("videoid");
//        System.out.println("videoId:" + videoid);
        InsVideo insVideo = new InsVideo();
		insVideo.setId(relationId);
		InsVideo video = institutionStyleServiceImpl.queryInsVideo(insVideo);
		if(video == null){
			return "success";
		}
        // 增加乐视视频删除 hanrb
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", config.getCcUserId());
        params.put("format", "json");
        params.put("videoid", video.getVideoCcId());
        long time = System.currentTimeMillis();
        String salt = config.getCcApiKey();
        String hashUser = APIServiceFunction.createHashedQueryString(params, time, salt);
        userUrl += hashUser;

        String resultInfo = "";
        try {
            resultInfo = HttpPostRequest.get(userUrl);
            institutionStyleServiceImpl.deleteInsVideo(relationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonMsg.SUCCESS;
    }
    
}

