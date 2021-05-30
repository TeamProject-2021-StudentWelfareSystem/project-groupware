package com.mju.groupware.dto;

public class TeamUser {
	private int UserID;
	private int TeamID;
	private String UserLoginID;
	private String UserName;
	
	public String getUserLoginID() {
		return UserLoginID;
	}
	public void setUserLoginID(String userLoginID) {
		UserLoginID = userLoginID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getTeamID() {
		return TeamID;
	}
	public void setTeamID(int teamID) {
		TeamID = teamID;
	}
	
}
