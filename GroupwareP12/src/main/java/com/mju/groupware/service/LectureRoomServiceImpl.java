package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.LectureRoomDao;
import com.mju.groupware.dto.LectureRoom;
import com.mju.groupware.dto.UserReservation;

@Service
public class LectureRoomServiceImpl implements LectureRoomService {
	@Autowired
	private LectureRoomDao lectureRoomDao;

	@Override
	public List<LectureRoom> SelectLectureRoomList() {
		return lectureRoomDao.SelectLectureRoomList();
	}

	@Override
	public int SelectMaxNumOfPeople(String lectureRoomNo) {
		return lectureRoomDao.SelectMaxNumOfPeople(lectureRoomNo);
	}

	@Override
	public String SelectLoginUserID(String userLoginID) {
		return lectureRoomDao.SelectLoginUserID(userLoginID);
	}

	@Override
	public void InsertReservation(UserReservation userReservation) {
		lectureRoomDao.InsertReservation(userReservation);
	}

	@Override
	public List<UserReservation> SelectStartTime(String lectureRoomNo) {
		return lectureRoomDao.SelectStartTime(lectureRoomNo);
	}

	@Override
	public int SelectReservationUserID(int userID) {
		return lectureRoomDao.SelectReservationUserID(userID);
	}

	@Override
	public String SelectUserIDForReservationConfirm(String loginID) {
		return lectureRoomDao.SelectUserIDForReservationConfirm(loginID);
	}

	@Override
	public int SelectLectureRoomNo(String userID) {
		return lectureRoomDao.SelectLectureRoomNo(userID);
	}

	@Override
	public String SelectLectureRoomLocation(int lectureRoomNo) {
		return lectureRoomDao.SelectLectureRoomLocation(lectureRoomNo);
	}

	@Override
	public int SelectLectureRoomMaxNumOfPeople(int lectureRoomNo) {
		return lectureRoomDao.SelectLectureRoomMaxNumOfPeople(lectureRoomNo);
	}

	@Override
	public int SelectReservationNumOfPeople(String userID) {
		return lectureRoomDao.SelectReservationNumOfPeople(userID);
	}

	@Override
	public String SelectReservationStartTime(String userID) {
		return lectureRoomDao.SelectReservationStartTime(userID);
	}

	@Override
	public int SelectRoomFloor(int lectureRoomNo) {
		return lectureRoomDao.SelectRoomFloor(lectureRoomNo);
	}

	@Override
	public String SelectReservationStartTimeForException(String startTime) {
		return lectureRoomDao.SelectReservationStartTimeForException(startTime);
	}

	@Override
	public UserReservation SelectRoomInfo(String UserID, UserReservation userReservation) {
		userReservation = lectureRoomDao.SelectRoomInfo(UserID, userReservation);
		return userReservation;
	}

	@Override
	public boolean DeleteReservation(UserReservation userReservation) {
		boolean Check = lectureRoomDao.DeleteReservation(userReservation);
		return Check;
	}
}
