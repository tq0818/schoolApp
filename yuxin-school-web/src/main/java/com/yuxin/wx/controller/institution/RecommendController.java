package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.api.institution.InstitutionCategoryService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.IndexRecommendVo;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/institutionRecommend")
public class RecommendController {
    private static Log log = LogFactory.getLog("log");



    @Autowired
    private InstitutionCategoryManageService institutionCategoryService;


    /**
     * 获取首页列表推荐信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRecommendList",method = RequestMethod.POST)
    public JSONObject getRecommendList(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{

            String name = request.getParameter("name");
            Integer page = Integer.valueOf(request.getParameter("page"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            Integer typeId = Integer.valueOf(request.getParameter("typeId"));
            Integer level = Integer.valueOf(request.getParameter("level"));
            String status = request.getParameter("status");
            Integer iStatus = null;
            if(null == status ||  "".equals(status)){
                iStatus = null;
            }else{
                iStatus = "1".equals(status) ? 1 : 2;
            }

            int count = institutionCategoryService.getIndexRecommendListCount(typeId,name,iStatus,level);
            List<Map<String,Object>> list = institutionCategoryService.getIndexRecommendList(typeId,name,iStatus,page*pageSize,pageSize,level);

            //int recommendAll = institutionCategoryService.getIndexRecommendYesCount(typeId);
            int recommendAll = institutionCategoryService.getIndexRecommendYesSum(typeId);

            int maxSort = institutionCategoryService.getMaxSortByTypeId(typeId);

            json.put("status",1);
            JSONObject data = new JSONObject();
            data.put("count",count);
            data.put("page",page);
            data.put("pageSize",pageSize);
            data.put("list",list);
            data.put("recommendNum",recommendAll);  //当前分类中状态为推荐的机构个数
            data.put("maxSort",maxSort);    //分类中最大排序值   用于应对在一级分类中排序后查询二级分类时候的显示问题
            json.put("data",data);

            return json;
        }catch (Exception e){
            e.printStackTrace();
            return WebUtils.getFailedJSON(0,"获取列表失败");
        }

    }


    /**
     * 推荐状态调整转换
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateIndexRecommendStatus",method = RequestMethod.POST)
    public String cancelRecommend(HttpServletRequest request) {
        try{

            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer rid = Integer.valueOf(request.getParameter("rid"));
            //获取当前分类编号，用于定制排序方案
            Integer typeId = Integer.valueOf(request.getParameter("typeId"));

            Integer level = Integer.valueOf(request.getParameter("level"));
            Integer flag = Integer.valueOf(request.getParameter("flag"));

            Map<String,Object> map = new HashMap<>();
            map.put("rid",rid);
            map.put("insId",insId);
            map.put("typeId",typeId);
            map.put("level",level);
            map.put("flag",flag);
            int num = institutionCategoryService.alterIndexRecommendStatus(map);
            if(num != 1){
                log.error("====> 更新首页列表推荐失败,num = "+num + "insId = "+insId + "rid = " + rid);
                return "操作失败";
            }

            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "操作失败";
    }



    /**
     * 重新对推荐进行排序
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sort",method = RequestMethod.POST)
    public String sortRecommend(HttpServletRequest request) {
        try{

            Integer typeId = Integer.valueOf(request.getParameter("typeId"));
            Integer rid = Integer.valueOf(request.getParameter("rid"));
            String method = request.getParameter("method");
            // 'add' : 'sub'

            if(!"add".equals(method) && !"sub".equals(method)){
                return "参数错误";
            }

            //updateSort
            boolean result =  institutionCategoryService.updateSort(typeId,rid,"add".equals(method));

            // TODO 取消推荐
            return result ? "success" : "操作失败";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "操作失败";
    }



//========================== 首页分类推荐部分  ==========================================================


    /**
     * 获取分类推荐列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/typeList",method = RequestMethod.POST)
    public JSONObject getTypeList(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            Integer page = Integer.valueOf(request.getParameter("pageStart"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            json.put("data",institutionCategoryService.queryCateList(page,pageSize));
            json.put("num",institutionCategoryService.queryRecommendCount());
            return json;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * change recommend status , if true ? false : true
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
    public JSONObject changeRecommendStatus(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            InstitutionCategoryVo entity = institutionCategoryService.getCateById(id);


            if(null == entity){
                json.put("status",0);
                json.put("msg","参数错误");
                return json;
            }

            int status = entity.getFirstRecommend() == null ? 1 : (entity.getFirstRecommend() == 1 ? 0 : 1) ;

            institutionCategoryService.updateRecommendStatusById(status ,entity.getId(),entity.getSort());

            json.put("status",1);
            json.put("msg","操作成功");
            return json;

        }catch (Exception e){
            e.printStackTrace();
            json.put("status",0);
            json.put("msg","操作异常");
            return json;
        }
    }

    /**
     * 更新某个分类的排序
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeSort",method = RequestMethod.POST)
    public JSONObject changeRecommendSort(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            String method = request.getParameter("method");
            Integer id = Integer.valueOf(request.getParameter("id"));
            //标记字段，用来标记更新哪个字段，isThird == null ? first_recommend : third_recommend
            String isThird = request.getParameter("third");
            if(null == method || ( !"up".equals(method) && !"down".equals(method))){
                json.put("status",0);
                json.put("msg","参数错误");
                return json;
            }
            InstitutionCategoryVo entity = institutionCategoryService.getCateById(id);

            if(null == entity){
                json.put("status",0);
                json.put("msg","参数错误");
                return json;
            }

            boolean flag = (null == isThird ? institutionCategoryService.updateSort(entity,"up".equals(method)) : institutionCategoryService.updateSort3(entity,"up".equals(method)));
            if(flag){
                json.put("status",1);
                json.put("msg","操作成功");
            }else{
                json.put("status",0);
                json.put("msg","操作失败");
            }

            return json;

        }catch (Exception e){
            e.printStackTrace();
            json.put("status",0);
            json.put("msg","操作异常");
            return json;
        }
    }





    /**
     * 获取所有首页分类列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/typeListAll",method = RequestMethod.POST)
    public List<InstitutionCategoryVo> getTypeListAll(HttpServletRequest request) {
        try{

            return   institutionCategoryService.queryInstitutionCategorysEnabled();

           /* String type = request.getParameter("type");
            if("0".equals(type)){

            }else{
                return   institutionCategoryService.queryInstitutionCategorysEnabled1();
            }*/
          //  return   institutionCategoryService.queryInstitutionCategorysEnabled();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/typeIndexListAll",method = RequestMethod.POST)
    public List<InstitutionCategoryVo> getTypeIndexListAll(HttpServletRequest request) {
        try{

            return   institutionCategoryService.queryInstitutionCategorysEnabled1();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/updateTree",method = RequestMethod.POST)
    public JSONObject updateTree(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            String tree = request.getParameter("tree");
            String type = request.getParameter("type");
            if(null == tree){
                json.put("status",0);
                json.put("msg","参数错误");
            }

            JSONArray arr = JSONArray.parseArray(tree);
            List<InstitutionCategoryVo> list = null;
            //首页分类推荐
            if("0".equals(type)){
                list =  institutionCategoryService.queryInstitutionCategorysEnabled();
                //算法说明 因为推荐分类本身数量比较少，而且 list.size() <= arr.length  , 所以算法复杂度为 list.size() * arr.length
                for(int i = 0; i< arr.size();i++){

                    int id = arr.getJSONObject(i).getIntValue("id");
                    int checked = arr.getJSONObject(i).getIntValue("checked");
                    for(InstitutionCategoryVo vo : list){
                        if(vo.getId() - id == 0){
                            if(checked == 1 && (vo.getFirstRecommend() == null || vo.getFirstRecommend() == 0 )){
                                //新增
                                institutionCategoryService.updateRecommendStatusById( 1 ,vo.getId(),null);
                            }else{
                                if((null == vo.getFirstRecommend() || vo.getFirstRecommend() == 1) && checked != 1){
                                    //减少
                                    institutionCategoryService.updateRecommendStatusById( 0 ,vo.getId(),vo.getSort());
                                }
                            }

                        }
                    }
                }


                //更新完推荐状态后，刷新排序
                institutionCategoryService.flushSortAll();



            }else{
                //首页列表推荐
                list = institutionCategoryService.queryInstitutionCategorysEnabled();

                for(int i = 0; i< arr.size();i++){

                    int id = arr.getJSONObject(i).getIntValue("id");
                    int checked = arr.getJSONObject(i).getIntValue("checked");
                    for(InstitutionCategoryVo vo : list){
                        if(vo.getId() - id == 0){

                            if(checked == 1 && (vo.getThirdRecommend() == null || vo.getThirdRecommend() == 0 )){
                                //新增
                                institutionCategoryService.updateRecommendStatusById1( 1 ,vo.getId(),null);
                            }else{
                                if((null == vo.getThirdRecommend() ||  vo.getThirdRecommend() == 1) && checked != 1){
                                    //减少
                                    institutionCategoryService.updateRecommendStatusById1( 0 ,vo.getId(),vo.getSort());
                                }
                            }


                        }
                    }
                }

            }

          //  List<Integer> deleteList = new LinkedList<>();
          //  List<Integer> addList = new LinkedList<>();



            json.put("status",1);
            json.put("msg","操作成功");

            return  json;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//updateTree

}
