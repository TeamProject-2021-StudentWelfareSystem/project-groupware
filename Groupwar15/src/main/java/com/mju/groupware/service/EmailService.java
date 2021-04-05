package com.mju.groupware.service;

import com.mju.groupware.dto.User;

public interface EmailService {
	public void sendEmail(User user);
	
	public boolean authNum(String authNum);
}
