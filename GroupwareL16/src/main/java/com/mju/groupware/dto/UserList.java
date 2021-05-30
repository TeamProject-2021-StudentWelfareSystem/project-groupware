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
	// 로그인 활성화
	private boolean Enabled;
	// 탈퇴여부
	private boolean Withdrawal;
	private String OpenName;
	private String OpenEmail;
	private String OpenPhoneNum;
	private String OpenMajor;
	private String OpenGrade;

	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public boolean isWithdrawal() {
		return Withdrawal;
	}

	public void setWithdrawal(boolean withdrawal) {
		Withdrawal = withdrawal;
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
