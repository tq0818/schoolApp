package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.CommentManageService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.CommentManageMapper;
import com.yuxin.wx.model.institution.CommentApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class CommentManageServiceImpl extends BaseServiceImpl implements CommentManageService {
    @Autowired
    private CommentManageMapper commentManageMapper;
    @Override
    public PageFinder<CommentApp> findInsComment(CommentApp commentApp) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        DateFormat format2 = new SimpleDateFormat("HH:mm");
        if(commentApp.getPage() == 1){
            commentApp.setPage(0);
        }else{
            commentApp.setPage((commentApp.getPage()-1)*10);
        }
        commentApp.setPageSize(10);

        List<CommentApp> data = commentManageMapper.findInsComment(commentApp);
        Integer count = commentManageMapper.findInsCommentCount(commentApp);

        PageFinder<CommentApp> pageFinder = new PageFinder<>(commentApp.getPage()/10,commentApp.getPageSize(),count,data);

        for(int i =0;i<pageFinder.getData().size();i++){
            pageFinder.getData().get(i).setCreateTimeText(format.format(pageFinder.getData().get(i).getCreateTime()));
            //设置分钟
            pageFinder.getData().get(i).setCreateTimeText2(format2.format(pageFinder.getData().get(i).getCreateTime()));
        }
        return pageFinder;
    }

    @Override
    public PageFinder<CommentApp> findInsClassComment(CommentApp commentApp) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        DateFormat format2 = new SimpleDateFormat("HH:mm");
        if(commentApp.getPage() == 1){
            commentApp.setPage(0);
        }else{
            commentApp.setPage((commentApp.getPage()-1)*10);
        }
        commentApp.setPageSize(10);
        List<CommentApp> data = commentManageMapper.findInsClassComment(commentApp);
        Integer count = commentManageMapper.findInsCommentClassCount(commentApp);
        PageFinder<CommentApp> pageFinder = new PageFinder<>(commentApp.getPage()/10,commentApp.getPageSize(),count,data);
        for(int i =0;i<pageFinder.getData().size();i++){
            pageFinder.getData().get(i).setCreateTimeText(format.format(pageFinder.getData().get(i).getCreateTime()));
            //设置分钟
            pageFinder.getData().get(i).setCreateTimeText2(format2.format(pageFinder.getData().get(i).getCreateTime()));
        }
        return pageFinder;
    }

    @Override
    public void update(CommentApp commentApp) {
        commentManageMapper.update(commentApp);
    }

    @Override
    public Integer commentCuont() {
        return commentManageMapper.commentCuont();
    }


}
