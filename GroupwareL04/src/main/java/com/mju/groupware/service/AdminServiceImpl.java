package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserListDao;
import com.mju.groupware.dto.UserList;
import com.mju.groupware.dto.WithdrawalProfessor;
import com.mju.groupware.dto.WithdrawalStudent;
import com.mju.groupware.dto.WithdrawalUser;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserListDao userListDao;

	@Override
	public List<UserList> SelectUserlist() throws Exception {
		return userListDao.SelectUserlist();
	}

	@Override
	public List<UserList> SelectDormantUserList() {
		return userListDao.SelectDormantUserList();
	}

	@Override
	public List<WithdrawalUser> SelectWithdrawalUserList() {
		return userListDao.SelectWithdrawalUserList();
	}

	@Override
	public WithdrawalUser SelectWithdrawalUserListForRecovery(String userLoginID) {
		WithdrawalUser Output = userListDao.SelectWithdrawalUserListForRecovery(userLoginID);
		return Output;
	}

	@Override
	public WithdrawalStudent SelectWithdrawalStudentListForRecovery(String wuserID) {
		WithdrawalStudent Output = userListDao.SelectWithdrawalStudentListForRecovery(wuserID);
		return Output;
	}

	@Override
	public WithdrawalProfessor SelectWithdrawalProfessorListForRecovery(String wuserID) {
		WithdrawalProfessor Output = userListDao.SelectWithdrawalProfessorListForRecovery(wuserID);
		return Output;
	}

	
	@Override
	public void InsertUserForRecovery(WithdrawalUser withdrawalUser) {
		userListDao.InsertUserForRecovery(withdrawalUser);
	}

	@Override
	public void InsertStudentForRecovery(WithdrawalStudent withdrawalStudent) {
		userListDao.InsertStudentForRecovery(withdrawalStudent);
	}

	@Override
	public void InsertProfessorForRecovery(WithdrawalProfessor withdrawalProfessor) {
		userListDao.InsertProfessorForRecovery(withdrawalProfessor);
	}
}
