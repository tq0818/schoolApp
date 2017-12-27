package com.yuxin.wx.user.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.app.UserStudyPlay;
import com.yuxin.wx.model.user.UserHistory;
import com.yuxin.wx.vo.user.UserHistoryAllVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface UserHistoryMapper extends BaseMapper<UserHistory> {
	
	int queryCountByClassTypeIdAndUserId(UserHistory search);

    void insertHistoryAll(UserHistoryAllVo userHistoryAllVo);

    void insertPlayLogs(UserHistoryAllVo uha);
    
    UserStudyPlay queryUserStudyPlay(Map map);
    
    void insertUserStudyPlay(Map map);
    
    void updateUserStudyPlay(Map map);
    
    String queryVideoTime(Integer lectureId);

    /**
     * 查询用户学习的课程的时长
     * @param userId
     * @return
     */
    List<UserHistory> findStudyRecord(Integer userId);
}