package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Calender;

public interface CalenderDao {

	int addSchedule(Calender calender);

	List<Calender> getSchedule(Calender calender);

	Integer SelectUserIdForCalender(String loginID); // get userId

}