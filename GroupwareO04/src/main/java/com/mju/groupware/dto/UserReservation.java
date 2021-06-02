package com.mju.groupware.dto;

public class UserReservation {
	private String ReservationDate;
	private String ReservationStartTime;
	private String ReservationEndTime;
	private int ReservationNumOfPeople;
	private int LectureRoomNo;
	private int UserID;
	
	public String getReservationDate() {
		return ReservationDate;
	}
	public void setReservationDate(String reservationDate) {
		ReservationDate = reservationDate;
	}
	public String getReservationStartTime() {
		return ReservationStartTime;
	}
	public void setReservationStartTime(String reservationStartTime) {
		ReservationStartTime = reservationStartTime;
	}
	public String getReservationEndTime() {
		return ReservationEndTime;
	}
	public void setReservationEndTime(String reservationEndTime) {
		ReservationEndTime = reservationEndTime;
	}
	public int getReservationNumOfPeople() {
		return ReservationNumOfPeople;
	}
	public void setReservationNumOfPeople(int reservationNumOfPeople) {
		ReservationNumOfPeople = reservationNumOfPeople;
	}
	public int getLectureRoomNo() {
		return LectureRoomNo;
	}
	public void setLectureRoomNo(int lectureRoomNo) {
		LectureRoomNo = lectureRoomNo;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	
	
}
