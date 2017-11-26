package com.yuxin.wx.company.impl;

import com.yuxin.wx.api.company.INoticeTemplateService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.company.mapper.NoticeTemplatMapper;
import com.yuxin.wx.model.company.NoticeTemplatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 17/11/26.
 */
@Service
@Transactional
public class NoticeTemplateServiceImpl extends BaseServiceImpl implements INoticeTemplateService{

    @Autowired
    private NoticeTemplatMapper noticeTemplatMapper;


    @Override
    public List<NoticeTemplatVo> queryAllNoticeTemplateByCondition(Map<String, Object> map) {
        return noticeTemplatMapper.queryAllNoticeTemplateList(map);
    }
}
