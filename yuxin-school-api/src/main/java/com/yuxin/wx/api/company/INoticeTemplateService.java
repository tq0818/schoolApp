package com.yuxin.wx.api.company;

import com.yuxin.wx.model.company.NoticeTemplatVo;

import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 17/11/26.
 */
public interface INoticeTemplateService {
    /**
     * 根据条件查询通知模版
     * @return
     */
    public List<NoticeTemplatVo> queryAllNoticeTemplateByCondition(Map<String,Object> map);

}
