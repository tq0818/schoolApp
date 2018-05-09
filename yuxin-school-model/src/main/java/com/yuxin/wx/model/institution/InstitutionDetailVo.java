package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.List;

/**
 * APP前端机构详情VO
 * @author hello
 *
 */
public class InstitutionDetailVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer institutionId;//		机构id
	private String institutionName;//		机构名称
	private Float institutionScore;//	机构评分等级
	private String institutionArea;//		机构所在区域
	private List<String> institutionSignList;//		机构标签
	private String institutionImgUrl	;	//机构图片
	private String reServationPackage	;	//预约礼包
	private String institutionAddress	;	//机构地址
	private Integer isCertified		;//是否认证(0否 1是)
	private List<String> institutionMobileList;//		机构电话集合
	private String specialServer;//		特色服务
	private List<InstitutionStyleVo> institutionList;//		机构风采结果集合
	private List<CourseInfoVo> hotOnLineCourseList	;//	热门线上课程集合
	private List<CourseInfoVo> hotCourseList;//		热门线下课程集合
	private List<TeacherStyleVo> teacherStyleList;//		教师风采集合
	private List<StudentCommentVo> studentCommentList;//		学员评论
	private List<InstitutionCly> chainInstitutionList;//		连锁机构(预留)
	private List<InstitutionCly> guessLikeList		;//猜你喜欢
	private String mobile;//电话集合
	private Integer isHotOnLineMore;//是否存在更多的线上课程
	private Integer isHotCourseMore;//是否存在更多的线下课程
	private Integer isCommentMore;//是否存在更多的评论
	public Integer getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public Float getInstitutionScore() {
		return institutionScore;
	}
	public void setInstitutionScore(Float institutionScore) {
		this.institutionScore = institutionScore;
	}
	public String getInstitutionArea() {
		return institutionArea;
	}
	public void setInstitutionArea(String institutionArea) {
		this.institutionArea = institutionArea;
	}
	public List<String> getInstitutionSignList() {
		return institutionSignList;
	}
	public void setInstitutionSignList(List<String> institutionSignList) {
		this.institutionSignList = institutionSignList;
	}
	public String getInstitutionImgUrl() {
		return institutionImgUrl;
	}
	public void setInstitutionImgUrl(String institutionImgUrl) {
		this.institutionImgUrl = institutionImgUrl;
	}
	public String getReServationPackage() {
		return reServationPackage;
	}
	public void setReServationPackage(String reServationPackage) {
		this.reServationPackage = reServationPackage;
	}
	public String getInstitutionAddress() {
		return institutionAddress;
	}
	public void setInstitutionAddress(String institutionAddress) {
		this.institutionAddress = institutionAddress;
	}
	public Integer getIsCertified() {
		return isCertified;
	}
	public void setIsCertified(Integer isCertified) {
		this.isCertified = isCertified;
	}
	public List<String> getInstitutionMobileList() {
		return institutionMobileList;
	}
	public void setInstitutionMobileList(List<String> institutionMobileList) {
		this.institutionMobileList = institutionMobileList;
	}
	public String getSpecialServer() {
		return specialServer;
	}
	public void setSpecialServer(String specialServer) {
		this.specialServer = specialServer;
	}
	public List<InstitutionStyleVo> getInstitutionList() {
		return institutionList;
	}
	public void setInstitutionList(List<InstitutionStyleVo> institutionList) {
		this.institutionList = institutionList;
	}
	public List<CourseInfoVo> getHotOnLineCourseList() {
		return hotOnLineCourseList;
	}
	public void setHotOnLineCourseList(List<CourseInfoVo> hotOnLineCourseList) {
		this.hotOnLineCourseList = hotOnLineCourseList;
	}
	public List<CourseInfoVo> getHotCourseList() {
		return hotCourseList;
	}
	public void setHotCourseList(List<CourseInfoVo> hotCourseList) {
		this.hotCourseList = hotCourseList;
	}
	public List<TeacherStyleVo> getTeacherStyleList() {
		return teacherStyleList;
	}
	public void setTeacherStyleList(List<TeacherStyleVo> teacherStyleList) {
		this.teacherStyleList = teacherStyleList;
	}
	public List<StudentCommentVo> getStudentCommentList() {
		return studentCommentList;
	}
	public void setStudentCommentList(List<StudentCommentVo> studentCommentList) {
		this.studentCommentList = studentCommentList;
	}
	public List<InstitutionCly> getChainInstitutionList() {
		return chainInstitutionList;
	}
	public void setChainInstitutionList(List<InstitutionCly> chainInstitutionList) {
		this.chainInstitutionList = chainInstitutionList;
	}
	public List<InstitutionCly> getGuessLikeList() {
		return guessLikeList;
	}
	public void setGuessLikeList(List<InstitutionCly> guessLikeList) {
		this.guessLikeList = guessLikeList;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getIsHotOnLineMore() {
		return isHotOnLineMore;
	}
	public void setIsHotOnLineMore(Integer isHotOnLineMore) {
		this.isHotOnLineMore = isHotOnLineMore;
	}
	public Integer getIsHotCourseMore() {
		return isHotCourseMore;
	}
	public void setIsHotCourseMore(Integer isHotCourseMore) {
		this.isHotCourseMore = isHotCourseMore;
	}
	public Integer getIsCommentMore() {
		return isCommentMore;
	}
	public void setIsCommentMore(Integer isCommentMore) {
		this.isCommentMore = isCommentMore;
	}
	
}
