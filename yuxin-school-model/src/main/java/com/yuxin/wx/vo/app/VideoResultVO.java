package com.yuxin.wx.vo.app;

import java.io.Serializable;
import java.util.List;
/**
 * 录播的结果
 * @author admin
 *
 */
public class VideoResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4436105494205456548L;
	private List<VideoInfoVO> finishedList;
	private List<VideoInfoVO> unFinishedList;
	private Integer Flag;
	private String message;
	public List<VideoInfoVO> getFinishedList() {
		return finishedList;
	}
	public void setFinishedList(List<VideoInfoVO> finishedList) {
		this.finishedList = finishedList;
	}
	public List<VideoInfoVO> getUnFinishedList() {
		return unFinishedList;
	}
	public void setUnFinishedList(List<VideoInfoVO> unFinishedList) {
		this.unFinishedList = unFinishedList;
	}
	public Integer getFlag() {
		return Flag;
	}
	public void setFlag(Integer flag) {
		Flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
