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

	@Override
	public void InsertForSignUp(User user) {
		userDao.InsertForSignUp(user);
	}

	@Override
	public boolean SelctForIDConfirm(User user) {
		boolean Checker = userDao.SelctForIDConfirm(user);
		return Checker;
	}

	@Override
	public boolean SelectPwdForConfirmToFindPwd(User user) {
		boolean PwdChecker = userDao.SelectPwdForConfirmToFindPwd(user);
		return PwdChecker;
	}

	@Override
	public String SelectForShowPassword(User user) {// 임시 비밀번호 생성
		boolean Checker = userDao.SelectForShowPassword(user);
		Random Random = new Random();
		String Result = "";
		if (Checker) {
			Result = Integer.toString(Random.nextInt(8) + 1);
			for (int i = 0; i < 7; i++) {
				Result += Integer.toString(Random.nextInt(9));
			}
		} else {
			Result = "false";
		}
		return Result;
	}

	@Override
	public int SelectUserID(Student student) {
		return userDao.SelectUserID(student);
	}

	@Override
	public void UpdateLoginDate(User user) {
		userDao.UpdateLoginDate(user);
	}

	@Override
	public String SelectCurrentPwd(String id) {
		return userDao.SelectCurrentPwd(id);
	}

	@Override
	public void UpdatePwd(User user) {
		userDao.UpdatePwd(user);
	}

	@Override
	public ArrayList<String> SelectUserProfileInfo(String id) {
		ArrayList<String> Info = new ArrayList<String>();
		Info = userDao.SelectUserProfileInfo(id);
		return Info;
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfo(String userId) {
		ArrayList<String> Info = new ArrayList<String>();
		ArrayList<String> UserInfo = new ArrayList<String>();
		ArrayList<String> StudentInfo = new ArrayList<String>();

		UserInfo = userDao.SelectMyPageUserInfo(userId);
		StudentInfo = studentDao.SelectMyPageUserInfo(UserInfo.get(0));
		UserInfo.remove(0);

		// 학년
		// 복수전공
		// 성별
		for (int i = 0; i < UserInfo.size(); i++) {
			Info.add(UserInfo.get(i));
		}
		for (int i = 0; i < StudentInfo.size(); i++) {
			Info.add(StudentInfo.get(i));
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
	public ArrayList<String> SelectUserInformation(String userId) {
		ArrayList<String> UserInfo = new ArrayList<String>();
		UserInfo = userDao.SelectUserInformation(userId);
		return UserInfo;
	}

	@Override
	public boolean SelectForPwdCheckBeforeModify(String id, String pw) {
		return userDao.SelectForPwdCheckBeforeModify(id, pw);
	}

	@Override
	public void UpdateTemporaryPwd(User user) {
		userDao.UpdateTemporaryPwd(user);

	}

	@Override
	public void UpdateWithdrawlUser(String id) {
		userDao.UpdateWithdrawlUser(id);
	}
}