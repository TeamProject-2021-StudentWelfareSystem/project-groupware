package com.mju.groupware.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Student;

@Service
@Repository
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void SaveInformation(Student student) {
		sqlSession.insert("StudentInsert", student);
	}

	@Override
	public void UpdateUserID(Student student) {
		sqlSession.update("UpdateUserID", student);
	}

}
