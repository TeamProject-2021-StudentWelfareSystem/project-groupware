package com.mju.groupware.dto;

public class UserEmail {

	private int UserEmailID;
	private String UserEmail;
	private int UserCertificationNum;
	private String UserCertificationTime;
	
	public int getUserEmailID() {
		return UserEmailID;
	}

	public void setUserEmailID(int userEmailID) {
		this.UserEmailID = userEmailID;
	}
	
	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String email) {
		this.UserEmail = email;
	}

	public int getUserCertificationNum() {
		return UserCertificationNum;
	}
	
	public void setUserCertificationNum(int num) {
		this.UserCertificationNum = num;
	}

	public String getUserCertificationTime() {
		return UserCertificationTime;
		
	}
	public void setUserCertificationTime(String now) {
		this.UserCertificationTime = now;
	}
	

}
