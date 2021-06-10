package com.mju.groupware.dto;

public class UserEmail {

	private int UserEmailID;
	private String UserEmail;
	private int UserCertificationNum;
	private String UserCertificationTime;

	private int Counter;
	private String From;
	private String Content;
	private String Title;
	private String Date;

	public int getUserEmailID() {
		return UserEmailID;
	}

	public void setUserEmailID(int userEmailID) {
		UserEmailID = userEmailID;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public int getUserCertificationNum() {
		return UserCertificationNum;
	}

	public void setUserCertificationNum(int userCertificationNum) {
		UserCertificationNum = userCertificationNum;
	}

	public String getUserCertificationTime() {
		return UserCertificationTime;
	}

	public void setUserCertificationTime(String userCertificationTime) {
		UserCertificationTime = userCertificationTime;
	}

	public int getCounter() {
		return Counter;
	}

	public void setCounter(int counter) {
		Counter = counter;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

}
