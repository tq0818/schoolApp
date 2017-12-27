package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 错题分类
 * @author admin
 *
 */
public class TikuUserWrongDigestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer subjectId;//题库科目id
	private String subjectName;
	private Integer num;//错题书
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}
