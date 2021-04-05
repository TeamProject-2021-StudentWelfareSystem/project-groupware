package com.mju.groupware.dao;

import com.mju.groupware.dto.User;
import com.mju.groupware.security.UsersDetails;

public interface UserDao {

	// 회원가입
	public void signUp(User user);
	
	// 로그인
	public UsersDetails selectByLoginID(String userLoginID);

	public boolean IdConfirm(User user);

	// 비번 찾기
	public boolean PwdConfirm(User user);

	// 비번 보여주기
	public boolean ShowPassword(User user);

}
