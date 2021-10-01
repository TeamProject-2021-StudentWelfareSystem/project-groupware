package com.mju.groupware.dto;

public class Team {
	private String TeamName;
	private String TeamLeaderName;
	private String TeamCreationDate;
	private String TeamLeaderID;
	private int ClassID;
	private int TeamID;

	public String getTeamLeaderID() {
		return TeamLeaderID;
	}
	public void setTeamLeaderID(String teamLeaderID) {
		TeamLeaderID = teamLeaderID;
	}
	public int getClassID() {
		return ClassID;
	}
	public int getTeamID() {
		return TeamID;
	}
	public void setTeamID(int teamID) {
		TeamID = teamID;
	}
	public void setClassID(int classID) {
		ClassID = classID;
	}
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public String getTeamLeaderName() {
		return TeamLeaderName;
	}
	public void setTeamLeaderName(String teamLeaderName) {
		TeamLeaderName = teamLeaderName;
	}
	public String getTeamCreationDate() {
		return TeamCreationDate;
	}
	public void setTeamCreationDate(String teamCreationDate) {
		TeamCreationDate = teamCreationDate;
	}

}
