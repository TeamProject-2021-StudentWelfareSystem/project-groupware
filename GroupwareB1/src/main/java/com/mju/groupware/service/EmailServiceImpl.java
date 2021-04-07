package com.mju.groupware.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserDao;
import com.mju.groupware.dto.User;
import com.mju.groupware.email.EmailImpl;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private UserDao emailDao;
	@Autowired
	private EmailImpl emailImpl;

	private String email;
	private int num;
	private boolean checker;
	private boolean emailChecker;

	@Override
	public void sendEmail(User user) {
		Random random = new Random();
		this.num = random.nextInt(888888) + 111111; // 인증번호 생성
		this.email = user.getUserEmail();
		if (this.email.equals("@mju.ac.kr")) {
		} else {
			emailImpl.sendEmail(email, num);
		}
	}

	@Override
	public boolean authNum(String authNum) {// 입력한 인증번호 가져오기

		checker = emailImpl.authNum(Integer.parseInt(authNum), this.num);

		return checker;

	}

	// 이메일 중복확인
	@Override
	public boolean EmailDuplicateCheck(User user) {
		// 이메일 중복
		emailChecker = emailDao.EmailDuplicateCheck(user);
		return emailChecker;
	}
}