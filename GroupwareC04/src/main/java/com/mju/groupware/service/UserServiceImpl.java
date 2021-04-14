package com.mju.groupware.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.StudentDao;
import com.mju.groupware.dao.UserDao;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private StudentDao studentDao;

	private int num;
	private String showPwd;

	@Override
	public void SignUp(User user) {
		userDao.SignUp(user);
	}

	@Override
	public boolean IdConfirm(User user) {
		boolean checker = userDao.IdConfirm(user);
		return checker;
	}

	@Override
	public boolean PwdConfirm(User user) {
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

	@Override
	public int SelectUserID(Student student) {
		return userDao.SelectUserID(student);
	}

	@Override
	public void DateUpdate(User user) {
		userDao.DateUpdate(user);
	}

	@Override
	public ArrayList<String> getUserInfo(String id) {
		ArrayList<String> info = new ArrayList<String>();
		info = userDao.getUserInfo(id);
		String grade = studentDao.getGrade(info.get(3));
		info.remove(3);
		info.add(grade);
		return info;
	}

	@Override
	public String currentPW(String id) {
		return userDao.currentPW(id);
	}

	@Override
	public void modifyPW(User user) {
		userDao.modifyPW(user);
	}

	@Override
	public boolean pwCheckBeforeModify(String pw) {
		return userDao.pwCheckBeforeModify(pw);
	}

	@Override
	public ArrayList<String> GetProfileUserInfo(String id) {
		ArrayList<String> info = new ArrayList<String>();
		info = userDao.GetProfileUserInfo(id);
		String grade = studentDao.getGrade(info.get(3));
		info.remove(3);
		info.add(grade);
		return info;
	}

	@Override
	public ArrayList<String> GetMyPageUserInfo(String userId) {
		ArrayList<String> Info = new ArrayList<String>();
		ArrayList<String> userInfo = new ArrayList<String>();
		ArrayList<String> studentInfo = new ArrayList<String>();

		userInfo = userDao.GetMyPageUserInfo(userId);
		studentInfo = studentDao.GetMyPageUserInfo(userInfo.get(0));
		userInfo.remove(0);

		// 학년
		// 복수전공
		// 성별
		for (int i = 0; i < userInfo.size(); i++) {
			Info.add(userInfo.get(i));
		}
		for (int i = 0; i < studentInfo.size(); i++) {
			Info.add(studentInfo.get(i));
		}
		return Info;
	}

	@Override
	public void updateUserPhoneNumber(User user) {
		userDao.updateUserPhoneNumber(user);
	}

	@Override
	public void UpdateUserMajor(User user) {
		userDao.updateUserMajor(user);
	}

	@Override
	public void UpdateUserColleges(User user) {
		userDao.updateUserColleges(user);
	}

	@Override
	public ArrayList<String> GetUser(String userId) {
		ArrayList<String> userInfo = new ArrayList<String>();
		userInfo = userDao.GetUser(userId);
		return userInfo;
	}

	
}