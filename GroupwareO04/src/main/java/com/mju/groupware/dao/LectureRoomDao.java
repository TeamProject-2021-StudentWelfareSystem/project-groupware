package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.LectureRoom;
import com.mju.groupware.dto.UserReservation;

public interface LectureRoomDao {

	List<LectureRoom> SelectLectureRoomList();

	int SelectMaxNumOfPeople(String lectureRoomNo);

	String SelectLoginUserID(String userLoginID);

	void InsertReservation(UserReservation userReservation);

	List<UserReservation> SelectStartTime(String lectureRoomNo);

	int SelectReservationUserID(int userID);

	String SelectUserIDForReservationConfirm(String loginID);

	int SelectLectureRoomNo(String userID);

	String SelectLectureRoomLocation(int lectureRoomNo);

	int SelectLectureRoomMaxNumOfPeople(int lectureRoomNo);

	int SelectReservationNumOfPeople(String userID);

	String SelectReservationStartTime(String userID);

	int SelectRoomFloor(int lectureRoomNo);

	String SelectReservationStartTimeForException(String startTime);

	boolean DeleteReservation(UserReservation userReservation);

	UserReservation SelectRoomInfo(String userID, UserReservation userReservation);


}
