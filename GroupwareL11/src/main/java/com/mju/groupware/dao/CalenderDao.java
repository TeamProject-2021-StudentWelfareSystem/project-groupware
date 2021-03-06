package com.mju.groupware.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mju.groupware.dto.Calender;

public interface CalenderDao {

	int InsertSchedule(Calender calender);

	List<HashMap<String, Object>> SelectSchedule(int userID);

	Integer SelectUserIdForCalender(String loginID); // get userId

	int UpdateSchedule(String userId, String id, Calender calender);

	int DeleteSchedule(String userId, String id);

}