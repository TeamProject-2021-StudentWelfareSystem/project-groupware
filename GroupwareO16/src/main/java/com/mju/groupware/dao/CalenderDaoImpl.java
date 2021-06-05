package com.mju.groupware.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.constant.ConstantCalenderDao;
import com.mju.groupware.dto.Calender;

@Service
@Repository
public class CalenderDaoImpl implements CalenderDao {

	private ConstantCalenderDao Constant;

	public CalenderDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/CalenderDao.xml");
		CTX.refresh();
		this.Constant = (ConstantCalenderDao) CTX.getBean("CalenderID");
	}

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int InsertSchedule(Calender calender) {
		int count = sqlSession.insert(this.Constant.getInsertSchedule(), calender);
		return count;
	}

	@Override
	public List<HashMap<String, Object>> SelectSchedule(int userID) {
		return sqlSession.selectList(this.Constant.getSelectSchedule(), userID);
	}

	@Override
	public Integer SelectUserIdForCalender(String loginID) {
		Integer userID = sqlSession.selectOne(this.Constant.getSelectUserID(), loginID);
		if (userID == null) {
			return 0;
		} else {
			return userID;
		}
	}

	@Override
	public int UpdateSchedule(String userId, String id, Calender calender) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(this.Constant.getUserID(), userId);
		map.put(this.Constant.getScheduleID(), id);
		map.put(this.Constant.getTitle(), calender.getTitle());
		map.put(this.Constant.getStart(), calender.getStart());
		map.put(this.Constant.getEnd(), calender.getEnd());
		map.put(this.Constant.getBackgroundColor(), calender.getBackgroundColor());
		map.put(this.Constant.getDescription(), calender.getDescription());

		return sqlSession.update(this.Constant.getUpdateSchedule(), map);
	}

	@Override
	public int DeleteSchedule(String userId, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("scheduleID", id);

		return sqlSession.delete(this.Constant.getDeleteSchedule(), map);

	}

	@Override
	public int UpdateTimeInMonth(HashMap<String, String> map) {

		return sqlSession.update(this.Constant.getUpdateTimeInMonth(), map);
	}

	@Override
	public int UpdateTimeInWeek(String userId, String id, Calender calender) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(this.Constant.getUserID(), userId);
		map.put(this.Constant.getStart(), calender.getStart());
		map.put(this.Constant.getEnd(), calender.getEnd());
		map.put(this.Constant.getScheduleID(), id);
		System.out.println(map);
		return sqlSession.update(this.Constant.getUpdateTimeInWeek(), map);
	}

}
