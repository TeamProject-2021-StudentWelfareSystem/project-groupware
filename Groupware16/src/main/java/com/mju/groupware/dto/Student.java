package com.mju.groupware.dto;

public class Student extends User {
	
	public enum studentColleges {인문대학, 사회과학대학, 경영대학, 법과대학, ICT융합대학, 미래융합대학}
	public enum studentMajor {국어국문학과, 영어영문학과, 중어중문학과, 일어일문학과, 사학과, 문헌정보학과, 아랍지역학과, 미술사학과, 철학과, 문예창작학과, 
		행정학과, 경제학과, 정치외교학과, 디지털미디어학과, 아동학과, 청소년지도학과,
		경영정보학과, 국제통상학과,
		법학과,
		융합소프트웨어학부, 디지털콘텐츠디자인학과,
		창의융합인재학부, 사회복지학과, 부동산학과, 법무행정학과, 심리치료학과, 미래융합경영학과, 멀티디자인학과, 계약학과}
	public enum studentDoubleMajor {국어국문학과, 영어영문학과, 중어중문학과, 일어일문학과, 사학과, 문헌정보학과, 아랍지역학과, 미술사학과, 철학과, 문예창작학과, 
		행정학과, 경제학과, 정치외교학과, 디지털미디어학과, 아동학과, 청소년지도학과,
		경영정보학과, 국제통상학과,
		법학과,
		융합소프트웨어학부, 디지털콘텐츠디자인학과,
		창의융합인재학부, 사회복지학과, 부동산학과, 법무행정학과, 심리치료학과, 미래융합경영학과, 멀티디자인학과, 계약학과}
	
	private int studentID;
	private int studentGrade;
	private String studentGender;
	private studentColleges studentColleges;
	private studentMajor studentMajor;
	private studentDoubleMajor studentDoubleMajor;
	private int userID; // foreign key
	
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getStudentGrade() {
		return studentGrade;
	}
	public void setStudentGrade(int studentGrade) {
		this.studentGrade = studentGrade;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public studentColleges getStudentColleges() {
		return studentColleges;
	}
	public void setStudentColleges(studentColleges studentColleges) {
		this.studentColleges = studentColleges;
	}
	public studentMajor getStudentMajor() {
		return studentMajor;
	}
	public void setStudentMajor(studentMajor studentMajor) {
		this.studentMajor = studentMajor;
	}
	public studentDoubleMajor getStudentDoubleMajor() {
		return studentDoubleMajor;
	}
	public void setStudentDoubleMajor(studentDoubleMajor studentDoubleMajor) {
		this.studentDoubleMajor = studentDoubleMajor;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
