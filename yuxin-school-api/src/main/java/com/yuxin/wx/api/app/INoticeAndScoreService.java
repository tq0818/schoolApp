package com.yuxin.wx.api.app;

import com.yuxin.wx.model.integral.ScoreRulsAppVo;

import java.util.Map;

/**
 * Created by lym_gxm on 17/12/2.
 */
public interface INoticeAndScoreService {

    /**
     * 填充发送通知内容并 发送信息
     * @param url
     * @param userId
     * @param cotentData
     * 参数说明
     * userName 用户名
     * courseName 课程名
     * classTime 开课时间
     * scoreUpdateTopic 积分变更名目
     * scoreUpdateValue 积分变化值
     * scoreTotalValue 现有积分
     * scoreLessonMode 课次名称
     *
     * @return
     */
    public String sendMsg(String url,String userId,Map<String,String> cotentData);


    /**
     * 处理用户积分
     * @param url
     * @param scoreData
     * @return
     */
    public Map<String, String> dealWithScore(String url, String userId, Map<String,String>scoreData);

}
