package com.mju.groupware.email;

import java.util.List;

import com.mju.groupware.dto.UserEmail;

public interface Email {

	public void sendEmail(String email,int Num);

	public boolean AuthNum(int authnum, int num);

	public List<String> printEmailList(String id, String pw);

	public List<String> getContent();

	public List<String> getFrom();

	public List<String> getsubject();

	public List<String> getDate();
}
