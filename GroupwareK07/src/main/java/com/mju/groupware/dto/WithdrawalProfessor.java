package com.mju.groupware.dto;

public class WithdrawalProfessor extends WithdrawalUser {

	private int WProfessorID;
	private String WProfessorColleges; // 단과대학
	private String WProfessorMajor; // 전공
	private String WProfessorRoom; // 교수실
	private String WProfessorRoomNum; // 교수실 전화번호
	private int WUserID; // foreign key
	public int getWProfessorID() {
		return WProfessorID;
	}
	public void setWProfessorID(int wProfessorID) {
		WProfessorID = wProfessorID;
	}
	public String getWProfessorColleges() {
		return WProfessorColleges;
	}
	public void setWProfessorColleges(String wProfessorColleges) {
		WProfessorColleges = wProfessorColleges;
	}
	public String getWProfessorMajor() {
		return WProfessorMajor;
	}
	public void setWProfessorMajor(String wProfessorMajor) {
		WProfessorMajor = wProfessorMajor;
	}
	
	public String getWProfessorRoom() {
		return WProfessorRoom;
	}
	public void setWProfessorRoom(String wProfessorRoom) {
		WProfessorRoom = wProfessorRoom;
	}
	public String getWProfessorRoomNum() {
		return WProfessorRoomNum;
	}
	public void setWProfessorRoomNum(String wProfessorRoomNum) {
		WProfessorRoomNum = wProfessorRoomNum;
	}
	public int getWUserID() {
		return WUserID;
	}
	public void setWUserID(int wUserID) {
		WUserID = wUserID;
	}

}