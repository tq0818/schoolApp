package com.yuxin.wx.controller.institution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.riseschool.InstitutionStyleService;
import com.yuxin.wx.model.institution.InstitutionStyle;
import com.yuxin.wx.model.riseschool.RiseSchoolStyleVo;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyPicsVo;
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
	//查询风采图上半部分
	@RequestMapping(value = "/queryInstitutionStyle")// ,method = RequestMethod.POST
    public String elegantDemeanor(HttpServletRequest request,HttpServletResponse response,Model model,InstitutionStyle institutionStyle){
		//TODO 这里先做一个测试
		Integer page = institutionStyle.getPage();
		institutionStyle.setRelationId(3);
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
		//查询视频
		institutionStyle.setType(1);
		List<InstitutionStyle> videoInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
		model.addAttribute("coverInfo",coverInfoList.size() > 0 ?coverInfoList.get(0):new InstitutionStyle());
		model.addAttribute("videoInfo",videoInfoList.size() > 0 ?videoInfoList.get(0):new InstitutionStyle());
//		model.addAttribute("result",styleInfoList);
		model.addAttribute("pageNo",page);
        model.addAttribute("rowCount",count);
        model.addAttribute("institutionId", institutionStyle.getId());
		return "/institution/elegantDemeanor";
    }
	
	//查询风采图下半部分
	@RequestMapping(value = "/queryInsStyle")// ,method = RequestMethod.POST
    public String queryInsStyle(HttpServletRequest request,HttpServletResponse response,Model model,InstitutionStyle institutionStyle){
		//TODO 这里先做一个测试
		Integer page = institutionStyle.getPage();
		institutionStyle.setRelationId(3);
		institutionStyle.setPageSize(9);
		if (page != null && page.intValue() == 0) {
			institutionStyle.setPage(1);
			page = page + 1;
		}
		//查询风采图@
		institutionStyle.setSourceFlag(0);
		institutionStyle.setType(2);
		List<InstitutionStyle> styleInfoList = institutionStyleServiceImpl.queryInstitutionStyle(institutionStyle);
		//查询风采总数
		Integer count = institutionStyleServiceImpl.queryInstitutionStyleCount(institutionStyle);
		model.addAttribute("result",styleInfoList);
		model.addAttribute("pageNo",page);
        model.addAttribute("rowCount",count);
        model.addAttribute("institutionId", institutionStyle.getId());
		return "/institution/insStyleInfo";
    }
	//上传图片
	//新增风采图
	//添加视频
	//修改风采图
	//修改视频
	//删除风采图
	//回写视频中的状态
	/**
     * 上传图片
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
    public JSONObject saveCutPic(HttpServletRequest request,InstitutionStyle riseSchoolStyleVo,Integer updateId, String path, double x, double y, double w, double h){
//        log.info("初始化截图开始：");
        JSONObject jsonObject = new JSONObject();
        Resource resource = new ClassPathResource("config.properties");
        Map<String,Object>params = new HashMap<String,Object>();
        params.put("id",updateId==null?-1:updateId);
        RiseSchoolStyleVo rssv = null;
        boolean flag = true;
        if(null!=rssv){
            String oldImgName = rssv.getImgUrl().substring(rssv.getImgUrl().lastIndexOf("/"));
            String newImgName = path.substring(path.lastIndexOf("/"));
            if(oldImgName.equals(newImgName)){
                flag = false;
            }
        }
        String realPath=null;
        String cssStyle = request.getParameter("cssStyle");
        String windowFlag = request.getParameter("windowFlag");
        String imgType = null;//1 竖图  2横图

        if(flag){
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
            CompanyPicsVo pics=new CompanyPicsVo();
            pics.setRealPath(realPath);
        }


        Date date = new Date();
//        riseSchoolStyleVo.setImgType(imgType);
//        if ("1".equals(windowFlag)||"3".equals(windowFlag)){//1新增风采图 3新增封面图
//            if ("1".equals(windowFlag)){
//                riseSchoolStyleVo.setIsCover(0);
//            }else{
//                riseSchoolStyleVo.setIsCover(1);
//            }
//
//            //将剪切的图片地址存放数据库中
//            riseSchoolStyleVo.setImgUrl(realPath);
//            riseSchoolStyleVo.setIsTop(0);
//            riseSchoolStyleVo.setCreateTime(date);
//            riseSchoolStyleVo.setUpdateTime(date);
//            riseSchoolStyleServiceImpl.insertRiseSchoolStyle(riseSchoolStyleVo);
//        }else if ("2".equals(windowFlag)||"4".equals(windowFlag)){//2修改风采图  4//修改封面图
//            riseSchoolStyleVo.setRiseSchoolId(null);
//            riseSchoolStyleVo.setId(updateId);
//            riseSchoolStyleVo.setImgUrl(realPath);
//            riseSchoolStyleVo.setUpdateTime(date);
//            riseSchoolStyleServiceImpl.updateRiseSchoolStyle(riseSchoolStyleVo);
//        }

        jsonObject.put("flag","1");
        return jsonObject;
    }
}
