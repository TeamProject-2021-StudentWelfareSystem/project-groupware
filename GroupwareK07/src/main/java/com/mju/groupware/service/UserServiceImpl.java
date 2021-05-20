package com.mju.groupware.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.ProfessorDao;
import com.mju.groupware.dao.StudentDao;
import com.mju.groupware.dao.UserDao;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserInfoOpen;
import com.mju.groupware.dto.WithdrawalUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ProfessorDao professorDao;

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
	public int SelectUserID(Professor professor) {
		return userDao.SelectUserID(professor);
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

	@Override
	public void UpdateDoWithdrawalRecoveryByAdmin(String id) {
		userDao.UpdateDoWithdrawalRecoveryByAdmin(id);
	}

	@Override
	public void UpdateDormantOneToZero(String id) {
		userDao.UpdateDormantOneToZero(id);
	}

	@Override
	public void UpdateUserRole(String id, String optionValue) {
		userDao.UpdateUserRole(id, optionValue);
	}

	@Override
	public void UpdateAdminRole(String id, String optionValue) {
		userDao.UpdateAdminRole(id, optionValue);
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfo(String userId) {
		ArrayList<String> Info = new ArrayList<String>();
		ArrayList<String> UserInfo = new ArrayList<String>();
		ArrayList<String> StudentInfo = new ArrayList<String>();
		ArrayList<String> ProfessorInfo = new ArrayList<String>();

		UserInfo = userDao.SelectMyPageUserInfo(userId);
		StudentInfo = studentDao.SelectMyPageUserInfo(UserInfo.get(0));
		ProfessorInfo = professorDao.SelectMyPageUserInfo(UserInfo.get(0));
		UserInfo.remove(0);

		for (int i = 0; i < UserInfo.size(); i++) {
			Info.add(UserInfo.get(i));
		}
		for (int i = 0; i < StudentInfo.size(); i++) {
			Info.add(StudentInfo.get(i));
		}
		for (int i = 0; i < ProfessorInfo.size(); i++) {
			Info.add(ProfessorInfo.get(i));
		}
		return Info;
	}

	@Override
	public ArrayList<String> SelectUserProfileInfoByID(String mysqlID) {
		ArrayList<String> Info = new ArrayList<String>();
		ArrayList<String> UserInfo = new ArrayList<String>();
		ArrayList<String> StudentInfo = new ArrayList<String>();
		ArrayList<String> ProfessorInfo = new ArrayList<String>();
		// 유저정보를 mysqlID를 던져줘서 받아온다.
		UserInfo = userDao.SelectMyPageUserInfoByID(mysqlID);
		// 학생정보를 mysqlID를 통해서 받아온다.
		StudentInfo = studentDao.SelectMyPageUserInfoByID(mysqlID);
		// 교수정보를 mysqlID를 통해서 받아온다.
		ProfessorInfo = professorDao.SelectMyPageUserInfoByID(mysqlID);		
		// Data의 크기만큼 Info List에 채워준다.
		for (int i = 0; i < UserInfo.size(); i++) {
			Info.add(UserInfo.get(i));
		}
		// Data의 크기만큼 Info List에 채워준다.
		for (int i = 0; i < StudentInfo.size(); i++) {
			Info.add(StudentInfo.get(i));
		}
		//Data의 크기만큼 Info List에 채워준다.
		for (int i = 0; i < ProfessorInfo.size(); i++) {
			Info.add(ProfessorInfo.get(i));
				}
		return Info;
	}

	@Override
	public void UpdateUserName(User user) {
		userDao.UpdateUserName(user);
	}

	@Override
	public void UpdateOpenName(User user) {
		userDao.UpdateOpenName(user);
	}

	@Override
	public void UpdateOpenEmail(User user) {
		userDao.UpdateOpenEmail(user);
	}

	@Override
	public void UpdateOpenPhoneNum(User user) {
		userDao.UpdatePhoneNum(user);
	}

	@Override
	public void UpdateOpenMajor(User user) {
		userDao.UpdateOpenMajor(user);
	}

	@Override
	public void UpdateOpenGrade(User user) {
		userDao.UpdateOpenGrade(user);
	}

	@Override
	public User SelectUserInfo(String userLoginID) {
		return userDao.SelectUserInfo(userLoginID);
	}

	@Override
	public void InsertWithdrawalUser(User userInfo) {
		userDao.InsertWithdrawalUser(userInfo);
	}

	@Override
	public void DeleteWithdrawalUser(User user) {
		userDao.DeleteWithdrawalUser(user);
	}

	@Override
	public void DeleteWithdrawalUserList(WithdrawalUser withdrawalUser) {
		userDao.DeleteWithdrawalUserList(withdrawalUser);

	}

	@Override
	public String SelectOpenInfo(String userID) {
		// XML화해야할지 팀원들과 얘기하기
		// 추후 Entity로 옮겨야함
		List<UserInfoOpen> SelectOpenInfo = userDao.SelectOpenInfo(userID);
		String result = "Error";

		result = SelectOpenInfo.get(0).getOpenEmail() + "," + SelectOpenInfo.get(0).getOpenGrade() + ","
				+ SelectOpenInfo.get(0).getOpenPhoneNum() + "," + SelectOpenInfo.get(0).getOpenMajor() + ","
				+ SelectOpenInfo.get(0).getOpenName();
	

		if (result.contains(",비공개") || result.contains("비공개")) {
			result = result.replaceAll(",비공개", "");
			result = result.replaceAll("비공개", "");
			boolean startComma = result.startsWith(",");
			boolean endComma = result.endsWith(",");
			if (startComma || endComma) {
				result = result.substring(result.length() - (result.length() -1), result.length());
			}
		}

		return result;

	}

	@Override
	public int SelectUserIDFromBoardController(String userLoginID) {
		return userDao.SelectUserIDFromBoardController(userLoginID);
	}

}