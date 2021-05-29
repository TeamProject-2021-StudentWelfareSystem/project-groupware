package com.mju.groupware.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.mju.groupware.dto.Schedule;

public class ScheduleDao {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.mju.groupware.dao.ScheduleDao";
	
	public List<Schedule> showSchedule() throws Exception{
		return sqlSession.selectList(namespace + ".showSchedule");
	}
	public void addSchedule(Schedule schedule) throws Exception{
		sqlSession.insert(namespace + ".addSchedule", schedule);
	}
}
