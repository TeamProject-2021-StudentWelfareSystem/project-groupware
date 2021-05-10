package com.mju.groupware.service;

import java.util.ArrayList;

import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.WithdrawalUser;

public interface UserService {

	// 회원가입
	public void InsertForSignUp(User user);

	// 중복확인
	public boolean SelctForIDConfirm(User user);

	// 비번 찾기
	public boolean SelectPwdForConfirmToFindPwd(User user);

	// userID(다른 테이블들의 foreign key) 찾기
	public int SelectUserID(Student student);

	public int SelectUserID(Professor professor);
	
	public String SelectForShowPassword(User user);

	public void UpdateLoginDate(User user);

	public String SelectCurrentPwd(String id);

	public void UpdatePwd(User user);

	public boolean SelectForPwdCheckBeforeModify(String pw, String pw2);

	public ArrayList<String> SelectMyPageUserInfo(String userId);

	public ArrayList<String> SelectUserProfileInfo(String loginID);

	public void updateUserPhoneNumber(User user);

	public void UpdateUserMajor(User user);

	public void UpdateUserColleges(User user);

	public ArrayList<String> SelectUserInformation(String userId);

	public void UpdateTemporaryPwd(User user);

	public void UpdateWithdrawlUser(String id);

	public void UpdateDoWithdrawalRecoveryByAdmin(String string);

	public void UpdateDormantOneToZero(String id);

	public void UpdateAdminRole(String string, String optionValue);

	public void UpdateUserRole(String string, String optionValue);

	public ArrayList<String> SelectUserProfileInfoByID(String mysqlID);

	public void UpdateUserName(User user);
	
	public void UpdateOpenName(User user);

	public void UpdateOpenEmail(User user);

	public void UpdateOpenPhoneNum(User user);
	
	public void UpdateOpenMajor(User user);

	public void UpdateOpenGrade(User user);

	public User SelectUserInfo(String userLoginID);

	public void InsertWithdrawalUser(User userInfo);

	public void DeleteWithdrawalUser(User user);

	public void DeleteWithdrawalUserList(WithdrawalUser withdrawalUser);

}