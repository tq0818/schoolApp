package com.yuxin.wx.model.institution;
/**
 * 课程图片
 * @author hello
 *
 */
public class CourseImg {
	private String imgUrl;//		图片地址
	private Integer commentId;//		评论id
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
}
