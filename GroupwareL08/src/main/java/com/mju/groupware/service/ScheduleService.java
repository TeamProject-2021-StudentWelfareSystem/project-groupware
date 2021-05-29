package com.mju.groupware.service;

import java.util.List;

import javax.inject.Inject;

import com.mju.groupware.dao.ScheduleDao;
import com.mju.groupware.dto.Schedule;

public class ScheduleService {
	
	@Inject
	private ScheduleDao scheduleDao;
	
	public List<Schedule> showSchedule() throws Exception{
		return scheduleDao.showSchedule();
	}
	public void addSchedule(Schedule schedule) throws Exception{
		scheduleDao.addSchedule(schedule);
	}
}
