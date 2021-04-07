package com.mju.groupware.dao;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.security.UsersDetails;

public interface UserDao {

	// 회원가입
	public void SignUp(User user);
	
	// 로그인
	public UsersDetails selectByLoginID(String userLoginID);

	// 중복 확인
	public boolean IdConfirm(User user);

	// 비번 찾기
	public boolean PwdConfirm(User user);

	// userID(다른 테이블들의 foreign key) 찾기
	public int SelectUserID(Student student);
	
	// 비번 보여주기
	public boolean ShowPassword(User user);

}