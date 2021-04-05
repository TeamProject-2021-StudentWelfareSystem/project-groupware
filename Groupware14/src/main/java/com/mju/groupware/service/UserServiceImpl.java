package com.mju.groupware.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserDao;
import com.mju.groupware.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	private int num;
	private String showPwd;

	@Override
	public void signUp(User user) {
		userDao.signUp(user);
	}

	@Override
	public boolean IdConfirm(User user) {
		boolean checker = userDao.IdConfirm(user);
		return checker;
	}

	@Override
	public boolean pwdConfirm(User user) {
		boolean pwdChecker = userDao.PwdConfirm(user);
		return pwdChecker;
	}

	@Override
	public String ShowPassword(User user) {

		boolean checker = userDao.ShowPassword(user);
		if (checker) {
			Random random = new Random();
			this.num = random.nextInt(888888) + 111111;
			this.showPwd = Integer.toString(this.num);
		} else {
             this.showPwd = "false";
		}
		return showPwd;
	}
}
