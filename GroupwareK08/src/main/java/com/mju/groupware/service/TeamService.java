package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.Team;

public interface TeamService {

	public List<String> SelectClassName();

	public List<String> SelectProfessorName(String className);

}
