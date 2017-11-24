package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 学生表基础信息
 * @author admin
 *
 */
public class StudentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//学生id
	private String name;//学生姓名
	private String eduStep;//阶段
	private String 	eduYear;//入学年份
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEduStep() {
		return eduStep;
	}
	public void setEduStep(String eduStep) {
		this.eduStep = eduStep;
	}
	public String getEduYear() {
		return eduYear;
	}
	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}
	
}
