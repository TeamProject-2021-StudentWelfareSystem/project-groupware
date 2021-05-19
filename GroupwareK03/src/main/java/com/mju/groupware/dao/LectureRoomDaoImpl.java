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
	public boolean DeleteReservation(UserReservation userReservation) {
		// delete문의 경우 삭제된 row의 수를 return한다.
		int row = sqlSession.delete("DeleteReservation", userReservation);
		if (row == 0) {
			return false;
		} else {
			return true;
		}
	}

}
