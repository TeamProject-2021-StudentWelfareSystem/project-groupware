package com.mju.groupware.dto;

public class UserList {
	private int UserID;
	// 학번
	private String UserLoginID;
	// 이름
	private String UserName;
	// 전화번호
	private String UserPhoneNum;
	// 이메일
	private String UserEmail;
	// 학생 교수 관리자
	private String UserRole;
	// 권한
	private String Authority;
	// 최근로그인한 날짜
	private String LoginDate;

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserLoginID() {
		return UserLoginID;
	}

	public void setUserLoginID(String userLoginID) {
		UserLoginID = userLoginID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPhoneNum() {
		return UserPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		UserPhoneNum = userPhoneNum;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
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

	public void setAuthority(String authority) {
		Authority = authority;
	}

	public String getLoginDate() {
		return LoginDate;
	}

	public void setLoginDate(String loginDate) {
		LoginDate = loginDate;
	}

}
