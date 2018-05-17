package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.StringUtil;
import com.yuxin.wx.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/5/9.
 */
@Controller
@RequestMapping("/insCateManage")
public class InstitutionCategoryManageController {

    @Autowired
    private InstitutionCategoryManageService institutionCategoryManageServiceIml;

    @Autowired
    private PropertiesUtil propertiesUtil;


    /**
     * 查询所有分类数据
     * @param insCate
     * @return
     */
    @RequestMapping(value = "/queryAllInsCate",method= RequestMethod.POST)
    public String queryAllInsCate(InstitutionCategoryVo insCate,Model model){
        try{
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("search",insCate);
            List<InstitutionCategoryVo> insCates = institutionCategoryManageServiceIml.queryInstitutionCategorys(params);
            int recordCount = institutionCategoryManageServiceIml.queryInstitutionCategorysCount(params);
            //封装分页数据
            PageFinder<InstitutionCategoryVo> pageFinder = new PageFinder<InstitutionCategoryVo>(insCate.getPage(), insCate.getPageSize(), recordCount, insCates);

            model.addAttribute("insManageData",pageFinder);
            model.addAttribute("pageNo",pageFinder.getPageNo());
            model.addAttribute("count",recordCount);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "institution/classificationDetails";
    }


    /**
     * 查询单条分类数据信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/querySingleInsCate/{id}",method=RequestMethod.POST)
    public JSONObject querySingleInsCate(@PathVariable Integer id, HttpServletRequest request){
        JSONObject resultJson = new JSONObject();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        InstitutionCategoryVo insCates = institutionCategoryManageServiceIml.queryInstitutionCategoryByCondition(params);
        if(null!=insCates){
            String header="http://"+propertiesUtil.getProjectImageUrl()+"/";
            if(StringUtil.isNotEmpty(insCates.getImgUrl())){
                insCates.setImgUrl(header+insCates.getImgUrl());
            }
            resultJson.put("result",insCates);

        }else{
            resultJson.put("result","noData");
        }
        return resultJson;
    }

    /**
     * 修改分类数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateInsCate",method=RequestMethod.POST)
    public JSONObject updateInsCate(HttpServletRequest request){
        JSONObject resultJson = new JSONObject();
        try{
            InstitutionCategoryVo insCate = new InstitutionCategoryVo();
            String flag = request.getParameter("flag");
            String ids = request.getParameter("ids");
            //启用禁用切换
            if("1".equals(flag)){
                String isEnable = request.getParameter("enable");
                if("1".equals(isEnable)){
                    insCate.setIsEnable(0);
                }else{
                    insCate.setIsEnable(1);
                }
            }else{
                String codeName = request.getParameter("codeName");
                insCate.setCodeName(codeName);
                String imgUrl = request.getParameter("imgUrl");
                insCate.setImgUrl(imgUrl);
            }
            insCate.setIds(ids);
            institutionCategoryManageServiceIml.updateInstitutionCategoryInfo(insCate);
            //更新数据成功
            resultJson.put("flag","1");
        }catch (Exception e){
            //更新数据失败
            resultJson.put("flag","0");
        }
        return resultJson;
    }

    /**
     * 保存分类数据
     * @param insCate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveInsCate",method=RequestMethod.POST)
    public JSONObject saveInsCate(InstitutionCategoryVo insCate){
        JSONObject resultJson = new JSONObject();
        try{
            //初始化数据为禁用
            insCate.setIsEnable(0);
            //级别
            if(null==insCate.getParentId()){
                insCate.setCodeLevel(1);
                insCate.setIsEnable(0);
            }else{
                insCate.setCodeLevel(2);
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("id",insCate.getParentId());
                InstitutionCategoryVo insCates = institutionCategoryManageServiceIml.queryInstitutionCategoryByCondition(params);
                insCate.setIsEnable(insCates.getIsEnable());
            }
            insCate.setCodeType("0");
            insCate.setSort(999999);
            institutionCategoryManageServiceIml.saveInstitutionCategoryInfo(insCate);
            //保存数据成功
            resultJson.put("flag","1");
        }catch (Exception e){
            //保存数据失败
            resultJson.put("flag","0");
            e.printStackTrace();
        }
        return resultJson;
    }


    @ResponseBody
    @RequestMapping(value="/saveCutPic",method=RequestMethod.POST)
    public JSONObject saveCutPic(HttpServletRequest request, String path, double x, double y, double w, double h){
        JSONObject json = new JSONObject();
        json.put("flag",1);
        String fileName=path.substring(path.lastIndexOf("/")+1);
        String tempPath=propertiesUtil.getTempPath()+"/source/"+fileName;
        String target=propertiesUtil.getTempPath()+"/target/"+fileName;
        String header="http://"+propertiesUtil.getProjectImageUrl()+"/";

        File tempFile = new File(propertiesUtil.getTempPath()+"/source");
        if(!tempFile.exists()){
            tempFile.mkdirs();
        }

        File targetFile = new File(propertiesUtil.getTempPath()+"/target");
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
            e.printStackTrace();
            json.put("flag",0);
            return json;
        }
        //原图尺寸
        double realW=img.getWidth();
        double realH=img.getHeight();
        //示例图尺寸
        double slW=0;
        double slH=0;
        if(realW/realH>186.57/300.00){
            //过宽
            slH=186.57 * realH/realW;
            slW=186.57;
        }else{
            //过高
            slH=300;
            slW=300 * realW/realH;
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
        String realPath=null;
        try {
            realPath=FileUtil.upload(cutImgPath,"institutionCate", WebUtils.getCurrentCompanyId()+"");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("flag",0);
            return json;
        }
        FileUtil.deleteFile(target);
        FileUtil.deleteFile(cutImgPath);
        json.put("realPath",realPath);
        json.put("header",header);
        json.put("flag",1);
        return json;
    }

}
