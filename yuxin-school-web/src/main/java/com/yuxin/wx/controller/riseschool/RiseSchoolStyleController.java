package com.yuxin.wx.controller.riseschool;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.riseschool.RiseSchoolStyleService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolStyleVo;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/riseSchoolStyle")
public class RiseSchoolStyleController {

    @Autowired
    private RiseSchoolStyleService riseSchoolStyleServiceImpl;
    /**
     * 添加学校风采
     * @param request
     * @param riseSchoolStyleVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRiseSchoolStyle")
    public JSONObject addRiseSchoolStyle(HttpServletRequest request, RiseSchoolStyleVo riseSchoolStyleVo){
        JSONObject json = new JSONObject();
        try{
            riseSchoolStyleServiceImpl.insertRiseSchoolStyle(riseSchoolStyleVo);
            json.put("flag","1");
            json.put("msg","添加成功");
        }catch (Exception e){
            json.put("flag","0");
            json.put("msg","添加失败");
        }
        return json;
    }

    /**
     *更新学校风采
     * @param request
     * @param riseSchoolStyleVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRiseSchoolStyle")
    public JSONObject updateRiseSchoolStyle(HttpServletRequest request, RiseSchoolStyleVo riseSchoolStyleVo){
        JSONObject json = new JSONObject();
        try{
            riseSchoolStyleServiceImpl.updateRiseSchoolStyle(riseSchoolStyleVo);
            json.put("flag","1");
            json.put("msg","更新成功");
        }catch (Exception e){
            json.put("flag","0");
            json.put("msg","更新失败");
        }
        return json;
    }

    /**
     * 删除学校风采
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteRiseSchoolStyle")
    public JSONObject deleteRiseSchoolStyle(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtils.isEmpty(id)){
            json.put("flag","0");
            json.put("msg","未获取到风采id");
            return json;
        }
        try{
            riseSchoolStyleServiceImpl.deleteRiseSchoolStyleById(Integer.parseInt(id));
            json.put("flag","1");
            json.put("msg","删除成功");
        }catch (Exception e){
            json.put("flag","0");
            json.put("msg","删除失败");
        }
        return json;
    }

    /**
     * 查询学校风采
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryRiseSchoolStyle")
    public JSONObject queryRiseSchoolStyle(HttpServletRequest request,RiseSchoolStyleVo riseSchoolStyleVo){
        JSONObject json = new JSONObject();
        PageFinder<RiseSchoolStyleVo> pageFinder = riseSchoolStyleServiceImpl.queryRiseSchoolStyle(riseSchoolStyleVo);
        json.put("result",pageFinder);
        return json;
    }

    /**
     * 查询学校风采封面图片
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryRiseSchoolStyle")
    public JSONObject queryRiseSchoolStyle(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String schoolId = request.getParameter("schoolId");
        if(StringUtils.isEmpty(schoolId)){
            json.put("flag","0");
            json.put("msg","未获取到学校id");
            return json;
        }
        Map map = new HashMap();
        map.put("schoolId",schoolId);
        map.put("isCover",1);
        try{
            RiseSchoolStyleVo riseSchoolStyleVo = riseSchoolStyleServiceImpl.queryRiseSchoolStyleById(map);
            json.put("result",riseSchoolStyleVo);
            json.put("flag","1");
            json.put("msg","获取成功");
        }catch (Exception e){
            json.put("flag","0");
            json.put("msg","获取封面信息出错");
        }
        return json;
    }

    /**
     * 上传图片
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upRiseSchoolStyleImg")
    public JSONObject upRiseSchoolStyleImg(HttpServletRequest request,MultipartRequest multiPartRquest){
        JSONObject json = new JSONObject();
        MultipartFile multipartFile = multiPartRquest.getFile("imgData");
        String realPath=null;
        try {
            realPath = FileUtil.upload(multipartFile, "imgStyle", WebUtils.getCurrentCompanyId()+"");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("flag","0");
            json.put("msg","上传失败");
            return json;
        }
        json.put("flag","1");
        json.put("msg","上传成功");
        json.put("realPath",realPath);
        return json;
    }
}
