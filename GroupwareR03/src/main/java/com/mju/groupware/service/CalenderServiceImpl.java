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
		int Count = calenderDao.InsertSchedule(calender);
		return Count;
	}

	@Override
	public List<HashMap<String, Object>> SelectSchedule(int userID) {
		return calenderDao.SelectSchedule(userID);
	}

	@Override
	public int SelectUserIdForCalender(String loginID) {
		Integer UserID = calenderDao.SelectUserIdForCalender(loginID);
		if (UserID == null) {
			return 0;
		} else {
			return UserID;
		}
	}

	@Override
	public int UpdateSchedule(String userId, String id, Calender calender) {
		int Count = calenderDao.UpdateSchedule(userId, id, calender);
		return Count;

	}

	@Override
	public int DeleteSchedule(String userId, String id) {
		int Count = calenderDao.DeleteSchedule(userId, id);
		return Count;
	}

	@Override
	public int UpdateTimeInMonth(HashMap<String, String> map) {
		return calenderDao.UpdateTimeInMonth(map);
	}

	@Override
	public int UpdateTimeInWeek(String userId, String id, Calender calender) {
		int Count = calenderDao.UpdateTimeInWeek(userId, id, calender);
		return Count;
	}
	
}
