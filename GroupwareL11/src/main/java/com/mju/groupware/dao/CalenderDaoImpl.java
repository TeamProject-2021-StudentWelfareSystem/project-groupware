package com.mju.groupware.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Calender;

@Service
@Repository
public class CalenderDaoImpl implements CalenderDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int InsertSchedule(Calender calender) {
		int count = sqlSession.insert("InsertSchedule", calender);
		return count;
	}
	
	@Override
	public List<HashMap<String, Object>> SelectSchedule(int userID) {
		return sqlSession.selectList("SelectSchedule", userID);
	}

	@Override
	public Integer SelectUserIdForCalender(String loginID) {
		Integer userID = sqlSession.selectOne("SelectUserID", loginID);
		if (userID == null) {
			return 0;
		} else {
			return userID;
		}
	}

}
