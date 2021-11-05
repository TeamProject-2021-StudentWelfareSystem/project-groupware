package com.mju.groupware.service;

import java.util.ArrayList;

import com.mju.groupware.dto.Student;

public interface StudentService {

	// 정보 저장
	public void InsertInformation(Student student);

	// 회원가입 후 userID(foreign key) 업데이트
	public void UpdateUserID(Student student);

	// 성별 update
	public void updateStudentGender(Student student);

	// 학년 update
	public void updateStudentGrade(Student student);

	// 부전공
	public void UpdateStudentDobuleMajor(Student student);

	// 로그인 완료 화면에 띄울 데이터 select
	public ArrayList<String> SelectStudentProfileInfo(String userID);

	public void UpdateStudentGender(Student student);

	public void UpdateStudentColleges(Student student);

	public void UpdateStudentMajor(Student student);

	public Student SelectStudentInfo(String userID);

	public void InsertWithdrawalStudent(Student student);

	public void DeleteWithdrawalStudent(Student student);

	public void DeleteWithdrawalStudentList(String string);

	public void UpdateStudentLoginDate(Student student);
	
	public Student SelectModifyStudentInfo(int userID);

}
