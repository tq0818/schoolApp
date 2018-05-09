package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.List;

/**
 * 教师风采集合
 * @author hello
 *
 */
public class TeacherStyleVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer teacherId;//		教师id
	private String teacherImgUrl;//		图片地址
	private String teacherName;//		教师姓名
	private String graduteSchool;//		毕业学校
	private String teacherIntro;//	教师简介
	private String teacherSign;//		标签
	private List<String> teacherSignList;
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherImgUrl() {
		return teacherImgUrl;
	}
	public void setTeacherImgUrl(String teacherImgUrl) {
		this.teacherImgUrl = teacherImgUrl;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getGraduteSchool() {
		return graduteSchool;
	}
	public void setGraduteSchool(String graduteSchool) {
		this.graduteSchool = graduteSchool;
	}
	public String getTeacherIntro() {
		return teacherIntro;
	}
	public void setTeacherIntro(String teacherIntro) {
		this.teacherIntro = teacherIntro;
	}
	public String getTeacherSign() {
		return teacherSign;
	}
	public void setTeacherSign(String teacherSign) {
		this.teacherSign = teacherSign;
	}
	public List<String> getTeacherSignList() {
		return teacherSignList;
	}
	public void setTeacherSignList(List<String> teacherSignList) {
		this.teacherSignList = teacherSignList;
	}
	
}
