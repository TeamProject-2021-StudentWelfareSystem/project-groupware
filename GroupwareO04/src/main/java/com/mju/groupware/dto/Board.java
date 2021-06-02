package com.mju.groupware.dto;

public class Board {
	private String BFileID;
	private int Bno;
	private String BoardSubject;
	private String BoardWriter;
	private String BoardContent;
	private String BoardDate;
	private String BoardType;
	
	public String getBoardType() {
		return BoardType;
	}

	public void setBoardType(String boardType) {
		BoardType = boardType;
	}

	private int UserID;
	private int BoardID;
	private int BoardHit;
	private String OriginalFileName;
	private String StoredFileName;
	private int FileSize;

	public String getBFileID() {
		return BFileID;
	}

	public void setBFileID(String bFileID) {
		BFileID = bFileID;
	}

	public String getBoardContent() {
		return BoardContent;
	}

	public void setBoardContent(String boardContent) {
		BoardContent = boardContent;
	}

	public int getBoardHit() {
		return BoardHit;
	}

	public void setBoardHit(int boardHit) {
		BoardHit = boardHit;
	}

	public String getOriginalFileName() {
		return OriginalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		OriginalFileName = originalFileName;
	}

	public String getStoredFileName() {
		return StoredFileName;
	}

	public void setStoredFileName(String storedFileName) {
		StoredFileName = storedFileName;
	}

	public int getFileSize() {
		return FileSize;
	}

	public void setFileSize(int fileSize) {
		FileSize = fileSize;
	}

	public int getBoardID() {
		return BoardID;
	}

	public void setBoardID(int boardID) {
		BoardID = boardID;
	}

	public int getBno() {
		return Bno;
	}

	public void setBno(int bno) {
		Bno = bno;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getBoardSubject() {
		return BoardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		BoardSubject = boardSubject;
	}

	public String getBoardWriter() {
		return BoardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		BoardWriter = boardWriter;
	}

	public String getBoardDate() {
		return BoardDate;
	}

	public void setBoardDate(String boardDate) {
		BoardDate = boardDate;
	}

}
