package com.mju.groupware.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.LectureRoom;
import com.mju.groupware.dto.UserReservation;

@Service
@Repository
public class LectureRoomDaoImpl implements LectureRoomDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<LectureRoom> SelectLectureRoomList() {
		return sqlSession.selectList("SelectLectureRoomList");
	}

	@Override
	public int SelectMaxNumOfPeople(String lectureRoomNo) {
		return sqlSession.selectOne("SelectMaxNumOfPeople", lectureRoomNo);
	}

	@Override
	public String SelectLoginUserID(String userLoginID) {
		return sqlSession.selectOne("SelectLoginUserIDForLecture", userLoginID);
	}

	@Override
	public void InsertReservation(UserReservation userReservation) {
		sqlSession.insert("InsertReservation", userReservation);
	}

	@Override
	public List<UserReservation> SelectStartTime(String lectureRoomNo) {
		List<UserReservation> Output = sqlSession.selectList("SelectStartTime", lectureRoomNo);
		return Output;
	}

	@Override
	public int SelectReservationUserID(int userID) {
		Integer Output = sqlSession.selectOne("SelectReservationUserID", userID);
		if (Output != null) {
			return Output;
		} else {
			return 0;
		}
	}

	@Override
	public String SelectUserIDForReservationConfirm(String loginID) {
		return sqlSession.selectOne("SelectUserIDForReservationConfirm", loginID);
	}

	@Override
	public int SelectLectureRoomNo(String userID) {

		Integer SelectLectureRoomNo = sqlSession.selectOne("SelectLectureRoomNo", userID);
		if (SelectLectureRoomNo != null) {
			return SelectLectureRoomNo;
		} else {
			return 0;
		}
	}

	@Override
	public String SelectLectureRoomLocation(int lectureRoomNo) {
		return sqlSession.selectOne("SelectLectureRoomLocation", lectureRoomNo);
	}

	@Override
	public int SelectLectureRoomMaxNumOfPeople(int lectureRoomNo) {
		return sqlSession.selectOne("SelectLectureRoomMaxNumOfPeople", lectureRoomNo);
	}

	@Override
	public int SelectReservationNumOfPeople(String userID) {
		return sqlSession.selectOne("SelectReservationNumOfPeople", userID);
	}

	@Override
	public String SelectReservationStartTime(String userID) {
		return sqlSession.selectOne("SelectReservationStartTime", userID);
	}

	@Override
	public int SelectRoomFloor(int lectureRoomNo) {
		return sqlSession.selectOne("SelectRoomFloor", lectureRoomNo);
	}

	@Override
	public String SelectReservationStartTimeForException(String startTime) {
		String Output = sqlSession.selectOne("SelectReservationTimeForException", startTime);
		if (Output == null) {
			return "0";
		} else {
			return Output;
		}
	}

	@Override
	public UserReservation SelectRoomInfo(String userID, UserReservation userReservation) {
		userReservation = sqlSession.selectOne("SelectRoomInfo", userID);
		return userReservation;
	}

	@Override
	public boolean DeleteReservation(UserReservation userReservation) {
		// delete문의 경우 삭제된 row의 수를 return한다.
		int Row = sqlSession.delete("DeleteReservation", userReservation);
		if (Row == 0) {
			return false;
		} else {
			return true;
		}
	}
}
