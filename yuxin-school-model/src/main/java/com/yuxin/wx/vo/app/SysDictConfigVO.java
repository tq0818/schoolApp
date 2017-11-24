package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 返回字典表数据
 * @author admin
 *
 */
public class SysDictConfigVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemCode; //编码
	private String name;//编码名称
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
