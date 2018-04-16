package com.yuxin.wx.vo.app;

import java.sql.Date;

import com.yuxin.wx.common.BaseEntity;
/**
 * 学生购买的课程信息
 * @author admin
 *
 */
public class StudentCourseVO extends BaseEntity{
	private Integer teacherId;//教师ID
	private Integer comId;//商品ID
	private Integer proId;
	private String comName;//商品名称
	private Integer buyNum;//购买数
	private String coverUrl;//课程地址
	private Integer faceFlag;
	private Integer liveFlag;
	private Integer videoFlag;
	private Double realPrice;//真是价格
	private String status;//是否上架
	private Integer classTypeId;//课程ID
	private Date lessonDate;//
	private String lessonTimeStart;//课程开始时间
	private String lessonTimeEnd;//课程结束时间
	private String lessonName;//章节名称
	private String teacherName;//教师名称
	private String schoolShortName;//学校简称
	private Integer isOver;//是否结束  0即将开始,1是直播中,2是已完结
	private Integer isToday;//是否是今天的课程 0当天之后的，1则是当天，2则是当天桌子前的
	private String afterStudyUrl;
	private String beforeStudyUrl;
	private Integer showFlag;//显示状态 0课程详情，1观看回放,
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public Integer getFaceFlag() {
		return faceFlag;
	}
	public void setFaceFlag(Integer faceFlag) {
		this.faceFlag = faceFlag;
	}
	public Integer getLiveFlag() {
		return liveFlag;
	}
	public void setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
	}
	public Integer getVideoFlag() {
		return videoFlag;
	}
	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public String getLessonTimeStart() {
		return lessonTimeStart;
	}
	public void setLessonTimeStart(String lessonTimeStart) {
		this.lessonTimeStart = lessonTimeStart;
	}
	public String getLessonTimeEnd() {
		return lessonTimeEnd;
	}
	public void setLessonTimeEnd(String lessonTimeEnd) {
		this.lessonTimeEnd = lessonTimeEnd;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSchoolShortName() {
		return schoolShortName;
	}
	public void setSchoolShortName(String schoolShortName) {
		this.schoolShortName = schoolShortName;
	}
	public Integer getIsOver() {
		return isOver;
	}
	public void setIsOver(Integer isOver) {
		this.isOver = isOver;
	}
	public Integer getIsToday() {
		return isToday;
	}
	public void setIsToday(Integer isToday) {
		this.isToday = isToday;
	}
	public String getAfterStudyUrl() {
		return afterStudyUrl;
	}
	public void setAfterStudyUrl(String afterStudyUrl) {
		this.afterStudyUrl = afterStudyUrl;
	}
	public String getBeforeStudyUrl() {
		return beforeStudyUrl;
	}
	public void setBeforeStudyUrl(String beforeStudyUrl) {
		this.beforeStudyUrl = beforeStudyUrl;
	}
	public Integer getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}
	
}
