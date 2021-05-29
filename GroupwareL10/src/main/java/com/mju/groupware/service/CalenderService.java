package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.Calender;

public interface CalenderService {

	int addSchedule(Calender calender);

	List<Calender> getSchedule(Calender calender);

	int SelectUserIdForCalender(String loginID);
}
