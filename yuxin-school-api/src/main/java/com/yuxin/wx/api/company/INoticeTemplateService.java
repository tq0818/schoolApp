package com.yuxin.wx.api.company;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.NoticeTemplatVo;

import java.util.Map;

/**
 * Created by lym_gxm on 17/11/26.
 */
public interface INoticeTemplateService {
    /**
     * 根据条件查询通知模版
     * @return
     */
    public PageFinder<NoticeTemplatVo> queryAllNoticeTemplateByCondition(Map<String,Object> map);

    /**
     * 更新通知模版状态
     * @param ntv
     * @return
     */
    public int updateTemplateStatus(NoticeTemplatVo ntv);
}
