package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.UserList;
import com.mju.groupware.dto.WithdrawalStudent;
import com.mju.groupware.dto.WithdrawalUser;

public interface UserListDao {

	public List<UserList> SelectUserlist() throws Exception;

	public List<UserList> SelectDormantUserList();

	public List<WithdrawalUser> SelectWithdrawalUserList();

	public WithdrawalUser SelectWithdrawalUserListForRecovery(String userLoginID);

	public WithdrawalStudent SelectWithdrawalStudentListForRecovery(String wuserID);

	public void InsertUserForRecovery(WithdrawalUser withdrawalUser);

	public void InsertStudentForRecovery(WithdrawalStudent withdrawalStudent);

}
