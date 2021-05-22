package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Team;

public interface TeamDao {

	public List<String> SelectClassName();

	public List<String> SelectProfessorName(String className);

}
