package com.yuxin.wx.controller.institution;

import com.yuxin.wx.api.institution.InstitutionCategoryService;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import com.yuxin.wx.model.user.UsersFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/institutionCategory")
public class InstitutionCategoryController {

    @Autowired
    private InstitutionCategoryService institutionCategoryService;

    /**
     * 添加分类
     */
    @RequestMapping(value = "/addInstitutionCategory",method = RequestMethod.POST)
    public void addInstitutionCategory(InstitutionCategoryVo institutionCategoryVo){
        institutionCategoryService.insert(institutionCategoryVo);
    }

    /**
     *删除分类
     */
    @RequestMapping(value = "/deleteInstitutionCategoryById",method = RequestMethod.POST)
    public void deleteInstitutionCategoryById(Integer id){
        institutionCategoryService.deleteInstitutionCategoryById(id);
    }

    /**
     *修改分类
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(InstitutionCategoryVo institutionCategoryVo){
        institutionCategoryService.update(institutionCategoryVo);
    }

    /**
     *根据id查询分类
     */
    @RequestMapping(value = "/findInstitutionCategoryById",method = RequestMethod.POST)
    public InstitutionCategoryVo findInstitutionCategoryById(Integer id){
        return institutionCategoryService.findInstitutionCategoryById(id);
    }

    /**
     *根据父级id查询分类
     */
    @RequestMapping(value = "/findInstitutionCategoryByParentId",method = RequestMethod.POST)
    public InstitutionCategoryVo findInstitutionCategoryByParentId(Integer id){
        return institutionCategoryService.findInstitutionCategoryByParentId(id);
    }

    /**
     *查询分类列表
     */
    @RequestMapping(value = "/findInstitutionCategorys",method = RequestMethod.POST)
    public List<InstitutionCategoryVo> findInstitutionCategorys(){
        return institutionCategoryService.findInstitutionCategorys();
    }
}
