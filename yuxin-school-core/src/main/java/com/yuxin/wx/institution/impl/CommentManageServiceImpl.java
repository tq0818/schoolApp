package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.CommentManageService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.CommentManageMapper;
import com.yuxin.wx.model.institution.CommentApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentManageServiceImpl extends BaseServiceImpl implements CommentManageService {
    @Autowired
    private CommentManageMapper commentManageMapper;
    @Override
    public PageFinder<CommentApp> findInsComment(CommentApp commentApp) {
        List<CommentApp> data = commentManageMapper.findInsComment(commentApp);
        Integer count = commentManageMapper.findInsCommentCount(commentApp);
        PageFinder<CommentApp> pageFinder = new PageFinder<>(commentApp.getPage(),commentApp.getPageSize(),count,data);
        return pageFinder;
    }

    @Override
    public PageFinder<CommentApp> findInsClassComment(CommentApp commentApp) {
        List<CommentApp> data = commentManageMapper.findInsClassComment(commentApp);
        Integer count = commentManageMapper.findInsCommentClassCount(commentApp);
        PageFinder<CommentApp> pageFinder = new PageFinder<>(commentApp.getPage(),commentApp.getPageSize(),count,data);
        return pageFinder;
    }

    @Override
    public void update(Integer id) {
        commentManageMapper.update(id);
    }
}
