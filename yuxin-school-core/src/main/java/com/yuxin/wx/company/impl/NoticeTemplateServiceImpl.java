package com.yuxin.wx.company.impl;

import com.yuxin.wx.api.company.INoticeTemplateService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.NoticeTemplatMapper;
import com.yuxin.wx.model.company.NoticeTemplatVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
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
    public PageFinder<NoticeTemplatVo> queryAllNoticeTemplateByCondition(Map<String, Object> map) {

        List<NoticeTemplatVo> datas = noticeTemplatMapper.queryAllNoticeTemplateList(map);
        NoticeTemplatVo ntv = (NoticeTemplatVo) map.get("ntv");
        int rowCount = noticeTemplatMapper.queryAllNoticeTemplateCount(map);

        PageFinder<NoticeTemplatVo> pageFinder=new PageFinder<NoticeTemplatVo>(ntv.getPage(), ntv.getPageSize(), rowCount, datas);

        return pageFinder;
    }

    @Override
    public int updateTemplateStatus(NoticeTemplatVo ntv) {
        return noticeTemplatMapper.updateTemplatStatus(ntv);
    }
}
