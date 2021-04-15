package com.mju.groupware.service;

import java.util.ArrayList;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;

public interface UserService {

	// 회원가입
	public void SignUp(User user);

	// 중복확인
	public boolean IdConfirm(User user);

	// 비번 찾기
	public boolean PwdConfirm(User user);

	// userID(다른 테이블들의 foreign key) 찾기
	public int SelectUserID(Student student);

	public String ShowPassword(User user);

	public void DateUpdate(User user);

	public String currentPW(String id);

	public void modifyPW(User user);

	public boolean pwCheckBeforeModify(String pw, String pw2);

	public ArrayList<String> GetMyPageUserInfo(String userId);

	public ArrayList<String> GetProfileUserInfo(String loginID);

	public void updateUserPhoneNumber(User user);

	public void UpdateUserMajor(User user);
	
	public void UpdateUserColleges(User user);

	public ArrayList<String> GetUser(String userId);

}