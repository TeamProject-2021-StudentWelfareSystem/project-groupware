package com.mju.groupware.dao;

import com.mju.groupware.dto.User;
import com.mju.groupware.security.UsersDetails;

public interface UserDao {

	// 회원가입
	public void signUp(User user);
	
	// 로그인
	public UsersDetails selectByLoginID(String userLoginID);

	public boolean IdConfirm(User user);

}
