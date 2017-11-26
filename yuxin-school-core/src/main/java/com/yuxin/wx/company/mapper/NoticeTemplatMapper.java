package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.NoticeTemplatVo;

import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 17/11/26.
 */
public interface NoticeTemplatMapper extends BaseMapper<NoticeTemplatVo> {
    /**
     * 根据条件查询所有的通知模版
     * @param map
     * @return
     */
   public List<NoticeTemplatVo> queryAllNoticeTemplateList(Map<String,Object> map);
}
