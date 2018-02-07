package com.yuxin.wx.controller.riseschool;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.user.Users;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 学校管理
 */
@Controller
@RequestMapping(value = "/riseSchoolManage")
public class RiseSchoolManageController {
    @Autowired
    private RiseSchoolManageService riseSchoolInfoServiceImpl;
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
        JSONObject json = new JSONObject();
        try {
            riseSchoolInfoServiceImpl.insertRiseSchoolInfoAndUsers(map);
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
        try {
            riseSchoolInfoServiceImpl.updateRiseSchoolInfo(riseSchoolManageVo);
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
    @ResponseBody
    @RequestMapping(value = "/queryRiseSchoolInfo")
    public JSONObject queryRiseSchoolInfo(HttpServletRequest request,RiseSchoolManageVo riseSchoolManageVo){
        JSONObject json = new JSONObject();
        PageFinder<RiseSchoolManageVo> pageFinder = riseSchoolInfoServiceImpl.queryRiseSchoolInfo(riseSchoolManageVo);
        json.put("result",pageFinder);
        return json;
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
        map.put("itemCode",request.getParameter("itemCode"));
        json.put("flag","1");
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
        usersServiceImpl.update(users);
        //由于这里是公用的方法，所以没有对异常进行抛出,因此这里成功或者失败都只能返回成功
        json.put("flag","1");
        json.put("msg","成功");
        return json;
    }

}
