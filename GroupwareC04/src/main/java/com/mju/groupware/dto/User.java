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
	private UserColleges UserColleges;
	private UserMajor UserMajor;

	public enum UserColleges {
		인문대학, 사회과학대학, 경영대학, 법과대학, ICT융합대학, 미래융합대학
	}

	public enum UserMajor {
		국어국문학과, 영어영문학과, 중어중문학과, 일어일문학과, 사학과, 문헌정보학과, 아랍지역학과, 미술사학과, 철학과, 문예창작학과, 행정학과, 경제학과, 정치외교학과, 디지털미디어학과, 아동학과,
		청소년지도학과, 경영정보학과, 국제통상학과, 법학과, 융합소프트웨어학부, 디지털콘텐츠디자인학과, 창의융합인재학부, 사회복지학과, 부동산학과, 법무행정학과, 심리치료학과, 미래융합경영학과,
		멀티디자인학과, 계약학과
	}

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

	public UserColleges getUserColleges() {
		return UserColleges;
	}

	public void setUserColleges(UserColleges UserColleges) {
		this.UserColleges = UserColleges;
	}

	public UserMajor getUserMajor() {
		return UserMajor;
	}

	public void setUserMajor(UserMajor UserMajor) {
		this.UserMajor = UserMajor;
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