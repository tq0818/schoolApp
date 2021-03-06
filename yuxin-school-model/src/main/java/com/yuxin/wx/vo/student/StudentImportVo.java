package com.yuxin.wx.vo.student;

import com.yuxin.wx.common.BaseEntity;

public class StudentImportVo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String name; /* 姓名 */
	private String identityTypeCode; /* 证件类型（身份证、护照、港澳通行证、台胞证、军官证、士官证等），字典表 */
	private String identityId; /* 证件号码（身份证号、护照、港澳通行证、台胞证、军官证、士官证等） */
	private String mobile; /* 手机号 */
	private String email; /* 邮箱 */
	private String qq; /* QQ号码 */
	private String emergencyContact; /* 紧急联系人 */
	private String emergencyPhone; /* 紧急联系电话 */
	private Integer deleteFlag; /* 删 */
	private Integer companyId;
	private Integer schoolId;
	private Integer userId;
	private String password;
	private String username;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityTypeCode() {
		return identityTypeCode;
	}

	public void setIdentityTypeCode(String identityTypeCode) {
		this.identityTypeCode = identityTypeCode;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "StudentImportVo [name=" + name + ", identityTypeCode=" + identityTypeCode + ", identityId=" + identityId
				+ ", mobile=" + mobile + ", email=" + email + ", qq=" + qq + ", emergencyContact=" + emergencyContact
				+ ", emergencyPhone=" + emergencyPhone + ", deleteFlag=" + deleteFlag + ", companyId=" + companyId
				+ ", schoolId=" + schoolId + ", userId=" + userId + ", password=" + password + ", username=" + username
				+ "]";
	}
}
