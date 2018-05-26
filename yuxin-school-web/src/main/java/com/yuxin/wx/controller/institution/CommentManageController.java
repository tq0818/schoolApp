package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.CommentManageService;
import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.CommentApp;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentManageController {
    @Autowired
    private InstitutionClassTypeService institutionClassTypeService;
    @Autowired
    private CommentManageService commentManageService;
    @Autowired
    private InstitutionInfoService institutionInfoService;

    /**
     * 机构下的课程
     * @return
     */
    @RequestMapping(value = "/insCommentIndex")
    public String insCommenIndex(Model model, HttpServletRequest request,InstitutionClassTypeVo institutionClassTypeVo){
        String id = request.getParameter("id");
        //获取课程名称
        List<InstitutionClassTypeVo> classTypeVos= null;
        try{
            classTypeVos = institutionClassTypeService.queryAllByIns(institutionClassTypeVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        InstitutionInfoVo institutionInfoVo = institutionInfoService.findInstitutionInfoById(Integer.parseInt(id));
        model.addAttribute("classTypeVos",classTypeVos);
        model.addAttribute("insId",id);
        model.addAttribute("ins",institutionInfoVo);
        return "institution/evaluation";
    }

    /**
     * 所有被评论的机构
     * @return
     */
    @RequestMapping(value = "/commentIndex")
    public String commentIndex(Model model, HttpServletRequest request,InstitutionClassTypeVo institutionClassTypeVo){
        //获取机构名称
        List<InstitutionInfoVo> insVos= null;
        try{
            insVos = institutionInfoService.queryInsByComment();
        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("insVos",insVos);
        return "institution/evaluationList";
    }

    /**
     *查询机构评论
     * @param commentApp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findInsComment",method = RequestMethod.POST)
    public JSONObject findInsCommen(CommentApp commentApp,HttpServletRequest request){
        JSONObject json = new JSONObject();
        Users users = WebUtils.getCurrentUser();
        try{
            json.put("usersType",users.getUserType());
            json.put("comment",commentManageService.findInsComment(commentApp));
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *查询机构下评论
     * @param commentApp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findInsClassComment",method = RequestMethod.POST)
    public JSONObject findInsClassComment(CommentApp commentApp){
        JSONObject json = new JSONObject();
        Users users = WebUtils.getCurrentUser();
        try {
            json.put("usersType",users.getUserType());
            json.put("comment",commentManageService.findInsClassComment(commentApp));
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除评论和审核评论
     * @param id
     */
    @ResponseBody
    @RequestMapping(value = "/updateComment",method = RequestMethod.POST)
    public Integer update(CommentApp commentApp){
        try{
            commentManageService.update(commentApp);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }

    }

    /**
     * 获取未审核的评论总条数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commentCuont")
    public Integer commentCuont(){
        Integer comCount = commentManageService.commentCuont();
        return comCount;
    }


    @ResponseBody
    @RequestMapping(value = "/initInsClassList")
    public List<InstitutionInfoVo> initInsClassList(){
        List<InstitutionInfoVo> insVos= null;
        try{
            insVos = institutionInfoService.queryInitInsClassList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  insVos;
    }


    @ResponseBody
    @RequestMapping(value = "/findInsClassByInsId")
    public List<InstitutionClassTypeVo> findInsClassByInsId(Model model, HttpServletRequest request,InstitutionClassTypeVo institutionClassTypeVo){
        String id = request.getParameter("id");
        //获取课程名称
        List<InstitutionClassTypeVo> classTypeVos= null;
        try{
            classTypeVos = institutionClassTypeService.queryAllByIns(institutionClassTypeVo);
            return classTypeVos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
