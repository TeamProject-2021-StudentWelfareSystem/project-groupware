package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.LectureRoom;
import com.mju.groupware.dto.UserReservation;

public interface LectureRoomService {

	List<LectureRoom> SelectLectureRoomList();

	int SelectMaxNumOfPeople(String lectureRoomNo);

	String SelectLoginUserID(String userLoginID);

	void InsertReservation(UserReservation userReservation);

	List<UserReservation> SelectStartTime(String lectureRoomNo);


}
