package com.mju.groupware.dao;

import java.util.ArrayList;

import com.mju.groupware.dto.Student;

public interface StudentDao {

	// 정보 저장
	public void InsertInformation(Student student);

	// 회원가입 후 userID(foreign key) 업데이트
	public void UpdateUserID(Student student);

	public String getGrade(String userId);

	public ArrayList<String> SelectMyPageUserInfo(String mysqlID);

	public void UpdateStudentGender(Student student);

	public void UpdateStudentDobuleMajor(Student student);

	public ArrayList<String> SelectStudentProfileInfo(String userID);

	public ArrayList<String> SelectMyPageUserInfoByID(String mysqlID);

	public void UpdateStudentGrade(Student student);

	public void UpdateStudentColleges(Student student);

	public void UpdateStudentMajor(Student student);

	public Student SelectStudentInfo(String userID);

	public void InsertWithdrawalStudent(Student student);

	public void DeleteWithdrawalStudent(Student student);

	public void DeleteWithdrawalStudentList(String string);

	public void UpdateStudentLoginDate(Student student);

	public Student SelectModifyStudentInfo(int userID);

}
