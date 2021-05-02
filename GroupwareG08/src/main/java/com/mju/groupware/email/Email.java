package com.mju.groupware.email;

public interface Email {

	public void sendEmail(String email,int Num);

	public boolean AuthNum(int authnum, int num);
}
