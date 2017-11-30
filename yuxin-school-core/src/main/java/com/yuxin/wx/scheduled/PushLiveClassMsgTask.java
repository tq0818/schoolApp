package com.yuxin.wx.scheduled;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.INoticeTemplateService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.NoticeTemplatVo;
import com.yuxin.wx.model.student.Student;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 推送直播课程通知(提前推送10-12小时段的课程)
 */
@Component
public class PushLiveClassMsgTask {
	private Log log = LogFactory.getLog("log");
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private IStudentService stuServiceImpl;
	@Autowired
	private INoticeTemplateService noticeTemplateService;
	
	/**
	 * 推送直播课程通知
	 */
	public String pushLiveClassMsg() {
		try {
			//查询10-12小时段的要直播的课程
			List<ClassType> liveClassList=classTypeServiceImpl.queryNeedPushLiveClass();
			if(null!=liveClassList&&liveClassList.size()>0){
				for(ClassType liveclass:liveClassList){
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date=sdf.format(liveclass.getCreateTime());
					String className=liveclass.getName();
					//读取模板
//					Map<String, Object> params=new HashMap<String, Object>();
//					params.put("noticeCode", "M001");
//					params.put("pageSize", "1");
//					PageFinder<NoticeTemplatVo> templatePage= noticeTemplateService.queryAllNoticeTemplateByCondition(params);
//					String notice_content=templatePage.getData().get(0).getNoticeContent();
				
					String title="直播课程消息通知";
					String content="您报名的"+className+"课程将于"+date+"开始，请您安排还自己的时间准时参与，非常感谢！";
					//查询需要推送的学员
					List<Student> studentList=stuServiceImpl.queryStudentListByClassTypeId(liveclass.getCommodityId());
					
				}
			}
			
		} catch (Exception e) {
			log.error("推送直播课程通知(提前推送10-12小时段的课程)--推送失败--"+e.getMessage());
		}
		return null;
	}

	
}
