package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.TeamDao;
import com.mju.groupware.dto.Team;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamDao teamDao;

	@Override
	public List<String> SelectClassName() {

		List<String> SelectClassName = teamDao.SelectClassName();
		return SelectClassName;
	}

	@Override
	public List<String> SelectProfessorName(String className) {
		List<String> SelectProfessorName = teamDao.SelectProfessorName(className);
		return SelectProfessorName;
	}

}
