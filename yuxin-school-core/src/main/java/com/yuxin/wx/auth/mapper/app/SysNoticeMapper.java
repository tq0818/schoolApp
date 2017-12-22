package com.yuxin.wx.auth.mapper.app;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.NoticeTemplatVo;

import java.util.Map;

/**
 * Created by lym_gxm on 17/12/2.
 * 系统通知监听
 */
public interface SysNoticeMapper extends BaseMapper<NoticeTemplatVo> {
    /**
     * 根据url获取通知模版内容
     * @param paramsMap
     * @return
     */
    NoticeTemplatVo queryNoticeTemplateByUrl(Map<String, Object> paramsMap);
}
