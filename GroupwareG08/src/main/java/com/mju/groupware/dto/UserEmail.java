package com.mju.groupware.dto;

public class UserEmail {

	private int UserEmailID;
	private String UserEmail;
	private int UserCertificationNum;
	private String UserCertificationTime;
	
	private String SenderEmail;
	private String content;
	private String title;

	public int getUserEmailID() {
		return UserEmailID;
	}

	public String getSenderEmail() {
		return SenderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		SenderEmail = senderEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
