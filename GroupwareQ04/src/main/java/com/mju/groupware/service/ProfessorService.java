package com.mju.groupware.service;

import java.util.ArrayList;

import com.mju.groupware.dto.Professor;

public interface ProfessorService {

	// 정보 저장
	public void InsertInformation(Professor professor);

	// 회원가입 후 userID(foreign key) 업데이트
	public void UpdateUserID(Professor professor);

	//교수 학과 update
	public void UpdateProfessorColleges(Professor professor);
	
	//교수 전공 update
	public void UpdateProfessorMajor(Professor professor);
	
	//교수실 update
	public void UpdateProfessorRoom(Professor professor);

	//교수실 전화번호 update
	public void UpdateProfessorRoomNum(Professor professor);

	// 로그인 완료 화면에 띄울 데이터 select
	public ArrayList<String> SelectProfessorProfileInfo(String userID);

	public Professor SelectProfessorInfo(String userID);

	public void InsertWithdrawalProfessor(Professor professor);

	public void DeleteWithdrawalProfessor(Professor professor);

	public void DeleteWithdrawalProfessorList(String string);

	public void UpdateProfessorLoginDate(Professor professor);

	public Professor SelectModifyProfessorInfo(int userID);
	
}
