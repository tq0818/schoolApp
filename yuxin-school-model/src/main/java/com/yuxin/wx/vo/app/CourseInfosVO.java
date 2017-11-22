package com.yuxin.wx.vo.app;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 存放组装的课程结果
 * @author admin
 *
 */
public class CourseInfosVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date time;
	private List<StudentCourseVO> courseList;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<StudentCourseVO> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<StudentCourseVO> courseList) {
		this.courseList = courseList;
	}
}
