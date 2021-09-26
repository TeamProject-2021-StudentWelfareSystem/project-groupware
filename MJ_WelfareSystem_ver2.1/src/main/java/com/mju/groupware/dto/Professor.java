package com.mju.groupware.dto;

public class Professor extends User {

	private int ProfessorID;
	private String ProfessorColleges; // 단과대학
	private String ProfessorMajor; // 전공
	private String ProfessorRoom; // 교수실
	private String ProfessorRoomNum; // 교수실 전화번호
	private int UserID; // foreign key
	private int WUserID;
	private String WithdrawalDate;

	public int getProfessorID() {
		return ProfessorID;
	}

	public void setProfessorID(int professorID) {
		ProfessorID = professorID;
	}
	
	public String getProfessorColleges() {
		return ProfessorColleges;
	}

	public void setProfessorColleges(String professorColleges) {
		ProfessorColleges = professorColleges;
	}

	public String getProfessorMajor() {
		return ProfessorMajor;
	}

	public void setProfessorMajor(String professorMajor) {
		ProfessorMajor = professorMajor;
	}

	public String getProfessorRoom() {
		return ProfessorRoom;
	}

	public void setProfessorRoom(String professorRoom) {
		ProfessorRoom = professorRoom;
	}

	public String getProfessorRoomNum() {
		return ProfessorRoomNum;
	}

	public void setProfessorRoomNum(String professorRoomNum) {
		ProfessorRoomNum = professorRoomNum;
	}
	
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getWUserID() {
		return WUserID;
	}

	public void setWUserID(int wUserID) {
		WUserID = wUserID;
	}

	public String getWithdrawalDate() {
		return WithdrawalDate;
	}

	public void setWithdrawalDate(String withdrawalDate) {
		WithdrawalDate = withdrawalDate;
	}

}
