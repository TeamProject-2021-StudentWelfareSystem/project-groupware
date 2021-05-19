package com.mju.groupware.dto;

public class Student extends User {

	private int StudentID;
	private String StudentGrade; // 학년
	private String StudentGender; // 성별
	private String StudentColleges; // 단과대학
	private String StudentMajor; // 전공
	private String StudentDoubleMajor; // 복수전공
	private int UserID; // foreign key
	private int WUserID;
	private String WithdrawalDate;

	public String getWithdrawalDate() {
		return WithdrawalDate;
	}

	public void setWithdrawalDate(String withdrawalDate) {
		WithdrawalDate = withdrawalDate;
	}

	public int getWUserID() {
		return WUserID;
	}

	public void setWUserID(int wUserID) {
		WUserID = wUserID;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public String getStudentGrade() {
		return StudentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		StudentGrade = studentGrade;
	}

	public String getStudentGender() {
		return StudentGender;
	}

	public void setStudentGender(String studentGender) {
		StudentGender = studentGender;
	}

	public String getStudentColleges() {
		return StudentColleges;
	}

	public void setStudentColleges(String studentColleges) {
		StudentColleges = studentColleges;
	}

	public String getStudentMajor() {
		return StudentMajor;
	}

	public void setStudentMajor(String studentMajor) {
		StudentMajor = studentMajor;
	}

	public String getStudentDoubleMajor() {
		return StudentDoubleMajor;
	}

	public void setStudentDoubleMajor(String studentDoubleMajor) {
		StudentDoubleMajor = studentDoubleMajor;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

}