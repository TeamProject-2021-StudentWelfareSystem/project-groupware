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
	public String ShowPassword(User user) {// 임시 비밀번호 생성
		boolean checker = userDao.ShowPassword(user);
		Random random = new Random();
		String result = "";
		if (checker) {
			result = Integer.toString(random.nextInt(8) + 1);
			for(int i=0; i < 7; i++){
				result += Integer.toString(random.nextInt(9));
			}
		} else {
			result = "false";
		}
		return result;
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
	public String currentPW(String id) {
		return userDao.currentPW(id);
	}

	@Override
	public void modifyPW(User user) {
		userDao.modifyPW(user);
	}

	@Override
	public ArrayList<String> SelectProfileUserInformationList(String ID) {
		ArrayList<String> ProfileUserInformationList = new ArrayList<String>();
		ProfileUserInformationList = userDao.SelectProfileUserInformationList(ID);
		return ProfileUserInformationList;
	}

	@Override
	public ArrayList<String> SelectMyPageUserInformationList(String UserId) {
		ArrayList<String> Information = new ArrayList<String>();
		ArrayList<String> UserInformationList = new ArrayList<String>();
		ArrayList<String> StudentInformationList = new ArrayList<String>();

		UserInformationList = userDao.SelectMyPageUserInformationList(UserId);
		StudentInformationList = studentDao.SelectMyPageStudentInformationList(UserInformationList.get(0));
		UserInformationList.remove(0);

		// 학년
		// 복수전공
		// 성별
		for (int i = 0; i < UserInformationList.size(); i++) {
			Information.add(UserInformationList.get(i));
		}
		for (int i = 0; i < StudentInformationList.size(); i++) {
			Information.add(StudentInformationList.get(i));
		}
		return Information;
	}

	@Override
	public void UpdateUserPhoneNumber(User user) {
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
	public ArrayList<String> SelectUserID(String userId) {
		ArrayList<String> userIDList = new ArrayList<String>();
		userIDList = userDao.SelectUserID(userId);
		return userIDList;
	}

	@Override
	public boolean pwCheckBeforeModify(String id, String pw) {
		return userDao.pwCheckBeforeModify(id, pw);
	}

	@Override
	public void TemporaryPW(User user) {
		userDao.TemporaryPW(user);
		
	}

	@Override
	public void withdrawl(String id) {
		userDao.withdrawl(id);
	}
}