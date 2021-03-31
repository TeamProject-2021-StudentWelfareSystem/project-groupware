package com.mju.groupware.service;

import com.mju.groupware.dto.User;

public interface UserService {

	// 회원가입
	public void signUp(User user);
	//중복확인
	public boolean IdConfirm(User user);
	
}
