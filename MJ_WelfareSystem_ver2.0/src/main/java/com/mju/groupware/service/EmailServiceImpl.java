package com.mju.groupware.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserDao;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserEmail;
import com.mju.groupware.email.Email;
import com.mju.groupware.email.EmailImpl;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private UserDao emailDao;
	@Autowired
	private EmailImpl emailImpl;
	@Autowired
	private Email email;

	private String Email;
	private int Num;

	@Override
	public int sendEmail(User user) {
		Random RandomNum = new Random();
		this.Num = RandomNum.nextInt(888888) + 111111; // 인증번호 생성
		this.Email = user.getUserEmail();
		System.out.println(Num);
		if (this.Email.equals("@mju.ac.kr")) {
		} else {
			emailImpl.sendEmail(Email, Num);
		}
		return Num;
	}

	@Override
	public boolean AuthNum(String authNum) {// 입력한 인증번호 가져오기
		boolean Checker;
		Checker = emailImpl.AuthNum(Integer.parseInt(authNum), this.Num);
		return Checker;

	}

	// 이메일 중복확인
	@Override
	public boolean SelectForEmailDuplicateCheck(User user) {
		// 이메일 중복
		boolean EmailChecker;
		EmailChecker = emailDao.SelectForEmailDuplicateCheck(user);
		return EmailChecker;
	}

	@Override
	public List<UserEmail> PrintEmailList() {
		return email.printEmailList();
	}

	@Override
	public boolean CheckEmailLogin(String id, String pw) {
		boolean CheckEmailLogin = email.CheckEmailLogin(id, pw);
		return CheckEmailLogin;
	}

	@Override
	public List<UserEmail> GetEmailList() {
		return email.GetEmailList();
	}
}