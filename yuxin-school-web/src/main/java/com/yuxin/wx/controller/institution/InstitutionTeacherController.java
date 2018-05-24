package com.yuxin.wx.controller.institution;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.institution.InstitutionLabelService;
import com.yuxin.wx.api.institution.InstitutionTeacherService;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import com.yuxin.wx.model.institution.InstitutionTeacher;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/institutionTeacher")
public class InstitutionTeacherController {

    private static Log log = LogFactory.getLog("log");

    @Autowired
    private InstitutionTeacherService institutionTeacherService;

    @Autowired
    private InstitutionLabelService labelService;

    @Autowired
    private PropertiesUtil propertiesUtil;

    /**
     * 获取机构中的所有名师列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/teacherList", method = RequestMethod.POST)
    public JSONObject teacherList(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            //获取机构下的所有名师
            List<InstitutionTeacher> list = institutionTeacherService.loadTeacherByInstitutionId(insId);
            //获取机构下所有老师的自定义标签
            List<InstitutionLabelVo> labelVoList = labelService.findTeacherLabelByInsId(insId);

            JSONObject obj = null;
            JSONArray arr = new JSONArray();
            for (InstitutionTeacher teacher : list) {
                obj = new JSONObject();
                obj.put("id", teacher.getId());
                obj.put("name", teacher.getName());
                obj.put("label", getTeacherLabel(labelVoList, teacher));
                obj.put("school", teacher.getGraduateSchool());
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
     * 将名师的标签放置在JSONArray中。
     * 算法设计理由:  每个机构最多8个名师，每个名师最多5个标签，所以最多进行 8*8*5 = 320次循环,并且该应用为后端管理，所以并发量并不高
     *
     * @param labelVoList
     * @param teacher
     * @return
     */
    private JSONArray getTeacherLabel(List<InstitutionLabelVo> labelVoList, InstitutionTeacher teacher) {
        if (null == teacher || null == labelVoList || labelVoList.size() == 0) {
            return new JSONArray();
        }
        JSONArray arr = new JSONArray();
        JSONObject obj = null;
        for (InstitutionLabelVo label : labelVoList) {
            if (label.getRelationId() - teacher.getId() == 0) {
                obj = new JSONObject();
                obj.put("id", label.getId());
                obj.put("name", label.getLabelName());
                arr.add(obj);
            }
        }

        return arr;
    }


    /**
     * 获取机构中的所有名师列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/teacherInfo", method = RequestMethod.POST)
    public JSONObject teacherInfo(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {


            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer teacherId = Integer.valueOf(request.getParameter("tid"));
            Map<String, Object> map = new HashMap<>();
            map.put("tid", teacherId);
            map.put("insId", insId);
            InstitutionTeacher teacher = institutionTeacherService.getTeacherByTidInsId(map);
            if (null == teacher) {
                json.put("status", 0);
                json.put("msg", "参数错误");
                return json;
            }

            //获取机构下所有老师的自定义标签
            List<InstitutionLabelVo> labelVoList = labelService.getTeacherLabelsByTeacherId(teacherId);

            JSONObject obj = null;
            JSONArray arr = new JSONArray();
            for (InstitutionLabelVo labelVo : labelVoList) {
                obj = new JSONObject();
                obj.put("id", labelVo.getId());
                obj.put("name", labelVo.getLabelName());
                arr.add(obj);
            }
            JSONObject data = new JSONObject();
            data.put("id", teacher.getId());
            data.put("headUrl", teacher.getHeadUrl());
            data.put("fullUrl", "http://" + propertiesUtil.getProjectImageUrl() + "/" + teacher.getHeadUrl());
            data.put("name", teacher.getName());
            data.put("school", teacher.getGraduateSchool());
            data.put("labels", arr);
            data.put("desc", teacher.getDetailDesc());

            json.put("data", data);
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
     * 删除一个名师
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delTeacher", method = RequestMethod.POST)
    public JSONObject delTeacher(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer teacherId = Integer.valueOf(request.getParameter("tid"));
            Map<String, Object> map = new HashMap<>();
            map.put("tid", teacherId);
            map.put("insId", insId);
            InstitutionTeacher teacher = institutionTeacherService.getTeacherByTidInsId(map);
            if (null == teacher) {
                json.put("status", 0);
                json.put("msg", "参数错误");
                return json;
            }

            institutionTeacherService.deleteById(teacher.getId());

            json.put("status", 1);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作异常");
            return json;
        }
        return json;
    }


    @ResponseBody
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public JSONObject addTeacher(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String label = request.getParameter("label");
            Integer insId = Integer.valueOf(request.getParameter("insId"));

            InstitutionTeacher teacher = checkTeacherParams(request, true);
            if (null == teacher) {
                json.put("status", 0);
                json.put("msg", "请按要求填写信息");
                return json;
            }

            //新增老师
            institutionTeacherService.addTeacher(teacher, insId);

            JSONArray arr = JSONArray.parseArray(label);

            for (int i = 0; i < arr.size(); i++) {
                String name = arr.getJSONObject(i).getString("name");
                if(null == name || "".equals(name.trim())){
                    //不添加空白标签
                    continue;
                }
                InstitutionLabelVo vo = new InstitutionLabelVo();
                vo.setUpdateTime(new Date());
                vo.setCreateTime(new Date());
                vo.setSourceFlag(2);
                vo.setLabelType("1");
                vo.setRelationId(teacher.getId());
                vo.setLabelName(arr.getJSONObject(i).getString("name"));
                // vo.setImgUrl(headUrl);

                labelService.insert(vo);
            }

            json.put("status", 1);
            json.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作异常");
            return json;
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public JSONObject updateTeacher(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String label = request.getParameter("label");
            Integer insId = Integer.valueOf(request.getParameter("insId"));
            Integer tid = Integer.valueOf(request.getParameter("id"));
            InstitutionTeacher teacher = checkTeacherParams(request, false);
            if (null == teacher) {
                json.put("status", 0);
                json.put("msg", "请按要求填写信息");
                return json;
            }

            //修改老师基本信息
            institutionTeacherService.update(teacher);

            JSONArray arr = JSONArray.parseArray(label);
            //TODO  label信息处理

            List<InstitutionLabelVo> labelVoList = labelService.getTeacherLabelsByTeacherId(tid);

            //检查哪些标签被删除了，并删除
            Map<String, InstitutionLabelVo> map = new HashMap<>();
            for (InstitutionLabelVo vo : labelVoList) {
                map.put("key" + vo.getId(), vo);
            }

            for (int i = 0; i < arr.size(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String name = arr.getJSONObject(i).getString("name");
                if (obj.getString("id") == null) {

                    if(null == name || "".equals(name.trim())){
                        //不添加空白标签
                        continue;
                    }
                    InstitutionLabelVo vo = new InstitutionLabelVo();
                    vo.setUpdateTime(new Date());
                    vo.setCreateTime(new Date());
                    vo.setSourceFlag(2);
                    vo.setLabelType("1");
                    vo.setRelationId(teacher.getId());
                    vo.setLabelName(name.trim());
                    // vo.setImgUrl(headUrl);

                    labelService.insert(vo);
                } else {
                    int id = obj.getIntValue("id");
                    for (InstitutionLabelVo listVo : labelVoList) {
                        if (listVo.getId() - id == 0) {
                            if(null == name || "".equals(name.trim())){
                                //空白标签不入库
                                continue;
                            }
                            listVo.setUpdateTime(new Date());
                            listVo.setLabelName(arr.getJSONObject(i).getString("name"));
                            // vo.setImgUrl(headUrl);
                            labelService.update(listVo);

                            map.remove("key" + id);

                        }
                    }

                }


            }

            //遍历map，将数据删掉
            for (Map.Entry<String, InstitutionLabelVo> entry : map.entrySet()) {
                InstitutionLabelVo delEntity = entry.getValue();
                labelService.deleteInstitutionLabelById(delEntity.getId());
            }


            json.put("status", 1);
            json.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作异常");
            return json;
        }
        return json;
    }


    /**
     * 新增、修改名师信息参数验证
     *
     * @param request
     * @param addFlag true ? add : update
     * @return
     */
    private InstitutionTeacher checkTeacherParams(HttpServletRequest request, boolean addFlag) {
        try {

            Integer insId = Integer.valueOf(request.getParameter("insId"));
            String headUrl = request.getParameter("headUrl");
            String name = request.getParameter("name");
            String school = request.getParameter("school");
            String label = request.getParameter("label");
            String desc = request.getParameter("desc");
            Integer tid = null;
            if (!addFlag) {
                tid = Integer.valueOf(request.getParameter("id"));
            }

            if (existBlank(headUrl, name, school, label, desc)) {
                log.error((addFlag ? " 新增 " : " 修改 ") + "名师信息重要参数存在空 ......");
                return null;
            }

            if (name.length() > 10 || school.length() > 30 || desc.length() > 300) {
                log.error((addFlag ? " 新增 " : " 修改 ") + "名师信息重要限长字段超过长度  ......");
                return null;
            }

            InstitutionTeacher teacher = null;

            if (!addFlag) {
                Map<String, Object> map = new HashMap<>();
                map.put("tid", tid);
                map.put("insId", insId);
                teacher = institutionTeacherService.getTeacherByTidInsId(map);
                if (null == teacher) {
                    log.error(" 修改名师信息,参数错误,获取名师信息失败 , tid = " + tid + " , insId = " + insId);
                    return null;
                }

                // return teacher;
            }

            if (addFlag) {
                teacher = new InstitutionTeacher();
                teacher.setCreateTime(new Date());
                teacher.setDelFlag(0);
            }


            teacher.setDetailDesc(desc);
            teacher.setGraduateSchool(school);
            teacher.setHeadUrl(headUrl);
            teacher.setName(name);
            teacher.setUpdateTime(new Date());
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private boolean existBlank(String... args) {
        for (String str : args) {
            if (null == str || "".equals(str.trim())) {
                return true;
            }
        }
        return false;
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

                    //  String[] arr = realPath.split("/");
                    // realPath = arr[arr.length - 1];

                } catch (Exception e) {
                    log.error("文件上传失败", e);
                    e.printStackTrace();
                }
            }
            picPath = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;
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

        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", 0);
            json.put("msg", "操作失败");
            return json;
        }

    }


}
