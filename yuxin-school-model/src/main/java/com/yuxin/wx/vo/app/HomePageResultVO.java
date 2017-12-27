package com.yuxin.wx.vo.app;

import java.io.Serializable;
import java.util.List;
/**
 * 首页第二板块结果集
 * @author admin
 *
 */
public class HomePageResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer flag;//是否成功标志,0是未成功,1是成功
	private String msg;//返回的消息
	private List<SysDictAppVO> gradeList;//学段集合
	private List<CourseResultVO> courseResultVOList;//查询的结果集
	private List<SysDictAppVO> subjectList;//学科的集合
	private List<SysDictAppVO> pullDataList;//查询中考高考选择学科返回的知识面
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<CourseResultVO> getCourseResultVOList() {
		return courseResultVOList;
	}
	public void setCourseResultVOList(List<CourseResultVO> courseResultVOList) {
		this.courseResultVOList = courseResultVOList;
	}
	public List<SysDictAppVO> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<SysDictAppVO> gradeList) {
		this.gradeList = gradeList;
	}
	public List<SysDictAppVO> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<SysDictAppVO> subjectList) {
		this.subjectList = subjectList;
	}
	public List<SysDictAppVO> getPullDataList() {
		return pullDataList;
	}
	public void setPullDataList(List<SysDictAppVO> pullDataList) {
		this.pullDataList = pullDataList;
	}
	
}
