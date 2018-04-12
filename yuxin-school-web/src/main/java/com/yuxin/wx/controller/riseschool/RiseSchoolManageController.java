package com.yuxin.wx.controller.riseschool;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.SearchRiseSchoolVo;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
	private IStudentService studentServiceImpl;
    /**
     * 添加学校信息及用户信息
     * @param request
     * @param riseSchoolManageVo
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRiseSchoolInfo",method = RequestMethod.POST)
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
        users.setStatus(1);
        users.setUserType("RISE_SCHOOL_MANAGER");
        //获取学校编号
        try {
        	String schoolNumber = "";
        	String schoolNo = riseSchoolManageServiceImpl.findSchoolNo();
        	if(schoolNo == null || schoolNo == ""){
        		schoolNumber = "01";
        	}else{
        		Integer num = Integer.valueOf(schoolNo);
        		schoolNumber = String.valueOf(num+1);
        		if(schoolNumber.length() == 1){
        			schoolNumber = "0"+schoolNumber;
        		}
        	}
        	riseSchoolManageVo.setSchoolNo(schoolNumber);
		} catch (Exception e) {
			json.put("flag","0");
            json.put("msg","添加学校信息失败");
            return json;
		}
        map.put("riseSchoolManageVo",riseSchoolManageVo);
        map.put("users",users);
        map.put("curUserId",curUserId);
        try {
            riseSchoolManageServiceImpl.insertRiseSchoolInfoAndUsers(map);
            json.put("flag","1");//成功
            json.put("msg","成功");
        } catch (Exception e) {
            e.printStackTrace();
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
    @RequestMapping(value = "/updateRiseSchoolInfo",method = RequestMethod.POST)
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
     * 更新基本信息
     * @param request
     * @param riseSchoolManageVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRiseSchoolInfoTwo",method = RequestMethod.POST)
    public JSONObject updateRiseSchoolInfoTwo(HttpServletRequest request, RiseSchoolManageVo riseSchoolManageVo){
        JSONObject json = new JSONObject();
        riseSchoolManageVo.setUpdateTime(new Date());
        try {
            riseSchoolManageServiceImpl.updateRiseSchoolInfoTwo(riseSchoolManageVo);
            json.put("flag","1");//成功
            json.put("msg","成功");
        }catch (Exception e){
            json.put("flag","0");
            json.put("msg","添加学校信息失败");
        }
        return json;
    }
    /**
     * 检查学校名称是否重复
     * @param request
     * @param riseSchoolManageVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkSchoolName",method = RequestMethod.POST)
    public JSONObject checkSchoolName(HttpServletRequest request, String schoolName){
    	JSONObject json = new JSONObject();
    	try {
    		Integer count = riseSchoolManageServiceImpl.checkSchoolName(schoolName);
    		if(count > 0){
    			json.put("flag","1");//重复
    			json.put("msg","学校名称重复");
    		}
    	}catch (Exception e){
    		json.put("flag","0");
    		json.put("msg","通过");
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
    	riseSchoolManageVo.setPageSize(10);
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
    @RequestMapping(value = "/queryDimRiseSchoolInfo",method=RequestMethod.POST)
    public String queryDimRiseSchoolInfo(HttpServletRequest request, SearchRiseSchoolVo serchRiseSchoolVo, Model model){
    	serchRiseSchoolVo.setPageSize(10);
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
    /**
     * 返回学年
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(value = "/queryRiseSchoolYear")
    public JSONObject queryRiseSchoolYear(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String step = request.getParameter("step");
        if (StringUtils.isEmpty(step)){
            json.put("flag","0");
            json.put("msg","未获取到学段");
            return json;
        }
        List list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        //小学
        if(step.equals("STEP_01")){
        	list.add(year);
			list.add(year-1);
			list.add(year-2);
			list.add(year-3);
			list.add(year-4);
			list.add(year-5);
        }else if (step.equals("STEP_02")) {//初中
        	list.add(year);
			list.add(year-1);
			list.add(year-2);
		}else{//高中
			list.add(year);
			list.add(year-1);
			list.add(year-2);
		}
        if (list == null){
            json.put("flag","0");
        }else{
            json.put("flag","1");
            json.put("dictList",list);
        }
        return json;
    }
    /**
     * 返回学校
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/querySchoolName")
    public JSONObject querySchoolName(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Map map = new HashMap();
        //区
        String registStatus = request.getParameter("registStatus");
        if (StringUtils.isEmpty(registStatus)){
            json.put("flag","0");
            json.put("msg","未获取区县");
            return json;
        }
        map.put("registStatus",registStatus);
        List<SysDictVo> list = riseSchoolManageServiceImpl.querySchoolName(map);
        if (list == null){
            json.put("flag","0");
        }else{
            json.put("flag","1");
            json.put("dictList",list);
        }
        return json;
    }
    /**
     * 通知返回指定发送的用户
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchUsers",method = RequestMethod.POST)
    public JSONObject searchUsers(HttpServletRequest request){
    	JSONObject json = new JSONObject();
    	Map map = new HashMap();
    	//电话
    	String searchCondition = request.getParameter("searchCondition");
    	if (StringUtils.isEmpty(searchCondition)){
    		json.put("flag","0");
    		json.put("msg","未获到电话");
    		return json;
    	}
    	map.put("mobile",searchCondition);
    	List<UsersFront> list = riseSchoolManageServiceImpl.searchUsers(map);
    	if (list == null){
    		json.put("flag","0");
    	}else{
    		json.put("flag","1");
    		json.put("dictList",list);
    	}
    	return json;
    }
    /**
     * 返回当前学校需要发送多少条短信
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/provinceMsgCount")
    public JSONObject provinceMsgCount(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Map map = new HashMap();
        //省，市，区，学校，学段，年份
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String schoolCode = request.getParameter("schoolCode");
        String step = request.getParameter("step");
        String stepYear = request.getParameter("stepYear");
        map.put("province",province);
        map.put("city",city);
        map.put("district",district);
        map.put("schoolCode",schoolCode);
        map.put("step",step);
        map.put("stepYear",stepYear);
        Integer count = studentServiceImpl.schoolMsgCount(map);
        json.put("flag","1");
        json.put("count",count);
        return json;
    }
    /**
     * 返回当前登录或未登录用户有多少
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginUserCount")
    public JSONObject loginUserCount(HttpServletRequest request){
    	JSONObject json = new JSONObject();
    	Map map = new HashMap();
    	//登录和未登录参数,值为0表示未选中，1表示选中
    	String registeredUser = request.getParameter("registeredUser");
    	String noRegisteredUser = request.getParameter("noRegisteredUser");
    	map.put("registeredUser",registeredUser);
    	map.put("noRegisteredUser",noRegisteredUser);
    	Integer count = studentServiceImpl.loginUserCount(map);
    	json.put("flag","1");
    	json.put("count",count);
    	return json;
    }
}
