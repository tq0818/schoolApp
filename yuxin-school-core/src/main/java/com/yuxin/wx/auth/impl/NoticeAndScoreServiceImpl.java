package com.yuxin.wx.auth.impl;

import com.yuxin.wx.api.app.INoticeAndScoreService;
import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.auth.mapper.app.SysNoticeMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.company.NoticeTemplatVo;
import com.yuxin.wx.model.integral.ScoreDetailsAppVo;
import com.yuxin.wx.model.integral.ScoreRulsAppVo;
import com.yuxin.wx.model.integral.TotalScoreVo;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.util.JiGuangPushUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by lym_gxm on 17/12/2.
 * 消息及积分通知监控
 */
@Service
@Transactional
public class NoticeAndScoreServiceImpl extends BaseServiceImpl implements INoticeAndScoreService{

    protected static final Logger LOG = LoggerFactory.getLogger(INoticeAndScoreService.class);

    @Autowired
    private SysNoticeMapper sysNoticeMapper;
    @Autowired
    private ICompanyStudentMessageService companyStudentMessageServiceImpl;
    @Override
    public String sendMsg(String url,String userId, Map<String, String> cotentData) {
        //TODO  根据特殊业务需要进行一定的处理
        Map<String,Object>paramsMap = new HashMap<String,Object>();
        paramsMap.put("noticeUrl",url.replace(" ",""));
        NoticeTemplatVo ntv = sysNoticeMapper.queryNoticeTemplateByUrl(paramsMap);
        if(null!=ntv){
            Map<String,String>tuisong = new HashMap<String,String>();
           this.dealWithNoticeTemplateParams(ntv,cotentData);
            //调用极光接口发送消息
            if(userId != null && userId != ""){
                List<String> userList = new ArrayList<String>();
                userList.add(userId);
                String result = JiGuangPushUtil.push(userList,ntv.getNoticeContent(),null,tuisong);

                LOG.info("userId:"+userId+"url:"+url+"result:"+result);

                //记录消息
                CompanyStudentMessage companyStudentMessage = new CompanyStudentMessage();
                companyStudentMessage.setContent(ntv.getNoticeContent());
                companyStudentMessage.setContentText(ntv.getNoticeContent());
                Integer schoolId = 681;
                companyStudentMessage.setSchoolId(schoolId);
                companyStudentMessage.setMessageType("STUDENT_MESSAGE_SYSTEM");
                companyStudentMessage.setMessageMethod("STUDENT_MESSAGE_SYSTEM");
                companyStudentMessage.setCreator(0);
                companyStudentMessage.setCreateTime(new Date());
                companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_COMMIT");
                companyStudentMessage.setTitle("系统消息");
                companyStudentMessage.setSendNum(1);
                companyStudentMessage.setFailNum(0);
                companyStudentMessage.setContentText(ntv.getNoticeContent());
                companyStudentMessageServiceImpl.insert(companyStudentMessage);
                //记录发送对象
                List<UserMessage> umList=new ArrayList<UserMessage>();
                UserMessage um = new UserMessage();
                um.setUserId(Integer.parseInt(userId));
                um.setMessageId(companyStudentMessage.getId());
                um.setReadFlag(0);
                umList.add(um);
                companyStudentMessageServiceImpl.batchInsertUserMessage(umList);
                companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_FINISH");
                companyStudentMessageServiceImpl.update(companyStudentMessage);
            }
        }
        return "OK";
    }


    /**
     * 处理通知发送内容
     * @param ntv
     * @param cotentData
     */
    public void dealWithNoticeTemplateParams(NoticeTemplatVo ntv,Map<String, String> cotentData){
        //如果模版存在处理相应参数

        if(null!=cotentData){
            String cotent = ntv.getNoticeContent();
            String userName = cotentData.get("userName");
            //用户名
            if(userName != null && userName != ""){
                cotent = cotent.replace("(aa)",userName);
            }
            //课程名
            String courseName = cotentData.get("courseName");
            if(courseName != null && courseName != ""){
                cotent = cotent.replace("(bb)",courseName);
            }
            //上课时间
            String classTime = cotentData.get("classTime");
            if(classTime != null && classTime != ""){
                cotent=cotent.replace("(cc)",classTime);
            }
            //积分变更名目
            String scoreUpdateTopic = cotentData.get("scoreUpdateTopic");
            if(scoreUpdateTopic != null && scoreUpdateTopic != ""){
                cotent = cotent.replace("(dd)",scoreUpdateTopic);
            }
            //变化积分值
            String scoreUpdateValue = cotentData.get("scoreUpdateValue");
            if(scoreUpdateValue != null && scoreUpdateValue != ""){
                cotent = cotent.replace("(ee)",scoreUpdateValue);
            }
            //现有积分值
            String scoreTotalValue = cotentData.get("scoreTotalValue");
            if(scoreTotalValue != null && scoreTotalValue != ""){
                cotent = cotent.replace("(ff)",scoreTotalValue);
            }
            //课次名称
            String scoreLessonMode = cotentData.get("scoreLessonMode");
            if(scoreLessonMode != null && scoreLessonMode != ""){
                cotent = cotent.replace("(gg)",scoreLessonMode);
            }

            ntv.setNoticeContent(cotent);

        }
    }


	@Override
	public Map<String, String> dealWithScore(String url, String userId, Map<String, String> scoreData) {
		// TODO Auto-generated method stub
		return null;
	}
}
