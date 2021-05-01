package com.mju.groupware.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserDao;
import com.mju.groupware.dto.User;
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
	private boolean Checker;
	private boolean EmailChecker;

	@Override
	public int sendEmail(User user) {
		Random random = new Random();
		this.Num = random.nextInt(888888) + 111111; // 인증번호 생성
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
		Checker = emailImpl.AuthNum(Integer.parseInt(authNum), this.Num);
		return Checker;

	}

	// 이메일 중복확인
	@Override
	public boolean SelectForEmailDuplicateCheck(User user) {
		// 이메일 중복
		EmailChecker = emailDao.SelectForEmailDuplicateCheck(user);
		return EmailChecker;
	}

	@Override
	public List<String> printEmailList(String id, String pw) {
		return email.printEmailList(id, pw);
	}
}