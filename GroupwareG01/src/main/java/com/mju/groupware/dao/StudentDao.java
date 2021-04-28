package com.mju.groupware.dao;

import java.util.ArrayList;

import com.mju.groupware.dto.Student;

public interface StudentDao {

	// 정보 저장
	public void InsertInformation(Student student);

	// 회원가입 후 userID(foreign key) 업데이트
	public void UpdateUserID(Student student);

	public String getGrade(String UserId);

	public ArrayList<String> SelectMyPageUserInfo(String MysqlID);

	public void UpdateStudentGender(Student student);

	public void UpdateStudentGrade(Student student);

	public void UpdateStudentDobuleMajor(Student student);

	public ArrayList<String> SelectStudentProfileInfo(String userID);

	public ArrayList<String> SelectMyPageUserInfoByID(String mysqlID);

	public void updateUserGrade(Student student);

}
