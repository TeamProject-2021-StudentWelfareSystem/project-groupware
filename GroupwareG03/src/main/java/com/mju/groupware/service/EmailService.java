package com.mju.groupware.service;

import com.mju.groupware.dto.User;

public interface EmailService {
	// 이메일 보내기
	public int sendEmail(User user);

	// 인증번호확인
	public boolean AuthNum(String authNum);

	// 이메일중복확인
	public boolean SelectForEmailDuplicateCheck(User user);

}
