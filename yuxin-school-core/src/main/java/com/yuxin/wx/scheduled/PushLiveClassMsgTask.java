package com.yuxin.wx.scheduled;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.api.company.INoticeTemplateService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.company.NoticeTemplatVo;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.util.JiGuangPushUtil;
import com.yuxin.wx.vo.classes.ClassTypeVo;

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
	
	@Autowired
	private ICompanyStudentMessageService companyStudentMessageServiceImpl;
	
	/**
	 * 推送直播课程通知
	 */
	public String pushLiveClassMsg() {
		try {
			//查询10-12小时段的要直播的课程
			List<ClassTypeVo> liveClassList=classTypeServiceImpl.queryNeedPushLiveClass();
			if(null!=liveClassList&&liveClassList.size()>0){
				for(ClassTypeVo liveclass:liveClassList){
					//查询需要推送的学员
					List<Student> studentList=stuServiceImpl.queryStudentListByClassTypeId(liveclass.getId());
					if(null==studentList||studentList.size()<1){
						continue;
					}
//					//读取模板
//					Map<String, Object> param=new HashMap<String, Object>();
//					NoticeTemplatVo ntv=new NoticeTemplatVo();
//					ntv.setNoticeCode("M001");
//					ntv.setPageSize(1);
//					ntv.setPage(1);
//					param.put("ntv", ntv);
//					PageFinder<NoticeTemplatVo> templatePage= noticeTemplateService.queryAllNoticeTemplateByCondition(param);
//					if(null!=templatePage&&null!=templatePage.getData()&&templatePage.getData().size()>0){
//						String notice_content=templatePage.getData().get(0).getNoticeContent();
//					}
					
					String className = "《"+liveclass.getName()+"》之《"+liveclass.getLessonName()+"》";
					String title="课程开课通知";
					String content="您报名的"+className+"课程将于"+liveclass.getLessonTimeStart()+"开始，请您安排还自己的时间准时参与，非常感谢！";
					CompanyStudentMessage message=new CompanyStudentMessage();
					message.setTitle(title);
					message.setContentText(content);
					message.setContent("<p>"+content+"</p>");
					message.setMessageType("STUDENT_MESSAGE_CLASSTYPE");
					message.setMessageMethod("STUDENT_MESSAGE_WEB");
					message.setItemOneId(liveclass.getItemOneId());
					message.setItemSecondId(liveclass.getItemSecondId());
					message.setClassTypeId(liveclass.getId());
					message.setSchoolId(681);
					message.setClassTypeName(liveclass.getName());
					message.setModuleNoId(liveclass.getModuleNoId());
					message.setModuleNoName(liveclass.getModuleNoName());
					message.setCreateTime(new Date());
					message.setMessageStatus("STUDENT_MESSAGE_COMMIT");
					message.setItemOneCode(liveclass.getItemOneCode());
					message.setItemSecondCode(liveclass.getItemSecondCode());
					message.setItemThirdCode(liveclass.getItemThirdCode());
					companyStudentMessageServiceImpl.insert(message);
					List<UserMessage> umList=new ArrayList<UserMessage>();
					List<String> userIdList=new ArrayList<String>();
					for (Student s : studentList) {
						UserMessage um = new UserMessage();
						um.setUserId(s.getUserId());
						um.setMessageId(message.getId());
						um.setReadFlag(0);
						umList.add(um);
						userIdList.add(String.valueOf(s.getUserId()));
					}
					companyStudentMessageServiceImpl.batchInsertUserMessage(umList);
					message.setSendNum(studentList.size());
					message.setMessageStatus("STUDENT_MESSAGE_FINISH");
					companyStudentMessageServiceImpl.update(message);
					Map<String, String> params=new HashMap<String, String>();
					params.put("message_method", message.getMessageMethod());
					params.put("message_id", String.valueOf(message.getId()));
					//极光推送
					List<List<String>> sList = new ArrayList<List<String>>();
					List<String> ssList = new ArrayList<String>();
					for(int n = 1 ; n <= userIdList.size() ; n++){
						ssList.add(userIdList.get(n-1));
						if(n==userIdList.size()||n%1000==0){
							sList.add(ssList);
							ssList= new ArrayList<String>();
						}
					}
					for(List<String> userList:sList){
						try {
							JiGuangPushUtil.push(userList , content, title,params);
						} catch (Exception e) {
							log.error("推送直播课程通知推送失败--"+e.getMessage());
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("推送直播课程通知(提前推送10-12小时段的课程)--推送失败--"+e.getMessage());
		}
		return null;
	}

	
}
