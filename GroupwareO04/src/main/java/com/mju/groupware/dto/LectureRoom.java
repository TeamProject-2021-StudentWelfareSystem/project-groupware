package com.mju.groupware.dto;

public class LectureRoom {
	private int LectureRoomNo;
	private String RoomLocation;
	private int RoomFloor;
	private int MaxNumOfPeople;
	private String RoomType;
	
	public int getLectureRoomNo() {
		return LectureRoomNo;
	}
	public void setLectureRoomNo(int lectureRoomNo) {
		LectureRoomNo = lectureRoomNo;
	}
	public String getRoomLocation() {
		return RoomLocation;
	}
	public void setRoomLocation(String roomLocation) {
		RoomLocation = roomLocation;
	}
	public int getRoomFloor() {
		return RoomFloor;
	}
	public void setRoomFloor(int roomFloor) {
		RoomFloor = roomFloor;
	}
	public int getMaxNumOfPeople() {
		return MaxNumOfPeople;
	}
	public void setMaxNumOfPeople(int maxNumOfPeople) {
		MaxNumOfPeople = maxNumOfPeople;
	}
	public String getRoomType() {
		return RoomType;
	}
	public void setRoomType(String roomType) {
		RoomType = roomType;
	}
	
}
