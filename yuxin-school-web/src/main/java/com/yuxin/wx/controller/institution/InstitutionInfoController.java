package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.*;
import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InsFeaturesVo;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.HttpPostRequest;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/InsInfoBase")
public class InstitutionInfoController {
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private InstitutionInfoService institutionInfoService;
    @Autowired
    private InstitutionLabelService institutionLabelService;
    @Autowired
    private InsFeaturesService insFeaturesService;
    @Autowired
    private InstitutionCategoryManageService institutionCategoryService;
    @Autowired
    private InstitutionCategoryService institutionCategoryService2;
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private MerchantEntryService merchantEntryService;
    @Autowired
    private RiseSchoolManageService riseSchoolManageServiceImpl;
    /**
     * 机构首页
     * @return
     */
    @RequestMapping(value = "/organizationIndex")
    public String organizationIndex(Model model){
        //商家入驻申请
        try{
            Integer count = merchantEntryService.queryCount();
            model.addAttribute("count",count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "institution/organizationIndex";
    }

    /**
     * 机构列表
     * @param model
     * @param insInfoVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insData",method = RequestMethod.POST)
    public PageFinder<InstitutionInfoVo> findIns(Model model, InstitutionInfoVo insInfoVo){
        //insInfoVo.setPageSize(10);
        PageFinder<InstitutionInfoVo> pageFinder = institutionInfoService.findInstitutionInfos(insInfoVo);
        model.addAttribute("insList",pageFinder);
        return pageFinder;
    }

    /**
     * 判断机构名称是否重复
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insCheckName",method = RequestMethod.POST)
    public InstitutionInfoVo insCheckName(String name){

        InstitutionInfoVo insInfoVon = institutionInfoService.insCheckName(name);

        return insInfoVon;
    }

    /**
     * 添加机构
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/addIns",method = RequestMethod.POST)
    public void addIns(InstitutionInfoVo insInfoVon, HttpServletRequest request){
        if(null != insInfoVon.getUserName() && !"".equals(insInfoVon.getUserName())) {
            String md5Pwd = new Md5Hash("111111", ByteSource.Util.bytes(insInfoVon.getUserName()
                    + "salt")).toHex();
            insInfoVon.setPwd(md5Pwd);
        }
        Integer curUserId = WebUtils.getCurrentUserId(request);
        insInfoVon.setCurrtUser(curUserId);
        institutionInfoService.insert(insInfoVon);
    }


    /**
     * 机构基本信息
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/findInsById",method = RequestMethod.GET)
    public String  findInsById(Model model,HttpServletRequest request){
        String id = request.getParameter("id");
        //机构信息
        InstitutionInfoVo ins = institutionInfoService.findInstitutionInfoById(Integer.parseInt(id));
        //电话号码数据处理
        String mobile = ins.getMobile();
        List<String> mobiles = new ArrayList<>();//手机号码容器
        List<String> tells = new ArrayList<>();//座机号码区号容器
        if(null != mobile && !"".equals(mobile)){
            if(!mobile.contains(",")){
                //只有一个电话号码
                if(!mobile.contains("-")){
                    //一个手机号码
                    mobiles.add(mobile);
                    model.addAttribute("mobiles",mobiles);
                }else{
                    //一个电话号码
                    tells.add(mobile);
                    model.addAttribute("tells",tells);
                }
            }else{
                //多个号码
                String [] mobileArr = mobile.split(",");
                for(int i = 0; i<mobileArr.length; i++){
                    if(!mobileArr[i].contains("-")){
                        //当前是手机号码
                        mobiles.add(mobileArr[i]);
                    }else{
                        //当前是座机号码
                        tells.add(mobileArr[i]);
                    }
                }
                model.addAttribute("mobiles",mobiles);
                model.addAttribute("tells",tells);
            }
        }

        //一级分类列表
        List<InstitutionCategoryVo> fistCategorys =  institutionCategoryService2.findFistCategorys();
        //二级分类列表
        List<InstitutionCategoryVo> secondCategorys =  institutionCategoryService2.findSecondCategorys();
        //机构分类
        List<InstitutionCategoryVo> categoryVos =  institutionCategoryService.queryInstitutionCategorysByInsId(Integer.parseInt(id));
        //系统标签
        List<InstitutionLabelVo> sysLabel = institutionLabelService.findSysLabelByInsId(Integer.parseInt(id));
        //自定义标签
        List<InstitutionLabelVo> customLabel = institutionLabelService.findCustomLabelByInsId(Integer.parseInt(id));
        //特殊服务
        List<InstitutionLabelVo> specialSer = institutionLabelService.findSpecialServiceByInsId(Integer.parseInt(id));
        //地址
        List<SysDictVo> listCity = riseSchoolManageServiceImpl.queryCity();
        List<SysDictVo> listDistrict = riseSchoolManageServiceImpl.queryDistrict();

        model.addAttribute("listCity",listCity);
        model.addAttribute("listDistrict",listDistrict);
        String header="http://"+propertiesUtil.getProjectImageUrl()+"/";
        for(int i = 0;i<specialSer.size();i++){
            if(null == specialSer.get(i).getImgUrl() || "".equals(specialSer.get(i).getImgUrl())){
                specialSer.get(i).setImgUrl("");
            }else{
                specialSer.get(i).setImgUrl(header+specialSer.get(i).getImgUrl());
            }
        }
        model.addAttribute("ins",ins);
        model.addAttribute("categoryVos",categoryVos);
        model.addAttribute("fistCategorys",fistCategorys);
        model.addAttribute("secondCategorys",secondCategorys);
        model.addAttribute("sysLabel",sysLabel);
        model.addAttribute("customLabel",customLabel);
        model.addAttribute("specialSer",specialSer);
        model.addAttribute("tellSize",tells.size());
        model.addAttribute("mobileSize",mobiles.size());
        model.addAttribute("catSize",categoryVos.size());
        Users users = WebUtils.getCurrentUser();
        model.addAttribute("userType",users.getUserType());

        return "institution/basicInformation";
    }

    /**
     * 特殊服务图片
     * @param model
     * @param insFeaturesVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findSpecialServiceImg",method = RequestMethod.POST)
    public PageFinder<InsFeaturesVo> findSpecialServiceImg(Model model,InsFeaturesVo insFeaturesVo){
        //特殊服务
        try{
            if(null != insFeaturesVo.getPage() && !"".equals(insFeaturesVo.getPage())){
                if(insFeaturesVo.getPage() == 1){
                    insFeaturesVo.setPage(0);
                }else{
                    insFeaturesVo.setPage((insFeaturesVo.getPage()-1)*55);
                }

            }
            insFeaturesVo.setPageSize(55);
            String header="http://"+propertiesUtil.getProjectImageUrl()+"/";
            List<InsFeaturesVo> specialSer = insFeaturesService.findInsFeaturesVos(insFeaturesVo);
            for(int i=0;i<specialSer.size();i++){
                specialSer.get(i).setImgUrl(header+specialSer.get(i).getImgUrl());
            }
            Integer specialSerCount = insFeaturesService.findInsFeaturesVosCount(insFeaturesVo);
            PageFinder<InsFeaturesVo> specialSerPage = new PageFinder<>(insFeaturesVo.getPage()/55,insFeaturesVo.getPageSize(),specialSerCount,specialSer);
            return specialSerPage;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 上传图片
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upSpecialServiceImg",method = RequestMethod.POST)
    public JSONObject upRiseSchoolStyleImg(HttpServletRequest request, MultipartRequest multiPartRquest){
        JSONObject json = new JSONObject();
        MultipartFile multipartFile = multiPartRquest.getFile("imgData");
        String realPath=null;
        try {
            realPath = FileUtil.upload(multipartFile, "temp", WebUtils.getCurrentCompanyId()+"");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("flag","0");
            json.put("msg","失败");
            return json;
        }
        json.put("flag","1");
        json.put("msg","成功");
        json.put("realPath","http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath);
        return json;
    }


    @ResponseBody
    @RequestMapping("/updateInsById")
    public Integer updateInsById(InstitutionInfoVo insInfoVo){
        institutionInfoService.updateInsById(insInfoVo);
        return 0;
    }


    /**
     * 修改认证和上下架
     * @param id
     * @param num
     * @param flag 上架为0 认证为1
     */
    @ResponseBody
    @RequestMapping(value = "/authFrameLower",method = RequestMethod.POST)
    public Integer authFrameLower(String id,String num,String flag){
        InstitutionInfoVo institutionInfoVo = new InstitutionInfoVo();
        Date date = new Date();
        institutionInfoVo.setId(Integer.parseInt(id));
        if(flag .equals("0")){
            institutionInfoVo.setIsShelves(Integer.parseInt(num));
        }else{
            institutionInfoVo.setIsCertified(Integer.parseInt(num));
        }
        institutionInfoVo.setUpdateTime(date);
        try{
            institutionInfoService.update(institutionInfoVo);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }
    }

    /**
     *判断机构是否有管理员
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkUser",method = RequestMethod.GET)
    public InstitutionInfoVo checkUser(HttpServletRequest request){
        Integer institutionInfoVo = null ;
        try{
            String id = request.getParameter("checkId");
            return institutionInfoService.checkUser(Integer.parseInt(id));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 机构账号管理
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/cureatManageUser",method = RequestMethod.POST)
    public void cureatManageUser(HttpServletRequest request){
        String userName = request.getParameter("manageUser");
        String insId = request.getParameter("countManage");
        Integer curUserId = WebUtils.getCurrentUserId(request);

        String md5Pwd = new Md5Hash("111111", ByteSource.Util.bytes(userName + "salt")).toHex();
        Users users = new Users();
        users.setId(null);
        users.setUsername(userName);
        users.setPassword(md5Pwd);
        users.setUserType("INSTITUTION_MANAGE");
        users.setCurrtUser(curUserId);
        users.setCompanyId(WebUtils.getCurrentCompanyId());
        users.setStatus(1);
        try{
            usersServiceImpl.insertA(users);
            InstitutionInfoVo institutionInfoVo = new InstitutionInfoVo();
            institutionInfoVo.setId(Integer.parseInt(insId));
            institutionInfoVo.setUserId(users.getId());
            institutionInfoService.updateInsManageById(institutionInfoVo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @ResponseBody
    @RequestMapping(value = "/updataUserPwd",method = RequestMethod.POST)
    public void updataUserPwd(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String updataPwd = request.getParameter("updataPwd");
        String userNames = request.getParameter("userName");
        String userName = userNames.substring(userNames.indexOf(":")+1);
        String md5Pwd = new Md5Hash(updataPwd, ByteSource.Util.bytes(userName + "salt")).toHex();
        Users users = new Users();
        users.setId(Integer.parseInt(userId));
        users.setPassword(md5Pwd);
        usersServiceImpl.update(users);
    }


    @ResponseBody
    @RequestMapping(value = "/updateIns" ,method = RequestMethod.POST)
    public Integer updateIns(InstitutionInfoVo insInfoVon){
        try{
            institutionInfoService.updateInsById(insInfoVon);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }
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
        if(realW/realH>150/150){
            //过宽
            slH=150 * realH/realW;
            slW=150;
        }else{
            //过高
            slH=150;
            slW=150 * realW/realH;
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
            realPath=FileUtil.upload(cutImgPath,"institutionSpe", WebUtils.getCurrentCompanyId()+"");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("flag",0);
            return json;
        }
        //将图片放入数据库
        InsFeaturesVo insFeaturesVo = new InsFeaturesVo();
        Date date = new Date();
        insFeaturesVo.setId(null);
        insFeaturesVo.setImgType(0);
        insFeaturesVo.setImgUrl(realPath);
        insFeaturesVo.setCreateTime(date);
        insFeaturesVo.setUpdateTime(date);
        try {
            insFeaturesService.insert(insFeaturesVo);
        }catch (Exception e){
            e.printStackTrace();
        }

        FileUtil.deleteFile(target);
        FileUtil.deleteFile(cutImgPath);
        json.put("realPath",realPath);
        json.put("header",header);
        json.put("flag",1);
        return json;
    }

}
