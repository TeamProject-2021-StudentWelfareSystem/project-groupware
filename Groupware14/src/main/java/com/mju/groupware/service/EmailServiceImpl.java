package com.mju.groupware.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.User;
import com.mju.groupware.email.EmailImpl;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailImpl emailImpl;

	private String email;
	private String name;
	private int num;
	private boolean checker;

	@Override
	public void sendEmail(User user) {
		Random random = new Random();
		this.num = random.nextInt(888888) + 111111; // 인증번호 생성
		this.email = user.getUserEmail();
		if (this.email.equals("@mju.ac.kr")) {
		} else {
			System.out.println(num);
			emailImpl.sendEmail(email,num);
		}

	}

	@Override
	public boolean authNum(String authNum) {// 입력한 인증번호 가져오기

		checker = emailImpl.authNum(Integer.parseInt(authNum), this.num);

		return checker;

	}
}