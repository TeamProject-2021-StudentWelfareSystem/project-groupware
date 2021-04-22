package com.mju.groupware.dto;

public class User {

	private int UserID;
	private String UserName; // 이름
	private String UserPhoneNum; // 핸드폰 번호
	private String UserEmail; // 이메일
	private String UserLoginID; // 로그인 ID
	private String UserLoginPwd; // 로그인 Pwd
	private String UserModifiedPW; // ? // PW?Pwd?
	private String UserRole; // 'STUDENT', 'PROFESSOR', 'ADMINISTRATOR'
	private String Authority; // ROLE_USER, ROLE_ADMIN
	private boolean Enabled; // true : 계쩡 활성화, false : 비활성화
	private String date;

	public String getUserModifiedPW() {
		return UserModifiedPW;
	}
	
	public void setUserModifiedPW(String UserModifiedPW) {
		this.UserModifiedPW = UserModifiedPW;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int UserID) {
		this.UserID = UserID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public String getUserPhoneNum() {
		return UserPhoneNum;
	}


	public void setUserPhoneNum(String UserPhoneNum) {
		this.UserPhoneNum = UserPhoneNum;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String UserEmail) {
		this.UserEmail = UserEmail;
	}

	public String getUserLoginID() {
		return UserLoginID;
	}

	public void setUserLoginID(String UserLoginID) {
		this.UserLoginID = UserLoginID;
	}

	public String getUserLoginPwd() {
		return UserLoginPwd;
	}

	public void setUserLoginPwd(String UserLoginPwd) {
		this.UserLoginPwd = UserLoginPwd;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public String getAuthority() {
		return Authority;
	}

	public void setAuthority(String Authority) {
		this.Authority = Authority;
	}

	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean Enabled) {
		this.Enabled = Enabled;
	}

}