package com.mju.groupware.service;

import java.util.ArrayList;

import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;

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

	public void UpdateDoWithdrawalRecoveryByAdmin(String ajaxMsg);

	public void UpdateDormantOneToZero(String id);

	public void UpdateAdminRole(String string, String optionValue);

	public void UpdateUserRole(String string, String optionValue);

	public ArrayList<String> SelectUserProfileInfoByID(String mysqlID);

	public void UpdateUserName(User user);

	public void UpdateOpenPhoneNum(User user);

	public void UpdateOpenGrade(User user);

	public User SelectUserInfo(String userLoginID);

	public String SelectOpenInfo(String userID);

	public int SelectUserIDFromBoardController(String userLoginID);

	public String SelectUserRole(String userLoginID);

	public String SelectUserName(String userLoginID);

	public void UpdateWithdrawal(User user);

	public void UpdateRecoveryWithdrawal(User user);

	public void UpdateWithdrawalByDormant(String string);

	public boolean SelectDormant(String loginID);

	public void UpdateRecoveryDormant(String loginID);

	public String SelectWriter(String userLoginID);

	public String SelectUserIDForDocument(String userLoginID);

	public String SelectTWriterID(String tWriter);

	public String SelectUserIDForMyBoard(String loginID);

	public String SelectEmailForInquiry(String userLoginID);

	public String SelectPhoneNumForInquiry(String userLoginID);

	public String SelectUserIDForDate(String loginID);

	public String SelectIDForReview(String userLoginID);

	public User SelectModifyUserInfo(String loginID);


}