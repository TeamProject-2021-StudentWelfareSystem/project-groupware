package com.mju.groupware.dto;

public class UserReview {
	private String ReviewID;
	private String Positive;
	private String Contribute;
	private String Respect;
	private String Flexible;
	private String ClassName;
	private String ClassProfessorName;
	private String ReviewDate;
	private String UserID;
	private String WriterUserID;
	private String TeamName;
	
	public String getWriterUserID() {
		return WriterUserID;
	}
	public void setWriterUserID(String writerUserID) {
		WriterUserID = writerUserID;
	}
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public String getReviewID() {
		return ReviewID;
	}
	public void setReviewID(String reviewID) {
		ReviewID = reviewID;
	}
	public String getPositive() {
		return Positive;
	}
	public void setPositive(String positive) {
		Positive = positive;
	}
	public String getContribute() {
		return Contribute;
	}
	public void setContribute(String contribute) {
		Contribute = contribute;
	}
	public String getRespect() {
		return Respect;
	}
	public void setRespect(String respect) {
		Respect = respect;
	}
	public String getFlexible() {
		return Flexible;
	}
	public void setFlexible(String flexible) {
		Flexible = flexible;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getClassProfessorName() {
		return ClassProfessorName;
	}
	public void setClassProfessorName(String classProfessorName) {
		ClassProfessorName = classProfessorName;
	}
	public String getReviewDate() {
		return ReviewDate;
	}
	public void setReviewDate(String reviewDate) {
		ReviewDate = reviewDate;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	
}
