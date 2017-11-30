package com.yuxin.wx.model.zx;
/**
 * 直播参数模型
 * @author zengdeqiang
 *
 */
@SuppressWarnings("serial")
public class ZxLiveVo {
	/**
	 * 访问直播地址
	 */
	private String domain;
	/**
	 * 房间号
	 */
	private String number;
	/**
	 * 客户端密码
	 */
	private String joinPwd;
	/**
	 * 用户标识号
	 */
	private String uid;
	
	private String k;
	/**
	 * 昵称
	 */
	private String nicKname;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getJoinPwd() {
		return joinPwd;
	}
	public void setJoinPwd(String joinPwd) {
		this.joinPwd = joinPwd;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getNicKname() {
		return nicKname;
	}
	public void setNicKname(String nicKname) {
		this.nicKname = nicKname;
	}
	
	
}
