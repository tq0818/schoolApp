package com.yuxin.wx.controller.riseschool;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.riseschool.IRiseStudentServiceF;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.riseschool.RiseStudentVo;
import com.yuxin.wx.utils.ExcelUtil;

/**
 * 学员管理
 */
@Controller
@RequestMapping(value = "/riseStudentSchoolTag")
public class RiseStudentSchoolTagController {
	@Autowired
	private IRiseStudentServiceF riseStudentServiceF ;
	/**
     * 查询信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryStudentSchoolTag",method=RequestMethod.POST)
    public String queryRiseSchoolInfo(HttpServletRequest request,Model model,RiseStudentVo riseStudent){
    	riseStudent.setPageSize(10);
    	//查询所有申请的学生
    	List<RiseStudentVo> list = riseStudentServiceF.queryAllStudent(riseStudent);
    	Integer count = riseStudentServiceF.queryAllStudentCount(riseStudent);
    	PageFinder<RiseStudentVo> pageFinder = new PageFinder<RiseStudentVo>(riseStudent.getPage(), riseStudent.getPageSize(), count, list);
    	model.addAttribute( "data", pageFinder);
    	return "/riseschool/studentManagementAjaxList";
    }
    /**
     * 通过
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(value = "/passStudent",method=RequestMethod.POST)
    public String passStudent(HttpServletRequest request,Model model){
    	String id = request.getParameter("id");
    	String studentNo = "";//学生编号11位：年份+学校编号+人数+身份证后两位
    	//年份
    	String format = new SimpleDateFormat("yy",Locale.CHINESE).format(new Date());
    	
    	try {
    		//学校编号
        	String schoolNo = riseStudentServiceF.findSchoolNo(id);
        	//身份证后两位
        	RiseStudentVo riseStudentVo = riseStudentServiceF.findById(id);
        	String idCardNo = riseStudentVo.getIdNo();
        	String idNo = idCardNo.substring(idCardNo.length()-2, idCardNo.length());
        	//人数
        	String count = "";
        	String studentCount = riseStudentServiceF.findStudentCount();
        	if(studentCount == null || studentCount == ""){
        		count = "00001";
        	}else{
        		Integer num = Integer.valueOf(studentCount.substring(4, 9));
        		String numm = String.valueOf(num+1);
        		int length = numm.length();
        		if(length == 4){
        			count = "0"+numm;
        		}else if (length == 3) {
        			count = "00"+numm;
    			}else if (length == 2) {
    				count = "000"+numm;
    			}else if (length == 1){
    				count = "0000"+numm;
    			}else if (length == 5){
    				count = numm;
    			}else{
    				return "false";
    			}
        	}
        	studentNo = format + schoolNo + count +idNo;
        	Map map = new HashMap<>();
        	map.put("id", id);
        	map.put("studentNo", studentNo);
        	//更新学生编号
        	riseStudentServiceF.passStudent(map);
        	//更新通过状态
        	riseStudentServiceF.updateIsCheck(id);
        	return "success";
		} catch (Exception e) {
			return "false";
		}
    	
    }
    /**
     * 学员管理详情
     */
    @RequestMapping(value = "/studentDetails")
    public String studentDetails(HttpServletRequest request,Model model){
        return "/riseschool/studentDetails";
    }
    /**
     * 导出
     * @param request
     * @return
     */
    @RequestMapping(value = "/exportExcle",method=RequestMethod.POST)
    public ModelAndView exportExcle(HttpServletRequest request,Model model,RiseStudentVo riseStudent){
    	riseStudent.setPageSize(30000);
    	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	//查询所有申请的学生
    	List<RiseStudentVo> list = riseStudentServiceF.queryAllStudent(riseStudent);
    	List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
    	for (RiseStudentVo riseStudentVo : list) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("studentName",riseStudentVo.getStudentName());
    		map.put("sex",riseStudentVo.getSex());
    		map.put("schoolTag",riseStudentVo.getSchoolTag());
    		map.put("mobile",riseStudentVo.getMobile());
    		map.put("birthday",riseStudentVo.getBirthday());
    		map.put("censusDetAddress",riseStudentVo.getCensusDetAddress());
    		map.put("putTime",dateFormater.format(riseStudentVo.getPutTime()));
    		if(riseStudentVo.getIsCheck().equals("0")){
    			map.put("isCheck","审核不通过");
    		}else if (riseStudentVo.getIsCheck().equals("1")) {
    			map.put("isCheck","待审核");
			}else{
				map.put("isCheck","审核通过");
			}
    		
    		map.put("studentNo",riseStudentVo.getStudentNo());
    		lists.add(map);
		}
    	 StringBuffer title = new StringBuffer(
                 "姓名:studentName,性别:sex,毕业学校:schoolTag,手机号:mobile,出生日期:birthday,户籍详细地址:censusDetAddress,提交日期:putTime,审核状态:isCheck,学生编号:studentNo");
         ViewFiles excel = new ViewFiles();
         HSSFWorkbook wb = new HSSFWorkbook();
         try {
             wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
         } catch (Exception ex) {

         }
         Map map = new HashMap();
         map.put("workbook", wb);
         map.put("fileName", "学员管理.xls");
         return new ModelAndView(excel, map);
    }
}
