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
		ArrayList<String> StudentInfo = new ArrayList<String>();
		StudentInfo = studentDao.SelectStudentProfileInfo(userID);
		return StudentInfo;
	}

	@Override
	public void UpdateStudentGender(Student student) {
		studentDao.UpdateStudentGender(student);
	}

	@Override
	public void UpdateStudentColleges(Student student) {
		studentDao.UpdateStudentColleges(student);
	}

	@Override
	public void UpdateStudentMajor(Student student) {
		studentDao.UpdateStudentMajor(student);
	}

	@Override
	public Student SelectStudentInfo(String userID) {
		return studentDao.SelectStudentInfo(userID);
	}

	@Override
	public void InsertWithdrawalStudent(Student student) {
		studentDao.InsertWithdrawalStudent(student);
	}

	@Override
	public void DeleteWithdrawalStudent(Student student) {
		studentDao.DeleteWithdrawalStudent(student);
	}

	@Override
	public void DeleteWithdrawalStudentList(String string) {
		studentDao.DeleteWithdrawalStudentList(string);
	}

	@Override
	public void UpdateStudentLoginDate(Student student) {
		studentDao.UpdateStudentLoginDate(student);
	}
	
	@Override
	public Student SelectModifyStudentInfo(int userID) {
		return studentDao.SelectModifyStudentInfo(userID);
	}
}
