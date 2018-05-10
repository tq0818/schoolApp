package com.yuxin.wx.controller.institution;

import com.yuxin.wx.api.institution.InsFeaturesService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.api.institution.InstitutionLabelService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InsFeaturesVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import com.yuxin.wx.model.user.Users;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/InsInfoBase")
public class InstitutionInfoController {
    @Autowired
    private InstitutionInfoService institutionInfoService;
    @Autowired
    private InstitutionLabelService institutionLabelService;
    @Autowired
    private InsFeaturesService insFeaturesService;
    @Autowired
    private IUsersService usersServiceImpl;
    /**
     * 机构首页
     * @return
     */
    @RequestMapping(value = "/organizationIndex")
    public String organizationIndex(Model model, InstitutionInfoVo insInfoVo,HttpServletRequest request){
        insInfoVo.setPage(1);
        insInfoVo.setPageSize(10);
        PageFinder<InstitutionInfoVo> pageFinder = institutionInfoService.findInstitutionInfos(insInfoVo);
        model.addAttribute("insList",pageFinder);
        //商家入驻申请
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
        insInfoVo.setPageSize(10);
        PageFinder<InstitutionInfoVo> pageFinder = institutionInfoService.findInstitutionInfos(insInfoVo);
        model.addAttribute("insList",pageFinder);
        return pageFinder;
    }

    /**
     * 添加机构
     * @param insInfoVo
     */
    @ResponseBody
    @RequestMapping(value = "/addIns",method = RequestMethod.POST)
    public void addIns(InstitutionInfoVo insInfoVon){
        if(null != insInfoVon.getUserName() && !"".equals(insInfoVon.getUserName())) {
            String md5Pwd = new Md5Hash("111111", ByteSource.Util.bytes(insInfoVon.getUserName()
                    + "salt")).toHex();
            insInfoVon.setPwd(md5Pwd);
        }
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
        //系统标签
        List<InstitutionLabelVo> sysLabel = institutionLabelService.findSysLabelByInsId(Integer.parseInt(id));
        //自定义标签
        List<InstitutionLabelVo> customLabel = institutionLabelService.findCustomLabelByInsId(Integer.parseInt(id));
        //特殊服务
        List<InstitutionLabelVo> specialSer = institutionLabelService.findSpecialServiceByInsId(Integer.parseInt(id));
        model.addAttribute("ins",ins);
        model.addAttribute("sysLabel",sysLabel);
        model.addAttribute("customLabel",customLabel);
        model.addAttribute("specialSer",specialSer);

        return "institution/basicInformation";
    }

    /**
     * 特殊服务图片
     * @param model
     * @param institutionInfoVo
     * @return
     */
    @RequestMapping(value = "/findSpecialServiceImg",method = RequestMethod.POST)
    public String findSpecialServiceImg(Model model,InstitutionInfoVo institutionInfoVo){
        //特殊服务
        List<InsFeaturesVo> specialSer = insFeaturesService.findInsFeaturesVos();
        Integer specialSerCount = insFeaturesService.findInsFeaturesVosCount();

        PageFinder<InsFeaturesVo> specialSerPage = new PageFinder<>(institutionInfoVo.getPage(),institutionInfoVo.getPageSize(),specialSerCount,specialSer);
        model.addAttribute("specialSerPage",specialSerPage);
        return "";
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
    @RequestMapping(value = "/authFrameLower",method = RequestMethod.POST)
    public void authFrameLower(String id,String num,String flag){
        InstitutionInfoVo institutionInfoVo = new InstitutionInfoVo();
        Date date = new Date();
        institutionInfoVo.setId(Integer.parseInt(id));
        if(flag .equals("0")){
            institutionInfoVo.setIsShelves(Integer.parseInt(num));
        }else{
            institutionInfoVo.setIsCertified(Integer.parseInt(num));
        }

        institutionInfoVo.setUpdateTime(date);

        institutionInfoService.update(institutionInfoVo);
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

        String md5Pwd = new Md5Hash("111111", ByteSource.Util.bytes(userName
                + "salt")).toHex();
        Users users = new Users();
        users.setId(null);
        users.setUsername(userName);
        users.setPassword(md5Pwd);
        users.setUserType("3");
        try{
            usersServiceImpl.insertA(users);
            InstitutionInfoVo institutionInfoVo = new InstitutionInfoVo();
            institutionInfoVo.setId(Integer.parseInt(insId));
            institutionInfoVo.setUserId(users.getId());
            institutionInfoService.updateInsById(institutionInfoVo);
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
        String md5Pwd = new Md5Hash(updataPwd, ByteSource.Util.bytes(userName
                + "salt")).toHex();
        Users users = new Users();
        users.setId(Integer.parseInt(userId));
        users.setPassword(md5Pwd);
        usersServiceImpl.update(users);
    }




}
