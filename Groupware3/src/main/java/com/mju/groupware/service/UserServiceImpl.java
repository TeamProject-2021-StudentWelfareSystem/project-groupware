package com.mju.groupware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserDao;
import com.mju.groupware.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void signUp(User user) {
		userDao.signUp(user);
	}

}
