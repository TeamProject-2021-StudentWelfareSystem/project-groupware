package com.mju.groupware.dto;

public class UserInfoOpen {
	private String UserLoginID;
	private String OpenName;
	private String OpenEmail;
	private String OpenPhoneNum;
	private String OpenMajor;
	private String OpenGrade;

	public String getUserLoginID() {
		return UserLoginID;
	}

	public void setUserLoginID(String userLoginID) {
		UserLoginID = userLoginID;
	}

	public String getOpenName() {
		return OpenName;
	}

	public void setOpenName(String openName) {
		OpenName = openName;
	}

	public String getOpenEmail() {
		return OpenEmail;
	}

	public void setOpenEmail(String openEmail) {
		OpenEmail = openEmail;
	}

	public String getOpenPhoneNum() {
		return OpenPhoneNum;
	}

	public void setOpenPhoneNum(String openPhoneNum) {
		OpenPhoneNum = openPhoneNum;
	}

	public String getOpenMajor() {
		return OpenMajor;
	}

	public void setOpenMajor(String openMajor) {
		OpenMajor = openMajor;
	}

	public String getOpenGrade() {
		return OpenGrade;
	}

	public void setOpenGrade(String openGrade) {
		OpenGrade = openGrade;
	}

}
