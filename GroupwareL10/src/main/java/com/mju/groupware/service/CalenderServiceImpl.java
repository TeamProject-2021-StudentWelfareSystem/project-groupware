package com.mju.groupware.service;

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
	public int addSchedule(Calender calender) {
		int count = calenderDao.addSchedule(calender);
		return count;
	}

	@Override
	public List<Calender> getSchedule(Calender calender) {
		return calenderDao.getSchedule(calender);
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
}
