package com.mju.groupware.dto;

public class UserEmail {

	private int userEmailID;
	private String userEmail;
	private int userCertificationNum;
	private String userCertificationTime;
	
	public int getUserEmailID() {
		return userEmailID;
	}

	public void setUserEmailID(int userEmailID) {
		this.userEmailID = userEmailID;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String email) {
		this.userEmail = email;
	}

	public int getUserCertificationNum() {
		return userCertificationNum;
	}
	
	public void setUserCertificationNum(int num) {
		this.userCertificationNum = num;
	}

	public String getUserCertificationTime() {
		return userCertificationTime;
		
	}
	public void setUserCertificationTime(String now) {
		this.userCertificationTime = now;
	}
	

}
