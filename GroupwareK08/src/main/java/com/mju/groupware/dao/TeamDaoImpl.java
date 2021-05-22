package com.mju.groupware.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Team;

@Service
@Repository
public class TeamDaoImpl implements TeamDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<String> SelectClassName() {

		List<String> OriginalClassNameList = sqlSession.selectList("SelectClassName");

		return OriginalClassNameList;
	}

	@Override
	public List<String> SelectProfessorName(String className) {
		List<String> OriginalClassProfessorNameList = sqlSession.selectList("SelectProfessorName",className);
		return OriginalClassProfessorNameList;
	}

}
