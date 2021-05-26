package com.mju.groupware.dto;

public class WithdrawalUser {

	private String WUserName; // 이름
	private String WUserPhoneNum; // 핸드폰 번호
	private String WUserEmail; // 이메일
	private String WUserLoginID; // 로그인 ID
	private String WUserLoginPwd;
	private String WUserRole; // 'STUDENT', 'PROFESSOR', 'ADMINISTRATOR'
	private String WAuthority; // ROLE_USER, ROLE_ADMIN
	private String WithdrawalDate; // 탈퇴 날짜
	private boolean WEnabled; // true : 계쩡 활성화, false : 비활성화
	private int WUserID;
	private int UserID;

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getWUserLoginPwd() {
		return WUserLoginPwd;
	}

	public void setWUserLoginPwd(String wUserLoginPwd) {
		WUserLoginPwd = wUserLoginPwd;
	}

	public String getWUserName() {
		return WUserName;
	}

	public void setWUserName(String wUserName) {
		WUserName = wUserName;
	}

	public String getWUserPhoneNum() {
		return WUserPhoneNum;
	}

	public void setWUserPhoneNum(String wUserPhoneNum) {
		WUserPhoneNum = wUserPhoneNum;
	}

	public String getWUserEmail() {
		return WUserEmail;
	}

	public void setWUserEmail(String wUserEmail) {
		WUserEmail = wUserEmail;
	}

	public String getWUserLoginID() {
		return WUserLoginID;
	}

	public void setWUserLoginID(String wUserLoginID) {
		WUserLoginID = wUserLoginID;
	}
	
	public String getWUserRole() {
		return WUserRole;
	}

	public void setWUserRole(String wUserRole) {
		WUserRole = wUserRole;
	}

	public String getWAuthority() {
		return WAuthority;
	}

	public void setWAuthority(String wAuthority) {
		WAuthority = wAuthority;
	}

	public String getWithdrawalDate() {
		return WithdrawalDate;
	}

	public void setWithdrawalDate(String withdrawalDate) {
		WithdrawalDate = withdrawalDate;
	}

	public boolean isWEnabled() {
		return WEnabled;
	}

	public void setWEnabled(boolean wEnabled) {
		WEnabled = wEnabled;
	}

	public int getWUserID() {
		return WUserID;
	}

	public void setWUserID(int wUserID) {
		WUserID = wUserID;
	}

}