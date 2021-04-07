package com.mju.groupware.service;

import com.mju.groupware.dto.User;

public interface EmailService {
	// 이메일 보내기
	public void sendEmail(User user);

	// 인증번호확인
	public boolean authNum(String authNum);

	// 이메일중복확인
	public boolean EmailDuplicateCheck(User user);
}
