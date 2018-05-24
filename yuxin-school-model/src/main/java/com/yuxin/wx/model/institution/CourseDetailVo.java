package com.yuxin.wx.model.institution;

import java.util.List;

/**
 * 课程详情
 * @author hello
 *
 */
public class CourseDetailVo {
	private Integer courseInfoId;//		课程id
	private Integer isReservFull;//		预约人数是否已满(0否 1已满)
	private String courseName;//		课程名称
	private String courseInfoDeac;//		课程介绍
	private Float courseInfoPrice;//		课程价格
	private List<String> courseInfoSign;//		课程标签
	private List<CourseImg> courseImgList;//		课程图片集合
	private List<StudentCommentVo> courseCommentList;//		课程评论集合
	private List<CourseInfoVo> hotCourseList;//		热门课程集合
	private Integer isReser;//是否限定预约人数
	private Integer isCollect;//是否收藏
	private Integer isCommentMore;//是否还有更多评论
	private Integer isHotCourseMore;//是否还有更多课程
	private Integer reserCount;//预约人数
	private String mobile;//电话号码
	private Integer insId;//机构id
	private String shareUrl;//分享页面地址
	private String shareName;//分享名称
	public Integer getCourseInfoId() {
		return courseInfoId;
	}
	public void setCourseInfoId(Integer courseInfoId) {
		this.courseInfoId = courseInfoId;
	}
	public Integer getIsReservFull() {
		return isReservFull;
	}
	public void setIsReservFull(Integer isReservFull) {
		this.isReservFull = isReservFull;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseInfoDeac() {
		return courseInfoDeac;
	}
	public void setCourseInfoDeac(String courseInfoDeac) {
		this.courseInfoDeac = courseInfoDeac;
	}
	public Float getCourseInfoPrice() {
		return courseInfoPrice;
	}
	public void setCourseInfoPrice(Float courseInfoPrice) {
		this.courseInfoPrice = courseInfoPrice;
	}
	public List<String> getCourseInfoSign() {
		return courseInfoSign;
	}
	public void setCourseInfoSign(List<String> courseInfoSign) {
		this.courseInfoSign = courseInfoSign;
	}
	public List<CourseImg> getCourseImgList() {
		return courseImgList;
	}
	public void setCourseImgList(List<CourseImg> courseImgList) {
		this.courseImgList = courseImgList;
	}
	public List<StudentCommentVo> getCourseCommentList() {
		return courseCommentList;
	}
	public void setCourseCommentList(List<StudentCommentVo> courseCommentList) {
		this.courseCommentList = courseCommentList;
	}
	public List<CourseInfoVo> getHotCourseList() {
		return hotCourseList;
	}
	public void setHotCourseList(List<CourseInfoVo> hotCourseList) {
		this.hotCourseList = hotCourseList;
	}
	public Integer getIsReser() {
		return isReser;
	}
	public void setIsReser(Integer isReser) {
		this.isReser = isReser;
	}
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
	public Integer getIsCommentMore() {
		return isCommentMore;
	}
	public void setIsCommentMore(Integer isCommentMore) {
		this.isCommentMore = isCommentMore;
	}
	public Integer getIsHotCourseMore() {
		return isHotCourseMore;
	}
	public void setIsHotCourseMore(Integer isHotCourseMore) {
		this.isHotCourseMore = isHotCourseMore;
	}
	public Integer getReserCount() {
		return reserCount;
	}
	public void setReserCount(Integer reserCount) {
		this.reserCount = reserCount;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getInsId() {
		return insId;
	}
	public void setInsId(Integer insId) {
		this.insId = insId;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public String getShareName() {
		return shareName;
	}
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	
}
