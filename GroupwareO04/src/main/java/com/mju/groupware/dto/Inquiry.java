package com.mju.groupware.dto;

public class Inquiry {
	private int Ino;
	private String IBoardSubject;
	private String IBoardWriter;
	private String IBoardContent;
	private String IBoardDate;
	private String IBoardType;
	private String UserEmail;
	private String UserPhoneNum;
	private String State;
	private String IBoardAnswer;
	
	public String getIBoardAnswer() {
		return IBoardAnswer;
	}

	public void setIBoardAnswer(String iBoardAnswer) {
		IBoardAnswer = iBoardAnswer;
	}

	private int UserID;
	private int IBoardID;
	
	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getIBoardType() {
		return IBoardType;
	}

	public void setIBoardType(String iboardType) {
		IBoardType = iboardType;
	}

	public int getIBoardID() {
		return IBoardID;
	}

	public void setIBoardID(int iboardID) {
		IBoardID = iboardID;
	}

	public int getIno() {
		return Ino;
	}

	public void setIno(int ino) {
		Ino = ino;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getIBoardSubject() {
		return IBoardSubject;
	}

	public void setIBoardSubject(String iboardSubject) {
		IBoardSubject = iboardSubject;
	}

	public String getIBoardWriter() {
		return IBoardWriter;
	}

	public void setIBoardWriter(String iboardWriter) {
		IBoardWriter = iboardWriter;
	}
	
	public String getIBoardContent() {
		return IBoardContent;
	}

	public void setIBoardContent(String iboardContent) {
		IBoardContent = iboardContent;
	}

	public String getIBoardDate() {
		return IBoardDate;
	}

	public void setIBoardDate(String iboardDate) {
		IBoardDate = iboardDate;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getUserPhoneNum() {
		return UserPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		UserPhoneNum = userPhoneNum;
	}



}
