package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 学魔榜
 * @author admin
 *
 */
public class StudentRankVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//用户id
	private String nickName;//用户昵称
	private String mobile;//用户电话
	private Integer rankScore;//分数
	private String headPicMax;//头像地址
	private Integer rankId;//排名
	private String isView;// 1 显示 0不显示

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getRankScore() {
		return rankScore;
	}
	public void setRankScore(Integer rankScore) {
		this.rankScore = rankScore;
	}
	public String getHeadPicMax() {
		return headPicMax;
	}
	public void setHeadPicMax(String headPicMax) {
		this.headPicMax = headPicMax;
	}
	public Integer getRankId() {
		return rankId;
	}
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}
}
