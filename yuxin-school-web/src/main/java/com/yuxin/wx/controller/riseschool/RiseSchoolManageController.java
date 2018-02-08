package com.yuxin.wx.controller.riseschool;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.SearchRiseSchoolVo;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学校管理
 */
@Controller
@RequestMapping(value = "/riseSchoolManage")
public class RiseSchoolManageController {
    @Autowired
    private RiseSchoolManageService riseSchoolManageServiceImpl;
    @Autowired
    private IUsersService usersServiceImpl;
    /**
     * 添加学校信息及用户信息
     * @param request
     * @param riseSchoolManageVo
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRiseSchoolInfo")
    public JSONObject addRiseSchoolInfo(HttpServletRequest request, RiseSchoolManageVo riseSchoolManageVo, Users users){
        Map map = new HashMap<>();
        Integer curUserId = WebUtils.getCurrentUserId(request);
        JSONObject json = new JSONObject();
        riseSchoolManageVo.setIsTop(0);
        riseSchoolManageVo.setIsShalve(0);
        riseSchoolManageVo.setCollectNum(0);
        Date date = new Date();
        riseSchoolManageVo.setCreateTime(date);
        riseSchoolManageVo.setUpdateTime(date);
        users.setPassword(new Md5Hash("111111", ByteSource.Util.bytes(users.getUsername() + "salt")).toHex());
        users.setCompanyId(WebUtils.getCurrentCompanyId());
        map.put("riseSchoolManageVo",riseSchoolManageVo);
        map.put("users",users);
        map.put("curUserId",curUserId);
        try {
            riseSchoolManageServiceImpl.insertRiseSchoolInfoAndUsers(map);
            json.put("flag","1");//成功
            json.put("msg","成功");
        } catch (Exception e) {
            json.put("flag","0");
            json.put("msg","添加学校信息失败");
        }
        return json;
    }

    /**
     * 更新学校信息
     * @param request
     * @param riseSchoolManageVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRiseSchoolInfo")
    public JSONObject updateRiseSchoolInfo(HttpServletRequest request, RiseSchoolManageVo riseSchoolManageVo){
        JSONObject json = new JSONObject();
        riseSchoolManageVo.setUpdateTime(new Date());
        try {
            riseSchoolManageServiceImpl.updateRiseSchoolInfo(riseSchoolManageVo);
            json.put("flag","1");//成功
            json.put("msg","成功");
        }catch (Exception e){
            json.put("flag","0");
            json.put("msg","添加学校信息失败");
        }
        return json;
    }

    /**
     * 查询学校信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryRiseSchoolInfo")
    public String queryRiseSchoolInfo(HttpServletRequest request, RiseSchoolManageVo riseSchoolManageVo, Model model){
        PageFinder<RiseSchoolManageVo> pageFinder = riseSchoolManageServiceImpl.queryRiseSchoolInfo(riseSchoolManageVo);
        model.addAttribute("result",pageFinder.getData());
        model.addAttribute("pageNo",riseSchoolManageVo.getPage());
        model.addAttribute("rowCount",pageFinder.getRowCount());
        return "/riseschool/riseSchoolDetail";
    }

    /**
     * 模糊搜索
     * @param request
     * @param serchRiseSchoolVo
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryDimRiseSchoolInfo")
    public String queryDimRiseSchoolInfo(HttpServletRequest request, SearchRiseSchoolVo serchRiseSchoolVo, Model model){
        PageFinder<RiseSchoolManageVo> pageFinder = riseSchoolManageServiceImpl.queryDimRiseSchoolInfo(serchRiseSchoolVo);
        model.addAttribute("result",pageFinder.getData());
        model.addAttribute("pageNo",serchRiseSchoolVo.getPage());
        model.addAttribute("rowCount",pageFinder.getRowCount());
        model.addAttribute("dimFlag","1");
        return "/riseschool/riseSchoolDetail";
    }

    /**
     * 联动的区域信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryRiseSchoolDict")
    public JSONObject queryRiseSchoolDict(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Map map = new HashMap();
        String itemType = request.getParameter("itemType");
        if (StringUtils.isEmpty(itemType)){
            json.put("flag","0");
            json.put("msg","未获取到字典类型");
            return json;
        }
        map.put("itemType",itemType);
        map.put("parentCode",request.getParameter("itemCode"));
        List<SysDictVo> list = riseSchoolManageServiceImpl.queryRiseSchoolDict(map);
        if (list == null){
            json.put("flag","0");
        }else{
            json.put("flag","1");
            json.put("dictList",list);
        }
        return json;
    }

    /**
     * 更新账户信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUsersInfo")
    public JSONObject updateUsersInfo(HttpServletRequest request,Users users){
        JSONObject json = new JSONObject();
        users.setPassword(new Md5Hash(users.getPassword(), ByteSource.Util.bytes(users.getUsername() + "salt")).toHex());
        usersServiceImpl.update(users);
        //由于这里是公用的方法，所以没有对异常进行抛出,因此这里成功或者失败都只能返回成功
        json.put("flag","1");
        json.put("msg","成功");
        return json;
    }

    /**
     * 判断账号是否已经存在
     * @param request
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/judgeAccountName")
    public JSONObject judgeAccountName(HttpServletRequest request,String username){
        JSONObject json = new JSONObject();
        Integer u = usersServiceImpl.queryByNameCount(username);
        json.put("count",u.intValue());
        return json;
    }


}
