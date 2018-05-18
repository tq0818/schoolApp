package com.yuxin.wx.controller.institution;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.institution.InstitutionLabelService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.institution.*;
import com.yuxin.wx.utils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.common.PageFinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/institutionClassType")
public class InstitutionClassTypeController {
    private static Log log = LogFactory.getLog("log");

    private final int MAX_RECOMMEND_NUM = 2;

    @Autowired
    private InstitutionLabelService labelService;

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Autowired
    private InstitutionClassTypeService institutionClassTypeService;

    @Autowired
    private InstitutionInfoService institutionInfoService;

    @Autowired
    private IClassTypeService classTypeService;

    /**
     * 进入课程管理首页
     *
     * @param model
     * @param insId
     * @return
     */
    @RequestMapping(value = "/classTypeMain/{insId}")
    public String intoClassTypeMain(Model model, @PathVariable Integer insId) {
        try {
            getInstitution(model, insId);
            return "institution/course";
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }


    }

    /**
     * 获取课程分页列表信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClassTypeList", method = RequestMethod.POST)
    public PageFinder<InstitutionClassTypeVo> getClassTypeList(HttpServletRequest request) {
        try {
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer status = Integer.valueOf(request.getParameter("status"));
            Integer pageStart = Integer.valueOf(request.getParameter("pageStart"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));

            PageFinder<InstitutionClassTypeVo> page = institutionClassTypeService.page(insId, status, pageStart, pageSize);
            List<InstitutionClassTypeVo> list = page.getData();
            if(null != list){
                for(int i=0;i<list.size();i++){
                    //picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
                    list.get(i).setFullCoverUrl("http://"+propertiesUtil.getProjectImageUrl()+list.get(i).getCoverUrl());
                }
            }
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/addClassType/{insId}")
    public String addClassType(Model model, @PathVariable Integer insId) {
        try {
            getInstitution(model, insId);
            return "institution/course";
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }

    }

    /**
     * @param model
     * @param insId
     * @throws Exception
     */
    private void getInstitution(Model model, Integer insId) throws Exception {
        InstitutionInfoVo insEntity = institutionInfoService.findInstitutionInfoById(insId);
        model.addAttribute("insEntity", insEntity);
    }


    /**
     * 课程信息上线或者下线
     *
     * @param request 请求信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upDownClass", method = RequestMethod.POST)
    public String upDownClass(HttpServletRequest request) {
        try {
            Integer cid = Integer.valueOf(request.getParameter("cid"));
            InstitutionClassTypeVo entity = institutionClassTypeService.findById(cid);
            if (null == entity) {
                return JsonMsg.ERROR;
            }
            entity.setIsShelves(entity.getIsShelves() == 1 ? 0 : 1);
            //将下架的课程置为不推荐
            if (entity.getIsShelves() == 0) {
                entity.setIsReser(0);
            }
            entity.setUpdateTime(new java.util.Date());

            //update to database
            institutionClassTypeService.update(entity);

        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 删除某个课程
     *
     * @param request 默认请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delClass", method = RequestMethod.POST)
    public String delClass(HttpServletRequest request) {
        try {
            Integer cid = Integer.valueOf(request.getParameter("cid"));
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            InstitutionClassTypeVo entity = institutionClassTypeService.findById(cid);
            if (null == entity) {
                return JsonMsg.ERROR;
            }

            //del from database
            institutionClassTypeService.deleteById(entity.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("insId", insId);
            map.put("cid", cid);
            institutionClassTypeService.deleteRelation(map);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 推荐课程或者取消推荐课程
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public String recommendClass(HttpServletRequest request) {
        try {
            Integer cid = Integer.valueOf(request.getParameter("cid"));
            Integer insId = Integer.valueOf(request.getParameter("insId"));

            InstitutionClassTypeVo entity = institutionClassTypeService.findById(cid);
            if (null == entity) {
                return JsonMsg.ERROR;
            }

            if (entity.getIsReser() == 0) {
                //当前课程是处于未推荐状态，要推荐该课程，则需要判断库中推荐课程数量是否超过最大推荐量
                int num = institutionClassTypeService.getRecommendCountByClassTypeId(insId);
                if (num >= MAX_RECOMMEND_NUM) {
                    return "该机构推荐课程已经达到最大个数" + MAX_RECOMMEND_NUM;
                }
            }

            entity.setIsReser(entity.getIsReser() == 0 ? 1 : 0);
            entity.setUpdateTime(new java.util.Date());

            //update to database
            institutionClassTypeService.update(entity);

        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
        return JsonMsg.SUCCESS;
    }


    /**
     * 获取指定机构已有推荐课程数量
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recommendCount", method = RequestMethod.POST)
    public JSONObject recommendClassCount(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            int num = institutionClassTypeService.getRecommendCountByClassTypeId(insId);
            json.put("num", num);
            json.put("status", 1);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作异常");
            return json;
        }
        return json;
    }


    /**
     * 获取课程分页列表信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOnlineClassTypeList", method = RequestMethod.POST)
    public PageFinder<ClassTypeOnlineVo> getOnlineClassTypeList(HttpServletRequest request) {
        try {
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer link = Integer.valueOf(request.getParameter("link"));
            Integer pageStart = Integer.valueOf(request.getParameter("pageStart"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));

            Map<String, Object> map = new HashMap<>();
            map.put("insId", insId);
            map.put("firstIndex", pageStart * pageSize);
            map.put("pageSize", pageSize);
            map.put("link", link == 0 ? null : (link == 1 ? 1 : 0));

            int count = institutionClassTypeService.pageOnlineCount(map);
            List<ClassTypeOnlineVo> list = institutionClassTypeService.pageOnline(map);

            return new PageFinder<ClassTypeOnlineVo>(pageStart, pageSize, count, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据课程名获取在线课程
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryClassByName", method = RequestMethod.POST)
    public JSONObject queryClassByName(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String name = request.getParameter("name");
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            if (null == name || "".equals(name.trim())) {
                json.put("status", 0);
                json.put("msg", "清输入课程名");
                return json;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("insId", insId);

            List<ClassTypeOnlineFindVo> list = institutionClassTypeService.findOnlineClassType(map);
            if (null == list) {
                json.put("status", 0);
                json.put("msg", "没有找到相应的课程信息");
                return json;
            }

            JSONArray arr = new JSONArray();
            JSONObject obj = null;
            for (ClassTypeOnlineFindVo vo : list) {
                obj = new JSONObject();
                obj.put("name", vo.getName());
                obj.put("subject", vo.getSubjectName());
                obj.put("school", vo.getCompanyName());
                obj.put("cid", vo.getId());
                obj.put("link", vo.getLink());
                arr.add(obj);
            }

            json.put("list", arr);

            json.put("status", 1);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作异常");
            return json;
        }
        return json;
    }


    /**
     * 删除在线课程
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delOnlineClass", method = RequestMethod.POST)
    public String delOnlineClass(HttpServletRequest request) {
        try {
            Integer rid = Integer.valueOf(request.getParameter("rid"));
            institutionClassTypeService.deleteOnlineRelation(rid);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 添加一个在线课程信息 , 同一个机构不能重复添加同一个课程
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addOnlineClass", method = RequestMethod.POST)
    public String addOnlineClass(HttpServletRequest request) {
        try {
            Integer cid = Integer.valueOf(request.getParameter("cid"));
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Map<String, Object> map = new HashMap<>();
            map.put("insId", insId);
            map.put("cid", cid);

            if (institutionClassTypeService.getCountOfOnlineClassyCidInsId(map) > 0) {
                return "该机构已经添加该课程";
            }


            institutionClassTypeService.addOnlineClass(map);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
        return JsonMsg.SUCCESS;
    }


    /**
     * 关联或者取消关联某个在线课程
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/linkOnlineClass", method = RequestMethod.POST)
    public String linkOnlineClass(HttpServletRequest request) {
        try {

            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer rid = Integer.valueOf(request.getParameter("rid"));

            boolean flag = institutionClassTypeService.linkOpenClass(insId, rid);
            if (!flag) {
                return JsonMsg.ERROR;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 更新在线课程关联排名
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSortOnlineClass", method = RequestMethod.POST)
    public String updateSortOnlineClass(HttpServletRequest request) {
        try {

            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer rid = Integer.valueOf(request.getParameter("rid"));
            String method = request.getParameter("method");
            if (null == method || (!"add".equals(method) && !"sub".equals(method))) {
                return JsonMsg.ERROR;
            }

            boolean flag = institutionClassTypeService.updateSortOpenClass(insId, rid, method);
            if (!flag) {
                return JsonMsg.ERROR;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
        return JsonMsg.SUCCESS;
    }


    /**
     * 上传图片
     *
     * @param multiPartRquest
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadImgs", method = RequestMethod.POST)
    public JSONObject uploadImgs(MultipartRequest multiPartRquest, HttpServletRequest req) {
        JSONObject json = new JSONObject();
        try {
            Subject subject = SecurityUtils.getSubject();
            String realPath = null;
            String picPath = null;
            MultipartFile multipartFile = multiPartRquest.getFile("imgData");
            subject.getSession().setAttribute("imgData", multipartFile);
            String name = multipartFile.getOriginalFilename();
            if (name != null && !"".equals(name)) {
                try {
                    realPath = FileUtil.upload(multipartFile, "coursefile", "");
                } catch (Exception e) {
                    log.error("文件上传失败", e);
                    e.printStackTrace();
                }
            }
            	picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
           // picPath = "http://localhost/" + realPath;

            if (realPath == null) {
                json.put("status", 0);
                json.put("msg", "没有文件");
                return json;
            }

            json.put("status", 1);
            json.put("url", picPath);
            json.put("picPath", realPath);
            return json;
            // return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作失败");
            return json;
        }

    }

    /**
     * 新增、修改一个线下课程
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editUnderLineClass", method = RequestMethod.POST)
    public JSONObject editUnderLineClass(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer id = Integer.valueOf(request.getParameter("id"));
            String face = request.getParameter("face");
            String style = request.getParameter("style");
            String name = request.getParameter("name");
            String summary = request.getParameter("summary");
            String label = request.getParameter("label");
            Float price = Float.valueOf(request.getParameter("price"));
            Integer limit = Integer.valueOf(request.getParameter("limit"));
            String limitNum = request.getParameter("limitNum");
            String detail = request.getParameter("detail");

            //数据验证
            if (StringUtil.existBlank(name, detail)) {
                json.put("status", 0);
                json.put("msg", "请将数据填写完整");
                return WebUtils.getFailedJSON(0, "请将数据填写完整");
            }
            //验证价格
            if (price < 0) {
                return WebUtils.getFailedJSON(0, "价格为大于等于0的数字");
            }
            //获取entity ， 如果是新增课程(id <= 0)则new一个entity ，否则则查询数据库
            InstitutionClassTypeVo entity = (id <= 0 ? new InstitutionClassTypeVo() : institutionClassTypeService.findById(id));
            if (null == entity) {
                return WebUtils.getFailedJSON(0, "参数错误");
            }

            entity.setName(name.trim());
            entity.setCoverUrl(face);
            entity.setPrice(price);
            entity.setIsReser(limit == 1 ? 1 : 0);
            if (limit == 1) {
                try {
                    int num = Integer.valueOf(limitNum);
                    if (num <= 0) {
                        return WebUtils.getFailedJSON(0, "限定人数必须大于0");
                    }

                    entity.setReserCount(num);
                } catch (Exception e) {
                    return WebUtils.getFailedJSON(0, "限定人数必须大于0");
                }
            }

            entity.setSummary(summary);
            entity.setDetaildesc(detail);
            entity.setIsShelves(0);
            entity.setDelFlag(0);

            entity.setUpdateTime(new Date());
            if (id <= 0) {
                //新增线下课程
                entity.setCreateTime(new Date());
                entity.setIsRecommend(0);
                //新增课程不修改数据库，直接将课程中的风采、标签写入数据库
                institutionClassTypeService.insert(entity);
                Integer newId = entity.getId();
                if(null == newId){
                    return WebUtils.getFailedJSON(0,"操作失败");
                }

                //线下课程关联机构
                Map<String,Object> relationMap = new HashMap<>();
                relationMap.put("insId",insId);
                relationMap.put("cid",newId);
                institutionClassTypeService.addUnderlineClass(relationMap);

                System.out.println(style);

                //风采
                JSONArray styleArr = JSONArray.parseArray(style);
                for(int i=0;i<styleArr.size();i++){
                    JSONObject obj = styleArr.getJSONObject(i);
                    //新增style
                    InstitutionStyle styleEntity = new InstitutionStyle();
                    styleEntity.setContent("");
                    styleEntity.setCreateTime(new Date());
                    styleEntity.setImgUrl(obj.getString("path"));
                    styleEntity.setIsVideo(0);
                    styleEntity.setSourceFlag(1);
                    styleEntity.setType(2);
                    styleEntity.setUpdateTime(new Date());
                    styleEntity.setRelationId(newId);
                    institutionClassTypeService.addStyle(styleEntity);
                   // styleEntity.setImgUrl();
                }

                System.out.println(label);
                //标签
                JSONArray labelArr = JSONArray.parseArray(label);
                for(int i=0;i<labelArr.size();i++){
                    JSONObject obj = labelArr.getJSONObject(i);
                    InstitutionLabelVo labelVo = new InstitutionLabelVo();
                    labelVo.setLabelName(obj.getString("name"));
                    labelVo.setRelationId(newId);
                    labelVo.setLabelType("1");
                    labelVo.setSourceFlag(1);
                    labelVo.setCreateTime(new Date());
                    labelVo.setUpdateTime(new Date());

                    labelService.insert(labelVo);

                }


            } else {
                //修改课程

                entity.setUpdateTime(new Date());
                //新增课程不修改数据库，直接将课程中的风采、标签写入数据库
                institutionClassTypeService.update(entity);


                //风采
                JSONArray styleArr = JSONArray.parseArray(style);
                List<InstitutionStyle> styleList = institutionClassTypeService.getStyleByClassId(entity.getId());
                for(int i=0;i<styleArr.size();i++){
                    JSONObject obj = styleArr.getJSONObject(i);
                    if("".equals(obj.getString("id"))){
                        //新增style
                        InstitutionStyle styleEntity = new InstitutionStyle();
                        styleEntity.setContent("");
                        styleEntity.setCreateTime(new Date());
                        styleEntity.setImgUrl(obj.getString("path"));
                        styleEntity.setIsVideo(0);
                        styleEntity.setSourceFlag(1);
                        styleEntity.setType(2);
                        styleEntity.setUpdateTime(new Date());
                        styleEntity.setRelationId(entity.getId());
                        institutionClassTypeService.addStyle(styleEntity);
                    }else{
                        for(InstitutionStyle vo : styleList){
                            if(vo.getId() - Integer.valueOf(obj.getString("id")) == 0){
                                vo.setImgUrl(obj.getString("path"));
                                vo.setUpdateTime(new Date());
                                institutionClassTypeService.updateStyle(vo);
                                continue;
                            }
                        }
                    }


                }

                //标签
                JSONArray labelArr = JSONArray.parseArray(label);
                List<InstitutionLabelVo> labelVoList = labelService.getClassLabels(entity.getId());
                for(int i=0;i<labelArr.size();i++){
                    JSONObject obj = labelArr.getJSONObject(i);
                    if("".equals(obj.getString("id"))){
                        InstitutionLabelVo labelVo = new InstitutionLabelVo();
                        labelVo.setLabelName(obj.getString("name"));
                        labelVo.setRelationId(entity.getId());
                        labelVo.setLabelType("1");
                        labelVo.setSourceFlag(1);
                        labelVo.setCreateTime(new Date());
                        labelVo.setUpdateTime(new Date());

                        labelService.insert(labelVo);
                    }else{
                        for(InstitutionLabelVo vo : labelVoList){
                            if(vo.getId() - Integer.valueOf(obj.getString("id")) == 0){
                                vo.setLabelName(obj.getString("name"));
                                vo.setUpdateTime(new Date());
                                labelService.update(vo);
                            }
                        }
                    }

                }

            }

            json.put("status",1);
            json.put("msg","操作成功");

            return json;
            // return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作失败");
            return json;
        }

    }

    /**
     * 获取一个线下课程的信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/underLineClassInfo", method = RequestMethod.POST)
    public JSONObject underLineClassInfo(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            InstitutionClassTypeVo entity = institutionClassTypeService.findById(id);
            if(null == entity){
                return WebUtils.getFailedJSON(0,"参数错误");
            }

            List<InstitutionLabelVo> labelVoList = labelService.getClassLabels(entity.getId());

            List<InstitutionStyle> styleList = institutionClassTypeService.getStyleByClassId(entity.getId());

            JSONObject data = new JSONObject();
            data.put("id",entity.getId());
            data.put("face",entity.getCoverUrl());
            data.put("fullFace","http://"+propertiesUtil.getProjectImageUrl()+entity.getCoverUrl());
            data.put("name",entity.getName());
            data.put("summary",entity.getSummary());
            data.put("price", FloatFormatUtil.format(entity.getPrice(),"0.00"));
            data.put("limit",entity.getIsReser());
            data.put("limitNum",entity.getReserCount());
            data.put("detail",entity.getDetaildesc());

            JSONArray styleArr = new JSONArray();
            JSONObject obj = null;
            for(InstitutionStyle vo : styleList){
                    obj = new JSONObject();
                    obj.put("id",vo.getId());
                    obj.put("path",vo.getImgUrl());
                    obj.put("url","http://"+propertiesUtil.getProjectImageUrl()+vo.getImgUrl());
                styleArr.add(obj);
            }

            JSONArray labelArr = new JSONArray();
            for(InstitutionLabelVo lvo : labelVoList){
                obj = new JSONObject();
                obj.put("id",lvo.getId());
                obj.put("name",lvo.getLabelName());
                labelArr.add(obj);
            }

            data.put("styles",styleArr);
            data.put("labels",labelArr);

            json.put("status",1);
            json.put("data",data);
            return json;
            // return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作失败");
            return json;
        }

    }





}
