package com.mju.groupware.service;

import java.util.HashMap;
import java.util.List;

import com.mju.groupware.dto.Calender;

public interface CalenderService {

	int InsertSchedule(Calender calender);

	List<HashMap<String, Object>> SelectSchedule(int userId);

	int SelectUserIdForCalender(String loginID);

	List<HashMap<String, Object>> UpdateSchedule(int userId);
}
