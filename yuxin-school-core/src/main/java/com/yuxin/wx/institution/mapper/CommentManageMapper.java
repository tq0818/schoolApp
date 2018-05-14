package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.CommentApp;

import java.util.List;

public interface CommentManageMapper {

    /**
     * 查询机构评论
     * @param commentApp
     * @return
     */
    List<CommentApp> findInsComment(CommentApp commentApp);

    /**
     * 查询机构评论总数
     * @param commentApp
     * @return
     */
    Integer findInsCommentCount(CommentApp commentApp);

    /**
     * 查询机构课程评论
     * @param commentApp
     * @return
     */
    List<CommentApp> findInsClassComment(CommentApp commentApp);

    /**
     * 查询机构课程评论总数
     * @param commentApp
     * @return
     */
    Integer findInsCommentClassCount(CommentApp commentApp);

    /**
     * 删除评论和审核评论
     * @param id
     */
    void update(CommentApp commentApp);
}
