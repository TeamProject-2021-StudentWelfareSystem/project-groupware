package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.UserList;

public interface UserListDao {

	public List<UserList> SelectUserlist() throws Exception;

	public List<UserList> SelectDormantUserList();

	public List<UserList> SelectWithdrawalUserList();

}
