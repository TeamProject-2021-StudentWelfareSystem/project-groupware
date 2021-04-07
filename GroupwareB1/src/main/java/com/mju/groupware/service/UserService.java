package com.mju.groupware.service;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;

public interface UserService {

	// 회원가입
	public void SignUp(User user);

	// 중복확인
	public boolean IdConfirm(User user);

	// 비번 찾기
	public boolean PwdConfirm(User user);

	// userID(다른 테이블들의 foreign key) 찾기
	public int SelectUserID(Student student);

	public String ShowPassword(User user);

}