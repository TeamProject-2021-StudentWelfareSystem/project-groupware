package com.mju.groupware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.StudentDao;
import com.mju.groupware.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void SaveInformation(Student student) {
		studentDao.SaveInformation(student);
	}

	@Override
	public void UpdateUserID(Student student) {
		studentDao.UpdateUserID(student);
	}

}
