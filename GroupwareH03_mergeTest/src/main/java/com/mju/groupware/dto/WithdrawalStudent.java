package com.mju.groupware.dto;

public class WithdrawalStudent extends WithdrawalUser {

	private int WStudentID;
	private String WStudentGrade; // 학년
	private String WStudentGender; // 성별
	private String WStudentColleges; // 단과대학
	private String WStudentMajor; // 전공
	private String WStudentDoubleMajor; // 복수전공
	private int WUserID; // foreign key
	public int getWStudentID() {
		return WStudentID;
	}
	public void setWStudentID(int wStudentID) {
		WStudentID = wStudentID;
	}
	public String getWStudentGrade() {
		return WStudentGrade;
	}
	public void setWStudentGrade(String wStudentGrade) {
		WStudentGrade = wStudentGrade;
	}
	public String getWStudentGender() {
		return WStudentGender;
	}
	public void setWStudentGender(String wStudentGender) {
		WStudentGender = wStudentGender;
	}
	public String getWStudentColleges() {
		return WStudentColleges;
	}
	public void setWStudentColleges(String wStudentColleges) {
		WStudentColleges = wStudentColleges;
	}
	public String getWStudentMajor() {
		return WStudentMajor;
	}
	public void setWStudentMajor(String wStudentMajor) {
		WStudentMajor = wStudentMajor;
	}
	public String getWStudentDoubleMajor() {
		return WStudentDoubleMajor;
	}
	public void setWStudentDoubleMajor(String wStudentDoubleMajor) {
		WStudentDoubleMajor = wStudentDoubleMajor;
	}
	public int getWUserID() {
		return WUserID;
	}
	public void setWUserID(int wUserID) {
		WUserID = wUserID;
	}

}