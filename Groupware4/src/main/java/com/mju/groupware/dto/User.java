package com.mju.groupware.dto;

public class User {
	
//	public enum userRole {STUDENT, PROFESSOR, ADMINISTRATOR}
	
	private int userID;
	private String userName; 
	private String userPhoneNum;
	private String userEmail;
	private String userLoginID;
	private String userLoginPwd; 
//	private userRole userRole;
	private String authority; // ROLE_USER
	private boolean enabled; // true : 계쩡 활성화, false : 비활성화
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserLoginID() {
		return userLoginID;
	}
	public void setUserLoginID(String userLoginID) {
		this.userLoginID = userLoginID;
	}
	public String getUserLoginPwd() {
		return userLoginPwd;
	}
	public void setUserLoginPwd(String userLoginPwd) {
		this.userLoginPwd = userLoginPwd;
	}
//	public userRole getUserRole() {
//		return userRole;
//	}
//	public void setUserRole(userRole userRole) {
//		this.userRole = userRole;
//	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
