package com.mju.groupware.email;

import java.util.List;

import com.mju.groupware.dto.UserEmail;

public interface Email {

	public void sendEmail(String email, int Num);

	public boolean AuthNum(int authnum, int num);

	public List<UserEmail> printEmailList();

	public boolean CheckEmailLogin(String id, String pw);

	public List<UserEmail> GetEmailList();
}
