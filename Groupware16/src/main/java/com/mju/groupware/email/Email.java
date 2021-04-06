package com.mju.groupware.email;

public interface Email {

	public void sendEmail(String email, int Num);

	public boolean authNum(int authnum,int num);
}
