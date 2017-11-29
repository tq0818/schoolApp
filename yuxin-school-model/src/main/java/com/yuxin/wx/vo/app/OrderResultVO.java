package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 订单返回结果
 * @author admin
 *
 */
public class OrderResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer flag;//是否成功,0失败,1是成功
	private String msg;//返回消息
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

}
