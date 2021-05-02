package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserEmail;
import com.mju.groupware.email.EmailImpl;

public interface EmailService {
	// 이메일 보내기
	public int sendEmail(User user);

	// 인증번호확인
	public boolean AuthNum(String authNum);

	// 이메일중복확인
	public boolean SelectForEmailDuplicateCheck(User user);

	public List<String> printEmailList(String id, String pw);

	public List<String> getContent();

	public List<String> getFrom();

	public List<String> getsubject();

	public List<String> getDate();

}
