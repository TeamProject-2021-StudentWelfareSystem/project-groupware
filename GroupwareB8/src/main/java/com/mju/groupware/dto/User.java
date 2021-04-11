package com.mju.groupware.dto;

public class User {

	public enum userRole {
		STUDENT, PROFESSOR, ADMINISTRATOR
	}

	private int userID;
	private String userName;
	private String userPhoneNum;
	private String userEmail;
	private String userLoginID;
	private String userLoginPwd;
	private String userModifiedPW;
	private userRole userRole;
	private String authority; // ROLE_USER
	private boolean enabled; // true : 계쩡 활성화, false : 비활성화
	private String date;
	private UserColleges userColleges;
	private UserMajor userMajor;

	public enum UserColleges {
		인문대학, 사회과학대학, 경영대학, 법과대학, ICT융합대학, 미래융합대학
	}

	public enum UserMajor {
		국어국문학과, 영어영문학과, 중어중문학과, 일어일문학과, 사학과, 문헌정보학과, 아랍지역학과, 미술사학과, 철학과, 문예창작학과, 행정학과, 경제학과, 정치외교학과, 디지털미디어학과, 아동학과,
		청소년지도학과, 경영정보학과, 국제통상학과, 법학과, 융합소프트웨어학부, 디지털콘텐츠디자인학과, 창의융합인재학부, 사회복지학과, 부동산학과, 법무행정학과, 심리치료학과, 미래융합경영학과,
		멀티디자인학과, 계약학과
	}

	public String getUserModifiedPW() {
		return userModifiedPW;
	}

	public void setUserModifiedPW(String userModifiedPW) {
		this.userModifiedPW = userModifiedPW;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

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

	public UserColleges getUserColleges() {
		return userColleges;
	}

	public void setUserColleges(UserColleges userColleges) {
		this.userColleges = userColleges;
	}

	public UserMajor getUserMajor() {
		return userMajor;
	}

	public void setUserMajor(UserMajor userMajor) {
		this.userMajor = userMajor;
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

	public userRole getUserRole() {
		return userRole;
	}

	public void setUserRole(userRole userRole) {
		this.userRole = userRole;
	}

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