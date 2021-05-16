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


}
