package com.yuxin.wx.model.queAns;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:QuestionClassify
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class QuestionClassify extends BaseEntity {
	
	
	private String	itemId;		 /* 学科小类ID */ 
	private String	classifyName;		 /* 分类的别名 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private Integer	classType;		 /* 分类类型（1. 自定义分类  2. 学科分类） */ 
	private Integer	delFlag;		 /* 是否启用 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private String 	itemName;		/* 学科小类名*/
	
	private Integer id;			//系统标签id
	private String	labName;	//标签名称
	private Integer labType;	//标签类型
	private Integer labelStatus;//标签状态
	private Date creatTime;		//创建时间
	private Date updateTime;	//更新时间


	// Constructor
	public QuestionClassify() {
	}
	
	/**
	 * full Constructor
	 */
	

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为QuestionClassify可以实现连缀设置属性
	
	public String getItemId() {
		return itemId;
	}

	public QuestionClassify(String itemId, String classifyName, Integer companyId, Integer schoolId, Integer classType,
	        Integer delFlag, Date createTime, String itemName, Integer id, String labName, Integer labType, Integer labelStatus,
	        Date creatTime, Date updateTime) {
		super();
		this.itemId = itemId;
		this.classifyName = classifyName;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.classType = classType;
		this.delFlag = delFlag;
		this.createTime = createTime;
		this.itemName = itemName;
		this.id = id;
		this.labName = labName;
		this.labType = labType;
		this.labelStatus = labelStatus;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

	public QuestionClassify setItemId(String itemId) {
		this.itemId = itemId;
		return this;
	}
	
	
	public String getClassifyName() {
		return classifyName;
	}

	public QuestionClassify setClassifyName(String classifyName) {
		this.classifyName = classifyName;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public QuestionClassify setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public QuestionClassify setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getClassType() {
		return classType;
	}

	public QuestionClassify setClassType(Integer classType) {
		this.classType = classType;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public QuestionClassify setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public QuestionClassify setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	

	/**
	 * @author jishangyang 2017年11月26日 下午8:54:29
	 * @Method: toString 
	 * @Description: TODO
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	
	@Override
	public String toString() {
		return "QuestionClassify [itemId=" + itemId + ", classifyName=" + classifyName + ", companyId=" + companyId
		        + ", schoolId=" + schoolId + ", classType=" + classType + ", delFlag=" + delFlag + ", createTime=" + createTime
		        + ", itemName=" + itemName + ", id=" + id + ", labName=" + labName + ", labType=" + labType + ", labelStatus="
		        + labelStatus + ", creatTime=" + creatTime + ", updateTime=" + updateTime + "]";
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getLabName() {
		return labName;
	}

	
	public void setLabName(String labName) {
		this.labName = labName;
	}

	
	public Integer getLabType() {
		return labType;
	}

	
	public void setLabType(Integer labType) {
		this.labType = labType;
	}

	
	public Integer getLabelStatus() {
		return labelStatus;
	}

	
	public void setLabelStatus(Integer labelStatus) {
		this.labelStatus = labelStatus;
	}

	
	public Date getCreatTime() {
		return creatTime;
	}

	
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	
	public Date getUpdateTime() {
		return updateTime;
	}

	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
