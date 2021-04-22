package com.mju.groupware.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.StudentDao;
import com.mju.groupware.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public void InsertInformation(Student student) {
		studentDao.InsertInformation(student);
	}

	@Override
	public void UpdateUserID(Student student) {
		studentDao.UpdateUserID(student);
	}

	@Override
	public void updateStudentGender(Student student) {
		studentDao.UpdateStudentGender(student);
	}

	@Override
	public void updateStudentGrade(Student student) {
		studentDao.UpdateStudentGrade(student);
	}

	@Override
	public void UpdateStudentDobuleMajor(Student student) {
		studentDao.UpdateStudentDobuleMajor(student);
	}

	@Override
	public ArrayList<String> SelectStudentProfileInfo(String userID) {
		ArrayList<String> studentInfo = new ArrayList<String>();
		studentInfo = studentDao.SelectStudentProfileInfo(userID);
		return studentInfo;
	}

}
