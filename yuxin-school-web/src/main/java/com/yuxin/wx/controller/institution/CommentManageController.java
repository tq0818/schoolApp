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
        List<InstitutionClassTypeVo> classTypeVos = institutionClassTypeService.queryAllByIns(Integer.parseInt("1"));
        model.addAttribute(classTypeVos);
        return "";
    }

    /**
     *查询机构评论
     * @param commentApp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findInsComment",method = RequestMethod.POST)
    public PageFinder<CommentApp> findInsCommen(CommentApp commentApp){

        return commentManageService.findInsComment(commentApp);
    }

    /**
     *查询机构下评论
     * @param commentApp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findInsClassComment",method = RequestMethod.POST)
    public PageFinder<CommentApp> findInsClassComment(CommentApp commentApp){

        return commentManageService.findInsClassComment(commentApp);
    }

    /**
     * 删除评论和审核评论
     * @param id
     */
    @RequestMapping(value = "/updateComment",method = RequestMethod.POST)
    public void update(Integer id){
        commentManageService.update(id);
    }

}
