package com.mju.groupware.dto;

public class TeamBoard {

	// 게시판정보
	private int TBoardID;
	private String TBoardSubject;
	private String TBoardContent;
	private String TBoardWriter;
	private String TBoardDate;
	private String TUserLoginID;

	// 파일정보
	private int TBno;
	private String TFileID;
	private String TOriginalFileName;
	private String TStoredFileName;
	private String TFileSize;
	private String TeamID;
	
	private int TUserID;
	
	public int getTUserID() {
		return TUserID;
	}

	public void setTUserID(int tUserID) {
		TUserID = tUserID;
	}

	public String getTUserLoginID() {
		return TUserLoginID;
	}

	public void setTUserLoginID(String tUserLoginID) {
		TUserLoginID = tUserLoginID;
	}

	public int getTBno() {
		return TBno;
	}

	public void setTBno(int tBno) {
		TBno = tBno;
	}

	public int getTBoardID() {
		return TBoardID;
	}

	public void setTBoardID(int tBoardID) {
		TBoardID = tBoardID;
	}

	public String getTBoardSubject() {
		return TBoardSubject;
	}

	public void setTBoardSubject(String tBoardSubject) {
		TBoardSubject = tBoardSubject;
	}

	public String getTBoardContent() {
		return TBoardContent;
	}

	public void setTBoardContent(String tBoardContent) {
		TBoardContent = tBoardContent;
	}

	public String getTBoardWriter() {
		return TBoardWriter;
	}

	public void setTBoardWriter(String tBoardWriter) {
		TBoardWriter = tBoardWriter;
	}

	public String getTBoardDate() {
		return TBoardDate;
	}

	public void setTBoardDate(String tBoardDate) {
		TBoardDate = tBoardDate;
	}

	public String getTFileID() {
		return TFileID;
	}

	public void setTFileID(String tFileID) {
		TFileID = tFileID;
	}

	public String getTOriginalFileName() {
		return TOriginalFileName;
	}

	public void setTOriginalFileName(String tOriginalFileName) {
		TOriginalFileName = tOriginalFileName;
	}

	public String getTStoredFileName() {
		return TStoredFileName;
	}

	public void setTStoredFileName(String tStoredFileName) {
		TStoredFileName = tStoredFileName;
	}

	public String getTFileSize() {
		return TFileSize;
	}

	public void setTFileSize(String tFileSize) {
		TFileSize = tFileSize;
	}

	public String getTeamID() {
		return TeamID;
	}

	public void setTeamID(String teamID) {
		TeamID = teamID;
	}

}
