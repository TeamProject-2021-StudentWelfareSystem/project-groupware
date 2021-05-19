package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.LectureRoom;
import com.mju.groupware.dto.UserReservation;

public interface LectureRoomService {

	public List<LectureRoom> SelectLectureRoomList();

	public int SelectMaxNumOfPeople(String lectureRoomNo);

	public String SelectLoginUserID(String userLoginID);

	public void InsertReservation(UserReservation userReservation);

	public List<UserReservation> SelectStartTime(String lectureRoomNo);

	public boolean DeleteReservation(UserReservation userReservation);

}
