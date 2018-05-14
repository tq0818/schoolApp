package com.yuxin.wx.controller.institution;

import com.yuxin.wx.api.institution.CommentManageService;
import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.CommentApp;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;
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

    /**
     * 机构下的课程
     * @return
     */
    @RequestMapping(value = "/insCommentIndex")
    public String insCommenIndex(Model model, HttpServletRequest request){
        String id = request.getParameter("id");
        //获取课程名称
        List<InstitutionClassTypeVo> classTypeVos = institutionClassTypeService.queryAllByIns(Integer.parseInt(id));
        model.addAttribute("classTypeVos",classTypeVos);
        model.addAttribute("insId",id);
        return "institution/evaluation";
    }

    /**
     *查询机构评论
     * @param commentApp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findInsComment",method = RequestMethod.POST)
    public PageFinder<CommentApp> findInsCommen(CommentApp commentApp){

        try{
            return commentManageService.findInsComment(commentApp);
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
    public PageFinder<CommentApp> findInsClassComment(CommentApp commentApp){

        try {
            return commentManageService.findInsClassComment(commentApp);
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

}
