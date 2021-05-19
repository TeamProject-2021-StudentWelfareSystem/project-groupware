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

	boolean DeleteReservation(UserReservation userReservation);

}
