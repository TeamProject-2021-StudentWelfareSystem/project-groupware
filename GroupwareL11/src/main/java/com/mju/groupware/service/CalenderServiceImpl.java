package com.mju.groupware.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.CalenderDao;
import com.mju.groupware.dto.Calender;

@Service
public class CalenderServiceImpl implements CalenderService {

	@Autowired
	private CalenderDao calenderDao;

	@Override
	public int InsertSchedule(Calender calender) {
		int count = calenderDao.InsertSchedule(calender);
		return count;
	}

	@Override
	public List<HashMap<String, Object>> SelectSchedule(int userID) {
		return calenderDao.SelectSchedule(userID);
	}

	@Override
	public int SelectUserIdForCalender(String loginID) {
		Integer userID = calenderDao.SelectUserIdForCalender(loginID);
		if (userID == null) {
			return 0;
		} else {
			return userID;
		}
	}

	@Override
	public int UpdateSchedule(String userId, String id, Calender calender) {
		int count = calenderDao.UpdateSchedule(userId, id, calender);

		return count;

	}

	@Override
	public int DeleteSchedule(String userId, String id) {
		int count = calenderDao.DeleteSchedule(userId, id);
		return count;
	}
}
