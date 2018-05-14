package com.yuxin.wx.api.institution;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.CommentApp;

public interface CommentManageService {

    /**
     * 查询机构评论
     * @param commentApp
     * @return
     */
    PageFinder<CommentApp> findInsComment(CommentApp commentApp);

    /**
     * 查询机构下课程评论
     * @param commentApp
     * @return
     */
    PageFinder<CommentApp> findInsClassComment(CommentApp commentApp);

    /**
     * 删除评论和审核评论
     * @param id
     */
    void update(Integer id);
}
