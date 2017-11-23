package com.yuxin.wx.vo.app;

import java.io.Serializable;
import java.util.List;

/**
 * 学生直播课返回结果
 * @author admin
 *
 */
public class CourseScheResultVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer flag;//状态标志,0成功,1,未查询到数据,2用户未登录或未获取到学生ID
	private List<CourseInfosVO> resultList;//查询结果
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public List<CourseInfosVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<CourseInfosVO> resultList) {
		this.resultList = resultList;
	}
	
}
