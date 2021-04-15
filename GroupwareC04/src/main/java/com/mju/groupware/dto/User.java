package com.mju.groupware.dto;

public class User {

	public enum userRole {
		STUDENT, PROFESSOR, ADMINISTRATOR
	}

	private int UserID;
	private String UserName;
	private String UserPhoneNum;
	private String UserEmail;
	private String UserLoginID;
	private String UserLoginPwd;
	private String UserModifiedPW;
	private userRole UserRole;
	private String Authority; // ROLE_USER
	private boolean Enabled; // true : 계쩡 활성화, false : 비활성화
	private String date;
	private String StudentColleges;
	private String StudentMajor;

	public String getUserModifiedPW() {
		return UserModifiedPW;
	}

	public String getStudentMajor() {
		return StudentMajor;
	}

	public void setStudentMajor(String studentMajor) {
		StudentMajor = studentMajor;
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

	public String getStudentColleges() {
		return StudentColleges;
	}

	public void setStudentColleges(String studentColleges) {
		StudentColleges = studentColleges;
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

	public userRole getUserRole() {
		return UserRole;
	}

	public void setUserRole(userRole UserRole) {
		this.UserRole = UserRole;
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